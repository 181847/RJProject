package unfinishedClass.basicRClass;

import rClass.AbstractRClassWithFunctionList;
import rClass.RReference;
import rClassInterface.IRReference;

public class RCharacter extends AbstractRClassWithFunctionList {

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

}
