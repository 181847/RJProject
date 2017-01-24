package testSpace;
import functionInterface.*;

public class Parameter extends RReference implements IParameter{
	IReturnval returnvalSource;
	
	public Parameter(String parameterName, boolean isAtom, String referenceClass){
		super(parameterName, isAtom, referenceClass);
		returnvalSource = null;
	}

	@Override
	public void extractParameter()
	{
		set(returnvalSource);
	}

	@Override
	public void linkReturnval(IReturnval returnval)
	{
		returnvalSource = returnval;
	}

	@Override
	public IReturnval getReturnval()
	{
		return returnvalSource;
	}


	
	

}
