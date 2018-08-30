package Mapping;


/*
 * This program is responsible for mapping the surrounding of the NXT Robot. It measures the distance of a point using 
 * the ultrasonic sensor. The values which are initially in polar coordinates are converted to cartesian coordinates and then
 * are plotted on the XY graph. 
 * For the GUI portion of the program I use Java Swing library, in particular JPanel. 
 * The graph obtained can then be saved, and also processed to remove outliers. 
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
public class TestMain {
    
    static Test t;
    
    TestMain()
    {
    	//Jframe is the window which will hold the JPanel, where the Graph and the values are directly painted.
        JFrame jfrm = new JFrame();
        jfrm.setSize(500,500);
        jfrm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jfrm.setBackground(Color.WHITE);
        jfrm.setLayout(new BorderLayout());
        t = new Test();
        
        JPanel buttonPanel = new JPanel();
        //Add two buttons which are responsible for the saving and processing actions.
        JButton jb1 = new JButton("Save");
        JButton jb2 = new JButton("Process");
        jb1.addActionListener(new ActionListener()
        		{
        			@Override
        			public void actionPerformed(ActionEvent ae)
        			{
        				t.SaveImage();
        			}
        			
        		});
        
        jb2.addActionListener(new ActionListener()
        {
        	/*
        	 * (non-Javadoc)
        	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
        	 * The process button opens a new Jframe window with a new JPanel which is created by the Process class 
        	 * in another part of the program. 
        	 */
        	@Override
        	public void actionPerformed(ActionEvent ae)
        	{
        		JFrame ProcessedJframe = new JFrame("Processed");
        		ProcessedJframe.setSize(700,700);
                ProcessedJframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ProcessedJframe.setBackground(Color.WHITE);
                ProcessedJframe.setLayout(new BorderLayout());
                
                Process p = new Process(t.point);
                ProcessedJframe.add(p);
                ProcessedJframe.setVisible(true);
                p.setVisible(true);
        	}
        });
        
        buttonPanel.add(jb1);
        buttonPanel.add(jb2);
        
        
        
        
        jfrm.add(t);
        jfrm.add(buttonPanel,BorderLayout.PAGE_END);
        
        jfrm.setVisible(true);
        jb1.setVisible(true);
        jb2.setVisible(true);
        
    }
    
    public static void main(String args[])
    {
    	try
    	{
    	SwingUtilities.invokeAndWait(new Runnable()
        {
            public void run()
            {
                new TestMain();
            }
        });
    	}
    	catch(Exception e)
    	{
    		
    	}
    	
    	Sensor sense = new Sensor();
    	
    	
    	int rotation = 0;
    	
        
        
        int distance;
        int angle;
       
        
        while(rotation<=180)
        {
        	/*
        	 * Here we rotate the motor and thus the UltraSonic sensor by 5 degrees, obtain the distance reading
        	 * and repeat till we have rotated 180 degree clockwise.
        	 * The values are then add to an array list of points in the Test class, these points are later plotted
        	 * on the XY graph. 
        	 */
        	distance = sense.distance(rotation);
        	angle = sense.angle();
        	System.out.println("The distance is " + distance);
        	System.out.println("The angle is " + angle);
        	t.setValue(distance,angle);
        	rotation+=5;
        	try{
        	Thread.sleep(100);
        	}
        	catch(InterruptedException e){
        		
        	}
        }
        
        //We return the motor to angle zero, and reinitialize the motor tacho count. 
        rotation = 0;
        sense.reinitialize();
        
        while(rotation>=-180)
        {
        	/*
        	 * Here we rotate the motor and thus the UltraSonic sensor by 5 degrees, obtain the distance reading
        	 * and repeat till we have rotated 180 degree counterclockwise.
        	 * The values are then add to an array list of points in the Test class, these points are later plotted
        	 * on the XY graph. 
        	 */
        	distance = sense.distance(rotation);
        	angle = sense.angle();
        	System.out.println("The distance is " + distance);
        	System.out.println("The angle is " + angle);
        	t.setValue(distance,angle);
        	
        	rotation-=5;
        	try
        	{
        		Thread.sleep(100);
        		
        	}
        	catch(InterruptedException e)
        	{
        		
        	}
        }
        
        rotation = 0;
        sense.reinitialize();
     
        }
    
    
    }
   
    
