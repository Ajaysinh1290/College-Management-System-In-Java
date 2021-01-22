package collegeapplication.subject;

import collegeapplication.cource.Cource;

public class Subject extends Cource
{

	private String subjectname;
	private String subjectcode;
	private int maxtheorymarks=0;
	private int maxpracticalmarks=0;
	private String subjecttype;
	
	
	public void setSubjectType(String subjecttype)
	{
		this.subjecttype=subjecttype;
	}
	public void setMaxTheoryMarks(int maxtheorymarks)
	{
		this.maxtheorymarks=maxtheorymarks;
	}
	public void setMaxPracticalMarks(int maxpracticalmarks)
	{
		this.maxpracticalmarks=maxpracticalmarks;
	}
	public void setSubjectName(String subject)
	{
		this.subjectname=subject;
	}
	public void setSubjectCode(String subjectcode)
	{
		this.subjectcode=subjectcode;
	}
	public void setSemorYear(int semoryear)
	{
		super.setSemorYear(semoryear);
	}
	public String getSubjectCode()
	{
		return subjectcode;
	}
	public String getSubjectName()
	{
		return subjectname;
	}
	public int getMaxTheoryMarks()
	{
		return maxtheorymarks;
	}
	public int getMaxPracticalMarks()
	{
		return maxpracticalmarks;
	}
	public String getSubjectType()
	{
		return subjecttype;
	}
}
