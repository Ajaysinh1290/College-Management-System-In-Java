package collegeapplication.chat;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import collegeapplication.admin.AdminData;
import collegeapplication.common.ScrollPaneUtil;
import collegeapplication.common.TimeUtil;
import collegeapplication.faculty.FacultyData;
import collegeapplication.student.StudentData;


/*
 * Title : SubChatPanel.java ** Part of chat panel
 * Created by : Ajaysinh Rathod
 * Purpose : All the messages which user sending and receiving are handling here 
 * Mail : ajaysinhrathod1290@gmail.com
 */
@SuppressWarnings("serial")
public class SubChatPanel extends JPanel implements ActionListener,Runnable {

	Box vertical = Box.createVerticalBox();
	private JTextField messagefield;
	private ChatUser user=null;
	private ObjectInputStream reader;
	public Socket socket;
	private ObjectOutputStream writer;
	private JPanel chat;
	private JScrollPane chatscroll;
	public JButton send;
	private String date="";
	private JLabel progresslabel;
	private JLabel hintlabel;
	public ArrayList<MessageStatus> messagestatus=new ArrayList<MessageStatus>(); 
			

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubChatPanel frame = new SubChatPanel(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	
	public SubChatPanel(ChatUser user) {
		
		
		
		setLayout(null);
		UIManager.put("ScrollBarUI", "com.sun.java.swing.plaf.windows.WindowsScrollBarUI");
		setBounds(100, 100, 498, 640);
		this.setBorder(new EmptyBorder(0,0,0,0));
		chatscroll = new JScrollPane();
		chatscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		chatscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chatscroll.setBounds(0, 0, this.getWidth(), this.getHeight()-59);
		chatscroll.setAutoscrolls(true);
		chatscroll.setBorder(this.getBorder());
		chatscroll.setOpaque(false);
		chatscroll.getViewport().setOpaque(false);
		chatscroll.getVerticalScrollBar().setPreferredSize(new Dimension(5,0));
		chatscroll.getVerticalScrollBar().setUnitIncrement(80);
		add(chatscroll);

		chat = new JPanel();
		chatscroll.setViewportView(chat);;
		chat.setLayout(new BorderLayout());
		chat.setBounds(0, 70, 405,351);
		chat.setOpaque(false);
		JPanel bottonpanel = new JPanel()
				{
					protected void paintComponent(Graphics g)
				    {
				        g.setColor( getBackground() );
				        g.fillRect(0, 0, getWidth(), getHeight());
				        super.paintComponent(g);
				    }
				};
		bottonpanel.setBounds(0, this.getHeight()-54, this.getWidth(), 60);
		bottonpanel.setBackground(new Color(255,255,255));
		add(bottonpanel);
		bottonpanel.setLayout(null);
		
		messagefield = new JTextField() {
			  @Override protected void paintComponent(Graphics g) {
				    if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
				      Graphics2D g2 = (Graphics2D) g.create();
				      g2.setPaint(getBackground());
				      g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
				          0, 0, getWidth() - 1, getHeight() - 1));
				      g2.dispose();
				    }
				    super.paintComponent(g);
				  }
				  @Override public void updateUI() {
				    super.updateUI();
				    setOpaque(false);
				    setBorder(new RoundedCornerBorder());
				  }
				};
				messagefield.setLayout(new BorderLayout());
				messagefield.setBounds(5, 10, bottonpanel.getWidth()-60, 36);
				messagefield.setFocusable(true);
				messagefield.getDocument().addDocumentListener(new MyDocumentListener());
				messagefield.addMouseListener(new MouseAdapter()
						{
					
								public void mousePressed(MouseEvent e)
								{
									messagefield.grabFocus();
									messagefield.setFocusable(true);
								}
						});
	
		bottonpanel.add(messagefield);
		messagefield.setForeground(Color.DARK_GRAY);
		messagefield.setBackground(new Color(255,255,255));
		messagefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
		messagefield.setColumns(10);
		hintlabel=new JLabel("Write a message...");
		hintlabel.setFont(messagefield.getFont());
		hintlabel.setForeground(new Color(100,100,100));
		messagefield.add(hintlabel,BorderLayout.LINE_START);
		
		
		send = new JButton(new ImageIcon("./assets/sendmessage.png"));
		send.setFocusPainted(false);
		send.setFont(new Font("Segoe UI", Font.BOLD, 15));
		send.setBorder(this.getBorder());
		send.setBounds(bottonpanel.getWidth()-40, 10, 30, 36);
		send.addActionListener(this);
		send.setBackground(new Color(255,255,255));
		send.setContentAreaFilled(false);
		bottonpanel.add(send);
		
		progresslabel=new JLabel("Loading...");
		progresslabel.setSize(100,38);
		progresslabel.setBackground(new Color(255,255,255,120));
		progresslabel.setOpaque(true);
		progresslabel.setVisible(false);
		progresslabel.setHorizontalAlignment(JLabel.CENTER);
		progresslabel.setLocation(200, 31);
		add(progresslabel);
		
		JLabel label =new JLabel(new ImageIcon("./assets/chatbg.jpg"));
		label.setBounds(0,0,this.getWidth(),this.getHeight());
		add(label);
		this.user=user;
		
		
		getMessages();
		this.setVisible(true);;
		
		Timer timer=new Timer(1000,new MessageStatusActionListener());
		timer.start();
		try 
		{
			socket=new Socket("localhost",Server.port);
			writer=new ObjectOutputStream(socket.getOutputStream());
			reader=new ObjectInputStream(socket.getInputStream());
		} 
		catch(ConnectException e)
		{
			JOptionPane.showMessageDialog(null,"Enable to connect with the server","Error",JOptionPane.ERROR_MESSAGE);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void getMessages()
	{
			ArrayList<ChatUser> list=new ChatData().getUserMessages(user);
			ArrayList<Integer> readbylist=new ArrayList<Integer>();
			if(list.size()>100)
			{
				progresslabel.setVisible(true);
			}
			this.repaint();
			EventQueue.invokeLater(new Runnable()
			{
				@Override
				public void run() 
				{
					// TODO Auto-generated method stub
					for(int i=0; i<list.size(); i++)
					{
						if(i==list.size()-1)
						{
							progresslabel.setVisible(false);
						}
						ChatUser u=list.get(i);
						if(u.getFromUserId().equals(user.getFromUserId())&&u.getToUserId().equals(user.getToUserId()))
						{
							RightSidePanel(u);
						}
						else if(u.getToUserId().equals(user.getFromUserId())&&u.getFromUserId().equals(user.getToUserId()))
						{
							
							if(u.getReadBy().isEmpty())	
							{
								readbylist.add(u.getSr_no());
							}
							else
							{
								
								StringTokenizer read=new StringTokenizer(u.getReadBy(),"#");
								boolean contain=false;
								while(read.hasMoreTokens())
								{
									String str=read.nextToken();
									if(str.equals(user.getFromUserId()))
									{
										
										contain=true;
										break;
									}
									
								}
								if(!contain)
								{
									readbylist.add(u.getSr_no());
								}
								
							}
							LeftSidePanel(u);
						}
						else if(u.getToUserId().equals(user.getToUserId())&&u.getToUserId().contains("Group"))
						{
							if(u.getReadBy().isEmpty())	
							{
								readbylist.add(u.getSr_no());

							}
							else
							{
								StringTokenizer read=new StringTokenizer(u.getReadBy(),"#");
								boolean contain=false;
								while(read.hasMoreTokens())
								{
									String str=read.nextToken();
									if(str.equals(user.getFromUserId()))
									{
										contain=true;
										break;
									}
									
								}
								if(!contain)
								{
									readbylist.add(u.getSr_no());
								}
							}
							LeftSidePanel(u);
						}
					}
					if(readbylist.size()>0)
					{
					new ChatData().addReadBy(readbylist,user.getFromUserId());
					}
				}
				
			});
						
			
		
	}
	public  void sendmessage()
	{
		if(socket!=null)
		{
			try
			{
			
				String sendmessage=messagefield.getText();
				sendmessage=sendmessage.trim();
				if(!sendmessage.isEmpty())
				{
				Date date=new Date();
				this.user.setMessage(sendmessage,date);
				user.setSr_no(new ChatData().getNewSr_no());
				new ChatData().saveMessage(this.user);
				writer.writeObject(this.user);
				writer.reset();
				}
			}
			catch(SocketException exp)
			{
				JOptionPane.showMessageDialog(null,"Enable to connect with the server","Error",JOptionPane.ERROR_MESSAGE);
	
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
			messagefield.setText("");
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		sendmessage();
		
	}
	
	public void RightSidePanel(ChatUser u)
	{

		JPanel panel=formatPanel(u,true);
		
		JPanel right=new JPanel(new BorderLayout());
		right.setBorder(new EmptyBorder(5,0,5,0));
		right.add(panel,BorderLayout.LINE_END);
		right.setBackground(new Color(0,0,0,0));
		right.setBorder(new EmptyBorder(5,5,0,5));
		right.setName(u.getSr_no()+"");
		
		vertical.add(right);
		chat.add(vertical,BorderLayout.PAGE_START);
		ScrollPaneUtil.scrollToBottom(chatscroll);
		this.repaint();
		this.revalidate();
	}
	
	public void	LeftSidePanel(ChatUser u)
	{
		JPanel panel=formatPanel(u,false);	
		JPanel right=new JPanel(new BorderLayout());
		right.add(panel,BorderLayout.LINE_START);
		right.setBackground(new Color(255,255,255,0));
		right.setBorder(new EmptyBorder(5,5,0,5));
		right.setName(u.getSr_no()+"");
		vertical.add(right);
		chat.add(vertical,BorderLayout.PAGE_START);
		ScrollPaneUtil.scrollToBottom(chatscroll);
		this.repaint();
		this.revalidate();
		
	}
	public void addLabel(String text)
	{
	
		JPanel p=new JPanel();
		p.setLayout(new FlowLayout());
		p.setBackground(new Color(240,240,240,0));
		p.setBorder(new EmptyBorder(5,0,5,0));
		p.setOpaque(true);
		
		JLabel specialnote=new JLabel();
		if(text.length()>40)
		{
			specialnote.setText("<html><p style=\"width:150px\">"+text+"</p></html>");
		}
		else
		{
			specialnote.setText(text);
		}
		specialnote.setBorder(new EmptyBorder(2,20,3,20));
		specialnote.setBackground(new Color(240,240,240,200));
		specialnote.setForeground(Color.black);
		specialnote.setOpaque(true);
		specialnote.setFont(new Font("Segoe UI",Font.PLAIN,15));
		p.add(specialnote);
		vertical.add(p);
		chat.add(vertical,BorderLayout.PAGE_START);
		ScrollPaneUtil.scrollToBottom(chatscroll);
		this.repaint();
		this.revalidate();
	}
	
	public JPanel formatPanel(ChatUser u,boolean from)
	{
		//Creating panel of message
		if(!u.getMessageDate().equals(date))
		{
			int diff=TimeUtil.getDayDifference(u.getMessageDate(),NewMessage.getCurrentDate());
			if(diff==0)
			{
				addLabel("TODAY");
			}
			else if(diff==1) 
			{
				addLabel("YESTERDAY");
			}
			else
			{
			addLabel(u.getMessageDate());
			}
			date=u.getMessageDate();
		}
		JPanel p=new JPanel();
		p.setBorder(new EmptyBorder(5,0,5,0));
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
		p.setBackground(new Color(240,240,240));
		
		
		//Adding username to panel
		JLabel username=new JLabel();
		if(!from)
		{
			if(user.getToUserId().contains("Group"))
			{
			username.setText(u.getFromUserName());
			}
			
		}
		username.setHorizontalAlignment(JLabel.LEFT);
		username.setBorder(new EmptyBorder(0,0,5,20));
		username.setForeground(new Color(0,139,139));
		username.setFont(new Font("Segoe UI",Font.BOLD,14));
		p.add(username);
		
		//Adding message to panel
		JLabel messagelabel=new JLabel();
		messagelabel.setFont(new Font("Segoe UI Emoji",Font.PLAIN,16));
		messagelabel.setHorizontalTextPosition(JLabel.CENTER);
		messagelabel.setVerticalTextPosition(JLabel.BOTTOM);
		if(u.getMessage().length()<40)
		{
		messagelabel.setText(u.getMessage());
		}
		else
		{
			messagelabel.setText("<html><p style=\"width:150px\">"+u.getMessage()+"</p></html>");
		}
		messagelabel.setForeground(Color.black);
		messagelabel.setBorder(new EmptyBorder(0,0,2,20));
		p.add(messagelabel);
		
		//Adding time to panel
		
		//Creating panel of time
		JPanel timepanel=new JPanel(new BorderLayout());
		JLabel timelabel=new JLabel(u.getMessageTime());
		timepanel.setBackground(p.getBackground());
		timelabel.setFont(new Font("Segoe UI",Font.PLAIN,10));
		timelabel.setForeground(Color.gray);
		timepanel.add(timelabel,BorderLayout.LINE_END);
		timepanel.setBorder(new EmptyBorder(2,0,0,2));
		timelabel.setHorizontalAlignment(JLabel.CENTER);
		p.add(timepanel);
		if(from)
		{
			p.setBackground(new Color(20,130,130));
			messagelabel.setForeground(Color.white);
			timelabel.setForeground(new Color(245,245,245));
			timepanel.setBackground(p.getBackground());
			timelabel.setIcon(new ImageIcon("./assets/singletick.png"));
			timelabel.setVerticalTextPosition(JLabel.CENTER);
			timepanel.setOpaque(false);
			timelabel.setHorizontalTextPosition(JLabel.LEFT);
			timelabel.setBorder(new EmptyBorder(0,0,0,3));
			if(!user.getToUserId().contains("Group"))
			{
				
				StringTokenizer read=new StringTokenizer(u.getReadBy(),"#");
				boolean contain=false;
				while(read.hasMoreTokens())
				{
					String str=read.nextToken();
					if(str.equals(user.getToUserId()))
					{
						
						contain=true;
						break;
					}
					
				}
				if(contain)
				{
					timelabel.setIcon(new ImageIcon("./assets/doubletickblue.png"));
				}
				else
				{
					messagestatus.add(new MessageStatus(u,timelabel));
					if(user.getUserProfile().equals("Student"))
					{
						if(new StudentData().isActive(user.getToUserId()))
						{
							timelabel.setIcon(new ImageIcon("./assets/doubletick.png"));
						}
					}
					else if(user.getUserProfile().equals("Faculty"))
					{
						if(new FacultyData().isActive(user.getToUserId()))
						{
							timelabel.setIcon(new ImageIcon("./assets/doubletick.png"));
						}
					}
					else if(user.getUserProfile().equals("Admin"))
					{
						if(new AdminData().isActive())
						{
							timelabel.setIcon(new ImageIcon("./assets/doubletick.png"));
						}
					}
				}
			}
			
				
		}

		
		return p;
	}

	@Override
	public void run() 
	{
		
		try
		{
					ChatUser u=null;
					while(socket!=null&&!socket.isClosed()&&(u=(ChatUser)reader.readObject())!=null)
					{
						if(u.getFromUserId().equals(user.getFromUserId())&&u.getToUserId().equals(user.getToUserId()))
						{
							this.RightSidePanel(u);
						}
						else if(u.getToUserId().equals(user.getFromUserId())&&u.getFromUserId().equals(user.getToUserId()))
						{
							new ChatData().readBy(u.getSr_no(), u.getReadBy()+user.getFromUserId()+"#");
							this.LeftSidePanel(u);
						}
						else if(u.getToUserId().equals(user.getToUserId())&&u.getToUserId().contains("Group"))
						{
							new ChatData().readBy(u.getSr_no(), u.getReadBy()+user.getFromUserId()+"#");
							this.LeftSidePanel(u);
						}
						
					}
					
		}
		catch(SocketException exp)
		{
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	class MessageStatusActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			for(int i=0; i<messagestatus.size(); i++)
			{
					MessageStatus m=messagestatus.get(i);

					StringTokenizer read=new StringTokenizer(new ChatData().getReadBy(m.getSrNo()),"#");
					boolean contain=false;
					while(read.hasMoreTokens())
					{
						
						String str=read.nextToken();
						if(str.equals(user.getToUserId()))
						{
							contain=true;
							break;
						}
						
					}
					if(contain)
					{
							JLabel label=m.getLabel();
							label.setIcon(new ImageIcon("./assets/doubletickblue.png"));
							m.setMessageStatus(true);
					}
					if(!contain)
					{
						if(user.getUserProfile().equals("Student"))
						{
							if(new StudentData().isActive(user.getToUserId()))
							{
								JLabel label=m.getLabel();
								label.setIcon(new ImageIcon("./assets/doubletick.png"));
							}
						}
						else if(user.getUserProfile().equals("Faculty"))
						{
							if(new FacultyData().isActive(user.getToUserId()))
							{
								JLabel label=m.getLabel();
								label.setIcon(new ImageIcon("./assets/doubletick.png"));
							}
						}
						else if(user.getUserProfile().equals("Admin"))
						{
							if(new AdminData().isActive())
							{
								JLabel label=m.getLabel();
								label.setIcon(new ImageIcon("./assets/doubletick.png"));
							}
						}
					}
					
			}
		}
	}
			
	private class MyDocumentListener implements DocumentListener 
	{
	    public void insertUpdate(DocumentEvent e)
	    {
	        updateLog(e, "inserted into");
	    }
	    public void removeUpdate(DocumentEvent e) 
	    {
	        updateLog(e, "removed from");
	    }
	    public void changedUpdate(DocumentEvent e) 
	    {
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
	    }
	}
}

@SuppressWarnings("serial")
class RoundedCornerBorder extends AbstractBorder
{
	  private static final Color ALPHA_ZERO = new Color(0x0, true);
	  @Override
	  public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) 
	  {
	    Graphics2D g2 = (Graphics2D) g.create();
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    Shape border = getBorderShape(x, y, width - 1, height - 1);
	    g2.setPaint(ALPHA_ZERO);
	    Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
	    corner.subtract(new Area(border));
	    g2.fill(corner);
	    g2.setPaint(Color.GRAY);
	    g2.draw(border);
	    g2.dispose();
	  }
	  
	  public Shape getBorderShape(int x, int y, int w, int h) 
	  {
	    int r = h; //h / 2;
	    return new RoundRectangle2D.Double(x, y, w, h, r, r);
	  }
	  
	  @Override 
	  public Insets getBorderInsets(Component c) 
	  {
	    return new Insets(4, 8, 4, 8);
	  }
	  
	  @Override public Insets getBorderInsets(Component c, Insets insets) 
	  {
	    insets.set(4, 8, 4, 8);
	    return insets;
	  }
	  
}
class MessageStatus
{
	private int sr_no=-1;
	private boolean status=false;
	private JLabel label;
	public MessageStatus(ChatUser u,JLabel label)
	{
		sr_no=u.getSr_no();
		this.label=label;
	}
	public void setMessageStatus(boolean status)
	{
		this.status=status;
	}
	public int getSrNo()
	{
		return sr_no;
	}
	public boolean getMessageStatus()
	{
		return status;
	}
	public JLabel getLabel()
	{
		return label;
	}
	
}

