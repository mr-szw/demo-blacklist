package com.blacklist.demo.utils;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import javax.print.DocFlavor.BYTE_ARRAY;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sinbad  on 2019/11/29
 */
public abstract class AbstractBloomFilter<T> {


    private BloomFilter bloomFilter;


    private static final Logger logger = LoggerFactory.getLogger(AbstractBloomFilter.class);


    public AbstractBloomFilter(BloomFilterConfig bloomFilterConfig) throws Exception {

        Field elementClass = bloomFilterConfig.getClass().getDeclaredField("elementClass");
        Type genericType = elementClass.getGenericType();

        System.out.println(genericType.getTypeName());
        if (genericType == Long.class) {
            bloomFilter = BloomFilter.create(Funnels.longFunnel(), bloomFilterConfig.getContainerSize());
        } else if (genericType == String.class) {
            bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("UTF-8")), bloomFilterConfig.getContainerSize());
        } else if (genericType == Integer.TYPE) {
            bloomFilter = BloomFilter.create(Funnels.integerFunnel(), bloomFilterConfig.getContainerSize());
        } else if (genericType == BYTE_ARRAY.class) {
            bloomFilter = BloomFilter.create(Funnels.byteArrayFunnel(), bloomFilterConfig.getContainerSize());
        }
//        }else if (genericType == SequentialFunnel.class) {
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


    @Data
    public static class BloomFilterConfig<T> {
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

        //element type class
        private T elementClass;
    }

}
