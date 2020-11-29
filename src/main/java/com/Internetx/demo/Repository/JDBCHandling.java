package com.Internetx.demo.Repository;

import com.Internetx.demo.model.UserModel;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

@Service
public class JDBCHandling {

    static String DB_URL = "";

    @Value("${spring.datasource.url}")
    public void setUrl(String url) {
        this.DB_URL = url;
    }


    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";
    PreparedStatement statement = null;
    Connection conn = null;

    public ResponseEntity<String> insertUser(UserModel userModel) {
        String login = userModel.getLoginName();
        String password = userModel.getPassword();
        String fname = userModel.getF_name();
        String lname = userModel.getL_name();
        String email = userModel.getEmail();


        try {
            //STEP 2: Register JDBC driver
            //Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //STEP 3: Open a connection
            statement = conn.prepareStatement("INSERT INTO user (login, password, fname, lname, email) Values (?, ?, ?, ?, ?)");
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, fname);
            statement.setString(4, lname);
            statement.setString(5, email);

            statement.executeUpdate();
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
        System.out.println("Goodbye!");


        return ResponseEntity.ok("it is ok");

    }
    public List<SimpleGrantedAuthority> getRoles(int id) {


      Hier bin ich und muss mit der id die rollen bekommen   
    }

    public UserModel getUserFromMySQL(String s) {

        Statement std;


        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String statement = "Select * from users where name = " + s;
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
            UserModel tempUserModel = new UserModel( loginName, password,id, f_name, l_name, email);
            return tempUserModel;


        } catch (Exception e) {


        }


        return null;


    }
}//end JDBCExample