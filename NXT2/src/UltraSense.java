import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;


public class UltraSense {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UltrasonicSensor us = new UltrasonicSensor(SensorPort.S1);
		int arr[] = new int[8];
		if(us.getDistances(arr) == 0)
		{
			for(int i=0;i<8;i++)
			System.out.println(arr[i]);
		}
		
		
	}

}
