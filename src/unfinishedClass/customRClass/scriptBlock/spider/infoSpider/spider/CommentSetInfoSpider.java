package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.set.CommentSet;
import unfinishedClass.customRClass.struct.CommentStruct;

/**
 * 收集Block链上面的注释信息，
 * 并整合成一个CommentSet对象。
 */
public class CommentSetInfoSpider extends AbstractBCSpider {
	protected CommentSet commentSet;

	public CommentSetInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		commentSet = new CommentSet();
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
		
		//提取子链中的详细注释信息到commentStruct对象中
		pickUpCommentLines(commentStruct);
		commentSet.addComment(commentStruct);
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

	public CommentSet getCommentSet() {
		return commentSet;
	}
	
}
