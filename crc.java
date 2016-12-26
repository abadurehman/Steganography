import javax.swing.*;
import java.awt.*;
import java.applet.Applet.*;
import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.event.*;


class crc extends JPanel implements ActionListener
 {
   JRadioButton r1,r2,r3;
   JPanel p,p1;
   JLabel l=new JLabel();
   JFrame f=new JFrame();
 public crc()
   {
     f.setVisible(true);
     f.setTitle("CRC");
     f.setBounds(250,250,540,370);
     f.setResizable(false);
	 Image img=Toolkit.getDefaultToolkit().getImage("ddsm113.jpg");
      f.setIconImage(img);
    p=new JPanel();
    p1=new JPanel();
    l=new JLabel("C R C",JLabel.LEFT);
    l.setFont(new Font("TimesRoman",Font.BOLD,30));
    l.setForeground(Color.lightGray);
     p1.setBackground(Color.darkGray);
     p.setBackground(Color.gray);
     p1.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
     p.setLayout(new FlowLayout(FlowLayout.LEFT,230,70));
     p1.add(l);
     r1=new JRadioButton("Generate CRC");
     r2=new JRadioButton("Verify CRC");
     r3=new JRadioButton("Exit");
     r1.setBackground(Color.gray);
     r2.setBackground(Color.gray);
     r3.setBackground(Color.gray);

     r1.addActionListener(this);
     r2.addActionListener(this);
     r3.addActionListener(this);

     p.add(r1);
     p.add(r2);
     p.add(r3);
     f.getContentPane().add(p1,BorderLayout.NORTH);
     f.getContentPane().add(p,BorderLayout.CENTER);

     ButtonGroup bg=new ButtonGroup();
     bg.add(r1);
     bg.add(r2);
     bg.add(r3);

    }
public void actionPerformed(ActionEvent ae)
   {
    if(ae.getSource()==r1)
     {
       crcgen g=new crcgen();
     }
    else if(ae.getSource()==r2)
     {
       crcver g=new crcver();
       
     }

     else if(ae.getSource()==r3)
     { f.setVisible(false);
     f.dispose();
     }


  }
}
    
