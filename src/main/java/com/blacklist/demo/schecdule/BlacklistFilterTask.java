package com.blacklist.demo.schecdule;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.blacklist.demo.service.UserInfoServiceImpl;
import com.blacklist.demo.utils.DateHelper;

/**
 * Create by Dawei on 2019/11/26
 */
@Component
public class BlacklistFilterTask {

	@Resource
	private UserInfoServiceImpl userInfoService;


	//cron方式执行：     秒   分 时 日 月 年
	@Scheduled(cron = "0/15 *  *  * *  ?")
	public void initBlacklistFilterTask() {
		System.out.println("initBlacklistFilterTask + " + DateHelper.turnDateFormat(new Date(), "HH:mm ss SSS"));
		userInfoService.initBlacklist();
	}

	//fixedRate方式执行：  从上一个任务开始到下一个[任务开始]的间隔 启动后立刻执行 之后间隔执行 2 * 1000s
	@Scheduled(fixedRate = 1000 * 2)
	public void testFixedRateTask() {
		//System.out.println("testFixedRateTask + " + DateHelper.turnDateFormat(new Date(), "HH:mm ss SSS"));
	}

	//fixedDelay方式执行： 从上一个任务[完成开始]到下一个任务开始的间隔
	@Scheduled(fixedDelay = 5 * 1000)
	public void testFixedDelayTask() {
		//System.out.println("testFixedDelayTask + " + DateHelper.turnDateFormat(new Date(), "HH:mm ss SSS"));

	}

	//initialDelay方式执行： 任务首次执行延迟的时间
	@Scheduled(initialDelay = 3 * 1000, fixedRate = 1000 * 3)
	public void testInitialDelayTask() {
		//System.out.println("testInitialDelayTask + " + DateHelper.turnDateFormat(new Date(), "HH:mm ss SSS"));
	}






}




