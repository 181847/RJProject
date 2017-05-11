package unfinishedClass.customRClass.rCGraph;

/**
 * 统一的RCGraph中的弧线类型，
 * 对于任何一种RCGNode，
 * 它都有可能被多个子类继承，
 * 所以所有的弧线的出度都要求能够连接成一个链表。
 */
public class RCGArc {
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
}
