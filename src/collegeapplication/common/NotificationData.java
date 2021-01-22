package collegeapplication.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class NotificationData {

	
	Connection con=DataBaseConnection.getConnection();
	public void addNotification(Notification n)
	{
		try
		{
			String query="insert into notification values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement pr=con.prepareStatement(query);
			pr.setInt(1, 0);
			pr.setString(2, n.getUserProfile());
			pr.setString(3, n.getCourceCode());
			pr.setString(5, n.getUserId());
			pr.setInt(4, n.getSemorYear());
			pr.setString(6,n.getTitle());
			pr.setString(7, n.getMessage());
			pr.setString(8, n.getTime());
			pr.setString(9, "");
			pr.executeUpdate();
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
	}
	
	public ArrayList<Notification> getNotifications(String courcecode,int semoryear,String userprofile,String joinedtime)
	{
		ArrayList<Notification> list=new ArrayList<Notification>();
		try
		{
			String query="select * from notification where courcecode='"+courcecode+"'  and semoryear="+semoryear+" and userprofile='"+userprofile+"' order by sr_no desc";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				Notification n=new Notification();
				n.setSrNo(rs.getInt(1));
				n.setUserProfile(rs.getString(2));
				n.setCourceCode(rs.getString(3));
				n.setSemorYear(rs.getInt(4));
				n.setUserId(rs.getString(5));
				n.setTitle(rs.getString(6));
				n.setMessage(rs.getString(7));
				n.setTime(rs.getString(8));
				n.setReadBy(rs.getString(9));
				long diff=TimeUtil.getTimeDifference(joinedtime,n.getTime());
				System.out.println("Diff between "+n.getTime()+"-"+joinedtime+":"+diff);
				if(diff>0)
				{
				list.add(n);
				}
				else
				{
					break;
				}
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return list;
	}
	public void addReadBy(ArrayList<Integer> list,String userid)
	{
		try
		{
			String query="update notification set readby=concat(readby,?,'#') where ";
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
	public int getUnreadNotification(String userid,String userprofile,String courcecode,int sem,String joinedtime)
	{
		int total=0;
		try
		{
			String query="select readby,time from notification where courcecode='"+courcecode+"' and semoryear="+sem+" and userprofile='"+userprofile+"'";
			Statement st=con.createStatement();
			System.out.println(query);
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				if(!isReadBy(rs.getString(1),userid))
				{
					
					long diff=TimeUtil.getTimeDifference(joinedtime,rs.getString(2));
					if(diff>0)
					{
						total++;
					}
					
				}
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return total;
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
	
}
