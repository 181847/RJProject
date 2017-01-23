package functionInterface;
import rClassInterface.IRReference;

public interface IParameter extends IRReference{
	//从数据来源处获得数据，存储到一个RReference中
	public void extractParameter();
	//链接一个Returnval
	public void linkReturnval(IReturnval returnval);
	//返回链接的Returnval
	public IReturnval getReturnval();
}
