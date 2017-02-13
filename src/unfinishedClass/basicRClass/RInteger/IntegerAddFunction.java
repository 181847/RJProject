package unfinishedClass.basicRClass.RInteger;

import basicInterface.IModifier;
import function.component.Excuter;
import function.component.LinerExcutee;
import function.component.Parameter;
import function.component.Returnval;
import functionInterface.IExcuter;
import functionInterface.IFunction;
import functionInterface.IParameterList;
import functionInterface.IReturnval;
import unfinishedClass.AbstractFunctionNeedParameterForJava;

public class IntegerAddFunction extends AbstractFunctionNeedParameterForJava implements IFunction, IModifier {
	
	public IntegerAddFunction(){
		super("IntegerAddFunction", 1, 1, 1, 1);
		insertExcutee(new LinerExcutee("add", 1));
		insertParameter(new Parameter("Integer", "param_1"));
		insertExcuter(new Excuter("finished"));
		insertReturnval(new Returnval("Integer", "result"));
		
	}

	/**
	 * 这里返回的自定义信息是参数的数量。
	 * @return 参数的数量（字符串类型的信息）。
	 */
	@Override
	public String getModifierInfo() {
		//用字符串返回参数的数量
		return Integer.toString(getParameterList().getNum());
	}

	/**
	 * 接收一个写有整型数字的字符串，
	 * 要求整型数字大于等于1，
	 * 表示向当前参数列表增加多少个Integer类型的参数。
	 * @param modifierInfo 增加的参数数量（字符串类型的信息）
	 * @return 成功返回1，失败返回0。
	 */
	@Override
	public int modify(String modifierInfo) {
		int addPara = 0;
		try{
			addPara = Integer.parseInt(modifierInfo);
		} catch (NumberFormatException e){
			System.out.println("错误，IntegerAddFunction试图通过modify(String)方法增加参数，"
					+ "但是传入的字符串无法转换为整型。");
			e.printStackTrace();
		}
		
		
		for (int currentParaIndex = getParameterList().getNum() + 1; 
				addPara > 0;
				++currentParaIndex, --addPara){
			insertParameter(new Parameter("Integer", "param_" + currentParaIndex));
		}
		return 0;
	}

	@Override
	public IExcuter run(int paragraph) {
		int sum = 0;
		IParameterList parameterList = getParameterList();
		for (int i = parameterList.getNum() - 1; i >= 0; --i){
			sum += (Integer)(parameterList.getParameter(i).readObject());
		}
		IReturnval returnval= getReturnval(0);
		returnval.mallocSpace(1);
		returnval.writeObject(new Integer(sum), "Integer");
		parameterList = null;
		returnval = null;
		return getExcuter(0);
	}
}
