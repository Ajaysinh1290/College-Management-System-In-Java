package collegeapplication.chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import collegeapplication.common.DataBaseConnection;
import collegeapplication.cource.CourceData;
import collegeapplication.faculty.Faculty;
import collegeapplication.student.Student;

/*
 * Title : ChatData.java
 * Created by : Ajaysinh Rathod
 * Purpose : Manages all the data related to chat 
 * Mail : ajaysinhrathod1290@gmail.com
 */
public class ChatData {
	
	Connection con=DataBaseConnection.getConnection();
	
	
	public int saveMessage(ChatUser u)
	{
		int result=0;
		try 
		{
			String query="insert into chat values(?,?,?,?,?,?,?,?)";
			PreparedStatement pr=con.prepareStatement(query);
			pr.setInt(1, u.getSr_no());
			pr.setString(2, u.getFromUserId());
			pr.setString(3, u.getFromUserName());
			pr.setString(4, u.getToUserId());
			pr.setString(5, u.getMessage());
			pr.setString(6, u.getMessageTime());
			pr.setString(7, u.getMessageDate());
			pr.setString(8,"");
			result=pr.executeUpdate();
			pr.close();
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
	}
	public ArrayList<ChatUser> getMessages()
	{
		ArrayList <ChatUser> list=new ArrayList<ChatUser>();
		try 
		{
			
			String query="select * from chat";
			Statement pr=con.createStatement();
			ResultSet rs=pr.executeQuery(query);
			while(rs.next())
			{
				ChatUser user=new ChatUser();
				user.setSr_no(rs.getInt(1));
				user.setFromUserID(rs.getString(2));
				user.setFromUserName(rs.getString(3));
				user.setToUserID(rs.getString(4));
				user.setMessage(rs.getString(5));
				user.setMessageTime(rs.getString(6));
				user.setMessageDate(rs.getString(7));
				user.setReadBy(rs.getString(8));
				list.add(user);
			}
			pr.close();
			rs.close();
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return list;
	}
	public ArrayList<ChatUser> getUserMessages(ChatUser u)
	{
		ArrayList <ChatUser> list=new ArrayList<ChatUser>();
		try 
		{
		
			String query=null;
			if(u.getToUserId().contains("Group"))
			{
				query="select * from chat where touserid='"+u.getToUserId()+"'";

			}
			else
			{
				query="select * from chat where (touserid='"+u.getToUserId()+"' and fromuserid='"+u.getFromUserId()+"') or (fromuserid='"+u.getToUserId()+"' and touserid='"+u.getFromUserId()+"')";
			}
			Statement pr=con.createStatement();
			ResultSet rs=pr.executeQuery(query);
			while(rs.next())
			{
				ChatUser user=new ChatUser();
				user.setSr_no(rs.getInt(1));
				user.setFromUserID(rs.getString(2));
				user.setFromUserName(rs.getString(3));
				user.setToUserID(rs.getString(4));
				user.setMessage(rs.getString(5));
				user.setMessageTime(rs.getString(6));
				user.setMessageDate(rs.getString(7));
				user.setReadBy(rs.getString(8));
				list.add(user);
			}
			pr.close();
			rs.close();
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return list;
	}
	public NewMessage getNewMessages(String fromuserid,String touserid)
	{
		NewMessage newmessage=new NewMessage();
		String readbystr="";
		
		try
		{
			String query=null;
			if(touserid.contains("Group"))
			{
				query="select readby,message,fromuserid,messagetime,messagedate from chat where (touserid='"+touserid+"' and fromuserid!='"+fromuserid+"')";

			}
			else
			{
				query="select readby,message,fromuserid,messagetime,messagedate from chat where (touserid='"+fromuserid+"' and fromuserid='"+touserid+"')";
			}
			Statement pr=con.createStatement();
			ResultSet rs=pr.executeQuery(query);
			while(rs.next())
			{
				readbystr=rs.getString(1);
				boolean isreaded=isReadBy(readbystr,fromuserid);
				if(!isreaded)
				{
					newmessage.total++;
					
					if(touserid.contains("Group"))
					{
						newmessage.message=rs.getString(3)+" : "+rs.getString(2);	
					}
					else
					{
						newmessage.message=rs.getString(2);
					}
					newmessage.messagetime=rs.getString(5);
					if(newmessage.messagetime.equals(NewMessage.getCurrentDate()))
					{
						newmessage.messagetime=rs.getString(4);
					}
				}
				
			}
			pr.close();
			rs.close();
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		if(newmessage.message==null)
		{
			newmessage=getLastMessage(fromuserid, touserid);
		}
		return newmessage;
	}
	public String getReadBy(int sr_no)
	{
		try
		{
			String query="select readby from chat where sr_no="+sr_no;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			return rs.getString(1);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return null;
	}
	public NewMessage getLastMessage(String fromuserid,String touserid)
	{
		NewMessage newmessage=new NewMessage();
		newmessage.message="Start new Conversion";
		try
		{
			String query=null;
			if(touserid.contains("Group"))
			{
				query="select message,fromuserid,messagetime,messagedate from chat where touserid='"+touserid+"'";

			}
			else
			{
				query="select message,fromuserid,messagetime,messagedate from chat where (touserid='"+touserid+"' and fromuserid='"+fromuserid+"') or (fromuserid='"+touserid+"' and touserid='"+fromuserid+"')";
			}
			Statement pr=con.createStatement();
			ResultSet rs=pr.executeQuery(query);
			while(rs.next())
			{
				if(touserid.contains("Group"))
				{
					String from=fromuserid.equals(rs.getString(2))?"You":rs.getString(2);
					newmessage.message=from+" : "+rs.getString(1);	
				}
				else
				{
					newmessage.message=rs.getString(1);
				}
				newmessage.messagetime=rs.getString(4);
				if(newmessage.messagetime.equals(NewMessage.getCurrentDate()))
				{
					newmessage.messagetime=rs.getString(3);
				}
			}
			pr.close();
			rs.close();
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return newmessage;
	}
	public boolean isReadBy(String str,String fromuserid)
	{
		if(str.isEmpty())
		{
			return false;
		}
		
		StringTokenizer readby=new StringTokenizer(str,"#");
		while(readby.hasMoreTokens())
		{
			if(readby.nextToken().equals(fromuserid))
			{
				return true;
			}
		}
		return false;
	}
	
	public void readBy(int sr_no,String readby)
	{
		try
		{
			String query="update chat set readby=? where sr_no="+sr_no;
			PreparedStatement pr=con.prepareStatement(query);
			pr.setString(1, readby);
			pr.executeUpdate();
			pr.close();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}
	public void addReadBy(ArrayList<Integer> list,String userid)
	{
		try
		{
			String query="update chat set readby=concat(readby,?,'#') where ";
			query+=" sr_no="+list.get(0);
			for(int i=1; i<list.size(); i++)
			{
				query+=" or sr_no="+list.get(i);
			}
			PreparedStatement pr=con.prepareStatement(query);
			pr.setString(1, userid);
			pr.executeUpdate();
			pr.close();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}
	public int getNewSr_no()
	{
		try
		{
			String query="select count(*) from chat";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			int sr_no=rs.getInt(1)+1;
			rs.close();
			st.close();
			return sr_no;	
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return 0;
		
	}
	public int getUndreadMessageCountAdmin()
	{
		int total=0;
		try
		{
			String query="select readby from chat where (touserid like '%Official%' and fromuserid!='Admin') or touserid='Admin'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				String readbystr=rs.getString(1);
				boolean isreaded=isReadBy(readbystr,"Admin");
				if(!isreaded)
				{
					total++;
				}
					
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return total;
	}
	public int getUndreadMessageCountStudent(Student s)
	{
		int total=0;
		String cource=s.getCourceCode()+" "+new CourceData().getsemoryear(s.getCourceCode())+"-"+s.getSemorYear();
		try
		{
			String query="select readby from chat where ((touserid='"+cource+" Official Group' or touserid='"+cource+" Students Group') and fromuserid!='"+s.getUserId()+"') or touserid='"+s.getUserId()+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				String readbystr=rs.getString(1);
				
				boolean isreaded=isReadBy(readbystr,s.getUserId());
				if(!isreaded)
				{
					total++;
				}
					
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return total;
	}
	public int getUndreadMessageCountFaculty(Faculty f)
	{
		int total=0;
		String cource=f.getCourceCode()+" "+new CourceData().getsemoryear(f.getCourceCode())+"-"+f.getSemorYear();
		try
		{
			String query="select readby from chat where ((touserid='"+cource+" Official Group' or touserid='"+cource+" Faculties Group') and fromuserid!='"+f.getFacultyId()+"') or touserid='"+f.getFacultyId()+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				String readbystr=rs.getString(1);
				boolean isreaded=isReadBy(readbystr,f.getFacultyId()+"");
				if(!isreaded)
				{
					total++;
				}
				
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return total;
	}
	
	
}
/*
 * Title : NewMessage
 * Created by : Ajaysinh Rathod
 * Purpose : To calculate total messages,last message,last messagetime
 * Mail : ajaysinhrathod1290@gmail.com
 */
class NewMessage
{
	
	String message=null;
	String messagetime="";
	int total=0;
	public static String getCurrentDate()
	{
		Date date=new Date();
		SimpleDateFormat dateformatter=new SimpleDateFormat("dd-MMM,yyyy");
		return dateformatter.format(date);
	}
	
}
