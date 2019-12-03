package com.blacklist.demo.event;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.blacklist.demo.module.BloomFilterConfig;

/**
 * @author sinbad  on 2019/11/26
 */
@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {



	@Override
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
		doInitSomething();
	}


	private void doInitSomething() {

		BloomFilterConfig bloomFilterConfig = new BloomFilterConfig();
		bloomFilterConfig.setContainerSize(100L);
		bloomFilterConfig.setEffectTime(-1L);
		bloomFilterConfig.setExpiredTime(-1L);
		bloomFilterConfig.setProjectId(-1L);




	}
}
