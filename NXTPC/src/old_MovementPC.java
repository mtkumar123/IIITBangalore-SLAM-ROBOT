import lejos.nxt.Motor;
import lejos.pc.comm.*;
import lejos.robotics.navigation.DifferentialPilot;

import java.util.Scanner;

public class old_MovementPC {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);
		char c;	
		old_MovementController movement = new old_MovementController();
		movement.initialize();
		
		while(true)
		{	
			System.out.println("Enter Direction");
			c = in.next().charAt(0);
			movement.move(c);
			
			
		}
	}
}