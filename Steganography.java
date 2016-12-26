
import java.io.*;
import java.awt.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;



public class Steganography
{
   static final int MAX_INT_LEN = 4;
   static final int DATA_SIZE = 8;   
    



  public static String hide(String textFnm, String imFnm, String pass)
  
  {
    
    String inputText = readTextFile(textFnm);
    if ((inputText == null) || (inputText.length() == 0))
      return "NO MESSAGE FOUND";

    byte[] stego = buildStego(inputText);

    
    BufferedImage im1 = loadImage(imFnm);
	File newfile = new File("newimage.png");
	try{
	ImageIO.write(im1, "png", newfile);
	}catch(Exception e){System.out.println("Image not made : " + e);}
	
	BufferedImage im = loadImage(newfile.getName());
    if (im == null)
      return "NO IMAGE FILE FOUND";
    byte imBytes[] = accessBytes(im);

    String fal="Embeding Unsuccessful";
	if (!singleHide(imBytes, stego,pass))   // im is modified with the stego
      return fal;

    
    String fnm = getFileName(imFnm);
     
	
	/*try{
		FileOutputStream fout=new FileOutputStream(imFnm);	
		File file12=new File(imFnm);
		fout.write(imBytes,0,imBytes.length);
		}catch(Exception e){}
	return true;      */
	
	writeImageToFile( fnm + "msg.png", im);
	return (fnm+"msg.png");	
  
  } 


  private static String readTextFile(String fnm)
  {
    BufferedReader br = null;
    StringBuffer sb = new StringBuffer();
 
    try {
      br = new BufferedReader(new FileReader( new File(fnm) ));      String text = null;
      File f = new File(fnm);
	  StringBuffer fname1= new StringBuffer();
	  fname1.append(f.getName());
	  fname1.setLength(30);
	  sb.append(fname1);
	  System.out.println(sb);
	  while ((text = br.readLine()) != null)
        sb.append(text + "\n");
    } 
    catch (Exception e) {
      System.out.println("Could not completely read " + fnm);
      return null;
    } 
    finally {
      try {
        if (br != null)
          br.close();
       } 
       catch (IOException e) {
         System.out.println("Problem closing " + fnm);
         return null;
       } 
    }
    System.out.println("Read in " + fnm);
    return sb.toString();
  } 



  private static byte[] buildStego(String inputText)
  { 
    // convert data to byte arrays
    byte[] msgBytes = inputText.getBytes();   
    byte[] lenBs = intToBytes(msgBytes.length);

    int totalLen = lenBs.length + msgBytes.length;
    byte[] stego = new byte[totalLen];    

   
    
    System.arraycopy(lenBs, 0, stego, 0, lenBs.length);          //System.arraycopy ka arg(source, source start, destinatn, destinatn start, destinatn's no. of bytes)
    System.arraycopy(msgBytes, 0, stego, lenBs.length, msgBytes.length); 

    // System.out.println("Num. pixels to store message " + i + ": " + totalLen*DATA_SIZE);
    return stego;
  }



  private static byte[] intToBytes(int i)
  
  {
   
    byte[] integerBs = new byte[MAX_INT_LEN];
    integerBs[0] = (byte) ((i >>> 24) & 0xFF);
    integerBs[1] = (byte) ((i >>> 16) & 0xFF);
    integerBs[2] = (byte) ((i >>> 8) & 0xFF);
    integerBs[3] = (byte) (i & 0xFF);

    // for (int j=0; j < integerBs.length; j++)
    //  System.out.println(" integerBs[ " + j + "]: " + integerBs[j]);

    return integerBs;
  }



  private static BufferedImage loadImage(String imFnm)
  
  {
    BufferedImage im = null;
    try {
      im = ImageIO.read( new File(imFnm) );
      System.out.println("Read " + imFnm);
    } 
    catch (IOException e) 
    { System.out.println("Could not read image from " + imFnm);  }

    return im;
  }



  private static byte[] accessBytes(BufferedImage image)
  
  {
    WritableRaster raster = image.getRaster();
    DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();
    return buffer.getData();
  }



  private static boolean singleHide(byte[] imBytes, byte[] stego, String pass)
  
  {
    int imLen = imBytes.length;
    System.out.println("Byte length of image: " + imLen);

    int totalLen = stego.length;
    System.out.println("Total byte length of message: " + totalLen);

   
  
    if ((((totalLen*DATA_SIZE)+32)*4) > imLen) 
	{
      System.out.println("Image not big enough for message");
      return false;
    }
	
	hideStego(imBytes, pass.getBytes(), 0);
    hideStego(imBytes, stego, 32);  
	
    return true;
  }
    


  private static void hideStego(byte[] imBytes, byte[] stego, int offset)
  
  {
	offset*=4;
    for (int i = 0; i < stego.length; i++) {       
      int byteVal = stego[i];
      for(int j=7; j >= 0; j--) {    
        int bitVal = (byteVal >>> j) & 1;

        
        imBytes[offset] = (byte)((imBytes[offset] & 0xFE) | bitVal); //MAsk and insert
        offset+=4;
      }
    }
  } 




  private static String getFileName(String fnm)
 
  {
    int extPosn = fnm.lastIndexOf('.');
    if (extPosn == -1) {
      System.out.println("No extension found for " + fnm);
      return fnm;   
    }

    return fnm.substring(0, extPosn);
  }




