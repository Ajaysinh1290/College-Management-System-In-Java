package collegeapplication.subject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import collegeapplication.admin.AdminMain;
import collegeapplication.cource.CourceData;
import collegeapplication.faculty.FacultyMain;
import collegeapplication.student.StudentMain;
import net.proteanit.sql.DbUtils;

/*
 * Title : SubjectPanel.java
 * Created by : Ajaysinh Rathod
 * Purpose : Displaying all the subject in given cource and sem
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class SubjectPanel extends JPanel implements ActionListener
{

	/**
	 * Create the panel.
	 */
	private JComboBox<String> courcenamecombo;
	private JComboBox<String> semoryearcombo;
	private JButton addsubject;
	private String Courcecode[];
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel selectcourcelabel;
	private JLabel selectsemlabel;
	private JLabel headerlabel;
	private JButton backbutton;
	
	public SubjectPanel(AdminMain am)
	{
		this();
	}
	public SubjectPanel(StudentMain sm)
	{
		this();
		courcenamecombo.setVisible(false);
		semoryearcombo.setVisible(false);
		addsubject.setVisible(false);
		selectsemlabel.setVisible(false);
		selectcourcelabel.setVisible(false);
	
		scrollPane.setVisible(true);
		headerlabel.setBounds(10, 0, 1096, 183);
		headerlabel.setText(" Subjects");
		scrollPane.setBounds(10, headerlabel.getY()+headerlabel.getHeight()+20, 1096, this.getHeight()-headerlabel.getHeight()-20);
		headerlabel.setHorizontalAlignment(JLabel.LEFT);
		this.createtablemodel(sm.s.getCourceCode(),sm.s.getSemorYear() );
		
	}
	public SubjectPanel(FacultyMain fm)
	{
		this();
		courcenamecombo.setVisible(false);
		semoryearcombo.setVisible(false);
		addsubject.setVisible(false);
		selectsemlabel.setVisible(false);
		selectcourcelabel.setVisible(false);
	
		scrollPane.setVisible(true);
		headerlabel.setBounds(10, 0, 1096, 183);
		headerlabel.setText(" Subjects");
		scrollPane.setBounds(10, headerlabel.getY()+headerlabel.getHeight()+20, 1096, this.getHeight()-headerlabel.getHeight()-20);
		headerlabel.setHorizontalAlignment(JLabel.LEFT);
		this.createtablemodel(fm.f.getCourceCode(),fm.f.getSemorYear() );
		
	}
	public SubjectPanel(FacultyMain fm,JComponent lastpanel)
	{
		this(fm);
		headerlabel.setLayout(null);
		backbutton = new JButton("Back");
		backbutton.setContentAreaFilled(false);
		backbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		backbutton.setIcon(new ImageIcon(".\\assets\\back.png"));
		backbutton.setFocusable(false);
		backbutton.setForeground(Color.WHITE);
		backbutton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		backbutton.setBackground(new Color(32, 178, 170));
		backbutton.setBounds(10,141, 88, 36);
		headerlabel.add(backbutton);
		
		backbutton.addActionListener(e->
		{
			this.setVisible(false);
			lastpanel.setVisible(true);
		});
	}
	public SubjectPanel(StudentMain sm,JComponent lastpanel)
	{
		this(sm);
		headerlabel.setLayout(null);
		backbutton = new JButton("Back");
		backbutton.setContentAreaFilled(false);
		backbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		backbutton.setIcon(new ImageIcon(".\\assets\\back.png"));
		backbutton.setFocusable(false);
		backbutton.setForeground(Color.WHITE);
		backbutton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		backbutton.setBackground(new Color(32, 178, 170));
		backbutton.setBounds(10, 141, 88, 36);
		headerlabel.add(backbutton);
		
		backbutton.addActionListener(e->
		{
			this.setVisible(false);
			lastpanel.setVisible(true);
		});
	}
			
	private SubjectPanel()
	{
		new Timer(100,this);
//		timer.start();
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		this.setSize(1116, 705);
		setLayout(null);
		headerlabel = new JLabel("Subject Management");
		headerlabel.setBackground(new Color(32, 178, 170));
		headerlabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerlabel.setForeground(new Color(255, 255, 255));
		headerlabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		headerlabel.setBounds(10, 0, 1096, 66);
		headerlabel.setOpaque(true);
		add(headerlabel);
		
		selectcourcelabel = new JLabel("Select Cource  ");
		selectcourcelabel.setHorizontalAlignment(SwingConstants.LEFT);
		selectcourcelabel.setForeground(Color.DARK_GRAY);
		selectcourcelabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		selectcourcelabel.setBounds(30, 89, 248, 42);
		add(selectcourcelabel);
		
		 courcenamecombo = new JComboBox<String>(new CourceData().getCourceName());
		courcenamecombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		courcenamecombo.setBackground(Color.WHITE);
		courcenamecombo.setFocusable(false);
		courcenamecombo.addActionListener(this);
		courcenamecombo.setBounds(300, 88, 806, 42);
		add(courcenamecombo);
		
		selectsemlabel = new JLabel("Select Semester/Year  ");
		selectsemlabel.setHorizontalAlignment(SwingConstants.LEFT);
		selectsemlabel.setBackground(Color.DARK_GRAY);
		selectsemlabel.setForeground(Color.DARK_GRAY);
		selectsemlabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		selectsemlabel.setBounds(30, 165, 248, 40);
		add(selectsemlabel);
		
		 semoryearcombo = new JComboBox<String>();
		 semoryearcombo.setMaximumRowCount(16);
		semoryearcombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		semoryearcombo.setBackground(Color.WHITE);
		semoryearcombo.setFocusable(false);
		semoryearcombo.addActionListener(this);
		semoryearcombo.setBounds(300, 165, 806, 42);
		add(semoryearcombo);
		
		Courcecode=new CourceData().getCourcecode();
		
		addsubject = new JButton("Add New Subject");
		addsubject.setBorder(new EmptyBorder(0, 0, 0, 0));
		addsubject.addActionListener(this);
		addsubject.setForeground(new Color(255, 255, 255));
		addsubject.setBackground(new Color(32, 178, 170));
		addsubject.setFont(new Font("Segoe UI", Font.BOLD, 15));
		addsubject.setBounds(937, 242, 169, 37);
		addsubject.setVisible(false);
		addsubject.setFocusable(false);
		addsubject.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(addsubject);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(10, 311, 1096, 361);
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
		
		table.setGridColor(Color.LIGHT_GRAY);
		table.getTableHeader().setReorderingAllowed(false);		
		
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		scrollPane.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
		if(courcenamecombo.getSelectedIndex()==0 ||semoryearcombo.getSelectedIndex()==0)
		{
			scrollPane.setVisible(false);
		}
			if(e.getSource()==courcenamecombo)
			{
				
				
				scrollPane.setVisible(false);
				addsubject.setVisible(false);
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
			 if(e.getSource()==semoryearcombo)
			 {
				if(courcenamecombo.getSelectedIndex()>0 && semoryearcombo.getSelectedIndex()>0)
				{
				 scrollPane.setVisible(true);
				 int sem=semoryearcombo.getSelectedIndex();
				 int index=courcenamecombo.getSelectedIndex();
				 this.createtablemodel(Courcecode[index-1], sem);
					
				}
				else if(semoryearcombo.getSelectedIndex()==0)
				{
					scrollPane.setVisible(false);
					addsubject.setVisible(false);
				}
				
				else
				{
					scrollPane.setVisible(false);
				}
					
				 
			 }
			 if(courcenamecombo.getSelectedIndex()>0 && semoryearcombo.getSelectedIndex()>0)
			 {
				 addsubject.setVisible(true);
			 }

			 if(e.getSource()==addsubject)
			 {
				 int sem=semoryearcombo.getSelectedIndex();
				 int index=courcenamecombo.getSelectedIndex();
				 AddSubjectDialog sd=new AddSubjectDialog(Courcecode[index-1],sem,table);
				 sd.setLocationRelativeTo(null);
				 sd.setVisible(true);
				 
				 scrollPane.setVisible(true);
				
			 }
	}
	public void createtablemodel(String courcecode,int sem)
	{
		 ResultSet st=new SubjectData().getSubjectinfo(courcecode,sem);
		 table.setModel(DbUtils.resultSetToTableModel(st));
		 DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		 centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		 table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		 table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		 table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		 table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		 table.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
		 table.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
		 table.getColumnModel().getColumn(0).setMaxWidth(200);
		
			table.getColumnModel().getColumn(1).setMaxWidth(400);
			table.getColumnModel().getColumn(2).setMaxWidth(200);
			table.getColumnModel().getColumn(3).setMaxWidth(200);
			table.getColumnModel().getColumn(4).setMaxWidth(200);
			table.getColumnModel().getColumn(5).setMaxWidth(200);
			
	}

}
