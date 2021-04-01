package com.hg.mapper;

import com.hg.model.HGLogBean;
import com.hg.model.UserBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {

    @Select(" select id,json_object_agg(name,password) as name from t_user group by id ")
    List<UserBean> findUser();

    @Delete(" delete from t_user where id = #{id} ")
    void deleteUserById(Integer id);

    @Update(" update t_user set name = #{name},password = #{password} where id = #{id} ")
    void updateUser(UserBean userBean);

    @Insert(" insert into t_user values(#{id},#{name},#{password}) ")
    void saveUser(UserBean userBean);

    @Select(" select * from hgdb_log ")
    List<HGLogBean> findLogAll();
}
