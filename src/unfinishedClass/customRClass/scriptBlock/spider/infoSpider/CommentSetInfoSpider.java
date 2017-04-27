package unfinishedClass.customRClass.scriptBlock.spider.infoSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.CommentSetStruct;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.CommentStruct;

/**
 * 收集Block链上面的注释信息，
 * 并整合成一个CommentSetStruct对象。
 */
public class CommentSetInfoSpider extends AbstractBCSpider {
	protected CommentSetStruct commentSetStruct;

	public CommentSetInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		commentSetStruct = new CommentSetStruct();
	}

	@Override
	protected void dealWithTargetBlock() {
		switch(targetBlock.getInformation().getType()){
		case RECT:
			dealWith_RECT();
			break;
		default:
			break;
		}
	}

	/**
	 * 首先提取目标Block中包含的一个方形区域的信息，
	 * 然后提取目标Block的子链当中的所有信息。
	 */
	protected void dealWith_RECT() {
		CommentStruct commentStruct =
				new CommentStruct();
		
		//提取子链中的详细注释信息
		pickUpCommentLines(commentStruct);
		commentSetStruct.addCommentStruct(commentStruct);
	}
	
	/**
	 * 提取子链中的每一个Block中的信息到参数的commentStruct对象当中，
	 * 默认子链是一个带头结点的双重循环链表。
	 */
	protected void pickUpCommentLines(CommentStruct commentStruct){
		ScriptBlock subBlock = targetBlock.getSub();
		
		//子链为空，直接返回
		if (subBlock == null){
			return;
		}
		
		subBlock = subBlock.getNext();
		//执行循环到头结点
		while( ! subBlock.isHead() ){
			commentStruct.addCommentLine(
					subBlock
					.getInformation()
					.getOriginalString());
		}
	}

	public CommentSetStruct getCommentSetStruct() {
		return commentSetStruct;
	}
	
}
