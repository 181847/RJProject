package unfinishedClass.customRClass.scriptBlock.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * Spider不处理block为null的情况，
 * 请一定保证初始时targetBlock是一个双重循环链表中的一个节点。
 */
public abstract class AbstractBCSpider implements IBlockChainSpider {
	protected ScriptBlock originalBlock;
	protected ScriptBlock targetBlock;
	protected boolean hitTop;
	protected boolean hitButtom;
	
	/**
	 * @param targetBlock
	 * 		Spider第一次所在的Block。
	 */
	public AbstractBCSpider(ScriptBlock targetBlock){
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
