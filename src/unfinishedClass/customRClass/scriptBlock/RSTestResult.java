package unfinishedClass.customRClass.scriptBlock;

public class RSTestResult {
	protected boolean error;
	protected StringBuffer errorReason;
	
	public RSTestResult(){
		error = false;
		errorReason = new StringBuffer();
	}
	
	/**
	 * 从检查结果中获取是否发生了错误。
	 * @return
	 * 		检查过程是否发生错误。
	 */
	public boolean hasError() {
		return error;
	}

	/**
	 * 获取错误发生的原因。
	 * @return
	 * 		错误发生的原因。
	 */
	public String getErrorReason() {
		return errorReason.toString();
	}

	public void occurredError() {
		error = true;
	}

	public void appendReason(String string) {
		errorReason.append(string);
	}
}
