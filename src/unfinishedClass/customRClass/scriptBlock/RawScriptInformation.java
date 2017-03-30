package unfinishedClass.customRClass.scriptBlock;

public class RawScriptInformation implements Information {
	protected String scriptSourse;
	public RawScriptInformation(String source){
		scriptSourse = source;
	}
	
	@Override
	public String toString(){
		return scriptSourse;
	}
}
