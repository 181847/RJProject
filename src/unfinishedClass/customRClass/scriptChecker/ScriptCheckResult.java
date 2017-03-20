package unfinishedClass.customRClass.scriptChecker;

public class ScriptCheckResult {
	//0代表Interface，
	//1代表AbstractClass，
	//2代表Class
	protected int rClassType;
	
	//用来存储检查结果是否正确
	protected boolean isRight;
	
	public ScriptCheckResult(){
		rClassType = 0;
		isRight = true;
	}
	
	public int getRClassType() {
		return rClassType;
	}

	public void setRClassType(int rClassType) {
		this.rClassType = rClassType;
	}

	public void setResult(boolean isRight) {
		this.isRight = isRight;
	}

	public boolean isRight(){
		return isRight;
	}
}
