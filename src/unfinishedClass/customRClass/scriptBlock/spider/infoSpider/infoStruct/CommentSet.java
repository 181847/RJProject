package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct;

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
	public void addComment(CommentStruct commentStruct) {
		append(commentStruct);
	}

}
