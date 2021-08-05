package collegeapplication.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/*
 * Title : EditAdminDetailsDialog.java
 * Created by : Ajaysinh Rathod
 * Purpose : With the help of this file admin can edit collage details
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class EditAdminDetailsDialog extends JDialog implements ActionListener {

	private static EditAdminDetailsDialog dialog;
	private final JPanel contentPanel = new JPanel();
	private JTextField collagenamefield;
	private JTextField emailidfield;
	private JTextField contactnumberfield;
	private JTextField websitefield;
	private JPasswordField passwordfield;
	private File file;
	private String imagepath = null;
	private JButton choosefilebutton;
	private JTextArea addresstextarea;
	private JButton createaccountbutton;
	private JLabel Errorlabel;
	private JScrollPane scrollpaneforaddress;
	Admin a;
	AdminMain am;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new EditAdminDetailsDialog(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditAdminDetailsDialog(Admin a, AdminMain am) {

		super(dialog, "", Dialog.ModalityType.APPLICATION_MODAL);
		this.am = am;
		this.a = a;
		setResizable(false);
		setSize(570, 675);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblCreateAdminProfile = new JLabel("Edit Admin Profile ");
		lblCreateAdminProfile.setOpaque(true);
		lblCreateAdminProfile.setForeground(new Color(255, 255, 255));
		lblCreateAdminProfile.setBackground(new Color(32, 178, 170));
		lblCreateAdminProfile.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblCreateAdminProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateAdminProfile.setBounds(0, 0, 564, 58);
		contentPanel.add(lblCreateAdminProfile);

		JLabel lblCollageName = new JLabel("Collage Name   :");
		lblCollageName.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblCollageName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCollageName.setBounds(10, 90, 146, 35);
		contentPanel.add(lblCollageName);

		collagenamefield = new JTextField(a.getCollageName());
		collagenamefield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		collagenamefield.setBounds(166, 88, 388, 38);
		contentPanel.add(collagenamefield);
		collagenamefield.setColumns(10);

		JLabel lblAdress = new JLabel("Email ID  :");
		lblAdress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdress.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblAdress.setBounds(10, 147, 146, 35);
		contentPanel.add(lblAdress);

		emailidfield = new JTextField(a.getEmailId());
		emailidfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		emailidfield.setColumns(10);
		emailidfield.setBounds(166, 145, 388, 38);
		contentPanel.add(emailidfield);

		JLabel lblContactNumber = new JLabel("Contact Number  :");
		lblContactNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContactNumber.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblContactNumber.setBounds(10, 208, 146, 35);
		contentPanel.add(lblContactNumber);

		contactnumberfield = new JTextField(a.getContactNumber());
		contactnumberfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		contactnumberfield.setColumns(10);
		contactnumberfield.setBounds(166, 205, 388, 38);
		contentPanel.add(contactnumberfield);

		JLabel lblWebsite = new JLabel("Website  :");
		lblWebsite.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWebsite.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblWebsite.setBounds(10, 268, 146, 35);
		contentPanel.add(lblWebsite);

		websitefield = new JTextField(a.getWebsite());
		websitefield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		websitefield.setColumns(10);
		websitefield.setBounds(166, 265, 388, 38);
		contentPanel.add(websitefield);

		JLabel lblPassword = new JLabel("Password  :");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblPassword.setBounds(10, 327, 146, 35);
		contentPanel.add(lblPassword);

		passwordfield = new JPasswordField(a.getPassword());
		passwordfield.setBorder(new LineBorder(Color.GRAY));
		passwordfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		passwordfield.setBounds(166, 328, 313, 38);

		contentPanel.add(passwordfield);

		JButton showandhidebutton = new JButton("show");
		showandhidebutton.setForeground(new Color(255, 255, 255));
		showandhidebutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		showandhidebutton.setFocusable(false);
		showandhidebutton.setFocusPainted(false);
		showandhidebutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		showandhidebutton.setBackground(new Color(32, 178, 170));
		showandhidebutton.setBounds(483, 328, 71, 38);
		showandhidebutton.addActionListener(e -> {
			if (showandhidebutton.getText().equals("show")) {
				passwordfield.setEchoChar('\u0000');
				showandhidebutton.setText("hide");
			} else {
				passwordfield.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
				showandhidebutton.setText("show");
			}
		});
		contentPanel.add(showandhidebutton);

		JLabel lblAddress = new JLabel("Address  :");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblAddress.setBounds(10, 383, 146, 35);
		contentPanel.add(lblAddress);

		scrollpaneforaddress = new JScrollPane();
		scrollpaneforaddress.setBorder(new LineBorder(new Color(130, 135, 144)));
		scrollpaneforaddress.setBounds(166, 387, 388, 58);
		contentPanel.add(scrollpaneforaddress);

		addresstextarea = new JTextArea(a.getAddress());
		scrollpaneforaddress.setViewportView(addresstextarea);
		addresstextarea.setLineWrap(true);
		addresstextarea.setBorder(new EmptyBorder(0, 0, 0, 0));
		addresstextarea.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		addresstextarea.setRows(2);

		JLabel logoimagelabel = new JLabel("");
		logoimagelabel.setToolTipText("Logo");
		logoimagelabel.setBorder(new LineBorder(Color.GRAY));
		logoimagelabel.setIcon(new ImageIcon(a.getProfilePic(120, 120)));
		logoimagelabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoimagelabel.setBounds(21, 480, 120, 120);
		contentPanel.add(logoimagelabel);

		JLabel lblLogo = new JLabel("Collage logo");
		lblLogo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLogo.setBounds(166, 480, 111, 24);
		contentPanel.add(lblLogo);

		JLabel filesizelabel = new JLabel("127 KB");
		filesizelabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		filesizelabel.setBounds(269, 480, 232, 24);
		contentPanel.add(filesizelabel);

		choosefilebutton = new JButton("Choose Logo");
		choosefilebutton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		choosefilebutton.setForeground(Color.BLACK);
		choosefilebutton.addActionListener(this);
		choosefilebutton.setFocusable(false);
		choosefilebutton.setBackground(new Color(245, 245, 245));
		choosefilebutton.setBounds(166, 520, 137, 35);
		choosefilebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPanel.add(choosefilebutton);

		JLabel filenamelabel = new JLabel("No File Choosen");
		filenamelabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		filenamelabel.setBounds(308, 525, 246, 24);
		contentPanel.add(filenamelabel);

		JLabel filesizenote = new JLabel("Imagesize < 1024 KB");
		filesizenote.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		filesizenote.setBounds(166, 566, 246, 24);
		contentPanel.add(filesizenote);

		createaccountbutton = new JButton("Update Details");
		createaccountbutton.setFocusPainted(false);
		createaccountbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		createaccountbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		createaccountbutton.setForeground(new Color(255, 255, 255));
		createaccountbutton.setBackground(new Color(32, 178, 170));
		createaccountbutton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		createaccountbutton.setBounds(417, 600, 139, 37);
		createaccountbutton.addActionListener(this);
		contentPanel.add(createaccountbutton);

		Errorlabel = new JLabel("This is required question  !");
		Errorlabel.setVisible(false);
		Errorlabel.setForeground(Color.RED);
		Errorlabel.setFont(new Font("Arial", Font.PLAIN, 14));
		Errorlabel.setBounds(233, 45, 225, 17);
		contentPanel.add(Errorlabel);

		choosefilebutton.addActionListener(e -> {
			FileDialog fd = new FileDialog(this, "Choose logo", FileDialog.LOAD);
			fd.setDirectory("C:\\Downloads");
			fd.setFile("*.jpeg;*.jpg;*.png;*.tiff;*.tif;*.gif;");
			fd.setLocationRelativeTo(null);
			fd.setVisible(true);
			String strfilename = fd.getFile();
			imagepath = fd.getDirectory() + strfilename;
			if (!imagepath.isEmpty()) {
				file = new File(imagepath);
			}

			if (strfilename != null) {
				long bytes = file.length();
				if (bytes < 1048576) {

					try {
						filesizelabel.setText(bytes / 1024 + " KB");
						filesizenote.setForeground(new Color(46, 139, 27));
						filesizenote.setText("Image size < 1024 KB");
						Image image = ImageIO.read(file);
						logoimagelabel.setIcon(new ImageIcon(image.getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
					} catch (IOException ex) {
						// TODO Auto-generated catch block
						file = null;
						filenamelabel.setText("No file Choosen");
						filesizelabel.setText("");
						filesizenote.setForeground(Color.red);
						filesizenote.setText("Image Not supported");
						ex.printStackTrace();
					}
					filenamelabel.setText(file.getName());
				} else {
					file = null;
					filenamelabel.setText("No File Choosen");
					filesizelabel.setText("");
					filesizenote.setForeground(Color.red);
					filesizenote.setText("Image size is greater than 1 MB");
				}
				

			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == createaccountbutton) {
			if (collagenamefield.getText().isEmpty()) {
				showerror(collagenamefield);
			} else if (emailidfield.getText().isEmpty()) {
				showerror(emailidfield);
			} else if (contactnumberfield.getText().isEmpty()) {
				showerror(contactnumberfield);
			} else if (websitefield.getText().isEmpty()) {
				showerror(websitefield);
			} else if (passwordfield.getPassword().toString().isEmpty()) {
				showerror(passwordfield);
			} else if (addresstextarea.getText().isEmpty()) {
				showerror(scrollpaneforaddress);
			} else {
				Admin ad = new Admin();
				ad.setCollageName(collagenamefield.getText());
				ad.setEmailId(emailidfield.getText());
				ad.setContactNumber(contactnumberfield.getText());
				ad.setAddress(addresstextarea.getText());
				ad.setPassword(String.valueOf(passwordfield.getPassword()));
				ad.setWebsite(websitefield.getText());

				if(file!=null) {
					try {
						ad.setProfilePic(ImageIO.read(file));
					} catch (Exception exp) {
						exp.printStackTrace();
					}
				} else {
					ad.setProfilePic(a.getProfilePic());
				}
			
				int result = new AdminData().updateAdminDetails(ad);
				if (result > 0) {
					am.adminprofilepanel.setVisible(false);
					am.adminprofilepanel = new AdminProfilePanel(am);
					am.adminprofilepanel.setLocation(am.panelx, am.panely);
					am.adminprofilepanel.setVisible(true);
					am.contentPane.add(am.adminprofilepanel);
					am.setCollageDetails();

					this.dispose();

				}
			}
		}

	}

	public void showerror(JComponent tf) {
		Errorlabel.setVisible(true);
		Errorlabel.setText("This is required question !");
		Errorlabel.setBounds(tf.getX(), tf.getY() + tf.getHeight() - 5, 400, 26);
	}
}
