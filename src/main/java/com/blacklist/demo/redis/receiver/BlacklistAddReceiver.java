package com.blacklist.demo.redis.receiver;

import java.nio.charset.StandardCharsets;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import com.blacklist.demo.entity.BlacklistInfo;
import com.blacklist.demo.service.UserBlacklistFilterImpl;
import com.blacklist.demo.utils.GsonUtil;

/**
 * @author by Dawei on 2019/4/30.
 * Redis 消息监听
 */
@Component
public class BlacklistAddReceiver implements MessageListener {

	private static final Logger logger = LoggerFactory.getLogger(BlacklistAddReceiver.class);

	@Resource
	private UserBlacklistFilterImpl userBlacklistFilter;

	/**
	 * 监听消息内容
	 *
	 * @param message 传送的消息 包含消息渠道和消息体
	 * @param pattern 订阅模式
	 */
	@Override
	public void onMessage(Message message, byte[] pattern) {
		String patternType = new String(pattern, StandardCharsets.UTF_8);
		logger.info("get message info message is ={} patternType={}", GsonUtil.toJson(message),
				patternType);

		byte[] channel = message.getChannel();
		String channelName = new String(channel, StandardCharsets.UTF_8);

		byte[] body = message.getBody();
		String messageBody = new String(body, StandardCharsets.UTF_8);

		BlacklistInfo blacklistInfo = GsonUtil.fromJson(messageBody, BlacklistInfo.class);

		if (blacklistInfo != null) {
			doOperationOnBloomFilter(blacklistInfo);
		}
	}

	private void doOperationOnBloomFilter(BlacklistInfo blacklistInfo) {
		if (blacklistInfo == null) {
			return;
		}
		Long effectTime = userBlacklistFilter.getEffectTime();
		Long expiredTime = userBlacklistFilter.getExpiredTime();

		long currentTimeMillis = System.currentTimeMillis();
		if (effectTime < currentTimeMillis && expiredTime > currentTimeMillis) {
			userBlacklistFilter.putElement(blacklistInfo.getUserId());
		}
	}

}
