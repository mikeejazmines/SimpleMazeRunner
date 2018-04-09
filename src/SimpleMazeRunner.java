import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

import room.Room1;

public class SimpleMazeRunner 
{
	private Object currentRoom;
	
	public SimpleMazeRunner() throws Exception
	{
		currentRoom = new Room1();
		printDescription();
	}
	
	
	public void printDescription() throws Exception
	{
		Method m = currentRoom.getClass().getDeclaredMethod("getDescription");
		System.out.println(m.invoke(currentRoom));		
	}
	
	public void move(String direction)
	{
		// just assume "north", "south", "east", "west" for now
		
		Class clazz = currentRoom.getClass();
		try
		{
			Field f = clazz.getDeclaredField(direction);
			Class fieldClass = f.getType();
			currentRoom = fieldClass.newInstance();
			printDescription();
		}
		catch(NoSuchFieldException e)
		{
			System.out.println("Can't go that way");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) throws Exception
	{
		SimpleMazeRunner maze = new SimpleMazeRunner();
		
		Scanner scanner = new Scanner(System.in);
		
		while (true)
		{
			System.out.println();
			System.out.println("Where do you want to go?: ");
			String text = scanner.nextLine();
			if (text.equals("exit"))
			{
				break;
			}
			else
			{
				maze.move(text);
			}
		}
	}
	
	
	// ASSESSMENT 1 -- Fields
	// very fragile -- if you type in field name that exists but is not a room, i.e. no getDescription() DEAD
	// what if you wanted to allow "go north" "n" "N" "go NORTH" -- using field names is very limiting
	
	// ASSESSMENT 2 -- room state
	// what if rooms are meant to store state and remember stuff inside?  like dropping items, starting items, etc
}
