package unfinishedClass.customRClass.scriptBlock;

public class SequenceSpider extends AbstractBCSpider {

	public SequenceSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		if (targetBlock.getSub() != null){
			new ScriptSpider(targetBlock.getSub())
				.workUntilEnd();
		}
	}

}
