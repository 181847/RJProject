package testSpace;

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

public class OriginalFunction extends NameableWithString implements IFunction {
	
	public IFunctionHeadSlot functionHeadSlot;
	public IFunctionRearSlot functionRearSlot;
	
	public OriginalFunction(){
		functionHeadSlot = new FunctionHeadSlot(1, 0);
		functionRearSlot = new FunctionRearSlot();
		
		insertExcutee(new NormalExcutee("fire", 1));
		setName("Hello World Function");
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
	public IExcutee getExcutee(String excuteeName) {
		return functionHeadSlot.getExcutee(excuteeName);
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
	public IParameter getParameter(String parameterName) {
		return functionHeadSlot.getParameter(parameterName);
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
	public IExcuter getExcuter(String excuterName) {
		return functionRearSlot.getExcuter(excuterName);
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
	public IReturnval getReturnval(String returnvalName) {
		return functionRearSlot.getReturnval(returnvalName);
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
	
	
	
	
	//********************************************** ABOUT GET LIST *************************************************************
	@Override
	public IFunctionHeadSlot getHeadSlot() {
		return functionHeadSlot;
	}

	@Override
	public IFunctionRearSlot getRearSlot() {
		return functionRearSlot;
	}
	

	
	
	//********************************************** ABOUT FUNCTION *************************************************************
	@Override
	public IExcuter run(int paragraph) {
		
		switch(paragraph){
		case 1:
			System.out.println("Hello World");
			return null;
		default:
			return null;
		}
	}

	@Override
	public void fillContentGraph() {
		
	}

	@Override
	public void clearGraph() {
		
	}

	@Override
	public boolean needParameter() {
		return (functionHeadSlot.getParameterList().getNum()) == 0;
	}

	
	//得到这个function节点所有Excutee/Parameter/Excuter/Returnval的总数
	@Override
	public int getNum(){
		return functionHeadSlot.getNum() + functionRearSlot.getNum();
	}

}
