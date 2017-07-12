package unfinishedClass.customRClass.scriptBlock.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * Spider不处理block为null的情况，
 * 请一定保证初始时targetBlock是一个双重循环链表中的一个节点。
 */
public abstract class AbstractBCSpider implements IBlockChainSpider {
	/**
	 * 可以用来恢复初始状态的记录。
	 */
	protected ScriptBlock originalBlock;
	
	/**
	 * 当前正在遍历道德targetBlock。
	 */
	protected ScriptBlock targetBlock;
	
	/**
	 * 是否到达链表头部，
	 * 如果targetBlock的前一个节点是头部节点，则当前已到达尾部。
	 */
	protected boolean hitTop;
	
	/**
	 * 是否到达链表尾部，
	 * 如果targetBlock的下一个是头部节点，则当前已到达尾部，
	 * 注意这个属性有延迟性，<br>
	 * 假设有 HEAD -> A -> B -> HEAD这样一个链表，
	 * Spider从Head开始向后走，<br>
	 * 第一次走到A，hitButtom = false，<br>
	 * 第二次走到B，hitButtom = false，<br>
	 * 第三次发现下个Block是HEAD，停止行走，hitButtom = true，<br>
	 */
	protected boolean hitButtom;
	
	/**
	 * 空的构造方法。
	 */
	public AbstractBCSpider() {
		originalBlock = targetBlock = null;
		//防止Spider未经初始化就移动。
		hitTop = hitButtom = true;
	}
	
	/**
	 * @param targetBlock
	 * 		Spider第一次所在的Block。
	 */
	public AbstractBCSpider(ScriptBlock targetBlock){
		placeSpider(targetBlock);
	}
	
	/**
	 * 用于代替带参构造方法的初始化方法。
	 * @param targetBlock
	 */
	public void placeSpider(ScriptBlock targetBlock) {
		originalBlock = 
				this.targetBlock = targetBlock;
		
		hitTop = hitButtom = false;
	}

	protected abstract void dealWithTargetBlock();
	
	@Override
	public void walk() {
		if (hitButtom){
			return;
		} else {
			ScriptBlock nextBlock = targetBlock.getNext();
			if (nextBlock.isHead()){
				hitButtom = true;
			} else {
				targetBlock = nextBlock;
			}
		}
	}
	
	@Override
	public void reverse(){
		if (hitTop){
			return;
		} else {
			ScriptBlock precBlock = targetBlock.getPrec();
			if (precBlock.isHead()){
				hitTop = true;
			} else {
				targetBlock = precBlock;
			}
		}
	}
	
	@Override
	public void reset(){
		targetBlock = originalBlock;
		hitTop = hitButtom = false;
	}

	@Override
	public boolean isAtHead() {
		return targetBlock.isHead();
	}

	@Override
	public boolean hitTop(){
		return hitTop;
	}
	
	@Override
	public boolean hitButtom() {
		return hitButtom;
	}
	
	/**
	 * 检查当前Spider是否在循环链表的最后一个节点上，
	 * 即在头结点前面的一个节点上。
	 * @return
	 * 		targetBlock所指向的next是否是头部。
	 */
	public boolean isAtLastBlock(){
		return targetBlock.getNext().isHead();
	}

	@Override
	public void work() {
		if ( ! targetBlock.isHead()){
			dealWithTargetBlock();
		}
		walk();
	}

	@Override
	public void workUntilEnd() {
		do {
			work();
		} while( ! hitButtom);
		
	}

}
