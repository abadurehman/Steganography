import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public	class sen 
    {
				String addr=JOptionPane.showInputDialog("Enter address: ");
			    String name="";
        public sen()
		{
		try
		{
			JFrame jp=new JFrame();
		     FileDialog fd=new FileDialog(jp,"select the file",FileDialog.LOAD);
		     fd.show();
		     if(fd.getFile()!=null)
		     {
				    name=fd.getDirectory()+fd.getFile();
		     }//if
			
			System.out.println("Ip addr" + fd.getFile());
			
			File f1=new File(name);
            if(f1.exists())
		    {   
				FileInputStream fis=new	FileInputStream(f1);
				byte[] b=new byte[fis.available()];
				//Socket sc=new Socket(addr,2222);
				Socket sc=new Socket("192.168.0.3",2222);
			
				
				OutputStream os=sc.getOutputStream();
				for(int i=0;i<b.length;i++)
				{
					b[i]=(byte)fis.read();
				}
				ByteArrayOutputStream bout= new ByteArrayOutputStream();
				bout.write(b,0,b.length);
				bout.writeTo(os);
				JOptionPane.showMessageDialog(null,"Message sent  successfully","Message",JOptionPane.INFORMATION_MESSAGE);
			 }//if
			 else
				 JOptionPane.showMessageDialog(null,"File not found","Message",JOptionPane.INFORMATION_MESSAGE);
		
	  }catch(UnknownHostException ex){
			JOptionPane.showMessageDialog(null,"Host not found","Message",JOptionPane.INFORMATION_MESSAGE);}
    	catch(IOException iex){
				JOptionPane.showMessageDialog(null,"Unable to perform IO","Message",JOptionPane.INFORMATION_MESSAGE);
	      } 
	}
 }//class