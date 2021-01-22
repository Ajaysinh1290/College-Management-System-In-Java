package collegeapplication.admin;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dialog;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;

/*
 * Title : EditAdminLinksDialog.java
 * Created by : Ajaysinh Rathod
 * Purpose : With the help of this file admin can edit the social media links
 * Mail : ajaysinhrathod1290@gmail.com
 */
@SuppressWarnings("serial")
public class EditAdminLinksDialog extends JDialog {

	private static EditAdminLinksDialog dialog;
	private final JPanel contentPanel = new JPanel();
	private JTextField facebookfield;
	private JTextField instagramfield;
	private JTextField twitterfield;
	private JTextField linkedinfield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new EditAdminLinksDialog(null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditAdminLinksDialog(Admin a,AdminMain am) {
		
		super(dialog,"",Dialog.ModalityType.APPLICATION_MODAL);
		dialog=this;
		setResizable(false);
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 568, 342);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit Social Media Links");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(32, 178, 170));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 552, 39);
		getContentPane().add(lblNewLabel);
		
		JLabel lblFaceBook = new JLabel("Face Book  :");
		lblFaceBook.setBorder(new EmptyBorder(0, 0, 0, 0));
		lblFaceBook.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblFaceBook.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFaceBook.setBounds(10, 50, 125, 39);
		getContentPane().add(lblFaceBook);
		
		facebookfield = new JTextField(a.getFacebookLink());
		facebookfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		facebookfield.setBounds(145, 50, 397, 39);
		getContentPane().add(facebookfield);
		facebookfield.setColumns(10);
		
		JLabel lblInstagram = new JLabel("Instagram  :");
		lblInstagram.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInstagram.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblInstagram.setBorder(new EmptyBorder(0, 0, 0, 0));
		lblInstagram.setBounds(10, 97, 125, 39);
		getContentPane().add(lblInstagram);
		
		JLabel lblTwitter = new JLabel("Twitter  :");
		lblTwitter.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTwitter.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblTwitter.setBorder(new EmptyBorder(0, 0, 0, 0));
		lblTwitter.setBounds(10, 144, 125, 39);
		getContentPane().add(lblTwitter);
		
		JLabel lblLinkedin = new JLabel("LinkedIn  :");
		lblLinkedin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLinkedin.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblLinkedin.setBorder(new EmptyBorder(0, 0, 0, 0));
		lblLinkedin.setBounds(10, 192, 125, 39);
		getContentPane().add(lblLinkedin);
		
		instagramfield = new JTextField(a.getInstagramLink());
		instagramfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		instagramfield.setColumns(10);
		instagramfield.setBounds(145, 97, 397, 39);
		getContentPane().add(instagramfield);
		
		twitterfield = new JTextField(a.getTwitterLink());
		twitterfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		twitterfield.setColumns(10);
		twitterfield.setBounds(145, 144, 397, 39);
		getContentPane().add(twitterfield);
		
		linkedinfield = new JTextField(a.getLinkedinLink());
		linkedinfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		linkedinfield.setColumns(10);
		linkedinfield.setBounds(145, 195, 397, 39);
		getContentPane().add(linkedinfield);
		
		JButton updatelinksbutton = new JButton("Update Links");
		updatelinksbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		updatelinksbutton.setForeground(new Color(255, 255, 255));
		updatelinksbutton.setBackground(new Color(32, 178, 170));
		updatelinksbutton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		updatelinksbutton.setBounds(403, 269, 139, 35);
		updatelinksbutton.setFocusPainted(false);
		updatelinksbutton.addActionListener(e->
		{
			Admin ad=new Admin();
			ad.setFaceBookLink(facebookfield.getText());
			ad.setInstagramLink(instagramfield.getText());
			ad.setTwitterLink(twitterfield.getText());
			ad.setLinkedinLink(linkedinfield.getText());
			int result=new AdminData().updateAdminLink(ad);
			if(result>0)
			{
				am.adminprofilepanel.setVisible(false);
				am.adminprofilepanel=new AdminProfilePanel(am);
				am.adminprofilepanel.setLocation(am.panelx, am.panely);
				am.adminprofilepanel.setVisible(true);
				am.contentPane.add(am.adminprofilepanel);
				dialog.dispose();
			
			}
		});
		getContentPane().add(updatelinksbutton);
		
		JLabel label = new JLabel("");
		label.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(192, 192, 192)));
		label.setBounds(0, 242, 562, 14);
		getContentPane().add(label);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
	}
}
