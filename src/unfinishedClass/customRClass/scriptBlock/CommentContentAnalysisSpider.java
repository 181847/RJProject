package unfinishedClass.customRClass.scriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;

/**
 * 为Comment注释的方形区域的内部的所有Information赋予
 * InformationType.CONTENT，在这里不需要进行检查，
 * 默认每一行都是一个注释。
 */
public class CommentContentAnalysisSpider extends AbstractBCSpider {

	public CommentContentAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		targetBlock.getInformation().setType(InformationType.CONTENT);
	}

}
