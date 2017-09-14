package unfinishedClass.customFunction;

import function.AbstractFunctionWithRClassID;
import functionInterface.IExcutee;
import functionInterface.IExcuteeList;
import functionInterface.IExcuter;
import functionInterface.IExcuterList;
import functionInterface.IFunction;
import functionInterface.IFunctionHeadSlot;
import functionInterface.IFunctionRearSlot;
import functionInterface.IParameter;
import functionInterface.IParameterList;
import functionInterface.IReturnval;
import functionInterface.IReturnvalList;

/**
 * 这个方法将有关独立执行Function运算功能的方法实现为空方法。
 */
public abstract class AbstractDisrunableFunction extends AbstractFunctionWithRClassID implements IFunction {
	/**
	 * @param name function的名字。
	 */
	public AbstractDisrunableFunction(String name) {
		super(name);
	}

	

	/**
	 * AbstractDisrunableFunction本身是为自定义Function服务的，
	 * 只是起到一个承载其他Function的作用，
	 * 它不能单独执行功能，这个方法实现为空方法，
	 * 什么也不做。
	 * @return 返回值总为null。
	 */
	@Override
	public IExcuter invoke(int paragraph){
		//Empty Body
		return null;
	}

	/**
	 * AbstractDisrunableFunction本身是为自定义Function服务的，
	 * 只是起到一个承载其他Function的作用，
	 * 它不能单独执行功能，这个方法实现为空方法，
	 * 什么也不做。
	 * @return 返回值总为false。
	 */
	@Override
	public boolean needParameters(){
		//Empty Body
		return false;
	}

	/**
	 * AbstractDisrunableFunction本身是为自定义Function服务的，
	 * 只是起到一个承载其他Function的作用，
	 * 它不能单独执行功能，这个方法实现为空方法，
	 * 什么也不做。
	 */
	@Override
	public void prepareParameters(){
		//Empty Body
	}
	
	/**
	 * AbstractDisrunableFunction本身是为自定义Function服务的，
	 * 只是起到一个承载其他Function的作用，
	 * 它不能单独执行功能，这个方法实现为空方法，
	 * 什么也不做。
	 * @return 返回值总为null。
	 */
	@Override
	public IExcuter run(int paragraph){
		//Empty Body
		return null;
	}
	
	@Override
	public abstract int assignExceptionHandler(IFunction catchExceptionFunction);
	
	@Override
	public abstract void fillContentGraph();

	@Override
	public abstract void clearGraph();

	@Override
	public abstract int getNum();
	
	//******************************************About Slot**************************************************
	@Override
	public abstract IFunctionHeadSlot getHeadSlot();

	@Override
	public abstract IFunctionRearSlot getRearSlot();
	
	//******************************************About ComponentList*********************************************
	@Override
	public abstract IExcuteeList getExcuteeList() ;
	
	@Override
	public abstract IParameterList getParameterList();

	@Override
	public abstract IExcuterList getExcuterList();

	@Override
	public abstract IReturnvalList getReturnvalList();

	//******************************************About Excutee**************************************************
	@Override
	public abstract int insertExcutee(IExcutee excutee);

	@Override
	public abstract IExcutee Excutee(String excuteeName);

	@Override
	public abstract IExcutee getExcutee(int excuteeIndex);

	@Override
	public abstract int deleteExcutee(String excuteeName);

	@Override
	public abstract int getExcuteeIndexOf(String excuteeName);
	
	//******************************************About Parameter**************************************************
	@Override
	public abstract int insertParameter(IParameter parameter);

	@Override
	public abstract IParameter Parameter(String parameterName);

	@Override
	public abstract IParameter getParameter(int parameterIndex);

	@Override
	public abstract int deleteParameter(String parameterName);

	@Override
	public abstract int getParameterIndexOf(String parameterName);
	
	//******************************************About Excuter**************************************************
	@Override
	public abstract int insertExcuter(IExcuter excuter);

	@Override
	public abstract IExcuter Excuter(String excuterName);

	@Override
	public abstract IExcuter getExcuter(int excuterIndex);

	@Override
	public abstract int deleteExcuter(String excuterName);

	@Override
	public abstract int getExcuterIndexOf(String excuterName);
	
	//******************************************About Returnval**************************************************
	@Override
	public abstract int insertReturnval(IReturnval returnval);

	@Override
	public abstract IReturnval Returnval(String returnvalName);

	@Override
	public abstract IReturnval getReturnval(int returnvalIndex);

	@Override
	public abstract int deleteReturnval(String returnvalName);

	@Override
	public abstract int getReturnvalIndexOf(String returnvalName);
}
