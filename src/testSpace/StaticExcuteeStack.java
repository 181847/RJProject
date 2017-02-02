package testSpace;

import basicInterface.IExcuteeStack;
import basicInterface.IMarkInExcuteeStack;
import functionInterface.IExcutee;

//StaticExcuteeStack不允许有null元素压入栈内
public class StaticExcuteeStack implements IExcuteeStack {
	IExcutee[] buffer;
	int top;
	int capacity;
	
	
	
	public StaticExcuteeStack(int n){
		buffer = new IExcutee[n];
		capacity = n;
		//数组元素全部初始化为null
		for (top = 0; top < capacity; ++top)
			buffer[top] = null;
		top = 0;
	}
	
	public StaticExcuteeStack(){
		buffer = new IExcutee[100];
		capacity = 100;
		//数组元素全部初始化为null
		for (top = 0; top < capacity; ++top)
			buffer[top] = null;
		top = 0;
	}
	
	/**
	 * 栈是否为空
	 */
	@Override
	public boolean isEmpty() {
		return top == 0;
	}


	
	/**
	 *压入Excutee
	 *如果栈满
	 *就直接返回0
	 *如果传入的元素为null
	 *就直接返回-1
	 */
	@Override
	public int pushExcutee(IExcutee excutee){
		if (top >= capacity)
			return 0;
		if (excutee == null)
			return -1;
		
		buffer[top++] = excutee;
		return 1;
	}

	/**
	 *获取顶部的Excutee
	 */
	@Override
	public IExcutee getTopExcutee() {
		if (top == 0)
			return null;
		return buffer[top - 1];
	}

	/**
	 *将顶部的Excutee弹出栈来
	 */
	@Override
	public IExcutee popExcutee() {
		if (top <= 0){
			return null;
		}
		
		IExcutee tempExcutee = buffer[--top];
		buffer[top] = null;
		return tempExcutee;
	}

	/**
	 *将当前的栈空间清理到指定的标记处，
	 *标记处也要清理
	 *top会指向标记位置,注：top所指向的位置是一个空的栈顶
	 *清除位置超过栈顶（大于等于top）返回2
	 *正常清除返回1
	 *mark为null，返回0
	 *mark类型不合法返回-1
	 *清除位置小于栈顶返回-2
	 */
	@Override
	public int clearTo(IMarkInExcuteeStack mark) {
		if (mark == null)
			return 0;
		if ( !checkMark(mark) )
			return -1;
		
		//强制类型转换，将mark转换为MarkInStaticExcuteeStack类型
		MarkInStaticExcuteeStack properMark = (MarkInStaticExcuteeStack)mark;
		int destination = properMark.markLocation;
		
		if (destination >= top)
			return 2;
		if (destination < 0)
			return -2;
		
		do{
			buffer[--top] = null;
		}while(top > destination);
		
		return 1;
	}

	/**
	 *获取当前栈顶元素的标记位置
	 *因为top指向的是栈顶元素的下一个空位
	 *所以要返回top-1
	 */
	@Override
	public IMarkInExcuteeStack getMark() {
		return new MarkInStaticExcuteeStack(top - 1);
	}

	/**
	 * 检查当前excuteeStack与指定IMarkInExcuteeStack的实例对象的相容性
	 */
	@Override
	public boolean checkMark(IMarkInExcuteeStack mark) {
		return mark instanceof MarkInStaticExcuteeStack;
	}
	
}
