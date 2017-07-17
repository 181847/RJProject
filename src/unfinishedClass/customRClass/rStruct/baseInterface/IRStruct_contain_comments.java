package unfinishedClass.customRClass.rStruct.baseInterface;

import unfinishedClass.customRClass.rStruct.CommentStruct;
import unfinishedClass.customRClass.rStruct.RSet;

public interface IRStruct_contain_comments {
	/**
	 * 定义注释。
	 * @param commentSet
	 * 		注释结构集合。
	 */
	public void defineComments_by_RSet(RSet<CommentStruct> commentSet);
	
	/**
	 * 增加定义注释。
	 * @param sfStruct
	 * 		注释定义结构。
	 */
	public void defineComment(CommentStruct cStruct);
}
