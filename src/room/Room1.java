package room;

import anno.*;

public class Room1 {

	@Direction(command="go north")
	private Room2 north;
	
	@Direction(command="Room6")
	private Room6 room;
	
	public boolean canEnter()
	{
		return true;
	}
	
	public static boolean can()
	{
		return true;
	}
	
	public String enterMessage() {
		return "You find yourself inside a dark room. You see four doors marked Room2, Room3, Room4\nYou can 'look' around.\n";
	}
	
	public String unableToEnterMessage() {
		return null;
	}
	
	@Command(command="getDescription")
	public void getDescription()
	{
		System.out.println("You find yourself inside a dark room.  You see four doors marked Room2, Room3, Room4");
		System.out.println("You can 'look' around.");
		
		if(Room2.wordFound==true && Room3.wordFound==true && Room4.wordFound==true)
		{
			System.out.println("You may now access secret Room5");
		} else {
		
		}
	}
	
	@Command(command="look")
	public void look() 
	{
		System.out.println("You may 'go north'");
	}
}
