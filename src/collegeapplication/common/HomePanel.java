package collegeapplication.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import collegeapplication.admin.Admin;
import collegeapplication.cource.CourceData;
import collegeapplication.faculty.Faculty;
import collegeapplication.faculty.FacultyData;
import collegeapplication.student.Student;
import collegeapplication.student.StudentData;
import collegeapplication.subject.SubjectData;

/*
 * Title : HomePanel.java
 * Created by : Ajaysinh Rathod
 * Purpose : Home Page
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class HomePanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private JPanel homeheaderpanel;
	private JLabel totalstudentlabel, totalfaculitieslabel, totalcourcelabel, totallectureslabel;
	public JLabel lastloginlabel;
	private JLabel timedifflabel;
	private JLabel welcomelabel;
	private JLabel totalnotificationlabel;
	private JPanel notificationpanel;
	private JPanel courcespanel;
	private JPanel faculitiespanel;
	private JPanel studentspanel;
	int pos[]= {20,294,568,842};
	private JPanel subjectpanel;
	/**
	 * Create the panel.
	 */
	private HomePanel() {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		this.setSize(1116, 705);
		setLayout(null);
		
		
				

		notificationpanel = new JPanel();
		notificationpanel.setBorder(new LineBorder(new Color(192, 192, 192), 3));
		notificationpanel.setBounds(20, 244, 253, 247);
		add(notificationpanel);
		notificationpanel.setBackground(new Color(255, 255, 255));
		notificationpanel.setLayout(null);
		notificationpanel.setVisible(false);

		totalnotificationlabel = new JLabel("0");
		totalnotificationlabel.setForeground(new Color(128, 128, 128));
		totalnotificationlabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		totalnotificationlabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalnotificationlabel.setBounds(10, 174, 233, 35);
		notificationpanel.add(totalnotificationlabel);

		JLabel lblNotification = new JLabel("Notification");
		lblNotification.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblNotification.setForeground(new Color(128, 128, 128));
		lblNotification.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotification.setHorizontalTextPosition(JLabel.CENTER);
		lblNotification.setVerticalTextPosition(JLabel.BOTTOM);
		lblNotification.setBounds(10, 31, 233, 142);
		notificationpanel.add(lblNotification);
		lblNotification.setIcon(new ImageIcon(".//assets//notificationhomepage.png"));
		
		courcespanel = new JPanel();
		courcespanel.setBorder(new LineBorder(new Color(192, 192, 192), 3));
		courcespanel.setBounds(20, 244, 253, 247);
		add(courcespanel);
		courcespanel.setBackground(new Color(255, 255, 255));
		courcespanel.setLayout(null);
		
		totalcourcelabel = new JLabel("0");
		totalcourcelabel.setForeground(new Color(128, 128, 128));
		totalcourcelabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		totalcourcelabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalcourcelabel.setBounds(10, 174, 233, 35);
		courcespanel.add(totalcourcelabel);
		
		JLabel lblCources = new JLabel("Cources");
		lblCources.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblCources.setForeground(new Color(128, 128, 128));
		lblCources.setHorizontalAlignment(SwingConstants.CENTER);
		lblCources.setHorizontalTextPosition(JLabel.CENTER);
		lblCources.setVerticalTextPosition(JLabel.BOTTOM);
		lblCources.setBounds(10, 31, 233, 142);
		courcespanel.add(lblCources);
		lblCources.setIcon(new ImageIcon(".//assets//courceshomepage.png"));

		studentspanel = new JPanel();
		studentspanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		studentspanel.setLayout(null);
		studentspanel.setBackground(Color.WHITE);
		studentspanel.setBounds(294, 244, 253, 247);
		add(studentspanel);

		totalstudentlabel = new JLabel("0");
		totalstudentlabel.setText(new StudentData().getTotalStudents() + "");
		totalstudentlabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalstudentlabel.setForeground(Color.GRAY);

		totalstudentlabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		totalstudentlabel.setBounds(10, 174, 233, 35);
		studentspanel.add(totalstudentlabel);

		JLabel lblStudents = new JLabel("Students");
		lblStudents.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudents.setForeground(Color.GRAY);
		lblStudents.setIcon(null);
		lblStudents.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblStudents.setBounds(10, 32, 233, 144);
		lblStudents.setHorizontalTextPosition(JLabel.CENTER);
		lblStudents.setVerticalTextPosition(JLabel.BOTTOM);
		studentspanel.add(lblStudents);
		lblStudents.setIcon(new ImageIcon(".//assets//studenthomepage.png"));
		
		faculitiespanel = new JPanel();
		faculitiespanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		faculitiespanel.setLayout(null);
		faculitiespanel.setBackground(Color.WHITE);
		faculitiespanel.setBounds(568, 244, 253, 247);
		add(faculitiespanel);

		totalfaculitieslabel = new JLabel("0");
		totalfaculitieslabel.setBackground(Color.WHITE);
		totalfaculitieslabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalfaculitieslabel.setForeground(Color.GRAY);
		totalfaculitieslabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		totalfaculitieslabel.setBounds(10, 174, 233, 35);
		faculitiespanel.add(totalfaculitieslabel);

		JLabel lblFaculities = new JLabel("Faculities");
		lblFaculities.setHorizontalAlignment(SwingConstants.CENTER);
		lblFaculities.setForeground(Color.GRAY);
		lblFaculities.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblFaculities.setBounds(10, 34, 233, 140);
		lblFaculities.setHorizontalTextPosition(JLabel.CENTER);
		lblFaculities.setVerticalTextPosition(JLabel.BOTTOM);
		faculitiespanel.add(lblFaculities);
		lblFaculities.setIcon(new ImageIcon(".//assets//facultyhomepage.png"));
		
		subjectpanel = new JPanel();
		subjectpanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		subjectpanel.setLayout(null);
		subjectpanel.setBackground(Color.WHITE);
		subjectpanel.setBounds(842, 244, 253, 247);
		add(subjectpanel);

		totallectureslabel = new JLabel("0");

		totallectureslabel.setHorizontalAlignment(SwingConstants.CENTER);
		totallectureslabel.setForeground(Color.GRAY);
		totallectureslabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		totallectureslabel.setBounds(10, 174, 233, 35);
		subjectpanel.add(totallectureslabel);

		JLabel lblLectures = new JLabel("Subjects");
		lblLectures.setHorizontalAlignment(SwingConstants.CENTER);
		lblLectures.setForeground(Color.GRAY);
		lblLectures.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblLectures.setBounds(10, 40, 233, 141);
		lblLectures.setIconTextGap(10);
		lblLectures.setHorizontalTextPosition(JLabel.CENTER);
		lblLectures.setVerticalTextPosition(JLabel.BOTTOM);
		subjectpanel.add(lblLectures);
		try {
			Image image=ImageIO.read(new File(".//assets//subjects2.png"));
			lblLectures.setIcon(new ImageIcon(image.getScaledInstance(85, 85, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();         
		}

		homeheaderpanel = new JPanel();
		homeheaderpanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		homeheaderpanel.setBackground(new Color(32, 178, 170));
		homeheaderpanel.setLayout(null);
		homeheaderpanel.setBounds(10, 0, 1096, 279);
		add(homeheaderpanel);

		welcomelabel = new JLabel("Welcome");
		welcomelabel.setHorizontalAlignment(SwingConstants.RIGHT);
		welcomelabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		welcomelabel.setForeground(Color.WHITE);
		welcomelabel.setBounds(10, 11, 1076, 45);
		homeheaderpanel.add(welcomelabel);

		JLabel lblHome = new JLabel("Home Page");
		lblHome.setIcon(null);
		lblHome.setForeground(Color.WHITE);
		lblHome.setFont(new Font("Segoe UI", Font.BOLD, 29));
		lblHome.setBounds(10, 97, 377, 45);
		homeheaderpanel.add(lblHome);

		lastloginlabel = new JLabel("Last Login : First Login");
		lastloginlabel.setBackground(Color.WHITE);
		lastloginlabel.setForeground(Color.WHITE);
		lastloginlabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lastloginlabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lastloginlabel.setBounds(20, 47, 1066, 30);
		homeheaderpanel.add(lastloginlabel);

		timedifflabel = new JLabel("");
		timedifflabel.setHorizontalAlignment(SwingConstants.RIGHT);
		timedifflabel.setForeground(Color.WHITE);
		timedifflabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		timedifflabel.setBackground(Color.WHITE);
		timedifflabel.setBounds(599, 75, 486, 19);
		homeheaderpanel.add(timedifflabel);

	}

	public HomePanel(Admin a) {
		this();
		totalfaculitieslabel.setText(new FacultyData().getTotalFaculaty() + "");
		totalstudentlabel.setText(new StudentData().getTotalStudents() + "");
		totalcourcelabel.setText(new CourceData().getTotalCource() + "");
		welcomelabel.setText("Welcome Adminstrator");
		totallectureslabel.setText(new SubjectData().getTotalSubject() + "");
	}

	public HomePanel(Faculty f) {
		this();
		totalfaculitieslabel.setText(new FacultyData().getFaculaty(f.getCourceCode(), f.getSemorYear()) + "");
		totalstudentlabel.setText(new StudentData().getTotalStudentInCource(f.getCourceCode(), f.getSemorYear()) + "");
		totalnotificationlabel.setText(""+new NotificationData().getUnreadNotification(f.getFacultyId()+"", "Faculty", f.getCourceCode(), f.getSemorYear(),f.getJoinedDate()));
		courcespanel.setVisible(false);
		notificationpanel.setVisible(true);
		welcomelabel.setText("Welcome " + f.getFacultyName());
		totallectureslabel.setText(new SubjectData().getTotalSubjectinCource(f.getCourceCode(), f.getSemorYear()) + "");

		studentspanel.setLocation(pos[0], studentspanel.getY());
		faculitiespanel.setLocation(pos[1], faculitiespanel.getY());
		subjectpanel.setLocation(pos[2], subjectpanel.getY());
		notificationpanel.setLocation(pos[3], notificationpanel.getY());
		
	}

	public HomePanel(Student s) {
		this();
		totalfaculitieslabel.setText(new FacultyData().getFaculaty(s.getCourceCode(), s.getSemorYear()) + "");
		totalstudentlabel.setText(new StudentData().getTotalStudentInCource(s.getCourceCode(), s.getSemorYear()) + "");
		
		totalnotificationlabel.setText(""+new NotificationData().getUnreadNotification(s.getUserId()+"", "Student", s.getCourceCode(), s.getSemorYear(),s.getAdmissionDate()));
		courcespanel.setVisible(false);
		notificationpanel.setVisible(true);
		welcomelabel.setText("Welcome " +s.getFullName());
		totallectureslabel.setText(new SubjectData().getTotalSubjectinCource(s.getCourceCode(), s.getSemorYear()) + "");
		studentspanel.setLocation(pos[0], studentspanel.getY());
		faculitiespanel.setLocation(pos[1], faculitiespanel.getY());
		subjectpanel.setLocation(pos[2], subjectpanel.getY());
		notificationpanel.setLocation(pos[3], notificationpanel.getY());
	}

	public void setLastLogin(String lastlogin) {
		if (lastlogin == null || lastlogin.isEmpty()) {
			this.lastloginlabel.setText("last login : First Time");
		} else {
			this.lastloginlabel.setText("last login : " + lastlogin);
//		this.timedifflabel.setText(TimeUtil.getDateDifference(lastlogin));
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
