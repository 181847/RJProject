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
	RCGArc inArc;
	public RCGNode(RClassStruct rClassStruct){
		this.rClassStruct = rClassStruct;
	}

	/**
	 * 返回内部存储的RClassStruct结构。
	 */
	public RClassStruct getRClassStruct() {
		return rClassStruct;
	}
	
	/**
	 * 在结点的出度插入非接口父类继承弧线，
	 * 本方法不对弧线的入度链表进行修改。
	 * @param extOut
	 * 		非接口父类继承弧线。
	 */
	public void linkOut(ExtArc extOut){
		this.extOut = extOut;
	}
	
	/**
	 * 在结点的出度插入接口继承弧线，
	 * 本方法不对弧线的入度链表进行修改，
	 * 只对弧线的出度链表进行连接。
	 * @param impOut
	 * 		接口继承弧线。
	 */
	public void linkOut(ImpArc impOut){
		if (this.impOut == null){
			this.impOut = impOut;
		} else {
			//将参数弧线的入度作为入度链表的头结点。
			impOut.setNextOut(this.impOut);
			this.impOut = impOut;
		}
	}

	/**
	 * 在结点的入度插入继承弧线（接口或者非接口都可以）
	 * @param newArc
	 */
	public void linkIn(RCGArc inArc) {
		if (this.inArc == null){
			this.inArc = inArc;
		} else {
			//让弧线的入度形成链表，
			//参数中的弧线称为链表的头部。
			inArc.setNextIn(this.inArc);
			this.inArc = inArc;
		}
	}

}
