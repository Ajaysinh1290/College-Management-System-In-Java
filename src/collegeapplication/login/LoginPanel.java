package collegeapplication.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import collegeapplication.admin.AdminData;
import collegeapplication.admin.AdminMain;
import collegeapplication.common.HintPasswordField;
import collegeapplication.common.HintTextField;
import collegeapplication.faculty.Faculty;
import collegeapplication.faculty.FacultyData;
import collegeapplication.faculty.FacultyMain;
import collegeapplication.common.UserData;
import collegeapplication.student.Student;
import collegeapplication.student.StudentData;
import collegeapplication.student.StudentMain;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel implements ActionListener
{

	public HintTextField useridfield;
	public JPasswordField passwordfield;
	public JButton loginbutton;
	String loginprofile;
	private LoginPageFrame loginpageframe;

	/**
	 * Create the panel.
	 */
	public LoginPanel(String loginprofile,ImageIcon imageicon,LoginPageFrame lpf) {
		
		this.loginprofile=loginprofile;
		this.loginpageframe=lpf;
		setBorder(new LineBorder(new Color(192, 192, 192)));
		setBackground(new Color(0, 0, 0,80));
		setBounds(490, 206, 420, 434);
		setLayout(null);
		
		JLabel lblPassword = new JLabel("");
		lblPassword.setOpaque(true);
		lblPassword.setBackground(new Color(32, 178, 170));
		lblPassword.setIcon(new ImageIcon(".\\assets\\password1.png"));
		lblPassword.setBounds(20, 272, 60, 44);
		add(lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblPassword.setBorder(new LineBorder(new Color(192, 192, 192)));
		
		useridfield = new HintTextField("Userid");
		useridfield.setBorder(new EmptyBorder(0,3,0,0));
		useridfield.setToolTipText("User Id");
		useridfield.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		useridfield.setBounds(80, 196, 323, 44);
		useridfield.setForeground(Color.DARK_GRAY);
		add(useridfield);
		useridfield.setColumns(10);
		
		JLabel lblEmailId = new JLabel("");
		lblEmailId.setOpaque(true);
		lblEmailId.setFocusable(true);
		lblEmailId.setBackground(new Color(32, 178, 170));
		lblEmailId.setIcon(new ImageIcon(".\\assets\\userid.png"));
		lblEmailId.setBounds(20, 196, 60, 44);
		add(lblEmailId);
		lblEmailId.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailId.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblEmailId.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		loginbutton = new JButton("Login");
		
	
		loginbutton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		loginbutton.setForeground(new Color(255, 255, 255));
		loginbutton.addActionListener(this);
		loginbutton.setBackground(new Color(32, 178, 170));
		loginbutton.setBounds(20, 355, 383, 44);
		loginbutton.setFocusable(false);
		loginbutton.setBorderPainted(false);
		add(loginbutton);
		
		JLabel lblStudentLogin = new JLabel(loginprofile+" Login");
		lblStudentLogin.setForeground(new Color(255, 255, 255));
		lblStudentLogin.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblStudentLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentLogin.setBounds(10, 121, 420, 38);
		add(lblStudentLogin);
		
		JLabel userprofilelabel = new JLabel();
		userprofilelabel.setIcon(imageicon);
		userprofilelabel.setBounds(169, 28, 100, 98);
		add(userprofilelabel);
		
		passwordfield = new HintPasswordField("Password");
		passwordfield.setBorder(useridfield.getBorder());
		passwordfield.setToolTipText("Password");
		passwordfield.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		passwordfield.setBounds(80, 272, 261, 44);
		add(passwordfield);
		
		JButton showandhidebutton = new JButton("show");
		   showandhidebutton.setForeground(new Color(255, 255, 255));
		showandhidebutton.setBounds(341, 272, 62, 44);
		  showandhidebutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		    showandhidebutton.setFocusable(false);
		    showandhidebutton.setFocusPainted(false);
		    showandhidebutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		    showandhidebutton.setBackground(new Color(32, 178, 170));
		    showandhidebutton.setBorderPainted(false);
		    showandhidebutton.addActionListener(e->
		    {
		    	if(showandhidebutton.getText().equals("show"))
		    	{
		    		passwordfield.setEchoChar('\u0000');
		    		showandhidebutton.setText("hide");
		    	}
		    	else
		    	{
		    		passwordfield.setEchoChar((Character)UIManager.get("PasswordField.echoChar"));
		    		showandhidebutton.setText("show");
		    	}
		    });
		add(showandhidebutton);

	}
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		
		if(loginprofile.equals("Admin"))
		{
			boolean result=new AdminData().checkPassword(useridfield.getText(), passwordfield.getText());
			if(result==true)
			{
				
				AdminMain am=new AdminMain();
				am.setVisible(true);
				am.setLocationRelativeTo(null);
				loginpageframe.timer.stop();
				loginpageframe.imagetimer.stop();
				System.out.println("Timer running "+loginpageframe.timer.isRunning());

				loginpageframe.dispose();
				
				
				
			}
			
		}
		else if(loginprofile.equals("Faculty"))
		{
			boolean result=new FacultyData().checkPassword(useridfield.getText(), passwordfield.getText());
			if(result==true)
			{
				Faculty f=new FacultyData().getFacultyInfobyUserId(useridfield.getText());
				if(!f.getCourceCode().equals("Not Assigned"))
				{
				
					new UserData().addFacultyLoginTime(f);
					FacultyMain fm=new FacultyMain(f);
					fm.setVisible(true);
					fm.setLocationRelativeTo(null);
					loginpageframe.timer.stop();
					loginpageframe.imagetimer.stop();
					loginpageframe.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Your account is not activated. contact principal","Login Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		else if(loginprofile.equals("Student"))
		{
			boolean result=new StudentData().checkPassword(useridfield.getText(), passwordfield.getText());
			if(result==true)
			{
				Student s=new StudentData().getStudentDetailsByUserId(useridfield.getText());
				new UserData().addStudentLoginTime(s);
				StudentMain sm=new StudentMain(s);
				sm.setVisible(true);
				sm.setLocationRelativeTo(null);
				loginpageframe.timer.stop();
				loginpageframe.imagetimer.stop();
				loginpageframe.dispose();
				
			}
		}
	}
}
