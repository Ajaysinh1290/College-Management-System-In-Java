package collegeapplication.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import collegeapplication.admin.AdminMain;
import collegeapplication.faculty.Faculty;
import collegeapplication.faculty.FacultyData;
import collegeapplication.faculty.ViewFacultyPanel;
import collegeapplication.student.Student;
import collegeapplication.student.StudentData;
import collegeapplication.student.ViewStudentPanel;


/*
 * Title : UserPanel.java
 * Created by : Ajaysinh Rathod
 * Purpose : To displaying users activity 
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class UsersPanel extends JPanel {

	private JLabel headerlabel;
	private JTable table;
	String condition="";

	/**
	 * Create the panel.
	 */

	public UsersPanel(AdminMain am)
	{
		this();
		createtablemodel();
		table.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if(e.getClickCount()>1&&e.getButton()==MouseEvent.BUTTON1)
				{
					
					JTable t=(JTable) e.getSource();
					int row=t.getSelectedRow();
					String userprofile=table.getValueAt(row, 0)+"";
					String userid=table.getValueAt(row, 2)+"";
					if(userprofile.equals("Student"))
					{
						Student s=new StudentData().getStudentDetailsByUserId(userid);
						am.viewstudentpanel=new ViewStudentPanel(s,am,am.userspanel);
						am.viewstudentpanel.setVisible(true);
						am.userspanel.setVisible(false);
						am.viewstudentpanel.setLocation(am.panelx,0);
						am.viewstudentpanel.setVisible(true);
						am.viewstudentpanel.setFocusable(true);
						am.contentPane.add(am.viewstudentpanel);
					}
					else if(userprofile.equals("Faculty"))
					{
						int fid=Integer.parseInt(userid);
						Faculty f=new FacultyData().getFacultyInfobyId(fid);
						am.viewfacultypanel=new ViewFacultyPanel(f,am,am.userspanel);
						am.viewfacultypanel.setVisible(true);
						am.userspanel.setVisible(false);
						am.viewfacultypanel.setLocation(am.panelx,am.panely);
						am.viewfacultypanel.setVisible(true);
						am.viewfacultypanel.setFocusable(true);
						am.contentPane.add(am.viewfacultypanel);
					}
						
					
				}
				
			}
		});
	}
	public UsersPanel() {
		setBackground(Color.WHITE);
		this.setSize(1116, 705);
		setLayout(null);
		setName("Users Panel");
		
		headerlabel = new JLabel("  Users");
		headerlabel.setBackground(new Color(32, 178, 170));
		headerlabel.setForeground(new Color(255, 255, 255));
		headerlabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		headerlabel.setHorizontalAlignment(JLabel.LEFT);
		headerlabel.setBounds(10, 0, 1096, 183);
		headerlabel.setOpaque(true);
		add(headerlabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(10, 194, 1096, 500);
		for(Component c:scrollPane.getComponents())
		{
			c.setBackground(Color.white);
		}
		add(scrollPane);
		
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
		table.setDefaultEditor(Object.class, null);
		table.setCursor(new Cursor(Cursor.HAND_CURSOR));
		table.setGridColor(Color.LIGHT_GRAY);
		table.getTableHeader().setReorderingAllowed(false);	
		scrollPane.setViewportView(table);

	}
	
	public void createtablemodel()
	{
		ArrayList<User> list=new UserData().getUserInfo(condition);
		String Column[]= {"User Profile","Class","User id","User name","Login Date","Login Time"};
		DefaultTableModel model=new DefaultTableModel(Column,0);
		for(int i=0; i<list.size(); i++)
		{
			User user=list.get(i);
			model.addRow(new Object[0]);
			model.setValueAt(user.getUserProfile(), i, 0);
			model.setValueAt(user.getCourceCode()+"-"+user.getSemorYear(), i, 1);
			model.setValueAt(user.getUserid(), i, 2);
			model.setValueAt(user.getName(), i, 3);
			model.setValueAt(user.getLoginDate(), i, 4);
			model.setValueAt(user.getLoginTime(), i, 5);
			
			
		}
		table.setModel(model);
		
		table.getColumnModel().getColumn(0).setMaxWidth(200);
		table.getColumnModel().getColumn(1).setMaxWidth(200);
		table.getColumnModel().getColumn(2).setMaxWidth(200);
		table.getColumnModel().getColumn(3).setMaxWidth(200);
		table.getColumnModel().getColumn(4).setMaxWidth(200);
		table.getColumnModel().getColumn(5).setMaxWidth(200);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.getColumnModel().getColumn(0).setCellRenderer(new CellRenderer());
		table.getColumnModel().getColumn(1).setCellRenderer(new CellRenderer());
		table.getColumnModel().getColumn(2).setCellRenderer(new CellRenderer());
		table.getColumnModel().getColumn(3).setCellRenderer(new CellRenderer());
		table.getColumnModel().getColumn(4).setCellRenderer(new CellRenderer());
		table.getColumnModel().getColumn(5).setCellRenderer(new CellRenderer());
		
		
	}
	private class CellRenderer extends DefaultTableCellRenderer 
	{


		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
		        int column) 
		{
		    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		    this.setHorizontalAlignment(JLabel.CENTER);
		    return this;
		}
	}
}
