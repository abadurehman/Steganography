import java.io.*;
import java.net.*;
public class client1 extends Thread
{
  public static void main(String s[])
  {
    try
	{
	File f1=new File(s[0]);
	FileInputStream fis=new	FileInputStream(f1);
	byte[] b=new byte[fis.available()];
	Socket sc=new Socket("PC6",2222);
	OutputStream os=sc.getOutputStream();
	for(int i=0;i<b.length;i++)
	{
		b[i]=(byte)fis.read();
	}


	///FileOutputStream fout=new FileOutputStream("x.jpg");
   // fout.write(b,0,b.length);
	
	ByteArrayOutputStream bout= new ByteArrayOutputStream();
	bout.write(b,0,b.length);
    bout.writeTo(os);
    ///OutputStream os = sc.getOutputStream();
     ///PrintStream out =new PrintStream(os);
    //out.println(data);
    //out.flush();
    // InputStream is =sc.getInputStream();
    //BufferedReader br1 =new BufferedReader(new InputStreamReader(is));
    //String da=br1.readLine().trim();
    //System.out.println("response from server"+da);	
   }
   catch(Exception e){e.printStackTrace();}
  }
}