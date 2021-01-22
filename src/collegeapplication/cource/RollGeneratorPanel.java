package collegeapplication.cource;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import javax.swing.JTextField;

/*
 * Title : RollGeneratorPanel.java
 * Created by : Ajaysinh Rathod
 * Purpose : Creating panel according to sem and sending to RollGeneratorDialog
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class RollGeneratorPanel extends JPanel {

	/**
	 * Create the panel.
	 * 
	 */
	JPanel panel[];
	JLabel courcecodelabel[];
	JLabel semyearlabel[];
	JTextField textField[];
	String courcecode;
	int sem=8;

	int start=1;
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(605,this.getHeight());
	}
	
	public RollGeneratorPanel(String strcourcecode,int intsem) {
		setBackground(Color.WHITE);
		this.courcecode=strcourcecode;
		this.sem=intsem;
		this.setSize(605, 71);
		setLayout(null);
		panel=new JPanel[sem];
		courcecodelabel=new JLabel[sem];
		semyearlabel=new JLabel[sem];
		textField=new JTextField[sem];
		
		JLabel lblCourceCode = new JLabel("Cource Code");
		lblCourceCode.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblCourceCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourceCode.setOpaque(true);
		lblCourceCode.setForeground(new Color(255, 255, 255));
		lblCourceCode.setBackground(new Color(32, 178, 170));
		lblCourceCode.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblCourceCode.setBounds(10, 10, 166, 50);
		add(lblCourceCode);
		
		JLabel lblSem = new JLabel("Sem/Year");
		lblSem.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblSem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSem.setOpaque(true);
		lblSem.setForeground(Color.WHITE);
		lblSem.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblSem.setBackground(new Color(32, 178, 170));
		lblSem.setBounds(176, 10, 158, 50);
		add(lblSem);
		
		JLabel lblRollNumber = new JLabel("Master Roll Number");
		lblRollNumber.setOpaque(true);
		lblRollNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblRollNumber.setForeground(Color.WHITE);
		lblRollNumber.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblRollNumber.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblRollNumber.setBackground(new Color(32, 178, 170));
		lblRollNumber.setBounds(333, 10, 262, 50);
		add(lblRollNumber);
		int y=60;
		for(int i=0; i<intsem; i++)
		{
				this.setSize(this.getWidth(),this.getHeight()+50);
				panel[i]= new JPanel();
				panel[i].setBackground(Color.WHITE);
				panel[i].setBorder(new LineBorder(new Color(192, 192, 192)));
				panel[i].setBounds(10, y, 585, 50);
				add(panel[i]);
				panel[i].setLayout(null);
				
				courcecodelabel[i] = new JLabel(courcecode);
				courcecodelabel[i].setOpaque(true);
				courcecodelabel[i].setHorizontalAlignment(SwingConstants.CENTER);
				courcecodelabel[i].setForeground(Color.DARK_GRAY);
				courcecodelabel[i].setFont(new Font("Segoe UI", Font.PLAIN, 20));
				courcecodelabel[i].setBorder(new LineBorder(new Color(192, 192, 192)));
				courcecodelabel[i].setBackground(new Color(255, 255, 255));
				courcecodelabel[i].setBounds(0, 0, 166, 50);
				panel[i].add(courcecodelabel[i]);
				
				semyearlabel[i] = new JLabel(start+"");
				semyearlabel[i].setOpaque(true);
				semyearlabel[i].setHorizontalAlignment(SwingConstants.CENTER);
				semyearlabel[i].setForeground(Color.DARK_GRAY);
				semyearlabel[i].setFont(new Font("Segoe UI", Font.PLAIN, 20));
				semyearlabel[i].setBorder(new LineBorder(new Color(192, 192, 192)));
				semyearlabel[i].setBackground(Color.WHITE);
				semyearlabel[i].setBounds(166, 0, 158, 50);
				panel[i].add(semyearlabel[i]);
				
				textField[i] = new JTextField();
				textField[i].setBounds(333, 5, 242, 40);
				textField[i].setFont(new Font("Segoe UI", Font.PLAIN, 20));
				panel[i].add(textField[i]);
				textField[i].setColumns(10);
				y+=50;
				start++;
				if(i<intsem-1)
				{
					changeFocusable(i);
				}
		}

	}
	public void changeFocusable(int n)
	{
		textField[n].addKeyListener(new KeyAdapter()
		{
			
			
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
				textField[n].setFocusable(false);
				textField[n+1].setFocusable(true);
				}
				
			}
		});
		textField[n].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField[n].setFocusable(true);
			}
		});
	}

}
