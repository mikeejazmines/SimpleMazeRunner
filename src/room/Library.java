package room;

import java.lang.reflect.Method;

import anno.Command;

public class Library {
	
	static boolean book;
	
	public Library()
	{
		book=false; 
	}

	public void getDescription()
	{
		System.out.println("You enter the room and find yourself in a large room filled with possibly a thousand books! "
				+ "\nYou notice one in particular.");
	}
	
	@Command(command="read book")
	public void readBook()
	{
		System.out.println("There is a great big ‘O’ on the first page. Hmm... maybe you should keep this.");
	}
	
	@Command(command="take book")
	public void takeBook()
	{
		book=true;
		System.out.println("You have taken the BOOK with you.");
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
				if(book==true&&action.equals("takeBook"))
					continue;
				System.out.println("You can " + c.command());
			}
		}
	}
	
	public static boolean hasBook()
	{
		return book;
	}
}
