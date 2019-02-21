package com.ccerp.utils;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	

	public static String getDate() {
		return getDate("yyyyMM");
	}
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
}
