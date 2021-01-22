package collegeapplication.common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;  
  
/*
 * Title : HintTextField.java
 * Created by : Ajaysinh Rathod
 * Purpose : For giving hint to user in text field
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class HintTextField extends JTextField {  
  
  
  private JLabel hintlabel;
/**
	 * 
	 */

  public HintTextField(String hint,Color hintforegroundcolor)
  {
	  this(hint);
	  hintlabel.setForeground(hintforegroundcolor);
  }
  public HintTextField(String hint) {  
  
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
    					JTextField field=(JTextField) e.getSource();
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
    
//    this.addFocusListener(new FocusAdapter() {  
//  
//      @Override  
//      public void focusGained(FocusEvent e) {  
//        if (getText().equals(hint)) {  
//          setText("");  
//        } else {  
//          setText(getText());  
//          
//        }  
//      }  
//  
//      @Override  
//      public void focusLost(FocusEvent e) {  
//        if (getText().equals(hint)|| getText().length()==0) {  
//          setText(hint);  
//        } else {  
//          setText(getText());  
//        }  
//      }  
//    });  
  
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
    }
   }
}