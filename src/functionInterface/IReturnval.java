package functionInterface;
import rClassInterface.*;

public interface IReturnval extends IRReference{
	//表明返回值是否从属于一个基本运算节点
	public boolean isBlongToBasicCalculatorFunction();
	//得到Returnval所具有的Excutee
	//但并不是所有的Retuernval都会有存储Excutee
	//只有基本运算节点的返回值才会存储Excutee
	//其他类型的Function的返回值不会存储Excutee
	//所以会有两种Returnval分别对应上面那两种Function
	//一种包含一个IExcutee引用，另一个没有这个引用
	//现阶段不对这两种返回值做区分，统一使用带引用的返回值
	public IExcutee getExcutee();
	//向返回值中插入Excutee
	//这里为了与Excuter中的linkExcutee()区分开来
	//使用insertExcutee()作为方法名
	public void insertExcutee(IExcutee excutee);
}
