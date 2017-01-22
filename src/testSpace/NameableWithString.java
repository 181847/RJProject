package testSpace;
import basicInterface.INameable;

public class NameableWithString implements INameable
{
	String name;

	@Override
	public String getName()
	{
		// TODO: Implement this method
		return name;
	}

	@Override
	public void setName(String newName)
	{
		// TODO: Implement this method
		name = newName;
	}

	
	
}
