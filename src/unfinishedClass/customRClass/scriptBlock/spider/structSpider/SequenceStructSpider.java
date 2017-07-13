package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.RClassStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.RStructInfo;
import unfinishedClass.customRClass.scriptBlock.spider.CountableSpider;

/**
 * 将一系列脚本Block的信息提取出来生成RClassStruct。
 */
public class SequenceStructSpider extends UtilsRStructSpider {

	public SequenceStructSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	public void countWork() {
		//获取脚本结果。
		RClassStruct rs = getRStruct_fromSub_use(new RClassStructSpider());
		//记录原始的脚本来源路径。
		rs.logSourcePath(targetInfoString);
		
		//将读取到的RClass结构放进targetBlock中。
		targetBlock.setInformation(
				new RStructInfo<RClassStruct>(rs));
	}

}
