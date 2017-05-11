package unfinishedClass.customRClass.rCGraph;

/**
 * 接口继承弧线，
 * 由于支持接口多继承，
 * 所以和ExtArc不同，
 * ImpArc的出度能够形成链表。
 */
public class ImpArc extends RCGArc {
	protected ImpArc nextOut;
	/**
	 * @param in
	 * 		设置入度索引，
	 * 		正数表示在本图中的结点，
	 * 		负数表示在运行时继承图中的结点。
	 */
	public ImpArc(int in) {
		super(in);
		nextOut = null;
	}

	/**
	 * 与相同出度的弧线形成出度链表，
	 * 单纯的设置nextOut为参数指明的弧线对象。
	 * @param impOut
	 * 		假定相同出度的弧线。
	 */
	public void setNextOut(ImpArc nextOut) {
		this.nextOut = nextOut;
	}
}
