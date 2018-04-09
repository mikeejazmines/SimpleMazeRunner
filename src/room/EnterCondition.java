package room;
import anno.Enter;

public interface EnterCondition
{
	public boolean canEnter();
		//can only enter when all 3 words are true 
	public String enterMessage();
	public String unableToEnterMessage(); 
		// print this out when you can’t enter
}
