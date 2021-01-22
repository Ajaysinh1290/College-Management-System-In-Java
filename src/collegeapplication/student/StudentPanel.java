package collegeapplication.student;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;

import collegeapplication.admin.AdminMain;
import collegeapplication.common.PhotoViewPanel;
import collegeapplication.faculty.FacultyMain;
import net.proteanit.sql.DbUtils;

/*
 * Title : StudentPanel.java
 * Created by : Ajaysinh Rathod
 * Purpose : Displaying all student details in table/photo view
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class StudentPanel extends JPanel implements ActionListener
{
	public JTable table;
	private JButton viewstudentbutton,addstudentbutton;
	public AdminMain am;
	public FacultyMain fm;
	public StudentMain sm;
	public JButton viewbutton;
	public JScrollPane tableviewscroll;
	public JScrollPane photoviewscrollpane;
	private JSpinner maxphotospinner;
	private JLabel maxphotolabel;
	private int maxphoto=5;
	private String condition="";
	private JLabel studentslabel;
	private JButton backbutton;
	private JPanel panel;
	/**
	 * Create the panel.
	 */
	public StudentPanel(AdminMain am)
	{
		this();
		this.am=am;
	}
	public StudentPanel(FacultyMain fm)
	{
		this();
		this.fm=fm;
		condition=" and s.courcecode='"+fm.f.getCourceCode()+"' and s.semoryear="+fm.f.getSemorYear()+" ";
		this.createtablemodel();
		this.addstudentbutton.setVisible(false);
		this.viewstudentbutton.setVisible(false);
		this.viewbutton.setLocation(addstudentbutton.getX(), addstudentbutton.getY());
	}
	public StudentPanel(FacultyMain fm,JComponent lastpanel)
	{
		this();
		this.fm=fm;
		condition=" and s.courcecode='"+fm.f.getCourceCode()+"' and s.semoryear="+fm.f.getSemorYear()+" ";
		this.createtablemodel();
		this.addstudentbutton.setVisible(false);
		this.viewstudentbutton.setVisible(false);
		this.viewbutton.setLocation(addstudentbutton.getX(), addstudentbutton.getY());
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
		
		backbutton.addActionListener(e->
		{
			fm.studentpanel.setVisible(false);
			lastpanel.setVisible(true);
		});
	}
	public StudentPanel(StudentMain sm)
	{
		this();
		this.sm=sm;
		condition=" and s.courcecode='"+sm.s.getCourceCode()+"' and s.semoryear="+sm.s.getSemorYear()+" "+" and userid!='"+sm.s.getUserId()+"'";
		this.createtablemodel();
		this.addstudentbutton.setVisible(false);
		this.viewstudentbutton.setVisible(false);
		this.viewbutton.setLocation(addstudentbutton.getX(), addstudentbutton.getY());
		studentslabel.setText("Classmates");
	}
	private StudentPanel() 
	{
		this.setName("Student Panel");
		setBackground(new Color(255, 255, 255));
		this.setSize(1116, 705);
		setLayout(null);
		
		tableviewscroll = new JScrollPane();
		tableviewscroll.setBorder(new EmptyBorder(0, 0, 0, 0));
		tableviewscroll.setBounds(10, 194, 1096, 500);
		for(Component c : tableviewscroll.getComponents())
		{
			c.setBackground(Color.white);
		}
		add(tableviewscroll);
		
		
		table = new JTable();
		table.setBorder(new LineBorder(Color.LIGHT_GRAY));
		table.getTableHeader().setBackground(new Color(32,178,170));
		table.getTableHeader().setForeground(Color.white);
		table.setSelectionBackground(new Color(240, 255, 255));
		table.getTableHeader().setFont(new Font("Arial",Font.BOLD,20));
		table.setFont(new Font("Segoe UI",Font.PLAIN,20));
		table.getTableHeader().setPreferredSize(new Dimension(50,40));
		table.setFocusable(false);
		table.setDragEnabled(false);
		table.setRowHeight(40);
		createtablemodel();
		table.setDefaultEditor(Object.class, null);
		table.setCursor(new Cursor(Cursor.HAND_CURSOR));
		table.setGridColor(Color.LIGHT_GRAY);
		table.getTableHeader().setReorderingAllowed(false);	
		tableviewscroll.setViewportView(table);
		table.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if(e.getClickCount()>1&&e.getButton()==MouseEvent.BUTTON1)
				{
					
				JTable t=(JTable) e.getSource();
				int row=t.getSelectedRow();
				String courcecode=table.getValueAt(row,0)+"";
				String  strsem=table.getValueAt(row, 4)+"";
				int sem=Integer.parseInt(strsem.substring(strsem.indexOf('-')+1));
				String strroll=table.getValueAt(row, 1)+"";
				long rollnumber=Long.parseLong(strroll);
				Student s=new StudentData().getStudentDetails(courcecode,sem,rollnumber);
				
					if(am!=null)
					{
						am.viewstudentpanel=new ViewStudentPanel(s,am,am.studentpanel);
						am.viewstudentpanel.setVisible(true);
						am.studentpanel.setVisible(false);
						am.viewstudentpanel.setLocation(am.panelx,0);
						am.viewstudentpanel.setVisible(true);
						am.viewstudentpanel.setFocusable(true);
						am.contentPane.add(am.viewstudentpanel);
					}
					else if(fm!=null)
					{
						fm.viewstudentpanel=new ViewStudentPanel(s,fm,fm.studentpanel);
						fm.viewstudentpanel.setVisible(true);
						fm.studentpanel.setVisible(false);
						fm.viewstudentpanel.setLocation(fm.panelx,0);
						fm.viewstudentpanel.setVisible(true);
						fm.viewstudentpanel.setFocusable(true);
						fm.contentPane.add(fm.viewstudentpanel);
					}
					else if(sm!=null)
					{
						sm.viewstudentpanel=new ViewStudentPanel(s,sm,sm.studentpanel);
						sm.viewstudentpanel.setVisible(true);
						sm.studentpanel.setVisible(false);
						sm.viewstudentpanel.setLocation(sm.panelx,0);
						sm.viewstudentpanel.setVisible(true);
						sm.viewstudentpanel.setFocusable(true);
						sm.contentPane.add(sm.viewstudentpanel);
					}
				}
				
			}
		});
		
		panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(10, 0, 1096, 183);
		add(panel);
		panel.setLayout(null);
		
		studentslabel = new JLabel("All Students");
		studentslabel.setIcon(null);
		studentslabel.setBounds(10, 65, 224, 44);
		panel.add(studentslabel);
		studentslabel.setBackground(new Color(32, 178, 170));
		studentslabel.setHorizontalAlignment(SwingConstants.LEFT);
		studentslabel.setForeground(Color.WHITE);
		studentslabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		studentslabel.setOpaque(true);
		  
		   viewstudentbutton = new JButton("View Student");
		   viewstudentbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		   viewstudentbutton.setBounds(769, 139, 153, 33);
		   panel.add(viewstudentbutton);
		   viewstudentbutton.setFocusable(false);
		   viewstudentbutton.setForeground(new Color(0, 128, 128));
		   viewstudentbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		   viewstudentbutton.setBackground(new Color(255, 255, 255));
		   viewstudentbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		   viewstudentbutton.addActionListener(this);
		 
		  addstudentbutton = new JButton("Add Student");
		  addstudentbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		  addstudentbutton.setBounds(932, 139, 153, 33);
		  panel.add(addstudentbutton);
		  addstudentbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		  addstudentbutton.setFocusable(false);
		  addstudentbutton.setForeground(new Color(0, 128, 128));
		  addstudentbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		  addstudentbutton.setBackground(new Color(255, 255, 255));
		  
		   viewbutton = new JButton("Photo View");
		 viewbutton.addActionListener(this);
		  viewbutton.setForeground(new Color(0, 128, 128));
		  viewbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		  viewbutton.setFocusable(false);
		  viewbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		  viewbutton.setBackground(Color.WHITE);
		  viewbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		  viewbutton.setBounds(606, 139, 153, 33);
		  panel.add(viewbutton);
		  maxphotospinner = new JSpinner();
		  
		  maxphotospinner.setModel(new SpinnerNumberModel(maxphoto, 1, 12, 1));
		  maxphotospinner.setFont(new Font("Tahoma", Font.PLAIN, 17));
		  maxphotospinner.setBounds(1009, 98, 76, 30);
		  maxphotospinner.setVisible(false);
		  JComponent c=maxphotospinner.getEditor();
		  JFormattedTextField tf=(JFormattedTextField)c.getComponent(0);
		  tf.setFocusable(false);
		  DefaultFormatter f=(DefaultFormatter) tf.getFormatter();
		  f.setCommitsOnValidEdit(true);
		  maxphotospinner.addChangeListener(new ChangeListener()
				  {

					@Override
					public void stateChanged(ChangeEvent arg0) {
						// TODO Auto-generated method stub
						maxphoto=(int) maxphotospinner.getValue();
						
						createphotopanel();
					}
			  
			  
		  });
		  
		  panel.add(maxphotospinner);
		  
		  maxphotolabel = new JLabel("Max Photos in Row");
		  maxphotolabel.setHorizontalAlignment(SwingConstants.RIGHT);
		  maxphotolabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		  maxphotolabel.setForeground(new Color(255, 255, 255));
		  maxphotolabel.setBounds(797, 98, 193, 30);
		  maxphotolabel.setVisible(false);
		  panel.add(maxphotolabel);
		  addstudentbutton.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getSource()==viewstudentbutton)
		{
			ViewStudentDialog vs=new ViewStudentDialog(am);
			vs.setLocationRelativeTo(null);
			vs.setVisible(true);
		}
		if(e.getSource()==addstudentbutton)
		{
			AddStudentDialog sd=new AddStudentDialog(table,this);
			sd.setLocationRelativeTo(null);
			sd.setVisible(true);
			
		
		}
		if(e.getSource()==viewbutton && viewbutton.getText().equals("Photo View"))
		{
			
			
			createphotopanel();
			
			
		}
		else if(e.getSource()==viewbutton && viewbutton.getText().equals("Table View"))
		{
			if(photoviewscrollpane!=null)
			{
				photoviewscrollpane.setVisible(false);
			}
			createtablemodel();
			tableviewscroll.setVisible(true);
			viewbutton.setText("Photo View");
			
		}
		if(photoviewscrollpane!=null && photoviewscrollpane.isVisible())
		{
			maxphotolabel.setVisible(true);
			maxphotospinner.setVisible(true);
		}
		else
		{
			maxphotolabel.setVisible(false);
			maxphotospinner.setVisible(false);
		}
	}
	public void createphotopanel()
	{
		if(this.photoviewscrollpane!=null)
		{
			this.photoviewscrollpane.setVisible(false);
		}
		this.tableviewscroll.setVisible(false);
		PhotoViewPanel photoviewpanel=new PhotoViewPanel(this,maxphoto);
		photoviewpanel.setVisible(true);
		this.photoviewscrollpane=new JScrollPane(photoviewpanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.photoviewscrollpane.getVerticalScrollBar().setUnitIncrement(16);
		this.photoviewscrollpane.setBounds(0, 189, 1105, 500);
		this.photoviewscrollpane.setVisible(true);
		this.add(photoviewscrollpane);
		this.photoviewscrollpane.setBorder(new EmptyBorder(0,0,0,0));
		viewbutton.setText("Table View");
	}
	public void createtablemodel()
	{
		ResultSet rs=new StudentData().getStudentinfo(condition);
		if(rs!=null)
		{
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		table.getColumnModel().getColumn(0).setMaxWidth(150);
		table.getColumnModel().getColumn(1).setMaxWidth(200);
		table.getColumnModel().getColumn(2).setMaxWidth(300);
		table.getColumnModel().getColumn(3).setMaxWidth(300);
		table.getColumnModel().getColumn(4).setMaxWidth(150);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	}
}
