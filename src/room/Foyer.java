package room;

import java.lang.reflect.Method;

import anno.Command;

public class Foyer implements Help {
	
	boolean talk;
	boolean dance;
	boolean key; 
	
	public Foyer()
	{
		talk = false; 
		dance = false;
		key = false;
	}
	
	public void getDescription()
	{
		System.out.println("Welcome!");
		System.out.println("You are Belle and you are stuck in the Beast’s castle. "
				+ "\nHe has a number of tasks for you to do before he sets you free. "
				+ "\nThe other objects also tell you that the beast can be made good if learns how to love. "
				+ "\n\nYou will have tasks to do. You have to find clues throughout the castle to figure out how to break the spell. "
				+ "\nTo start off, the Beast has given you a handheld mirror and left you the key to the bedroom."
				+ "\n\nYou have obtained a MIRROR!"
				);
	}
	
	@Command(command="talk to Lumiere and Cogsworth")
	public void talkToLumiereAndCogsworth()
	{
		System.out.println("You must break the spell and win the beast’s heart! We will teach you how to dance.");
		talk=true;
	}
	
	@Command(command="learn to dance")
	public void learnToDance()
	{
		System.out.println("Congratulations! Now you are ready to dance.");
		dance=true;
	}
	
	@Command(command="take key")
	public void takeKey()
	{
		System.out.println("You have obtained your KEY. Now you can enter your bedroom.");
		key=true;
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
				if(talk==false && action.equals("learnToDance"))
					continue;
				if(key==true&&action.equals("takeKey"))
					continue;
				if(dance==true&&action.equals("learnToDance"))
					continue;
				System.out.println("You can " + c.command());
			}
		}
		
	}
	
	public boolean canDance()
	{
		return dance;
	}
	
	public boolean hasKey()
	{
		return key;
	}
	
	public static void main(String[] args)
	{
		Foyer f = new Foyer();
		f.help();
		f.talkToLumiereAndCogsworth();
		f.help();
	}

}
