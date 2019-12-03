package com.blacklist.demo.service;

import org.junit.jupiter.api.Test;

import com.blacklist.demo.module.BloomFilterConfig;
import com.blacklist.demo.utils.AbstractBloomFilter;

/**
 * @author by Dawei on 2019/12/2.
 */

class UserBlacklistFilterImplTest {

    @Test void testMethod() throws Exception {
        BloomFilterConfig bloomFilterConfig = new BloomFilterConfig();
        bloomFilterConfig.setContainerSize(1000L);
        AbstractBloomFilter userInfoService = new UserBlacklistFilterImpl(bloomFilterConfig);

        System.out.println(userInfoService.putElement(1L));
        System.out.println(userInfoService.checkUserInBlacklist(1L));
    }
}