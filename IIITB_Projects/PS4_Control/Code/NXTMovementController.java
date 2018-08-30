import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.robotics.navigation.DifferentialPilot;


public class NXTMovementController extends Thread {
	
	NXTMotor A;
	Brain brain;
	Thread t;
	public float x_axis,y_axis,acceleration,brake;
	
	
	NXTMovementController(Brain brain)
	{
		this.brain = brain;
		A = new NXTMotor(MotorPort.A);
		A.setPower(50);
		t = new Thread(this, "NXTMovement Controller");
		t.start();
	}
	
	
	
	public void run()
	{
		try
		{
			Thread.sleep(1000);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		
		while(true)
		{
			synchronized(brain)
			{
				while(brain.movement_complete == true)
				{
					try
					{
						wait();
					}
					catch(InterruptedException e)
					{
					
					}
				}
				//System.out.println("NXTMOVEMENT CONTROLLER GONNA GET VALUES");
				getValue();
			}
			
			if(acceleration>=0)
			{
			forward();
			}
			else if(brake>=0)
			backward();
			
			else
			stopMotor();
			
			try {
				brain.MovementComplete();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		/*while(true)
		{
			getValue();
			
			if(acceleration>0)
			{
				forward();
			}
			
			if(brake>0)
			{
				backward();
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
	}
	
	public void forward()
	{

			A.forward();
			System.out.println("Forward");


		
		/* while(acceleration>0)
		{
			float power = (acceleration * 100)-50;
			A.setPower((int)power);
			B.setPower((int)power);
			System.out.println("Forward");
			A.forward();
			B.forward(); 
			try
			{
				Thread.sleep(400);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			getValue();
		}

		A.stop();
		B.stop(); */
	}
	
	public void backward()
	{
		A.backward();
		System.out.println("Backward");
			
		
		
		/*while(brake>0)
		{
			float power = (brake * 100)-50;
			A.setPower((int)power);
			B.setPower((int)power);
			System.out.println("Backward");
			A.backward();
			B.backward();
			
			try
			{
				Thread.sleep(400);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			
			getValue(); 
		}
		
		A.stop();
		B.stop(); */
	}
	
	public void stopMotor()
	{
		A.stop();
		System.out.println("Stop");
	}
	
	

}
