package function;
import functionInterface.*;
import rClass.RReference;

public class Returnval extends RReference implements IReturnval{
	IExcutee excutee;
	public Returnval(String referenceClass, String returnvalName){
		super(referenceClass, returnvalName);
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
