package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * VarGSpider为DeclarGSpider，
 * 因为其中每一个变量都要有子信息来声明变量的类型，
 * 而对于静态部分，
 * 一旦声明Static:
 * 就一定再静态代码部分声明变量。
 * @author 75309
 *
 */
public class VarGSpider extends DeclarGSpider {
	/**
	 * 默认发生错误。
	 * @param targetBlock
	 * 		目标Block。
	 * @param spiderDescription
	 * 		对本Spider的具体描述，
	 * 		这个描述将会被添加进错误信息的头部，
	 * 		用来帮助用户确定错误是发生在哪个检查过程中的。
	 */
	public VarGSpider(ScriptBlock targetBlock) {
		//两个记录槽。
		super(targetBlock, "连续变量声明", 2);
	}
	
	@Override
	protected void declarGrammarWork() {
		switch(infoType)
		{
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
	 * 增加警告缺少变量声明。
	 */
	@Override
	public boolean occurredError(){
		return super.occurredError()
				//缺少具体的类型声明或者过多的类型声明。
				|| 1 != getRecordOf(InformationType.RCLASS);
	}
	
	/**
	 * 增加警告缺少变量声明。
	 */
	@Override
	public String getRawReport(){
		String appendReport = "";
		
		if ( 0 == getRecordOf(InformationType.VAR)){
			appendReport = "\n缺少具体的变量声明。";
		}
		
		return super.occurredError()
				+ appendReport.toString();
	}
	
	/**
	 * 只分析VAR，
	 * 其他的映射到0。
	 */
	protected int map_infoType_to_logIndex(InformationType infoType){
		switch(infoType){
		case VAR:
			return 1;
			
		default:
			return 0;
		}
	}
}
