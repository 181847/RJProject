package unfinishedClass.customFunction;

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
import rClass.RClassLoaderManager;

/**
 * 这个抽象类加入了两个位于Function前后的两个SentryFunction，
 * 这两个SentryFunction的功能是代替FunctionSlot的功能 。
 */
public abstract class AbstractFunctionWithSentryFunction extends AbstractDisrunableFunction implements IFunction {
	/**
	 * 前方用于传递参数和执行入口的Function。
	 */
	IFunction headSentryFunction;
	
	/**
	 * 后方用于传递返回值，执行出口以及抛出异常的Function。
	 */
	IFunction rearSentryFunction;
	
	/**
	 * @param name function的名字。
	 */
	public AbstractFunctionWithSentryFunction(String name) {
		super(name);
		
		
		headSentryFunction.assignExceptionHandler(rearSentryFunction);
	}
	
	@Override
	public abstract void clearGraph();

	/**
	 * 在AbstractFunctionWiithSentryFunction当中，
	 * 设置异常处理的时候是为rearSentryFunction设置异常捕获。
	 * @param catchExceptionFunction 一个可以获取异常的Function
	 * @return 如果成功设置就返回1，参数为null返回0，
	 * 参数Function不能获取异常就返回-1。
	 */
	@Override
	public int assignExceptionHandler(IFunction catchExceptionFunction) {
		rearSentryFunction.assignExceptionHandler(catchExceptionFunction);
		return 0;
	}
	
	/**
	 * 填充内部的子Function
	 */
	@Override
	public void fillContentGraph(){
		RClassLoaderManager.
			getRClassLoader().
			getRClass(getRClassID()).
			fillFunctionGraphOf(getName(),
							headSentryFunction, 
							rearSentryFunction);
	}

	/**
	 * 这个方法会获取这个function向外部开放的所有插口的数量，
	 * 实际的获取方法是，
	 * 先通过这个类的get Head/Rear Slot()获取前后的插槽，
	 * 然后返回这两个插槽的getNum()的总和。
	 * @return 能够被外部链接的插口数量，
	 * 包括Excutee/Parameter/Excuter/Returnvl。
	 */
	@Override
	public int getNum(){
		return getHeadSlot().getNum() + getRearSlot().getNum();
	}
	
	//******************************************About Slot**************************************************
	/**
	 * 返回这个Function的前方插槽，
	 * 实际过程是返回子成员headSentryFunction调用getHeadSlot()的结果。
	 * @return 这个Function的前方插槽，
	 * 这个插槽里包括这个Function的Excutee，参数。
	 */
	@Override
	public IFunctionHeadSlot getHeadSlot(){
		return headSentryFunction.getHeadSlot();
	}

	/**
	 * 返回这个Function的前方插槽，
	 * 实际过程是返回子成员rearSentryFunction调用getRearSlot()的结果。
	 * @return 这个Function的前方插槽，
	 * 这个插槽里包括这个Function的Excuter，返回值。
	 */
	@Override
	public IFunctionRearSlot getRearSlot(){
		return rearSentryFunction.getRearSlot();
	}
	
	//******************************************About ComponentList*********************************************
	/**
	 * 返回这个Function的Excutee列表，
	 * 实际过程是返回headSentry的Excutee列表。
	 * @return Excutee列表
	 */
	@Override
	public IExcuteeList getExcuteeList(){
		return headSentryFunction.getExcuteeList();
	}
	
	/**
	 * 返回这个Function的Parameter列表，
	 * 实际过程是返回headSentry的Parameter列表。
	 * @return Parameter列表
	 */
	@Override
	public IParameterList getParameterList(){
		return headSentryFunction.getParameterList();
	}

	/**
	 * 返回这个Function的Excuter列表，
	 * 实际过程是返回rearSentry的Excuter列表。
	 * @return Excuter列表
	 */
	@Override
	public IExcuterList getExcuterList(){
		return rearSentryFunction.getExcuterList();
	}

	/**
	 * 返回这个Function的Returnval列表，
	 * 实际过程是返回rearSentry的Returnval列表。
	 * @return Returnval列表
	 */
	@Override
	public IReturnvalList getReturnvalList(){
		return rearSentryFunction.getReturnvalList();
	}

	//******************************************About Excutee**************************************************
	/**
	 * 自定义Function的这个方法无用，
	 * 实现为空方法。
	 * @return 返回值总是0。
	 */
	@Override
	public int insertExcutee(IExcutee excutee){
		//Empty Body
		return 0;
	}

	/**
	 * 通过名字得到一个的Excutee，
	 * 实际过程是返回调用headSentryFunction的同名方法的结果。
	 * @param excuteeName 想要获取的Excutee的名字。
	 * @return 指定Excutee的元素，
	 * 如果没有找到就返回null。
	 */
	@Override
	public IExcutee Excutee(String excuteeName){
		return headSentryFunction.Excutee(excuteeName);
	}

	/**
	 * 通过序号得到一个的Excutee，
	 * 实际过程是返回调用headSentryFunction的同名方法的结果。
	 * @param excuteeIndex 想要获取的Excutee的名字。
	 * @return 指定Excutee的元素，
	 * 如果没有找到就返回null。
	 */
	@Override
	public IExcutee getExcutee(int excuteeIndex){
		return headSentryFunction.getExcutee(excuteeIndex);
	}

	/**
	 * 自定义Function的这个方法无用，
	 * 实现为空方法。
	 * @return 返回值总是0。
	 */
	@Override
	public int deleteExcutee(String excuteeName){
		//Empty Body
		return 0;
	}

