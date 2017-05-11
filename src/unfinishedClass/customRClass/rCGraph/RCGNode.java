package unfinishedClass.customRClass.rCGraph;

import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.struct.RClassStruct;

/**
 * RCGNode作为RCGraph中的结点，
 * 用于代表一个拥有继承信息的RClass结点。
 * 日后可能直接让这个类型实现IRClass的相关接口，
 * 然后作为RClass来使用。
 */
public class RCGNode {
	RClassStruct rClassStruct;
	ExtArc extOut;
	ImpArc impOut;
	RCGArc in;
	public RCGNode(RClassStruct rClassStruct){
		this.rClassStruct = rClassStruct;
	}

	/**
	 * 返回内部存储的RClassStruct结构。
	 */
	public RClassStruct getRClassStruct() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 插入非接口父类继承弧线，
	 * 这个弧线可以是虚的，
	 * 指向运行时继承图中的某个节点。
	 * @param extOut
	 */
	public linkOut(ExtArc extOut){
		//TODO 插入非接口父类继承弧线
	}
	
	public linkOut(ImpArc impOut){
		//TODO 插入接口父类继承弧线，
	}

}
