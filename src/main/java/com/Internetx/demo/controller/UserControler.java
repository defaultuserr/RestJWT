package com.Internetx.demo.controller;


import com.Internetx.demo.Repository.JDBCHandling;
import com.Internetx.demo.model.RoleModel;
import com.Internetx.demo.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserControler {

    @Autowired
    private JDBCHandling jdbcHandling;

    @RequestMapping({"/hellouser"})
    public String helloUser() {
        return "Hello User";
    }

    @RequestMapping({"/helloadmin"})
    public String helloAdmin() {
        return "Hello Admin";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String showUserWithId(@PathVariable("id") int id) {
        return jdbcHandling.getUserById(id);
        //return "show user" + id;
    }
    @RequestMapping(value="/user/{id}", method =  RequestMethod.PUT)
    @ResponseBody
    public String updateUserWithId(@RequestBody UserModel userModel, @PathVariable("id") int id){

        int i = 0;
        jdbcHandling.updateUserById(id, userModel);

        return "updated";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUserWithId(@PathVariable("id") int id) {
         return jdbcHandling.deleteUserById(id);
        //return "show user" + id;
    }


}
