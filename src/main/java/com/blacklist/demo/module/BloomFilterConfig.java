package com.blacklist.demo.module;

import com.blacklist.demo.utils.AbstractBloomFilter;
import com.google.common.hash.BloomFilter;

import lombok.Data;

@Data
public class BloomFilterConfig {

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

	//
	private BloomFilter<String> bloomFilter;

}