package com.blacklist.demo.utils;

import java.util.List;

import com.blacklist.demo.module.BloomFilterConfig;

import lombok.Data;

/**
 * @author Dawei on 2019/12/3
 */
@Data
public class BloomFilterRepository {

	private static volatile BloomFilterRepository instance;

	private BloomFilterRepository() {
	}

	public static BloomFilterRepository getInstance() {
		BloomFilterRepository bloomFilterRepository = instance;
		if (bloomFilterRepository == null) {
			synchronized (BloomFilterRepository.class) {
				bloomFilterRepository = instance;
				if (bloomFilterRepository == null) {
					bloomFilterRepository = new BloomFilterRepository();
					instance = bloomFilterRepository;
				}
			}
		}
		return bloomFilterRepository;
	}


	private List<BloomFilterConfig>  bloomFilterConfigList;



}
