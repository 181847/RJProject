package unfinishedClass;
import functionInterface.IExcuter;
import function.LinerExcutee;
import function.Parameter;
import function.Excuter;
import function.FunctionNeedParameters;
import function.Returnval;

public class HelloWorldFunction extends FunctionNeedParameters {
	
	public HelloWorldFunction(){
		super(3, 2, 1, 1);
		//创建Excutee
		insertExcutee(new LinerExcutee("fire", 1));
		insertExcutee(new LinerExcutee("fireSlot2", 2));
		insertExcutee(new LinerExcutee("tryPara&Retu", 3));
		//创建Parameter
		insertParameter(new Parameter("Integer", "n"));
		insertParameter(new Parameter("Character", "ch"));
		//创建Excuter
		insertExcuter(new Excuter("endTry"));
		//创建Returnval
		insertReturnval(new Returnval("Integer", "return"));
		//设置Function的名字
		setName("Hello World Function");
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
	public void fillContentGraph() {
		//Empty body
		
	}

	@Override
	public void clearGraph() {
		//Empty body
	}
}
