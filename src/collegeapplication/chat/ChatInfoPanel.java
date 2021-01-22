package collegeapplication.chat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import collegeapplication.admin.Admin;
import collegeapplication.admin.AdminData;
import collegeapplication.common.ImageUtil;
import collegeapplication.faculty.Faculty;
import collegeapplication.student.Student;

/*
 * Title : ChatInfoPanel.java
 * Created by : Ajaysinh Rathod
 * Purpose : To display user details at right side of panel
 * Mail : ajaysinhrathod1290@gmail.com
 */
@SuppressWarnings("serial")
public class ChatInfoPanel extends JPanel {
	private JLabel bottomlabel;
	private JLabel contactnamelabel;
	private JLabel profilepic;
	private JLabel headerlabel;
	private JPanel userdetailspanel;
	private JLabel emailidlabel;
	private JLabel locationlabel;
	private JLabel dateofbirthlabel;
	private JLabel contactnumberlabel;
	private JPanel memberspanel;
	private int total=0;
	private JScrollPane memberspanelscroll;
	private JLabel aboutheadinglabel;
	private JLabel lblLocation;
	private JLabel locationicon;
	private JLabel line3;
	private JLabel lblDateOfBirth;
	private JLabel dobicon;
	private Student student;
	private Faculty faculty;
	private Admin admin;
	private Image profileimage;
	private JLabel onlinestatus;

	/**
	 * Create the panel.
	 */
	public Dimension getPreferredSize()
	{
		return new Dimension(286,memberspanel.getY()+memberspanel.getHeight());
	}
	public void setStudent(Student s)
	{
		this.student=s;
		headerlabel.setText("Your Info");
	}
	public void setFaculty(Faculty f)
	{
		this.faculty=f;
		headerlabel.setText("Your Info");
	}
	public void setAdmin(Admin a)
	{
		this.admin=a;
	}
	
