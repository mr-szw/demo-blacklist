package com.blacklist.demo.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author sinbad  2019/11/26
 */
public class ExecutorFuture{

	private static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 20, 100, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));



	public static Future execure(Callable callable) {
		Future<?> submit = null;
		try {
			submit = poolExecutor.submit(() -> {
				try {
					callable.call();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();

		}
		return submit;
	}

}
