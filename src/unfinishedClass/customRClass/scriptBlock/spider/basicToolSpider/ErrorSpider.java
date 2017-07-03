package unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.spider.CountableSpider;

/**
 * 可以存储错误信息的Spider。
 */
public abstract class ErrorSpider extends CountableSpider {
	/**
	 * 记录错误信息。
	 */
	protected StringBuffer errorReason;

	/**
	 * 默认Spider没有发生错误。
	 * @param targetBlock
	 * 		目标Block节点。
	 */
	public ErrorSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		errorReason = new StringBuffer();
	}
	
	/**
	 * 报告错误信息，
	 * 形如：（注意：下面的引号不出在返回值中）<br>
	 * “<br>
	 * {<br>
	 * 第一个描述；<br>
	 * 第二个描述；<br>
	 * }<br>
	 * ”<br>
	 * @return
	 */
	public String report (){
		return "{\n" 
				+ errorReason.toString()
				+ "}";
	}
	
	/**
	 * 添加对错误的描述。
	 * @param addition
	 * 		单个错误描述，
	 * 		添加时会在末尾加上一个换行符。
	 */
	public void describeError (String addition){
		errorReason.append(addition + '\n');
	}
	
	/**
	 * 通过这个方法来查看是否发现了错误，
	 * 这个方法的具体实现由子类来实现。
	 * @return
	 */
	public abstract boolean occurredError();
}
