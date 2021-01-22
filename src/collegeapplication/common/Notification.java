package collegeapplication.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import collegeapplication.cource.Cource;

public class Notification extends Cource
{
	
	private String time;
	private String title;
	private String message;
	private String readby;
	private int sr_no;
	private String userid;
	private String userprofile;
	
	
	public void setUserProfile(String userprofile)
	{
		this.userprofile=userprofile;
	}
	public void setSrNo(int sr_no)
	{
		this.sr_no=sr_no;
	}
	public void setUserId(String userid)
	{
		this.userid=userid;
	}
	public void setTime(String time)
	{
		this.time=time;
	}
	public void setTitle(String title)
	{
		this.title=title;
	}
	public void setMessage(String message)
	{
		this.message=message;
	}
	public void setReadBy(String readby)
	{
		this.readby=readby;
	}
	public int getSrNo()
	{
		return sr_no;
	}
	public String getUserId()
	{
		return userid;
	}
	public String getTitle()
	{
		return title;
	}
	public	String getMessage()
	{
		return message;
	}
	public String getReadBy()
	{
		return readby;
	}
	public String getNotificationDate()
	{
		SimpleDateFormat dateformatter=new SimpleDateFormat("dd-MMM,yyyy");
		SimpleDateFormat formater=new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aaa");
		Date date=null;
			try {
				date = formater.parse(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return dateformatter.format(date);
		
	}
	public String getTime()
	{
		return time;
	}
	public String getNotificationTime()
	{
		SimpleDateFormat timeformatter=new SimpleDateFormat("hh:mm aaa");
		SimpleDateFormat formater=new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aaa");
		Date date=null;
			try {
				date = formater.parse(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return timeformatter.format(date);
	}
	public String getUserProfile()
	{
		return userprofile;
	}
}
