
public class MazeRunner {

	/**
	 * @param args
	 */
	
	static UltraSensePC usp;
	static MazeMzovementController bot;
	static MazePositionController pos;
	static int cardinal_distance[];
	static int DistanceFromLeftWall;
	static int DistanceFromRightWall;
	static final int forward = 0;
	static final int left = -95;
	static final int right = 90;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int i=0;
		
		usp = new UltraSensePC();
		usp.initialize();
		
		bot = new MazeMovementController();
		pos = new MazePositionController();
		
		cardinal_distance = new int[3];
		
		Collision_Avoidance ca = new Collision_Avoidance(usp);
		
		while(i<2)
		{
		
		synchronized(usp)
		{
			Cardinal_Sense();
			usp.reinitialize();
		}
		
		bot.forward();

		synchronized(usp)
		{
		DistanceFromLeftWall = usp.ReadingFromWall(left);
		
		usp.reinitialize();
		}
		
		System.out.println("Distance from left wall " + DistanceFromLeftWall);
		
		double angle_correction = pos.ParallelWall(DistanceFromLeftWall, cardinal_distance[1], MazeMovementController.move);
		
		System.out.println("Angle of correction " + angle_correction);
		
		bot.Rotation_Correction(angle_correction);
		
		if(angle_correction == 0)
		{
			double distance_to_travel_midpoint = pos.DirectToMidPoint(cardinal_distance[1], cardinal_distance[2], MazeMovementController.move);
			System.out.println("Distance To Midpoint at 30 " + distance_to_travel_midpoint);
			
			bot.midpoint_correction(distance_to_travel_midpoint);
			}
		
		i++;
		
		}
	}
	
	public static void Cardinal_Sense()
	{
		cardinal_distance[0] = usp.getReadingsInCardinalDirections(forward);
		
		System.out.println("Forward " + cardinal_distance[0]);
		
		cardinal_distance[1] = usp.getReadingsInCardinalDirections(left);
		
		System.out.println("Left " + cardinal_distance[1]);
		
		cardinal_distance[2] = usp.getReadingsInCardinalDirections(right);
		System.out.println("Right " + cardinal_distance[2]);
		
	}	

}

	
