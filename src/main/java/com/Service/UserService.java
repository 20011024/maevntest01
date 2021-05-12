package com.Service;

import com.entity.Page;
import com.entity.User;

import java.util.List;

public interface UserService {
    User getById(Integer id);
    List<User> getAllUser();
    int add(User user);
    int update(User user);
    int delete(Integer id);
    List<User> getLike(String name, String userRole);
    User login(String userCode, String password);
    int pwdModify(User user);

    User getUserCode(String userCode);

    List<User> getAll();

    int getTotalCount(String userName, Integer userRole);
    List<User> getPageList(String userName, Integer userRole, Integer pageNo, Integer pageSize);
    Page getPageList2(String userName, Integer userRole, Integer pageNo, Integer pageSize);


}