	/**
	 * 获取指定名字的Excutee的序号
	 * 实际过程是返回调用headSentryFunction的同名方法的结果。
	 * @param excuteeName 目标Excutee。
	 * @return Excutee的序号，如果不存在就返回-1。
	 */
	@Override
	public int getExcuteeIndexOf(String excuteeName){
		return headSentryFunction.getExcuteeIndexOf(excuteeName);
	}
	
	//******************************************About Parameter**************************************************
	/**
	 * 自定义Function的这个方法无用，
	 * 实现为空方法。
	 * @return 返回值总是0。
	 */
	@Override
	public int insertParameter(IParameter parameter){
		//Empty Body
		return 0;
	}

	/**
	 * 通过名字得到一个的Parameter，
	 * 实际过程是返回调用headSentryFunction的同名方法的结果。
	 * @param parameterName 想要获取的Parameter的名字。
	 * @return 指定Parameter的元素，
	 * 如果没有找到就返回null。
	 */
	@Override
	public IParameter Parameter(String parameterName){
		return headSentryFunction.Parameter(parameterName);
	}

	/**
	 * 通过序号得到一个的Parameter，
	 * 实际过程是返回调用headSentryFunction的同名方法的结果。
	 * @param parameterIndex 想要获取的Parameter的序号。
	 * @return 指定Parameter的元素，
	 * 如果没有找到就返回null。
	 */
	@Override
	public IParameter getParameter(int parameterIndex){
		return headSentryFunction.getParameter(parameterIndex);
	}

	/**
	 * 自定义Function的这个方法无用，
	 * 实现为空方法。
	 * @return 返回值总是0。
	 */
	@Override
	public int deleteParameter(String parameterName){
		//Empty Body
		return 0;
	}

	/**
	 * 获取指定名字的Parameter的序号。
	 * 实际过程是返回调用headSentryFunction的同名方法的结果。
	 * @param parameterName 目标Parameter。
	 * @return Parameter的序号，如果不存在就返回-1。
	 */
	@Override
	public int getParameterIndexOf(String parameterName){
		return headSentryFunction.getParameterIndexOf(parameterName);
	}
	
	//******************************************About Excuter**************************************************
	/**
	 * 自定义Function的这个方法无用，
	 * 实现为空方法。
	 * @return 返回值总是0。
	 */
	@Override
	public int insertExcuter(IExcuter excuter){
		//Empty Body
		return 0;
	}

	/**
	 * 通过名字得到一个的Excuter，
	 * 实际过程是返回调用rearSentryFunction的同名方法的结果。
	 * @param excuterName 想要获取的Excuter的名字。
	 * @return 指定Excuter的元素，
	 * 如果没有找到就返回null。
	 */
	@Override
	public IExcuter Excuter(String excuterName){
		return rearSentryFunction.Excuter(excuterName);
	}

	/**
	 * 通过序号得到一个的Excuter，
	 * 实际过程是返回调用rearSentryFunction的同名方法的结果。
	 * @param excuterIndex 想要获取的Excuter的名字。
	 * @return 指定Excuter的元素，
	 * 如果没有找到就返回null。
	 */
	@Override
	public IExcuter getExcuter(int excuterIndex){
		return rearSentryFunction.getExcuter(excuterIndex);
	}

	/**
	 * 自定义Function的这个方法无用，
	 * 实现为空方法。
	 * @return 返回值总是0。
	 */
	@Override
	public int deleteExcuter(String excuterName){
		//Empty Body
		return 0;
	}

	/**
	 * 获取指定名字的Excuter的序号，
	 * 实际过程是返回调用rearSentryFunction的同名方法的结果。
	 * @param excuterName 目标Excuter。
	 * @return Excuter的序号，如果不存在就返回-1。
	 */
	@Override
	public int getExcuterIndexOf(String excuterName){
		return rearSentryFunction.getExcuterIndexOf(excuterName);
	}
	
	//******************************************About Returnval**************************************************
	/**
	 * 自定义Function的这个方法无用，
	 * 实现为空方法。
	 * @return 返回值总是0。
	 */
	@Override
	public int insertReturnval(IReturnval returnval){
		//Empty Body
		return 0;
	}

	/**
	 * 通过名字得到一个的Returnval，
	 * 实际过程是返回调用rearSentryFunction的同名方法的结果。
	 * @param returnvalName 想要获取的Returnval的名字。
	 * @return 指定Returnval的元素，
	 * 如果没有找到就返回null。
	 */
	@Override
	public IReturnval Returnval(String returnvalName){
		return rearSentryFunction.Returnval(returnvalName);
	}

	/**
	 * 通过序号得到一个的Returnval，
	 * 实际过程是返回调用rearSentryFunction的同名方法的结果。
	 * @param returnvalIndex 想要获取的Returnval的名字。
	 * @return 指定Returnval的元素，
	 * 如果没有找到就返回null。
	 */
	@Override
	public IReturnval getReturnval(int returnvalIndex){
		return rearSentryFunction.getReturnval(returnvalIndex);
	}

	/**
	 * 自定义Function的这个方法无用，
	 * 实现为空方法。
	 * @return 返回值总是0。
	 */
	@Override
	public int deleteReturnval(String returnvalName){
		//Empty Body
		return 0;
	}

	/**
	 * 获取指定名字的Returnval的序号，
	 * 实际过程是返回调用rearSentryFunction的同名方法的结果。
	 * @param returnvalName 目标Returnval。
	 * @return Returnval的序号，如果不存在就返回-1。
	 */
	@Override
	public int getReturnvalIndexOf(String returnvalName){
		return rearSentryFunction.getReturnvalIndexOf(returnvalName);
	}
}
