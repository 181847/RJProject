package function.tool;
import functionInterface.*;

/**
 * 存储 执行出口列表 和 返回值插口列表，用于Function中，
 * 对 执行出口和 返回值插口 统一管理。
 */
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
	
	/**
	 * 返回excuter和returnval的总数
	 * @return excuter和returnval的总数
	 */
	@Override
	public int getNum() {
		return excuterList.getNum() + returnvalList.getNum();
	}

	@Override
	public int insertExcuter(IExcuter excuter)
	{
		return excuterList.insertExcuter(excuter);
	}

	@Override
	public IExcuter Excuter(String excuterName)
	{
		return excuterList.Excuter(excuterName);
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
	public int getExcuterIndexOf(String excuterName) {
		return excuterList.getExcuterIndexOf(excuterName);
	}

	@Override
	public int insertReturnval(IReturnval returnval)
	{
		return returnvalList.insertReturnval(returnval);
	}

	@Override
	public IReturnval Returnval(String returnvalName)
	{
		return returnvalList.Returnval(returnvalName);
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

	@Override
	public IExcuterList getExcuterList()
	{
		return excuterList;
	}

	@Override
	public IReturnvalList getReturnvalList()
	{
		return returnvalList;
	}

	@Override
	public int getReturnvalIndexOf(String returnvalName) {
		return returnvalList.getReturnvalIndexOf(returnvalName);
	}
}
