package unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

public abstract class DeleteSpider extends ErrorSpider {
	protected boolean deleteTarget;

	public DeleteSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}
	
	@Override
	public void work(){
		if ( ! targetBlock.isHead()){
			dealWithTargetBlock();
			
			if (deleteTarget){
				deleteTargetBlock();
				deleteTarget = false;
			}
		}
		walk();
	}

	/**
	 * 删除当前targetBlock节点，
	 * 将Spider定向到targetBlock的前一个节点上。
	 */
	private void deleteTargetBlock() {
		ScriptBlock deleteBlock = targetBlock;
		targetBlock = targetBlock.getPrec();
		deleteBlock.detach(); 
	}

}
