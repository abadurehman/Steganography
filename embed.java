import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.applet.Applet.*;
import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;

public class embed extends JFrame implements ActionListener
{
	    int i=0;
         int j=0;
     
   JLabel tf = new JLabel("");
   JLabel imf = new JLabel("");
   JButton b1,b2,b3,b4;
   JFrame f = new JFrame();
   JPanel p0,p1,p2,p3,p4,p5,p8;
   JLabel l1 = new JLabel();
   JLabel l2 = new JLabel();
	JPasswordField jtf = new JPasswordField(3);
 public embed() 
 {

  JLabel l1,l2,l3,l4,l5,l6;

   f.setVisible(true);
   f.setTitle("Embed");
   Image img=Toolkit.getDefaultToolkit().getImage("ddsm113.jpg");
    f.setIconImage(img);
   f.setBounds(250,250,540,400);
   f.setResizable(false);

   p0=new JPanel();
   p1=new JPanel();
   p2=new JPanel();
   p3=new JPanel();
   p4=new JPanel();
   p5=new JPanel();
   p8=new JPanel();

   p0.setBackground(Color.darkGray);
   p1.setBackground(Color.gray);
   p8.setBackground(Color.gray);
   p2.setBackground(Color.gray);
   p3.setBackground(Color.gray);
   p4.setBackground(Color.gray);
   p5.setBackground(Color.gray);
   
   p0.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
   p1.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
   p8.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
   p3.setLayout(new FlowLayout(FlowLayout.LEFT,40,40));
   p2.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
   p5.setLayout(new FlowLayout(FlowLayout.LEFT,40,0));

   l1=new JLabel("INFO FILE",JLabel.LEFT);
   l1.setForeground(Color.black);
   l1.setFont(new Font("TimesRoman",Font.BOLD,20));

   l2=new JLabel("CARRIER FILE",JLabel.LEFT);
   l2.setForeground(Color.black);
   l2.setFont(new Font("TimesRoman",Font.BOLD,20));
   
   l6=new JLabel("4-Bit Password :",JLabel.LEFT);
   l6.setForeground(Color.black);
   l6.setFont(new Font("TimesRoman",Font.BOLD,15));

   l3=new JLabel("EMBED",JLabel.LEFT);
   l3.setForeground(Color.lightGray);
   l3.setBackground(Color.darkGray);
   l3.setFont(new Font("TimesRoman",Font.BOLD,30));

   l4=new JLabel("Selected Info File  :-",JLabel.LEFT);
   l4.setForeground(Color.black);
   l4.setFont(new Font("chari",Font.BOLD,15));

   l5=new JLabel("Selected Embeding File :-",JLabel.LEFT);
   l5.setForeground(Color.black);
   l5.setFont(new Font("chari",Font.BOLD,15));

   tf.setForeground(Color.black);
   tf.setFont(new Font("TimesRoman",Font.BOLD,15));
   imf.setForeground(Color.black);
   imf.setFont(new Font("TimesRoman",Font.BOLD,15));

   b1=new JButton("CHOOSE..."); 
   b2=new JButton("CHOOSE...");
   b3=new JButton(" EMBED ");
   b4=new JButton("CANCEL");
   b1.addActionListener(this);   
   b2.addActionListener(this);
   b3.addActionListener(this);
   b4.addActionListener(this);   
   


   p1.add(l1);
   p1.add(b1);
   p1.add(l2);
   p1.add(b2);
   p8.add(l6);
   p8.add(jtf);
   p2.add(b3);
   p2.add(b4);
   p3.add(l4);
   p3.add(tf);
   p5.add(l5);
   p5.add(imf);

   p4.setLayout(new GridLayout(5,1,0,0));
   
   p4.add(p1);
   
   p4.add(p2);
   p4.add(p3);
   p4.add(p5);
   p0.add(l3);
  p4.add(p8);

   f.getContentPane().add(p0,BorderLayout.NORTH);
   f.getContentPane().add(p4,BorderLayout.CENTER);

 }


public void actionPerformed(ActionEvent ae)
{
     JFrame jp;
     String m1,m2;
     m1=new String();
     m2=new String();
     jp=new JFrame();
  
	  
     FileDialog fd=new FileDialog(jp,"fd",FileDialog.LOAD);
     fd.setFile("*.*;");		//*.cmp
     
     FileDialog fd1=new FileDialog(jp,"fd",FileDialog.LOAD);
     fd1.setFile("*.*;");
     
	 if(ae.getSource()==b1)
     {
		 i=1;
		 
     fd.setVisible(true);
	 
      l1.setText(fd.getFile());
      tf.setText(fd.getDirectory()+fd.getFile());
     }
     
	 else if(ae.getSource()==b2)
     {
		 j=1;
		 fd1.setVisible(true);
		 l2.setText(fd1.getFile());
		 fd1.getFile();
		 imf.setText(fd1.getDirectory()+fd1.getFile());
     }
     
	 if(ae.getSource()==b3)
     {
		 try
		 {
			 
		  m1=l1.getText();
		  m2=l2.getText();
		 
		 Scanner sc= new Scanner(System.in);
		// System.out.println("Enter 4-bit password :");
		 //password = sc.next();
		 char[] input = jtf.getPassword();
		 String password =new String(input);
		 //System.out.println(password);
		 if(password.length()!=4)
		 {
		JOptionPane.showMessageDialog(this," INCORRECT PASSWORD LENGTH");
		 return;
		 }
		 String pass ="";
		 pass= password.substring(0,4);
		 
		 if(i==0 || j==0)
			  JOptionPane.showMessageDialog(this," File not found ");
		  else 
		  {
			
			String s3,s2;
			s3=m1.substring((m1.lastIndexOf(".")+1),m1.length());
			s2=m2.substring((m2.lastIndexOf(".")+1),m2.length());
			String s4= "txt";
			String s9="java";
			String s5="jpg";
			String s6="png";
			String s7= "bmp";
			String s8="gif";
			//String s10="crc";
			
			String newfile = "";
			if(s3.equals(s4) || s3.equals(s9))
			{
				if(s2.equals(s5) || s2.equals(s6) || s2.equals(s7) || s2.equals(s8))
				{
					Steganography s=new Steganography();
					newfile += s.hide(m1,m2,pass);
				}
				else
				{
					emb(m2,m1,pass);
					newfile += m2;
				}
			}
		
		else
		
		{
			emb(m2,m1,pass);
			newfile += m2;
		}
        
		JOptionPane.showMessageDialog(this," Data is embed and file saved as : " + newfile);
	  }
      }catch(Exception e){System.out.println(e);}
     }
     else if(ae.getSource()==b4)
     { f.setVisible(false);
     f.dispose();
     }
    }

public static String encrypt(String msg)
{
    byte str[] = msg.getBytes();
    for(int i=0; i<str.length; i++) 
    str[i]=(byte)(str[i]+128);
    return new String(str);
    
}


public static void emb(String imgfilename, String datfilename, String password) throws Exception
	{
        File file1=new File(imgfilename);
        File file2=new File(datfilename);

        FileInputStream fin=new FileInputStream(imgfilename);
        FileOutputStream fout=new FileOutputStream("temp");	
		
		
        final int bsize=8;
        byte buffer[]=new byte[bsize];
        int nbytes,nb=0;
        while((nbytes=fin.read(buffer,0,bsize))>0)
		{
          fout.write(buffer,0,nbytes);
          nb=nbytes;
        }
        fin.close();

		for(int i=1; i<=8-nb; i++)
          fout.write(65-i);
	
        fout.write("$#@%&*+-".getBytes(),0,8);
		password=encrypt(password);
		fout.write(password.getBytes(),0,4);
        StringBuffer sb=new StringBuffer(file2.getName());
        String sbnew = encrypt(sb.toString()+"$");
		StringBuffer ssbnew = new StringBuffer(sbnew);
		ssbnew.setLength(50);
		//System.out.println(ssbnew + "$");
		fout.write(ssbnew.toString().getBytes(),0,50);

		
		fin=new FileInputStream(datfilename);
        
		while((nbytes=fin.read(buffer,0,bsize))>0) 
		{
          fout.write(buffer,0,nbytes);
        
        }
        fin.close();
        fout.close();

        file1.delete();
		File file=new File("temp");
        file.renameTo(file1);

   
	}


 
}

