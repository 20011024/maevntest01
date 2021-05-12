package com.Service;

import com.entity.Role;
import com.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
   private RoleMapper roleMapper;
    @Override
    public List<Role> getList() {
        return roleMapper.getList();
    }
}
