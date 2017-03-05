package unfinishedClass.basicRClass;

import rClass.AbstractRClassForJava;
import rClass.RReference;
import rClassInterface.IRClass;
import rClassInterface.IRReference;
import unfinishedClass.RLogger;

public class RCharacter extends AbstractRClassForJava implements IRClass {

	public RCharacter(){
		super("Character");
	}
	
	@Override
	public IRReference getNewInstance() {
		IRReference newInstance = new RReference();
		newInstance.mallocSpace(1);
		newInstance.writeObject(new Character('\0'), "Character");
		return newInstance;
	}


	@Override
	public int loadFunction() {
		RLogger.log(this.getName() + " 加载成员Function。");
		return 0;
	}

}
