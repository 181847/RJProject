package unfinishedClass.customRClass.scriptBlock;

/**
 * RClassGraph，
 * 作为CusRClass的基本存储结构，
 * 只提供获取RClass数量和RCGNode的方法，
 * 真正实现更加丰富功能的是它的子类：
 * LoadRCGraph和RuntimeRCGraph。
 */
public class RCGraph {
	RCGNode[] vertics;
	int vCount;
	
	/**
	 * @return
	 * 		结点的数量。
	 */
	public int getNum(){
		return vCount;
	}
	
	/**
	 * 通过序号获取图中的结点。
	 * @param index
	 * 		结点的序号。
	 * @return
	 * 		如果序号不合法就返回null。
	 */
	public RCGNode getNode(int index){
		if (index < 0 || index >= vCount){
			//非法序号，返回null。
			return null;
		}
		return vertics[index];
	}
}
