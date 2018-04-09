package room;

import java.lang.reflect.Method;

import anno.Command;

public class Balcony implements Help {
	
	boolean paper;
	boolean towel;
	boolean teacup;
	boolean book;
	boolean clues;
	boolean knife;
	
	public Balcony()
	{
		clues=false;
		book = Library.hasBook();
		towel = Fireplace.woundsTreated();
		teacup= Kitchen.hasCup();
		paper=Ballroom.hasPaper();
		knife=Kitchen.hasKnife();
		if(book&&towel&&teacup&&paper)
		{
			clues=true;
		}
	}
	
	public void getDescription()
	{
		if(!clues)
		{
			System.out.println("You see a vase with a rose encased in it. A petal falls. "
					+ "As you approach it, the Beast storms in and roars at you. "
					+ "You are paralyzed with fear and turn to leave.");
		}
		else
		{
			System.out.println("Gaston enters and stabs the beast! You must defend yourselves!");
			if(!knife)
				System.out.println("You realize you have nothing to fight Gaston with except your bare hands! He kicks you out of the window and you die");
			else
			{
				System.out.println("Gaston charges for you and runs into your kitchen knife. He falls to the floor.");
				System.out.println("The beast is dying before you. You try to recall all that you’ve seen so far so you can a word that may save him.");
			}
			
		}
		
	}
	
	@Command(command="break the curse")
	public void breakCurse(String s)
	{
		if(s.equalsIgnoreCase("LOVE"))
			System.out.println("The beast transforms into a total hottie and lives! The spell is lifted and everyone becomes human!");
		else
			System.out.println("The word meant nothing. The last rose petal falls and the beast takes his last breath. You didn’t save him.");
	}
	
	public void help() {
		Method[] meths = this.getClass().getDeclaredMethods();
		
		for( Method m : meths)
		{
			String action = m.getName();
			if(m.isAnnotationPresent(Command.class))
			{
				Command c = m.getAnnotation(Command.class);
				if(clues==false&&action.equals("breakCurse"))
					continue;
				if(clues==true&&action.equals("breakCurse"))
					System.out.println("You've already done it! You've broken the curse!");
				else
				System.out.println("You can " + c.command());
			}
		}
		
	}

}
