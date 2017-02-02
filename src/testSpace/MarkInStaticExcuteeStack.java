package testSpace;

import basicInterface.IMarkInExcuteeStack;

public class MarkInStaticExcuteeStack implements IMarkInExcuteeStack {
	int markLocation;
	
	public MarkInStaticExcuteeStack(int location){
		markLocation = location;
	}
}
