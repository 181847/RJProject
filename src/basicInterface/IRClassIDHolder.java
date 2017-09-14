package basicInterface;

/**
 * 所有拥有RClassID类型的接口，
 * 例如
 * 【
 * RClass对象，
 * RClass拥有的RClassID是这个RClass在RClassLoader中分配到的RClassID，
 * 每个被创建的RClass对象都应该被分配唯一一个RClass。
 * 】
 * 【
 * FunctionMaker，
 * FunctionMaker属于每个RClass中的组件，
 * 用来产生Function对象和填充CustomFunction对象。
 * 】
 * 【
 * RReference、Parameter、Returnval，
 * 这些对象是一种引用类型，用来引用相关数据，
 * 他们的RClassID是指引用数据所属的RClass。
 * 】
 * 【
 * Function，
 * Function拥有的RClassID用来表示这个Function从哪个RClass产生而来。
 * 】
 */
public interface IRClassIDHolder {
	/**
	 * 获取RClassID。
	 * @return RClassID。
	 */
	public int getRClassID();
	
	/**
	 * 设置RClassID。
	 * @param 要设置的rClassID。
	 */
	public void setRClassID(int rClassID); 
}
