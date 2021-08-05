package collegeapplication.student;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import collegeapplication.admin.AdminMain;
import collegeapplication.common.HintTextField;
import collegeapplication.cource.CourceData;
import collegeapplication.cource.RollNumberData;
import collegeapplication.subject.SubjectData;

/*
 * Title : AddStudentDialog.java
 * Created by : Ajaysinh Rathod
 * Purpose : For adding new student or edit student details
 * Mail : ajaysinhrathod1290@gmail.com
 */
@SuppressWarnings("serial")
public class AddStudentDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField rollnumberfield;
	private JTextField firstnamefield;
	private JTextField lastnamefield;
	private JTextField emailidfield;
	private JTextField contactnumberfield;
	private JTextField statefield;
	private JTextField cityfield;
	private JTextField fathernamefield;
	private JTextField fatheroccupationfield;
	private JTextField mothernamefield;
	private JTextField motheroccupationfield;
	private JLabel lblPhoto;
	private JLabel filename;
	private JComboBox<String> courcenamecombo, semoryearcombo, optionalsubjectcombo, gendercombo;
	private JSpinner birthdatespinner;
	private JButton choosefilebutton, addstudentbutton;
	private File file ;
	private String imagepath = null;
	private JLabel filesize;
	private AdminMain am;
	private JLabel profilepiclabel, filesizenote;
	private JLabel Errorlabel;
	private static AddStudentDialog dialog;
	private StudentPanel sp;
	private JLabel headerlabel;
	private Student student;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		// If translucent windows aren't supported, exit.

		JFrame.setDefaultLookAndFeelDecorated(true);

		try {
			dialog = new AddStudentDialog();

			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 */
	public AddStudentDialog() {

		super(new JFrame(), true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		setSize(850, 700);
		getContentPane().setLayout(null);
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		headerlabel = new JLabel("Add New Student");
		headerlabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		headerlabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerlabel.setBounds(0, 0, 834, 40);
		getContentPane().add(headerlabel);

		headerlabel.setBackground(new Color(32, 178, 170));
		headerlabel.setOpaque(true);
		headerlabel.setForeground(new Color(255, 255, 255));
		headerlabel.setFont(new Font("Arial", Font.BOLD, 23));
		headerlabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));

		courcenamecombo = new JComboBox<String>(new CourceData().getCourceName());
		courcenamecombo.setForeground(Color.DARK_GRAY);
		courcenamecombo.setToolTipText("Cource");
		courcenamecombo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		courcenamecombo.addActionListener(this);
		courcenamecombo.setBackground(new Color(255, 255, 255));
		courcenamecombo.setBounds(10, 51, 400, 40);
		courcenamecombo.setFocusable(false);
		getContentPane().add(courcenamecombo);

		semoryearcombo = new JComboBox<String>();
		semoryearcombo.setPrototypeDisplayValue("--select prototype--");
		semoryearcombo.setToolTipText("Semester/Year");
		semoryearcombo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		semoryearcombo.setBackground(Color.WHITE);
		semoryearcombo.setFocusable(false);
		semoryearcombo.setBounds(424, 51, 400, 40);
		semoryearcombo.addActionListener(this);
		semoryearcombo.setModel(new DefaultComboBoxModel<String>(new String[] { "" }));
		getContentPane().add(semoryearcombo);

		rollnumberfield = new HintTextField("");
		rollnumberfield.setToolTipText("Roll Number");
		rollnumberfield.setFocusable(false);
		rollnumberfield.setFont(new Font("Segoe UI Historic", Font.PLAIN, 16));
		rollnumberfield.setBounds(134, 116, 276, 40);
		getContentPane().add(rollnumberfield);
		rollnumberfield.setColumns(10);

		JLabel lblRollNo = new JLabel("Roll Number");
		lblRollNo.setForeground(Color.DARK_GRAY);
		lblRollNo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 19));
		lblRollNo.setFocusable(true);
		lblRollNo.setBounds(20, 116, 106, 40);
		getContentPane().add(lblRollNo);

		optionalsubjectcombo = new JComboBox<String>();
		optionalsubjectcombo.setToolTipText("Optional Subject");
		optionalsubjectcombo.setFocusable(false);
		optionalsubjectcombo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					optionalsubjectcombo.setFocusable(false);
					firstnamefield.setFocusable(true);
				}
			}
		});
		optionalsubjectcombo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 16));
		optionalsubjectcombo.setBackground(Color.WHITE);
		optionalsubjectcombo.setBounds(424, 116, 400, 40);
		optionalsubjectcombo.addActionListener(this);
		getContentPane().add(optionalsubjectcombo);

		firstnamefield = new HintTextField("First Name");
		firstnamefield.setToolTipText("First Name");
		firstnamefield.setForeground(Color.DARK_GRAY);
		firstnamefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		firstnamefield.addActionListener(this);
		firstnamefield.setColumns(10);
		firstnamefield.setBounds(10, 177, 400, 40);
		getContentPane().add(firstnamefield);

		lastnamefield = new HintTextField("Last Name");
		lastnamefield.setToolTipText("Last Name");
		lastnamefield.setForeground(Color.DARK_GRAY);
		lastnamefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		lastnamefield.setColumns(10);
		lastnamefield.setBounds(424, 177, 400, 40);
		getContentPane().add(lastnamefield);

		emailidfield = new HintTextField(" Email ID");
		emailidfield.setToolTipText("Email ID");
		emailidfield.setForeground(Color.DARK_GRAY);
		emailidfield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		emailidfield.setColumns(10);
		emailidfield.setBounds(10, 240, 400, 40);
		getContentPane().add(emailidfield);

		contactnumberfield = new HintTextField(" Contact Number");
		contactnumberfield.setToolTipText("Contact Number");
		contactnumberfield.setForeground(Color.DARK_GRAY);
		contactnumberfield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		contactnumberfield.setColumns(10);
		contactnumberfield.setBounds(424, 240, 400, 40);
		getContentPane().add(contactnumberfield);

		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setForeground(Color.DARK_GRAY);
		lblDateOfBirth.setFont(new Font("Segoe UI Historic", Font.PLAIN, 19));
		lblDateOfBirth.setBounds(10, 302, 114, 40);
		getContentPane().add(lblDateOfBirth);

		birthdatespinner = new JSpinner();
		birthdatespinner.setToolTipText("Date Of Birth");
		birthdatespinner.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					birthdatespinner.setFocusable(false);
				}
			}
		});
		birthdatespinner.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		SimpleDateFormat model = new SimpleDateFormat("dd-MM-yyyy");
		birthdatespinner.setModel(new SpinnerDateModel());
		birthdatespinner.setEditor(new JSpinner.DateEditor(birthdatespinner, model.toPattern()));
		birthdatespinner.setBounds(134, 302, 276, 42);
		getContentPane().add(birthdatespinner);

		gendercombo = new JComboBox<String>();
		gendercombo.setToolTipText("Gender");
		gendercombo.setModel(new DefaultComboBoxModel<String>(new String[] { "---Select Gender---", "Male", "Female" }));
		gendercombo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 16));
		gendercombo.setBackground(Color.WHITE);
		gendercombo.addActionListener(this);
		gendercombo.setBounds(424, 303, 400, 40);
		gendercombo.setFocusable(false);
		getContentPane().add(gendercombo);

		statefield = new HintTextField("State");
		statefield.setToolTipText("State");
		statefield.setForeground(Color.DARK_GRAY);
		statefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		statefield.setColumns(10);
		statefield.setBounds(10, 363, 400, 40);
		getContentPane().add(statefield);

		cityfield = new HintTextField(" City");
		cityfield.setToolTipText("City");
		cityfield.setForeground(Color.DARK_GRAY);
		cityfield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		cityfield.setColumns(10);
		cityfield.setBounds(424, 363, 400, 40);
		getContentPane().add(cityfield);

		fathernamefield = new HintTextField(" Father Name");
		fathernamefield.setToolTipText("Father Name");
		fathernamefield.setForeground(Color.DARK_GRAY);
		fathernamefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		fathernamefield.setColumns(10);
		fathernamefield.setBounds(10, 424, 400, 40);
		getContentPane().add(fathernamefield);

		fatheroccupationfield = new HintTextField(" Father Occupation");
		fatheroccupationfield.setToolTipText("Father Occupation");
		fatheroccupationfield.setForeground(Color.DARK_GRAY);
		fatheroccupationfield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		fatheroccupationfield.setColumns(10);
		fatheroccupationfield.setBounds(424, 424, 400, 40);
		getContentPane().add(fatheroccupationfield);

		mothernamefield = new HintTextField(" Mother Name");
		mothernamefield.setToolTipText("Mother Name");
		mothernamefield.setForeground(Color.DARK_GRAY);
		mothernamefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		mothernamefield.setColumns(10);
		mothernamefield.setBounds(10, 485, 400, 40);
		getContentPane().add(mothernamefield);

		motheroccupationfield = new HintTextField(" Mother Occupation");
		motheroccupationfield.setToolTipText("Mother Occupation");
		motheroccupationfield.setForeground(Color.DARK_GRAY);
		motheroccupationfield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		motheroccupationfield.setColumns(10);
		motheroccupationfield.setBounds(424, 485, 400, 40);
		getContentPane().add(motheroccupationfield);

		filesizenote = new JLabel("Image size  <  1024 KB");
		filesizenote.setFont(new Font("Tahoma", Font.PLAIN, 14));
		filesizenote.setBounds(134, 624, 545, 32);
		getContentPane().add(filesizenote);

		filesize = new JLabel("");
		filesize.setToolTipText("Image Size");
		filesize.setFont(new Font("Tahoma", Font.PLAIN, 14));
		filesize.setBounds(200, 544, 566, 32);
		getContentPane().add(filesize);

		profilepiclabel = new JLabel();
		profilepiclabel.setToolTipText("Profile Picture");
		profilepiclabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		profilepiclabel.setFont(new Font("Segoe UI Historic", Font.PLAIN, 16));
		profilepiclabel.setBounds(10, 536, 100, 120);
		getContentPane().add(profilepiclabel);
		profilepiclabel.setIcon(new ImageIcon("./assets/profilepicicon.jpg"));

		choosefilebutton = new JButton("Choose File");
		choosefilebutton.addActionListener(this);
		choosefilebutton.setFocusable(false);
		choosefilebutton.setBackground(new Color(245, 245, 245));
		choosefilebutton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		choosefilebutton.setBounds(134, 582, 114, 32);
		choosefilebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(choosefilebutton);

		lblPhoto = new JLabel("Photo");
		lblPhoto.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		lblPhoto.setBounds(136, 548, 73, 21);
		getContentPane().add(lblPhoto);

		filename = new JLabel("No file choosen");
		filename.setToolTipText("File Name");
		filename.setFont(new Font("Tahoma", Font.PLAIN, 14));
		filename.setBounds(258, 582, 566, 32);
		getContentPane().add(filename);

		addstudentbutton = new JButton("Add Student");
		addstudentbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		addstudentbutton.setForeground(new Color(255, 255, 255));
		addstudentbutton.setBackground(new Color(32, 178, 170));
		addstudentbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		addstudentbutton.addActionListener(this);
		addstudentbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addstudentbutton.setBounds(685, 613, 139, 37);
		addstudentbutton.setFocusable(false);
		getContentPane().add(addstudentbutton);

		Errorlabel = new JLabel("This is required question !");
		Errorlabel.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 0, 0)));
		Errorlabel.setHorizontalAlignment(SwingConstants.LEFT);
		Errorlabel.setForeground(new Color(255, 69, 0));
		Errorlabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		Errorlabel.setVisible(false);
		Errorlabel.setBounds(10, 90, 400, 26);
		getContentPane().add(Errorlabel);

	}

	public AddStudentDialog(AdminMain am, Student s) {
		this();
		this.am = am;
		this.student = s;
		courcenamecombo.setSelectedItem(s.getCourceName());
		semoryearcombo.setSelectedIndex(s.getSemorYear());
		rollnumberfield.setText(s.getRollNumber() + "");
		rollnumberfield.setEditable(false);
		optionalsubjectcombo.setSelectedItem(s.getOptionalSubject());
		firstnamefield.setText(s.getFirstName());
		lastnamefield.setText(s.getLastName());
		emailidfield.setText(s.getEmailId());
		contactnumberfield.setText(s.getContactNumber());
		birthdatespinner.setValue(s.getBirthDateInDateFormat());

		gendercombo.setSelectedItem(s.getGender()+"");
		statefield.setText(s.getState());
		cityfield.setText(s.getCity());
		fathernamefield.setText(s.getFatherName());
		fatheroccupationfield.setText(s.getFatherOccupation());
		mothernamefield.setText(s.getMotherName());
		motheroccupationfield.setText(s.getMotherOccupation());
		profilepiclabel.setIcon(new ImageIcon(s.getProfilePic(100, 120)));
		headerlabel.setText("Edit Student Details");
		addstudentbutton.setText("Update Student");
		courcenamecombo.setEnabled(false);
		semoryearcombo.setEnabled(false);
		rollnumberfield.setEditable(false);
//		optionalsubjectcombo.setEnabled(false);
		 courcenamecombo.setRenderer(new DefaultListCellRenderer() {
		        @Override
		        public void paint(Graphics g) {
		            setForeground(Color.BLACK);
		            setBackground(Color.WHITE);
		            super.paint(g);
		        }
		    });
		 semoryearcombo.setRenderer(new DefaultListCellRenderer() {
		        @Override
		        public void paint(Graphics g) {
		            setForeground(Color.BLACK);
		            setBackground(Color.WHITE);
		            super.paint(g);
		        }
		    });

	}

	public AddStudentDialog(JTable table, StudentPanel studentpanel) {
		this();
		this.sp = studentpanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Errorlabel.setVisible(false);
		Errorlabel.setText("This is required question  !");
		if (e.getSource() == choosefilebutton)
		{

			FileDialog fd = new FileDialog(this, "Choose a Profile pic", FileDialog.LOAD);
			fd.setDirectory(".\\Students Profile pic");
			fd.setFile("*.jpeg;*.jpg;*.png;*.tiff;*.tif;*.gif;");
			fd.setLocationRelativeTo(null);
			fd.setVisible(true);
			String strfilename = fd.getFile();
			imagepath = fd.getDirectory() + strfilename;
		

			if(fd.getFile()!=null) 
			{
				file = new File(imagepath);
				long bytes = file.length();
				if (bytes < 1048576) {

					try 
					{
						filesize.setText(bytes / 1024 + " KB");
						filesizenote.setForeground(new Color(46, 139, 27));
						filesizenote.setText("Image size < 1024 KB");
						Image image = ImageIO.read(file).getScaledInstance(100, 120, Image.SCALE_SMOOTH);
						profilepiclabel.setIcon(new ImageIcon(image));
						filename.setText(file.getName());	

					} 
					catch (IOException ex) {
						file = null;
						// TODO Auto-generated catch block
						filename.setText("No file Choosen");
						filesize.setText("");
						filesizenote.setForeground(Color.red);
						filesizenote.setText("Image Not supported");
						ex.printStackTrace();
					}
				} 
				else {
					file = null;
					filename.setText("No File Choosen");
					filesize.setText("");
					filesizenote.setForeground(Color.red);
					filesizenote.setText("Image size is greater than 1 MB");
				}

			}
		}

		if (e.getSource() == courcenamecombo)
		{
			courcenamecombo.setFocusable(false);
			rollnumberfield.setText("");
			rollnumberfield.setEditable(true);
			optionalsubjectcombo.setModel(new DefaultComboBoxModel<String>(new String[] { "" }));
			rollnumberfield.setText("");
			if (courcenamecombo.getSelectedIndex() == 0) 
			{
				semoryearcombo.setModel(new DefaultComboBoxModel<String>(new String[] { "" }));

			}
			else 
			{
				String cource = (String) courcenamecombo.getSelectedItem();
				semoryearcombo.setModel(new DefaultComboBoxModel<String>(new CourceData().getSemorYear(cource)));
			}

		}
		if (e.getSource() == semoryearcombo && semoryearcombo.getSelectedIndex() > 0) 
		{
			String courcecode = new CourceData().getCourcecode(courcenamecombo.getSelectedItem() + "");
			int sem = semoryearcombo.getSelectedIndex();
			long rollnumber = 0;
			if (student != null && courcecode.equals(student.getCourceCode()) && sem == student.getSemorYear()) 
			{
				rollnumber = student.getRollNumber();

			} 
			else 
			{
				rollnumber = new RollNumberData().getRollNumber(courcecode, sem);
			}
			if (rollnumber == 0) 
			{
				rollnumberfield.setText("");
				rollnumberfield.setEditable(true);

			} 
			else
			{
				rollnumberfield.setText(rollnumber + "");
				rollnumberfield.setEditable(false);

			}
			String[] totalopsub = new SubjectData().getOptionalSubject(courcecode, sem);
			if (totalopsub != null) 
			{
				optionalsubjectcombo.setModel(new DefaultComboBoxModel<String>(totalopsub));
			} 
			else 
			{
				optionalsubjectcombo.setModel(new DefaultComboBoxModel<String>(new String[] { "No Optional Subject" }));

			}
		}

		if (e.getSource() == addstudentbutton)
		{

			if (courcenamecombo.getSelectedIndex() == 0) 
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(courcenamecombo.getX(), courcenamecombo.getY() + courcenamecombo.getHeight(), 400,
						26);
			} 
			else if (semoryearcombo.getSelectedIndex() == 0)
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(semoryearcombo.getX(), semoryearcombo.getY() + semoryearcombo.getHeight(), 400,
						26);
			}
			else if (rollnumberfield.getText().isEmpty())
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(rollnumberfield.getX(), rollnumberfield.getY() + rollnumberfield.getHeight(), 400,
						26);
			}

			else if (optionalsubjectcombo.getSelectedIndex() == 0
					&& !optionalsubjectcombo.getSelectedItem().toString().equals("No Optional Subject")) 
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(optionalsubjectcombo.getX(),
						optionalsubjectcombo.getY() + optionalsubjectcombo.getHeight(), 400, 26);
			} 
			else if (firstnamefield.getText().isEmpty())
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(firstnamefield.getX(), firstnamefield.getY() + firstnamefield.getHeight(), 400,
						26);
			} 
			else if (lastnamefield.getText().isEmpty())
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(lastnamefield.getX(), lastnamefield.getY() + lastnamefield.getHeight(), 400, 26);
			} 
			else if (emailidfield.getText().isEmpty())
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(emailidfield.getX(), emailidfield.getY() + emailidfield.getHeight(), 400, 26);
			} 
			else if (contactnumberfield.getText().isEmpty())
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(contactnumberfield.getX(),contactnumberfield.getY() + contactnumberfield.getHeight(), 400, 26);
			} 
			else if (gendercombo.getSelectedIndex() == 0)
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(gendercombo.getX(), gendercombo.getY() + gendercombo.getHeight(), 400, 26);
			}
			else if (statefield.getText().isEmpty())
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(statefield.getX(), statefield.getY() + statefield.getHeight(), 400, 26);
			} 
			else if (cityfield.getText().isEmpty()) 
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(cityfield.getX(), cityfield.getY() + cityfield.getHeight(), 400, 26);
			} 
			else if (fathernamefield.getText().isEmpty())
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(fathernamefield.getX(), fathernamefield.getY() + fathernamefield.getHeight(), 400,26);
			}
			else if (fatheroccupationfield.getText().isEmpty()) 
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(fatheroccupationfield.getX(),fatheroccupationfield.getY() + fatheroccupationfield.getHeight(), 400, 26);
			} 
			else if (mothernamefield.getText().isEmpty()) 
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(mothernamefield.getX() + 120, mothernamefield.getY() + mothernamefield.getHeight(),400, 26);
			} 
			else if (motheroccupationfield.getText().isEmpty()|| motheroccupationfield.getText().equals(" Mother Occupation")) 
			{
				Errorlabel.setVisible(true);
				Errorlabel.setBounds(motheroccupationfield.getX(),motheroccupationfield.getY() + motheroccupationfield.getHeight(), 400, 26);
			}
			else {
				try 
				{
					Student s = new Student();

					s.setCourceCode(new CourceData().getCourcecode(courcenamecombo.getSelectedItem() + ""));
					s.setSemorYear(semoryearcombo.getSelectedIndex());
					s.setRollNumber(Long.parseLong(rollnumberfield.getText()));
					int rollnumberexist = new RollNumberData().isExist(s.getCourceCode(), s.getSemorYear(), s.getRollNumber());
					if (rollnumberexist > 0)
					{
						if (!(student != null && student.getRollNumber()==s.getRollNumber())) 
						{
							throw new RollNumberAvailableException();
						}

					}
					s.setOptionalSubject(optionalsubjectcombo.getSelectedItem().toString());
					s.setFirstName(firstnamefield.getText());
					s.setLastName(lastnamefield.getText());
					s.setEmailId(emailidfield.getText());
					s.setContactNumber(contactnumberfield.getText());

					s.setGender(gendercombo.getSelectedItem() + "");
					Date date = (Date) birthdatespinner.getValue();
					s.setBirthDate(new SimpleDateFormat("dd-MM-yyyy").format(date));
					s.setState(statefield.getText());
					s.setCity(cityfield.getText());
					s.setFatherName(fathernamefield.getText());
					s.setMotherName(mothernamefield.getText());
					s.setFatherOccupation(fatheroccupationfield.getText());
					s.setMotherOccupation(motheroccupationfield.getText());
					s.generateAdmissionDate();
					s.generateUserId();
					if(student!=null)
					{
						s.setPassword(student.getPassword());
						s.setAdmissionDate(student.getAdmissionDate());
						s.setLastLogin(student.getLastLogin());
					}
					if (file!=null) {
						s.setProfilePic(ImageIO.read(file));
					} 
					else if(student!=null){
						s.setProfilePic(student.getProfilePic());
					} else {
						 file = new File("./assets/profilepicicon.jpg");
							s.setProfilePic(ImageIO.read(file));
					}
					int result = 0;
					if (sp != null) 
					{
						result = new StudentData().addStudent(s);
					} 
					else if (am != null && student != null) 
					{
						result = new StudentData().updateStudentData(student, s);
					}
					if (result > 0) {

						
						if (sp != null) 
						{
							if (sp.photoviewscrollpane != null && sp.photoviewscrollpane.isVisible()) 
							{
								sp.createtablemodel();
								sp.createphotopanel();
							}
							else 
							{
								sp.createtablemodel();
							}

						} 
						else if (am != null && student != null) {

							am.viewstudentpanel.setVisible(false);
							am.viewstudentpanel = new ViewStudentPanel(s, am, am.viewstudentpanel.lastpanel);
							am.viewstudentpanel.setVisible(true);
							am.viewstudentpanel.setLocation(am.panelx, am.panely);
							am.getContentPane().add(am.viewstudentpanel);

						}
						this.dispose();
					}

				} catch (NumberFormatException exp) {
					Errorlabel.setVisible(true);
					Errorlabel.setText("Characters are not allowed!");
					Errorlabel.setBounds(rollnumberfield.getX(), rollnumberfield.getY() + rollnumberfield.getHeight(),
							400, 26);
				} catch (RollNumberAvailableException exp) {
					Errorlabel.setVisible(true);
					Errorlabel.setText("RollNumber already Exist...!");
					Errorlabel.setBounds(rollnumberfield.getX(), rollnumberfield.getY() + rollnumberfield.getHeight(),
							400, 26);
					exp.printStackTrace();

				} catch (Exception e1) {

					// TODO Auto-generated catch block

					e1.printStackTrace();
				}
			}
		}

	}

}

@SuppressWarnings("serial")
class RollNumberAvailableException extends Exception {
	public RollNumberAvailableException() {
		super("Roll number already available");
	}
}