package com.Internetx.demo.Repository;


import com.Internetx.demo.Util;
import com.Internetx.demo.model.UserAndRoleModel;
import com.Internetx.demo.model.UserModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

@Service
public class JDBCHandling {

    static String DB_URL = "";

    @Autowired
    Util util;

    @Value("${spring.datasource.url}")
    public void setUrl(String url) {
        this.DB_URL = url;
    }


    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";
    PreparedStatement statement = null;
    Connection conn = null;

    public String updateUserById(int id, UserModel userModel) {

        Statement std;
        LinkedHashMap<String, String> temp = util.analyzeUserModel(userModel);
        String statement = "update user set ";

        for (Map.Entry<String,String> entry : temp.entrySet()) {

            String key = entry.getKey();
            if(key =="Id")continue;
            String value = entry.getValue();

            statement += key +  " =  \"" + value + "\" ,";
            // now work with key and value...
        }

        statement = (statement.substring(0, statement.length() - 1));
        statement +="where id = " +  id ;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            this.statement = conn.prepareStatement(statement);
            this.statement.executeUpdate(statement);






        } catch (Exception e) {


        }


        return "ok";
    }

    public String deleteUserById(int id) {

        Statement std;
        String total = "";
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String statementSt = "Delete  from  user where id = \" " + id + "  \"";
            statement = conn.prepareStatement(statementSt);

            statement.executeUpdate();
            //STEP 3: Open a connection
            return "User with id " + id + "deleted";


        } catch (Exception e) {
            System.out.print("in getuser by id " + e);
            return "did not work";
        }


    }

    private int matchBooleanToTinyInt(String set) {
        if (set == "true") return 1;
        else return 0;

    }

    public void insertRoleSet(UserAndRoleModel roleModel) {
        //order is important
        LinkedHashMap<String, Boolean> resultsHashMap = util.analyze(roleModel.getRoleModel());

        int userId = roleModel.getUserModel().getId();

        try {
            //STEP 2: Register JDBC driver
            //Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //STEP 3: Open a connection
            statement = conn.prepareStatement("INSERT INTO role (user_id,role_admin, role_develop ,role_cctld, role_gtld, role_billing, role_registry, role_purchase_read, role_purchase_write, role_sale_write, role_sql ) Values (" + userId + ", ?, ?, ?, ?,?,?,?,?,?,?)");
            int temp = 1;

            for (Map.Entry<String, Boolean> entry : resultsHashMap.entrySet()) {

                statement.setString(temp, "" + matchBooleanToTinyInt(String.valueOf(entry.getValue())));
                temp++;
            }

            try {
                statement.executeUpdate();
            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("error in execute update " + e);
                return;
            }
            System.out.println("Inserted records into the table...");

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (statement != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try


    }


    public String getUserById(int id) {
        Statement std;
        String total = "";
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String statement = "Select * from  user where id = \" " + id + "  \"";

            std = conn.createStatement();
            ResultSet rs = std.executeQuery(statement);
            //STEP 3: Open a connection
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    total += columnValue + " is " + rsmd.getColumnName(i) + ", ";
                    //System.out.print();
                }
            }


        } catch (Exception e) {
            System.out.print("in getuser by id " + e);
        }
        return total;
    }

    public long
    insertUser(UserModel userModel) throws SQLException {
        String login = userModel.getLogin();
        String password = userModel.getPassword();
        String fname = userModel.getF_name();
        String lname = userModel.getL_name();
        String email = userModel.getEmail();
        long userId = 0;


        try {
            //STEP 2: Register JDBC driver
            //Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //STEP 3: Open a connection
            statement = conn.prepareStatement("INSERT INTO user (login, password, fname, lname, email) Values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, fname);
            statement.setString(4, lname);
            statement.setString(5, email);

            int affectedRows = statement.executeUpdate();
            System.out.println("Inserted records into the table...");
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    userId = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (statement != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");


        return userId;

    }

    public List<SimpleGrantedAuthority> getRoles(int id) {
        Statement std;
        HashMap<Roles, Integer> roles = new HashMap<Roles, Integer>();
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String statement = "Select * from role where user_id = " + id;

            std = conn.createStatement();
            ResultSet rs = std.executeQuery(statement);
            int role_admin;
            int role_develop;
            int role_cctld;
            int role_gtld;
            int role_billing;
            int role_registry;
            int role_purchase_read;
            int role_purchase_write;
            int role_sale_write;
            int role_sql;
            while (rs.next()) {
                //Retrieve by column name
                id = rs.getInt("id");
                role_admin = rs.getInt("role_admin");
                roles.put(Roles.ROLE_ADMIN, role_admin);
                role_develop = rs.getInt("role_develop");
                roles.put(Roles.ROLE_DEVELOP, role_develop);
                role_cctld = rs.getInt("role_cctld");
                roles.put(Roles.ROLE_CCTLD, role_cctld);
                role_gtld = rs.getInt("role_gtld");
                roles.put(Roles.ROLE_GTLD, role_gtld);
                role_billing = rs.getInt("role_billing");
                roles.put(Roles.ROLE_BILLING, role_billing);
                role_registry = rs.getInt("role_registry");
                roles.put(Roles.ROLE_REGISTRY, role_registry);
                role_purchase_read = rs.getInt("role_purchase_read");
                roles.put(Roles.ROLE_PURCHASE_READ, role_purchase_read);
                role_purchase_write = rs.getInt("role_purchase_write");
                roles.put(Roles.ROLE_PURCHASE_WRITE, role_purchase_write);
                role_sale_write = rs.getInt("role_sale_write");
                roles.put(Roles.ROLE_SALE_WRITE, role_sale_write);
                role_sql = rs.getInt("role_sql");
                roles.put(Roles.ROLE_SQL, role_sql);

                // matchSQLtoRoles(roles);


            }
        } catch (Exception e) {
            System.out.print("excetpi  " + e);


        }


        return matchSQLtoRoles(roles);
    }

    public enum Roles {
        ROLE_ADMIN, ROLE_DEVELOP, ROLE_CCTLD, ROLE_GTLD, ROLE_BILLING, ROLE_REGISTRY, ROLE_PURCHASE_READ, ROLE_PURCHASE_WRITE, ROLE_SALE_WRITE, ROLE_SQL


    }

    private List<SimpleGrantedAuthority> matchSQLtoRoles(HashMap<Roles, Integer> list) {
        System.out.print("Match sql to roles");
        System.out.print(Roles.ROLE_CCTLD.toString());
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        for (Roles role : Roles.values()) {
            String temp = role.toString();
            if (list.get(role) == 1 && temp != null) {
                roles.add(new SimpleGrantedAuthority(temp));


            }


            System.out.print(list.get(role));

        }


        return roles;
    }

    public UserModel getUserFromMySQL(String s) {

        Statement std;


        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String statement = "Select * from user where login = \"" + s + " \"";
            std = conn.createStatement();
            ResultSet rs = std.executeQuery(statement);
            int id = 0;
            String loginName = "";
            String password = "";
            String f_name = "";
            String l_name = "";
            String email = "";

            while (rs.next()) {
                //Retrieve by column name
                id = rs.getInt("id");
                loginName = rs.getString("login");
                password = rs.getString("password");
                f_name = rs.getString("fname");
                l_name = rs.getString("lname");
                email = rs.getString("email");

            }
            UserModel tempUserModel = new UserModel(loginName, password, id, f_name, l_name, email);
            return tempUserModel;


        } catch (Exception e) {


        }


        return null;


    }
}//end JDBCExample