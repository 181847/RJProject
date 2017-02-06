package functionInterface;
import rClassInterface.*;

public interface IReturnval extends IRReference,IExcuter{
	/**
	 * Returnval与发动主要function的运算功能相关
	 * 只有基本运算节点的返回值才会存储Excutee
	 * 其他类型的Function的返回值不会存储Excutee
	 * 所以会有两种Returnval分别对应上面那两种Function
	 * 一种包含一个IExcutee引用，另一个没有这个引用
	 * 现阶段不对这两种返回值做区分，统一使用带引用的返回值
	 * @return 当前Returnval所具有的Excutee
	 */
}
