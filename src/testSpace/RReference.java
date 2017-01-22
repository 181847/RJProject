package testSpace;
import basicInterface.INameable;
import rClassInterface.IRReference;

public class RReference implements IRReference {
	//是否是Java包装类型的引用
	public boolean isAtom;
	//是否是基本数据类型
	public boolean isBasicType;
	//数据域
	public Object[] datas;
	//成员数量
	public int memberNum;
	//引用所属的RClass名称
	public String referenceClass;
	//数据所属的RClass名称
	public String dataClass;
	

	@Override
	public boolean set(IRReference source) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void duplicateBasicData(IRReference source) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getObjects() {
		// TODO Auto-generated method stub
		return null;
	}
}
