import java.io.*;
import lejos.nxt.*;
import lejos.nxt.comm.*;

/**
 * sample of selecting channel at run time
 */
public class test 
{
  public static void main(String[] args) { 
	  System.out.println("Going to Connect");
      NXTConnection connection = USB.waitForConnection();
      System.out.println("Connection Obtained");
      
      DataInputStream input = connection.openDataInputStream();
      try{
      int a = input.readInt();
      System.out.println(a);
      }
      catch(IOException e)
      {
    	  System.out.println(e);
      }
      Button.waitForAnyPress();
  }
}
      
     
      
      