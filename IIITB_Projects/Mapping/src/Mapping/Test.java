package Mapping;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

/*
 * This program is used to paint the graph, and the points obtained from the Sense and TestMain class using the ultrasonic
 * sensor mounted ontop of the motor. 
 * I use a class from the Java Swing Library called the JPanel. 
 */
public class Test extends JPanel{
    
    static double x=0;
    static double y=0;
    static List<Shape> point = new ArrayList<Shape>();
    JButton jb;
    BufferedImage bf;
    
    
    @Override
    protected void paintComponent(Graphics g)
    {
    	//Paints the graph axis
    	super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.translate(getWidth()/2, getHeight()/2);
        g2D.draw(new Line2D.Double(0,getHeight()/2,0,-getHeight()/2));
        g2D.draw(new Line2D.Double(getWidth()/2,0,-getWidth()/2,0));
        
        
        g2D.setStroke(new BasicStroke(1.6f));
        int i,j;
        
        for(j=0; j<=getWidth(); j+=10)
        {
            g2D.draw(new Line2D.Double(0,j,0,j));
        }
        for(j=0; j>=-getWidth(); j-=10)
        {
            g2D.draw(new Line2D.Double(0,j,0,j));
 
        }
        for(i=0; i<=getHeight(); i+=10)
        {
            g2D.draw(new Line2D.Double(i,0,i,0));
        }
        for(i=0; i>=-getHeight(); i-=10)
        {
            g2D.draw(new Line2D.Double(i,0,i,0));
        }
        
        g2D.setStroke(new BasicStroke(1.9f));
        
        g2D.setColor(Color.BLUE);
        
        //paints the points 
        for(Shape s : point)
        {
            g2D.draw(s);
        }
           
    }
        
    
   
    public void setValue(int distance, int angle)
    {
    	//converts the polar coordinates to cartesian coordinates
        x = (distance * Math.sin(Math.toRadians(angle)));
        y =  (distance * Math.cos(Math.toRadians(angle)));
        System.out.println("Value of x1 is " + x);
        System.out.println("Value of y1 is " + y); 
        point.add(new Ellipse2D.Double(x,-y,2,2));
        repaint();
        
    }
    
    public void UpdateCanvas()
    {
    	// Updates the canvas and BufferedImage. The BufferedImage is used to save the image into a file.
        bf = new BufferedImage(600,600,BufferedImage.TYPE_INT_ARGB);
        bf = resize(bf,getWidth(),getHeight());
        
        System.out.println("Update Canvas");
        
        Graphics2D g2D = bf.createGraphics();
        g2D.setColor(Color.WHITE);
        g2D.fillRect(0,0,getWidth(),getHeight());
        g2D.setColor(Color.BLACK);
        g2D.translate(getWidth()/2, getHeight()/2);
        g2D.draw(new Line2D.Double(0,getHeight()/2,0,-getHeight()/2));
        g2D.draw(new Line2D.Double(getWidth()/2,0,-getWidth()/2,0));
        g2D.setStroke(new BasicStroke(1.6f));
        int i,j;
        
        for(j=0; j<=getWidth(); j+=10)
        {
            g2D.draw(new Line2D.Double(0,j,0,j));
        }
        for(j=0; j>=-getWidth(); j-=10)
        {
            g2D.draw(new Line2D.Double(0,j,0,j));
 
        }
        for(i=0; i<=getHeight(); i+=10)
        {
            g2D.draw(new Line2D.Double(i,0,i,0));
        }
        for(i=0; i>=-getHeight(); i-=10)
        {
            g2D.draw(new Line2D.Double(i,0,i,0));
        }
        
        g2D.setStroke(new BasicStroke(1.9f));
        
        g2D.setColor(Color.BLUE);
        
        for(Shape s : point)
        {
            g2D.draw(s);
        }
       
        bf = resize(bf,getWidth(),getHeight());
        
    }
    
    public BufferedImage resize(BufferedImage bf, int width, int height)
    {
    	//Used for resizing operations
        Image tmp = bf.getScaledInstance(width,height, Image.SCALE_SMOOTH);
        BufferedImage newbf = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2D = newbf.createGraphics();
        g2D.drawImage(tmp,0,0,null);
        g2D.dispose();
        
        return newbf;
        
    }
    
    public void SaveImage()
    {
    	//Saves the image.
        File f = new File("FIRSTSAVE.png");
        UpdateCanvas();
        try
        {
        ImageIO.write(resize(bf,getWidth(), getHeight()),"PNG",f);
        System.out.println("Saved" + System.getProperty("user.dir"));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
