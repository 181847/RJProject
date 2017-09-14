package unfinishedClass.customRClass.scriptBlock.information;

public interface Information {
	public String getOriginalString();
	
	public InformationType getType();
	public void setType(InformationType type);
	
	public String getDescription();
	public void appendDescription(String anotherDescription);
}
