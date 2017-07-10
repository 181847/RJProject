package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.RClassStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.spider.CountableSpider;

/**
 * 将一系列脚本Block的信息提取出来生成RClassStruct。
 */
public class SequenceStructSpider extends CountableSpider {

	public SequenceStructSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	public void countWork() {
		//放置用于都去RClassStruct的结构
		RClassStructSpider rss = new RClassStructSpider(subBlock);
		
		//读取脚本信息。
		rss.workUntilEnd();
		
		//获取脚本结果。
		RClassStruct rs = rss.getRStruct();
		//添加原始的脚本来源路径。
		rs.setSourcePath(targetInfoString);
		
		//将读取到的RClass结构放进targetBlock中。
		targetBlock.setInformation(
				new StructInfo(
						rss.getRStruct()));
	}

}
