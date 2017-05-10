package unfinishedClass.customRClass.scriptBlock;

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
}
