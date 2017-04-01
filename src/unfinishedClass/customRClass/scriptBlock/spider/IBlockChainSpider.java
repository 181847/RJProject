package unfinishedClass.customRClass.scriptBlock.spider;

/**
 * 用于遍历BlockChain的工具类型，
 * 请务必保证Spider遍历的是一个带头结点的循环链表。
 */
public interface IBlockChainSpider {
	/**
	 * Spider向后移动一个Block，
	 * 如果移动之前发现hitEnd()为true，
	 * 则不进行移动。
	 */
	public void walk();
	
	/**
	 * Spider向前移动一个Block，
	 * 如果发现isAtHead()为true，
	 * 则不进行移动。
	 */
	public void reverse();
	
	/**
	 * Spider回到初始的Block。
	 */
	public void reset();
	
	/**
	 * Spider是否在头结点的Block。
	 */
	public boolean isAtHead();
	
	/**
	 * Spider是否到达了Chain的顶部，
	 * 到达顶部不是到达了Head头结点，
	 * 而是当前的Block的前一个Block为头结点时才算到达顶部。
	 * @return
	 * 		是否到达链表的顶部。
	 */
	public boolean hitTop();
	
	/**
	 * Spider是否到达了Chain的结尾，
	 * Spider到达结尾的标志是下一个将要移动到的Block为头结点。
	 * @return
	 * 		是否到达链表的结尾。
	 */
	public boolean hitButtom();
	
	/**
	 * Spider针对当前的节点开始执行操作，
	 * 要求不允许对头结点执行任何操作，
	 * 并且执行一次walk()方法。
	 */
	public void work();
	
	/**
	 * Spider循环进行work()方法，
	 * 直到hitEnd()为true。
	 */
	public void workUntilEnd();
}
