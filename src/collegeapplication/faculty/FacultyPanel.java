package collegeapplication.faculty;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.DefaultFormatter;

import collegeapplication.admin.AdminMain;
import collegeapplication.common.PhotoViewPanel;
import collegeapplication.student.StudentMain;
import net.proteanit.sql.DbUtils;

/*
 * Title : FacultyPanel.java
 * Created by : Ajaysinh Rathod
 * Purpose : To display all faculty in table or photoview
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class FacultyPanel extends JPanel implements ActionListener {
	private JButton addnewfaculity;
	public JButton viewbutton;
	public JTable table;
	public AdminMain am;
	public JPanel tableviewpanel;
	public JScrollPane photoviewscrollpane;
	private JLabel maxphotolabel;
	private JSpinner maxphotospinner;
	int maxphoto=4;
	public StudentMain sm;
	public FacultyMain fm;
	private String condition="";
	private JLabel headinglabel;

	/**
	 * Create the panel.
	 * @param am 
	 */
	public FacultyPanel(AdminMain am)
	{
		this();
		this.am=am;
		table.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if(e.getClickCount()>1  && e.getButton()==MouseEvent.BUTTON1)
				{
					JTable t=(JTable) e.getSource();
					int fid=Integer.parseInt(t.getValueAt(t.getSelectedRow(), 0)+"");
					Faculty f=new FacultyData().getFacultyInfobyId(fid);
				
				am.viewfacultypanel=new ViewFacultyPanel(f,am,am.facultypanel);
				am.viewfacultypanel.setVisible(true);
				am.facultypanel.setVisible(false);
				am.viewfacultypanel.setLocation(am.panelx,am.panely);
				am.viewfacultypanel.setVisible(true);
				am.viewfacultypanel.setFocusable(true);
				am.contentPane.add(am.viewfacultypanel);
				
				}
				
			}
		});
	}
	public FacultyPanel(FacultyMain fm)
	{
		this();
		this.fm=fm;
		headinglabel.setText("Co-Faculties");
		this.addnewfaculity.setVisible(false);
		this.viewbutton.setLocation(addnewfaculity.getX(), addnewfaculity.getY());
		condition=" where courcecode='"+fm.f.getCourceCode()+"' and semoryear="+fm.f.getSemorYear()+" and facultyid!="+fm.f.getFacultyId();
		this.createtablemodel();
		 table.addMouseListener(new MouseAdapter()
			{
				public void mousePressed(MouseEvent e)
				{
					if(e.getClickCount()>1  && e.getButton()==MouseEvent.BUTTON1)
					{
					JTable t=(JTable) e.getSource();
					int fid=Integer.parseInt(t.getValueAt(t.getSelectedRow(), 0)+"");
					Faculty f=new FacultyData().getFacultyInfobyId(fid);
				
				fm.viewfacultypanel=new ViewFacultyPanel(f,fm,fm.facultypanel);
				fm.viewfacultypanel.setVisible(true);
				fm.facultypanel.setVisible(false);
				fm.viewfacultypanel.setLocation(fm.panelx,fm.panely);
				fm.viewfacultypanel.setVisible(true);
				fm.viewfacultypanel.setFocusable(true);
				fm.contentPane.add(fm.viewfacultypanel);
				
				}
				
			}
		});
	}
	/**
	 * @param sm
	 */
	public FacultyPanel(StudentMain sm)
	{
		this();
		this.sm=sm;
		headinglabel.setText("Faculties");
		this.addnewfaculity.setVisible(false);
		this.viewbutton.setLocation(addnewfaculity.getX(), addnewfaculity.getY());
		condition=" where courcecode='"+sm.s.getCourceCode()+"' and semoryear="+sm.s.getSemorYear()+" ";
		this.createtablemodel();
		table.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if(e.getClickCount()>1  && e.getButton()==MouseEvent.BUTTON1)
				{
					JTable t=(JTable) e.getSource();
					int fid=Integer.parseInt(t.getValueAt(t.getSelectedRow(), 0)+"");
					Faculty f=new FacultyData().getFacultyInfobyId(fid);
				
				sm.viewfacultypanel=new ViewFacultyPanel(f,sm,sm.facultypanel);
				sm.viewfacultypanel.setVisible(true);
				sm.facultypanel.setVisible(false);
				sm.viewfacultypanel.setLocation(sm.panelx,sm.panely);
				sm.viewfacultypanel.setVisible(true);
				sm.viewfacultypanel.setFocusable(true);
				sm.contentPane.add(sm.viewfacultypanel);
				
				}
				
			}
		});
	}
	
	private FacultyPanel() {
		this.setName("Faculty Panel");
		setBackground(Color.WHITE);
		this.setSize(1116, 705);
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(10, 0, 1096, 183);
		add(panel);
		panel.setLayout(null);
		headinglabel = new JLabel("All Faculities");
		headinglabel.setIcon(null);
		headinglabel.setBounds(10, 65, 272, 44);
		panel.add(headinglabel);
		headinglabel.setBackground(new Color(32, 178, 170));
		headinglabel.setHorizontalAlignment(SwingConstants.LEFT);
		headinglabel.setForeground(Color.WHITE);
		headinglabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		headinglabel.setOpaque(true);
		 
		  addnewfaculity = new JButton("Add Faculity");
		  addnewfaculity.setBorder(new EmptyBorder(0, 0, 0, 0));
		  addnewfaculity.setBounds(932, 139, 153, 33);
		  panel.add(addnewfaculity);
		  addnewfaculity.setFocusable(false);
		  addnewfaculity.setForeground(new Color(0, 128, 128));
		  addnewfaculity.setFont(new Font("Segoe UI", Font.BOLD, 15));
		  addnewfaculity.setCursor(new Cursor(Cursor.HAND_CURSOR));
		  
		  addnewfaculity.setBackground(new Color(255, 255, 255));
		  
		  viewbutton = new JButton("Photo View");
		  viewbutton.setForeground(new Color(0, 128, 128));
		  viewbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		  viewbutton.setFocusable(false);
		  viewbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		  viewbutton.setBackground(Color.WHITE);
		  viewbutton.setBounds(769, 139, 153, 33);
		  
		  viewbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		  viewbutton.addActionListener(this);
		  panel.add(viewbutton);
		  
		   maxphotospinner = new JSpinner();
		   maxphotospinner.setForeground(new Color(255, 255, 255));
		   maxphotospinner.setBackground(new Color(255, 255, 255));
		  
		   maxphotospinner.setVerifyInputWhenFocusTarget(false);
		  maxphotospinner.setModel(new SpinnerNumberModel(4, 1, 12, 1));
		  maxphotospinner.setFont(new Font("Tahoma", Font.PLAIN, 17));
		  JComponent comp = maxphotospinner.getEditor();
		    JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
		    field.setFocusable(false);
		    DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
		    formatter.setCommitsOnValidEdit(true);
		    maxphotospinner.addChangeListener(new ChangeListener() {

		        @Override
		        public void stateChanged(ChangeEvent e) {
		        	maxphoto=(int) maxphotospinner.getValue();
		        	createphotoviewpanel();
		        }
		    });
		  maxphotospinner.setBounds(1000, 83, 85, 33);
		  maxphotospinner.setVisible(false);
		  maxphotospinner.setFocusable(false);
		  panel.add(maxphotospinner);
		  
		  maxphotolabel = new JLabel("Max Photos in Row  ");
		  maxphotolabel.setHorizontalAlignment(SwingConstants.RIGHT);
		  maxphotolabel.setForeground(Color.WHITE);
		  maxphotolabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		  maxphotolabel.setBounds(793, 83, 197, 33);
		  panel.add(maxphotolabel);
		  maxphotolabel.setVisible(false);
		  
		  addnewfaculity.addActionListener(this);
		  
		   tableviewpanel = new JPanel();
		  tableviewpanel.setBackground(Color.WHITE);
		  tableviewpanel.setBounds(0, 189, 1116, 528);
		  add(tableviewpanel);
		  tableviewpanel.setLayout(null);
		  
		  JScrollPane scrollPane = new JScrollPane();
		  scrollPane.setBounds(10, 11, 1095, 483);
		  scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
			for(Component c : scrollPane.getComponents())
			{
				c.setBackground(Color.white);
			}
		  tableviewpanel.add(scrollPane);
		  
		  table = new JTable();
		
			table = new JTable();
			table.setCursor(new Cursor(Cursor.HAND_CURSOR));
			table.setBorder(new LineBorder(Color.LIGHT_GRAY));
			table.getTableHeader().setBackground(new Color(32,178,170));
			table.getTableHeader().setForeground(Color.white);
			table.getTableHeader().setFont(new Font("Arial",Font.BOLD,20));
			table.setFont(new Font("Segoe UI",Font.PLAIN,20));
			table.getTableHeader().setPreferredSize(new Dimension(50,40));
			table.setDragEnabled(false);
			table.setRowHeight(40);
			createtablemodel();
			table.setSelectionBackground(new Color(240, 255, 255));
			table.setFocusable(false);
			table.setDefaultEditor(Object.class,null);
			table.setGridColor(Color.LIGHT_GRAY);
			table.getTableHeader().setReorderingAllowed(false);	
			scrollPane.setViewportView(table);
		  
			
		  
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		if(e.getSource()==addnewfaculity)
		{
			AddFaculityDialog afd=new AddFaculityDialog(this);
			afd.setLocationRelativeTo(null);
			afd.setVisible(true);
		}
		if(e.getSource()==viewbutton && viewbutton.getText().equals("Photo View"))
		{
			createphotoviewpanel();
			
		}
		else if(e.getSource()==viewbutton && viewbutton.getText().equals("Table View"))
		{
			if(photoviewscrollpane!=null)
			{
				photoviewscrollpane.setVisible(false);
			}
			createtablemodel();
			tableviewpanel.setVisible(true);
			viewbutton.setText("Photo View");
			
		}
			
		if(photoviewscrollpane!=null && photoviewscrollpane.isVisible()) {
			maxphotolabel.setVisible(true);
			maxphotospinner.setVisible(true);
		}
		else
		{
			maxphotolabel.setVisible(false);
			maxphotospinner.setVisible(false);
		}
	}
	public void createphotoviewpanel()
	{
		if(this.photoviewscrollpane!=null)
		{
			this.photoviewscrollpane.setVisible(false);
		}
		this.tableviewpanel.setVisible(false);
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
		ResultSet rs=new FacultyData().getFacultyInfo(condition);
		if(rs!=null)
		{
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		table.getColumnModel().getColumn(0).setMaxWidth(200);
		table.getColumnModel().getColumn(1).setMaxWidth(300);
		table.getColumnModel().getColumn(2).setMaxWidth(500);
		table.getColumnModel().getColumn(3).setMaxWidth(250);
		table.getColumnModel().getColumn(4).setMaxWidth(250);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		DefaultTableCellRenderer cellrenderer=new DefaultTableCellRenderer();
		cellrenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(cellrenderer);
	}

}
