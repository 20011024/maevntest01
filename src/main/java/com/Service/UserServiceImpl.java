package com.Service;

import com.entity.Page;
import com.entity.User;
import com.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    Page page=new Page();
    @Autowired
    UserMapper userMapper;
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public int add(User user) {
        return userMapper.add(user);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int delete(Integer id) {
        return userMapper.delete(id);
    }

    @Override
    public List<User> getLike(String name,String userRole) {
        return userMapper.getLike(name,userRole);
    }

    @Override
    public User login(String userCode, String password) {
        return userMapper.login(userCode,password);
    }

    @Override
    public int pwdModify(User user) {
        return userMapper.pwdModify(user);
    }

    @Override
    public User getUserCode(String userCode) {
        return userMapper.getUserCode(userCode);
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    public int getTotalCount(String userName,Integer userRole) {
        return userMapper.getTotalCount(userName,userRole);
    }

    @Override
    public List<User> getPageList(String userName,Integer userRole,Integer pageNo, Integer pageSize) {
        return userMapper.getPageList(userName,userRole,pageNo,pageSize);
    }

    @Override
    public Page getPageList2(String userName,Integer userRole,Integer pageNo, Integer pageSize) {

        //1，获取总条数
        int totalCount = this.getTotalCount( userName, userRole);
        //2,确定总页数
        int totalPageCount
                =totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        //对pageNo和pageSize做合法处理
        //pageNo=pageNo<=0?1:pageNo;
        pageNo=pageNo>totalPageCount?totalPageCount:pageNo;
        pageNo=pageNo<=0?1:pageNo;

        pageSize=pageSize<=0?page.getPageSize():pageSize;
        //3，获取当前页面集合

        List<User> userList = this.getPageList(userName,userRole,(pageNo-1)*pageSize,  pageSize);
        //4，返回初始化后的Page
        page.setCurrPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotalCount(totalCount);
        page.setTotalPageCount(totalPageCount);
        page.setUserList(userList);
        return page;
    }


}
