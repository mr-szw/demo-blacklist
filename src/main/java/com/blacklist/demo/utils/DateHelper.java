package com.blacklist.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by Dawei on 2019/11/26
 */
public class DateHelper {





	public static String turnDateFormat(Date date, String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}

}
