package com.blacklist.demo.mapper;

import java.util.List;

import com.blacklist.demo.entity.UserInfo;

public interface UserInfoMapper {


    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    List<Long> getAllBlackListUserId();

    UserInfo selectByPrimaryKey(Long id);


    UserInfo selectByUserId(Long userId);
}