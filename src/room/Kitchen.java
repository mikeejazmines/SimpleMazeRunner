package room;

import java.lang.reflect.Method;

import anno.Command;

public class Kitchen {
	
	static boolean teacup;
	static boolean knife;
	
	public Kitchen()
	{
		teacup=false;
		knife=false;
	}

	public void getDescription()
	{
		System.out.println("You see many things in the kitchen that you can pick up, like a knife and a teacup.");
	}
	
	@Command(command="take teacup")
	public void takeCup()
	{
		teacup=true;
		System.out.println("The tea cup has a ‘V’ engraved on it. Interesting.");
	}
	
	@Command(command="take knife")
	public void takeKnife()
	{
		knife=true;
		System.out.println("You store the knife hidden in your dress. Maybe this will come in handy later.");
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
				if(teacup==true&&action.equals("takeCup"))
					continue;
				if(knife==true&&action.equals("takeKnife"))
					continue;
				if(teacup==true&&knife==true)
					System.out.println("There's nothing left to do here... Maybe try exploring other rooms.");
				else
					System.out.println("You can " + c.command());
			}
		}
	}
	
	public static boolean hasCup()
	{
		return teacup;
	}
	
	public static boolean hasKnife()
	{
		return knife;
	}
}
