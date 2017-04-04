package unfinishedClass.customRClass.scriptBlock.information;

public interface Information {
	public String toString();
	public InformationType getType();
	public void setType(InformationType type);
	public String getOriginalString();
	public String getDescription();
	public void appendDescription(String anotherDescription);
}
