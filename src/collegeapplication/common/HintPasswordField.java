package collegeapplication.common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;  
  

/*
 * Title : HintPasswordField.java
 * Created by : Ajaysinh Rathod
 * Purpose : For giving hint to user in password field
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class HintPasswordField extends JPasswordField {  
  
  
  private JLabel hintlabel;
/**
	 * 
	 */

  public HintPasswordField(String hint,Color hintforegroundcolor)
  {
	  this(hint);
	  hintlabel.setForeground(hintforegroundcolor);
  }
  public HintPasswordField(String hint) {  
  
	hint=hint.trim();
	
    setForeground(Color.DARK_GRAY);  
    setLayout(new BorderLayout());
    hintlabel=new JLabel(hint);
    hintlabel.setFont(new Font("Segoe UI",Font.PLAIN,18));
    hintlabel.setForeground(new Color(100,100,100));
    add(hintlabel,BorderLayout.LINE_START);
    this.getDocument().addDocumentListener(new MyDocumentListener());
    
    this.addMouseListener(new MouseAdapter()
    		{
    			public void mousePressed(MouseEvent e)
    			{
    					JPasswordField field=(JPasswordField) e.getSource();
    					field.setFocusable(true);
    			}
    		}
    		);
    this.addKeyListener(new KeyAdapter()
    		{
    			@Override
    			public void keyPressed(KeyEvent e)
    			{
    				if(e.getKeyCode()==KeyEvent.VK_ENTER)
    				{
    					setFocusable(false);
    				}
    			}
    		}
    		
    		);
  }  
	private class MyDocumentListener implements DocumentListener 
	{
	    public void insertUpdate(DocumentEvent e) 
	    {
	        updateLog(e);
	    }
	    public void removeUpdate(DocumentEvent e)
	    {
	        updateLog(e);
	    }
	    public void changedUpdate(DocumentEvent e) 
	    {
	        //Plain text components do not fire these events
	    }
	    public void updateLog(DocumentEvent e)
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