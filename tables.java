import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class tables extends JFrame
 {
   static JFrame mf=new JFrame("Content");
   public  tables()
    {
   mf.setBounds(380,300,450,450);
Image img=Toolkit.getDefaultToolkit().getImage("ddsm113.jpg");
             mf.setIconImage(img);
  
  String data[][]={
	       {"Page No.","Information related to"},
	       {"",""},
	       {"1.","Introduction"},
                   {"2.","Embed"},
		   {"3.","De-Embed"},
		   {"4.","CRC"},
		   {"5.","CRC Generation"},
		   {"6.","CRC Verification"},
		   {"7.","Compress"},
		   {"8.","Decompress"},
		   };
  String cn[]={"Page No.","Topic"};
  JTable tab=new JTable(data,cn);
  mf.getContentPane().add(BorderLayout.CENTER,tab);
  mf.setSize(270,195);
  mf.addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent e)
 {
  mf.dispose();
  }
});
mf.setVisible(true);
mf.setResizable(false);
}
}

 		
  
