import java.io.*;
import java.util.zip.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;


public class crcver extends JApplet  implements ActionListener
 {
  JButton b1,b2,b3;
  JPanel p0,p1,p2,p3,p4,p5;
  JLabel l1,l2,l4;
  JLabel l3=new JLabel();
  JLabel st=new JLabel();
  JLabel df=new JLabel();
  JFrame f=new JFrame();
  public crcver()
   {
    f.setVisible(true);
    f.setBounds(250,250,540,400);
    f.setTitle("CRC VERIFY");
	Image img=Toolkit.getDefaultToolkit().getImage("ddsm113.jpg");
             f.setIconImage(img);
    f.setResizable(false);
    p0=new JPanel();
    p1=new JPanel();
    p2=new JPanel();
    p3=new JPanel();
    p4=new JPanel();
    p5=new JPanel();

   p0.setBackground(Color.darkGray);
    p1.setBackground(Color.gray);
     p2.setBackground(Color.gray);
      p3.setBackground(Color.gray);
       p4.setBackground(Color.gray);
        p5.setBackground(Color.gray);
    p0.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
    l1=new JLabel("CRC VERIFY",JLabel.LEFT);
    l1.setFont(new Font("TimesRoman",Font.BOLD,30));
    l1.setForeground(Color.lightGray);
    l4=new JLabel("Choose File",JLabel.LEFT);
    l4.setFont(new Font("TimesRoman",Font.BOLD,20));
    l4.setForeground(Color.black);
    p2.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));
    p3.setLayout(new FlowLayout(FlowLayout.CENTER,50,0));
    p4.setLayout(new FlowLayout(FlowLayout.LEFT,20,40));
    p5.setLayout(new FlowLayout(FlowLayout.LEFT,20,0));
    l2=new JLabel("Selected File :-",JLabel.LEFT);
    l2.setFont(new Font("TimesRoman",Font.BOLD,15));
    l2.setForeground(Color.black);
    st.setFont(new Font("TimesRoman",Font.BOLD,15));
    st.setForeground(Color.black);

    b1=new JButton("Browse...");
    b2=new JButton("   VERIFY   ");
    b3=new JButton("   CANCEL   ");
    b1.addActionListener(this);
    b2.addActionListener(this);
    b3.addActionListener(this);

    p0.add(l1);
    p2.add(l4);
    p2.add(b1);
    p3.add(b2);
    p3.add(b3);
    p4.add(l2);
    p4.add(st);   
    p5.add(df);
    p1.setLayout(new GridLayout(4,1,0,0));
    f.getContentPane().add(p0,BorderLayout.NORTH);
    f.getContentPane().add(p1,BorderLayout.CENTER);
    p1.add(p2);
    p1.add(p3);
    p1.add(p4);
    }
  public void actionPerformed(ActionEvent ae)
   {

     JFrame jp;
     jp=new JFrame();
     String m=new String();
     FileDialog fd=new FileDialog(jp,"fd",FileDialog.LOAD);
     fd.setFile("*.crc;");

     if(ae.getSource()==b1)
     {
     fd.setVisible(true);
     
     fd.getFile();
     l3.setText(fd.getFile());
     st.setText(fd.getDirectory()+fd.getFile());
     }
     if(ae.getSource()==b2)
      {
       try
       {
        m=l3.getText();
        verify(m);
       }
       catch(Exception e)
        { }
        }
     else if(ae.getSource()==b3)
     { f.setVisible(false);
     f.dispose();
     }          

   }
  public void verify(String filepath) throws Exception
  {
    File file=new File(filepath);    
    DataInputStream fin=new DataInputStream(new FileInputStream(filepath));
    byte buffer[]=new byte[20];
    fin.read(buffer,0,20);
    String filename=new String(buffer);
    filepath=filepath.replace('\\','/');
    String outfilepath=filepath.substring(0,filepath.lastIndexOf("/")+1)+filename;

    BufferedOutputStream fout=new BufferedOutputStream(new FileOutputStream(outfilepath));
    
    CRC32 crc=new CRC32();

    int b;
    while((b=fin.read())!=-1) {
      fout.write(b);
      crc.update(b);
      
	  if(fin.available()<=8)
        break;
    }
 
 
    long checksum=fin.readLong();
    long compchecksum=crc.getValue();

    fin.close();
    fout.close();

    if(checksum==compchecksum)
    {
      vdb(true);
    }
    else
      {
        vdb(false);
      }
  }
void  vdb( boolean b)
 {
  JFrame f1=new JFrame();
    final JDialog dialog = new JDialog (f1, "STATUS...", true);
    dialog.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {dialog.dispose();}
    });
    String s=new String();
    if(b==true)
   {
    s= "VERIFICATION IS TRUE";}
    else
     {
      s="DATA CORRUPTED";
      }
    Container cp=getContentPane();
    JLabel  lab1  = new JLabel (" VERIFICATION STATUS ", JLabel.CENTER);
    lab1.setFont(new Font("TimesRoman",Font.BOLD+Font.ITALIC,17));
    JLabel  lab2  = new JLabel (s, JLabel.CENTER);
    lab2.setFont(new Font("TimesRoman",Font.BOLD+Font.ITALIC,12));

    dialog.getContentPane().setLayout(new GridLayout(3,1,0,0));
    dialog.getContentPane().add (lab1);
    dialog.getContentPane().add (lab2);
    
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
    dialog.setBounds(350,350,700,500);
    dialog.setSize (350, 200);
    dialog.setVisible(true);
  }
 


}
