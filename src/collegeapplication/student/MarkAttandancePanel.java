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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import collegeapplication.admin.AdminMain;
import collegeapplication.common.Notification;
import collegeapplication.common.NotificationData;
import collegeapplication.common.TimeUtil;
import collegeapplication.cource.CourceData;
import collegeapplication.faculty.FacultyMain;
import collegeapplication.subject.SubjectData;

/*
 * Title : MarkAttandancePanel.java
 * Created by : Ajaysinh Rathod
 * Purpose : For marking student attandance
 * Mail : ajaysinhrathod1290@gmail.com
 */
@SuppressWarnings("serial")
public class MarkAttandancePanel extends JPanel implements ActionListener {

	private JTable table;
	private JComboBox<String> semoryearcombo;
	private JLabel lblError;
	private JScrollPane scrollPane;
	private JComboBox<String> subjectnamecombo;
	private JSpinner datespinner;
	private JComboBox<String> courcenamecombo;
	int totalstudent=0;
	private JButton submitbutton;
	private JButton fetchstudentbutton;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel headerlabel;
	private JLabel label;
	private JLabel nodatafoundlabel;

	/**
	 * Create the panel.
	 */
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(1116,scrollPane.getY()+80+totalstudent*40+60);
		
	}
	public MarkAttandancePanel(AdminMain am)
	{
		this();
	}
	public MarkAttandancePanel(FacultyMain fm)
	{
		this();
		
		courcenamecombo.setSelectedItem(new CourceData().getcourcename(fm.f.getCourceCode()));
		semoryearcombo.setModel(new DefaultComboBoxModel<String>(new CourceData().getSemorYear(courcenamecombo.getSelectedItem()+"")));
		String[] totalsub=new SubjectData().getSubjectinCource(fm.f.getCourceCode(), fm.f.getSemorYear());	
		subjectnamecombo.setModel(new DefaultComboBoxModel<String>(totalsub));
		semoryearcombo.setSelectedIndex(fm.f.getSemorYear());
		subjectnamecombo.setSelectedItem(fm.f.getSubject());
		courcenamecombo.setVisible(false);
		semoryearcombo.setVisible(false);
		subjectnamecombo.setVisible(false);
		scrollPane.setLocation(scrollPane.getX(),semoryearcombo.getY());
		label1.setVisible(false);
		label2.setVisible(false);
		label3.setVisible(false);
		headerlabel.setHorizontalAlignment(JLabel.LEFT);
		headerlabel.setBounds(10, 0, 1078, 183);
		label.setLocation(label.getX(), headerlabel.getY()+headerlabel.getHeight()+20);
		fetchstudentbutton.setLocation(fetchstudentbutton.getX(), label.getY()+label.getHeight()+20);
		
		datespinner.setLocation(datespinner.getX(), label.getY());
		scrollPane.setBounds(scrollPane.getX(),fetchstudentbutton.getY()+fetchstudentbutton.getHeight()+20 , scrollPane.getWidth(), this.getHeight()-headerlabel.getHeight()-20);
		
	}
	
	
	private MarkAttandancePanel() {
		
		setBackground(Color.WHITE);
		this.setSize(1116, 705);
		setLayout(null);
		
		headerlabel = new JLabel("  Mark Attandance ");
		headerlabel.setBackground(new Color(32, 178, 170));
		headerlabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerlabel.setForeground(new Color(255, 255, 255));
		headerlabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		headerlabel.setBounds(10, 0, 1078, 66);
		headerlabel.setOpaque(true);
		add(headerlabel);
		
		label = new JLabel("Select Date   :");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		label.setBounds(39, 90, 153, 40);
		add(label);
		
		
		label1 = new JLabel("Cource Name   :");
		label1.setHorizontalAlignment(SwingConstants.RIGHT);
		label1.setForeground(Color.DARK_GRAY);
		label1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		label1.setBounds(29, 158, 163, 37);
		add(label1);
		
		label2 = new JLabel("Semester or Years   :");
		label2.setHorizontalAlignment(SwingConstants.RIGHT);
		label2.setForeground(Color.DARK_GRAY);
		label2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		label2.setBounds(23, 222, 169, 40);
		add(label2);
		
		label3 = new JLabel("Select Subject  :");
		label3.setHorizontalAlignment(SwingConstants.RIGHT);
		label3.setForeground(Color.DARK_GRAY);
		label3.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		label3.setBounds(29, 293, 163, 32);
		add(label3);
		
	
		lblError=new JLabel("This is required question !");
		lblError.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 0, 0)));
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setFont(new Font("Candara", Font.PLAIN, 17));
		lblError.setVisible(false);
		lblError.setBounds(211,134,355,21);
		add(lblError);
	
		
		
	
		
		 scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		 
		 scrollPane.setBorder(new MatteBorder(0, 0, 2, 1, (Color) new Color(192, 192, 192)));
		 scrollPane.setVisible(false);
		scrollPane.setBounds(29, 413, 1044, 66);
		for(Component c:scrollPane.getComponents())
		{
			c.setBackground(Color.white);
		}
		add(scrollPane);

		table = new JTable();
		table.setBorder(new LineBorder(Color.LIGHT_GRAY));
		table.setForeground(new Color(0, 0, 0));
		table.setRowHeight(40);
		table.getTableHeader().setBackground(new Color(32,178,170));
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setFont(new Font("Arial",Font.BOLD,20));
		table.setFont(new Font("Segoe UI",Font.PLAIN,20));
		table.getTableHeader().setPreferredSize(new Dimension(50,40));
		table.setDragEnabled(false);
		table.setFocusable(false);
		table.setGridColor(Color.LIGHT_GRAY);
		table.getTableHeader().setReorderingAllowed(false);			
		scrollPane.setViewportView(table);
	
		 submitbutton = new JButton("Submit");
		 submitbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
			submitbutton.setForeground(Color.WHITE);
			submitbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
			submitbutton.setFocusable(false);
			submitbutton.setBackground(new Color(32, 178, 170));
			submitbutton.setVisible(false);
			submitbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			submitbutton.setBounds(924, 504, 149, 37);
			submitbutton.addActionListener(this);
			add(submitbutton);
		
		
		
		courcenamecombo = new JComboBox<String>(new CourceData().getCourceName());
		
		courcenamecombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		courcenamecombo.addActionListener(this);
		courcenamecombo.setFocusable(false);
		courcenamecombo.setBackground(new Color(255, 255, 255));
		courcenamecombo.setBounds(204, 156, 867, 40);
		add(courcenamecombo);
		
		semoryearcombo = new JComboBox<String>();
		semoryearcombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		semoryearcombo.setBackground(Color.WHITE);
		semoryearcombo.setBounds(204, 222, 867, 40);
		semoryearcombo.addActionListener(this);
		semoryearcombo.setFocusable(false);
		
		add(semoryearcombo);
		subjectnamecombo = new JComboBox<String>();
		subjectnamecombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		subjectnamecombo.setFocusable(false);
		subjectnamecombo.addActionListener(this);
		subjectnamecombo.setBackground(Color.WHITE);
		subjectnamecombo.setBounds(205, 289, 867, 40);
		add(subjectnamecombo);
		
		datespinner = new JSpinner();
		datespinner.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		datespinner.setBounds(204, 90, 867, 40);
		SimpleDateFormat dateformat=new SimpleDateFormat("dd-MM-yyyy");
		datespinner.setModel(new SpinnerDateModel());
		datespinner.setEditor(new JSpinner.DateEditor(datespinner,dateformat.toPattern()));
