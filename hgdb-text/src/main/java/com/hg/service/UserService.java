package com.hg.service;

import com.hg.model.HGLogBean;
import com.hg.model.UserBean;

import java.util.List;

public interface UserService {
    List<UserBean> findUser();

    void deleteUserById(Integer id);

    void updateUser(UserBean userBean);

    List<HGLogBean> findLogAll();
}
