package room;

import java.util.HashMap;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.Annotation;

import anno.*;

public class Room2 {

	Class<? extends Room2> clazz = this.getClass();
	Method[] methods = clazz.getDeclaredMethods(); 
	public static boolean wordFound;
	static boolean inPool; 
	public static boolean tookSword;
	static boolean graveFound;
	static boolean dug;
	
	@Direction(command="go east")
	private Room3 east;
	
	@Direction(command="go south")
	private Room1 south;
	
	public Room2() 
	{
		wordFound = false;
		inPool = false; 
		tookSword = false;
		dug = false;
		graveFound = false;
	}
	
	public boolean canEnter()
	{
		return true;
	}
	
	public String enterMessage() {
		return "You the door leads down some steps into an underground cave system. There is a deep pool in the middle of the cave.\nYou can command to 'look' around.\n";
	}
	
	public String unableToEnterMessage() {
		return null;
	}
	
	@Command(command="getDescription")
	public void getDescription()
	{
		System.out.println("You the door leads down some steps into an underground cave system. There is a deep pool in the middle of the cave.");
		// System.out.println("You see something shiny at the bottom of the pool.");
		// System.out.println("You can command to 'swim' around.");
		System.out.println("You can command to 'look' around.");
		System.out.println();
		
		if(Room2.wordFound==true && Room3.wordFound==true && Room4.wordFound==true)
		{
			System.out.println("You may now access secret Room5");
		} else {
		
		}
	}
	
	@Command(command="swim")
	public void swim() throws Exception
	{
		inPool = true;
		System.out.println("You find a shiny sword at the bottom.");
		System.out.println("You can command to 'take sword'");
		Method m = clazz.getDeclaredMethod("takeSword");
		m.setAccessible(true);
		
	}
	
	@Command(command="take sword")
	private void takeSword()
	{
		if (inPool)
        {
            tookSword = true;
            System.out.println("You take the bright shiny sword.");
        }
        else
        {
            System.out.println("What sword?");
        }
	}
	
	@Command(command="look")
	public void look() throws Exception 
	{
		Method n = clazz.getDeclaredMethod("dig");
		n.setAccessible(true);
		
		
		System.out.println(n.isAccessible());
		for( Method m : methods )
		{
			if(m.getName().equals("look")||m.getName().equals("getDescription"))
				continue;
			System.out.println(m.getName());
			
//			for( int n : m.getModifiers() )
//			{
//				String n.getModifiers
//			}
			 
//			if(m.isAnnotationPresent(Access.class))
//			{
//				Access a = m.getAnnotation(Access.class);
//				boolean b = a.access();
//				if(b==true)
//				{
//					System.out.println(m.getName());
//				}
//			}
			//canDo.put(m.getName(), b[0]);
		}
//		System.out.println("You can 'go east' to Room 3 or 'go south' to Room 1");
//		if(!graveFound && !inPool)
//		{
//			graveFound = true;
//			System.out.println("You find a pile rubble.  It looks like a shallow grave.");
//			System.out.println("You can command to 'dig' to see what is under it.");
//			System.out.println("You see something shiny at the bottom of the pool.");
//			System.out.println("You can command to 'swim' around.");
//		} 
//		else if(!wordFound && !tookSword)
//		{
//			System.out.println("You can command to 'dig'");
//			System.out.println("You can command to 'take sword'");
//		}
//		else if(!wordFound) {
//			System.out.println("You can command to 'dig'");
//		}
//		else if(!inPool)
//		{
//			System.out.println("You see something shiny at the bottom of the pool.");
//			System.out.println("You can command to 'swim' around.");
//		}
//		else if(!tookSword) {
//			System.out.println("You find a shiny sword at the bottom.");
//			System.out.println("You can command to 'take sword'");
//		}
//		else
//		{
//			System.out.println("You see nothing else of interest.");
//		}
	}
	
	@Command(command="dig")
    private void dig()
    {
    	
        if (!graveFound)
        {
            System.out.println("You dig into the ground and disturb the home of a poisonous snake.  It bites and you die... The End");
            System.exit(0);
        }
        else
        {
            wordFound = true;
            System.out.println("You dig up the grave and find a skeleton holding a scroll.  It contains 3 words but 2 are unreadable.  The remaining word says 'Zam'");
            
            if(Room2.wordFound==true && Room3.wordFound==true && Room4.wordFound==true)
    			{
    			System.out.println("You may now access secret Room5");
    			}
        }
        
    }
	
	public static void main(String args[]) throws Exception
	{
		graveFound = false;
		inPool = false;
		Room2 room = new Room2();
		room.look();
		
		room.swim();
		
		room.look();
	}
	
}
