package collegeapplication.chat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Title : ChatUser.java
 * Created by : Ajaysinh Rathod
 * Purpose : Binding all the data about chat
 * Mail : ajaysinhrathod1290@gmail.com
 */
public class ChatUser implements Serializable
{
	
	private static final long serialVersionUID = -9098464960153178150L;
	private String fromuserid;
	private String fromusername;
	private String messagetime;
	private String messagedate;
	private String message;
	private String touserid;
	private String tousername;
	private String userprofile;
	private int sr_no=0;
	private boolean isactive=false;
	private String readby="";
	
	public void setReadBy(String readby)
	{
		this.readby=readby;
	}
	public void setUserProfile(String userprofile)
	{
		this.userprofile=userprofile;
	}
	public void setSr_no(int sr_no)
	{
		this.sr_no=sr_no;
	}
	public void setFromUserID(String fromuserid)
	{
		this.fromuserid=fromuserid;
	}
	public void setFromUserName(String fromusername)
	{
		this.fromusername=fromusername;
	}
	public void setToUserID(String touserid)
	{
		this.touserid=touserid;
	}
	public void setMessageTime(String messagetime)
	{
		this.messagetime=messagetime;
	}
	public void setMessageDate(String messagedate)
	{
		this.messagedate=messagedate;
	}
	public void setMessage(String message)
	{
		this.message=message;
	}
	public void setFromUser(String fromuserid,String fromusername)
	{
		this.fromuserid=fromuserid;;
		this.fromusername=fromusername;
	}
	public void setMessage(String message,Date messagetimeanddate)
	{
		this.message=message;
		SimpleDateFormat timeformatter=new SimpleDateFormat("hh:mm aaa");
		SimpleDateFormat dateformatter=new SimpleDateFormat("dd-MMM,yyyy");
		this.messagetime=timeformatter.format(messagetimeanddate);
		this.messagedate=dateformatter.format(messagetimeanddate);
		
	}
	public void setToUser(String userprofile,String touserid,String tousername,boolean isactive)
	{
		this.userprofile=userprofile;
		this.touserid=touserid;
		this.tousername=tousername;
		this.isactive=isactive;
	}
	public String getFromUserId()
	{
		return fromuserid;
	}
	public String getUserProfile()
	{
		return userprofile;
	}
	public String getFromUserName()
	{
		return fromusername;
	}
	public String getMessage()
	{
		return message;
	}
	
	public String getMessageTime()
	{
		return messagetime;
	}
	public String getMessageDate()
	{
		return messagedate;
	}
	
	public String getToUserId()
	{
		return touserid;
	}
	public String getToUserName()
	{
		return tousername;
	}
	public boolean isActive()
	{
		return isactive;
	}
	public int getSr_no()
	{
		return sr_no;
	}
	public String getReadBy()
	{
		return readby;
	}

}
