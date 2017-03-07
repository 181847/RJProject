package function;

import functionInterface.IMarkInExcuteeStack;

/**
 * 与StaticExcuteeStack相匹配的栈标记，
 * 用一个整形来表示栈的位置。
 */
public class MarkInStaticExcuteeStack implements IMarkInExcuteeStack {
	int markLocation;
	
	public MarkInStaticExcuteeStack(int location){
		markLocation = location;
	}
}
