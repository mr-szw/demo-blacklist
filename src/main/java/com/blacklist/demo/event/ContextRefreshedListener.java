package com.blacklist.demo.event;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.blacklist.demo.service.UserBlacklistFilterImpl;

/**
 * @author sinbad  on 2019/11/26
 */
@Component public class ContextRefreshedListener
		implements ApplicationListener<ContextRefreshedEvent> {

	@Resource private UserBlacklistFilterImpl userInfoService;

	@Override public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		//userInfoService.initBlacklist();
	}

}
