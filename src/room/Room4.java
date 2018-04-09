package room;

import java.util.Scanner;
import anno.*;
import java.lang.Math;


public class Room4 {

	public static boolean wordFound;
	
	@Direction(command="go north")
	private Room3 north;
	
	private int x, y;
	
	
	public Room4() 
	{
		wordFound = false;
		x=(int) (Math.random()*1000);
		y=(int) (Math.random()*1000);

	}
	
	public boolean canEnter()
	{
		return true;
	}
	
	public String enterMessage() {
		return "You find yourself in an empty circular room.  On the wall opposite, you see '" + x + " * " + y + " = _'\n"
				+ "You can attempt to 'answer' the equation on the wall\n"
				+ "You can 'look' around\n";
	}
	
	public String unableToEnterMessage() {
		return null;
	}
	
	@Command(command="getDescription")
	public void getDescription()
	{
		System.out.println("You find yourself in an empty circular room.  On the wall opposite, you see '" + x + " * " + y + " = _'");		
		System.out.println("You can attempt to 'answer' the equation on the wall");
        System.out.println("You can 'look' around");
        System.out.println();
        
        if(Room2.wordFound==true && Room3.wordFound==true && Room4.wordFound==true)
		{
			System.out.println("You may now access secret Room5");
		}
	}
    
    @Command(command="look")
    public void look()
    {
    		System.out.println("You can 'go north' to Room 3");
    		if(!wordFound) {
	    		System.out.println("You find yourself in an empty circular room.  On the wall opposite, you see '" + x + " * " + y + " = _'");
			System.out.println("You can attempt to 'answer' the equation on the wall");
    		}
    		else {
    			System.out.println("You see nothing else of interest.");
    		}
    }
    
    @Command(command="answer")
    public void answer()
    {
    		System.out.println("You can say the answer");
    		Scanner scanner = new Scanner(System.in);
    		int ans = scanner.nextInt();
        if (ans==x*y)
        {
            wordFound = true;
            
            System.out.println("A low voice reverberates the word 'Ka' and fades away");
            
            if(Room2.wordFound==true && Room3.wordFound==true && Room4.wordFound==true)
    			{
    				System.out.println("You may now access secret Room5");
    			}

        }
        else
        {
            System.out.println("The door closes behind you and you are trapped here forever to contemplate the value of '" + x + " * " + y + " = _'... The End");
            System.exit(0);
        }
    }
}
