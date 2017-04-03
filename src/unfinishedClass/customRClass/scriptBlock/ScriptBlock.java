package unfinishedClass.customRClass.scriptBlock;

import unfinishedClass.customRClass.scriptBlock.information.Information;

public class ScriptBlock {
	protected boolean isHead;
	protected ScriptBlock prec;
	protected ScriptBlock next;
	protected ScriptBlock sub;
	protected Information information;

	/**
	 * 生成一个非头结点的ScriptBlock。
	 * @param information
	 * 		ScriptBlock包含的信息。
	 */
	public ScriptBlock(Information information){
		
	}
	
	/**
	 * @param information
	 * 		ScriptBlock包含的信息。
	 * @param isHead
	 * 		生成的ScriptBlock是否为头结点。
	 */
	public ScriptBlock(Information information, boolean isHead){
		this.isHead = isHead;
		this.information = information;
	}
	
	/**
	 * 本节点是否为头部节点。
	 */
	public boolean isHead() {
		return isHead;
	}
	
	/**
	 * 本节点放置到目标节点的后面，
	 * 本节点跟随目标节点。
	 * @param prec
	 * 		前一个Block
	 */
	public void follow(ScriptBlock prec){
		this.next = prec.next;
		if (prec.next != null){
			prec.next.prec = this;
		}
		this.prec = prec;
		prec.next = this;
	}

	/**
	 * 本节点插入目标节点的前面，
	 * 本节点拉着目标节点。
	 * @param next
	 * 		下一个Block。
	 */
	public void pull(ScriptBlock next){
		this.prec = next.prec;
		if (next.prec != null){
			next.prec.next = this;
		}
		this.next = next;
		next.prec = this;
	}

	/**
	 * 包含子Block，
	 * 如果sub是头部节点，
	 * 那么sub.sub就设置成本节点。
	 * @param sub
	 * 		被包含的子Block。
	 */
	public void include(ScriptBlock sub) {
		this.sub = sub;
		if (sub.isHead()){
			sub.sub = this;
		}
	}
	
	/**
	 * 将本节点从前后的连接中删除。
	 */
	public void detach(){
		if (prec != null){
			prec.next = this.next;
		}
		if (next != null){
			next.prec = this.prec;
		}
		prec = null;
		next = null;
	}

	public ScriptBlock getPrec() {
		return prec;
	}

	public void setPrec(ScriptBlock prec) {
		this.prec = prec;
	}

	public ScriptBlock getNext() {
		return next;
	}

	public void setNext(ScriptBlock next) {
		this.next = next;
	}

	public ScriptBlock getSub() {
		return sub;
	}
	
	public Information getInformation(){
		return information;
	}
	
	public void setInformation(Information information) {
		this.information = information;
	}
}
