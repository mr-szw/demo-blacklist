package com.blacklist.demo.service;

import com.blacklist.demo.init.BlackListBloomFilter;
import com.blacklist.demo.mapper.UserInfoMapper;
import com.blacklist.demo.utils.AbstractBloomFilter;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author sinbad  on 2019/11/26
 */
@Service
public class UserInfoServiceImpl<T> extends AbstractBloomFilter<T> {

	@Resource
	private UserInfoMapper userInfoMapper;

	@PostConstruct
	public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
		this.userInfoMapper = userInfoMapper;
	}

	public UserInfoServiceImpl(AbstractBloomFilter.BloomFilterConfig bloomFilterConfig) throws Exception {
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
