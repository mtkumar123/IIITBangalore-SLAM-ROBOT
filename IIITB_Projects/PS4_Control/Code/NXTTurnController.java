import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;


public class NXTTurnController extends Thread {
	
	Brain brain;
	Thread t;
	public float x_axis,y_axis,acceleration,brake,circle,angle;
	

	NXTTurnController(Brain brain)
	{
		this.brain = brain;
		Motor.C.setPower(100);
		Motor.C.resetTachoCount();
		Thread t = new Thread(this, "NXTTurnController");
		t.start();
	}
	
	public void run()
	{
		try
		{
			Thread.sleep(1100);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		while(true)
	
		{
			synchronized(brain)

		{
			while(brain.movement_complete == false)
			{
				try
				{
					wait();
				}
				catch(InterruptedException e)
				{
				
				}
			}
			//System.out.println("NXTTURN CONTROLLER GONNA GET VALUES");
			
			getValue();
		}
			
			if(circle >= 0.9 && circle <= 1.2 && y_axis <= 0)
			{
				turn();
			}
			
			else
				NoTurn();
			
			try
			{
				Thread.sleep(100);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}	
		
		try {
			brain.TurnComplete();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		/* while(true)
		{
			getValue();
			if(circle >= 0.9 && circle <= 1.2 && y_axis <= 0)
			{
				turn();
			}
			try
			{
				Thread.sleep(200);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			
		} */
		
	}
	
	
	public void getValue()
	{
		float values[] = brain.getValues();
		x_axis = values[0];
		y_axis = values[1];
		acceleration = values[2];
		brake = values[3];
		
		circle = (float) (Math.pow(x_axis,2) + Math.pow(y_axis,2));
		if(circle >= 0.9 && circle <= 1.2 && y_axis <= 0)
		{
			angle = (float) Math.toDegrees(Math.atan(x_axis/y_axis));
			if(angle >= 90)
			{
				angle = -70;
			}
			if(angle <= -90)
			{
				angle = +70;
			}
		} 
	}
	
	public void turn()
	{
			
			Motor.C.rotateTo((int) -angle,true);
			System.out.println("Turn " + -angle);
		
		
		/*while(circle >= 0.9 && circle <= 1.2 && y_axis <= 0)
		{
			System.out.println("Turn " + -angle);
			Motor.C.rotateTo((int) -angle,true);
			try
			{
				Thread.sleep(200);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			getValue();
		}
		Motor.C.rotateTo(0); */
		
		
	}
	
	public void NoTurn()
	{
		Motor.C.rotateTo(0);
		System.out.println("No Turn");
	}

}


