package testSpace;
import functionInterface.*;

public class FunctionTester{
	IFunction testFunctionObject;
	
	public FunctionTester(IFunction testFunctionObject){
		this.testFunctionObject = testFunctionObject;
	}
	
	public int test(){
		IExcutee testExcutee = testFunctionObject.getExcutee(0);
		IExcuter testExcuter;
		int i = 0;
		while(testExcutee != null){
			System.out.println("////******Run*****************");
			//显示Excutee的名字
			System.out.println("EXCUTEE: " + testExcutee.getName());
			
			
			showParameters();
			testExcutee.fire();
			showReturnvals();
			
			
			testExcuter = testExcutee.getExcuter();
			//如果Excuter不为null，输出Excuter的名字
			//否则，输出null
			if (testExcuter == null){
				System.out.println("NEXT EXCUTER: null");
			}
			else{
				System.out.println("NEXT EXCUTER: " + testExcuter.getName());
			}
			
			System.out.println("**********RunEnd**********////\n\n");
			testExcutee = testFunctionObject.getExcutee(++i);
		}
		return i;
	}
	
	public void showParameters(){
		IParameter testParameter = testFunctionObject.getParameter(0);
		int i = 0;
		Object data;
		System.out.println("(*********Para**********");
		while (testParameter != null){
			//参数类型+名字
			System.out.print(testParameter.getReferenceClass() 
					+ " " + testParameter.toString() + ":");
			data = testParameter.readObject();
			
			//如果数据不为空，输出数据
			if (data != null)
				System.out.println(data.toString());
			testParameter = testFunctionObject.getParameter(++i);
		}
		
		System.out.println("**********ParaEnd******)");
	}
	
	public void showReturnvals(){
		IReturnval testReturnval = testFunctionObject.getReturnval(0);
		int i = 0;
		Object data;
		System.out.println("[*********Retu**********");
		while (testReturnval != null){
			//返回值类型+返回值名称
			System.out.print(testReturnval.getReferenceClass() 
							  + " " + testReturnval.toString() + ":");
			data = testReturnval.readObject();
			//如果读取的数据不为空，就输出数据
			if (data != null)
				System.out.println(data.toString());
			testReturnval = testFunctionObject.getReturnval(++i);
		}
		System.out.println("**********RetuEnd*******]");
	}
}
