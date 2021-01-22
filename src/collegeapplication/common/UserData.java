
package collegeapplication.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import collegeapplication.faculty.Faculty;
import collegeapplication.student.Student;


/*
 * Title : UserData.java
 * Created by : Ajaysinh Rathod
 * Purpose : For getting all the data related to user login activity
 * Mail : ajaysinhrathod1290@gmail.com
 */


public class UserData {
	Connection con=DataBaseConnection.getConnection();
			
			public int addStudentLoginTime(Student s)
			{
				int result=0;
				try
				{
				String query="insert into users values(?,?,?,?,?,?)";
				PreparedStatement pr=con.prepareStatement(query);
				pr.setInt(1, 0);
				pr.setString(2, s.getCourceCode());
				pr.setInt(3,s.getSemorYear());
				pr.setString(4,s.getUserId());
				pr.setString(5,TimeUtil.getCurrentTime());
				pr.setString(6, "Student");
				result=pr.executeUpdate();
				
				}
				catch(Exception exp) {
					exp.printStackTrace();
				}
			return result;	
			}
			public int addFacultyLoginTime(Faculty s)
			{
				int result=0;
				try
				{
				String query="insert into users values(?,?,?,?,?,?)";
				PreparedStatement pr=con.prepareStatement(query);
				pr.setInt(1, 0);
				pr.setString(2, s.getCourceCode());
				pr.setInt(3,s.getSemorYear());
				pr.setString(4,s.getFacultyId()+"");
				pr.setString(5,TimeUtil.getCurrentTime());
				pr.setString(6, "Faculty");
				result=pr.executeUpdate();
				
				}
				catch(Exception exp) {
					exp.printStackTrace();
				}
			return result;	
			}
			public ArrayList<User> getUserInfo(String condition)
			{
				ArrayList<User> list=new ArrayList<User>();
				try
				{
					String query="select courcecode as 'Cource',semoryear as 'Sem/Year',userid as 'Userid',logintime as 'Login Time',userprofile as 'User Profile' from users "+condition+" order by sr_no desc";
					Statement st=con.createStatement();
					ResultSet rs=st.executeQuery(query);
					while(rs.next())
					{
						User user=new User();
						user.setCourceCode(rs.getString(1));
						user.setSemorYear(rs.getInt(2));
						user.setUserId(rs.getString(3));
						user.setLoginTime(rs.getString(4));
						user.setUserProfile(rs.getString(5));
						list.add(user);
						
					}
					
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}
				return list;
			}

}

