package com.lnsoft.service;

import com.lnsoft.mapper.UserMapper;
import com.lnsoft.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by chr on 2018/12/7/0007.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public List<User> queryUserInfoAll() {
        return userMapper.selectAll();
    }
}
