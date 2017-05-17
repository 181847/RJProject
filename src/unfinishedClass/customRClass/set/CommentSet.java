package unfinishedClass.customRClass.set;

import unfinishedClass.customRClass.struct.CommentStruct;

/**
 * Function内部的注释集合。
 */
public class CommentSet extends Set {

	/**
	 * 添加一个注释信息，
	 * 一个注释信息包括位置信息和文本信息。
	 * @param commentStruct
	 * 		一个注释信息。
	 */
	public void appendComment(CommentStruct commentStruct) {
		append(commentStruct);
	}

}
