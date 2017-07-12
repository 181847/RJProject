package unfinishedClass.customRClass.scriptBlock.information;

import unfinishedClass.customRClass.rStruct.RClassStruct;

/**
 * 保存RClass结构化信息的类型。
 */
public class RClassStructInformation extends RawScriptInformation {
	protected RClassStruct rClassStruct;

	public RClassStructInformation(RClassStruct rClassStruct) {
		super("RCLASS_STRUCT_INFORMATION");
		this.rClassStruct = rClassStruct;
	}

	public RClassStruct getRClassStruct(){
		return rClassStruct;
	}
}
