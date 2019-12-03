package com.blacklist.demo.redis.receiver;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.blacklist.demo.entity.BlacklistInfo;
import com.blacklist.demo.module.BloomFilterConfig;
import com.blacklist.demo.utils.AbstractBloomFilter;
import com.blacklist.demo.utils.BloomFilterRepository;
import com.blacklist.demo.utils.GsonUtil;

/**
 * @author by Dawei on 2019/4/30.
 * Redis 消息监听
 */
@Component
public class BlacklistAddReceiver implements MessageListener {

	private static final Logger logger = LoggerFactory.getLogger(BlacklistAddReceiver.class);

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

		}
	}

	private void doOperationOnBloomFilter(BlacklistInfo blacklistInfo) {
		if (blacklistInfo == null) {
			return;
		}

		Long projectId = blacklistInfo.getProjectId();
		BloomFilterRepository bloomFilterRepository = BloomFilterRepository.getInstance();
		List<BloomFilterConfig> bloomFilterConfigList = bloomFilterRepository
				.getBloomFilterConfigList();

		if (CollectionUtils.isEmpty(bloomFilterConfigList)) {
			return;
		}
		List<Long> projectIdList = bloomFilterConfigList.stream().map(BloomFilterConfig::getProjectId)
				.collect(Collectors.toList());
		Map<Long, List<BloomFilterConfig>> bloomConfigMap = bloomFilterConfigList.stream()
				.collect(Collectors.groupingBy(BloomFilterConfig::getProjectId));
		if (projectIdList.contains(projectId)) {
			List<BloomFilterConfig> bloomFilterConfigs = bloomConfigMap.get(projectId);
			BloomFilterConfig bloomFilterConfig = bloomFilterConfigs.get(0);
		}
	}

}
