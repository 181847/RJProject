package testSpace;
import functionInterface.*;

public class Returnval extends RReference implements IReturnval{
	IExcutee excutee;
	public Returnval(String returnvalName, boolean isAtom, String referenceClass){
		super(returnvalName, isAtom, referenceClass);
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
