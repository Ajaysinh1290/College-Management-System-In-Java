package collegeapplication.cource;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import collegeapplication.common.HintTextField;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


/*
 * Title : AddCourceDialog.java
 * Created by : Ajaysinh Rathod
 * Purpose : Dialog for adding new cource
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class AddCourceDialog extends JDialog implements ActionListener
{

	private JTextField courcecodefield;
	private JTextField courcenamefield;
	private JTextField totalsemoryearfield;
	private JComboBox<String> semoryearcombo;
	private JLabel lblError;
	private CourcePanel courcepanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddCourceDialog dialog = new AddCourceDialog();
			
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddCourceDialog(CourcePanel courcepanel)
	{
		this();
		this.courcepanel=courcepanel;
	}
	public AddCourceDialog() {
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 476, 452);
		getContentPane().setLayout(null);
		
		JLabel lblAddNewCource = new JLabel("Add New Cource");
		lblAddNewCource.setForeground(new Color(255, 255, 255));
		lblAddNewCource.setBackground(new Color(32, 178, 170));
		lblAddNewCource.setOpaque(true);
		lblAddNewCource.setFont(new Font("Arial", Font.BOLD, 23));
		lblAddNewCource.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewCource.setBounds(0, 0, 473, 55);
		getContentPane().add(lblAddNewCource);
		
		JLabel lblCourceCode = new JLabel("Cource Code ");
		lblCourceCode.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblCourceCode.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblCourceCode.setHorizontalAlignment(SwingConstants.LEFT);
		lblCourceCode.setBounds(10, 79, 139, 24);
		lblCourceCode.setFocusable(true);
		getContentPane().add(lblCourceCode);
		
		JLabel lblCourceName = new JLabel("Cource Name ");
		lblCourceName.setHorizontalAlignment(SwingConstants.LEFT);
		lblCourceName.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblCourceName.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblCourceName.setBounds(10, 147, 139, 24);
		getContentPane().add(lblCourceName);
		
		JLabel lblSemyear = new JLabel("Sem/Year");
		lblSemyear.setHorizontalAlignment(SwingConstants.LEFT);
		lblSemyear.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblSemyear.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblSemyear.setBounds(10, 218, 139, 24);
		getContentPane().add(lblSemyear);
		
		courcecodefield = new HintTextField("");
		courcecodefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		courcecodefield.setBounds(157, 72, 292, 40);
		getContentPane().add(courcecodefield);
		courcecodefield.setColumns(10);
		
		courcenamefield = new HintTextField("");
		courcenamefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		courcenamefield.setColumns(10);
		courcenamefield.setBounds(159, 141, 292, 40);
		getContentPane().add(courcenamefield);
		
		totalsemoryearfield = new HintTextField("");
		totalsemoryearfield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		totalsemoryearfield.setColumns(10);
		totalsemoryearfield.setBounds(157, 278, 292, 40);
		getContentPane().add(totalsemoryearfield);
		
		semoryearcombo = new JComboBox<String>();
		semoryearcombo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		semoryearcombo.setModel(new DefaultComboBoxModel<String>(new String[] {"---Select Sem/Year---", "sem", "year"}));
		semoryearcombo.setBounds(159, 210, 292, 40);
		getContentPane().add(semoryearcombo);
		
		JLabel lblTotalSemyear = new JLabel("Total Sem/Year");
		lblTotalSemyear.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalSemyear.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblTotalSemyear.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblTotalSemyear.setBounds(10, 284, 139, 24);
		getContentPane().add(lblTotalSemyear);
		
		JButton addcourcebutton = new JButton("Add Cource");
		addcourcebutton.setBackground(new Color(32, 178, 170));
		addcourcebutton.setForeground(new Color(255, 255, 255));
		addcourcebutton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		addcourcebutton.setBounds(310, 373, 139, 37);
		addcourcebutton.addActionListener(this);
		getContentPane().add(addcourcebutton);
		
		lblError=new JLabel("This is required question !");
		lblError.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 0, 0)));
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setFont(new Font("Candara", Font.PLAIN, 15));
		lblError.setVisible(false);
		lblError.setBounds(157,115,355,21);
		getContentPane().add(lblError);
		
		JLabel label = new JLabel("");
		label.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		label.setBounds(0, 346, 470, 14);
		getContentPane().add(label);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		lblError.setForeground(Color.red);
		lblError.setVisible(false);
		lblError.setText("This is required question !");
		String courcename=courcenamefield.getText();
		String courcecode=courcecodefield.getText();
		String semoryear=(String) semoryearcombo.getSelectedItem();
		String strtotalsemoryear=totalsemoryearfield.getText();
	 	if(courcecode.isEmpty())
		{
			lblError.setVisible(true);
			lblError.setBounds(courcecodefield.getX(), courcecodefield.getY()+courcecodefield.getHeight(), lblError.getWidth(), 21);
			courcecodefield.setFocusable(true);
		}
	
		else if(courcename.isEmpty())
		{
			lblError.setVisible(true);
			lblError.setBounds(courcenamefield.getX(), courcenamefield.getY()+courcenamefield.getHeight(), lblError.getWidth(), 21);
			courcenamefield.setFocusable(true);
		}
		else if(semoryearcombo.getSelectedIndex()==0)
		{
			lblError.setVisible(true);
			lblError.setBounds(semoryearcombo.getX(), semoryearcombo.getY()+semoryearcombo.getHeight(),  lblError.getWidth(), 21);
			
		}
		else if(strtotalsemoryear.isEmpty())
		{
			
			lblError.setVisible(true);
			lblError.setBounds(totalsemoryearfield.getX(), totalsemoryearfield.getY()+totalsemoryearfield.getHeight(),  lblError.getWidth(), 21);
			totalsemoryearfield.setFocusable(true);
		}
		
		else
		{
			 
				try
				{
					int totalsemoryear=Integer.parseInt(strtotalsemoryear);
					if(new CourceData().isCourceCodeExist(courcecode.toUpperCase()))
					{
						lblError.setVisible(true);
						lblError.setBounds(courcecodefield.getX(), courcecodefield.getY()+courcecodefield.getHeight(),  lblError.getWidth(), 21);
						lblError.setText("Cource code already exist !");
					}
					else if(new CourceData().isCourceNameExist(courcename))
					{
						lblError.setVisible(true);
						lblError.setBounds(courcenamefield.getX(), courcenamefield.getY()+courcenamefield.getHeight(), lblError.getWidth(), 21);
						courcenamefield.setFocusable(true);
						lblError.setText("Cource name already exist !");
					}
					else if(totalsemoryear<1)
					{
						lblError.setVisible(true);
						lblError.setBounds(totalsemoryearfield.getX(), totalsemoryearfield.getY()+totalsemoryearfield.getHeight(),  lblError.getWidth(), 21);
						lblError.setText("Minimun 1 sem/year required !");
					}
					else
					{
						CourceData c=new CourceData();
						int result=c.addCource(courcecode, courcename, semoryear, totalsemoryear);
						if(result>0)
						{
							
							if(courcepanel!=null)
							{
							courcepanel.updatetableData();
							}
							this.dispose();
						}
						
					}
				}
				catch(NumberFormatException nexp)
				{
					lblError.setVisible(true);
					lblError.setBounds(totalsemoryearfield.getX(), totalsemoryearfield.getY()+totalsemoryearfield.getHeight(), lblError.getWidth(), 21);
					lblError.setText("Characters are not allowed !");
				}
				
		
		}
		
	}
	
}
