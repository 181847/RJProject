package unfinishedClass.customRClass.rCGraph;

import unfinishedClass.customRClass.set.Set;
import unfinishedClass.customRClass.struct.RClassStruct;
import unfinishedClass.customRClass.struct.Struct;

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
	RCGArc firstIn;
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
		if (this.firstIn == null){
			this.firstIn = inArc;
		} else {
			//让弧线的入度形成链表，
			//参数中的弧线称为链表的头部。
			inArc.setNextIn(this.firstIn);
			this.firstIn = inArc;
		}
	}

	/**
	 * 获取入度为当前结点的所有的弧线链表
	 * @return
	 * 		返回的弧线对象的入度部分形成一个链表，
	 * 		链表当中的弧线具有相同的入度。
	 */
	public RCGArc getFirstIn() {
		// TODO Auto-generated method stub
		return firstIn;
	}

	/**
	 * 获取RClass的名字。
	 * @return
	 * 		RClass的名字。
	 */
	public String getName() {
		return rClassStruct.getName();
	}

	/**
	 * 获取RClass的所有父类的名称，
	 * 包括接口和非接口父类。
	 */
	public Set getFatherSet() {
		Set returnSet = new Set();
		//如果RClass有非接口父类。
		if (rClassStruct.hasExtend()){
			returnSet.append(
					rClassStruct.getExtend());
		}
		
		//如果RClass有接口父类。
		if (rClassStruct.hasInterfaces()){
			Set implementSet = 
					rClassStruct.getImplementSet();
			//记录所有接口类。
			for (int i = implementSet.getNum();
					i >= 0; --i){
				returnSet.append(
							implementSet.getStruct(i));
			}
		}
		return returnSet;
	}

	/**
	 * 检测RCGNode是否存在这样的出度弧线，
	 * 弧线指向的结点序号为0，
	 * 检查的范围包括ExtArc和ImpArc。
	 * @return
	 * 		如果有弧线指向0号结点，返回true；
	 * 		否则返回false。
	 */
	public boolean linkZeroArc() {
		//检查非接口父类继承弧线。
		//检查每个出度弧线的入度序号
		//即A <------- B 弧线中 的A端的 序号。
		if (extOut != null && extOut.getInIndex() == 0){
			return true;
		}
		
		//检查接口父类继承弧线。
		ImpArc testPointer = impOut;
		while (testPointer != null){
			//检查每个出度弧线的入度序号
			//即A <------- B 弧线中 的A端的 序号。
			if (testPointer.getInIndex() == 0){
				return true;
			}
		}
		return false;
	}

}
