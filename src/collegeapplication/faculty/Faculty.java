package collegeapplication.faculty;

import collegeapplication.common.Person;
import collegeapplication.common.TimeUtil;
import collegeapplication.cource.CourceData;

/*
 * Title : Faculty.java
 * Created by : Ajaysinh Rathod
 * Purpose : Binding all the of faculty
 * Mail : ajaysinhrathod1290@gmail.com
 */
public class Faculty extends  Person
{
	
	private int facultyid;
	private String facultyname;
	private String qualification;
	private String experience;
	private String subject;
	private String position;
	private String joineddate;
	
	public void setJoinedDate(String joineddate)
	{
		this.joineddate=joineddate;
	}
	public void setFacultyId(int facultyid)
	{
		this.facultyid=facultyid;
	}
	public void setFacultyName(String facultyname)
	{
		this.facultyname=facultyname;
	}
	public void setQualification(String qualification)
	{
		this.qualification=qualification;
	}
	public void setExperience(String experience)
	{
		this.experience=experience;
	}
	public void setSubject(String subject)
	{
		this.subject=subject;
	}
	public void setPosition(String position)
	{
		this.position=position;
	}
	public String getCourceName()
	{
		return new CourceData().getcourcename(this.getCourceCode());
	}
	public String getFacultyName()
	{
		return facultyname;
	}
	
	public int getFacultyId()
	{
		return facultyid;
	}
	
	public String getQualification()
	{
		return qualification;
	}
	public String getPosition()
	{
		return position;
	}
	public String getExperience()
	{
		return experience;
	}
	public String getSubject()
	{
		return subject;
	}
	public String getJoinedDate()
	{
		return joineddate;
	}
	public String generateJoinedDate()
	{
		joineddate=TimeUtil.getCurrentTime();
		return joineddate;
	}
	
}