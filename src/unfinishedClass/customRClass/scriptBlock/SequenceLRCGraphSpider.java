package unfinishedClass.customRClass.scriptBlock;

import unfinishedClass.customRClass.rCGraph.LoadRCGraph;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.RClassStructInformation;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;

/**
 * 提取各个Block内部保存的RClassStruct，
 * 来生成一个加载时继承图。
 */
public class SequenceLRCGraphSpider extends AbstractBCSpider {
	protected LoadRCGraph loadRCG;

	public SequenceLRCGraphSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		loadRCG = new LoadRCGraph();
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		//检查是否能包含RClassStruct信息
		if (information instanceof RClassStructInformation){
			loadRCG.insert(
					((RClassStructInformation) information)
					.getRClassStruct());
		}
	}

	/**
	 * 获取加载时运行图。
	 */
	public LoadRCGraph getLoadRCG() {
		return loadRCG;
	}

}
