package room;

import java.lang.reflect.Method;

import anno.Command;

public class Bedroom {
	
	boolean key;
	static boolean dress;
	
	Foyer f;
	
	public Bedroom()
	{
		key = f.hasKey();
		dress = false; 
	}
	
	public void getDescription()
	{
		if(key==false)
		{
			System.out.println("You need a key to enter here!");
		}
		else
		{
			System.out.println("The talking cabinet speaks to you. 'You are still in villager’s clothes. Let me dress you up!' she says.");
		}
		
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
				if(dress==true&&action.equals("getDressed"))
					System.out.println("There's nothing left to do here... Maybe try exploring other rooms.");
				else
					System.out.println("You can " + c.command());
			}
		}
	}
	
	@Command(command="get dressed")
	public void getDressed()
	{
		dress=true;
		System.out.println("You look fit for a ball! Now you can explore the castle. The library and garden might interest you.");
	}

	public static boolean isDressed()
	{
		return dress;
	}
}
