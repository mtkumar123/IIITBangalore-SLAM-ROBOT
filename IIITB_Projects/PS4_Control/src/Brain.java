


public class Brain {
	
	static float values[];
	boolean movement_complete = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Brain brain = new Brain();
		values = new float[4];
		
		RemoteControl rc = new RemoteControl(brain);
		NXTMovementController nxtmove = new NXTMovementController(brain);
		NXTTurnController nxtturn = new NXTTurnController(brain);
		
		}
	
	public synchronized void setValues(float x, float y, float accel, float braking)
	{
		values[0] = x;
		values[1] = y;
		values[2]= accel;
		values[3] = braking;
	
	}
	
	public synchronized float[] getValues()
	{
	
		
		return values;
		
		
	}
	
	public synchronized void MovementComplete() throws InterruptedException
	{
		movement_complete = true;
		notify();
		//System.out.println("NXTMOVMENT CONTROLLER GONNA WAIT");
		wait();
	}
	
	public synchronized void TurnComplete() throws InterruptedException
	{
		movement_complete = false;
		notify();
		//System.out.println("NXTTURN CONTROLLER GONNA WAIT");
		wait();
	}

}
