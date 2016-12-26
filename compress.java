import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.applet.*;
import java.util.zip.*;
import java.util.*;

public class compress extends JPanel implements ActionListener
 {
  JButton b1,b2,b3;
  JPanel p0,p1,p2,p3,p4,p5;
  JLabel l1,l2,l3,l4,st,cf,buf;
  JFrame f;
  String m=new String();
public compress()
 {
	;
   st=new JLabel();
   buf=new JLabel();
   cf=new JLabel();
   f=new JFrame();
   f.setTitle("Compression");
   Image img=Toolkit.getDefaultToolkit().getImage("ddsm113.jpg");
             f.setIconImage(img);
    f.setBounds(250,250,540,430);
    p0=new JPanel();
    p1=new JPanel();
    p2=new JPanel();
    p3=new JPanel();
    p4=new JPanel();
    p5=new JPanel();
    p0.setBackground(Color.darkGray);
    p0.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
    p2.setBackground(Color.gray);                  
    p3.setBackground(Color.gray);
    p4.setBackground(Color.gray);
    p5.setBackground(Color.gray);
    p1.setLayout(new GridLayout(4,1,0,0));
    l1=new JLabel("COMPRESSION",JLabel.LEFT);
    l1.setFont(new Font("TimesRoman",Font.BOLD,30));
    l1.setForeground(Color.lightGray);
    l4=new JLabel("Choose Text File",JLabel.LEFT);
    l4.setFont(new Font("TimesRoman",Font.BOLD,20));
    l4.setForeground(Color.black);

    p2.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));
    p3.setLayout(new FlowLayout(FlowLayout.CENTER,40,0));
    p4.setLayout(new FlowLayout(FlowLayout.LEFT,40,40));
    p5.setLayout(new FlowLayout(FlowLayout.LEFT,40,0));
    l2=new JLabel("Selected Text File :-",JLabel.LEFT);
    l2.setFont(new Font("TimesRoman",Font.BOLD,15));
    l2.setForeground(Color.black);
    l3=new JLabel("Compressed File :-",JLabel.LEFT);
    l3.setFont(new Font("TimesRoman",Font.BOLD,15));
    l3.setForeground(Color.black);
    st.setFont(new Font("TimesRoman",Font.BOLD,15));
    st.setForeground(Color.black);
    cf.setFont(new Font("TimesRoman",Font.BOLD,15));
    cf.setForeground(Color.black);
    b1=new JButton("Browse...");
    b1.addActionListener(this);   
    b2=new JButton("COMPRESS");
    b3=new JButton("  CANCEL  ");
    b2.addActionListener(this);
    b3.addActionListener(this);
    p0.add(l1);
    p2.add(l4);
    p2.add(b1);
    p3.add(b2);
    p3.add(b3);
    p4.add(l2);
    p4.add(st);
    p5.add(l3);
    p5.add(cf);
    p1.add(p2);
    p1.add(p3);
    p1.add(p4);
    p1.add(p5);
    f.getContentPane().add(p0,BorderLayout.NORTH);
    f.getContentPane().add(p1,BorderLayout.CENTER);
    f.setVisible(true);
    f.setResizable(false);

 }

public void actionPerformed(ActionEvent ae) 
   {
     JFrame jp;
     
     m=null;
     jp=new JFrame();
     FileDialog fd=new FileDialog(jp,"fd",FileDialog.LOAD);
     fd.setFile("*.crc;");
     if(ae.getSource()==b1)
     {
     fd.setVisible(true);
     fd.getFile();
     st.setText(fd.getDirectory()+fd.getFile());
     buf.setText(fd.getFile());
     }

    if(ae.getSource()==b2)
     {
     try
     {
      m=(String)buf.getText();
      comp(m,mtrim(m));
      cf.setText(mtrim(m));
     }
      catch(Exception e)
       {
		  System.out.print(e);
		  JOptionPane.showMessageDialog(null,"No file is selected for compression","Message",JOptionPane.INFORMATION_MESSAGE);
	   }

		  
	 }
    else if(ae.getSource()==b3)
		 {	 f.setVisible(false);
			 f.dispose();
		}     
    }

String mtrim(String s1)
  {
    
    int l=s1.length();
    int k=l-4;
   
    return(s1.substring(0,k)+".cmp");
  }


  public void comp(String srcfile, String dstfile) throws IOException
  {
    BufferedInputStream fin=new BufferedInputStream(new FileInputStream(srcfile));

    BufferedOutputStream fout=new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(dstfile)));
    
	int b,ins,k;
    ins=0;
    float ous;
    while((b=fin.read())!=-1)
      {fout.write(b);ins++;}

    fin.close();
    fout.close();
    File f=new File(dstfile);
    ous=(int)f.length();
    cst(ins,ous);

  }

public static void cst(int inp,float out)
 {
  JFrame f1=new JFrame();
  float fcr=(float)((inp-out)/inp)*100;
    final JDialog dialog = new JDialog (f1, "STATUS...", true);
    dialog.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {dialog.dispose();}
    });
    String s=new String();
   
    s= "DATA COMPRESSED SUCCESSFULLY";
    JLabel  bc  = new JLabel("BEFORE COMPRESSION  :-   "+inp+" bytes",JLabel.CENTER);
    JLabel  ac  = new JLabel("AFTER  COMPRESSION  :-   "+out+" bytes",JLabel.CENTER);
    JLabel  cr  = new JLabel("COMPRESSION  RATIO  :-   "+fcr,JLabel.CENTER);

    JLabel  lab1  = new JLabel ("C O M P R E S S I O N   S T A T U S", JLabel.CENTER);
    lab1.setFont(new Font("TimesRoman",Font.BOLD+Font.ITALIC,17));
    JLabel  lab2  = new JLabel (s, JLabel.CENTER);
    lab2.setFont(new Font("TimesRoman",Font.BOLD+Font.ITALIC,12));

    dialog.getContentPane().setLayout(new GridLayout(6,1,0,0));
    dialog.getContentPane().add (lab1);
    dialog.getContentPane().add (lab2);
    dialog.getContentPane().add (bc);
    dialog.getContentPane().add (ac);
    dialog.getContentPane().add (cr);
    
    JButton butt = new JButton ("Close");
    JPanel p=new JPanel();
    p.add (butt);
    dialog.getContentPane().add(p);
    butt.addActionListener (new ActionListener () 
    {
      public void actionPerformed(ActionEvent e)
      {
        dialog.setVisible(false);
        dialog.dispose();
      }
    });
    dialog.setBounds(400,300,750,530);
    dialog.setSize (350, 230);
    dialog.setVisible(true);
  }

}
