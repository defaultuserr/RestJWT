package com.Internetx.demo.cfg;


import com.Internetx.demo.Repository.JDBCHandling;
import com.Internetx.demo.model.UserAndRoleModel;
import com.Internetx.demo.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Service
public class GetUserDetailsService implements UserDetailsService {


    // @Autowired
    //private UserRepo userDao;


    @Autowired
    private JDBCHandling jdbcHandling;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.print("load user By Usernme");
        List<SimpleGrantedAuthority> roles = null;


        UserModel myRetrievedUser = jdbcHandling.getUserFromMySQL(s);
        int id = myRetrievedUser.getId();

        List<SimpleGrantedAuthority> rolly = jdbcHandling.getRoles(id);

        if (id > 0) {

            return new User(myRetrievedUser.getLogin(), passwordEncoder.encode(myRetrievedUser.getPassword()), rolly);

        }


        //DAOUser user = userDao.findByUsername(s);
        //DAOUser user = plainSQLInterface.findUserUsingRollNo(s).get(0);
        //Select form database If yound the


        if (myRetrievedUser != null) {

            // roles = Arrays.asList(new SimpleGrantedAuthority(""));
            //roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
            //return new User(user.getUsername(), user.getPassword(), roles);
        }


        // if(s.equals("admin")){
        //   roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        // return new User("nameadmin", "$2y$12$I0Di/vfUL6nqwVbrvItFVOXA1L9OW9kLwe.1qDPhFzIJBpWl76PAe", roles);
        //}
        if (s.equals("user")) {
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
            return new User("nameuser", "$2y$12$VfZTUu/Yl5v7dAmfuxWU8uRfBKExHBWT1Iqi.s33727NoxHrbZ/h2", roles);
        }
        throw new UsernameNotFoundException("User not in list name: " + s);
    }


    public ResponseEntity saveTo(UserAndRoleModel userModel) throws SQLException {

        try {
            long id = jdbcHandling.insertUser(userModel.getUserModel());
            userModel.getUserModel().setId((int) id);
            createRoleSet(userModel);
        } catch (Exception e) {
            System.out.println("error in save TO" + e);
            return new ResponseEntity<>("Not Worked", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Worked", HttpStatus.OK);
    }


    private void createRoleSet(UserAndRoleModel roleModel) {
        jdbcHandling.insertRoleSet(roleModel);


    }

}
