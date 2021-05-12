package com.mapper;

import com.entity.Page;
import com.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User getById(@Param("uid") Integer id);
    List<User> getAllUser();
    int add(User user);
    int update(User user);
    int delete(@Param("id") Integer id);
    List<User> getLike(@Param("name") String name, @Param("userRole") String userRole);
    User login(@Param("userCode") String userCode, @Param("password") String password);
    int pwdModify(User user);

    User getUserCode(@Param("userCode") String userCode);

    List<User> getAll();

    List<User> getPageList(@Param("userName") String userName, @Param("userRole") Integer userRole, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);
    int getTotalCount(@Param("userName") String userName, @Param("userRole") Integer userRole);
    Page getPageList2(@Param("userName") String userName, @Param("userRole") Integer userRole, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);


}

