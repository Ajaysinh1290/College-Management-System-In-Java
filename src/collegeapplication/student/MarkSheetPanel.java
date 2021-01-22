package collegeapplication.student;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import collegeapplication.admin.AdminMain;
import collegeapplication.common.PrintMarksheetDialog;
import collegeapplication.cource.CourceData;
import collegeapplication.faculty.FacultyMain;

/*
 * Title : MarkSheetPanel.java
 * Created by : Ajaysinh Rathod
 * Purpose : For displaying student marksheet
 * Mail : ajaysinhrathod1290@gmail.com
 */
@SuppressWarnings("serial")
public class MarkSheetPanel extends JPanel {

	private JTable table;
	private int totalsubject=0;
	private Student s;
	private JLabel rollnumberlabel;
	private JLabel studentnamelabel;
	private JLabel profilepiclabel;
	private JLabel courcenamelabel;
	private JScrollPane tableviewpanel;
	private int rowsize=50;
	public JButton downloadbutton;
	private JButton backbutton;
	public String defaultDownloadPath="C:\\Users\\Sohansinh Rathod\\Downloads\\";
	private JLabel notdeclaredlabel;
	private JPanel studentdetailspanel;
	private JPanel headerpanel;
	/**
	 * Create the panel.
	 * 
	 */
	
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(1096,460+(totalsubject*rowsize));
		
	}
	public MarkSheetPanel(AdminMain am,Student s)
	{
		this(s);
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				am.marksheetpanelscroll.setVisible(false);
				am.viewstudentpanel.setVisible(true);
				
			}
		});
		downloadbutton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// TODO Auto-generated method stub
				PrintMarksheetDialog ppd=new PrintMarksheetDialog(am,s);
				ppd.setLocationRelativeTo(null);
				ppd.setVisible(true);
				
			}
	
		}
		);
		
	}
	public MarkSheetPanel(FacultyMain fm,Student s)
	{
		this(s);
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fm.marksheetpanelscroll.setVisible(false);
				fm.viewstudentpanel.setVisible(true);
				
			}
		});
		downloadbutton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// TODO Auto-generated method stub
				PrintMarksheetDialog ppd=new PrintMarksheetDialog(fm,s);
				ppd.setLocationRelativeTo(null);
				ppd.setVisible(true);
				
			}
	
		}
		);
		
	}
	public MarkSheetPanel(StudentMain sm,Student s)
	{
		this(s);
	
		backbutton.setVisible(false);
			downloadbutton.addActionListener(new ActionListener()
			{
	
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					// TODO Auto-generated method stub
					PrintMarksheetDialog ppd=new PrintMarksheetDialog(sm,s);
					ppd.setLocationRelativeTo(null);
					ppd.setVisible(true);
					
				}
		
			}
			);
	}
	public MarkSheetPanel(StudentMain sm,Student s,JComponent lastpanel)
	{
		this(s);
		backbutton.setVisible(true);
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sm.marksheetpanelscroll.setVisible(false);
				lastpanel.setVisible(true);
				
			}
		});
		downloadbutton.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// TODO Auto-generated method stub
				PrintMarksheetDialog ppd=new PrintMarksheetDialog(sm,s);
				ppd.setLocationRelativeTo(null);
				ppd.setVisible(true);
				
			}
			
		}
				);
	}
	public void notDeclared()
	{
		downloadbutton.setVisible(false);
		studentdetailspanel.setVisible(false);
		tableviewpanel.setVisible(false);
		notdeclaredlabel=new JLabel("");
		notdeclaredlabel.setHorizontalAlignment(SwingConstants.CENTER);
		try 
		{
			
			Image image= ImageIO.read(new File("./assets/notfound2.png"));
			notdeclaredlabel.setIcon(new ImageIcon(image.getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		notdeclaredlabel.setText("Oops result not declared yet...!");
		notdeclaredlabel.setVerticalTextPosition(JLabel.BOTTOM);
		notdeclaredlabel.setBorder(null);
		notdeclaredlabel.setBackground(new Color(245, 245, 245));
		notdeclaredlabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		notdeclaredlabel.setHorizontalTextPosition(JLabel.CENTER);
		notdeclaredlabel.setIconTextGap(20);
		notdeclaredlabel.setBounds(300, 220, 480, 321);
		add(notdeclaredlabel);
	}
	/**
	 * @wbp.parser.constructor
	 */
	private MarkSheetPanel(Student s) {
		this.s=s;
		
		setBackground(new Color(255, 255, 255));
		this.setSize(1116, 705);
		setLayout(null);
		
		 headerpanel = new JPanel();
		headerpanel.setLocation(10, 0);
		headerpanel.setBackground(new Color(32, 178, 170));
		headerpanel.setSize(1076, 100);
		add(headerpanel);
		headerpanel.setLayout(null);
		
		JLabel lblMarksheet = new JLabel("Marksheet");
		lblMarksheet.setBorder(new EmptyBorder(0, 0, 0, 0));
		lblMarksheet.setBounds(10, 26, 1066, 40);
		headerpanel.add(lblMarksheet);
		lblMarksheet.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarksheet.setForeground(Color.WHITE);
		lblMarksheet.setFont(new Font("Segoe UI", Font.BOLD, 30));
		
		backbutton = new JButton("Back");
		backbutton.setContentAreaFilled(false);
		backbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
	
		backbutton.setIcon(new ImageIcon(".\\assets\\back.png"));
		backbutton.setFocusable(false);
		backbutton.setForeground(Color.WHITE);
		backbutton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		backbutton.setBackground(new Color(32, 178, 170));
		backbutton.setBounds(10, 47, 88, 36);
		headerpanel.add(backbutton);
		
		downloadbutton = new JButton("Download");
		downloadbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		downloadbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		downloadbutton.setIcon(new ImageIcon(".\\assets\\downloadbutton.png"));
		downloadbutton.setFocusable(false);
		downloadbutton.setFont(new Font("Segoe UI", Font.BOLD, 17));
		downloadbutton.setForeground(new Color(32, 178, 170));
		downloadbutton.setBackground(Color.WHITE);
		downloadbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		downloadbutton.setBounds(891, 55, 164, 35);
		headerpanel.add(downloadbutton);
		
		studentdetailspanel = new JPanel();
		studentdetailspanel.setBackground(Color.WHITE);
		studentdetailspanel.setBounds(10, 111, 1076, 223);
		add(studentdetailspanel);
		studentdetailspanel.setLayout(null);
		
		JLabel studentnamelbl = new JLabel("Student Name  :");
		studentnamelbl.setBounds(462, 11, 151, 46);
		studentdetailspanel.add(studentnamelbl);
		studentnamelbl.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		studentnamelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel genderlabel = new JLabel(s.getGender());
		genderlabel.setBounds(623, 125, 216, 46);
		studentdetailspanel.add(genderlabel);
		genderlabel.setHorizontalAlignment(SwingConstants.LEFT);
		genderlabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel lblRollNumber = new JLabel("Roll Number   :");
		lblRollNumber.setBounds(20, 11, 166, 46);
		studentdetailspanel.add(lblRollNumber);
		lblRollNumber.setForeground(Color.BLACK);
		lblRollNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRollNumber.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		studentnamelabel = new JLabel(s.getFullName());
		studentnamelabel.setBounds(623, 11, 252, 46);
		studentdetailspanel.add(studentnamelabel);
		studentnamelabel.setHorizontalAlignment(SwingConstants.LEFT);
		studentnamelabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		rollnumberlabel = new JLabel(s.getRollNumber()+"");
		rollnumberlabel.setBounds(203, 11, 262, 46);
		studentdetailspanel.add(rollnumberlabel);
		rollnumberlabel.setHorizontalAlignment(SwingConstants.LEFT);
		rollnumberlabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		profilepiclabel = new JLabel("image");
		profilepiclabel.setBounds(885, 8, 181, 208);
		studentdetailspanel.add(profilepiclabel);
		profilepiclabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		profilepiclabel.setIcon(new ImageIcon(s.getProfilePic(profilepiclabel.getWidth()+10, profilepiclabel.getHeight())));
		profilepiclabel.setHorizontalAlignment(SwingConstants.CENTER);
		profilepiclabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel courcelbl = new JLabel("Cource   :");
		courcelbl.setBounds(10, 68, 176, 46);
		studentdetailspanel.add(courcelbl);
		courcelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		courcelbl.setForeground(Color.BLACK);
		courcelbl.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		JLabel lblSemesteryear = new JLabel("Semester/Year  :");
		lblSemesteryear.setBounds(462, 68, 152, 46);
		studentdetailspanel.add(lblSemesteryear);
		lblSemesteryear.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSemesteryear.setForeground(Color.BLACK);
		lblSemesteryear.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		courcenamelabel = new JLabel(s.getCourceName());
		courcenamelabel.setBounds(203, 68, 263, 46);
		studentdetailspanel.add(courcenamelabel);
		courcenamelabel.setHorizontalAlignment(SwingConstants.LEFT);
		courcenamelabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel semoryearlabel = new JLabel(new CourceData().getsemoryear(s.getCourceCode())+"-"+s.getSemorYear()+" ("+s.getCourceCode()+")");
		semoryearlabel.setBounds(623, 68, 252, 46);
		studentdetailspanel.add(semoryearlabel);
		semoryearlabel.setHorizontalAlignment(SwingConstants.LEFT);
		semoryearlabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth  :");
		lblDateOfBirth.setBounds(10, 125, 176, 46);
		studentdetailspanel.add(lblDateOfBirth);
		lblDateOfBirth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateOfBirth.setForeground(Color.BLACK);
		lblDateOfBirth.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		JLabel dateofbirthlabel = new JLabel(s.getBirthDate());
		dateofbirthlabel.setBounds(203, 125, 261, 46);
		studentdetailspanel.add(dateofbirthlabel);
		dateofbirthlabel.setHorizontalAlignment(SwingConstants.LEFT);
		dateofbirthlabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel lblGender = new JLabel("Gender   :");
		lblGender.setBounds(475, 125, 138, 46);
		studentdetailspanel.add(lblGender);
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGender.setForeground(Color.BLACK);
		lblGender.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		
		tableviewpanel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tableviewpanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		tableviewpanel.getVerticalScrollBar().setUnitIncrement(30);
		tableviewpanel.setBounds(10, 372, 1075, rowsize);
		add(tableviewpanel);
		
		table = new JTable();
		tableviewpanel.setViewportView(table);
		table.setToolTipText("Mark Sheet");
		
		
		table.setDefaultEditor(Object.class, null);
		table.setBorder(new LineBorder(new Color(192, 192, 192)));
		table.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		createtablemodel();
		table.setBackground(Color.white);
		table.setRowHeight(rowsize);
		table.getTableHeader().setBackground(new Color(32, 178, 170));
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setFont(new Font("Arial",Font.BOLD,20));
		table.setFont(new Font("Segoe UI",Font.PLAIN,20));
		table.getTableHeader().setPreferredSize(new Dimension(50,rowsize));
		table.setDragEnabled(false);
		table.setFocusable(false);
		table.setSelectionModel(new ForcedListSelectionModel());
//		table.setSelectionBackground(new Color(95, 158, 160));
		table.setSelectionBackground(Color.white);
//		table.setSelectionForeground(new Color(255, 255, 255));
		table.setSelectionForeground(Color.DARK_GRAY);
		table.setGridColor(Color.LIGHT_GRAY);
		table.getTableHeader().setReorderingAllowed(false);		
		DefaultTableCellRenderer cellrenderer=new DefaultTableCellRenderer();
		cellrenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(cellrenderer);
		
		if((totalsubject-1)==0)
		{
			table.setSelectionBackground(Color.white);
			table.setSelectionForeground(Color.DARK_GRAY);
		}
		table.addRowSelectionInterval(totalsubject-1,totalsubject-1);
		table.getColumnModel().getColumn(0).setCellRenderer(new CellRenderer());
		table.getColumnModel().getColumn(1).setCellRenderer(new CellRenderer());
		table.getColumnModel().getColumn(2).setCellRenderer(new CellRenderer());
		table.getColumnModel().getColumn(3).setCellRenderer(new CellRenderer());
		table.getColumnModel().getColumn(4).setCellRenderer(new CellRenderer());
		table.getColumnModel().getColumn(5).setCellRenderer(new CellRenderer());

	}
	
	
	private void createtablemodel() 
	{

		
		
		ArrayList<Marks> list=new StudentData().getMarkssheetOfStudent(s.getCourceCode(), s.getSemorYear(), s.getRollNumber());
		totalsubject=list.size();
		String ColumnName[]= {"Subject Code","Subject Name","Theory Marks","Practical Marks","Total Max. Marks","Total"};
		DefaultTableModel model=new DefaultTableModel(ColumnName,0);
		int totaltheorymarks=0;
		int maxtotaltheorymarks=0;
		int totalpracticalmarks=0;
		int maxtotalpracticalmarks=0;
		for(int i=0; i<list.size(); i++)
		{
//			Object data[]= {list.get(i).sr_no,list.get(i).subjectname,list.get(i).maxtheorymarks,list.get(i).theorymarks,list.get(i).maxpracticalmarks,list.get(i).practicalmarks,list.get(i).theorymarks+list.get(i).practicalmarks};
			maxtotaltheorymarks+=list.get(i).getMaxTheoryMarks();
			totaltheorymarks+=list.get(i).getTheoryMarks();
			maxtotalpracticalmarks+=list.get(i).getMaxPracticalMarks();
			totalpracticalmarks+=list.get(i).getPracticalMarks();
			Object data[]= {" "+list.get(i).getSubjectCode(),list.get(i).getSubjectName(),list.get(i).getTheoryMarks(),list.get(i).getPracticalMarks(),list.get(i).getMaxPracticalMarks()+list.get(i).getMaxTheoryMarks(),list.get(i).getTheoryMarks()+list.get(i).getPracticalMarks()};
			model.addRow(data);
		}
		if(totalsubject>0)
		{
		Object result[]= {"","Total Marks",totaltheorymarks,totalpracticalmarks,maxtotaltheorymarks+maxtotalpracticalmarks,(totaltheorymarks+totalpracticalmarks)};
		model.addRow(result);
		}
		else
		{
			Object result[]= {"-","No Subject Found","-","-","-","-"};
			model.addRow(result);
		}
	
		totalsubject++;
		table.setModel(model);
		
		table.getColumnModel().getColumn(0).setMaxWidth(200);
		table.getColumnModel().getColumn(1).setMaxWidth(250);
		table.getColumnModel().getColumn(2).setMaxWidth(175);
		table.getColumnModel().getColumn(3).setMaxWidth(175);
		table.getColumnModel().getColumn(4).setMaxWidth(175);
		table.getColumnModel().getColumn(5).setMaxWidth(175);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.setFocusable(false);

	
		tableviewpanel.setSize(1075, rowsize+(totalsubject*rowsize));
		table.setEnabled(false);
		this.setSize(1096,460+(totalsubject*rowsize));
		System.out.println("Marksheet :"+new CourceData().isDeclared(s.getCourceCode(), s.getSemorYear()));
		if(!new CourceData().isDeclared(s.getCourceCode(), s.getSemorYear()))
		{
			notDeclared();
		}
	}
	public void disablebutton()
	{
		downloadbutton.setVisible(false);
		backbutton.setVisible(false);
	}
	public void enablebutton()
	{
		downloadbutton.setVisible(true);
		backbutton.setVisible(true);
	}

private class CellRenderer extends DefaultTableCellRenderer {

private static final long serialVersionUID = 1L;

@Override
public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
        int column) 
{
    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    if (row==(totalsubject-1))
    {
    	
        this.setFont(this.getFont().deriveFont(Font.BOLD));
        if(row==0)
        {
        	this.setHorizontalAlignment(JLabel.CENTER);
        }
        
    }
    return this;
}
}
}


