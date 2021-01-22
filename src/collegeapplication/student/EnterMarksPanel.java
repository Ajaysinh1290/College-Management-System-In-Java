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
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import collegeapplication.cource.CourceData;
import collegeapplication.faculty.FacultyMain;
import collegeapplication.subject.SubjectData;

/*
 * Title : EnterMarksPanel.java
 * Created by : Ajaysinh Rathod
 * Purpose : For entering marks of student
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class EnterMarksPanel extends JPanel implements ActionListener
{
	
	private JComboBox<String> courcenamecombo,semoryearcombo,subjectnamecombo;
	private JLabel Errorlabel;
	private JScrollPane scrollPane;
	private JTable table;
	private int totalstudent=0;
	private JButton submitbutton;
	private JButton theorymarksbutton;
	private JButton practicalmarksbutton;
	private JLabel TableErrorlabel;
	private Timer timer;
	private JLabel label3;
	private JLabel label2;
	private JLabel label1;
	private JPanel selectcourcepanel;
	private JLabel nodatafoundlabel;
	
	/**
	 * Create the panel.
	 */
	 
		@Override
		public Dimension getPreferredSize()
		{
			
		    return new Dimension( 1116,this.getHeight());
		}
		
		public EnterMarksPanel(FacultyMain fm)
		{
			this();
			courcenamecombo.setSelectedItem(fm.f.getCourceName());
			semoryearcombo.setModel(new DefaultComboBoxModel<String>(new CourceData().getSemorYear(courcenamecombo.getSelectedItem()+"")));
			String[] totalsub=new SubjectData().getSubjectinCource(fm.f.getCourceCode(), fm.f.getSemorYear());	
			subjectnamecombo.setModel(new DefaultComboBoxModel<String>(totalsub));
			semoryearcombo.setSelectedIndex(fm.f.getSemorYear());
			subjectnamecombo.setSelectedItem(fm.f.getSubject());
			selectcourcepanel.setVisible(false);
			this.createtablemodel();
			scrollPane.setLocation(scrollPane.getX(),selectcourcepanel.getY());
			submitbutton.setLocation(submitbutton.getX(), scrollPane.getY()+scrollPane.getHeight()+40);
			
		}
		public EnterMarksPanel() {
		
		timer=new Timer(10000,this);
		timer.start();
		setBorder(null);
		setBackground(new Color(255, 255, 255));
		this.setSize(1116, 544);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 1076, 117);
		panel.setBackground(new Color(32, 178, 170));
		add(panel);
		panel.setLayout(null);
		
		
		 theorymarksbutton = new JButton("Theory Marks");
		theorymarksbutton.setBorder(new LineBorder(new Color(255, 255, 255)));
		theorymarksbutton.setForeground(new Color(0, 139, 139));
		theorymarksbutton.setBackground(new Color(255, 255, 255));
		theorymarksbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		theorymarksbutton.setName("Active");
		theorymarksbutton.addActionListener(this);
		theorymarksbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		theorymarksbutton.setFocusable(false);
		theorymarksbutton.setBounds(727, 69, 148, 33);
		panel.add(theorymarksbutton);
		
		 practicalmarksbutton = new JButton("Practical Marks");
		practicalmarksbutton.setBorder(new LineBorder(new Color(255, 255, 255)));
		practicalmarksbutton.setForeground(new Color(255, 255, 255));
		practicalmarksbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		practicalmarksbutton.setBackground(new Color(32, 178, 170));
		practicalmarksbutton.setBounds(893, 69, 148, 33);
		practicalmarksbutton.setFocusable(false);
		practicalmarksbutton.addActionListener(this);
		practicalmarksbutton.setName("Deactive");
		practicalmarksbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(practicalmarksbutton);
		
		JLabel lblEnterStudentMarks = new JLabel("Enter Student Marks");
		lblEnterStudentMarks.setForeground(new Color(255, 255, 255));
		lblEnterStudentMarks.setBackground(new Color(255, 255, 255));
		lblEnterStudentMarks.setFont(new Font("Segoe UI", Font.BOLD, 27));
		lblEnterStudentMarks.setBounds(10, 65, 309, 33);
		panel.add(lblEnterStudentMarks);
		  
		  selectcourcepanel = new JPanel();
		  selectcourcepanel.setBounds(10, 159, 1073, 222);
		  add(selectcourcepanel);
		  selectcourcepanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		  selectcourcepanel.setBackground(Color.WHITE);
		  selectcourcepanel.setLayout(null);
		  
		  courcenamecombo = new JComboBox<String>(new CourceData().getCourceName());
		  courcenamecombo.setFocusable(false);
		  courcenamecombo.setBackground(Color.WHITE);
		  courcenamecombo.addActionListener(this);
		  courcenamecombo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		  courcenamecombo.setBounds(236, 0, 825, 40);
		  selectcourcepanel.add(courcenamecombo);
		  
		   semoryearcombo = new JComboBox<String>();
		   semoryearcombo.setFocusable(false);
		   semoryearcombo.setBackground(Color.WHITE);
		   semoryearcombo.setBounds(236, 73, 825, 40);
		   semoryearcombo.addActionListener(this);
		   semoryearcombo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		   selectcourcepanel.add(semoryearcombo);
		   
		    subjectnamecombo = new JComboBox<String>();
		    subjectnamecombo.setFocusable(false);
		    subjectnamecombo.setBackground(Color.WHITE);
		    subjectnamecombo.setBounds(236, 143, 825, 40);
		    subjectnamecombo.addActionListener(this);
		    subjectnamecombo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		    selectcourcepanel.add(subjectnamecombo);
		    
		    label1 = new JLabel("Select Cource   :");
		    label1.setHorizontalAlignment(SwingConstants.RIGHT);
		    label1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		    label1.setBounds(10, -1, 200, 40);
		    selectcourcepanel.add(label1);
		    
		    label2 = new JLabel("Select Sem/Year  :");
		    label2.setHorizontalAlignment(SwingConstants.RIGHT);
		    label2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		    label2.setBounds(10, 72, 200, 40);
		    selectcourcepanel.add(label2);
		    
		    label3 = new JLabel("Select Subject   :");
		    label3.setHorizontalAlignment(SwingConstants.RIGHT);
		    label3.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		    label3.setBounds(10, 142, 200, 40);
		    selectcourcepanel.add(label3);
		    
		    Errorlabel = new JLabel("This is required question  !");
		    Errorlabel.setVisible(false);
		    Errorlabel.setForeground(Color.RED);
		    Errorlabel.setFont(new Font("Arial", Font.PLAIN, 14));
		    Errorlabel.setBounds(233, 45, 225, 17);
		    selectcourcepanel.add(Errorlabel);
		
	
	
		    scrollPane = new JScrollPane();
		    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
			scrollPane.setBounds(10, 415, 1062, 40+(totalstudent*40));
			for(Component c:scrollPane.getComponents())
			{
				c.setBackground(Color.white);
			}
			add(scrollPane);

			table = new JTable();
			table.setBorder(new LineBorder(new Color(192, 192, 192), 2));
			table.setBackground(Color.white);
			table.setRowHeight(40);
			table.getTableHeader().setBackground(new Color(32, 178, 170));
			table.getTableHeader().setForeground(Color.white);
			table.getTableHeader().setFont(new Font("Arial",Font.BOLD,20));
			table.setFont(new Font("Segoe UI",Font.PLAIN,20));
			table.getTableHeader().setPreferredSize(new Dimension(50,40));
			table.setDragEnabled(false);
			table.setFocusable(false);
			table.setSelectionModel(new ForcedListSelectionModel());
			table.setSelectionBackground(new Color(240, 255, 255));
			table.setSelectionForeground(Color.black);
			table.setGridColor(Color.LIGHT_GRAY);
			
		
			table.getTableHeader().setReorderingAllowed(false);		
			
