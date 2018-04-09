
import java.util.List;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

import anno.Command;
import anno.Direction;
import anno.Enter;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.DynamicType.Loaded;
import net.bytebuddy.dynamic.DynamicType.Unloaded;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import room.EnterCondition;

import java.util.HashMap;


public class CastleGame
{
	private HashMap<Class, Object> roomMap = new HashMap<Class, Object>();
	private Object currentRoom;

	public CastleGame() throws Exception
	{

	}
	
	public void proxy(Class field) throws Exception
	{
		ByteBuddy byteBuddy = new ByteBuddy();
		DynamicType.Builder<Object> builder = byteBuddy.subclass(field)
				  							   		   .implement(EnterCondition.class);
		
		builder = builder.method(ElementMatchers.isAnnotatedWith(Enter.class)).intercept(MethodDelegation.to(SimpleInterceptor.class));
		Unloaded<Object> unloadedClass = builder.make();
		Loaded<?> loaded = unloadedClass.load(getClass().getClassLoader());
		Class<?> dynamicType = loaded.getLoaded();
		
		Object instance = dynamicType.newInstance();
		roomMap.put(field, instance);
		
		
	}
	
	public void load() throws Exception
	{
		FastClasspathScanner scanner = new FastClasspathScanner("room");
		ScanResult result = scanner.scan();
		
		List<String> allClasses = result.getNamesOfAllClasses();		
		System.out.println(allClasses);
		
		// instantiate
		for (String className : allClasses)
		{
			Class clazz = Class.forName(className);
			if(!(clazz.isInterface()))
			{
				Object instance = clazz.newInstance();
				if(clazz.isAnnotationPresent(Enter.class))
				{
					//create proxy
					proxy(clazz);
				} 
				else
				{
					roomMap.put(clazz, instance);
				}
				
				// associate the clazz to the instance

			}
		}
		
		// associate fields

		for (Class roomClazz : roomMap.keySet())
		{
			Object currentRoom = roomMap.get(roomClazz);
			
			for (Field f : roomClazz.getDeclaredFields())
			{
				
				if (f.isAnnotationPresent(Direction.class))
				{
					Class fieldClazz = f.getType();
					Object roomInstance = roomMap.get(fieldClazz);
					f.setAccessible(true);
					f.set(currentRoom, roomInstance);
				}
			}
		}
		
		// set the first room
		currentRoom = roomMap.get(room.Room1.class);
		printDescription();
		
	}
	
	public void printDescription() throws Exception
	{	
		Class[] in = currentRoom.getClass().getInterfaces();
		if( in.length == 0 )
		{
			Method m = currentRoom.getClass().getDeclaredMethod("getDescription");
			m.invoke(currentRoom);
		} 
		else
		{
			for( Class i : in )
			{		
				if( i.equals(EnterCondition.class ))
				{
					Method m = currentRoom.getClass().getSuperclass().getDeclaredMethod("getDescription");
					m.invoke(currentRoom);	
				}
			}
		}
		
		
			
	}
	
	public void action(String command)
	{
		Boolean proxy = false; 
		Boolean moveCheck = false;
		Class<?> clazz = currentRoom.getClass();
		Class[] in = clazz.getInterfaces();
		if( in.length > 0 )
		{
			clazz = clazz.getSuperclass();
			proxy = true;
		} 
		//if it's a proxy, get superclass 

		
		try
		{
			Method[] ms = clazz.getDeclaredMethods();
			Field[] fields = clazz.getDeclaredFields();
			
			for( Method m : ms )
			{
				if(m.isAnnotationPresent(Command.class))
				{
					Command c = m.getAnnotation(Command.class);
					if(c.command().equals(command))
					{
						moveCheck = true;
						m.setAccessible(true);
						m.invoke(currentRoom);		
					}
				}
			}
			
			for (Field f : fields)
			{
				if (f.isAnnotationPresent(Direction.class))
				{
					Direction d = f.getAnnotation(Direction.class);
					
					if (d.command().equals(command))
					{
						Class fieldClass = f.getType();
						Object o = roomMap.get(fieldClass);
						
						Class[] in2 = fieldClass.getInterfaces();
						if( in2.length > 0 )
						{
							proxy = true;
						}
						
						if( proxy )
						{
							if(((EnterCondition) o).canEnter() == true)
							{
								System.out.println(((EnterCondition) o).enterMessage());
								currentRoom = o; 
								printDescription();
								moveCheck = true;
							}
							else
							{
								System.out.println(((EnterCondition) o).unableToEnterMessage());
							}
						}
						else
						{
							currentRoom = o; 
							printDescription();
							moveCheck = true;
						}
						
						break;
					}
				}
			}
			if(!moveCheck) {
				System.out.println("You can't do that.");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		DragonGame game = new DragonGame();
		game.load(); 
		
		Scanner scanner = new Scanner(System.in);
		
		while (true)
		{
			System.out.println();
			System.out.println("Action: ");
			String text = scanner.nextLine();
			if (text.equals("exit"))
			{
				break;
			} 
			else
			{
				game.action(text);
			}
		}
		scanner.close();
		
	}
}