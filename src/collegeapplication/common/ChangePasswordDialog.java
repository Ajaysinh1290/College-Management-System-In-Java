package collegeapplication.common;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

import collegeapplication.faculty.Faculty;
import collegeapplication.faculty.FacultyData;
import collegeapplication.student.Student;
import collegeapplication.student.StudentData;

import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class ChangePasswordDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField oldpasswordfield;
	private JPasswordField newpasswordfield;
	private JPasswordField newpasswordfield2;
	private JLabel headinglabel;
	private JButton changepasswordbutton;
	private JLabel lblError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ChangePasswordDialog dialog = new ChangePasswordDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("deprecation")
	public ChangePasswordDialog(Student s)
	{
		this();
		changepasswordbutton.addActionListener(e->
		{
			System.out.println(s.getPassword());
			if(oldpasswordfield.getText().isEmpty())
			{
				showerror(oldpasswordfield);
			}
			else if(!s.comparePassword(oldpasswordfield.getText()))
			{
				showerror(oldpasswordfield);
				lblError.setText("Old password doesn't match.");
			}
			else if(newpasswordfield.getText().isEmpty())
			{
				showerror(newpasswordfield);
			}
			else if(newpasswordfield2.getText().isEmpty())
			{
				showerror(newpasswordfield2);
			}
			else if(!newpasswordfield.getText().equals(newpasswordfield2.getText()))
			{
				showerror(newpasswordfield2);
				lblError.setText("password doesn't match");
			}
			else 
			{
				int result=new StudentData().changePassword(s.getUserId(), newpasswordfield.getText());
				if(result>0)
				{
				JOptionPane.showMessageDialog(null, "Password updated","Message",JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
				}
				
			}
		});
		
	}
	@SuppressWarnings("deprecation")
	public ChangePasswordDialog(Faculty f)
	{
		this();
		changepasswordbutton.addActionListener(e->
		{
			if(oldpasswordfield.getText().isEmpty())
			{
				showerror(oldpasswordfield);
			}
			else if(!f.comparePassword(oldpasswordfield.getText()))
			{
				showerror(oldpasswordfield);
				lblError.setText("Old password doesn't match.");
			}
			else if(newpasswordfield.getText().isEmpty())
			{
				showerror(newpasswordfield);
			}
			else if(newpasswordfield2.getText().isEmpty())
			{
				showerror(newpasswordfield2);
			}
			else if(!newpasswordfield.getText().equals(newpasswordfield2.getText()))
			{
				showerror(newpasswordfield2);
				lblError.setText("password doesn't match");
			}
			else 
			{
				int result=new FacultyData().changePassword(f.getFacultyId()+"", newpasswordfield.getText());
				if(result>0)
				{
				JOptionPane.showMessageDialog(null, "Password updated","Message",JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
				}
				
			}
		});
		
	}
	public ChangePasswordDialog() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 528, 354);
		getContentPane().setLayout(null);
		
		headinglabel = new JLabel("Change Password");
		headinglabel.setBackground(new Color(32, 178, 170));
		headinglabel.setOpaque(true);
		headinglabel.setFocusable(true);
		headinglabel.setForeground(Color.WHITE);
		headinglabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		headinglabel.setHorizontalAlignment(SwingConstants.CENTER);
		headinglabel.setBounds(0, 0, 523, 51);
		getContentPane().add(headinglabel);
		
		JLabel label1 = new JLabel("Enter old Password");
		label1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		label1.setBounds(10, 76, 173, 33);
		getContentPane().add(label1);
		
		JLabel label2 = new JLabel("Enter new password");
		label2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		label2.setBounds(10, 139, 173, 33);
		getContentPane().add(label2);
		
		JLabel label3 = new JLabel("Re-enter new password");
		label3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		label3.setBounds(10, 194, 173, 40);
		getContentPane().add(label3);
		
		oldpasswordfield = new JPasswordField();
		oldpasswordfield.setBorder(new LineBorder(new Color(171, 173, 179)));
		oldpasswordfield.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		oldpasswordfield.setBounds(193, 75, 248, 40);
		getContentPane().add(oldpasswordfield);
		
		newpasswordfield = new JPasswordField();
		newpasswordfield.setBorder(new LineBorder(new Color(171, 173, 179)));
		newpasswordfield.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		newpasswordfield.setBounds(193, 135, 248, 40);
		getContentPane().add(newpasswordfield);
		
		newpasswordfield2 = new JPasswordField();
		newpasswordfield2.setBorder(new LineBorder(new Color(171, 173, 179)));
		newpasswordfield2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		newpasswordfield2.setBounds(193, 197, 248, 40);
		getContentPane().add(newpasswordfield2);
		
		
		
		changepasswordbutton = new JButton("Change");
		changepasswordbutton.setFocusable(false);
		changepasswordbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		changepasswordbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		changepasswordbutton.setForeground(new Color(255, 255, 255));
		changepasswordbutton.setBackground(new Color(32, 178, 170));
		changepasswordbutton.setBounds(377, 281, 137, 33);
		getContentPane().add(changepasswordbutton);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(192, 192, 192)));
		lblNewLabel_1.setBounds(0, 256, 523, 14);
		getContentPane().add(lblNewLabel_1);
		
		JButton oldshowbutton = new JButton("show");
		oldshowbutton.setForeground(new Color(255, 255, 255));
		oldshowbutton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		oldshowbutton.setBackground(new Color(32, 178, 170));
		oldshowbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		oldshowbutton.setBounds(445, 74, 70, 40);
		oldshowbutton.setFocusable(false);
		oldshowbutton.addActionListener(e->
				{
					if(oldshowbutton.getText().equals("show"))
					{
						oldpasswordfield.setEchoChar('\u0000');
						oldshowbutton.setText("hide");
					}
					else if(oldshowbutton.getText().equals("hide"))
					{
						oldpasswordfield.setEchoChar((Character)UIManager.get("PasswordField.echoChar"));
						oldshowbutton.setText("show");
					}
				}
				);
		getContentPane().add(oldshowbutton);
		
		JButton newshowbutton = new JButton("show");
		newshowbutton.setForeground(Color.WHITE);
		newshowbutton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		newshowbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		newshowbutton.setBackground(new Color(32, 178, 170));
		newshowbutton.setBounds(445, 135, 70, 40);
		newshowbutton.setFocusable(false);
		newshowbutton.addActionListener(e->
					{
						if(newshowbutton.getText().equals("show"))
						{
							newpasswordfield.setEchoChar('\u0000');
							newshowbutton.setText("hide");
						}
						else if(newshowbutton.getText().equals("hide"))
						{
							newpasswordfield.setEchoChar((Character)UIManager.get("PasswordField.echoChar"));
							newshowbutton.setText("show");
						}
					}
				);
		getContentPane().add(newshowbutton);
		
		JButton new2showbutton = new JButton("show");
		new2showbutton.setForeground(Color.WHITE);
		new2showbutton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		new2showbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		new2showbutton.setBackground(new Color(32, 178, 170));
		new2showbutton.setBounds(445, 197, 70, 40);
		new2showbutton.setFocusable(false);
		new2showbutton.addActionListener(e->
		{
			if(new2showbutton.getText().equals("show"))
			{
				newpasswordfield2.setEchoChar('\u0000');
				new2showbutton.setText("hide");
			}
			else if(new2showbutton.getText().equals("hide"))
			{
				newpasswordfield2.setEchoChar((Character)UIManager.get("PasswordField.echoChar"));
				newshowbutton.setText("show");
			}
		});
		getContentPane().add(new2showbutton);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		lblError=new JLabel("This is required question !");
		lblError.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 0, 0)));
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setFont(new Font("Candara", Font.PLAIN, 15));
		lblError.setVisible(false);
		lblError.setBounds(211,134,355,21);
		getContentPane().add(lblError);
	}
	public void showerror(JComponent tf)
	{
		lblError.setVisible(true);
		lblError.setText("This is required question !");
		lblError.setBounds(tf.getX(), tf.getY()+tf.getHeight()-5, 400,26);
	}
}
