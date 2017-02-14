package function;

import basicInterface.IExceptionHolder;
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
import rClassInterface.IRReference;
import unfinishedClass.AbstractFunctionWithExceptionExcuter;
import unfinishedClass.ExceptionExcuter;

/**
 * 这个类实现了Function的invoke方法，
 * 以及有关prepareParameters()/run()方法中发生异常的处理方法。
 * @author 75309
 *
 */
public abstract class AbstractRunableFunction extends AbstractFunctionWithExceptionExcuter implements IFunction {
	
	/**
	 * 设置function的名字。
	 * @param name function的名字。
	 */
	public AbstractRunableFunction(String name){
		super(name);
	}
	
	/**
	 * 准备参数，发动run()方法。
	 * @param paragraph 用于区别这个function中不同功能代码段的数字。
	 */
	@Override
	public IExcuter invoke(int paragraph){
		IExcuter nextExcuter;
		try{
			prepareParameters();
			nextExcuter = run(paragraph);
		} catch (Exception e){
			nextExcuter = dealWithException(e);
		}
		return nextExcuter;
	}

	/**
	 * 本方法将传入的exception参数放进Function中名为“EXCEPTION”的参数当中
	 * @param e
	 */
	public ExceptionExcuter dealWithException(Exception e) {
		IExceptionHolder exceptionExcuter = (IExceptionHolder) Excuter("EXCEPTION");
		exceptionExcuter.setException(e);
		return (ExceptionExcuter) exceptionExcuter;
	}

	@Override
	public abstract boolean needParameters();

	@Override
	public abstract void prepareParameters();

	@Override
	public abstract IExcuter run(int paragraph);
	
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

	@Override
	public IRReference getNewInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFunction ConstructFunction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFunction Function(String functionName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFunction getFunction(int functionIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getFunctionIndexOf(String functionName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int fillFunctionGraphOf(String functionName, IFunctionRearSlot rearSlot, IFunctionHeadSlot headSlot) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int fillFunctionGraphOf(int functionIndex, IFunctionRearSlot rearSlot, IFunctionHeadSlot headSlot) {
		// TODO Auto-generated method stub
		return 0;
	}
}
