package com.daisyit.utils;

import java.util.Date;

public class Util {
	public static java.sql.Date getCurrentDate() {
		Date date = new Date();
		long t = date.getTime();
		java.sql.Date sqlDate = new java.sql.Date(t);
		return sqlDate;
	}
}
