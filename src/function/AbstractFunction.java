package function;

import basicTool.NameableWithString;
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

public abstract class AbstractFunction extends NameableWithString implements IFunction {
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
	 * Function所属的RClass的ID。
	 */
	int rClassID;
	
	/**
	 * @param name function的名字。
	 */
	public AbstractFunction(String name){
		super(name);
		functionHeadSlot = new FunctionHeadSlot();
		functionRearSlot = new FunctionRearSlot();
	}
	
	/**
	 * @param name function的名字
	 * @param excuteeList excutee列表的空间数量
	 * @param parameterList parameter列表的空间数量
	 * @param excuterList excuter列表的空间数量
	 * @param returnvalList returnval列表的空间数量
	 * 列表空间无需担心，如果不够程序会自动扩张，
	 * 但是预先就设置好的话能节省性能。
	 */
	public AbstractFunction(String name, 
			int excuteeList, int parameterList, int excuterList, int returnvalList)
	{
		super(name);
		functionHeadSlot = new FunctionHeadSlot(excuteeList, parameterList);
		functionRearSlot = new FunctionRearSlot(excuterList, returnvalList);
	}
	
	//请重载下面这些方法
	//********************************************** ABOUT FUNCTION *************************************************************
	public abstract IExcuter run(int paragraph);
	public abstract void fillContentGraph();
	public abstract void clearGraph();
	public abstract boolean needParameters();
	
	/**
	 * 开始function的参数处理，以及发动run方法。
	 * @param paragraph 用于区分不同运算功能的数字。
	 * @return 下一步要执行的Excuter。
	 */
	public IExcuter invoke(int paragraph){
		prepareParameters();
		return run(paragraph);
	}
	
	public int getRClassID(){
		return rClassID;
	}
	
	public void setRClassID(int rClassID){
		this.rClassID = rClassID;
	}
	
	//********************************************** ABOUT EXCUTEE *************************************************************
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
		if (excutee == null)
			return 0;
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
	
	@Override
	public int getExcuteeIndexOf(String excuteeName) {
		return functionHeadSlot.getExcuteeIndexOf(excuteeName);
	}

	
	//********************************************** ABOUT PARAMETER *************************************************************
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
	
	@Override
	public int getParameterIndexOf(String parameterName) {
		return functionHeadSlot.getParameterIndexOf(parameterName);
	}

	/**
	 * 对当前Function中的所有Parameter调用extractParameter()方法，
	 */
	public void prepareParameters(){
		functionHeadSlot.prepareParameters();
	}

	//********************************************** ABOUT EXCUTER *************************************************************
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

	@Override
	public int getExcuterIndexOf(String excuterName) {
		return functionRearSlot.getExcuterIndexOf(excuterName);
	}
	
	//********************************************** ABOUT RETURNVAL *************************************************************
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
	
	@Override
	public int getReturnvalIndexOf(String returnvalName) {
		return functionRearSlot.getReturnvalIndexOf(returnvalName);
	}
	
	//********************************************** ABOUT GET LIST *************************************************************
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
	
	
	
	
	//********************************************** ABOUT GET SLOT *************************************************************
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

	//********************************************** ABOUT GET NUM *************************************************************
	/**
	 * 得到这个function节点所有Excutee/Parameter/Excuter/Returnval的总数
	 * @return function节点所有Excutee/Parameter/Excuter/Returnval的总数
	 */
	@Override
	public int getNum(){
		return functionHeadSlot.getNum() + functionRearSlot.getNum();
	}


}
