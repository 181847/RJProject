package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 检查Block链上面是否正确声明了变量信息，
 * 允许Block链上面包含Static区域块，
 * 如果Static区域块出错的话，本Spider也算出错。
 */
public class VarFieldGSpider extends DeclarGSpider {

	/**
	 * 默认发生错误。
	 * @param targetBlock
	 * 		目标Block。
	 * @param spiderDescription
	 * 		对本Spider的具体描述，
	 * 		这个描述将会被添加进错误信息的头部，
	 * 		用来帮助用户确定错误是发生在哪个检查过程中的。
	 */
	public VarFieldGSpider(ScriptBlock targetBlock) {
		//三个记录槽。
		super(targetBlock, "变量区域声明", 3);
	}
	
	@Override
	protected void declarGrammarWork() {
		switch(infoType)
		{
		case DECLAR_STATIC:
			//静态变量声明部分。
			sendSpider(new VarGSpider(subBlock));
		case VAR:
			//变量，
			//由于是DeclarGSpider，
			//这里一定是有subBlock的，
			//检查具体的变量内部语法。
			sendSpider(new SingleVarGSpider(subBlock));
			break;
			
		default:
			//处理意外情况
			dealWith_Unexpected();
			break;
		}
	}
	
	/**
	 * 要求不能有多个静态变量区域声明，
	 * 至少有一个静态变量区域或者至少一个非静态变量。
	 */
	@Override
	public boolean occurredError(){
		int staticFieldNum = getRecordOf(InformationType.DECLAR_STATIC)
				, outerVarNum = getRecordOf(InformationType.VAR);
		return super.occurredError()
				//存在多个静态变量区域声明。
				|| 1 < staticFieldNum
				//没有静态变量区域，也没有非静态变量。
				|| 0 == (staticFieldNum + outerVarNum);
	}

	/**
	 * 过多的静态变量区域声明警告，
	 * 缺少至少一个静态变量区域或者至少一个非静态变量的警告。
	 */
	@Override
	public String getRawReport(){
		StringBuffer appendReport = new StringBuffer();
		int staticFieldNum = getRecordOf(InformationType.DECLAR_STATIC)
				, outerVarNum = getRecordOf(InformationType.VAR);
		
		if (1 < staticFieldNum){
			appendReport.append("过多的静态变量声明区域。");
		}
		
		if (0 == (staticFieldNum + outerVarNum)){
			appendReport.append("缺少至少一个静态变量区域或者至少一个非静态变量。");
		}
		
		return super.occurredError()
				+ appendReport.toString();
	}
	
	/**
	 * 只分析DECLAR_STATIC/VAR，
	 * 其他的映射到0。
	 */
	@Override
	protected int map_infoType_to_logIndex(InformationType infoType){
		switch(infoType){
		case DECLAR_STATIC:
			return 1;
			
		case VAR:
			return 2;
			
		default:
			return 0;
		}
	}
}
