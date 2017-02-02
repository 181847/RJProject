package basicTool;
import basicInterface.INameable;

public class NameableWithString implements INameable
{
	String name;
	
	public NameableWithString(){
		name = "Unknown";
	}
	
	public NameableWithString(String name){
		this.name = name;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public void setName(String newName)
	{
		name = newName;
	}

	
	public String toString(){
		return name;
	}
}
