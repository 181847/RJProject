package testSpace;
import functionInterface.*;

public class FunctionRearSlot implements IFunctionRearSlot{
	public IExcuterList excuterList;
	public IReturnvalList returnvalList;

	public FunctionRearSlot(){
		excuterList = new ExcuterList();
		returnvalList = new ReturnvalList();
	}

	public FunctionRearSlot(int excuterListSpace, int returnvalListSpace){
		excuterList = new ExcuterList(excuterListSpace);
		returnvalList = new ReturnvalList(returnvalListSpace);
	}

	@Override
	public int insertExcuter(IExcuter excuter)
	{
		return excuterList.insertExcuter(excuter);
	}

	@Override
	public IExcuter getExcuter(String excuterName)
	{
		return excuterList.getExcuter(excuterName);
	}

	@Override
	public IExcuter getExcuter(int excuterNumber)
	{
		return excuterList.getExcuter(excuterNumber);
	}

	@Override
	public int deleteExcuter(String excuterName)
	{
		return excuterList.deleteExcuter(excuterName);
	}

	@Override
	public int insertReturnval(IReturnval returnval)
	{
		return returnvalList.insertReturnval(returnval);
	}

	@Override
	public IReturnval getReturnval(String returnvalName)
	{
		return returnvalList.getReturnval(returnvalName);
	}

	@Override
	public IReturnval getReturnval(int returnvalNumber)
	{
		return returnvalList.getReturnval(returnvalNumber);
	}

	@Override
	public int deleteReturnval(String returnvalName)
	{
		return returnvalList.deleteReturnval(returnvalName);
	}
}
