package unfinishedClass.customRClass.scriptBlock.information;

public class LineScriptInformation extends RawScriptInformation{
	protected int line;
	
	public LineScriptInformation(Information information, int line){
		super(information.toString());
		this.line = line;
	}

	public int getLine(){
		return line;
	}
	
	public void setLine(int line){
		this.line = line;
	}
	
	/*
	@Override
	public String toString(){
		return "Line" + line + ": " + scriptSourse;
	}
	*/
}
