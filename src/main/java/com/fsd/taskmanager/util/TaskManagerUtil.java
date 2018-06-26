package com.fsd.taskmanager.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskManagerUtil {
	private static final DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	public static Date toDate(String dateAsString) {
		Date date = null;
		try {
			date = formatter.parse(dateAsString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String dateToString(Date date) {
		String dateStr = null;
		dateStr = formatter.format(date);
		return dateStr;
	}
}
