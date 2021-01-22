package collegeapplication.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;


/*
 * Title : TimeUtil.java
 * Created by : Ajaysinh Rathod
 * Purpose : To get Current login time 
 * Mail : ajaysinhrathod1290@gmail.com
 */


public class TimeUtil {
	
	static SimpleDateFormat formater=new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aaa");
	public static String getCurrentTime()
	{
		
		Date logintime=new Date();
		return formater.format(logintime);
	}
	@SuppressWarnings("deprecation")
	public static int getDayDifference(String strdate1,String strdate2)
	{
		if(strdate1.isEmpty()||strdate2.isEmpty())
		{
			return -1;
		}
		SimpleDateFormat dateformatter=new SimpleDateFormat("dd-MMM,yyyy");
		Date date1 = null;
		try {
			date1 = dateformatter.parse(strdate1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date date2 = null;
		try {
			date2 =dateformatter.parse(strdate2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(date1.getMonth());
		System.out.println(date2.getMonth());
		LocalDate dateone = LocalDate.of(date1.getYear(),date1.getMonth()+1, date1.getDate());
		LocalDate datetwo = LocalDate.of(date2.getYear(),date2.getMonth()+1, date2.getDate());
		
		
		Period diff = Period.between(dateone,datetwo);	
		return Math.abs(diff.getDays());
	}

	public static long getTimeDifference(String time1,String time2)
	{
		Date date1 = null;
		try {
			date1 = formater.parse(time1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date date2 = null;
		try {
			date2 = formater.parse(time2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long diff=date2.getTime()-date1.getTime();
//		long diffSeconds = diff / 1000 % 60;
//		long diffMinutes = diff / (60 * 1000) % 60;
//		long diffHours = diff / (60 * 60 * 1000) % 24;
		return diff;
	}
	public static void main(String a[])
	{
		System.out.println(getTimeDifference(getCurrentTime(),getCurrentTime()));
	}

}