//			table.setEnabled(false);
			scrollPane.setViewportView(table);
			
			 submitbutton = new JButton("Submit Marks");
			submitbutton.setForeground(Color.WHITE);
			submitbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
			submitbutton.setFocusable(false);
			submitbutton.setBackground(new Color(32, 178, 170));
			submitbutton.setBounds(923, 490, 149, 37);
			submitbutton.setVisible(false);
			submitbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			submitbutton.setBorder(new EmptyBorder(0,0,0,0));
			submitbutton.addActionListener(this);
			add(submitbutton);
			
			TableErrorlabel = new JLabel("Marks is greater than max marks  !");
			TableErrorlabel.setHorizontalAlignment(SwingConstants.RIGHT);
			TableErrorlabel.setForeground(new Color(255, 0, 0));
			TableErrorlabel.setFont(new Font("candara", Font.PLAIN, 18));
			TableErrorlabel.setBounds(20, 458, 1052, 21);
			TableErrorlabel.setVisible(false);
			add(TableErrorlabel);
			scrollPane.setVisible(false);
			
			nodatafoundlabel=new JLabel("");
			nodatafoundlabel.setHorizontalAlignment(SwingConstants.CENTER);
			try 
			{
				
				Image image= ImageIO.read(new File("./assets/notfound2.png"));
				nodatafoundlabel.setIcon(new ImageIcon(image.getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
				
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
			nodatafoundlabel.setText("No Students Found...!");
			nodatafoundlabel.setVerticalTextPosition(JLabel.BOTTOM);
			nodatafoundlabel.setBorder(null);
			nodatafoundlabel.setBackground(new Color(245, 245, 245));
			nodatafoundlabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
			nodatafoundlabel.setHorizontalTextPosition(JLabel.CENTER);
			nodatafoundlabel.setIconTextGap(20);
			nodatafoundlabel.setVisible(false);
			nodatafoundlabel.setBounds(300, 380, 480, 321);
			add(nodatafoundlabel);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
	
		Errorlabel.setVisible(false);
		TableErrorlabel.setVisible(false);
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
				subjectnamecombo.setModel(new DefaultComboBoxModel<String>(new String[] {"No Subject Found"}));
				
			}
		}
		else if(e.getSource()==semoryearcombo)
		{
			subjectnamecombo.setModel(new DefaultComboBoxModel<String>(new String[] {""}));	
		}
		if(e.getSource()==subjectnamecombo)
		{
			if(courcenamecombo.getSelectedIndex()==0)
			{
				showerror(courcenamecombo);
			}
			else if(semoryearcombo.getSelectedIndex()==0)
			{
				showerror(semoryearcombo);
			}
			else if(subjectnamecombo.getSelectedItem().equals("No Subject Found"))
			{
				Component tf=subjectnamecombo;
				Errorlabel.setVisible(true);
				Errorlabel.setText("No Subject Found !");
				Errorlabel.setBounds(tf.getX(), tf.getY()+tf.getHeight()-5, 400,26);
			}
			else if(subjectnamecombo.getSelectedIndex()==0)
			{
				showerror(subjectnamecombo);
			}
		
			else
			{
				
				createtablemodel();
				
			}
		}
		if(courcenamecombo.getSelectedIndex()==0 || semoryearcombo.getSelectedIndex()==0 || subjectnamecombo.getSelectedIndex()==0)
		{
			scrollPane.setVisible(false);
			submitbutton.setVisible(false);
			totalstudent=0;
			this.setSize(1116, 544+(totalstudent*40));
		}
		if(e.getSource()==submitbutton)
		{
			if (table.isEditing())
			{
				table.getCellEditor().stopCellEditing();
			}
			if(theorymarksbutton.getName().equals("Active"))
			{
			this.addtheorymarks();
			}
			else
			{
				this.addpracticalmarks();
			}
			
		}
		if(e.getSource()==theorymarksbutton)
		{
			ActiveButton(theorymarksbutton);
			DeactiveButton(practicalmarksbutton);
			
			if(courcenamecombo.getSelectedIndex()!=0 && semoryearcombo.getSelectedIndex()!=0 && subjectnamecombo.getSelectedIndex()!=0)
			{
				createtablemodel();
			}
		}
		if(e.getSource()==practicalmarksbutton)
		{
			DeactiveButton(theorymarksbutton);
			ActiveButton(practicalmarksbutton);
			if(courcenamecombo.getSelectedIndex()!=0 && semoryearcombo.getSelectedIndex()!=0 && subjectnamecombo.getSelectedIndex()!=0)
			{
				createtablemodel();
			}
		}
		if(totalstudent==0)
		{
			submitbutton.setVisible(false);
		}

		
	}
	public void showerror(JComponent tf)
	{
		Errorlabel.setVisible(true);
		Errorlabel.setText("This is required question !");
		Errorlabel.setBounds(tf.getX(), tf.getY()+tf.getHeight()-5, 400,26);
	}
	public void createtablemodel()
	{
	
		nodatafoundlabel.setVisible(false);	
		if(theorymarksbutton.getName().equals("Active"))
		{
			table.setModel(createTheoryMarksModel());
		}
		else
		{
			table.setModel(createPracticalMarksModel());
		}
			totalstudent=table.getRowCount();
			
			scrollPane.setBounds(10, scrollPane.getY(), 1062, 40+(totalstudent*40));
			this.setSize(1116, 544+(totalstudent*40));
			JTextField textField = new JTextField();
			textField.setFont(new Font("Segoe UI",Font.PLAIN,20));
			textField.setBorder(new LineBorder(Color.BLACK));
			textField.setEnabled(true);
			textField.setEditable(true);
			
			DefaultCellEditor dce = new DefaultCellEditor( textField );
			table.getColumnModel().getColumn(4).setCellEditor(dce);
		table.getColumnModel().getColumn(0).setMaxWidth(200);
		table.getColumnModel().getColumn(1).setMaxWidth(250);
		table.getColumnModel().getColumn(2).setMaxWidth(300);
		table.getColumnModel().getColumn(3).setMaxWidth(250);
		table.getColumnModel().getColumn(4).setMaxWidth(250);
		table.setSelectionBackground(new Color(240, 255, 255));
		table.setSelectionForeground(Color.black);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		submitbutton.setLocation(submitbutton.getX(),scrollPane.getY()+scrollPane.getHeight()+40);
		scrollPane.setVisible(true);
		if(totalstudent==0)
		{
			noDataFound();
		}
	}
	public void noDataFound()
	{
		scrollPane.setVisible(false);
		submitbutton.setVisible(false);
		nodatafoundlabel.setVisible(true);
		nodatafoundlabel.setLocation(nodatafoundlabel.getX(), scrollPane.getY()-100);
		
	}
	public DefaultTableModel createTheoryMarksModel()
	{
		
		String courcecode=new CourceData().getCourcecode(courcenamecombo.getSelectedItem()+"");
		int sem=semoryearcombo.getSelectedIndex();
		String subjectname=subjectnamecombo.getSelectedItem()+"";
		String[] columnname= {"Roll Number","Student Name","Subject Name","Max Theory Marks","Theory Marks"};
		DefaultTableModel model = new DefaultTableModel(columnname,0) {

            boolean[] canEdit = new boolean[]{
                    false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
		};
		ArrayList<Marks> listdata=new StudentData().getStudentTheoryMarksDetails(courcecode, sem, subjectname);

		for(int i=0; i<listdata.size(); i++)
		{
			Object[] data = {listdata.get(i).getRollNumber(),listdata.get(i).getStudentName(),listdata.get(i).getSubjectName(),listdata.get(i).getMaxTheoryMarks(),listdata.get(i).getTheoryMarks()};
			model.addRow(data);
		}
		submitbutton.setVisible(true);
		table.setEnabled(true);
		
		return model;
	}
	public DefaultTableModel createPracticalMarksModel()
	{
		String courcecode=new CourceData().getCourcecode(courcenamecombo.getSelectedItem()+"");
		int sem=semoryearcombo.getSelectedIndex();
		String subjectname=subjectnamecombo.getSelectedItem()+"";
		String[] columnname= {"Roll Number","Student Name","Subject Name","Max Practical Marks","Practical Marks"};
		DefaultTableModel model = new DefaultTableModel(columnname,0) {

            boolean[] canEdit = new boolean[]{
                    false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
		};
		ArrayList<Marks> listdata=new StudentData().getStudentPracticalMarksDetails(courcecode, sem, subjectname);

		for(int i=0; i<listdata.size(); i++)
		{
			Object[] data = {listdata.get(i).getRollNumber(),listdata.get(i).getStudentName(),listdata.get(i).getSubjectName(),listdata.get(i).getMaxPracticalMarks(),listdata.get(i).getPracticalMarks()};
			model.addRow(data);
		}
		table.setEnabled(true);
		submitbutton.setVisible(true);
			
		
		return model;
	}
	public void addtheorymarks()
	{
		int result=0;
		int i=0;
		for(i=0; i<table.getRowCount(); i++)
		{
			Marks m=new Marks();
			m.setCourceCode(new CourceData().getCourcecode(courcenamecombo.getSelectedItem()+""));
			m.setSemorYear(semoryearcombo.getSelectedIndex());
			m.setSubjectName(subjectnamecombo.getSelectedItem()+"");
			m.setSubjectCode(new SubjectData().getSubjectCode(m.getCourceCode(), m.getSemorYear(), m.getSubjectName()));
			m.setRollNumber(Long.parseLong(""+table.getValueAt(i,0)));
			m.setMaxTheoryMarks(Integer.parseInt(table.getValueAt(i, 3)+""));
			try
			{
			m.setTheoryMarks(Integer.parseInt(table.getValueAt(i,4)+""));
				if(m.getTheoryMarks()<0)
				{	
					TableError("Marks must be positive  !");
					break;
				}
				else if(m.getTheoryMarks()>m.getMaxTheoryMarks())
				{
					TableError("Marks must be less than or equal to Maximum Marks !");
					break;
				}
				else
				{
					
					result=new StudentData().addStudentTheoryMarks(m);
				}
		
			}
			catch(NumberFormatException e)
			{
				TableError("Must be a Number !");
				break;
			}
			
		}
		if(result>0 && i==table.getRowCount())
		{
			TableErrorlabel.setForeground(new Color(34,139,34));
			TableError("Theory Marks Succesfully submitted");
			timer.restart();
		}
	
		if(i!=table.getRowCount())
		{
			TableErrorlabel.setForeground(Color.red);
			timer.restart();
		
			table.setSelectionBackground(Color.red);
			table.setSelectionForeground(Color.white);
			table.addRowSelectionInterval(i, i);
	
		}
		else
		{
			this.createtablemodel();
		}
	}
	public void addpracticalmarks()
	{
		int result=0;
		int i=0;
		for(i=0; i<table.getRowCount(); i++)
		{
			Marks m=new Marks();
			m.setCourceCode(new CourceData().getCourcecode(courcenamecombo.getSelectedItem()+""));
			m.setSemorYear(semoryearcombo.getSelectedIndex());
			m.setSubjectName(table.getValueAt(i,2)+"");
			m.setSubjectCode(new SubjectData().getSubjectCode(m.getCourceCode(), m.getSemorYear(), m.getSubjectName()));
			m.setRollNumber(Long.parseLong(""+table.getValueAt(i,0)));
			m.setMaxPracticalMarks(Integer.parseInt(table.getValueAt(i, 3)+""));
			try
			{
				m.setPracticalMarks(Integer.parseInt(table.getValueAt(i,4)+""));
				if(m.getPracticalMarks()<0)
				{	
					TableError("Marks must be positive  !");
					break;
				}
				else if(m.getPracticalMarks()>m.getMaxPracticalMarks())
				{
					TableError("Marks must be less than or equal to Maximum Marks !");
					break;
				}
				else
				{
					
					result=new StudentData().addStudentPracticalMarks(m);
				}
		
			}
			catch(NumberFormatException e)
			{
				TableError("Must be a Number !");
				break;
			}
			
		}
		if(result>0 && i==table.getRowCount())
		{
			TableErrorlabel.setForeground(new Color(34,139,34));
			TableError("Practical Marks Succesfully submitted");
			timer.restart();
		}
	
		if(i!=table.getRowCount())
		{
			TableErrorlabel.setForeground(Color.red);
			timer.restart();
		
			table.setSelectionBackground(Color.red);
			table.setSelectionForeground(Color.white);
			table.addRowSelectionInterval(i, i);
	
		}
		else
		{
			this.createtablemodel();
		}
	}
	
	public void ActiveButton(JButton button)
	{
		button.setBorder(new LineBorder(new Color(255, 255, 255)));
		button.setForeground(new Color(0, 139, 139));
		button.setBackground(new Color(255, 255, 255));
		button.setName("Active");		
	}
	public void DeactiveButton(JButton button)
	{

		button.setBorder(new LineBorder(new Color(255, 255, 255)));
		button.setForeground(new Color(255, 255, 255));
		button.setBackground(new Color(32, 178, 170));
		button.setName("Deactive");
	}
	public void TableError(String error)
	{
		TableErrorlabel.setVisible(true);
		TableErrorlabel.setText(error);
		TableErrorlabel.setLocation(10, scrollPane.getY()+scrollPane.getHeight()+10);
	}
}
@SuppressWarnings("serial")
class ForcedListSelectionModel extends DefaultListSelectionModel {

    public ForcedListSelectionModel () {
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public void clearSelection() {
    }

    @Override
    public void removeSelectionInterval(int index0, int index1) {
    }

}