package com.blacklist.demo.service;

import org.junit.jupiter.api.Test;


/**
 * @author by Dawei on 2019/12/2.
 */

class UserInfoServiceImplTest {

    @Test
    void testMethod() throws Exception {
        UserInfoServiceImpl.BloomFilterConfig<Long> bloomFilterConfig = new UserInfoServiceImpl.BloomFilterConfig<>();

        UserInfoServiceImpl<Long> userInfoService = new UserInfoServiceImpl<>(bloomFilterConfig);
        System.out.println(userInfoService.putElement(1L));
        System.out.println(userInfoService.checkUserInBlacklist(1L));
    }
}