package com.blacklist.demo.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

import javax.print.DocFlavor.BYTE_ARRAY;

import com.blacklist.demo.module.BloomFilterConfig;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @author sinbad  on 2019/11/29
 */
public abstract class AbstractBloomFilter<T> {

	private BloomFilter bloomFilter;

	public AbstractBloomFilter(BloomFilterConfig bloomFilterConfig) throws Exception {

		Type actualTypeArgument = ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

		if (!(actualTypeArgument instanceof Class<?>)) {
			throw new RuntimeException("class type no support");
		}


		if (Long.class.isAssignableFrom((Class<?>) actualTypeArgument)) {
			bloomFilter = BloomFilter
					.create(Funnels.longFunnel(), bloomFilterConfig.getContainerSize());
		} else if (String.class.isAssignableFrom((Class<?>) actualTypeArgument)) {
			bloomFilter = BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8),
					bloomFilterConfig.getContainerSize());
		} else if (Integer.class.isAssignableFrom((Class<?>) actualTypeArgument)) {
			bloomFilter = BloomFilter
					.create(Funnels.integerFunnel(), bloomFilterConfig.getContainerSize());
		} else if (BYTE_ARRAY.class.isAssignableFrom((Class<?>) actualTypeArgument)) {
			bloomFilter = BloomFilter
					.create(Funnels.byteArrayFunnel(), bloomFilterConfig.getContainerSize());
		} else {
			throw new RuntimeException("class type no support");
		}
	}

	public boolean putElement(T element) {
		return bloomFilter.put(element);
	}

	public boolean checkUserInBlacklist(T element) {
		return bloomFilter.mightContain(element);
	}

	//用于热启动
	public abstract void initBloomFilter();

}
