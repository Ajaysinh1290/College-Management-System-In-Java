package collegeapplication.admin;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/*
 * Title : AdminProfilePanel.java
 * Created by : Ajaysinh Rathod
 * Purpose : All the data related to admin displaying using this panel
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class AdminProfilePanel extends JPanel {

	private JPanel panel;
	private Admin a;
	private JLabel headinglabel;

	/**
	 * Create the panel.
	 */
	
	
	public AdminProfilePanel(AdminMain am)
	{
		this();
		headinglabel.setText("Admin Profile");
		JButton editdetailsbutton = new JButton("Edit Details");
		editdetailsbutton.setFocusPainted(false);
		editdetailsbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		editdetailsbutton.setForeground(new Color(0, 139, 139));
		editdetailsbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		editdetailsbutton.setBackground(new Color(255, 255, 255));
		editdetailsbutton.setBounds(925, 139, 161, 33);
		editdetailsbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editdetailsbutton.addActionListener(e->
		{
				EditAdminDetailsDialog caad=new EditAdminDetailsDialog(a,am);
				caad.setLocationRelativeTo(null);
				caad.setVisible(true);
		});
		panel.add(editdetailsbutton);
		
		
		JButton editlinksbutton = new JButton("Edit Links");
		editlinksbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editlinksbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditAdminLinksDialog ed=new EditAdminLinksDialog(a,am);
				ed.setLocationRelativeTo(null);
				ed.setVisible(true);
			}
		});
		editlinksbutton.setForeground(new Color(0, 139, 139));
		editlinksbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		editlinksbutton.setFocusPainted(false);
		editlinksbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		editlinksbutton.setBackground(Color.WHITE);
		editlinksbutton.setBounds(754, 139, 161, 33);
		panel.add(editlinksbutton);
	}
	public AdminProfilePanel() {
		
		a=new AdminData().getAdminData();
		setBackground(new Color(255, 255, 255));
		this.setSize(1116, 705);
		setLayout(null);
		panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(10, 0, 1096, 183);
		add(panel);
		panel.setLayout(null);
		 headinglabel = new JLabel("Contact Us");
		headinglabel.setIcon(null);
		headinglabel.setBounds(10, 65, 272, 44);
		panel.add(headinglabel);
		headinglabel.setBackground(new Color(32, 178, 170));
		headinglabel.setHorizontalAlignment(SwingConstants.LEFT);
		headinglabel.setForeground(Color.WHITE);
		headinglabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		headinglabel.setOpaque(true);
		
		
		
		JLabel collagelogolabel = new JLabel();
		collagelogolabel.setHorizontalAlignment(SwingConstants.CENTER);
		collagelogolabel.setHorizontalTextPosition(SwingConstants.CENTER);
		collagelogolabel.setIcon(new ImageIcon(a.getProfilePic(200, 180)));
		collagelogolabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		collagelogolabel.setBounds(24, 213, 200, 180);
		add(collagelogolabel);
		
		JLabel lblCollageName = new JLabel("Collage Name  :  ");
		lblCollageName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCollageName.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblCollageName.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblCollageName.setBounds(233, 213, 191, 48);
		add(lblCollageName);
		
		JLabel collagenamelable = new JLabel("  "+a.getCollageName());
		collagenamelable.setToolTipText(a.getCollageName());
		collagenamelable.setHorizontalAlignment(SwingConstants.LEFT);
		collagenamelable.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		collagenamelable.setBorder(new LineBorder(Color.LIGHT_GRAY));
		collagenamelable.setBounds(423, 213, 672, 48);
		add(collagenamelable);
		
		JLabel lblEmailId = new JLabel("Email ID  :  ");
		lblEmailId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmailId.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblEmailId.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblEmailId.setBounds(233, 260, 191, 48);
		add(lblEmailId);
		
		JLabel emailidlabel = new JLabel("  "+a.getEmailId());
		emailidlabel.setToolTipText(a.getEmailId());
		emailidlabel.setHorizontalAlignment(SwingConstants.LEFT);
		emailidlabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		emailidlabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		emailidlabel.setBounds(423, 260, 672, 48);
		add(emailidlabel);
		
		JLabel lblContactNumber = new JLabel("Contact Number  :  ");
		lblContactNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContactNumber.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblContactNumber.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblContactNumber.setBounds(233, 307, 191, 48);
		add(lblContactNumber);
		
		JLabel contactnumberlabel = new JLabel("  "+a.getContactNumber());
		contactnumberlabel.setToolTipText(a.getContactNumber());
		contactnumberlabel.setHorizontalAlignment(SwingConstants.LEFT);
		contactnumberlabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		contactnumberlabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		contactnumberlabel.setBounds(423, 307, 672, 48);
		add(contactnumberlabel);
		
		JLabel lblWebsite = new JLabel("Website  :  ");
		lblWebsite.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWebsite.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblWebsite.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblWebsite.setBounds(233, 354, 191, 48);
		add(lblWebsite);
		
		JLabel websitelabel = new JLabel("<html>&nbsp <u>"+a.getWebsite()+"</u></html>");
		websitelabel.setToolTipText(a.getWebsite());
		websitelabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		websitelabel.setForeground(new Color(65, 105, 225));
		websitelabel.setHorizontalAlignment(SwingConstants.LEFT);
		websitelabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		websitelabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		websitelabel.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				websitelabel.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				openLink(a.getWebsite());
				websitelabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}	
		}
		);
		websitelabel.setBounds(423, 354, 672, 48);
		add(websitelabel);
		
		JLabel lblAddress = new JLabel("Address  :  ");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblAddress.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblAddress.setBounds(23, 401, 211, 48);
		add(lblAddress);
		
		JLabel addresslabel = new JLabel("  "+a.getAddress());
		addresslabel.setToolTipText(a.getAddress());
		addresslabel.setHorizontalAlignment(SwingConstants.LEFT);
		addresslabel.setForeground(Color.BLACK);
		addresslabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		addresslabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		addresslabel.setBounds(233, 401, 862, 48);
		add(addresslabel);
		
		JLabel lblFaceBook = new JLabel("Face Book  :  ");
		
		lblFaceBook.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFaceBook.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblFaceBook.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblFaceBook.setBounds(24, 460, 211, 48);
		add(lblFaceBook);
		
		JLabel facebooklabel = new JLabel("<html>&nbsp <u>"+a.getFacebookLink()+"</u></html>");
		facebooklabel.setToolTipText(a.getFacebookLink());
		facebooklabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		facebooklabel.setHorizontalAlignment(SwingConstants.LEFT);
		facebooklabel.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						facebooklabel.setCursor(new Cursor(Cursor.WAIT_CURSOR));
						openLink(a.getFacebookLink());
						facebooklabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
					}	
				}
				);
		
		facebooklabel.setForeground(new Color(65, 105, 225));
		facebooklabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		facebooklabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		facebooklabel.setBounds(234, 460, 862, 48);
		add(facebooklabel);
		
		JLabel lblTwitter = new JLabel("Instagram  :  ");
		lblTwitter.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTwitter.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblTwitter.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblTwitter.setBounds(24, 507, 211, 48);
		add(lblTwitter);
		
		JLabel instagramlabel = new JLabel("<html>&nbsp <u>"+a.getInstagramLink()+"</u></html>");
		instagramlabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		instagramlabel.setHorizontalAlignment(SwingConstants.LEFT);
		instagramlabel.setForeground(new Color(65, 105, 225));
		instagramlabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		instagramlabel.setToolTipText(a.getInstagramLink());
		instagramlabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		instagramlabel.setBounds(234, 507, 862, 48);
		instagramlabel.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				instagramlabel.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				openLink(a.getInstagramLink());
				instagramlabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}	
		}
		);
		add(instagramlabel);
		
		JLabel label_1 = new JLabel("Twitter  :  ");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		label_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		label_1.setBounds(24, 554, 211, 48);
		add(label_1);
		
		JLabel twitterlabel = new JLabel("<html>&nbsp <u>"+a.getTwitterLink()+"</u></html>");
		twitterlabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		twitterlabel.setHorizontalAlignment(SwingConstants.LEFT);
		twitterlabel.setForeground(new Color(65, 105, 225));
		twitterlabel.setToolTipText(a.getTwitterLink());
		twitterlabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		twitterlabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		twitterlabel.setBounds(234, 554, 862, 48);
		twitterlabel.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				twitterlabel.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				openLink(a.getTwitterLink());
				twitterlabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}	
		}
		);
		add(twitterlabel);
		
		JLabel lblLinkedin = new JLabel("LinkedIn  :  ");
		lblLinkedin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLinkedin.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblLinkedin.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblLinkedin.setBounds(24, 601, 211, 48);
		add(lblLinkedin);
		
		JLabel linkedinlabel = new JLabel("<html>&nbsp <u>"+a.getLinkedinLink()+"</u></html>");
		linkedinlabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		linkedinlabel.setHorizontalAlignment(SwingConstants.LEFT);
		linkedinlabel.setForeground(new Color(65, 105, 225));
		linkedinlabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		linkedinlabel.setToolTipText(a.getLinkedinLink());
		linkedinlabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		linkedinlabel.setBounds(234, 601, 862, 48);
		linkedinlabel.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				linkedinlabel.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				openLink(a.getLinkedinLink());
				linkedinlabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}	
		}
		);
		add(linkedinlabel);
	}
	
	public void openLink(String link)
	{
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		Desktop desktop=Desktop.getDesktop();
		try {
			desktop.browse(URI.create(link));
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		}
	}
}
