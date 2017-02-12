package unfinishedClass;

import functionInterface.IExcutee;
import functionInterface.IReturnval;
import rClass.RReference;

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
	 */
	@Override
	public void linkExcutee(IExcutee excutee)
	{
		this.excutee = excutee;
	}

}
