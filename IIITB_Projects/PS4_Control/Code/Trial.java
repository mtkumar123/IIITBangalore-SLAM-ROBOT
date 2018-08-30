import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;


public class Trial {
	
	static Controller controller; 
	static boolean press = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
			Controllers.create();
		}
		catch(LWJGLException e)
		{
			e.printStackTrace();
		}
		
		Controllers.poll();
		
		for(int i = 0; i<Controllers.getControllerCount(); i++)
		{
			System.out.println("Number of controllers " + Controllers.getControllerCount() );
			controller = Controllers.getController(i);
			System.out.println(controller.getName());
		}
		
		controller = Controllers.getController(0);
		controller.setDeadZone(0, 0.2f);
		controller.setDeadZone(1, 0.2f);
		controller.setDeadZone(2, 0.2f);
		controller.setDeadZone(3, 0.2f);
		
		while(true)
		{
			controller.poll();
			System.out.print(controller.getAxisValue(0) + "  ");
			System.out.print(controller.getAxisValue(1) + "  ");
			System.out.print(controller.getAxisValue(2) + "  ");
			System.out.print(controller.getAxisValue(3) + "  ");
			System.out.print(controller.getAxisValue(4) + "  ");
			System.out.print(controller.getAxisValue(5) + "  ");
			System.out.println("");
			
		}

	}

}
