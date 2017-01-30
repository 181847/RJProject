package function;
import functionInterface.*;

public class Returnval extends RReference implements IReturnval{
	IExcutee excutee;
	public Returnval(String referenceClass, String returnvalName){
		super(returnvalName, referenceClass);
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
