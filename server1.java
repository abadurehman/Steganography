import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
public class server1 
{
	public static void main(String a[])
	{
		String jo=JOptionPane.showInputDialog("Enter File Name"+"  *(must be .jpeg , .jpg or .bmp extension)");
        System.out.println(jo);
		if(jo==null)
		{
			System.exit(0);
		}
		int j=0,i=0;
		byte[] b=new byte[10000000] ;
		try{
			ServerSocket ss =new ServerSocket(2222);
			Socket sc =ss.accept();
			InputStream is=sc.getInputStream();
			System.out.println("after accept");
			FileOutputStream fos=new FileOutputStream(jo);
			j=is.read();
			
			while(j!=-1)
			{	
					
				fos.write(j);
				j=is.read();
				
		    }
			fos.close();
			is.close();
			JOptionPane.showMessageDialog(null,"Message recieved  successfully","Message",JOptionPane.INFORMATION_MESSAGE);
			System.out.println("End of Writing into file");		
           }catch(Exception e){e.printStackTrace();}
	}
	
	
}