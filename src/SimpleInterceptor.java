import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import anno.Enter;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import room.Room2;
import room.Room3;
import room.Room4;

public class SimpleInterceptor {

	
	// NOTE: INTERCEPTION METHODS SHOULD BE STATIC
	
	// interception is based on Java's method selection rules used by the compiler
	// to provide "BEST MATCH" even down to the method name
	
	// find correct name, return type, parameters
	// find correct parameters, return type
	
	
	// while simple this requires you to know before hand the proper parameter types to use
	
	
	public static boolean canEnter() throws ClassNotFoundException
	{
		if(Room2.wordFound==true && Room3.wordFound==true && Room4.wordFound==true)
			return true;
		return false; 
	}
	//can only enter when all 3 words are true 
	
	public static String enterMessage()
	{
		
		return "You may now enter Room 5";
		
	}
	
	public static String unableToEnterMessage()
	{
		return "Cannot enter Room 5";
	}
}
