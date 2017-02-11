package function;

import basicTool.NamedItemList;
import functionInterface.IFunction;
import functionInterface.IFunctionList;

public class FunctionList extends NamedItemList implements IFunctionList {
	
	public FunctionList(){
		//按照父类NameItemList的无参构造方法
		//自动申请五个元素空间
		super();
	}
	
	public FunctionList(int space){
		super(space);
	}
	
	/**
	 * 添加Function。
	 * @param function 要添加的Function对象。
	 * @return 如果添加的元素为null返回0；
	 * 如果插入成功就返回1；
	 * 如果已存在相同名字的元素就返回2。
	 */
	@Override
	public int insertFunction(IFunction function) {
		return insertItem(function);
	}

	/**
	 * 通过名字查找Function。
	 * @param functionName 要查找的function的名字。
	 * @return 指定名字的Function，
	 * 失败返回null。
	 */
	@Override
	public IFunction getFunction(String functionName) {
		return (IFunction) getItem(functionName);
	}

	/**
	 * 通过序号得到一个Function。
	 * @param index 想要获取的Function的在Function列表中的序号。
	 * @return 指定序号的Function，
	 * 如果没有找到就返回null。
	 */
	@Override
	public IFunction getFunction(int functionNumber) {
		return (IFunction) getItem(functionNumber);
	}

	/**
	 * 删除一个Function，
	 * @param name 想要删除的的Function的名字。
	 * @return 未找到指定名称的Function返回-1。
	 * 成功则返回1。
	 */
	@Override
	public int deleteFunction(String functionName) {
		return deleteItem(functionName);
	}

}
