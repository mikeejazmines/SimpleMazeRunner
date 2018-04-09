package room;

import java.lang.reflect.Method;

import anno.Command;

public class Garden {
	
	static boolean flower;
	
	public Garden()
	{
		flower=false;
	}

	public void getDescription()
	{
		System.out.println("You see beautiful flowers that will make a for a sweet and simple gesture.");
	}

	@Command(command="pick flower")
	public void pickFlower()
	{
		flower = true; 
		System.out.println("You have picked a flower. Maybe you can give this to someone...");
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
				if(flower==true&&action.equals("pickFlower"))
					System.out.println("There's nothing left to do here... Maybe try exploring other rooms.");
				else
					System.out.println("You can " + c.command());
			}
		}
		
	}
	
	public static boolean hasFlower()
	{
		return flower;
	}
}
