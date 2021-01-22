package collegeapplication.admin;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

import collegeapplication.common.DataBaseConnection;
import collegeapplication.common.TimeUtil;

/*
 * Title : AdminData.java
 * Created by : Ajaysinh Rathod
 * Purpose : Handling all the data related to admin
 * Mail : ajaysinhrathod1290@gmail.com
 */
public class AdminData {
	
	Connection con=DataBaseConnection.getConnection();

	public int updateAdminLink(Admin a)
	{
		int result=0;
		try
		{
			String query="update admin set facebook=?,instagram=?,twitter=?,linkedin=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, a.getFacebookLink());
			ps.setString(2, a.getInstagramLink());
			ps.setString(3, a.getTwitterLink());
			ps.setString(4, a.getLinkedinLink());
			result=ps.executeUpdate();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			
		}
		return result;
	}
	public String getLastLogin()
	{
		try
		{
			String query="select lastlogin from admin";
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
	public int updateAdminDetails(Admin a)
	{	
		int result=0;
			try
			{
				String query="update admin set collagename=?,address=?,emailid=?,contactnumber=?,website=?,lastlogin=?,password=?,logo=?,activestatus=?";
				PreparedStatement pr=con.prepareStatement(query);
				pr.setString(1, a.getCollageName());
				pr.setString(2, a.getAddress());
				pr.setString(3, a.getEmailId());
				pr.setString(4, a.getContactNumber());
				pr.setString(5, a.getWebsite());
				pr.setString(6, TimeUtil.getCurrentTime());
				pr.setString(7, a.getPassword());
				pr.setBytes(8,a.getProfilePicInBytes());
				pr.setBoolean(9, a.getActiveStatus());
				
				result=pr.executeUpdate();
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
			return result;
	}
	public boolean isActive()
	{
		try
		{
			String query="select activestatus from admin";
			java.sql.Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			return rs.getBoolean(1);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return false;
	}
	public Admin getAdminData()
	{
		Admin a=new Admin();
		try
		{
			String query="select * from admin";
			java.sql.Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			a.setCollageName(rs.getString(1));
			a.setAddress(rs.getString(2));
			a.setEmailId(rs.getString(3));
			a.setContactNumber(rs.getString(4));
			a.setWebsite(rs.getString(5));
			a.setLastLogin(rs.getString(6));
			a.setPassword(rs.getString(7));
			a.setFaceBookLink(rs.getString(8));
			a.setInstagramLink(rs.getString(9));
			a.setTwitterLink(rs.getString(10));
			a.setLinkedinLink(rs.getString(11));
			a.setProfilePic(rs.getBytes(12));
			a.setActiveStatus(rs.getBoolean(13));
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return a;
	}

	public boolean checkPassword(String userid,String password)
	{
		Admin a=this.getAdminData();
		userid=userid.trim();
		if(userid.equalsIgnoreCase("Admin")&&a.getPassword().equals(password))
		{
			return true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Incorrect Userid or Password","Error",JOptionPane.ERROR_MESSAGE);
		}
		return false;
			
	}
	public int setActiveStatus(boolean status)
	{
		int result=0;
		try
		{
		String query="update admin set activestatus="+status;
		PreparedStatement pr=con.prepareStatement(query);
		result=pr.executeUpdate();
		pr.close();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
		
	}
	public Image getProfilePic()
	{
		try
		{
			String query="select logo from admin";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			byte[] imagedata=rs.getBytes(1);
			Image image=Toolkit.getDefaultToolkit().createImage(imagedata);
			return image;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return null;
	}
}


