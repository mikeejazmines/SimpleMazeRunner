package room;

import java.lang.reflect.Method;
import java.util.List;
import java.lang.reflect.*;

public class Test {

public static void main(String[] args) throws Exception
{
	
	Class<?> room2Class = Class.forName("Room2");
    Object room2 = room2Class.newInstance();
	
	Method swim = room2Class.getDeclaredMethod("swim");
    Method look = room2Class.getDeclaredMethod("look");
    
    look.invoke(room2);
    swim.invoke(room2);
    look.invoke(room2);
	
}

}