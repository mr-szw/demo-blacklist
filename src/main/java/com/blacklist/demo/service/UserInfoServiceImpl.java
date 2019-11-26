package com.blacklist.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.blacklist.demo.entity.UserInfo;
import com.blacklist.demo.init.BlackListBloomFilter;
import com.blacklist.demo.mapper.UserInfoMapper;

/**
 * @author sinbad  on 2019/11/26
 */
@Service
public class UserInfoServiceImpl {


	@Resource
	private UserInfoMapper userInfoMapper;

	private BlackListBloomFilter blackListBloomFilter = BlackListBloomFilter.getBlackListBloomFilter();

	public void initBlacklist() {
		BlackListBloomFilter blackListBloomFilter = BlackListBloomFilter.getBlackListBloomFilter();
		List<Long> allBlackListUserId = userInfoMapper.getAllBlackListUserId();
		for (Long blackList : allBlackListUserId) {
			blackListBloomFilter.putBlacklistUserId(blackList);
		}

	}


	public boolean checkUserInBlacklist(Long userId) {
		boolean exist = blackListBloomFilter.checkExist(userId);
		if (!exist) {
			return exist;
		}
		UserInfo userInfo = userInfoMapper.selectByUserId(userId);
		if (userInfo.getBlackStatus() == 1) {
			return true;
		}
		return false;
	}

	public Long addUserToBlacklist(UserInfo userInfo) {
		int insertId = userInfoMapper.insert(userInfo);
		if (insertId == 1) {
			blackListBloomFilter.putBlacklistUserId(userInfo.getUserId());
		}
		return userInfo.getUserId();
	}

	public Long setUserToBlacklist(Long userId) {
		blackListBloomFilter.putBlacklistUserId(userId);
		return userId;
	}


}
