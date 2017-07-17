package unfinishedClass.customRClass.rStruct;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;
import unfinishedClass.customRClass.rStruct.baseRStruct.RStruct_contain_name_genParams_vars_type;
import unfinishedClass.customRClass.rStruct.detailInterface.IRClassStruct;

/**
 * 包含一个RClass定义的结构。
 */
public class RClassStruct
extends RStruct_contain_name_genParams_vars_type
implements IRClassStruct, IRStruct{
	
	/**
	 * 原始来源路径，包括工程路径及内部脚本路径。
	 */
	protected String sourcePath;
	
	/**
	 * 非接口父类继承。
	 */
	protected RClassRefStruct extendsRef;
	
	/**
	 * 接口实现集合。
	 */
	protected RSet<RClassRefStruct> implementsRefSet;
	
	/**
	 * 构造Function。
	 */
	protected FunStruct conFun;
	
	/**
	 * 静态Function集合。
	 */
	protected RSet<FunStruct> staticFunSet;

	/**
	 * 普通Function集合。
	 */
	protected RSet<FunStruct> funSet;

	/**
	 * 抽象Function集合。
	 */
	protected RSet<FunStruct> abstractFunSet;
	
	public RClassStruct() {
		implementsRefSet = new RSet<RClassRefStruct>();
		staticFunSet = new RSet<FunStruct>();
		funSet = new RSet<FunStruct>();
		abstractFunSet = new RSet<FunStruct>();
	}

	@Override
	public void defineName(String name) {
		//要求名字符合类名命名规范。
		this.defineNameAsRClassName(name);
	}

	@Override
	public void defineExtends(RClassRefStruct classRefStruct) {
		if (classRefStruct == null) {
			throw new IllegalArgumentException("RClass定义非接口父类时传入了参数null。");
		}
		
		//定义非接口父类。
		extendsRef = classRefStruct;
	}

	@Override
	public void defineImplements_by_RSet(RSet<RClassRefStruct> interfaceSet) {
		if (interfaceSet == null) {
			throw new IllegalArgumentException(
					"通过集合定义RClass的实现接口时，传入了参数null。");
		}
		
		for (RClassRefStruct singleInterface : interfaceSet) {
			//单独定义实现接口。
			defineImplement(singleInterface);
		}
	}

	@Override
	public void defineImplement(RClassRefStruct classRefStruct) {
		if (classRefStruct == null) {
			throw new IllegalArgumentException(
					"单独向RClassStruct中添加接口实现时，传入了参数null。");
		}
		
		//TODO 在这里添加对接口的冲突名检测，
		//发现同名冲突的话就要抛出异常NameConflictionException。
		
		implementsRefSet.add(classRefStruct);
	}

	@Override
	public void defineConFun(FunStruct funStruct) {
		if (funStruct == null) {
			throw new IllegalArgumentException(
					"不能使用null来定义RClassStruct的构造Function。");
		}
		
		conFun = funStruct;
	}

	@Override
	public void defineStaticFun(FunStruct funStruct) {
		if (funStruct == null) {
			throw new IllegalArgumentException(
					"不能使用null来定义RClassStruct的静态Function。");
		}
		
		//TODO 在这里添加对静态Function的冲突名检测，
		//发现同名冲突的话就要抛出异常NameConflictionException。
		
		staticFunSet.add(funStruct);
	}

	@Override
	public void defineFun(FunStruct funStruct) {
		if (funStruct == null) {
			throw new IllegalArgumentException(
					"不能使用null来定义RClassStruct的普通Function。");
		}
		
		//TODO 在这里添加对普通Function的冲突名检测，
		//发现同名冲突的话就要抛出异常NameConflictionException。
		
		funSet.add(funStruct);
	}

	@Override
	public void defineAbstractFun(FunStruct funStruct) {
		if (funStruct == null) {
			throw new IllegalArgumentException(
					"不能使用null来定义RClassStruct的抽象Function。");
		}
		
		//TODO 在这里添加对抽象Function的冲突名检测，
		//发现同名冲突的话就要抛出异常NameConflictionException。
		
		abstractFunSet.add(funStruct);
	}

	@Override
	public void logSourcePath(String path) {
		if (path == null || path.isEmpty()) {
			throw new IllegalArgumentException("不能用null或者空串来设定RClassStruct的来源路径。");
		}
		
		sourcePath = path;
	}

	@Override
	public FunStruct getConstructFunction() {
		return conFun;
	}

	@Override
	public RSet<FunStruct> getStaticFunSet() {
		return staticFunSet;
	}

	@Override
	public RSet<FunStruct> getFunSet() {
		return funSet;
	}

	@Override
	public RSet<FunStruct> getAbstractFunSet() {
		return abstractFunSet;
	}
}
