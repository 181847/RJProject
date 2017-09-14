package unfinishedClass.basicRClass.RUtils;
import function.AbstractFunctionForJava;
import functionInterface.IExcuter;

public class HelloWorldFunction extends AbstractFunctionForJava {
	
	public HelloWorldFunction(){
		super("HelloWorldFunction", 3, 2, 1, 1);
		//创建Excutee
		insertExcutee("fire", 1);
		insertExcutee("fireSlot2", 2);
		insertExcutee("tryPara&Retu", 3);
		//创建Parameter
		insertParameter("Integer", "n");
		insertParameter("Character", "ch");
		//创建Excuter
		insertExcuter("endTry");
		//创建Returnval
		insertReturnval("Integer", "return");
	}

	@Override
	public IExcuter run(int paragraph) {
		switch(paragraph)
		{
		case 1:
			System.out.println("Hello World");
			return null;
		case 2:
			System.out.println("fireSlot2 fired");
			return null;
		case 3:
			return myInnerFun();
		default:
			return null;
		}
	}
	
	private IExcuter myInnerFun(){
		int n = (Integer)Parameter("n").readObject();
		char ch = (Character)Parameter("ch").readObject();
		
		System.out.println("参数n：" + n + " 参数ch：" + ch);
		Returnval("return").writeObject(new Integer(n + 1), "Integer");
		
		return Excuter("endTry");
	}

	@Override
	public boolean needParameters() {
		// TODO Auto-generated method stub
		return false;
	}
}
