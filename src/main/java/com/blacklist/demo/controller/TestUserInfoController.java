package com.blacklist.demo.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blacklist.demo.entity.UserInfo;
import com.blacklist.demo.mapper.UserInfoMapper;
import com.blacklist.demo.redis.sender.BlacklistAddSender;
import com.blacklist.demo.utils.UniqueIDUtil;

/**
 * @author by Dawei on 2019/11/17.
 */
@RestController
@RequestMapping(value = "/test/user")
public class TestUserInfoController {


    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private BlacklistAddSender redisChannelSender;

    @GetMapping(value = "/detail")
    public Object getUserInfoDetail(Long userId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUpdateTime(System.currentTimeMillis());
        userInfo.setCreateTime(System.currentTimeMillis());
        Long uniqueID = UniqueIDUtil.getUniqueID();
        userInfo.setUserId(uniqueID);
        userInfo.setBlackStatus(1);
        userInfo.setUserName(UUID.randomUUID().toString());
        int insertSelective = userInfoMapper.insertSelective(userInfo);
        System.out.println(insertSelective);
        Long id = userInfo.getId();
        UserInfo userInfoById = userInfoMapper.selectByPrimaryKey(id);
        UserInfo userInfoByUserId = userInfoMapper.selectByUserId(uniqueID);
        List<Long> userIdList = userInfoMapper.getAllBlackListUserId();

        redisChannelSender.pushMessageByChannel("ListenerTopicA", "Test");


        return userIdList; }

}
