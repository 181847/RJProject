package function;

import function.component.ExceptionExcuter;
import function.tool.FunctionHeadSlot;
import function.tool.FunctionRearSlot;
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

public abstract class AbstractFunctionWithSlot extends AbstractRunableFunction implements IFunction {
	/**
	 * Function的前方插槽
	 * 存储Excutee和Parameter
	 */
	public IFunctionHeadSlot functionHeadSlot;
	
	/**
	 * Function的后方插插槽
	 * 存储Excuter和Returnval
	 */
	public IFunctionRearSlot functionRearSlot;
	
	/**
	 * 本构造方法中会自动对excuterList加一，
	 * 这多出来的一个位置用来插入所有Function当中都需要的“EXCEPTION”Excuter，
	 * 具体调用insertExceptionExcuter()方法插入这个“EXCEPTION”的时候是在
	 * 父类AbstractFunctionWithExceptionExcuter的构造方法中调用。
	 * @param name function的名字
	 * @param excuteeList excutee列表的初始化空间数量。
	 * @param parameterList parameter列表的初始化空间数量。
	 * @param excuterList excuter列表的初始化空间数量。
	 * @param returnvalList returnval列表的初始化空间数量。
	 */
	public AbstractFunctionWithSlot(String name, 
			int excuteeList, int parameterList, 
			int excuterList, int returnvalList) {
		super(name);
		functionHeadSlot = new FunctionHeadSlot(excuteeList, parameterList);
		functionRearSlot = new FunctionRearSlot(excuterList + 1, returnvalList);
		this.insertExcuter(new ExceptionExcuter("EXCEPTION"));
	}

	@Override
	public abstract boolean needParameters();

	@Override
	public abstract IExcuter run(int paragraph);
	
	@Override
	public abstract void fillContentGraph();

	@Override
	public abstract void clearGraph();
	

	/**
	 * 得到这个function节点所有Excutee/Parameter/Excuter/Returnval的总数
	 * @return function节点所有Excutee/Parameter/Excuter/Returnval的总数
	 */
	@Override
	public int getNum(){
		return functionHeadSlot.getNum() + functionRearSlot.getNum();
	}
	
	/**
	 * 对当前Function中的所有Parameter调用extractParameter()方法，
	 */
	public void prepareParameters(){
		functionHeadSlot.prepareParameters();
	}
	
	
	//******************************************About Slot**************************************************
	/**
	 * 得到Function实例对象的节点前方的插口引用。
	 * @return 一个FunctionHeadSlot，
	 * 这个插口当中包含了本Function实例的Excutee和Parameter。
	 */
	@Override
	public IFunctionHeadSlot getHeadSlot() {
		return functionHeadSlot;
	}
	
	/**
	 * 得到Function实例对象的节点后方的插口引用。
	 * @return 一个FunctionRearSlot。
	 * 这个插口当中包含了本Function实例的Excuter和Returnval。
	 */
	@Override
	public IFunctionRearSlot getRearSlot() {
		return functionRearSlot;
	}
	
	//******************************************About ComponentList*********************************************
	/**
	 * 得到ExcuteeList。
	 * @return 一个Excutee列表。
	 */
	@Override
	public IExcuteeList getExcuteeList() {
		return functionHeadSlot.getExcuteeList();
	}
	
	/**
	 * 得到ParameterList。
	 * @return 一个Parameter列表。
	 */
	@Override
	public IParameterList getParameterList() {
		return functionHeadSlot.getParameterList();
	}

	/**
	 * 得到ExcuterList。
	 * @return 一个Excuter列表。
	 */
	@Override
	public IExcuterList getExcuterList() {
		return functionRearSlot.getExcuterList();
	}

	/**
	 * 得到ReturnvalList。
	 * @return 一个Returnval列表。
	 */
	@Override
	public IReturnvalList getReturnvalList() {
		return functionRearSlot.getReturnvalList();
	}

	//******************************************About Excutee**************************************************
	/**
	 * 插入一个Excutee，同时设置这个Excutee的hostFunction为this，
	 * 用int返回插入的结果。
	 * @param excutee 要插入的Excutee。
	 * @return 如果插入的Excutee为null返回0；
	 * 如果已存在相同名字的Excuter就返回2；
	 * 如果插入成功就返回1。
	 */
	@Override
	public int insertExcutee(IExcutee excutee) {
		if (excutee == null){
			return 0;
		}
		excutee.setHostFunction(this);
		return functionHeadSlot.insertExcutee(excutee);
	}

	/**
	 * 通过名字得到一个的Excutee，
	 * @param excuteeName 想要获取的Excutee的名字。
	 * @return 指定Excutee的元素，
	 * 如果没有找到就返回null。
	 */
	@Override
	public IExcutee Excutee(String excuteeName) {
		return functionHeadSlot.Excutee(excuteeName);
	}

	/**
	 * 通过序号得到一个的Excutee，
	 * @param excuteeIndex 想要获取的Excutee的名字。
	 * @return 指定Excutee的元素，
	 * 如果没有找到就返回null。
	 */
	@Override
	public IExcutee getExcutee(int excuteeNumber) {
		return functionHeadSlot.getExcutee(excuteeNumber);
	}

	/**
	 * 删除一个Excutee，
	 * @param excuteeName 想要删除的的Excutee的名字。
	 * @return 未找到指定名称的Excutee返回-1。
	 * 成功则返回1。
	 */
	@Override
	public int deleteExcutee(String excuteeName) {
		return functionHeadSlot.deleteExcutee(excuteeName);
	}

