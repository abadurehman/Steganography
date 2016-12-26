
import javax.swing.*;


import java.awt.*;

import java.awt.event.*;

public class Sp {
 Sp()
	{
    JWindow jwin = new JWindow();
    Container cp111=jwin.getContentPane();
	JLabel slabel=new JLabel();
Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			
	ImageIcon ic111=new ImageIcon("ani_logo.gif");
	slabel.setIcon(ic111);
	
      slabel.setSize(360,360);    
	    cp111.add(slabel);
  // jwin.setLocation(250,180);
  jwin.setLocation(screenSize.width/2 - 250,screenSize.height/2 - 150);
                    
   jwin.setSize(460,460);
    
  
    jwin.setVisible(true);

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    jwin.setVisible(false);
    jwin.dispose();

  }
}

 
			