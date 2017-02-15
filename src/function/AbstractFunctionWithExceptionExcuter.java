package function;

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
import rClassInterface.IRClass;
/**
 * 本抽象类暂时废除，因为这个抽象类的唯一作用就是在构造方法中向Function调用
 * insertExceptionExcuter()，向function添加一个异常出口，
 * 然而在这个时候类中往往还没有初始化承载Excuter的列表，
 * 于是会产生空指针异常，还是改为手动增加ExceptionExcuter吧。
 * @author 75309
 *
 */
public abstract class AbstractFunctionWithExceptionExcuter extends AbstractFunctionWithRClassID implements IRClass {

	/**
	 * 强制加入一个名字为 “EXCEPTION” 的ExceptionExcuter，
	 * @param name RClass的名字。
	 */
	public AbstractFunctionWithExceptionExcuter(String name) {
		super(name);
	}
	
	/**
	 * @param catchExceptionFunction 要求这个传入的function实例对象必须是AbstractFunctionCatchException的子类。
	 * @return 如果传入的参数不是指定的类型就返回-1；
	 * 如果为null，返回0；
	 * 如果成功，放回。
	 */
	@Override
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
	public abstract IExcuter invoke(int paragraph);

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
