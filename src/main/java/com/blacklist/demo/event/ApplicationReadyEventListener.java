package com.blacklist.demo.event;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Create by Dawei on 2019/11/26
 */
@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {


	@Override
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
		doInitSomething();
	}


	private void doInitSomething() {

	}
}
