package function;
import functionInterface.*;
import rClass.RReference;

public class Parameter extends RReference implements IParameter{
	IReturnval returnvalSource;
	
	public Parameter(String referenceClass, String parameterName){
		super(referenceClass, parameterName);
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