//		datespinner.setBorder(new LineBorder(new Color(32, 178, 170), 2, true));
		add(datespinner);
		
		fetchstudentbutton = new JButton("Fetch Students");
		fetchstudentbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		fetchstudentbutton.setFocusPainted(false);
		fetchstudentbutton.setBackground(new Color(32, 178, 170));
		fetchstudentbutton.setForeground(Color.WHITE);
		fetchstudentbutton.setFocusable(true);
		fetchstudentbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		fetchstudentbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		fetchstudentbutton.setBounds(920, 352, 151, 37);
		fetchstudentbutton.addActionListener(this);
		add(fetchstudentbutton);
		
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
	public void actionPerformed(ActionEvent e) {
		lblError.setVisible(false);
		
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
		if(e.getSource()==fetchstudentbutton)
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
				lblError.setVisible(true);
				lblError.setText("No Subject Found !");
				lblError.setBounds(tf.getX(), tf.getY()+tf.getHeight()-5, 400,26);
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
		if(submitbutton==e.getSource())
		{
			this.submitAttandance();
			
		}
		// TODO Auto-generated method stub
		
	}
	public void noDataFound()
	{
		scrollPane.setVisible(false);
		submitbutton.setVisible(false);
		nodatafoundlabel.setVisible(true);
		nodatafoundlabel.setLocation(nodatafoundlabel.getX(), scrollPane.getY()-80);
		
	}
	public void showerror(JComponent tf)
	{
		lblError.setVisible(true);
		lblError.setText("This is required question !");
		lblError.setBounds(tf.getX(), tf.getY()+tf.getHeight()-5, 400,26);
	}
	public void setIcons(JTable table, int column, Icon icon, Icon selectedIcon) {
	    JCheckBox cellRenderer = (JCheckBox) table.getCellRenderer(0, column);
	    cellRenderer.setSelectedIcon(selectedIcon);
	    cellRenderer.setIcon(icon);

	    DefaultCellEditor cellEditor = (DefaultCellEditor) table.getCellEditor(0, column);
	    JCheckBox editorComponent = (JCheckBox) cellEditor.getComponent();
	    editorComponent.setSelectedIcon(selectedIcon);
	    editorComponent.setIcon(icon);
	}
	public void createtablemodel()
	{
		nodatafoundlabel.setVisible(false);
		Attandance a=new Attandance();
		a.setCourceCode(new CourceData().getCourcecode(courcenamecombo.getSelectedItem()+""));
		Date date=(Date) datespinner.getValue();
		a.setAttandanceDate(new SimpleDateFormat("dd-MM-yyyy").format(date));
		a.setSemorYear(semoryearcombo.getSelectedIndex());
		a.setSubjectName(subjectnamecombo.getSelectedItem()+"");
		a.setSubjectCode(new SubjectData().getSubjectCode(a.getCourceCode(), a.getSemorYear(),a.getSubjectName()));
		if(new StudentData().isSubmitted(a.getSubjectCode(), a.getAttandanceDate()))
		{
			scrollPane.setVisible(false);
			submitbutton.setVisible(false);
			JOptionPane.showMessageDialog(null, "Attandance Already Submitted","Error",JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
		table.setModel(createModel(a));
		scrollPane.setSize(scrollPane.getWidth(), 40+(totalstudent*40));
		this.setSize(1116, scrollPane.getY()+80+totalstudent*40+60);
	
		submitbutton.setLocation(submitbutton.getX(),scrollPane.getY()+scrollPane.getHeight()+20);
		table.getColumnModel().getColumn(0).setMaxWidth(200);
		table.getColumnModel().getColumn(1).setMaxWidth(250);
		table.getColumnModel().getColumn(2).setMaxWidth(250);
		table.getColumnModel().getColumn(3).setMaxWidth(175);
		table.getColumnModel().getColumn(4).setMaxWidth(150);
		table.getColumnModel().getColumn(5).setMaxWidth(250);
		table.getColumnModel().getColumn(6).setMaxWidth(80);
		
		 table.getColumnModel().getColumn(6).setHeaderRenderer(
		            new HeaderRendererForCheckBox(table.getTableHeader(),6));
		 
		 this.setIcons(table, 6, new ImageIcon("./assets/unselectedcheckboxicon.png"), new ImageIcon("./assets/selectedcheckboxicon.png"));
		table.setSelectionBackground(new Color(255, 255, 255));
		table.setSelectionForeground(Color.black);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		scrollPane.setVisible(true);
		if(totalstudent==0)
		{
			noDataFound();
		}
		}
	}
	public DefaultTableModel  createModel(Attandance a)
	{
		String Column[]= {"Roll Number","Student Name","Subject Code","Cource","Sem/Year","Attandance Date",""};
		DefaultTableModel model=new DefaultTableModel(Column,0)
				{
					boolean canEdit[]= {false,false,false,false,false,false,true};
					public Class<?> getColumnClass(int column)
					{
						switch(column)
						{
							case 0:	return String.class;
							case 1:	return String.class;
							case 2: return String.class;
							case 3: return String.class;
							case 4: return String.class;
							case 5:	return String.class;
							case 6:	return Boolean.class;
							default : return String.class;
						}
					}
					@Override
					public boolean isCellEditable(int row,int column)
					{
						return canEdit[column];
					}
				};
				
			ArrayList<Attandance> list=new StudentData().getStudentsForAttandance(a);
			for(int i=0; i<list.size(); i++)
			{
				
				model.addRow(new Object[0]);
				model.setValueAt(list.get(i).getRollNumber(), i, 0);
				model.setValueAt(list.get(i).getStudentName(), i, 1);
				model.setValueAt(a.getSubjectCode(), i, 2);
				model.setValueAt(a.getCourceCode(), i, 3);
				model.setValueAt(a.getAttandanceDate(), i, 5);
				model.setValueAt(a.getSemorYear(), i, 4);
				model.setValueAt(list.get(i).getPresentStatus(), i, 6);
			}
			totalstudent=list.size();
			submitbutton.setVisible(true);
			table.setEnabled(true);
			return model;
		
	}
	public void submitAttandance()
	{
		int result=0;
		Attandance a=new Attandance();
		for(int i=0; i<table.getRowCount(); i++)
		{
			
			a.setRollNumber((long) table.getValueAt(i, 0));
			a.setSubjectCode((String) table.getValueAt(i,2));
			a.setAttandanceDate((String) table.getValueAt(i, 5));
			a.setCourceCode(table.getValueAt(i, 3)+"");
			a.setSemorYear(Integer.parseInt(table.getValueAt(i, 4)+""));
			a.setPresentStatus(Boolean.valueOf(table.getValueAt(i, 6)+""));
			
			result=new StudentData().addStudentAttandance(a);
		}
		if(result==1)
		{
			Notification n=new Notification();
			n.setUserProfile("Student");
			n.setUserId("Admin");
			n.setCourceCode(a.getCourceCode());
			n.setSemorYear(a.getSemorYear());
			n.setTitle("Attandance");
			n.setMessage("Your "+new SubjectData().getSubjectName(a.getSubjectCode())+" subject attandance on "+a.getAttandanceDate()+" is updated.");
			n.setTime(TimeUtil.getCurrentTime());
			new NotificationData().addNotification(n);
			JOptionPane.showMessageDialog(null, "Attandance Submitted");
			scrollPane.setVisible(false);
			submitbutton.setVisible(false);
			totalstudent=0;
			scrollPane.setBounds(29, 413, 1068, 315);
			this.setSize(1116, scrollPane.getY()+80+totalstudent*40+60);
		}
	}
}



