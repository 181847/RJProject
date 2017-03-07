package function;

import functionInterface.IExcutee;
import functionInterface.IExcuteeStack;
import functionInterface.IMarkInExcuteeStack;

/**
 * StaticExcuteeStack不允许有null元素压入栈内。
 */
public class StaticExcuteeStack implements IExcuteeStack {
	IExcutee[] buffer;
	int top;
	int capacity;
	
	
	
	public StaticExcuteeStack(int n){
		buffer = new IExcutee[n];
		capacity = n;
		
		//数组元素全部初始化为null
		for (top = 0; top < capacity; ++top){
			buffer[top] = null;
		}
		top = 0;
	}
	
	public StaticExcuteeStack(){
		buffer = new IExcutee[100];
		capacity = 100;
		
		//数组元素全部初始化为null
		for (top = 0; top < capacity; ++top){
			buffer[top] = null;
		}
		top = 0;
	}
	
	/**
	 * 栈是否为空。
	 * @return 空则返回false；
	 * 不空返回true。
	 */
	@Override
	public boolean isEmpty() {
		return top == 0;
	}

	/**
	 * 压入Excutee。
	 * @param 要压入栈内的元素
	 * @return 如果栈满就直接返回2；
	 * 如果传入的元素为null返回0；
	 * 成功返回1。
	 */
	@Override
	public int pushExcutee(IExcutee excutee){
		if (top >= capacity){
			return 2;
		}
		if (excutee == null){
			return 0;
		}
		buffer[top++] = excutee;
		return 1;
	}


	/**
	 * 获取顶部的Excutee，但不将其从栈内弹出来。
	 * @return 栈顶的Excutee。
	 */
	@Override
	public IExcutee getTopExcutee() {
		if (top == 0){
			return null;
		}
		return buffer[top - 1];
	}

	/**
	 * 将顶部的Excutee弹出栈来。
	 * @return 栈顶的Excutee。
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
	 * 将当前的栈空间清理到指定的标记处。
	 * @param mark 与ExcuteeStack相匹配的栈元素位置标记
	 * @return 清除位置超过栈顶（大于等于top）返回2；
	 * 正常清除返回1；
	 * mark为null返回0；
	 * mark类型不合法返回-1；
	 * 清除位置小于栈底返回-2。
	 */
	@Override
	public int clearTo(IMarkInExcuteeStack mark) {
		if (mark == null){
			return 0;
		}
		if ( !checkMark(mark) ){
			return -1;
		}
		
		//强制类型转换，将mark转换为MarkInStaticExcuteeStack类型
		MarkInStaticExcuteeStack properMark = (MarkInStaticExcuteeStack)mark;
		int destination = properMark.markLocation;
		
		if (destination >= top){
			return 2;
		}
		if (destination < 0){
			return -2;
		}
		
		do{
			buffer[--top] = null;
		}while(top > destination);
		
		return 1;
	}

	/**
	 * 获取当前栈顶的标记，
	 * @return 当前栈顶元素的位置标记，可用于之后栈高度增加后，
	 * 清除栈内元素到指定标记处。
	 */
	@Override
	public IMarkInExcuteeStack getMark() {
		return new MarkInStaticExcuteeStack(top - 1);
	}

	/**
	 * 检查当前excuteeStack与指定IMarkInExcuteeStack的实例对象的相容性，
	 * @param mark 一个ExcuteeStack的标记对象，
	 * @return 当前mark是否能够用于当前ExcuteeStack。
	 */
	@Override
	public boolean checkMark(IMarkInExcuteeStack mark) {
		return mark instanceof MarkInStaticExcuteeStack;
	}
	
}
