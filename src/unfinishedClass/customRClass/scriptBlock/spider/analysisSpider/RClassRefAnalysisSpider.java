package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.CountableSpider;

/**
 * 分析一个信息块中关于RClass类型的引用的定义，
 * 可以是如下几种类型的RClass类型引用：<br>
 * T	未知的泛参，定义在RClass和Function中的泛参；<br>
 * String	具体的单一类型引用，直接引用一个午餐的RClass类型；<br>
 * Generics<String> 一个泛型类型，泛参被具体设置；<br>
 * Generics<T>		一个泛型类型，
 * 泛型所需的泛参没有被具体指定，
 * 而是指向RClass或Function中指定的泛参。<br>
 */
public class RClassRefAnalysisSpider extends CountableSpider {
	/**
	 * 最大可接受的检查数量，
	 * 超过此数量的Block会直接将InformationType设为VOID。
	 */
	private int maxAvaliableNum;

	public RClassRefAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		maxAvaliableNum = Integer.MAX_VALUE;
	}
	
	/**
	 * 通过一个limit参数对可接受检查的Block数量进行限制，
	 * 0表示不接受任何Block的检查请求，
	 * 将所有Block的InformationType设为VOID；
	 * 如果设为1就表示只对开头的一个Block进行检查，
	 * 以此类推。
	 * @param targetBlock
	 * 		初始block。
	 * @param limit
	 * 		可接受检查的Block数量，
	 * 		如果不需要对数量进行限制的话，
	 * 		请使用RClassRefAnalysisSpider(ScriptBlock targetBlock)。
	 */
	public RClassRefAnalysisSpider(ScriptBlock targetBlock, int limit) {
		super(targetBlock);
		maxAvaliableNum = limit;
	}

	@Override
	public void countWork() {
		//提醒：count每次在countWork执行之前进行加一操作。
		if (count > maxAvaliableNum){
			setInfo_VOID();
			descriptInfo("超出检查范围，最大可检查数量为：" + maxAvaliableNum);
		}
		//查看是否符合RClass命名规范。
		if (RStringChecker.checkRClassName(targetInfoString)){
			setInfo(InformationType.CLASS_REF_CL);
			
			if (hasSubBlock){
				//检查泛参指配
				new GenericsAssignAnalysisSpider(subBlock)
					.workUntilEnd();
			}
			
		//查看是否符合组件命名规范，
		//如果符合的话，表示这个类型引用的是一个泛参。
		} else if (RStringChecker.checkComponentName(targetInfoString)){
			//一般而言泛参底下不会有泛参指定，
			//所以如果有subBlock的话，
			//这就是一个错误的类型声明。
			if (hasSubBlock){
				setInfo_VOID();
				descriptInfo("此处声明的类型引用了一个泛参，不应该含有其他子信息。");
			} else {
				setInfo(InformationType.CLASS_REF_GP);
			}
			
		} else {
			setInfo_VOID();
			descriptInfo("类型声明字段既不满足RClass命名规范，"
					+ "也不满足组件命名规范（泛参）。");
		}
	}

}
