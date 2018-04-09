package room;

import java.lang.reflect.Method;

import anno.Command;

public class Ballroom {
	
	boolean dress;
	static boolean paper; 
	
	public Ballroom()
	{
		dress=Bedroom.isDressed();
		paper=false;
	}
	
	public void getDescription()
	{
		if(!dress)
		{
			System.out.println("You feel the mirror rumble in your pocket. You look at it and see your Papa.\n" + 
					"Papa says, 'You’re going in the ballroom in that? Change your clothes daughter!'");
		}
		else
		{
			System.out.println("What a beautiful ballroom! The Beast invites you to dance with him.");
		}
	}
	
	@Command(command="dance with Beast")
	public void dance()
	{
		System.out.println("You dance with the beast.");
		if(!paper)
		{
			System.out.println("As you dance, the beast hands to you a slip of paper… It says ‘E’");
			
			System.out.println("\nYou tell the Beast you want to see your father. He says, ‘Go, you can pass through the forest.’");
			paper = true;
		}
			
	}
	
	public void help()
	{
		Method[] meths = this.getClass().getDeclaredMethods();
		
		for( Method m : meths)
		{
			if(m.isAnnotationPresent(Command.class))
			{
				Command c = m.getAnnotation(Command.class);
				System.out.println("You can " + c.command());
			}
		}
		
	}
	
	public static boolean hasPaper()
	{
		return paper;
	}

}
