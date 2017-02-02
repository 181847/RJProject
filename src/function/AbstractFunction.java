package function;

import basicTool.NameableWithString;
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

public abstract class AbstractFunction extends NameableWithString implements IFunction {
	/**
	 * Function的前方插槽
	 * 存储Excutee和参数
	 */
	public IFunctionHeadSlot functionHeadSlot;
	/**
	 * Function的后方插插槽
	 * 存储Excuter和返回值
	 */
	public IFunctionRearSlot functionRearSlot;
	
	public AbstractFunction(){
		functionHeadSlot = new FunctionHeadSlot();
		functionRearSlot = new FunctionRearSlot();
	}
	
	public AbstractFunction(int excuteeList, int parameterList, int excuterList, int returnvalList)
	{
		functionHeadSlot = new FunctionHeadSlot(excuteeList, parameterList);
		functionRearSlot = new FunctionRearSlot(excuterList, returnvalList);
	}
	
	//请重载下面这三个方法
	//********************************************** ABOUT FUNCTION *************************************************************
	public abstract IExcuter run(int paragraph);
	public abstract void fillContentGraph();
	public abstract void clearGraph();
	/**
	 * 是否需要参数由程序员手动重载这个方法
	 * 表明这个Function是否需要参数
	 * 例如你将要创建的Function需要参数
	 * 只需要重载这个方法为
	 * public boolean needParameters(){
	 *     return true;
	 * }
	 */
	public abstract boolean needParameters();
	
	
	public IExcuter invoke(int paragraph){
		prepareParameters();
		return run(paragraph);
	}
	
	public void prepareParameters(){
		functionHeadSlot.prepareParameters();
	}

	
	//********************************************** ABOUT EXCUTEE *************************************************************
	@Override
	public int insertExcutee(IExcutee excutee) {
		if (excutee == null)
			return 0;
		excutee.setHostFunction(this);
		
		return functionHeadSlot.insertExcutee(excutee);
	}

	@Override
	public IExcutee Excutee(String excuteeName) {
		return functionHeadSlot.Excutee(excuteeName);
	}

	@Override
	public IExcutee getExcutee(int excuteeNumber) {
		return functionHeadSlot.getExcutee(excuteeNumber);
	}

	@Override
	public int deleteExcutee(String excuteeName) {
		return functionHeadSlot.deleteExcutee(excuteeName);
	}

	
	//********************************************** ABOUT PARAMETER *************************************************************
	@Override
	public int insertParameter(IParameter parameter) {
		return functionHeadSlot.insertParameter(parameter);
	}

	@Override
	public IParameter Parameter(String parameterName) {
		return functionHeadSlot.Parameter(parameterName);
	}

	@Override
	public IParameter getParameter(int parameterNumber) {
		return functionHeadSlot.getParameter(parameterNumber);
	}

	@Override
	public int deleteParameter(String parameterName) {
		return functionHeadSlot.deleteParameter(parameterName);
	}

	
	
	
	//********************************************** ABOUT EXCUTER *************************************************************
	@Override
	public int insertExcuter(IExcuter excuter) {
		return functionRearSlot.insertExcuter(excuter);
	}

	@Override
	public IExcuter Excuter(String excuterName) {
		return functionRearSlot.Excuter(excuterName);
	}

	@Override
	public IExcuter getExcuter(int excuterNumber) {
		return functionRearSlot.getExcuter(excuterNumber);
	}

	@Override
	public int deleteExcuter(String excuterName) {
		return functionRearSlot.deleteExcuter(excuterName);
	}

	
	
	
	//********************************************** ABOUT RETURNVAL *************************************************************
	@Override
	public int insertReturnval(IReturnval returnval) {
		return functionRearSlot.insertReturnval(returnval);
	}

	@Override
	public IReturnval Returnval(String returnvalName) {
		return functionRearSlot.Returnval(returnvalName);
	}

	@Override
	public IReturnval getReturnval(int returnvalNumber) {
		return functionRearSlot.getReturnval(returnvalNumber);
	}

	@Override
	public int deleteReturnval(String returnvalName) {
		return functionRearSlot.deleteReturnval(returnvalName);
	}
	
	
	
	
	//********************************************** ABOUT GET LIST *************************************************************
	@Override
	public IExcuteeList getExcuteeList() {
		return functionHeadSlot.getExcuteeList();
	}

	@Override
	public IParameterList getParameterList() {
		return functionHeadSlot.getParameterList();
	}
	
	@Override
	public IExcuterList getExcuterList() {
		return functionRearSlot.getExcuterList();
	}

	@Override
	public IReturnvalList getReturnvalList() {
		return functionRearSlot.getReturnvalList();
	}
	
	
	
	
	//********************************************** ABOUT GET SLOT *************************************************************
	@Override
	public IFunctionHeadSlot getHeadSlot() {
		return functionHeadSlot;
	}

	@Override
	public IFunctionRearSlot getRearSlot() {
		return functionRearSlot;
	}
	

	
	
	

	//********************************************** ABOUT GET NUM *************************************************************
	//得到这个function节点所有Excutee/Parameter/Excuter/Returnval的总数
	@Override
	public int getNum(){
		return functionHeadSlot.getNum() + functionRearSlot.getNum();
	}


}
