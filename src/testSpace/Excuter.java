package testSpace;
import functionInterface.*;

public class Excuter extends NameableWithString implements IExcuter{
	IExcutee excutee;
	
	public Excuter(String excuterName){
		setName(excuterName);
		excutee = null;
	}

	@Override
	public IExcutee getExcutee()
	{
		return excutee;
	}

	@Override
	public void linkExcutee(IExcutee excutee)
	{
		this.excutee = excutee;
	}


	
}
