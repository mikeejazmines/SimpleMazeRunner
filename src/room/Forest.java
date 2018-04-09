package room;

import java.lang.reflect.Method;

import anno.Command;

public class Forest {
	
	static boolean horse;
	
	public Forest()
	{
		horse=false;
	}
	
	public void getDescription()
	{
		System.out.println("It’s dark out but you decide to walk around the forest. "
				+ "While walking, a wolf starts sprinting towards you! "
				+ "The beast blocks you, and gets attacked by wolves!"
				+ "\nYou run to your horse Phillip.");
	}
	
	@Command(command="mount on horse")
	public void mountHorse()
	{
		horse=true;
		System.out.println("You jump on the horse, shoo the wolves away, and take the beast with you."
				+ "\nHe is badly wounded."
				+ "\nHe is in dire need of treatment and warmth. Where can you find a fire?");
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
				if(horse==true&&action.equals("mountHorse"))
					System.out.println("You have the beast with you already. He needs healing and warmth quick in the castle.");
				else
					System.out.println("You can " + c.command());
			}
		}
	}
	
	public static boolean isBeastWounded()
	{
		return horse;
	}

}
