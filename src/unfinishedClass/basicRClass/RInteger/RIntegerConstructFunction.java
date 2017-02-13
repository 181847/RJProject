package unfinishedClass.basicRClass.RInteger;

import functionInterface.IExcuter;
import functionInterface.IFunction;
import rClassInterface.IRReference;
import unfinishedClass.AbstractConstructFunctionForJava;

public class RIntegerConstructFunction extends AbstractConstructFunctionForJava implements IFunction {
	
	public RIntegerConstructFunction() {
		super("Integer", 1);
		insertParameter("String", "value");
	}

	@Override
	public IExcuter run(int paragraph) {
		String value = (String) getParameter(1).readObject();
		int intValue = 0;
		IRReference newInstance;
		if (!value.isEmpty()){
			try{
				intValue = Integer.parseInt(value);
			} catch (NumberFormatException e){
				System.out.println("错误，Integer型RClass的构造Function中，"
						+ "初始化字符串格式不正确，"
						+ "无法转换为int类型，"
						+ "默认初始化为0。");
				e.printStackTrace();
			}
		}
		
		newInstance = getReturnval(0);
		newInstance.writeObject(new Integer(intValue), "Integer");
		
		return getExcuter(0);
	}

}
