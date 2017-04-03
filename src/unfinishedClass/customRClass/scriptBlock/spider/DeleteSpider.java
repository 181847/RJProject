package unfinishedClass.customRClass.scriptBlock.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

public abstract class DeleteSpider extends ReasonedErrorSpider {
	protected boolean deleteTarget;

	public DeleteSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		error = false;
	}

	/**
	 * 如果想要删除targetBlock，
	 * 只需要设置deleteTarget为true就可以了。
	 */
	@Override
	protected abstract void dealWithTargetBlock();
	
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
