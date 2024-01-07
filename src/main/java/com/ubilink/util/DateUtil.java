package com.ubilink.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil 
{
	public static String getFormattedDateStr(String dateStr)
	{
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String strDate="";
		Date date=null;
		try {
			date = formatter.parse(dateStr);
			formatter= new SimpleDateFormat("dd-MMM-yy");
			strDate=formatter.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return strDate="";
		}
		
		return strDate;
	}
	
	public static String getDateNowStrForMySQL()
	{
		DateFormat formatter = null;
	
		try
		{
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
		return formatter.format(new Date());
	}
	
	public static String getFormattedDateStrTemp(String dateStr)
	{
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date=null;
		try {
			date = formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return formatter.format(date);
	}
	
	public static String getStringFromDate(Date date)
	{
		DateFormat dateFormat=new SimpleDateFormat("dd MMM yy");
		String strDate="";
		try
		{
			strDate=dateFormat.format(date);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		return strDate;
	}
	
	public static Date getDateFromString(String dateStr)
	{
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		Date date=null;
		try {
			date = formatter.parse(dateStr);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return date;
	}
}
