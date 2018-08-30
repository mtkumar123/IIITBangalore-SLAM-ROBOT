package MazeRunner;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;


public class MazeMovementController {
	
	static final float move = 10.0f;
	static final float wheel_diameter = 4.5f;
	static final float wheel_base = 14.0f;
	static final int rotation_correction_angle = 30;

	DifferentialPilot rover = new DifferentialPilot(wheel_diameter,wheel_base,Motor.A, Motor.B);
	

	public void initialize()
	{
		rover.setTravelSpeed(1.0f);
		rover.setRotateSpeed(25);
	}
	
	public void forward()
	{
		rover.travel(-move);
	}
	public void forward(double value)
	{
		rover.travel(-value);
	}
	public void backward()
	{
		rover.travel(move);
	}

	public void Rotation_Correction(double angle)
	{
		System.out.println("Correcting Angle");
		rover.rotate(angle);
	}
	
	public void midpoint_correction(double distance_to_travel_to_midpoint)
	{
		if(distance_to_travel_to_midpoint<0)
		{
			Rotation_Correction(-rotation_correction_angle);
			forward(-distance_to_travel_to_midpoint);
			Rotation_Correction(rotation_correction_angle);
			
		}
		if(distance_to_travel_to_midpoint>0)
		{
			Rotation_Correction(rotation_correction_angle);
			forward(distance_to_travel_to_midpoint);
			Rotation_Correction(-rotation_correction_angle);
		}
	}
}
