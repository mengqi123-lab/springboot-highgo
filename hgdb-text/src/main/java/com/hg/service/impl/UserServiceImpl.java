package com.hg.service.impl;

import com.hg.mapper.UserMapper;
import com.hg.model.HGLogBean;
import com.hg.model.UserBean;
import com.hg.service.UserService;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /*@Autowired
    private JdbcTemplate jdbcTemplate;*/

    @Override
    public List<UserBean> findUser() {

        /*String sql = "select *
        from t_user";

        BeanPropertyRowMapper<UserBean> rowMapper = new BeanPropertyRowMapper<>(UserBean.class);
        List<UserBean> query = jdbcTemplate.query(sql, rowMapper);
        query.forEach(userBean -> System.out.println(userBean));*/

        /*UserBean userBean = null;
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
        while (rs.next()){
            userBean = new UserBean(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("password"));
        }
        System.out.println(userBean);*/

        List<UserBean> user = userMapper.findUser();
        user.stream()
                .map(userBean -> userBean.getName())
                .forEach(System.out::println);

        user.stream()
                .filter(e -> e.getId() < 2)
                .limit(3)
                .skip(2)
                .distinct()
                .forEach(System.out::println);

        return user;
    }

    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }

    @Override
    public void updateUser(UserBean userBean) {
        if(userBean.getId() != null && userBean != null){
            userMapper.updateUser(userBean);
        }else{
            userMapper.saveUser(userBean);
        }
    }

    @Override
    public List<HGLogBean> findLogAll() {
        List<HGLogBean> logBeans = userMapper.findLogAll();
        logBeans.forEach(log -> System.out.println(log));

        logBeans.stream()
                    .map(log -> log.getUserName())
                    .forEach(System.out::print);

        ArrayList<String> logName = new ArrayList<>();
        logBeans.forEach(l -> logName.add(l.getUserName()));

        return logBeans;
    }
}
