package unfinishedClass;

import basicTool.NamedItemList;
import functionInterface.IFunction;
import functionInterface.IFunctionList;

public class FunctionList extends NamedItemList implements IFunctionList {
	
	public FunctionList(){
		//按照父类NameItemList的无参构造方法
		//自动申请五个元素空间
	}
	
	public FunctionList(int space){
		super(space);
	}
	
	/**
	 * 添加Function
	 * @param excutee
	 * @return 成功返回1
	 */
	@Override
	public int insertFunction(IFunction function) {
		return insertItem(function);
	}

	/**
	 * 通过名字查找Function
	 * @param functionName 要查找的function的名字
	 * @return 失败返回null
	 */
	@Override
	public IFunction Function(String functionName) {
		return (IFunction) getItem(functionName);
	}

	/**
	 * 通过序号查找Function
	 * @param functionNumber 要查找的function的序号，调用者必须知道目标的序号
	 * @return 失败返回null
	 */
	@Override
	public IFunction getFunction(int functionNumber) {
		return (IFunction) getItem(functionNumber);
	}

	/**
	 * 删除指定名称的Function
	 * @param functionName 要删除的目标Function的名字
	 * @return 删除成功返回1，失败返回0
	 */
	@Override
	public int deleteFunction(String functionName) {
		return deleteItem(functionName);
	}

}
