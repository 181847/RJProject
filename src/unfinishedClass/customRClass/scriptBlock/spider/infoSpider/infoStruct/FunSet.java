package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct;

/**
 * 本类用于存储FunctionStruct，
 * 并提供获取的方法。
 */
public class FunSet {

	/**
	 * 添加FunctionStruct，
	 * 要求不能与原有的元素的名字发生冲突，否则插入失败。
	 * @param funStruct
	 * 		被添加的Function元素。
	 */
	public void addFun(FunctionStruct funStruct) {
		if (noConflicOnName(funStruct)){
			append(funStruct);
		}
	}

	/**
	 * 在队尾添加FunStruct。
	 * @param funStruct
	 * 		被添加的funstruct对象。
	 */
	protected void append(FunctionStruct funStruct) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 检查funStruct是否和内部的某个funStruct的名字产生冲突。
	 * @param funStruct
	 * 		被检查的funStruct对象。
	 * @return
	 * 		如果有冲突，返回true；
	 * 		如果不冲突，返回false。
	 */
	public boolean noConflicOnName(FunctionStruct funStruct) {
		// TODO Auto-generated method stub
		return true;
	}
	
}
