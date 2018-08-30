package Mapping;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * This program is used to remove outliers from the map, which provides a small processing insight.
 */
public class Process extends JPanel {
	
	List<Shape> point = new ArrayList<Shape>();
	List<Shape> processed_point =  new ArrayList<Shape>();
	
	Process(List<Shape> point)
	{
		this.point = point;
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		process(point);
		
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
        
        for(Shape s : processed_point)
        {
            g2D.draw(s);
        } 
        Color c = new Color(0.0f,0.0f,0.0f,0.4f);
        g2D.setColor(c);
        double x1,y1,x2,y2,x3,y3,x4,y4,x5,y5,average_slope = 0;
        
        /*for(i = 0; i<processed_point.size()-4; i+=3)
        {
        	x1 = ((Ellipse2D.Double)processed_point.get(i)).getX();
        	y1 = ((Ellipse2D.Double)processed_point.get(i)).getY();
        	x2 = ((Ellipse2D.Double)processed_point.get(i+1)).getX();
        	y2 = ((Ellipse2D.Double)processed_point.get(i+1)).getY();
        	x3 = ((Ellipse2D.Double)processed_point.get(i+2)).getX();
        	y3 = ((Ellipse2D.Double)processed_point.get(i+2)).getY();
        	x4 = ((Ellipse2D.Double)processed_point.get(i+3)).getX();
        	y4 = ((Ellipse2D.Double)processed_point.get(i+3)).getY();
        	
        	average_slope = ((y2-y1)/(x2-x1) + (y3-y2)/(x3-x2) + (y4-y3)/(x4-x3))/3;
        	System.out.println(average_slope);
        	
        	x5 = x4;
        	
        	y5 = y1 + (average_slope*(x5-x1));
        	
        	g2D.draw(new Line2D.Double(x1,y1,x5,y5));
        } */
        
       
		
	}
	
	public void process(List<Shape> points)
	{
		double x1,y1,x2,y2;
		for(int i=0; i<points.size()-1; i++)
		{
			x1 = ((Ellipse2D.Double)points.get(i)).getX();
			y1 = ((Ellipse2D.Double)points.get(i)).getY();
			x2 = ((Ellipse2D.Double)points.get(i+1)).getX();
			y2 = ((Ellipse2D.Double)points.get(i+1)).getY();
			
			
			if(Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2)) < 20)
			{
				processed_point.add(new Ellipse2D.Double(x1,y1,2,2));
			}
		}
		
	}

}
