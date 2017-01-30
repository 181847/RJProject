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
	public void insertExcutee(IExcutee excutee)
	{
		this.excutee = excutee;
	}

	@Override
	public boolean isBlongToBasicCalculatorFunction()
	{
		// TODO: Implement this method
		return excutee == null;
	}



	
	
}
