package MazeRunner;



public class Collision_Avoidance implements Runnable{
	
	Thread t;
	UltraSensePC usp;
	static final int forward = 0;
	static final int left = -95;
	static final int right = 90;
	
	Collision_Avoidance(UltraSensePC usp)
	{
		this.usp = usp;
		t = new Thread(this, "Collision Detector");
		System.out.println("New thread created");
		t.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("New thread running");
		synchronized(usp)
		{
			int i=0;
			while(i<10)
			{
			System.out.println("The reading in the forward direction is " + usp.Collision_Detector());
			i++;
			}
		}
		
	}
	

}
