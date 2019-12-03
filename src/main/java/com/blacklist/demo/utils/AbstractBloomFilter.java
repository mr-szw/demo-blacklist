package com.blacklist.demo.utils;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import lombok.Data;

/**
 * @author sinbad  on 2019/11/29
 */
@Data
public abstract class AbstractBloomFilter {

	private BloomFilter<String> bloomFilter;

	private Long projectId;

	//过滤器名字
	private String filterName = "default";

	//过滤器名称
	private String filterDesc = "default-desc";

	//容器大小
	private long containerSize = 100000;

	//生效时间
	private Long effectTime = -1L;

	//失效时间
	private Long expiredTime = -1L;

	public AbstractBloomFilter(Long projectId,
			String filterName, String filterDesc, long containerSize, Long effectTime,
			Long expiredTime) {
		this.bloomFilter = BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8), containerSize);
		this.projectId = projectId;
		this.filterName = filterName;
		this.filterDesc = filterDesc;
		this.containerSize = containerSize;
		this.effectTime = effectTime;
		this.expiredTime = expiredTime;
	}

	public AbstractBloomFilter() {
		this.bloomFilter = BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8), containerSize);
	}

	public boolean putElement(String element) {
		return bloomFilter.put(element);
	}

	public boolean checkUserInBlacklist(String element) {
		return bloomFilter.mightContain(element);
	}

	//用于热启动
	public abstract void initBloomFilter();

}
