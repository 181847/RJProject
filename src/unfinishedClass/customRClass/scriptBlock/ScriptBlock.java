package unfinishedClass.customRClass.scriptBlock;

public class ScriptBlock {
	protected boolean isHead;
	protected ScriptBlock prec;
	protected ScriptBlock next;
	protected ScriptBlock sub;
	protected Information information;
	
	public ScriptBlock(Information information, boolean isHead){
		this.isHead = isHead;
		this.information = information;
	}
	
	/**
	 * 插入目标节点的后面。
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
	 * 本节点是否为头部节点。
	 */
	public boolean isHead() {
		return isHead;
	}

	/**
	 * 插入目标节点的前面。
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
	
	public Information getInformation(){
		return information;
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
}
