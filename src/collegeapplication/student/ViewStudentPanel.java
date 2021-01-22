package collegeapplication.student;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import collegeapplication.admin.AdminMain;
import collegeapplication.common.ChangePasswordDialog;
import collegeapplication.cource.CourceData;
import collegeapplication.faculty.FacultyMain;

/*
 * Title : ViewStudentPanel.java
 * Created by : Ajaysinh Rathod
 * Purpose : Displaying all the details of student
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class ViewStudentPanel extends JPanel {

	private JButton editdetailsbutton;
	public JComponent lastpanel;
	AdminMain am;
	FacultyMain fm;
	StudentMain sm;
	private JButton backbutton;
	private JButton marksheetbutton;
	private JLabel lblLastLogin;

	/**
	 * Create the panel.
	 * @wbp.parser.constructor
*/
	private ViewStudentPanel(Student s)
	{
		
		setBackground(new Color(255, 255, 255));
		this.setSize(1116, 705);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(10, 0, 1095, 188);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblDisplayingStudentDetails = new JLabel(s.getFullName()+" ("+s.getUserId()+")");
		lblDisplayingStudentDetails.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDisplayingStudentDetails.setForeground(new Color(255, 255, 255));
		lblDisplayingStudentDetails.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblDisplayingStudentDetails.setBounds(0, 11, 1067, 44);
		panel.add(lblDisplayingStudentDetails);
		
		editdetailsbutton = new JButton("Edit Details");
		editdetailsbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		editdetailsbutton.setFocusable(false);
		editdetailsbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editdetailsbutton.setForeground(new Color(0, 139, 139));
		editdetailsbutton.setBackground(new Color(255, 255, 255));
		editdetailsbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));

		editdetailsbutton.setBounds(932, 141, 140, 35);
		panel.add(editdetailsbutton);
		
		 marksheetbutton = new JButton("Marksheet");
		marksheetbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		marksheetbutton.setFocusable(false);

		
		marksheetbutton.setForeground(new Color(0, 139, 139));
		marksheetbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		marksheetbutton.setBackground(new Color(255, 255, 255));
		marksheetbutton.setBounds(782, 141, 140, 35);
		marksheetbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(marksheetbutton);
		
		backbutton = new JButton("Back");
		backbutton.setContentAreaFilled(false);
		backbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
	
		backbutton.setIcon(new ImageIcon(".\\assets\\back.png"));
		backbutton.setFocusable(false);
		backbutton.setForeground(Color.WHITE);
		backbutton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		backbutton.setBackground(new Color(32, 178, 170));
		backbutton.setBounds(10, 141, 88, 36);
		panel.add(backbutton);
		
		lblLastLogin = new JLabel("Last Login ");
		if(s.getLastLogin()==null||s.getLastLogin().isEmpty())
		{
			lblLastLogin.setText("Last Login : No Login");
		}
		
		else 
		{
			lblLastLogin.setText("Last Login : "+s.getLastLogin());
		}
		lblLastLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastLogin.setForeground(Color.WHITE);
		lblLastLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLastLogin.setBounds(710, 58, 357, 19);
		panel.add(lblLastLogin);
		
		JLabel lblStudentDetails = new JLabel("Student Details");
		lblStudentDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentDetails.setForeground(Color.WHITE);
		lblStudentDetails.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblStudentDetails.setBounds(10, 65, 415, 44);
		panel.add(lblStudentDetails);
		
		JLabel lblRollNo = new JLabel("Roll no  ");
		lblRollNo.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblRollNo.setBackground(new Color(255, 255, 255));
		lblRollNo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblRollNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRollNo.setOpaque(true);
		lblRollNo.setBounds(309, 66+150, 274, 48);
		add(lblRollNo);
		
		JLabel lblStudentName = new JLabel("Student Name  ");
		lblStudentName.setOpaque(true);
		lblStudentName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudentName.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblStudentName.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblStudentName.setBackground(Color.WHITE);
		lblStudentName.setBounds(309, 113+150, 274, 48);
		add(lblStudentName);
		
		JLabel lblAddress = new JLabel("Address  ");
		lblAddress.setOpaque(true);
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblAddress.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblAddress.setBackground(Color.WHITE);
		lblAddress.setBounds(309, 160+150, 274, 48);
		add(lblAddress);
		
		JLabel lblEmailId = new JLabel("Email ID  ");
		lblEmailId.setOpaque(true);
		lblEmailId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmailId.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblEmailId.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblEmailId.setBackground(Color.WHITE);
		lblEmailId.setBounds(309, 207+150, 274, 48);
		add(lblEmailId);
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth ");
		lblDateOfBirth.setOpaque(true);
		lblDateOfBirth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateOfBirth.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblDateOfBirth.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblDateOfBirth.setBackground(Color.WHITE);
		lblDateOfBirth.setBounds(309, 254+150, 274, 48);
		add(lblDateOfBirth);
		
		JLabel lblContactNumber = new JLabel("Contact Number ");
		lblContactNumber.setOpaque(true);
		lblContactNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContactNumber.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblContactNumber.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblContactNumber.setBackground(Color.WHITE);
		lblContactNumber.setBounds(309, 300+150, 274, 48);
		add(lblContactNumber);
		
		JLabel lblCource = new JLabel("Cource  ");
		lblCource.setOpaque(true);
		lblCource.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCource.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblCource.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblCource.setBackground(Color.WHITE);
		lblCource.setBounds(20, 359+150, 291, 48);
		add(lblCource);
		
		JLabel lblFatherName = new JLabel("Father Name  ");
		lblFatherName.setOpaque(true);
		lblFatherName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFatherName.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblFatherName.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblFatherName.setBackground(Color.WHITE);
		lblFatherName.setBounds(20, 405+150, 291, 48);
		add(lblFatherName);
		
		JLabel lblFatherOccupation = new JLabel("Father Occupation  ");
		lblFatherOccupation.setOpaque(true);
		lblFatherOccupation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFatherOccupation.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblFatherOccupation.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblFatherOccupation.setBackground(Color.WHITE);
		lblFatherOccupation.setBounds(582, 405+150, 239, 48);
		add(lblFatherOccupation);
		
		JLabel lblMotherName = new JLabel("Mother Name  ");
		lblMotherName.setOpaque(true);
		lblMotherName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMotherName.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblMotherName.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblMotherName.setBackground(Color.WHITE);
		lblMotherName.setBounds(20, 452+150, 291, 48);
		add(lblMotherName);
		
		JLabel lblMotherOccupation = new JLabel("Mother Occupation  ");
		lblMotherOccupation.setOpaque(true);
		lblMotherOccupation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMotherOccupation.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblMotherOccupation.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblMotherOccupation.setBackground(Color.WHITE);
		lblMotherOccupation.setBounds(582, 452+150, 239, 48);
		add(lblMotherOccupation);
		
		JLabel rollnumberlabel = new JLabel("  "+s.getRollNumber());
		rollnumberlabel.setOpaque(true);
		rollnumberlabel.setHorizontalAlignment(SwingConstants.LEFT);
		rollnumberlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		rollnumberlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		rollnumberlabel.setBackground(Color.WHITE);
		rollnumberlabel.setBounds(582, 66+150, 523, 48);
		add(rollnumberlabel);
		
		JLabel studentnamelabel = new JLabel("  "+s.getFullName());
		studentnamelabel.setOpaque(true);
		studentnamelabel.setHorizontalAlignment(SwingConstants.LEFT);
		studentnamelabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		studentnamelabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		studentnamelabel.setBackground(Color.WHITE);
		studentnamelabel.setBounds(582, 113+150, 523, 48);
		add(studentnamelabel);
		
		JLabel addresslabel = new JLabel("  "+s.getAddress());
		addresslabel.setOpaque(true);
		addresslabel.setHorizontalAlignment(SwingConstants.LEFT);
		addresslabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		addresslabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		addresslabel.setBackground(Color.WHITE);
		addresslabel.setBounds(582, 160+150, 523, 48);
		add(addresslabel);
		
		JLabel emailidlabel = new JLabel("  "+s.getEmailId());
		emailidlabel.setOpaque(true);
		emailidlabel.setHorizontalAlignment(SwingConstants.LEFT);
		emailidlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		emailidlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		emailidlabel.setBackground(Color.WHITE);
		emailidlabel.setBounds(582, 207+150, 523, 48);
		add(emailidlabel);
		
		JLabel dateofbirthlabel = new JLabel("  "+s.getBirthDate());
		dateofbirthlabel.setOpaque(true);
		dateofbirthlabel.setHorizontalAlignment(SwingConstants.LEFT);
		dateofbirthlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		dateofbirthlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		dateofbirthlabel.setBackground(Color.WHITE);
		dateofbirthlabel.setBounds(582, 254+150, 523, 48);
		add(dateofbirthlabel);
		
		JLabel contactnumberlabel = new JLabel("  "+s.getContactNumber());
		contactnumberlabel.setOpaque(true);
		contactnumberlabel.setHorizontalAlignment(SwingConstants.LEFT);
		contactnumberlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		contactnumberlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		contactnumberlabel.setBackground(Color.WHITE);
		contactnumberlabel.setBounds(582, 300+150, 523, 48);
		add(contactnumberlabel);
		
		JLabel courcenamelabel = new JLabel("  "+new CourceData().getcourcename(s.getCourceCode()));
		courcenamelabel.setOpaque(true);
		courcenamelabel.setHorizontalAlignment(SwingConstants.LEFT);
		courcenamelabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		courcenamelabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		courcenamelabel.setBackground(Color.WHITE);
		courcenamelabel.setBounds(309, 359+150, 274, 48);
		add(courcenamelabel);
		
		JLabel fathernamelabel = new JLabel("  "+s.getFatherName());
		fathernamelabel.setOpaque(true);
		fathernamelabel.setHorizontalAlignment(SwingConstants.LEFT);
		fathernamelabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		fathernamelabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		fathernamelabel.setBackground(Color.WHITE);
		fathernamelabel.setBounds(309, 405+150, 274, 48);
		add(fathernamelabel);
		
		JLabel fatheroccupationlabel = new JLabel("  "+s.getFatherOccupation());
		fatheroccupationlabel.setOpaque(true);
		fatheroccupationlabel.setHorizontalAlignment(SwingConstants.LEFT);
		fatheroccupationlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		fatheroccupationlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		fatheroccupationlabel.setBackground(Color.WHITE);
		fatheroccupationlabel.setBounds(820, 405+150, 285, 48);
		add(fatheroccupationlabel);
		
		JLabel mothernamelabel = new JLabel("  "+s.getMotherName());
		mothernamelabel.setOpaque(true);
		mothernamelabel.setHorizontalAlignment(SwingConstants.LEFT);
		mothernamelabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		mothernamelabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		mothernamelabel.setBackground(Color.WHITE);
		mothernamelabel.setBounds(309, 452+150, 274, 48);
		add(mothernamelabel);
		
		JLabel motheroccupationlabel = new JLabel("  "+s.getMotherOccupation());
		motheroccupationlabel.setOpaque(true);
		motheroccupationlabel.setHorizontalAlignment(SwingConstants.LEFT);
		motheroccupationlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		motheroccupationlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		motheroccupationlabel.setBackground(Color.WHITE);
		motheroccupationlabel.setBounds(820, 452+150, 285, 48);
		add(motheroccupationlabel);
		
		JLabel profilepiclabel = new JLabel();
		profilepiclabel.setBounds(20, 66+150, 250, 270);
		add(profilepiclabel);
		profilepiclabel.setIcon(new ImageIcon(s.getProfilePic(250, 270)));
		profilepiclabel.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		profilepiclabel.setOpaque(true);
		profilepiclabel.setBackground(new Color(240, 248, 255));
		profilepiclabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		profilepiclabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblsemoryear = new JLabel("Semester/Year  ");
		lblsemoryear.setOpaque(true);
		lblsemoryear.setHorizontalAlignment(SwingConstants.RIGHT);
		lblsemoryear.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblsemoryear.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblsemoryear.setBackground(Color.WHITE);
		lblsemoryear.setBounds(582, 359 + 150, 239, 48);
		add(lblsemoryear);

		JLabel semoryearlabel = new JLabel(
				"  " + new CourceData().getsemoryear(s.getCourceCode()) + "-" + s.getSemorYear() + " " + " (" + s.getCourceCode() + ")");
		semoryearlabel.setOpaque(true);
		semoryearlabel.setHorizontalAlignment(SwingConstants.LEFT);
		semoryearlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		semoryearlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		semoryearlabel.setBackground(Color.WHITE);
		semoryearlabel.setBounds(820, 359 + 150, 285, 48);
		add(semoryearlabel);
		
	
	}
	public ViewStudentPanel(Student s,AdminMain am,JComponent lastpanel)
	{
		this(s);
		this.am=am;
		this.lastpanel=lastpanel;
		JLabel lblAdmissionDate = new JLabel("Admission Date  ");
		lblAdmissionDate.setOpaque(true);
		lblAdmissionDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdmissionDate.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblAdmissionDate.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblAdmissionDate.setBackground(Color.WHITE);
		lblAdmissionDate.setBounds(20, 649, 291, 48);
		add(lblAdmissionDate);
		
		JLabel admissiondatelabel = new JLabel("  "+s.getAdmissionDate());
		admissiondatelabel.setOpaque(true);
		admissiondatelabel.setHorizontalAlignment(SwingConstants.LEFT);
		admissiondatelabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		admissiondatelabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		admissiondatelabel.setBackground(Color.WHITE);
		admissiondatelabel.setBounds(309, 649, 274, 48);
		add(admissiondatelabel);
		
		JLabel lblPassword = new JLabel("Password  ");
		lblPassword.setOpaque(true);
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblPassword.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblPassword.setBackground(Color.WHITE);
		lblPassword.setBounds(582, 649, 239, 48);
		add(lblPassword);
		
		JLabel passwordlabel = new JLabel("  "+s.getPassword());
		passwordlabel.setOpaque(true);
		passwordlabel.setHorizontalAlignment(SwingConstants.LEFT);
		passwordlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		passwordlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		passwordlabel.setBackground(Color.WHITE);
		passwordlabel.setBounds(820, 649, 285, 48);
		add(passwordlabel);
	
		marksheetbutton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				am.viewstudentpanel.setVisible(false);
				am.marksheetpanel=new MarkSheetPanel(am,s);
				am.marksheetpanel.setVisible(true);
				am.marksheetpanelscroll=new JScrollPane(am.marksheetpanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				am.marksheetpanelscroll.getVerticalScrollBar().setUnitIncrement(16);
				am.marksheetpanelscroll.setBounds(am.panelx, am.panely, 1116, 705);
				am.contentPane.add(am.marksheetpanelscroll);
				am.marksheetpanelscroll.setVisible(true);
			}
	
		}
		
		);
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				am.viewstudentpanel.setVisible(false);
				if(lastpanel.getName().equals("Student Panel"))
				{
					if(am.studentpanel.viewbutton.getText().equals("Photo View"))
					{
						am.studentpanel.createtablemodel();
					}
					else
					{
						am.studentpanel.createphotopanel();
					}
					am.studentpanel.setVisible(true);
				}
				else if(lastpanel.getName().equals("Attadance Report Panel Scroll"))
				{
					am.attandancereportpanel.createtablemodel();
					am.attandancereportpanelscroll.setVisible(true);
				}
				else if(lastpanel.getName().equals("Marksheet Report Panel Scroll"))
				{
					am.marksheetreportpanel.createtablemodel();
					am.marksheetreportpanelscroll.setVisible(true);
				}
				else if(lastpanel.getName().equals("Search Panel"))
				{
					am.searchpanel.createtablemodel();
					am.searchpanel.setVisible(true);
				}
				else if(lastpanel.getName().equals("Users Panel"))
				{
					am.userspanel.createtablemodel();
					am.userspanel.setVisible(true);
				}
				else if(lastpanel.getName().equals("Marksheet Report Panel Scroll"))
				{
					am.marksheetreportpanel.createtablemodel();
					am.marksheetreportpanelscroll.setVisible(true);
				}
				else
				{
					lastpanel.setVisible(true);
				}
				
				
			}
		});
		editdetailsbutton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
			
					AddStudentDialog as=new AddStudentDialog(am,s);
					as.setLocationRelativeTo(null);
					as.setVisible(true);
					
				
			}
	
		}
		);


		
	}
	public ViewStudentPanel(Student s,FacultyMain fm,JComponent lastpanel)
	{
		this(s);
		this.fm=fm;
		this.lastpanel=lastpanel;
	
		editdetailsbutton.setVisible(false);
		marksheetbutton.setLocation(editdetailsbutton.getX(), editdetailsbutton.getY());
		marksheetbutton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fm.viewstudentpanel.setVisible(false);
				fm.marksheetpanel=new MarkSheetPanel(fm,s);
				fm.marksheetpanel.setVisible(true);
				fm.marksheetpanelscroll=new JScrollPane(fm.marksheetpanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				fm.marksheetpanelscroll.getVerticalScrollBar().setUnitIncrement(16);
				fm.marksheetpanelscroll.setBounds(fm.panelx, fm.panely, 1116, 705);
				fm.contentPane.add(fm.marksheetpanelscroll);
				fm.marksheetpanelscroll.setVisible(true);
			}
	
		}
		
		);

		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fm.viewstudentpanel.setVisible(false);
				if(lastpanel.getName().equals("Student Panel"))
				{
					if(fm.studentpanel.viewbutton.getText().equals("Photo View"))
					{
						fm.studentpanel.createtablemodel();
					}
					else
					{
						fm.studentpanel.createphotopanel();
					}
					fm.studentpanel.setVisible(true);
				}
				else if(lastpanel.getName().equals("Attadance Report Panel Scroll"))
				{
					fm.attandancereportpanel.createtablemodel();
					fm.attandancereportpanelscroll.setVisible(true);
				}
				else if(lastpanel.getName().equals("Search Panel"))
				{
					fm.searchpanel.createtablemodel();
					fm.searchpanel.setVisible(true);
				}
				else if(lastpanel.getName().equals("Marksheet Report Panel Scroll"))
				{
					fm.marksheetreportpanel.createtablemodel();
					fm.marksheetreportpanelscroll.setVisible(true);
				}
				else
				{
					lastpanel.setVisible(true);
				}
				
			}
		});
		
	}
	public ViewStudentPanel(Student s,StudentMain sm,JComponent lastpanel)
	{
		this(s);
		this.sm=sm;
		this.lastpanel=lastpanel;
		
		editdetailsbutton.setVisible(false);
		marksheetbutton.setVisible(false);

		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sm.viewstudentpanel.setVisible(false);
				if(lastpanel.getName().equals("Student Panel"))
				{
					if(sm.studentpanel.viewbutton.getText().equals("Photo View"))
					{
						sm.studentpanel.createtablemodel();
					}
					else
					{
						sm.studentpanel.createphotopanel();
					}
					sm.studentpanel.setVisible(true);
				}
				else if(lastpanel.getName().equals("Attadance Report Panel Scroll"))
				{
					sm.attandancereportpanel.createtablemodel();
					sm.attandancereportpanelscroll.setVisible(true);
				}
				else if(lastpanel.getName().equals("Search Panel"))
				{
					sm.searchpanel.createtablemodel();
					sm.searchpanel.setVisible(true);
				}
				else
				{
					lastpanel.setVisible(true);
				}
				
			}
		});
	}
	public ViewStudentPanel(Student s,StudentMain sm)
	{
		this(s);
		this.sm=sm;
		
		editdetailsbutton.setText("Change Password");
		marksheetbutton.setVisible(false);
		backbutton.setVisible(false);
		editdetailsbutton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
			
					ChangePasswordDialog cp=new ChangePasswordDialog(s);
					cp.setLocationRelativeTo(null);
					cp.setVisible(true);
				
			}
	
		}
		);


	}
}

