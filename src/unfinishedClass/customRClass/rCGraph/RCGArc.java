package unfinishedClass.customRClass.rCGraph;

import basicInterface.IFree;

/**
 * 统一的RCGraph中的弧线类型，
 * 对于任何一种RCGNode，
 * 它都有可能被多个子类继承，
 * 所以所有的弧线的出度都要求能够连接成一个链表。
 */
public class RCGArc implements IFree{
	/**
	 * 弧线的出度，
	 * 例如对于弧线：B -----> A，
	 * out代表B端。
	 */
	protected int out;
	
	/**
	 * 弧线的入度，
	 * 例如对于弧线：B -----> A，
	 * in代表A端。
	 */
	protected int in;
	
	/**
	 * 相同入度的下一个弧线，
	 * 例如对于弧线：B -----> A，
	 * nextIn的弧线指向的也一定是A。
	 */
	protected RCGArc nextIn;
	
	/**
	 * @param in
	 * 		设置入度索引，
	 * 		正数表示在本图中的结点，
	 * 		负数表示在运行时继承图中的结点。
	 */
	public RCGArc (int in){
		this.in = in;
	}

	/**
	 * 设置相同入度的弧线链表。
	 * @param inArc
	 * 		相同入度的弧线链表。
	 */
	public void setNextIn(RCGArc inArc) {
		nextIn = inArc;
	}

	/**
	 * 获取相同入度的弧线链表。
	 * @return
	 * 		返回的弧线链表中，
	 * 		所有弧线的入度相同。
	 */
	public RCGArc getNextIn() {
		return nextIn;
	}

	/**
	 * @return
	 * 		弧线的入度部分的序号。
	 */
	public int getInIndex() {
		return in;
	}
	
	public int getOutIndex(){
		return out;
	}

	@Override
	public int free() {
		nextIn = null;
		return 0;
	}
}
