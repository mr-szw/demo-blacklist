package com.blacklist.demo.init;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;


/**
 * 黑名单 数据初始化
 * @author sinbad  on 2019/11/26
 *
 */
public class BlackListBloomFilter {

	private static final BlackListBloomFilter Instance = new BlackListBloomFilter();
	private BlackListBloomFilter() {
	}
	public static BlackListBloomFilter getBlackListBloomFilter() {
		return Instance;
	}

	private BloomFilter<Long> bloomFilter = BloomFilter.create(Funnels.longFunnel(), 10000);



	public boolean putBlacklistUserId(Long userId) {
		return this.bloomFilter.put(userId);
	}

	public boolean checkExist(Long userId) {
		return this.bloomFilter.mightContain(userId);
	}


}
