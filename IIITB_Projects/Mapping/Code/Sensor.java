package Mapping;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

/*
 * This part of the program is responsible for controlling the Ultrasonic sensor, obtaining the distance, and the 
 * angle of the motor reading. 
 * The ultrasonic sensor sits on top of a motor.
 */

public class Sensor {
	
	UltrasonicSensor us;
	
	Sensor()
	{
		//We initialize the motor, and ultrasonic sensor
		
		us = new UltrasonicSensor(SensorPort.S1);
		Motor.C.resetTachoCount();
		Motor.C.setSpeed(90);
		Motor.C.rotateTo(0);
		us.continuous();
	}
	
	public void reinitialize()
	{
		us.continuous();
		Motor.C.rotateTo(0);
	}
	
	public int angle()
	{
		//This returns the current angle count of the motor
		return Motor.C.getTachoCount();
	}
	
	public int distance(int rotation)
	{
		/*
		 * Here we find the distance of a point from the UltraSonic sensor 10 times, and then calculate the 
		 * average and return the average. This is done to reduce random errors which may occur.
		 */
		int average = 0;
		Motor.C.rotateTo(rotation);
		for(int i=0; i<10; i++)
		{
			average = average + us.getDistance();
		}
		return average/10;
	}

}
