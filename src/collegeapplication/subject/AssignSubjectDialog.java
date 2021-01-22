package collegeapplication.subject;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import collegeapplication.admin.AdminMain;
import collegeapplication.cource.CourceData;
import collegeapplication.faculty.Faculty;
import collegeapplication.faculty.FacultyData;
import collegeapplication.faculty.ViewFacultyPanel;

/*
 * Title : AssignSubjectDialog.java
 * Created by : Ajaysinh Rathod
 * Purpose : For assigning subject to faculty
 * Mail : ajaysinhrathod1290@gmail.com
 */
@SuppressWarnings("serial")
public class AssignSubjectDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	Faculty f=null;
	static AssignSubjectDialog dialog;
	private JComboBox<String> courcenamecombo,semoryearcombo,subjectnamecombo,positioncombo;
	private JButton assignsubjectbutton;
	private AdminMain am;
	JLabel Errorlabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new AssignSubjectDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param f 
	 * @wbp.parser.constructor
	 */
	private AssignSubjectDialog(Faculty f) {
		
		super(dialog,"",Dialog.ModalityType.APPLICATION_MODAL);
		this.setLocation(450, 100);
		
		getContentPane().setBackground(Color.WHITE);
		this.f=f;
		setSize(521, 580);
		getContentPane().setLayout(null);
		
		JLabel headerlabel = new JLabel("Assign Subject");
		headerlabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerlabel.setBackground(new Color(32, 178, 170));
		headerlabel.setOpaque(true);
		headerlabel.setForeground(new Color(255, 255, 255));
		headerlabel.setFont(new Font("Arial", Font.BOLD, 23));
		headerlabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		headerlabel.setBounds(0, 0, 505, 39);
		getContentPane().add(headerlabel);
		
		JLabel lblImage = new JLabel();
		lblImage.setBounds(34, 50, 98, 111);

		lblImage.setIcon(new ImageIcon(f.getProfilePic(lblImage.getWidth(), lblImage.getHeight())));
		lblImage.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		lblImage.setOpaque(true);
		lblImage.setBackground(new Color(240, 255, 255));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(lblImage);
		
		JLabel lblFacultyName = new JLabel("Faculty Name  :  "+f.getFacultyName());
		lblFacultyName.setHorizontalAlignment(SwingConstants.LEFT);
		lblFacultyName.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblFacultyName.setBounds(156, 77, 293, 29);
		getContentPane().add(lblFacultyName);
		
		JLabel lblDegree = new JLabel("Qualification :  "+f.getQualification());
		lblDegree.setHorizontalAlignment(SwingConstants.LEFT);
		lblDegree.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblDegree.setBounds(156, 108, 293, 26);
		getContentPane().add(lblDegree);
		
		JLabel lblExperience = new JLabel("Experience  :  "+f.getExperience());
		lblExperience.setHorizontalAlignment(SwingConstants.LEFT);
		lblExperience.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblExperience.setBounds(156, 138, 293, 26);
		getContentPane().add(lblExperience);
		
		JLabel lblFacultyId = new JLabel("Faculty ID  : "+f.getFacultyId());
		lblFacultyId.setHorizontalAlignment(SwingConstants.LEFT);
		lblFacultyId.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblFacultyId.setBounds(156, 50, 323, 22);
		getContentPane().add(lblFacultyId);
		
		courcenamecombo = new JComboBox<String>(new CourceData().getCourceName());
		courcenamecombo.setFocusable(false);
		courcenamecombo.addActionListener(this);
		courcenamecombo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		courcenamecombo.setBackground(new Color(255, 255, 255));
		courcenamecombo.setBounds(156, 199, 338, 39);
		getContentPane().add(courcenamecombo);
		
		JLabel lblCourceName = new JLabel("Cource Name  :");
		lblCourceName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCourceName.setFont(new Font("Candara", Font.PLAIN, 18));
		lblCourceName.setBounds(10, 199, 138, 39);
		getContentPane().add(lblCourceName);
		
		JLabel lblSelectSemyear = new JLabel("Semster/Year  :");
		lblSelectSemyear.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSelectSemyear.setFont(new Font("Candara", Font.PLAIN, 18));
		lblSelectSemyear.setBounds(10, 265, 138, 37);
		getContentPane().add(lblSelectSemyear);
		
		semoryearcombo = new JComboBox<String>();
		semoryearcombo.setFocusable(false);
		semoryearcombo.addActionListener(this);
		semoryearcombo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		semoryearcombo.setBackground(Color.WHITE);
		semoryearcombo.setBounds(156, 265, 338, 39);
		getContentPane().add(semoryearcombo);
		
		JLabel lblSubject = new JLabel("Subject  :");
		lblSubject.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubject.setFont(new Font("Candara", Font.PLAIN, 18));
		lblSubject.setBounds(10, 332, 138, 37);
		getContentPane().add(lblSubject);
		
		subjectnamecombo = new JComboBox<String>();
		subjectnamecombo.addActionListener(this);
		subjectnamecombo.setFocusable(false);
		subjectnamecombo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		subjectnamecombo.setBackground(Color.WHITE);
		subjectnamecombo.setBounds(156, 332, 338, 39);
		getContentPane().add(subjectnamecombo);
		
		JLabel lblPosition = new JLabel("Position  :");
		lblPosition.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPosition.setFont(new Font("Candara", Font.PLAIN, 18));
		lblPosition.setBounds(10, 397, 138, 37);
		getContentPane().add(lblPosition);
		
		positioncombo = new JComboBox<String>();
		positioncombo.setFocusable(false);
		positioncombo.addActionListener(this);
		positioncombo.setModel(new DefaultComboBoxModel<String>(new String[] {"---Select Position---", "Full Professor", "Associate Professor", "Assistant Professor", "Lecturer", "lab Assitant"}));
		positioncombo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		positioncombo.setBackground(Color.WHITE);
		positioncombo.setBounds(156, 397, 338, 39);
		getContentPane().add(positioncombo);
		
		assignsubjectbutton = new JButton("Assign Subject");
		assignsubjectbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		assignsubjectbutton.setFocusable(false);
		assignsubjectbutton.addActionListener(this);
		assignsubjectbutton.setBackground(new Color(32, 178, 170));
		assignsubjectbutton.setForeground(new Color(255, 255, 255));
		assignsubjectbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		assignsubjectbutton.setBounds(356, 485, 139, 37);
		assignsubjectbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(assignsubjectbutton);
		
		JLabel label = new JLabel("");
		label.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		label.setBounds(0, 462, 505, 8);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		label_1.setBounds(0, 172, 505, 8);
		getContentPane().add(label_1);
		
		Errorlabel = new JLabel("This is required question  !");
		Errorlabel.setVisible(false);
		Errorlabel.setForeground(Color.RED);
		Errorlabel.setFont(new Font("Arial", Font.PLAIN, 14));
		Errorlabel.setBounds(156, 236, 215, 22);
		getContentPane().add(Errorlabel);
	
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		if(!f.getCourceCode().equals("Not Assigned"))
		{
			this.setDataInComboBox();
		}
		
	}

	public AssignSubjectDialog(Faculty f, AdminMain am) 
	{
		// TODO Auto-generated constructor stub
		this(f);
		this.am=am;
	
	}
	public void setDataInComboBox()
	{
		courcenamecombo.setSelectedItem(new CourceData().getcourcename(f.getCourceCode()));
		 semoryearcombo.setModel(new DefaultComboBoxModel<String>(new CourceData().getSemorYear(courcenamecombo.getSelectedItem()+"")));
			String[] totalsub=new SubjectData().getSubjectinCource(f.getCourceCode(),f.getSemorYear());
			subjectnamecombo.setModel(new DefaultComboBoxModel<String>(totalsub));
			semoryearcombo.setSelectedIndex(f.getSemorYear());
			subjectnamecombo.setSelectedItem(f.getSubject());
			positioncombo.setSelectedItem(f.getPosition());
	}
	

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		Errorlabel.setVisible(false);
		if(e.getSource()==courcenamecombo)
		{
			courcenamecombo.setFocusable(false);
			
			subjectnamecombo.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
			if(courcenamecombo.getSelectedIndex()==0)
			{
				semoryearcombo.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
				
			}
			else
			{
			 String cource=(String) courcenamecombo.getSelectedItem();

			 semoryearcombo.setModel(new DefaultComboBoxModel<String>(new CourceData().getSemorYear(cource)));
			}
		 
		}
		if(e.getSource()==semoryearcombo && semoryearcombo.getSelectedIndex()>0)
		{
			 String cource=(String) courcenamecombo.getSelectedItem();
			
			String[] totalsub=new SubjectData().getSubjectinCource(new CourceData().getCourcecode(cource), semoryearcombo.getSelectedIndex());
			if(totalsub!=null)
			{
				subjectnamecombo.setModel(new DefaultComboBoxModel<String>(totalsub));
			}
			else
			{
				subjectnamecombo.setModel(new DefaultComboBoxModel<String>(new String[] {"No Subject"}));
				
			}
		}
		if(e.getSource()==assignsubjectbutton)
		{
			if(courcenamecombo.getSelectedIndex()==0)
			{
				showerror(courcenamecombo);
			}
			else if(semoryearcombo.getSelectedIndex()==0)
			{
				showerror(semoryearcombo);
			}
			else if(subjectnamecombo.getSelectedIndex()==0)
			{
				showerror(subjectnamecombo);
			}
			else if(positioncombo.getSelectedIndex()==0)
			{
				showerror(positioncombo);
			}
			else
			{
				Faculty fnew=new Faculty();
			
				fnew.setCourceCode(new CourceData().getCourcecode(courcenamecombo.getSelectedItem()+""));
				fnew.setPosition(positioncombo.getSelectedItem()+"");
				fnew.setSemorYear(semoryearcombo.getSelectedIndex());
				fnew.setSubject(subjectnamecombo.getSelectedItem()+"");
				fnew.setFacultyId(f.getFacultyId());
				fnew.setFacultyName(f.getFacultyName());
				int result=new FacultyData().assignSubject(f,fnew);
				if(result>0)
				{
					if(am!=null)
					{
						if(am.assignsubjectpanel!=null&&am.assignsubjectpanel.isVisible())
						{
							am.assignsubjectpanel.createtablemodel();
						}
						
						else if(am.viewfacultypanel!=null&&am.viewfacultypanel.isVisible())
						{
							System.out.println("updateing ");
							am.viewfacultypanel.setVisible(false);
							am.viewfacultypanel=new ViewFacultyPanel(new FacultyData().getFacultyInfobyId(f.getFacultyId()),am,am.viewfacultypanel.lastpanel);
							am.viewfacultypanel.setVisible(true);
							am.viewfacultypanel.setLocation(am.panelx, am.panely);
							am.getContentPane().add(am.viewfacultypanel);
						}
					}
					
					this.dispose();
				}
			}
		}

		
	}
	public void showerror(JComponent tf)
	{
		Errorlabel.setVisible(true);
		Errorlabel.setBounds(tf.getX(), tf.getY()+tf.getHeight()-5, 400,26);
	}
	
}
