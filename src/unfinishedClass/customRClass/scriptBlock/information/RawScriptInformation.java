package unfinishedClass.customRClass.scriptBlock.information;

public class RawScriptInformation implements Information {
	protected String scriptSourse;
	protected InformationType infoType;
	
	public RawScriptInformation(String source){
		scriptSourse = source;
		infoType = InformationType.VOID;
	}
	
	@Override
	public String toString(){
		return scriptSourse;
	}

	@Override
	public InformationType getType() {
		return infoType;
	}

	@Override
	public void setType(InformationType type) {
		infoType = type;
	}
}
