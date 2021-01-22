package collegeapplication.common;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;

import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.Destination;
import javax.swing.JButton;

import collegeapplication.admin.AdminMain;
import collegeapplication.faculty.FacultyMain;
import collegeapplication.student.StudentMain;

/*
 * Title : PrintPDF.java
 * Created by : Ajaysinh Rathod
 * Purpose : For printing marksheet in PDF format
 * Reference : https://stackoverflow.com/ 
 * Mail : ajaysinhrathod1290@gmail.com
 */

public class printMarksheetPDF implements Printable ,ActionListener
{
	private AdminMain am;
	private FacultyMain fm;
	private StudentMain sm;
	String pdfpath;
	PrintMarksheetDialog pgd;
	private String title="Marksheet";

	public printMarksheetPDF(AdminMain am)
	{
		this.am = am;
	}

	public printMarksheetPDF(FacultyMain fm) 
	{
		this.fm = fm;
	}
	public printMarksheetPDF(StudentMain sm)
	{
		this.sm=sm;
	}
    public PrintService findPrintService(String printerName)
    {
        for (PrintService service : PrinterJob.lookupPrintServices())
        {
            if (service.getName().equalsIgnoreCase(printerName))
                return service;
        }

        return null;
    }

    @Override
    public int print(Graphics pg, PageFormat pf, int pagenum) throws PrinterException 
    {
    	pf.setOrientation(PageFormat.LANDSCAPE);
		if(pagenum>0)
		{
			return Printable.NO_SUCH_PAGE;
		}
		Graphics2D g=(Graphics2D) pg;
		g.translate(pf.getImageableX(), pf.getImageableY());
		g.scale(0.543,0.60);
		
		//printing marksheetpanel graphics to Printable graphics
		if(am!=null)
		{
			am.marksheetpanel.print(g);
		}
		else if(fm!=null)
		{
			fm.marksheetpanel.print(g);
		}
		else if(sm!=null)
		{
			sm.marksheetpanel.print(g);
		}
		
		return 0;
											
												
    }

   public void setPdfTitle(String title)
   {
	   this.title=title;
   }
   
   public void setPath(String path)
   {
	this.pdfpath=path;   
   }
   public void disposeDialog(PrintMarksheetDialog pgd)
   {
	this.pgd=pgd;   
   }

	@Override
	public void actionPerformed(ActionEvent event) {
		
		
		 try
		 {
			
			 
			String printerName = "Microsoft Print to Pdf";
			//find the printService of name printerName
			PrintService ps = findPrintService(printerName);
			// create a printerJob
			PrinterJob job = PrinterJob.getPrinterJob();

			//set the printService found (should be tested)
			job.setPrintService(ps);
			job.setPrintable(this); 
			job.setJobName(title);
			if(am!=null)
			{
			am.marksheetpanel.disablebutton();
			
			}
			else if(fm!=null)
			{
			fm.marksheetpanel.disablebutton();
			}
			else if(sm!=null)
			{
				sm.marksheetpanel.downloadbutton.setVisible(false);
			}
			Paper paper = new Paper();
			paper.setSize(595, 842); // A4 dimensions in font points
			paper.setImageableArea(0, 0, paper.getWidth() * 2, paper.getHeight());

			PageFormat pageFormat = new PageFormat();
			pageFormat.setPaper(paper);
			pageFormat.setOrientation(PageFormat.PORTRAIT);

			Book book = new Book();// java.awt.print.Book
			book.append(pgd.p, pageFormat);
			job.setPageable(book);
			
			HashPrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
			// set the output file as a destination
			attributes.add(new Destination(new File(pdfpath).toURI()));

			//printing pdf
			job.print(attributes);
			  
			if(am!=null)
			{
			  am.marksheetpanel.enablebutton();
			}
			else if(fm!=null)
			{
				fm.marksheetpanel.enablebutton();
			}
			else if(sm!=null)
			{
				sm.marksheetpanel.setVisible(true);
			}
			pgd.afterbutton((JButton) event.getSource());
		} 
        catch (PrinterException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
      
	}
}