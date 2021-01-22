package collegeapplication.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import collegeapplication.admin.Admin;
import collegeapplication.admin.AdminData;
import collegeapplication.admin.AdminMain;
import collegeapplication.common.ImageUtil;
import collegeapplication.faculty.FacultyMain;
import collegeapplication.student.StudentMain;

import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import java.awt.Cursor;

/*
 * Title : ChatMainPanel.java
 * Created by : Ajaysinh Rathod
 * Purpose : Main chat frame 
 * Mail : ajaysinhrathod1290@gmail.com
 */
@SuppressWarnings("serial")
public class ChatMainPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	
	public ChatPanel chatpanel;
	private ContactListPanel contactlistpanel;
	public ChatInfoPanel chatinfopanel;
	private JScrollPane contactlistpanelscroll;
	private JPanel panel;
	public JTextField searchfield;
	private JLabel profilepiclabel;
	public AdminMain am;
	public FacultyMain fm;
	public StudentMain sm;
	private JLabel onlinestatus;
	MyDocumentListener fielddoc=new MyDocumentListener();
	private JLabel hintlabel;
	
	public ChatMainPanel(AdminMain am)
	{
		this();
		this.am=am;
		
		contactlistpanel=new ContactListPanel(am,this);
		contactlistpanel.setBounds(0, 0, 330, 600);
		contactlistpanelscroll.setViewportView(contactlistpanel);
		contactlistpanelscroll.setVisible(true);
		add(contactlistpanelscroll);
		
		chatpanel=new ChatPanel();
		chatpanel.setSize(500, 705);
		chatpanel.setLocation(330, 0);
		chatpanel.setVisible(true);
		add(chatpanel);
		
		Admin admin=new AdminData().getAdminData();
		chatpanel.setFromUserData("Admin", "Principal",admin.getProfilePic());
		BufferedImage image=ImageUtil.toBufferedImage(admin.getProfilePic(50, 50));
		profilepiclabel.setIcon(new ImageIcon(ImageUtil.makeRoundedCorner(image, 50)));
		chatinfopanel.setData(admin);
		chatinfopanel.setAdmin(new AdminData().getAdminData());
		profilepiclabel.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if(e.getButton()==MouseEvent.BUTTON1)
				{
					chatinfopanel.setData(admin);
					chatpanel.setEmptyChatPanel();
					for(JPanel panel:contactlistpanel.contactlist)
					{
						panel.setBackground(Color.white);
						
						for(Component c:panel.getComponents())
						{

							if(c.getName()!=null && c.getName().equals("lastmessage"))
							{
								if(!c.getForeground().equals(Color.DARK_GRAY))
								{
									c.setForeground(Color.gray);
								}
							}
							else if(c.getName()!=null &&c.getName().equals("messagetime")&&c.getForeground().equals(new Color(30,178,170)))
							{
								
								c.setForeground(Color.gray);
							}
							else if(c.getName()!=null&&c.getName().equals("username"))
							{
								c.setForeground(Color.DARK_GRAY);
							}
							else if(c.getName()!=null && c.getName().equals("messagetime"))
							{
								c.setForeground(Color.gray);
							}
						}
					}
				}
			}
		});
	}
	public ChatMainPanel(FacultyMain fm)
	{
		this();
		this.fm=fm;
		contactlistpanel=new ContactListPanel(fm,this);
		contactlistpanel.setBounds(0, 0, 330, 600);
		contactlistpanelscroll.setViewportView(contactlistpanel);
		contactlistpanelscroll.setVisible(true);
		add(contactlistpanelscroll);
		chatpanel=new ChatPanel();
		chatpanel.setSize(500, 705);
		chatpanel.setLocation(330, 0);
		chatpanel.setVisible(true);
		add(chatpanel);
		chatpanel.setFromUserData(fm.f.getFacultyId()+"",fm.f.getFacultyName(), fm.f.getProfilePic());
		BufferedImage image=ImageUtil.toBufferedImage(fm.f.getProfilePic().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		profilepiclabel.setIcon(new ImageIcon(ImageUtil.makeRoundedCorner(image, 50)));
		chatinfopanel.setData(fm.f);
		chatinfopanel.setFaculty(fm.f);
		profilepiclabel.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if(e.getButton()==MouseEvent.BUTTON1)
				{
					chatinfopanel.setData(fm.f);
					chatpanel.setEmptyChatPanel();
					for(JPanel panel:contactlistpanel.contactlist)
					{
						panel.setBackground(Color.white);
						for(Component c:panel.getComponents())
						{

							if(c.getName()!=null && c.getName().equals("lastmessage"))
							{
								if(!c.getForeground().equals(Color.DARK_GRAY))
								{
									c.setForeground(Color.gray);
								}
							}
							else if(c.getName()!=null &&c.getName().equals("messagetime")&&c.getForeground().equals(new Color(30,178,170)))
							{
								
								c.setForeground(Color.gray);
							}
							else if(c.getName()!=null&&c.getName().equals("username"))
							{
								c.setForeground(Color.DARK_GRAY);
							}
							else if(c.getName()!=null && c.getName().equals("messagetime"))
							{
								c.setForeground(Color.gray);
							}
						}
					}
				}
			}
		});
	}
	public ChatMainPanel(StudentMain sm)
	{
		this();
		
		this.sm=sm;
		contactlistpanel=new ContactListPanel(sm,this);
		contactlistpanel.setBounds(0, 0, 330, 600);
		contactlistpanelscroll.setViewportView(contactlistpanel);
		contactlistpanelscroll.setVisible(true);
		add(contactlistpanelscroll);
		
		chatpanel=new ChatPanel();
		chatpanel.setSize(500, 705);
		chatpanel.setLocation(330, 0);
		chatpanel.setVisible(true);
		add(chatpanel);
		
		chatpanel.setFromUserData(sm.s.getUserId(), sm.s.getFullName(), sm.s.getProfilePic());
		BufferedImage image=ImageUtil.toBufferedImage(sm.s.getProfilePic(50, 50));
		profilepiclabel.setIcon(new ImageIcon(ImageUtil.makeRoundedCorner(image, 50)));
		chatinfopanel.setData(sm.s);
		chatinfopanel.setStudent(sm.s);
		
		profilepiclabel.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if(e.getButton()==MouseEvent.BUTTON1)
				{
					chatinfopanel.setData(sm.s);
					chatpanel.setEmptyChatPanel();
					for(JPanel panel:contactlistpanel.contactlist)
					{
						panel.setBackground(Color.white);
						for(Component c:panel.getComponents())
						{

							if(c.getName()!=null && c.getName().equals("lastmessage"))
							{
								if(!c.getForeground().equals(Color.DARK_GRAY))
								{
									c.setForeground(Color.gray);
								}
							}
							else if(c.getName()!=null &&c.getName().equals("messagetime")&&c.getForeground().equals(new Color(30,178,170)))
							{
								
								c.setForeground(Color.gray);
							}
							else if(c.getName()!=null&&c.getName().equals("username"))
							{
								c.setForeground(Color.DARK_GRAY);
							}
							else if(c.getName()!=null && c.getName().equals("messagetime"))
							{
								c.setForeground(Color.gray);
							}
						}
						
					}
				}
			}
		});
	}
	public ChatMainPanel() {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		this.setSize(1116, 705);
		setLayout(null);
		this.requestFocusInWindow(true);
		contactlistpanelscroll=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contactlistpanelscroll.setBounds(0, 60,330,645);
		contactlistpanelscroll.getVerticalScrollBar().setUnitIncrement(80);
		contactlistpanelscroll.getVerticalScrollBar().setPreferredSize(new Dimension(5,0));
		
		
		
		
		chatinfopanel=new ChatInfoPanel(); 
		chatinfopanel.setLocation(830, 0);
		chatinfopanel.setVisible(true);
		add(chatinfopanel);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new MatteBorder(1, 1, 0, 0, (Color) new Color(192, 192, 192)));
		panel.setBounds(0, 0, 330, 60);
		add(panel);
		panel.setLayout(null);
		
		searchfield = new JTextField();
		searchfield.setBorder(new MatteBorder(0,0,1,0,Color.LIGHT_GRAY));
		searchfield.setLayout(new BorderLayout());
		searchfield.setForeground(Color.DARK_GRAY);
		searchfield.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		searchfield.setBounds(75, 11, 245, 38);
		searchfield.getDocument().addDocumentListener(fielddoc);
		searchfield.setFocusable(false);
		searchfield.addMouseListener(new MouseAdapter()
				{
					public void mousePressed(MouseEvent e)
					{
						searchfield.setFocusable(true);
						searchfield.grabFocus();
					}
					
				}
				);
		searchfield.setLayout(new BorderLayout());
		hintlabel=new JLabel("Search...");
		hintlabel.setFont(searchfield.getFont());
		hintlabel.setForeground(new Color(100,100,100));
		searchfield.add(hintlabel,BorderLayout.LINE_START);
		panel.add(searchfield);
		searchfield.setColumns(10);
		
		onlinestatus = new JLabel(new ImageIcon("./assets/onlinestatus.png"));
		onlinestatus.setBounds(50, 40, 15, 15);
		panel.add(onlinestatus);
		profilepiclabel = new JLabel("");
		
		profilepiclabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		profilepiclabel.setHorizontalAlignment(SwingConstants.CENTER);
		profilepiclabel.setBounds(15, 5, 50, 50);
		panel.add(profilepiclabel);

	}
	
	private class MyDocumentListener implements DocumentListener {
	    public void insertUpdate(DocumentEvent e) {
	        updateLog(e, "inserted into");
	    }
	    public void removeUpdate(DocumentEvent e) {
	        updateLog(e, "removed from");
	    }
	    public void changedUpdate(DocumentEvent e) {
	        //Plain text components do not fire these events
	    }
	    public void updateLog(DocumentEvent e, String action)
	    {
	        Document doc = (Document)e.getDocument();
	        if(doc.getLength()==0)
	        {
	        	hintlabel.setVisible(true);
	        }
	        else
	        {
	        	hintlabel.setVisible(false);
	        }
	        try 
	        {
	        	contactlistpanel.filterContact(doc.getText(0, doc.getLength()));
			} 
	        catch (BadLocationException e1) {
				e1.printStackTrace();
			}
	    }
	   }
}

