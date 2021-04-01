package com.hg.controller;

import com.hg.model.HGLogBean;
import com.hg.model.UserBean;
import com.hg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("findUser")
    public List<UserBean> findUser(){
        return userService.findUser();
    }

    @RequestMapping("deleteUserById")
    public Boolean deleteUserById(Integer id){
        try{
            userService.deleteUserById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("updateUser")
    public Boolean updataUser(UserBean userBean){
        try{
            userService.updateUser(userBean);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("findLogAll")
    public List<HGLogBean> findLogAll(){
        return userService.findLogAll();
    }
}
