package testSpace;
import functionInterface.IExcuter;

public class OriginalFunction extends AbstractFunction {
	public OriginalFunction(){
		super(2, 0, 0, 0);
		insertExcutee(new NormalExcutee("fire", 1));
		insertExcutee(new NormalExcutee("fireSlot2", 2));
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
		default:
			return null;
		}
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
