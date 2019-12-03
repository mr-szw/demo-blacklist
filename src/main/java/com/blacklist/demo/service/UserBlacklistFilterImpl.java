package com.blacklist.demo.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.blacklist.demo.init.BlackListBloomFilter;
import com.blacklist.demo.mapper.UserInfoMapper;
import com.blacklist.demo.module.BloomFilterConfig;
import com.blacklist.demo.utils.AbstractBloomFilter;

import lombok.Data;


/**
 * @author sinbad  on 2019/11/26
 */

@Data
@Component
public class UserBlacklistFilterImpl extends AbstractBloomFilter<Long> {

	@Resource
	private UserInfoMapper userInfoMapper;

	private BloomFilterConfig bloomFilterConfig;

	@PostConstruct
	public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
		this.userInfoMapper = userInfoMapper;
	}

	public UserBlacklistFilterImpl(BloomFilterConfig bloomFilterConfig) throws Exception {
		super(bloomFilterConfig);
	}

	@Override
	public void initBloomFilter() {
		BlackListBloomFilter blackListBloomFilter = BlackListBloomFilter.getBlackListBloomFilter();
		List<Long> allBlackListUserId = userInfoMapper.getAllBlackListUserId();
		for (Long blackList : allBlackListUserId) {
			blackListBloomFilter.putBlacklistUserId(blackList);
		}
	}

}
