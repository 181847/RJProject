package unfinishedClass.customRClass.scriptBlock.information;

public class RawScriptInformation implements Information {
	protected String scriptSourse;
	protected InformationType infoType;
	protected String description;
	
	public RawScriptInformation(String source){
		scriptSourse = source;
		infoType = InformationType.VOID;
		description = "";
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

	@Override
	public String getOriginalString() {
		return scriptSourse;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void appendDescription(String anotherDescription) {
		description += "; " + anotherDescription;
	}
}
