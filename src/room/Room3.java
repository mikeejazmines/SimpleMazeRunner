package room;

import anno.*;

public class Room3  {

	public static boolean wordFound;
	static boolean babyDead;
	static boolean chestFound;
	
	@Direction(command="go west")
	private Room2 west;
	@Direction(command="go north")
	private Room5 north;
	@Direction(command="go south")
	private Room4 south;
	
	public Room3() 
	{
		wordFound = false;
		babyDead = false;
		chestFound = false;
	}
	
	public boolean canEnter()
	{
		return true;
	}
	
	public String enterMessage() {
		return "You enter a large cavern and hear deep laboured breathing.\n"
				+ "In the center of the chamber is small baby dragon sleeping on a big pile of gold coins.\n"
				+ "You can 'attack' the dragon.\n"
				+ "You can 'look' around.\n";
	}
	
	public String unableToEnterMessage() {
		return null;
	}
	
	@Command(command="getDescription")
	public void getDescription()
	{
		System.out.println("You enter a large cavern and hear deep laboured breathing.");
		System.out.println("In the center of the chamber is small baby dragon sleeping on a big pile of gold coins.");
		System.out.println("You can 'attack' the dragon.");
		System.out.println("You can 'look' around.");
		System.out.println();
		
		if(Room2.wordFound==true && Room3.wordFound==true && Room4.wordFound==true)
		{
			System.out.println("You may now 'go north' to secret Room5");
		} else {
		
		}
	}
	
	@Command(command="look")
    public void look()
    {
		System.out.println("You can 'go west' to Room 2 or 'go south' to Room 4");
    	
        if (!chestFound)
        {
            chestFound = true;
            
            if (!babyDead)
            {
            		System.out.println("In the center of the chamber is small baby dragon sleeping on a big pile of gold coins.");
    				System.out.println("You can 'attack' the dragon.");
            		System.out.println("You quietly avoid the baby dragon and make your way to the other side of the chamber and find a chest.");
            		System.out.println("You can 'open chest'");
            }
            else
            {
            		System.out.println("You make your way to the other side of the chamber and find a chest.");
            		System.out.println("You can 'open chest'");            
            }
        }
        else
        {
            if (!babyDead)
            {
            		System.out.println("In the center of the chamber is small baby dragon sleeping on a big pile of gold coins.");
        			System.out.println("You can 'attack' the dragon.");
            		System.out.println("Other than the sleeping baby dragon.  There is nothing of interest.");
            }
            else
            {
            		System.out.println("There is nothing of interest.");                
            }
        }
    }
	
	@Command(command="attack")
    public void attack()
    {

        if (Room2.tookSword)
        {
        		babyDead = true;
            System.out.println("You charge the baby dragon with your bright shiny sword.  You cleave its head clean off.");
            System.out.println("You can 'look' around.");
        }
        else
        {
        		System.out.println("You charge the baby dragon and try to take in on with your bare hands.  Its wakes and bites your head clean off... The End");
            System.exit(0);
        }
    }

	
    
	@Command(command="open chest")
    public void openChest()
    {
    	
        if (chestFound)
        {
            wordFound = true;
            System.out.println("Inside is a book.  A page is ear-marked and the word 'Ala' written in blood.");
            
            if(Room2.wordFound==true && Room3.wordFound==true && Room4.wordFound==true)
    			{
    				System.out.println("You may now access secret Room5");
    			}
        }
        else
        {
            System.out.println("What chest?");
        }
    }
	
}
