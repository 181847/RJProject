package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;

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
public class RClassRefAnalysisSpider extends AbstractBCSpider {

	public RClassRefAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		//TODO 对注释中声明的几种情况分别进行分析处理。
	}

}
