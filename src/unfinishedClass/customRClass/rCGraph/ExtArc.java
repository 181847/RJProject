package unfinishedClass.customRClass.rCGraph;

/**
 * 非接口继承弧线，
 * 弧线的出度不能形成链表，
 * 只有入度能够形成链表。
 */
public class ExtArc extends RCGArc {
	/**
	 * @param in
	 * 		设置入度索引，
	 * 		正数表示在本图中的结点，
	 * 		负数表示在运行时继承图中的结点。
	 */
	public ExtArc (int in){
		super(in);
	}
}