	public ChatInfoPanel() {
		setSize(286,705);
		setLayout(null);
		setBackground(Color.white);
		
		JPanel groupinfopanel = new JPanel();
		groupinfopanel.setBorder(null);
		groupinfopanel.setBackground(Color.WHITE);
		groupinfopanel.setBounds(1, 60, 284, 275);
		add(groupinfopanel);
		groupinfopanel.setLayout(null);
		
		onlinestatus = new JLabel(new ImageIcon("./assets/onlinestatusbig.png"));
		onlinestatus.setBounds(180, 180, 30, 30);
		groupinfopanel.add(onlinestatus);
		
		profilepic = new JLabel("Image");
		profilepic.setBorder(null);
		profilepic.setBackground(new Color(245, 245, 245));
		profilepic.setHorizontalAlignment(SwingConstants.CENTER);
		profilepic.setBounds(41, 11, 200, 200);
		groupinfopanel.add(profilepic);
		
		contactnamelabel = new JLabel("Name");
		contactnamelabel.setBorder(null);
		contactnamelabel.setHorizontalAlignment(SwingConstants.CENTER);
		contactnamelabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		contactnamelabel.setBounds(0, 216, 286, 32);
		groupinfopanel.add(contactnamelabel);
		
		bottomlabel = new JLabel("");
		bottomlabel.setHorizontalAlignment(SwingConstants.CENTER);
		bottomlabel.setForeground(Color.GRAY);
		bottomlabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		bottomlabel.setBounds(0, 246, 286, 22);
		groupinfopanel.add(bottomlabel);
		
		userdetailspanel = new JPanel();
		userdetailspanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		userdetailspanel.setBackground(Color.WHITE);
		userdetailspanel.setBounds(1, 365, 285, 253);
		add(userdetailspanel);
		userdetailspanel.setLayout(null);
		
		JLabel contacticon = new JLabel();
		contacticon.setBounds(20, 18, 32, 32);
		contacticon.setBorder(null);
		contacticon.setHorizontalAlignment(SwingConstants.CENTER);
		contacticon.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contacticon.setIcon(new ImageIcon("./assets/callinfo.png"));
		userdetailspanel.add(contacticon);
		
		contactnumberlabel = new JLabel("9999343433");
		contactnumberlabel.setBounds(68, 11, 207, 19);
		contactnumberlabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		contactnumberlabel.setForeground(Color.DARK_GRAY);
		userdetailspanel.add(contactnumberlabel);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setBounds(70, 33, 207, 19);
		lblMobile.setForeground(Color.GRAY);
		lblMobile.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		userdetailspanel.add(lblMobile);
		
		JLabel line1 = new JLabel("");
		line1.setBounds(68, 60, 217, 6);
		line1.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.LIGHT_GRAY));
		userdetailspanel.add(line1);
		
		emailidlabel = new JLabel("ajaysinhrathod@gmail.com");
		emailidlabel.setBounds(68, 68, 207, 25);
		emailidlabel.setForeground(Color.DARK_GRAY);
		emailidlabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		userdetailspanel.add(emailidlabel);
		
		JLabel lblEmailid = new JLabel("Email-ID");
		lblEmailid.setBounds(68, 96, 207, 19);
		lblEmailid.setForeground(Color.GRAY);
		lblEmailid.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		userdetailspanel.add(lblEmailid);
		
		JLabel line2 = new JLabel("");
		line2.setBounds(68, 122, 217, 6);
		line2.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.LIGHT_GRAY));
		userdetailspanel.add(line2);
		
		JLabel emailicon = new JLabel(new ImageIcon("./assets/mailinfo.png"));
		emailicon.setBounds(20, 76, 32, 32);
		emailicon.setHorizontalAlignment(SwingConstants.CENTER);
		emailicon.setFont(new Font("Tahoma", Font.PLAIN, 18));
		emailicon.setBorder(null);
		userdetailspanel.add(emailicon);
		
		locationlabel = new JLabel("");
		locationlabel.setBounds(68, 134, 207, 19);
		locationlabel.setForeground(Color.DARK_GRAY);
		locationlabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		userdetailspanel.add(locationlabel);
		
		lblLocation = new JLabel("Location");
		lblLocation.setBounds(68, 158, 207, 19);
		lblLocation.setForeground(Color.GRAY);
		lblLocation.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		userdetailspanel.add(lblLocation);
		
		locationicon = new JLabel(new ImageIcon("./assets/locationinfo.png"));
		locationicon.setBounds(20, 143, 32, 32);
		locationicon.setHorizontalAlignment(SwingConstants.CENTER);
		locationicon.setFont(new Font("Tahoma", Font.PLAIN, 18));
		locationicon.setBorder(null);
		userdetailspanel.add(locationicon);
		
		line3 = new JLabel("");
		line3.setBounds(68, 185, 217, 6);
		line3.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.LIGHT_GRAY));
		userdetailspanel.add(line3);
		
		dateofbirthlabel = new JLabel("");
		dateofbirthlabel.setBounds(68, 199, 207, 19);
		dateofbirthlabel.setForeground(Color.DARK_GRAY);
		dateofbirthlabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		userdetailspanel.add(dateofbirthlabel);
		
		lblDateOfBirth = new JLabel("Date of birth");
		lblDateOfBirth.setBounds(68, 223, 207, 19);
		lblDateOfBirth.setForeground(Color.GRAY);
		lblDateOfBirth.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		userdetailspanel.add(lblDateOfBirth);
		
		dobicon = new JLabel(new ImageIcon("./assets/dobinfo.png"));
		dobicon.setBounds(20, 203, 32, 32);
		dobicon.setHorizontalAlignment(SwingConstants.CENTER);
		dobicon.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dobicon.setBorder(null);
		userdetailspanel.add(dobicon);
		
		
		

		memberspanelscroll=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		memberspanelscroll.setBounds(0, 368,280,335);
		memberspanelscroll.getVerticalScrollBar().setUnitIncrement(80);
		memberspanelscroll.getVerticalScrollBar().setPreferredSize(new Dimension(5,0));
		memberspanelscroll.setBorder(null);
		
		memberspanel = new JPanel()
				{
					public Dimension getPreferredSize()
					{
						return new Dimension(286,total*62);
					}
				};
		memberspanel.setSize(0, 0);
		memberspanel.setBackground(Color.WHITE);
		memberspanel.setLocation(0, 0);
		memberspanel.setVisible(false);
		memberspanel.setLayout(null);
		memberspanelscroll.setViewportView(memberspanel);
		add(memberspanelscroll);
		
		headerlabel = new JLabel("Group Info");
		headerlabel.setOpaque(true);
		headerlabel.setBounds(1, 0, 286, 60);
		add(headerlabel);
		headerlabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerlabel.setFont(new Font("Nirmala UI", Font.PLAIN, 20));
		headerlabel.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(192, 192, 192)));
		headerlabel.setBackground(Color.WHITE);
		
		aboutheadinglabel = new JLabel("About & Contact info");
		aboutheadinglabel.setHorizontalAlignment(SwingConstants.CENTER);
		aboutheadinglabel.setBackground(new Color(240,240,240));
		aboutheadinglabel.setBorder(new EmptyBorder(0, 0, 0, 0));
		aboutheadinglabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		aboutheadinglabel.setOpaque(true);
		aboutheadinglabel.setBounds(0, 336, 285, 30);
		add(aboutheadinglabel);

	}
	public void setData(Student s)
	{
		this.Enable();
		if(student!=null && s.getUserId().equals(student.getUserId()))
		{
			onlinestatus.setVisible(true);
		}
		total=0;
		profilepic.setText("");
		profilepic.setIcon(new ImageIcon(s.getRoundedProfilePic(profilepic.getWidth(), profilepic.getHeight(), profilepic.getWidth())));
		bottomlabel.setText(s.getUserId());
		contactnamelabel.setText(s.getFullName());
		headerlabel.setText("Student Info");
		if(student!=null && s.getUserId().equals(student.getUserId()))
		{
			headerlabel.setText("Your Info");	
		}
		
		memberspanel.setVisible(false);
		memberspanelscroll.setVisible(false);
		userdetailspanel.setVisible(true);
		emailidlabel.setText(s.getEmailId());
		contactnumberlabel.setText("+91 "+s.getContactNumber());
		locationlabel.setText(s.getAddress());
		dateofbirthlabel.setText(s.getBirthDate());
		aboutheadinglabel.setText("About & Contact info");
		if(s.getActiveStatus())
		{
			onlinestatus.setVisible(true);
		}
		else
		{
			onlinestatus.setVisible(false);
		}
	}
	public void setData(Faculty f)
	{
		this.Enable();
		if(faculty!=null && f.getFacultyId()==faculty.getFacultyId())
		{
			onlinestatus.setVisible(true);
		}
		total=0;
		profilepic.setText("");
		profilepic.setIcon(new ImageIcon(f.getRoundedProfilePic(profilepic.getWidth(),profilepic.getHeight(), profilepic.getWidth())));
		bottomlabel.setText("("+f.getFacultyId()+") "+f.getCourceCode()+"-"+f.getSemorYear()+"-"+f.getSubject());
		contactnamelabel.setText(f.getFacultyName());
		headerlabel.setText("Faculty Info");
		if(faculty!=null && f.getFacultyId()==faculty.getFacultyId())
		{
			headerlabel.setText("Your Info");	
		}
		memberspanel.setVisible(false);
		memberspanelscroll.setVisible(false);
		userdetailspanel.setVisible(true);
		emailidlabel.setText(f.getEmailId());
		contactnumberlabel.setText("+91 "+f.getContactNumber());
		locationlabel.setText(f.getCity()+", "+f.getState());
		dateofbirthlabel.setText(f.getBirthDate());
		aboutheadinglabel.setText("About & Contact info");
		if(f.getActiveStatus())
		{
			onlinestatus.setVisible(true);
		}
		else
		{
			onlinestatus.setVisible(false);
		}
		
	}
	public void setData(Group g)
	{
		this.Enable();
		total=0;
		profilepic.setText("");
		profileimage=g.getImage();
		
		BufferedImage image=ImageUtil.toBufferedImage(g.getImage().getScaledInstance(profilepic.getWidth(), profilepic.getHeight(), Image.SCALE_SMOOTH));
		profilepic.setIcon(new ImageIcon(ImageUtil.makeRoundedCorner(image, profilepic.getWidth())));
		bottomlabel.setText(g.getMembers()+" Members");
		contactnamelabel.setText(g.getGroupName());
		headerlabel.setText("Group Info");
		userdetailspanel.setVisible(false);
		membersPanel(g);
		memberspanel.setVisible(true);
		memberspanelscroll.setVisible(true);
		aboutheadinglabel.setText("Group Members");
		
	}
	
	public void setData(Admin a)
	{
		this.Disable();
		onlinestatus.setVisible(a.getActiveStatus());
		total=0;
		profilepic.setText("");
		profilepic.setIcon(new ImageIcon(a.getRoundedProfilePic(profilepic.getWidth(), profilepic.getHeight(), profilepic.getWidth())));
		bottomlabel.setText(a.getEmailId());
		contactnamelabel.setText("Admin");
		headerlabel.setText("Admin Info");
		memberspanel.setVisible(false);
		memberspanelscroll.setVisible(false);
		userdetailspanel.setVisible(true);
		aboutheadinglabel.setText("Contact info");
		emailidlabel.setText(a.getEmailId());
		contactnumberlabel.setText(a.getContactNumber());

		
	}
	private void membersPanel(Group g)
	{
		// TODO Auto-generated method stub
		total=0;
		onlinestatus.setVisible(false);
		memberspanel.removeAll();
		memberspanel.setVisible(false);
		String gn=g.getGroupName();
		if(admin!=null)
		{
			JPanel adminpanel=createPanel(g.getImage(),"You","Admin",admin.getActiveStatus());
			memberspanel.add(adminpanel);
			total++;
		}
		else if(faculty!=null)
		{
			JPanel facultypanel=createPanel(faculty.getProfilePic(),"You","Faculty-"+faculty.getFacultyId(),true);
			memberspanel.add(facultypanel);
			total++;
		}
		else if(student!=null)
		{
			JPanel studentpanel=createPanel(student.getProfilePic(),"You","Student-"+student.getUserId(),true);
			memberspanel.add(studentpanel);
			total++;
		}
		if(gn.contains("Official"))
		{
			if(admin==null)
			{
				JPanel adminpanel=createPanel(g.getImage(),"Principal","Admin",new AdminData().getAdminData().getActiveStatus());
				memberspanel.add(adminpanel);
				total++;
			}
			
		}
		
	for(ContactInfo c:ContactListPanel.contactinfo)
		{
			
		
			if((gn.contains("Students")||gn.contains("Official"))&&c.getClassName().equals("Student"))
			{
				Student s=c.getStudent();
				if(s.getCourceCode().equals(g.getCourceCode())&&s.getSemorYear()==g.getSemorYear())
				{
				JPanel panel=createPanel(s.getProfilePic(),s.getFullName(),"Student-"+s.getUserId(),s.getActiveStatus());
				memberspanel.add(panel);
				total++;
				}
			}
			else if((gn.contains("Faculties")||gn.contains("Official"))&&c.getClassName().equals("Faculty"))
			{
				Faculty f=c.getFaculty();
				if(f.getCourceCode().equals(g.getCourceCode())&&f.getSemorYear()==g.getSemorYear())
				{
				JPanel panel=createPanel(f.getProfilePic(),f.getFacultyName(),"Faculty-"+f.getFacultyId(),f.getActiveStatus());
				memberspanel.add(panel);
				total++;
				}
			}
			
		}
//		memberspanel.revalidate();
		memberspanel.setVisible(true);
	}
	public JPanel createPanel(Image image,String username,String lastlogin,boolean isactive)
	{
		JPanel usernamepanel=new JPanel();
		usernamepanel.setLayout(null);
		usernamepanel.setSize(286,60);
		usernamepanel.setLocation(0,total*62);
		usernamepanel.setBackground(Color.white);

		
		
		BufferedImage profilepic=ImageUtil.toBufferedImage(image.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		profilepic=ImageUtil.makeRoundedCorner(profilepic, 50);
		JLabel profilepiclabel=new JLabel(new ImageIcon(profilepic));
		profilepiclabel.setLocation(10, 5);
		profilepiclabel.setSize(50, 50);
		if(isactive)
		{
		JLabel onlinestatus = new JLabel(new ImageIcon("./assets/onlinestatus.png"));
		onlinestatus.setBounds(45, 40, 15, 15);
		usernamepanel.add(onlinestatus);
		}
		usernamepanel.add(profilepiclabel);
		
		JLabel usernamelabel=new JLabel(username);
		usernamelabel.setBackground(Color.white);
		usernamelabel.setBackground(Color.DARK_GRAY);
		usernamelabel.setFont(new Font("Segoe UI",Font.BOLD,15));
		usernamelabel.setLocation(70, 5);
		usernamelabel.setSize(250, 30);
		usernamepanel.add(usernamelabel);
		
		JLabel lastloginlabel=new JLabel();
		lastloginlabel.setName("lastlogin");
		if(lastlogin==null || lastlogin.isEmpty())
		{
			lastloginlabel.setText("Start new conversion");
		}
		else if(lastlogin.contains("Members")||lastlogin.equals("Admin")||lastlogin.contains("Faculty")||lastlogin.contains("Student"))
		{
			lastloginlabel.setText(lastlogin);
		}
		
		else 
		{
//			lastloginlabel.setText("Active "+TimeUtil.getDateDifference(lastlogin)+"");
			lastloginlabel.setText(lastlogin);
		}
		
		lastloginlabel.setBackground(Color.white);
		lastloginlabel.setForeground(Color.gray);
		lastloginlabel.setFont(new Font("Segoe UI",Font.PLAIN,13));
		lastloginlabel.setSize(200, 30);
		lastloginlabel.setLocation(70, 25);
		usernamepanel.add(lastloginlabel);
		return usernamepanel;
		
	}
	public Image imageIcon()
	{
		return profileimage;
	}
	public String getContactName()
	{
		return contactnamelabel.getText();
	}
	public void Disable()
	{

		locationlabel.setVisible(false);
		lblLocation.setVisible(false);		
		locationicon.setVisible(false);
		line3.setVisible(false);
		
		dateofbirthlabel.setVisible(false);
		lblDateOfBirth.setVisible(false);
		dobicon.setVisible(false);
	}
	public void Enable()
	{
		locationlabel.setVisible(true);
		lblLocation.setVisible(true);		
		locationicon.setVisible(true);
		line3.setVisible(true);
		
		dateofbirthlabel.setVisible(true);
		lblDateOfBirth.setVisible(true);
		dobicon.setVisible(true);
	}
}