  private static boolean writeImageToFile(String outFnm, BufferedImage im)
  // save the im image in a PNG file called outFnm
  {
    if (!canOverWrite(outFnm))
      return false;

    try {
      ImageIO.write(im, "png", new File(outFnm));
      System.out.println("Image written to PNG file: " + outFnm);
      return true;
    } 
    catch(IOException e)
    { System.out.println("Could not write image to " + outFnm); 
      return false;
    }
  }



  private static boolean canOverWrite(String fnm)
  
  {
    File f = new File(fnm);
    if (!f.exists())
      return true;     // can overewrite since the file is new

  
    Scanner in = new Scanner(System.in);
    String response;
    System.out.print("File " + fnm + " already exists. and will be overwrited");
    /*while (true) {
      System.out.print("Overwrite (y|n)? ");
      response = in.nextLine().trim().toLowerCase();
      if (response.startsWith("n"))  // no
        return true;
      else if (response.startsWith("y"))  // yes
        return true;
    }*/
	return true;
  }




  // --------------------------- reveal a message -----------------------------------


  public static String reveal(String imFnm, String pass)
  
  {
   
    BufferedImage im = loadImage(imFnm);
    if (im == null)
      return "NO IMAGE FOUND";
    byte[] imBytes = accessBytes(im);
    System.out.println("Byte length of image: " + imBytes.length);

    byte[] passfile = extractfilename(imBytes,4,0);
	StringBuffer passf= new StringBuffer();
	for(int i=0;i<passfile.length;i++)
	{passf.append((char)passfile[i]);}
	
	String passf1=passf.toString();
	passf1.trim();
	System.out.println(passf1);
	if(!pass.equals(passf1))
	{
		return "WRONG PASSWORD";
	}
	
    int msgLen = getMsgLength(imBytes, 32);   
    if (msgLen == -1)
      return "NO MESSAGE FOUND";
    System.out.println("Byte length of message: " + msgLen);
	//System.out.println("aaya");
	
	byte[] fname = extractfilename(imBytes, 30,((MAX_INT_LEN*DATA_SIZE)+32));	//getfilename
	//System.out.println("aaya");
	String filename = new String(fname);
	filename.trim();
	//System.out.println("aaya" + filename);
	
    
	int L=(MAX_INT_LEN*DATA_SIZE)+(30*DATA_SIZE)+32;
    byte[] msg = getMessage(imBytes, msgLen-30, L);  
	//System.out.println("aaya" +msg1[5]);	
    if (msg != null)
	{
     // String fnm = getFileName(imFnm);
	
	
	try{
		FileOutputStream fout=new FileOutputStream(filename);	
		File file12=new File(filename);
		fout.write(msg,0,msg.length);
		}catch(Exception e){}
	return filename;      
	  
	  
	  
	
    }
    else {
      System.out.println("No message found");
      return "No message found";
    }
  }  
    


  private static int getMsgLength(byte[] imBytes, int offset)
  
  {
    byte[] lenBytes = extractHiddenBytes(imBytes, MAX_INT_LEN, offset);
  
    if (lenBytes == null)
      return -1;

    // for (int j=0; j < lenBytes.length; j++)
    //  System.out.println(" lenBytes[ " + j + "]: " + lenBytes[j]);

    // convert the byte array into an integer
    int msgLen = ((lenBytes[0] & 0xff) << 24) | 
                 ((lenBytes[1] & 0xff) << 16) | 
                 ((lenBytes[2] & 0xff) << 8) | 
                  (lenBytes[3] & 0xff);
    // System.out.println("Message length: " + msgLen);

    if ((msgLen <= 0) || (msgLen > imBytes.length))  {
      System.out.println("Incorrect message length" + msgLen);
      return -1;
    }
    // else
    //  System.out.println("Revealed message length: " + msgLen);

    return msgLen;
  }



  private static byte[] getMessage(byte[] imBytes, int msgLen, int offset)
  
  {
    byte[] msgBytes = extractHiddenBytes(imBytes, msgLen, offset); 
    
    if (msgBytes == null)
      return null;

    //String msg = new String(msgBytes);
	return msgBytes;
    
  }

static byte[] extractfilename(byte[] imBytes,int size,int offset)
{	
	byte[] hiddBytes = extractHiddenBytes(imBytes,size,offset);
	
    
	return hiddBytes;
}	

  private static byte[] extractHiddenBytes(byte[] imBytes, int size, int offset)
 
  {
    int finalPosn = offset + (size*DATA_SIZE);
    if (finalPosn > imBytes.length) {
      System.out.println("End of image reached");
      return null;
    }

    byte[] hiddenBytes = new byte[size];
	offset*=4;
    for (int j = 0; j < size; j++) { 
      for (int i=0; i < DATA_SIZE; i++) { 
        hiddenBytes[j] = (byte) ((hiddenBytes[j] << 1) | (imBytes[offset] & 1));   
                             
        offset+=4;
      }
    }
    return hiddenBytes;
  } 




/*public static void main(String args[])
{	
	Scanner sc=new Scanner(System.in);
	Steganography s= new Steganography();
	String s4="1";
	String s5="2";
	
	if(s4.equals(args[0]))
	{	
		System.out.println("Enter txt file name");
		String s1="";
		String s2="";
		
		s1=sc.next();
		System.out.println("Enter image file name");
		s2=sc.next();
		s.hide(s1,s2);
	}
	
	if(s5.equals(args[0]))
	{
		System.out.println("Enter image file name");
		String s1="";
		s1=sc.next();
		s.reveal(s1);
	}

}*/
}