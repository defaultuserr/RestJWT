package com.Internetx.demo.cfg;

import Repo.UserRepo;
import com.Internetx.demo.model.DAOUser;
import com.Internetx.demo.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class GetUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> roles = null;
        if(s.equals("admin")){
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new User("nameadmin", "$2y$12$I0Di/vfUL6nqwVbrvItFVOXA1L9OW9kLwe.1qDPhFzIJBpWl76PAe", roles);
        }
        if(s.equals("user")){
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
            return new User("nameuser", "$2y$12$VfZTUu/Yl5v7dAmfuxWU8uRfBKExHBWT1Iqi.s33727NoxHrbZ/h2", roles);
        }
        throw new UsernameNotFoundException("User not in list name: " + s);
    }



    public DAOUser save(UserDTO userDTO){
        DAOUser newUSer = new DAOUser();
        newUSer.setUsername(userDTO.getUsername());
        newUSer.setPassword(passwordEncoder.encode( userDTO.getPassword()));
        newUSer.setRole(userDTO.getRole());
        return userRepo.save(newUSer);

    }
}
