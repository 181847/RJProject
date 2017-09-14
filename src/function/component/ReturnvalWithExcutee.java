package function.component;

import functionInterface.IExcutee;
import functionInterface.IReturnval;
import rClass.RReference;

/**
 * 带有执行入口的返回值插口，
 * 这种返回值插口专门用于BasicCalculateorFunction（基本运算节点），
 * 当这种返回值插口被参数访问，它所对应的基本运算节点将会执行运算功能。
 */
public class ReturnvalWithExcutee extends RReference implements IReturnval {
	IExcutee excutee;
	
	/**
	 * @param referenceClass 返回值的类型。
	 * @param returnvalName 返回值的名字。
	 */
	public ReturnvalWithExcutee(String referenceClass, String returnvalName){
		super(referenceClass, returnvalName);
	}
	
	/**
	 * @return 与本返回值相连接的Function的Excutee，
	 * 这个Exuctee一般是包含这个返回值的BasicCalculatorFunction的Exuctee。
	 */
	@Override
	public IExcutee getExcutee()
	{
		return excutee;
	}

	/**
	 * @param excutee 要连接的Excutee，
	 * 这个Exuctee一般是包含这个返回值的BasicCalculatorFunction的Exuctee。
	 * @return 插入元素为null返回0，成功返回1。
	 */
	@Override
	public int linkExcutee(IExcutee excutee)
	{
		if (excutee == null){
			return 0;
		}
		this.excutee = excutee;
		return 1;
	}

}
