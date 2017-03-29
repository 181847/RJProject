package unfinishedClass.customRClass.scriptBlock;

public class ScriptInformation implements Information {
	protected String scriptSourse;
	public ScriptInformation(String source){
		scriptSourse = source;
	}
	
	@Override
	public String toString(){
		return scriptSourse;
	}
}
