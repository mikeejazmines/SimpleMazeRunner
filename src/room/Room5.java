package room;


import anno.*;
import java.util.Scanner;

@Enter
public class Room5 implements EnterCondition {

	@Direction(command="go south")
	private Room3 south;
	
	@Command(command="getDescription")
	public void getDescription()
    {
		System.out.println("You enter a long tunnel which opens into a large chamber.");  
        System.out.println("You can see an opening to the outside on the other side.");
        System.out.println("As you walk towards it, a large dragon head peers from the opening.");
        System.out.println("'What is the passphrase?' it asks.");
        System.out.println("You can say the 'passphrase'");
        System.out.println("You can 'attack' the dragon.");    
        System.out.println("You can 'look' around");
        System.out.println();
    }
    
	@Command(command="attack")
    public void attack()
    {
    	
        if (Room2.tookSword)
        {
            System.out.println("You charge to attack the dragon brandishing your sword.  The dragon breathes fire into the chamber turning you to ash... The End.");
            System.exit(0);
        }
        else
        {
            System.out.println("In a flash of wisdom, you resist.  Only a fool would attack such a creature with his bare hands.");
        }
    }
    
	@Command(command="passphrase")
    public void passphrase()
    {
		Scanner scanner = new Scanner(System.in);
		String word = scanner.nextLine();
		
        if ((word.equalsIgnoreCase("AlaKaZam")))
        {
            if (Room3.babyDead)
            {
                System.out.println("That is correct.  The dragon breathes fire into the chamber turning you to ash for killing her baby... The End.");
                System.exit(0);
            }
            else
            {
                System.out.println("That is correct.  The dragon allows you to pass and you escape... Congratulations on your 10pts.");
                System.exit(0);
            }
        }
        else
        {
            System.out.println("That is incorrect.  The dragon breathes fire into the chamber turning you to ash... The End.");
            System.exit(0);
        }
        
        scanner.close();
    }
    
	@Command(command="look")
    public void look()
    {
		System.out.println("There is no way around the dragon.");
		System.out.println("You can say the 'passphrase' followed by the passphrase on the next line");
        System.out.println("You can 'attack' the dragon.");
    }

	@Override
	public boolean canEnter() {
		if(Room2.wordFound==true && Room3.wordFound==true && Room4.wordFound==true)
			return true;
		return false; 
	}

	@Override
	public String enterMessage() {
		return "You can now enter the final room, Room 5";
	}

	@Override
	public String unableToEnterMessage() {
		return "Cannot enter Room 5";
	}
}
