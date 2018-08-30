import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;


public class RemoteControl extends Thread {
	
	Controller controller;
	public float x_axis,y_axis,acceleration,brake;
	Brain brain;
	
	RemoteControl(Brain brain)
	{
		this.brain = brain;
		Thread t = new Thread(this, "Remote Control");
		t.setPriority(MAX_PRIORITY);
		t.start();
	}
	
	public void run()
	{
		try
		{
			Controllers.create();
		}
		catch(LWJGLException e)
		{
			e.printStackTrace();
		}
		Controllers.poll();
		controller = Controllers.getController(0);
		controller.setDeadZone(0, 0.4f);
		controller.setDeadZone(1, 0.4f);
		
		while(true)
		{
			controller.poll();
			
			x_axis = (float) controller.getAxisValue(0);
			y_axis = (float) controller.getAxisValue(1);
			acceleration = (float) controller.getAxisValue(5);
			brake = (float) controller.getAxisValue(4);
			//System.out.println("Acceleration is " + acceleration);
			
			brain.setValues(x_axis, y_axis, acceleration, brake);
			
			try
			{
				Thread.sleep(100);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
	
		}
		
	}
	
	

}
