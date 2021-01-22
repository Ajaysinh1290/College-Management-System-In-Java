package collegeapplication.common;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import collegeapplication.cource.Cource;
import collegeapplication.cource.CourceData;

public abstract class Person extends Cource{

	private String emailid;
	private String contactnumber;
	private String birthdate;
	private String gender;
	private String state;
	private String city;
	private Image image;
	private int sr_no;
	private String lastlogin;
	private String password;
	private boolean isactive;
	
	
	
	public void setEmailId(String emailid)
	{
		this.emailid=emailid;
	}
	public void setContactNumber(String contactnumber)
	{
		this.contactnumber=contactnumber;
	}
	public void setBirthDate(String birthdate)
	{
		this.birthdate=birthdate;
	}
	public void setGender(String gender)
	{
		this.gender=gender;
	}
	public void setState(String state)
	{
		this.state=state;
	}
	public void setCity(String city)
	{
		this.city=city;
	}
	
	public void setProfilePic(Image image)
	{
		this.image=image;
	}
	public void setProfilePic(byte[] imagedata)
	{
		this.image=Toolkit.getDefaultToolkit().createImage(imagedata);
	}
	public void setSrNo(int sr_no)
	{
		this.sr_no=sr_no;
	}
	public void setLastLogin(String lastlogin)
	{
		this.lastlogin=lastlogin;
	}
	
	public void setPassword(String password)
	{
		this.password=password;
	}
	public void setActiveStatus(boolean isactive)
	{
		this.isactive=isactive;
	}
	
	public String getCourceName()
	{
		return new CourceData().getcourcename(this.getCourceCode());
	}

	public String getEmailId()
	{
		return emailid;
	}
	public String getContactNumber()
	{
		return contactnumber;
	}
	public String getBirthDate()
	{
		return birthdate;
	}
	public Date getBirthDateInDateFormat()
	{
		Date date=null;
		try {
			date=new SimpleDateFormat("dd-MM-yyyy").parse(this.birthdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public String getGender()
	{
		return gender;
	}
	public String getAddress()
	{
		return city+", "+state;
	}
	public String getPassword()
	{
		return password;
	}
	public String getState()
	{
		return state;
	}
	public String getCity()
	{
		return city;
	}
	
	public Image getProfilePic()
	{
		return image;
	}
	public byte[] getProfilePicInBytes()
	{
		ByteArrayOutputStream imagedata=new ByteArrayOutputStream();
		try {
			ImageIO.write(ImageUtil.toBufferedImage(image), "jpg", imagedata);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imagedata.toByteArray();
		
	}
	public boolean comparePassword(String password)
	{
		return password.equals(this.password)?true:false;
	}
	public Image getProfilePic(int width,int height)
	{
		return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
	public BufferedImage getRoundedProfilePic(int width,int height,int radius)
	{
		return ImageUtil.makeRoundedCorner(ImageUtil.toBufferedImage(image.getScaledInstance(width, height, Image.SCALE_SMOOTH)), radius);
	}
	public String getLastLogin()
	{
		return lastlogin;
	}
	public int getSrNo()
	{
		return sr_no;
	}
	public boolean getActiveStatus()
	{
		return isactive;
	}
}
