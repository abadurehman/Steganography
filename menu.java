import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
 

public class menu extends JFrame implements ActionListener
{ 
   JMenuItem it1,it2,it3,it4,it5,it6,it7,it8,it9,it10,it11,it12,it13;
   Calendar d=Calendar.getInstance();
   JLabel dl=new JLabel(" ",JLabel.LEFT);
 
   int day,mon,yr;
   String dt;
menu()
	{
			 JFrame f=new JFrame(); Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			 f.setSize(screenSize);
			 f.setResizable(false);
			
			 Container cp=f.getContentPane();
			
			 day=d.get(Calendar.DATE);
			 mon=d.get(Calendar.MONTH)+1;
			 yr=d.get(Calendar.YEAR);
			 
			 dt="DATE :- "+day+"/"+mon+"/"+yr;
			 dl.setText(dt);
			 dl.setSize(200,25);
			 
			 int k=(screenSize.width)-175;
			 dl.setLocation(k,675);
			 dl.setFont(new Font("Courier",Font.BOLD,15));
			 dl.setForeground(Color.white);
			 
			 cp.setLayout(null);
			 JLabel label=new JLabel();
			ImageIcon ic11=new ImageIcon("toplogo.jpg");
					label.setIcon(ic11);
					label.setSize(1024,256);		//screenSize.width
			 label.setLocation(0,0);
			
			ImageIcon ic12=new ImageIcon("cube.gif");		//
		    JLabel label1=new JLabel();
		    
			label1.setIcon(ic12);
            label1.setSize(400,256);
		 
			 //label.setSize(screenSize.width,105);
			 label.setLocation(320,0);
			 cp.add(label);

			 cp.setBackground(Color.gray);
			 JMenuBar bar = new JMenuBar( );
			 
			 bar.setSize(250,20);int i=((screenSize.width)/2)-125;
			 bar.setLocation(i-20,260);int j=((screenSize.width)/2)-175;
				label1.setLocation(j+25,320);
			 bar.setBorderPainted(true);
			 cp.add(bar);
			 cp.add(dl);
			 cp.add(label1);

			JMenu data = new JMenu("   DATA   ");
			data.setMnemonic(KeyEvent.VK_D);

			it1 = new JMenuItem(" CRC ");
			it1.setMnemonic(KeyEvent.VK_C);

			it2 = new JMenuItem("EMBED");
			it2.setMnemonic(KeyEvent.VK_E);

			it3 = new JMenuItem("DE-EMBED");
			it3.setMnemonic(KeyEvent.VK_D);

			 
			data.add(it1);
			data.add(it2);
			data.add(it3);
						  
			it1.addActionListener(this);     
			it2.addActionListener(this);     
			it3.addActionListener(this);     
		   
			JMenu utilities = new JMenu("   UTILITIES   ");
			utilities.setMnemonic(KeyEvent.VK_U);

			it4 = new JMenuItem("COMPRESS");
			it4.setMnemonic(KeyEvent.VK_O);

			it5 = new JMenuItem("DECOMPRESS");
			it5.setMnemonic(KeyEvent.VK_P);

			it10 = new JMenuItem("SEND");
			it10.setMnemonic(KeyEvent.VK_S);

           

			utilities.add(it4);
			utilities.add(it5);
			utilities.add(it10);
			
			
			it4.addActionListener(this);     
			it5.addActionListener(this);  
			it10.addActionListener(this);
			
			JMenu help = new JMenu("   HELP   ");
			help.setMnemonic(KeyEvent.VK_H);

			it6 = new JMenuItem("ABOUT");
			it6.setMnemonic(KeyEvent.VK_A);
			help.add(it6);

			it7 = new JMenuItem("CONTENTS");
			it7.setMnemonic(KeyEvent.VK_N);
			help.add(it7);

			it8 = new JMenuItem("INFRMATION");
			it8.setMnemonic(KeyEvent.VK_I);
			help.add(it8);

			 it6.addActionListener(this);
			 it7.addActionListener(this);
			 it8.addActionListener(this); 

			 JMenu exit=new JMenu("EXIT");
			 exit.setMnemonic(KeyEvent.VK_X);

			 it9 = new JMenuItem( "EXIT ");
			 it9.setMnemonic(KeyEvent.VK_T);
			 exit.add(it9);
			 
			 it9.addActionListener(new ActionListener() {
			 public void actionPerformed (ActionEvent e) {
				 System.exit(0);
			    }
             });
			bar.add(data);
			bar.add(utilities); 
			bar.add(help);
			bar.add(exit);
			//f.setLocation(200,100);
			f.setVisible(true);
            f.setTitle("Digital Security System");
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			System.out.println("in end of if1");
			   
	 }//menu cons
		public void actionPerformed(ActionEvent ae)
	{
	if(ae.getSource()==it1)
     {
       
       crc x=new crc();
       repaint();
     }

    else if(ae.getSource()==it2)
     {
       
       embed x=new embed();
       repaint();
     }

    else if(ae.getSource()==it3)
     {
       
       dembed x=new dembed();
       repaint();
     }

    else if(ae.getSource()==it4)
     {
       
       compress x=new compress();
       repaint();
     }

    else if(ae.getSource()==it5)
     {
       
       Decompress x=new Decompress();
       repaint();
      }
    
	else if(ae.getSource()==it6)
     {
     
      about();
      }

    else if(ae.getSource()==it7)
     {
      
       tables x=new tables();
       repaint();
      }

    else if(ae.getSource()==it8)
     {
       
       index x=new index();
       repaint();
     }

	

	  else if(ae.getSource()==it10)
     {
      
       sen d=new sen();
       repaint();
     }
	 

 }//actionperformed

void about()
 {
    JFrame f1=new JFrame();
	Image img=Toolkit.getDefaultToolkit().getImage("ddsm113.jpg");
             f1.setIconImage(img);
    final JDialog dialog = new JDialog (f1, "About...", true);
    dialog.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {dialog.dispose();}
      });
    
    JLabel  lab1  = new JLabel ("Image Stegnography", JLabel.CENTER);
    lab1.setFont(new Font("TimesRoman",Font.BOLD+Font.ITALIC,20));
    lab1.setForeground(Color.gray);
    JLabel  lab2  = new JLabel ("Version 1.0", JLabel.CENTER);
    JLabel  lab3  = new JLabel ("Copyright(c) 2012-2013 by", JLabel.CENTER);
    JLabel  lab4  = new JLabel ("K SURAJ", JLabel.CENTER);
	JLabel  lab51  = new JLabel ("K SURABHI", JLabel.CENTER);
	JLabel  lab61  = new JLabel ("POOJA PRASANNAN", JLabel.CENTER);
    dialog.getContentPane().setLayout(new GridLayout(5,1,0,0));
    dialog.getContentPane().add (lab1);
    dialog.getContentPane().add (lab2);
    dialog.getContentPane().add (lab3);
    dialog.getContentPane().add (lab4);
    JButton butt = new JButton("Close");
    JPanel p=new JPanel();
	
    p.add(butt);
    dialog.getContentPane().add(p);
    butt.addActionListener (new ActionListener () {
    public void actionPerformed(ActionEvent e) {
        dialog.setVisible(false);
        dialog.dispose();
      }
    });
    
    dialog.setBounds(325,250,390,370);
    
    dialog.setVisible(true);
  }//abt
      
   public static void main(String s[])
	{
	   
	   //Sp sppp= new Sp();
	   menu m=new menu();
	  
	}
	
 }//menuClass END