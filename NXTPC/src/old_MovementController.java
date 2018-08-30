import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;


public class old_MovementController {
	
	static DifferentialPilot rover = new DifferentialPilot(4.5f,14.f,Motor.A, Motor.B);
	
	//location coordinates
	static int x=0;
	static int y=0;
	static int theta =0;
	
	public void initialize()
	{
	rover.setTravelSpeed(20.0f);
	rover.setRotateSpeed(180);
	System.out.println(x + " " + y + " " + theta);
	}
	
	public void move(char c)
	{
	
	switch(c)
	{
	case 'w':
	{
		rover.travel(20.0f);
		updatelocation(c);
		System.out.println(x + " " + y + " " + theta);
		break;
	}
	case 'a':
	{
		rover.rotate(90.0f);
		updatelocation(c);
		System.out.println(x + " " + y + " " + theta);
		break;
	}
	case 's':
	{
		rover.travel(-20.0f);
		updatelocation(c);
		System.out.println(x + " " + y + " " + theta);
		break;
	}
	case 'd':
	{
		rover.rotate(-90.0f);
		updatelocation(c);
		System.out.println(x + " " + y + " " + theta);
		break;
	}
	}
	}
	
	public void updatelocation(char c)
	{
		switch(c)
		{
		case 'w':
		{
			if(theta == 0)
				y = y + 20;
			if(theta == 90)
				x = x - 20;
			if(theta == 180)
				y = y - 20;
			if(theta == 270)
				x = x + 20;
			break;
		}
		case 's':
		{
			if(theta == 0)
				y = y - 20;
			if(theta == 90)
				x = x + 20;
			if(theta == 180)
				y = y + 20;
			if(theta == 270)
				x = x - 20;
			break;
		}
		case 'a':
		{
			theta = theta + 90;
			if(theta == 360)
				theta = 0;
			break;
		}
		case 'd':
		{
			theta = theta - 90;
			if(theta == -90)
				theta = 270;
			break;
		}
			
	}

}

}
