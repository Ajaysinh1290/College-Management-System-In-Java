package collegeapplication.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import collegeapplication.cource.Cource;
import collegeapplication.faculty.FacultyData;
import collegeapplication.student.StudentData;


/*
 * Title : User.java
 * Created by : Ajaysinh Rathod
 * Purpose : Binding data related to login activity of students and faculties
 * Mail : ajaysinhrathod1290@gmail.com
 */


class User extends Cource
{
	
	
	private String logindate;
	private String logintime;
	private String userid;
	private String userprofile;
	
	public void setLoginTime(String logintime)
	{
		SimpleDateFormat timeformatter=new SimpleDateFormat("hh:mm aaa");
		SimpleDateFormat dateformatter=new SimpleDateFormat("dd-MMM,yyyy");
		SimpleDateFormat formater=new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aaa");
		Date date;
		try {
			date = formater.parse(logintime);
			this.logintime=timeformatter.format(date);
			this.logindate=dateformatter.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void setUserId(String userid)
	{
		this.userid=userid;
	}
	public void  setUserProfile(String userprofile)
	{
		this.userprofile=userprofile;
	}
	public String getLoginTime()
	{
		return logintime;
	}
	public String getLoginDate()
	{
		if(getCurrentDate().equals(logindate))
		{
			return "today";
		}
		
		return logindate;
	}
	public String getCurrentDate()
	{
		Date date=new Date();
		SimpleDateFormat dateformatter=new SimpleDateFormat("dd-MMM,yyyy");
		String time=dateformatter.format(date);
		return time;
	}
	public String getUserid()
	{
		return userid;
	}
	public String getUserProfile() 
	{
		return userprofile;
	}
	
	public String getName() 
	{
		if(getUserProfile().equals("Student"))
		{
			return new StudentData().getStudentName(userid);
		}
		else if(getUserProfile().equals("Faculty"))
		{
				return new FacultyData().getFacultyName(userid);
		}
		return "-";
	}

}
