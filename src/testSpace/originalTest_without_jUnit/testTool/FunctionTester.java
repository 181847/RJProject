package testSpace.originalTest_without_jUnit.testTool;
import functionInterface.*;

public class FunctionTester{
	IFunction testFunctionObject;
	
	public FunctionTester(IFunction testFunctionObject){
		this.testFunctionObject = testFunctionObject;
	}
	
	/**
	 *循环测试每个Excutee，遇到null就立即返回
	 *@return 测试的Excutee的个数
	 */
	public int test(){
		int i = 0;
		IExcutee testExcutee = testFunctionObject.getExcutee(i);
		IExcuter testExcuter;
		
		while(testExcutee != null){
			//单次发动分界线
			System.out.println("////******Run*****************");
			//显示Excutee的名字
			System.out.println("EXCUTEE: " + testExcutee.getName());
			
			showParameters();
			testExcutee.fire();
			showReturnvals();
			
			//得到下个要发动的Excuter
			testExcuter = testExcutee.getExcuter();
			//如果Excuter不为null，输出Excuter的名字
			//否则，输出null
			if (testExcuter == null){
				System.out.println("NEXT EXCUTER: null");
			}
			else{
				System.out.println("NEXT EXCUTER: " + testExcuter.getName());
			}
			//单次发动结束分界线
			System.out.println("**********RunEnd**********////\n\n");
			
			testExcutee = testFunctionObject.getExcutee(++i);
		}
		return i;
	}
	
	
	/**
	 * 显示当前testFunctionObject的所有返回值
	 */
	public void showParameters(){
		Object data;
		int i = 0;
		IParameter testParameter = testFunctionObject.getParameter(i);
		System.out.println("(*********Para**********");
		
		while (testParameter != null){
			//参数类型+名字
			System.out.print(testParameter.getReferenceRClass() 
					+ " " + testParameter.toString() + ":");
			
			data = testParameter.readObject();
			
			if (data != null){
				System.out.println(data.toString());
			}
			testParameter = testFunctionObject.getParameter(++i);
		}
		System.out.println("**********ParaEnd******)");
	}
	
	/**
	 * 显示当前testFunctionObject的所有参数
	 */
	public void showReturnvals(){
		Object data;
		int i = 0;
		IReturnval testReturnval = testFunctionObject.getReturnval(i);
		System.out.println("[*********Retu**********");
		
		while (testReturnval != null){
			//返回值类型+返回值名称
			System.out.print(testReturnval.getReferenceRClass() 
							  + " " + testReturnval.toString() + ":");
			
			data = testReturnval.readObject();
			
			if (data != null){
				System.out.println(data.toString());
			}
			testReturnval = testFunctionObject.getReturnval(++i);
		}
		System.out.println("**********RetuEnd*******]");
	}
}
