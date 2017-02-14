package unfinishedClass;

import function.AbstractFunctionWithRClassID;
import functionInterface.IExcutee;
import functionInterface.IExcuteeList;
import functionInterface.IExcuter;
import functionInterface.IExcuterList;
import functionInterface.IFunctionHeadSlot;
import functionInterface.IFunctionRearSlot;
import functionInterface.IParameter;
import functionInterface.IParameterList;
import functionInterface.IReturnval;
import functionInterface.IReturnvalList;
import rClassInterface.IRClass;

public abstract class AbstractFunctionWithExceptionExcuter extends AbstractFunctionWithRClassID implements IRClass {

	/**
	 * 强制加入一个名字为 “EXCEPTION” 的ExceptionExcuter，
	 * @param name RClass的名字。
	 */
	public AbstractFunctionWithExceptionExcuter(String name) {
		super(name);
		insertExceptionExcuter();
	}

	/**
	 * 从这个抽象类开始添加的一个方法，
	 * 用来专门插入一个名字为 “EXCEPTION” 的ExceptionExcuter，
	 * 这个方法的实现交由子类，调用则是在本抽象类的构造方法中调用。
	 * @return
	 */
	public abstract int insertExceptionExcuter();
	
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
