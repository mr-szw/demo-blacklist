package com.blacklist.demo.utils;

import java.nio.charset.Charset;

import javax.print.DocFlavor.BYTE_ARRAY;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blacklist.demo.module.BloomFilterConfig;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @author sinbad  on 2019/11/29
 */
public abstract class AbstractBloomFilter<T> {

	private BloomFilter bloomFilter;

	private static final Logger logger = LoggerFactory.getLogger(AbstractBloomFilter.class);

	public AbstractBloomFilter(BloomFilterConfig bloomFilterConfig) throws Exception {

		Class<?> superclass = this.getClass().getSuperclass();

		Class<?> genericType = GenericUtil.getGenericType(superclass);

		System.out.println(genericType);

		if (genericType == Long.class) {
			bloomFilter = BloomFilter
					.create(Funnels.longFunnel(), bloomFilterConfig.getContainerSize());
		} else if (genericType == String.class) {
			bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("UTF-8")),
					bloomFilterConfig.getContainerSize());
		} else if (genericType == Integer.TYPE) {
			bloomFilter = BloomFilter
					.create(Funnels.integerFunnel(), bloomFilterConfig.getContainerSize());
		} else if (genericType == BYTE_ARRAY.class) {
			bloomFilter = BloomFilter
					.create(Funnels.byteArrayFunnel(), bloomFilterConfig.getContainerSize());
		}
		//        else if (genericType == SequentialFunnel.class) {
		//            bloomFilter = BloomFilter.create(Funnels(), bloomFilterConfig.getContainerSize());
		//        }
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
