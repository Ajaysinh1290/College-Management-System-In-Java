package collegeapplication.chat;

import java.awt.Image;

/*
 * Title : Group.java
 * Created by : Ajaysinh Rathod
 * Purpose : To binding all the data related to group
 * Mail : ajaysinhrathod1290@gmail.com
 */
class Group
{
	private Image groupimage;
	private String groupname;
	private int members;
	private String courcecode;
	private int semoryear;
	public void setImage(Image image)
	{
		this.groupimage=image;
	}
	public void setGroupName(String groupname)
	{
		this.groupname=groupname;
	}
	public void setCourceCode(String courcecode)
	{
		this.courcecode=courcecode;
	}
	public void setSem(int semoryear)
	{
		this.semoryear=semoryear;
	}
	public void setMembers(int members)
	{
		this.members=members;
	}
	public Image getImage()
	{
		return groupimage;
	}
	public String getGroupName()
	{
		return groupname;
	}
	public String getCourceCode()
	{
		return courcecode;
	}
	public int getSemorYear()
	{
		return semoryear;
	}
	public int getMembers()
	{
		return members; 
	}
			
}