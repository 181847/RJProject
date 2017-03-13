package unfinishedClass.customRClass;

import function.tool.FunctionFactory;
import functionInterface.IFunctionMaker;
import rClass.AbstractRClassWithFunctionFactory;
import rClass.RClassLoaderManager;
import rClassInterface.IRReference;
import unfinishedClass.FunctionInfo;
import unfinishedClass.RReferenceInfo;

public class CustomRClass extends AbstractRClassWithFunctionFactory{
	/**
	 * 存有这个RClass新增的静态成员的信息。
	 */
	protected RReferenceInfo[] staticMembers;
	
	/**
	 * 存有这个RClass的新增的普通成员的信息。
	 */
	protected RReferenceInfo[] normalMembers;

	/**
	 * 存有这个RClass的静态成员的RReference。
	 */
	protected IRReference members;
	
	/**
	 * 成员Function偏移指针索引，
	 * 这个RClass所有的父类、接口，
	 * 都会在这个偏移索引中拥有一个整形数组记录，
	 * 这个记录指向FunctionFactory中的一个位置，
	 * 从这个位置开始都是这个父类、接口的成员方法。
	 */
	protected OffsetIndex functionOffsetIndex;
	
	/**
	 * 这个FunctionFactory用来存储functionMaker，
	 * 这个FunctionFactory需要特殊设计，
	 * 为了对应覆盖Function的功能，
	 * 允许同名FunctionMaker的存在。
	 */
	protected CustomFunctionFactory customFunctionFactory;
	
	/**
	 * @return
	 * 		获取包含成员的RReference对象。
	 */
	public IRReference getMembers() {
		return members;
	}

	public CustomRClass(String rClassName, int factorySpace) {
		super(rClassName, factorySpace);
	}

	/**
	 * 此方法为空，无任何作用。
	 * 只有用Java定义的RClass需要通过这个方法来加载所有的FunctionMaker，
	 * CustomRClass不需要这个方法来加载FunctionMaker，
	 * 它加载的FunctionMaker是特殊的，
	 * 需要借助脚本文件来创建对应的FunctionMaker。
	 */
	@Override
	public int loadFunction() {
		//Empty Body
		return 0;
	}

	public CustomFunctionFactory getCustomFunctionFactory() {
		return customFunctionFactory;
	}

	@Override
	public IRReference getNewInstance() {
		// TODO Auto-generated method stub
		return null;
	}
}
