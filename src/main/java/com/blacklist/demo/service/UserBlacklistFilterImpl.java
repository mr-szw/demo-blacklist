package com.blacklist.demo.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.blacklist.demo.mapper.UserInfoMapper;
import com.blacklist.demo.utils.AbstractBloomFilter;

/**
 * @author sinbad  on 2019/11/26
 */

@Component
public class UserBlacklistFilterImpl extends AbstractBloomFilter {

	@Resource
	private UserInfoMapper userInfoMapper;

	@PostConstruct
	public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
		this.userInfoMapper = userInfoMapper;
	}

	private UserBlacklistFilterImpl() {
		super();

	}

	@Override
	public void initBloomFilter() {
		UserBlacklistFilterImpl userBlacklistFilter = new UserBlacklistFilterImpl();
		List<Long> allBlackListUserId = userInfoMapper.getAllBlackListUserId();
		for (Long blackList : allBlackListUserId) {
			userBlacklistFilter.putElement(String.valueOf(blackList));
		}
	}
}
