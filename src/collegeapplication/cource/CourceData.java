package collegeapplication.cource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import collegeapplication.common.DataBaseConnection;
import collegeapplication.common.Notification;
import collegeapplication.common.NotificationData;
import collegeapplication.common.TimeUtil;


/*
 * Title : UserData.java
 * Created by : Ajaysinh Rathod
 * Purpose : Handling all the data related to cource
 * Mail : ajaysinhrathod1290@gmail.com
 */

public class CourceData
{
	
	static Connection con=DataBaseConnection.getConnection();

	public static void closeConnection() throws SQLException
	{
		con.close();
	}
	public int addCource(String courcecode,String courcename,String semoryear,int totalyearorsem)
	{
		int result=0;
		try 
		{
		String query="insert into cources values(?,?,?,?,?)";
		PreparedStatement pr=con.prepareStatement(query);
		pr.setInt(1,0);
		pr.setString(2, courcecode.toUpperCase());
		pr.setString(3, courcename);
		pr.setString(4, semoryear);
		pr.setInt(5, totalyearorsem);
		result=pr.executeUpdate();
			
			pr.close();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
	}
	
	public  ResultSet getCourceinfo()
	{
	
		ResultSet st=null;
		try
		{
			String query="select c.sr_no as 'Index no.',c.courcecode as 'Cource Code' ,c.courcename as 'Cource Name',(select count(*) from subject where subject.courcecode=c.courcecode) as 'Subjects' ,(select count(*) from students where students.courcecode=c.courcecode) as 'Students',concat(c.totalsemoryear,' ',c.semoryear) as 'Total Sem/Year' from cources c;";
			PreparedStatement pr=con.prepareStatement(query);
			
			st=pr.executeQuery();
			return st;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return st;
	}
	public int getTotalCource()
	{
		int totalrow=0;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery("select * from cources");
			while(st.next())
			{
				totalrow++;
			}
			pr.close();
		
			return totalrow;
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return totalrow;
	}
	
	
	
	public String[] getCourceName()
	{
				String courcename[];
				int i=0;
				courcename=new String[getTotalCource()+1];
				courcename[i++]="---Select Cource---";
		
				try
				{
					Statement pr=con.createStatement();
					ResultSet st=pr.executeQuery("select courcename from cources");
					
					
					while(st.next())
					{
						courcename[i++]=st.getString(1);
					}
					pr.close();
					st.close();
					return courcename;
					
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}
		return courcename;
		
	}
	public int getRollTotalCource()
	{
		int totalrow=0;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery("select courcename from cources where courcecode Not IN(select distinct courcecode from rollgenerator)");             
			while(st.next())
			{
				totalrow++;
			}
			pr.close();
			st.close();
			return totalrow;
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return totalrow;
	}
	public String[] getRollCourceName()
	{
				String courcename[];
				int i=0;
				courcename=new String[getRollTotalCource()+1];
				courcename[i++]="---select---";
		
				try
				{
					Statement pr=con.createStatement();
					ResultSet st=pr.executeQuery("select courcename from cources where courcecode NOT IN(select distinct courcecode from rollgenerator)");
					
					
					while(st.next())
					{
						courcename[i++]=st.getString(1);
					}
					pr.close();
					st.close();
					return courcename;
					
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}
		return courcename;
		
	}
	public String[] getSemorYear(String Courcename)
	{
		String query="select semoryear, totalsemoryear from cources where courcename='"+Courcename+"'";
		String totalsem[]=new String[1];
		totalsem[0]="---Select Sem/Year---";
		if(!Courcename.contains("--select--"))
		{
			try
			{
				Statement pr=con.createStatement();
				ResultSet st=pr.executeQuery(query);
				st.next();
				String semoryear=st.getString(1);
				int totalsemoryear=st.getInt(2);	
				
				totalsem=new String[totalsemoryear+1];
				if(semoryear.contains("sem"))
				{
					semoryear="Semester";
				}
				else
				{
					semoryear="Year";
				}
				totalsem[0]="---Select "+semoryear+"---";
				for(int i=1; i<=totalsemoryear; i++)
				{
					totalsem[i]=semoryear+" "+i;
				}
				pr.close();
				st.close();
				return totalsem;
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
		}
		return totalsem;
		
	}
	public String[] getCourcecode()
	{
		String courcecode[]=new String[this.getTotalCource()];
		String query="select courcecode from cources";
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery(query);
			int i=0;
			while(st.next())
			{
				courcecode[i++]=st.getString(1);
			}
			pr.close();
			st.close();
			
			return courcecode;
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return courcecode;
		
		
	}
	public String getCourcecode(String courcename)
	{
		String query="select courcecode from cources where courcename='"+courcename+"'";
		String courcecode=null;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery(query);
	
				st.next();
				courcecode=st.getString(1);
			
				pr.close();
				st.close();
			return courcecode;
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return courcecode;
	}
	public String getsemoryear(String courcecode)
	{
		String query="select semoryear from cources where courcecode='"+courcecode+"'";
		String semoryear=null;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery(query);
	
				st.next();
				semoryear=st.getString(1);
			
				pr.close();
				st.close();
			return semoryear;
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return semoryear;
	}
	public String getcourcename(String courcecode)
	{
		String query="select courcename from cources where courcecode='"+courcecode+"'";
		String courcename=null;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery(query);
	
				st.next();
				courcename=st.getString(1);
			
				pr.close();
				st.close();
			return courcename;
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return courcename;
	}
	public int getTotalsemoryear(String courcename)
	{
		String query="select totalsemoryear from cources where courcename='"+courcename+"'";
		int totalsemoryear=0;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery(query);
			while(st.next())
			{
			totalsemoryear=st.getInt(1);
			}
			pr.close();
			st.close();
			
			return totalsemoryear;
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return totalsemoryear;
	}
	public boolean isCourceCodeExist(String courcecode)
	{
		try
		{
			
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) from cources where courcecode='"+courcecode+"'");
			rs.next();
			if(rs.getInt(1)>0)
			{
				return true;
			}
			rs.close();
			st.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public boolean isCourceNameExist(String courcename)
	{
		try
		{
			
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) from cources where courcename='"+courcename+"'");
			rs.next();
			if(rs.getInt(1)>0)
			{
				return true;
			}
			rs.close();
			st.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public boolean isDeclared(String courcecode,int semoryear)
	{
		boolean isdeclared=false;
		try
		{
			String query="select isdeclared from result where courcecode='"+courcecode+"' and semoryear="+semoryear;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
			isdeclared=rs.getBoolean(1);
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return isdeclared;
	}
	public ArrayList<Cource> getCourcesForDeclareResult(String courcename)
	{
		ArrayList<Cource> list=new ArrayList<Cource>();
		try
		{
			String query="select courcename,courcecode,totalsemoryear from cources where courcename='"+courcename+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				int totalsem=rs.getInt(3);
				for(int i=0; i<totalsem; i++)
				{
					Cource cource=new Cource();
					cource.setCourceName(rs.getString(1));
					cource.setCourceCode(rs.getString(2));
					cource.setSemorYear((i+1));
					cource.setIsDeclared(isDeclared(rs.getString(2),(i+1)));
					list.add(cource);
				}
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return list;
	}
	public int updateResult(Cource c)
	{
		int result=0;
		try
		{
			String query="update result set isdeclared="+c.getIsDeclared()+" where courcecode='"+c.getCourceCode()+"' and semoryear="+c.getSemorYear();
			PreparedStatement pr=con.prepareStatement(query);
			result=pr.executeUpdate();
		}
		catch(Exception exp) 
		{
			exp.printStackTrace();
		}
		return result;
	}
	public void declareResult(Cource c)
	{
		try
		{		if(c.getIsDeclared())
				{
					Notification n=new Notification();
					n.setUserProfile("Student");
					n.setCourceCode(c.getCourceCode());
					n.setSemorYear(c.getSemorYear());
					n.setTitle("Result");
					n.setUserId("Admin");
					n.setMessage("Your result is declared. now you can see your marksheet.");
					n.setTime(TimeUtil.getCurrentTime());
					new NotificationData().addNotification(n);
					n.setMessage( c.getCourceCode()+" "+getsemoryear(c.getCourceCode())+"-"+c.getSemorYear()+" result is declared. now you can see student's marksheet.");
					n.setUserProfile("Faculty");
					new NotificationData().addNotification(n);
				}
				if(updateResult(c)==0)
				{
				String query="insert into result values(?,?,?)";
				PreparedStatement pr=con.prepareStatement(query);
				pr.setString(1,c.getCourceCode());
				pr.setInt(2, c.getSemorYear());
				pr.setBoolean(3, c.getIsDeclared());
				pr.executeUpdate();
				}
				
				
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
	}
	
}

