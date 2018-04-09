package room;

import java.lang.reflect.Method;

import anno.Command;

public class Fireplace {
	
	static boolean towel;
	boolean horse;
	
	public Fireplace()
	{
		horse=Forest.isBeastWounded();
		towel=false;
	}
	
	public void getDescription()
	{
		System.out.println("You are in a warm, cozy room with a blazing fire underneath the mantle.");
		if(horse)
			System.out.println("You see a damp towel and a basin of water in the corner, perfect for washing off blood.");
	}
	
	@Command(command="treat wounds")
	public void treatWounds()
	{
		towel=true;
		System.out.println("As you treat his wounds, you notice that the bloodstain on the towel forms an ‘L’. You wrap his wound, keep the towel, and he falls asleep");
	}
	
	@Command(command="check fireplace")
	public void checkFireplace()
	{
		System.out.println("The fire is warm.");
	}
	
	public void help()
	{
		Method[] meths = this.getClass().getDeclaredMethods();
		
		for( Method m : meths)
		{
			String action = m.getName();
			if(m.isAnnotationPresent(Command.class))
			{
				Command c = m.getAnnotation(Command.class);
				if(towel==true&&action.equals("treatWounds"))
					continue;
				System.out.println("You can " + c.command());
			}
		}
	}

	public static boolean woundsTreated()
	{
		return towel; 
	}
}
