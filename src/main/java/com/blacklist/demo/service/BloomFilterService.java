package com.blacklist.demo.service;

import com.blacklist.demo.entity.UserInfo;

/**
 * @author sinbad  on 2019/11/29
 */
public interface BloomFilterService {

	default void initBloomFilter() {
	}

	default void initBloomFilterConfig() {
	}

	default boolean checkUserInBlacklist(Long userId) {

		return false;
	}

	default Long addUserToBlacklist(UserInfo userInfo) {
		return 1L;
	}

	default Long setUserToBlacklist(Long userId) {

		return userId;
	}

}
