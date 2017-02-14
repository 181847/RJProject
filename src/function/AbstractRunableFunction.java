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
import unfinishedClass.AbstractFunctionCatchException;
import unfinishedClass.ExceptionExcuter;

/**
 * 这个类实现了Function的invoke方法，
 * 有关prepareParameters()/run()方法中发生异常的处理方法，
 * 设置由谁处理本function异常的方法assignExceptionHandler()。
 * @author 75309
 *
 */
public abstract class AbstractRunableFunction extends AbstractFunctionWithRClassID implements IFunction {
	
	/**
	 * 设置function的名字。
	 * @param name function的名字。
	 */
	public AbstractRunableFunction(String name){
		super(name);
	}
	
	/**
	 * 准备参数，发动run()方法，
	 * 当prepareParameters()/run()方法中发生任何异常，
	 * 都将会捕获异常，并且调用dealWithException(Exception)，
	 * 来对异常进行单独的处理，将异常放进名为“EXCEPTION”的Excuter中，
	 * 将这个特殊的Excuter返回，使得Runner进入到异常路线。
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
	
	/**
	 * 为当前这个Function指定，当它发生异常时，
	 * 哪个function来接受异常，注意接受异常的Function一定是一个带有ExceptionExcutee的function，
	 * @param catchExceptionFunction
	 * @return 成功指定异常的接受者就返回1，失败返回0。
	 */
	public int assignExceptionHandler(IFunction catchExceptionFunction){
		if (catchExceptionFunction == null){
			return 0;
		}
		if (catchExceptionFunction instanceof AbstractFunctionCatchException){
			Excuter("EXCEPTION").linkExcutee(catchExceptionFunction.Excutee("CATCH"));
			return 1;
		}
		return -1;
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
}
