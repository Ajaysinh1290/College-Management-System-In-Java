package collegeapplication.chat;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import collegeapplication.common.ImageUtil;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.SwingConstants;
import java.awt.Cursor;

/*
 * Title : ChatPanel.java
 * Created by : Ajaysinh Rathod
 * Purpose : Chat Panel top bar and subsubchatpanel
 * 			 Top bar : Display User name at top bar and handle the sub chat panel
 * 		   	 Sub chat panel : All the messages which user sending and receiving are display here
 * Mail : ajaysinhrathod1290@gmail.com
 */
@SuppressWarnings("serial")
public class ChatPanel extends JPanel
{

	private JLabel bottomlabel;
	private JLabel contactnamelabel;
	private JLabel profilepiclabel;
	public ChatUser user;
	private JPanel contactinfopanel;
	private JLabel selectchatlabel;
	private JLabel onlinestatus;
	private Thread thread;
	public SubChatPanel subchatpanel;


	/**
	 * Create the panel.
	 */
	public ChatPanel() 
	{
			
		
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setBorder(new LineBorder(Color.LIGHT_GRAY));
			
		setSize(500,705);
		setBackground(Color.white);
		setLayout(null);
		
		contactinfopanel = new JPanel();
		contactinfopanel.setBackground(Color.WHITE);
		contactinfopanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		contactinfopanel.setBounds(0, 0, 500, 60);
		add(contactinfopanel);
		contactinfopanel.setLayout(null);
		contactinfopanel.setVisible(false);
		
		onlinestatus = new JLabel(new ImageIcon("./assets/onlinestatus.png"));
		onlinestatus.setBounds(45,40, 15, 15);
		onlinestatus.setVisible(false);
		contactinfopanel.add(onlinestatus);
		
		profilepiclabel = new JLabel("");
		profilepiclabel.setBounds(10, 5, 50, 50);
		contactinfopanel.add(profilepiclabel);
		
		contactnamelabel = new JLabel("IT-sem-3 Group");
		contactnamelabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		contactnamelabel.setBounds(70, 5, 420, 22);
		contactinfopanel.add(contactnamelabel);
		
		 bottomlabel = new JLabel("33 Members online");
		bottomlabel.setForeground(Color.GRAY);
		bottomlabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		bottomlabel.setBounds(70, 27, 420, 22);
		contactinfopanel.add(bottomlabel);
		
		
		selectchatlabel=new JLabel("");
		selectchatlabel.setHorizontalAlignment(SwingConstants.CENTER);
		try 
		{
			
			Image image= ImageIO.read(new File("./assets/selectchaticon.png"));
			selectchatlabel.setIcon(new ImageIcon(image.getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		selectchatlabel.setText("Please select a chat to start messaging");
		selectchatlabel.setVerticalTextPosition(JLabel.BOTTOM);
		selectchatlabel.setBorder(null);
		selectchatlabel.setBackground(new Color(245, 245, 245));
		selectchatlabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		selectchatlabel.setHorizontalTextPosition(JLabel.CENTER);
		selectchatlabel.setBounds(10, 170, 480, 321);
		add(selectchatlabel);
		
		user=new ChatUser();
		
	}
	@SuppressWarnings("deprecation")
	public void setToUserData(String userprofile,String touserid,String tousername,Image tousericon,String lastlogin,boolean isactive)
	{
		
		if(user.getToUserId()!=null && user.getToUserId().equals(touserid))
		{
			onlinestatus.setVisible(false);
			bottomlabel.setForeground(Color.GRAY);
			bottomlabel.setText(lastlogin);
			if(isactive)
			{
				
				onlinestatus.setVisible(true);
				bottomlabel.setForeground(new Color(10,200,19));
				bottomlabel.setText("Online");
			}
			if(subchatpanel!=null)
			{
				subchatpanel.repaint();
			}
		}
		else
		{
			if(subchatpanel!=null)
			{
				subchatpanel.setVisible(false);
				try 
				{
					if(subchatpanel.socket!=null)
					{
						subchatpanel.socket.close();
					}
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				thread.stop();
			}
			contactinfopanel.setVisible(true);
			selectchatlabel.setVisible(false);
			user.setToUser(userprofile,touserid, tousername, isactive);
			bottomlabel.setForeground(Color.GRAY);
			bottomlabel.setText(lastlogin);
			contactnamelabel.setText(tousername);
			BufferedImage image=ImageUtil.toBufferedImage(tousericon.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
			profilepiclabel.setIcon(new ImageIcon(ImageUtil.makeRoundedCorner(image, 50)));
			onlinestatus.setVisible(false);
			if(isactive)
			{
				onlinestatus.setVisible(true);
				bottomlabel.setForeground(new Color(10,200,19));
				bottomlabel.setText("Online");
			}
			subchatpanel=new SubChatPanel(user);
			subchatpanel.setBounds(1, 61,this.getWidth()-2,this.getHeight());
			add(subchatpanel);
			subchatpanel.repaint();
			subchatpanel.setVisible(true);
			subchatpanel.setFocusable(true);
			thread=new Thread(subchatpanel);
			thread.start();
			this.getRootPane().setDefaultButton(subchatpanel.send);

		}			
	}
	public void setFromUserData(String fromuserid,String fromusername,Image fromusericon)
	{
			this.user.setFromUser(fromuserid, fromusername);
			
	}
	@SuppressWarnings("deprecation")
	public void setEmptyChatPanel()
	{
		user.setToUser("","", "", false);
		onlinestatus.setVisible(false);
		contactinfopanel.setVisible(false);
		selectchatlabel.setVisible(true);
		if(subchatpanel!=null)
		{
			subchatpanel.setVisible(false);
			try 
			{
				if(subchatpanel.socket!=null)
				{
					subchatpanel.socket.close();
				}
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
			thread.stop();
		}
	}
}