	/**
	 * 获取指定excutee在本function中的序号。
	 * @param excuteeName excutee的名字。
	 * @return excutee在本function中的序号。
	 */
	@Override
	public int getExcuteeIndexOf(String excuteeName) {
		return functionHeadSlot.getExcuteeIndexOf(excuteeName);
	}
	
	//******************************************About Parameter**************************************************
	/**
	 * 插入一个Parameter，用int返回插入的结果。
	 * @param parameter 要插入的Parameter。
	 * @return 如果插入的Parameter为null返回0；
	 * 如果已存在相同名字的Parameter就返回2；
	 * 如果插入成功就返回1。
	 */
	@Override
	public int insertParameter(IParameter parameter) {
		return functionHeadSlot.insertParameter(parameter);
	}

	/**
	 * 通过名字得到一个的Parameter，
	 * @param parameterName 想要获取的Parameter的名字。
	 * @return 指定Parameter的元素，
	 * 如果没有找到就返回null。
	 */
	@Override
	public IParameter Parameter(String parameterName) {
		return functionHeadSlot.Parameter(parameterName);
	}

	/**
	 * 通过序号得到一个的Parameter，
	 * @param parameterIndex 想要获取的Parameter的序号。
	 * @return 指定Parameter的元素，
	 * 如果没有找到就返回null。
	 */
	@Override
	public IParameter getParameter(int parameterNumber) {
		return functionHeadSlot.getParameter(parameterNumber);
	}

	/**
	 * 删除一个Parameter。
	 * @param parameterName 想要删除的的Parameter的名字。
	 * @return 未找到指定名称的Parameter返回-1。
	 * 成功则返回1。
	 */
	@Override
	public int deleteParameter(String parameterName) {
		return functionHeadSlot.deleteParameter(parameterName);
	}

	/**
	 * 获取指定paramete在本function中的序号。
	 * @param parameterName paramete的名字。
	 * @return paramete在本function中的序号。
	 */
	@Override
	public int getParameterIndexOf(String parameterName) {
		return functionHeadSlot.getParameterIndexOf(parameterName);
	}
	
	//******************************************About Excuter**************************************************
	/**
	 * 插入一个Excuter，用int返回插入的结果。
	 * @param excuter 要插入的Excuter。
	 * @return 如果插入的Excuter为null返回0；
	 * 如果已存在相同名字的Excuter就返回2；
	 * 如果插入成功就返回1。
	 */@Override
	public int insertExcuter(IExcuter excuter) {
		return functionRearSlot.insertExcuter(excuter);
	}

	 /**
	 * 通过名字得到一个的Excuter，
	 * @param excuterName 想要获取的Excuter的名字。
	 * @return 指定Excuter的元素，
	 * 如果没有找到就返回null。
	 */
	@Override
	public IExcuter Excuter(String excuterName) {
		return functionRearSlot.Excuter(excuterName);
	}

	/**
	 * 通过序号得到一个的Excuter，
	 * @param excuterIndex 想要获取的Excuter的名字。
	 * @return 指定Excuter的元素，
	 * 如果没有找到就返回null。
	 */
	@Override
	public IExcuter getExcuter(int excuterNumber) {
		return functionRearSlot.getExcuter(excuterNumber);
	}

	/**
	 * 删除一个Excuter，
	 * @param excuterName 想要删除的的Excuter的名字。
	 * @return 未找到指定名称的Excuter返回-1。
	 * 成功则返回1。
	 */
	@Override
	public int deleteExcuter(String excuterName) {
		return functionRearSlot.deleteExcuter(excuterName);
	}

	/**
	 * 获取指定excuter在本function中的序号。
	 * @param excuterName excuter的名字。
	 * @return excuter在本function中的序号。
	 */
	@Override
	public int getExcuterIndexOf(String excuterName) {
		return functionRearSlot.getExcuterIndexOf(excuterName);
	}
	
	//******************************************About Returnval**************************************************
	/**
	 * 插入一个Returnval，用int返回插入的结果。
	 * @param returnval 要插入的Returnval。
	 * @return 如果插入的Returnval为null返回0；
	 * 如果已存在相同名字的Returnval就返回2；
	 * 如果插入成功就返回1。
	 */
	@Override
	public int insertReturnval(IReturnval returnval) {
		return functionRearSlot.insertReturnval(returnval);
	}

	/**
	 * 通过名字得到一个的Returnval，
	 * @param returnvalName 想要获取的Returnval的名字。
	 * @return 指定Returnval的元素，
	 * 如果没有找到就返回null。
	 */
	@Override
	public IReturnval Returnval(String returnvalName) {
		return functionRearSlot.Returnval(returnvalName);
	}

	/**
	 * 通过序号得到一个的Returnval，
	 * @param returnvalIndex 想要获取的Returnval的名字。
	 * @return 指定Returnval的元素，
	 * 如果没有找到就返回null。
	 */
	@Override
	public IReturnval getReturnval(int returnvalNumber) {
		return functionRearSlot.getReturnval(returnvalNumber);
	}

	/**
	 * 删除一个Returnval，
	 * @param returnvalName 想要删除的的Returnval的名字。
	 * @return 未找到指定名称的Returnval返回-1。
	 * 成功则返回1。
	 */
	@Override
	public int deleteReturnval(String returnvalName) {
		return functionRearSlot.deleteReturnval(returnvalName);
	}

	/**
	 * 获取指定returnval在本function中的序号。
	 * @param returnvalName returnval的名字。
	 * @return returnval在本function中的序号。
	 */
	@Override
	public int getReturnvalIndexOf(String returnvalName) {
		return functionRearSlot.getReturnvalIndexOf(returnvalName);
	}
}
