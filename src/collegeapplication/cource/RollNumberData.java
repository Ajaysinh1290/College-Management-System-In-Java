package collegeapplication.cource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import collegeapplication.common.DataBaseConnection;

/*
 * Title : RollNumberData.java
 * Created by : Ajaysinh Rathod
 * Purpose : Handling all the data related to rollnumber
 * Mail : ajaysinhrathod1290@gmail.com
 */

public class RollNumberData
{	
	static Connection con=DataBaseConnection.getConnection();
	public static void closeConnection() throws SQLException
	{
		con.close();
	}
	
	public void adddata(String courcecode,int sem,long rollnumber)
	{
		String query="insert into Rollgenerator values(?,?,?)";
		try
		{
			
			PreparedStatement pr=con.prepareStatement(query);
			pr.setString(1, courcecode);
			pr.setInt(2, sem);
			pr.setLong(3, rollnumber);
			pr.executeUpdate();
			pr.close();
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
	}
	
	public long getMasterRollNumber(String courcecode,int sem)
	{
		long rollnumber=-1;
		String query="select rollnumber from rollgenerator where courcecode='"+courcecode+"' and semoryear=+"+sem;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			try
			{
			rollnumber=rs.getLong(1);
			}
			catch(Exception exp)
			{
				rollnumber=-1;
			}
			return rollnumber;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return rollnumber;
	}
	public long getRollNumber(String courcecode,int sem)
	{
		
		long rollnumber=this.getMasterRollNumber(courcecode, sem)+1;
		if(rollnumber==0)
		{
			return 0;
		}
		
		String query="select rollnumber from students where courcecode='"+courcecode+"' and semoryear=+"+sem;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				if(isExist(courcecode,sem,rollnumber)==0)
				{
					break;
				}
				rollnumber++;
				
			}
		
			return rollnumber;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return rollnumber;
	}
	public int isExist(String courcecode,int sem,long rollnumber)
	{
		int result=0;
		String query="select rollnumber from students where courcecode='"+courcecode+"' and semoryear=+"+sem+" and rollnumber="+rollnumber;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				result++;
			}
			
			
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
	
}
