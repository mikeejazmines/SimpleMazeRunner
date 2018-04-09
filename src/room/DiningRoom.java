package room;

import java.lang.reflect.Method;

import anno.Command;

public class DiningRoom {
	
	boolean dress;
	boolean flower;
	
	public DiningRoom()
	{
		dress=Bedroom.isDressed();
		flower=Garden.hasFlower();
	}
	
	public void getDescription()
	{
		if(!dress)
		{
			System.out.println("The beast doesn’t like dirty clothes in his dining room.");
		}
		else
		{
			System.out.println("Be our guest!");
		}
	}
	
	@Command(command="eat")
	public void eat()
	{
		System.out.println("Nom nom.");
	}
	
	@Command(command="give flower to Beast")
	public void giveFlower()
	{
		flower = false; 
		System.out.println("The beast thanks you and invites you to dance with him in the Ballroom.");
	}
	
	public void help()
	{
		if(!dress)
		{
			System.out.println("Sorry, nothing you can do here until you change your clothes.");
		}
		else
		{
			Method[] meths = this.getClass().getDeclaredMethods();
		
			for( Method m : meths)
			{
				String action = m.getName();
				if(m.isAnnotationPresent(Command.class))
				{
					Command c = m.getAnnotation(Command.class);
					if(flower==true&&action.equals("giveFlower"))
						continue;
					else
						System.out.println("You can " + c.command());
				}
			}
		}
	}

}
