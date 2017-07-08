package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.information.LineScriptInformation;
import unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider.ErrorSpider;

/**
 * 拥有一个可以返回错误信息的方法，
 * 这个Spider将所有检查结果进行存储，
 * 通过相关的方法返回检查结果和检查信息。
 */
public abstract class GrammarSpider extends ErrorSpider {
	/**
	 * 用于统计相应Block的记录数组，
	 * 这个类型声明为一个数组，
	 * 可以使得子类能够扩展这个数组，
	 * 来增加子类需要的Block记录，
	 * 在GrammarSpider的定义中，
	 * blockLog长度至少为1，
	 * 其中blockLog[0]表示VOID类型和意外类型Block的数量，
	 * 在这里要记住，blockLog[0]还会记录subBlock中是否发生过错误。
	 */
	protected int blockLog[];
	
	/**
	 * 对当前GrammarSpider进行描述，
	 * 可用于输出错误信息。
	 */
	protected String spiderDesc;
	
	/**
	 * 
	 * @param targetBlock
	 * 		目标Block节点。
	 * @param initError
	 * 		手动初始化Spider是否发生错误。
	 * @param spiderDescription
	 * 		对本GrammarSpider的基本描述，
	 * 		这个描述将会被添加到错误信息的文本当中，
	 * 		方便用户在发生错误的时候从信息中得知错误发生在哪一个语法检查阶段。
	 * @param logLength
	 * 		用于记录对应Block数量的记录数组长度，最少为1。
	 */
	public GrammarSpider(ScriptBlock targetBlock, String spiderDesc, int logLength){
		super(targetBlock);
		this.spiderDesc = spiderDesc;
		//初始化Block记录数组。
		
		blockLog = new int[logLength];
	}
	
	/**
	 * 已知targetBlock的InformationType是VOID类型，
	 * 毫无疑问，脚本信息中不允许出现任何无用信息，
	 * 当前脚本发生语法错误，不允许加载，
	 * 设置error成员为true。
	 */
	protected void dealWith_VOID() {
		describeError("发现未知信息。对此未知信息的描述是：" 
				+ targetInformation.getDescription());
	}
	
	/**
	 * 统一定义一个针对意外信息的处理方法，
	 * 这个方法由子类调用。
	 */
	protected void dealWith_Unexpected(){
		describeError("发现不属于本定义阶段的信息，对此信息的描述是：" 
				+ targetInformation.getDescription());
	}
	
	protected void dealWith_Lack_SubBlock(){
		describeError("缺少详细的内容声明。");
	}
	
	/**
	 * 添加错误信息的时候在错误信息的前面
	 * 加上targetBlock的information的行数信息，
	 * 本方法对Block中存储的Information对象的实际类型有要求，
	 * 必须是包含行数的LineScriptInformation。<br>
	 * 被添加的描述形式类似：<br>
	 * “<br>
	 * 错误行数：15<br>
	 * 描述：未知类型信息。<br>
	 * ”<br>
	 * @param anotherReason
	 * 		对错误的描述。
	 */
	public void describeError(String error){
		super.describeError("错误行数：" 
				+ ((LineScriptInformation) targetInformation)
					.getLine()
				+ "描述：" + error);
	}
	
	/**
	 * @see unfinishedClass.customRClass.scriptBlock.spider.CountableSpider#countWork()
	 * 在对单个Block进行检查之前，
	 * 如果Block是VOID的话，
	 * 就直接调用dealWith-VOID()这个方法记录空白信息，
	 * 然后跳过正常的检查过程。
	 */
	@Override
	public void countWork() {
		//对InfoType进行记录，
		//注意这个方法中涉及的blockLog长度和对应的序号都是由子类来决定的
		//记得在子类中建立对应的InfoType到序号的映射。
		record(infoType);
		if (infoType == InformationType.VOID) {
			//如果发现VOID，就直接记录路错误信息。
			dealWith_VOID();
			//然后返回。
			return;
		} else if (0 == map_infoType_to_logIndex(infoType)){
			//该类型不是当前可以被分析的类型。
			//增加意外信息记录。
			dealWith_Unexpected();
		}else {
			//执行自定义的语法检查。
			grammarWork();
		}
	}
	
	/**
	 * 返回是否发生过错误，
	 * 简单的判断处理过一个正确的Block，
	 * 即发生错误的情况是指：<br>
	 * 处理到至少一个无意义Block，
	 * 或者没有处理过一个有意义的Block。
	 */
	@Override
	public boolean occurredError(){
		return blockLog[0] > 0 || count <= 0;
	}
	
	/**
	 * 让错误Block数量加一。
	 */
	protected void recordNonesense(){
		blockLog[0]++;
	}
	
	/**
	 * 将对应Block的记录加一。
	 * @param infoType
	 * 		想要记录的InformationType。
	 */
	protected void record(InformationType infoType){
		blockLog[map_infoType_to_logIndex(infoType)] ++;
	}

	/**
	 * 获取指定类型Block的记录数量。
	 * @param infoType
	 * @return
	 */
	protected int getRecordOf(InformationType infoType){
		return blockLog[map_infoType_to_logIndex(infoType)];
	}
	
	
	/**
	 * 通过这个方法来建立infoType和blockLog中对应记录的连接，
	 * 由于GrammarSpider中只有无意义Block的记录，
	 * 所以这里这个方法只返回0，
	 * 子类可以重载这个方法，返回其他非负整数，
	 * 但是要在blockLog的数组长度之内，
	 * 重载initBlockLog()方法可修改blockLog的长度。
	 * @param infoType
	 * 		infoType信息类型。
	 * @return
	 * 		对应记录的序号。
	 */
	protected int map_infoType_to_logIndex(InformationType infoType){
		return 0;
	}
	
	/**
	 * 接受一个GrammarSpider，
	 * 对当前Block的子链进行检查，
	 * 并且对相应的错误信息进行记录，
	 * 这个方法的主要目的是为了简化代码，
	 * 因为很多中情况都是要调用一个Spider去子链中检查，
	 * 然后记录信息，
	 * 这种工作的代码流程几乎一样，
	 * 所以这里将这种代码流程用一个方法来实现，
	 * 使用的时候只需要根据需要创建好GrammarSpider就可以由
	 * 这些代码来完成剩余的工作。
	 * @param gs
	 * 		已经构造好了的GrammarSpider。
	 */
	protected void sendSpider(GrammarSpider gs){
		gs.workUntilEnd();
		if (gs.occurredError()) {
			//类型声明中发生错误。
			//无意义Block计数加一。
			recordNonesense();
			
			//用子block的检查结果来描述当前错误。
			describeError(gs.report());
		}
	}
	
	/**
	 * 借用另一个GrammarSpider对当前Block再进行一次语法检查，
	 * 此处只对当前Block执行功能，
	 * 不会让GrammarSpider运行到其他block上，
	 * 与sendSpider()不同，
	 * sendSpider()假定传入的spider参数作用在下层子链的所有block上，
	 * 而borrowSpider()假定传入的spider只作用在本层的targetBlock这一个Block上，
	 * 在方法内部只会执行一次gs.work()，
	 * 运行一次有关语法检查的方法，
	 * 如果被放到头结点上的话，则相当于什么都没干，
	 * 因为头结点会被跳过。
	 * @param gs
	 * 		已经放置到targetBlock上的grammarSpider。
	 */
	protected void borrowSpider(GrammarSpider gs){
		//只对一个block进行语法检查，
		//如果初始位置在头结点就会被跳过。
		gs.work();
		if (gs.occurredError()) {
			//类型声明中发生错误。
			//无意义Block计数加一。
			recordNonesense();
			
			//用子block的检查结果来描述当前错误。
			describeError(gs.report());
		}
	}
	
	/**
	 * 返回原生错误记录的时候要查看是否检查过至少一个Block，
	 * 利用CountableSpider中的count来完成这个功能，
	 * CountableSpider每次在处理一个Block之前会增加一个count计数，
	 * 假如这个Block链上没有一个可处理的Block（即非头部Block），
	 * 则我们需要在报告中增加这个错误记录，
	 * 即没有脚本可供检查。
	 */
	@Override
	protected String getRawReport(){
		return super.getRawReport() 
				+ ( count == 0 ? 	//截止调用此方法之前，已经接触到的Block数量。
						//如果没有接触到一个Block，就要增加一个记录表示当前没有脚本可供检查。
						"\n没有脚本可供检查" : "");	
	}

	/**
	 * 由子类来实现这个方法，
	 * 对Block进行语法检查，
	 * 但是子类无需处理InformationType为VOID的情况，
	 * 这种VOID的情况实时不会发动此方法的，
	 * 子类在使用的时候可以直接通过infoType这个成员变量来获取
	 * 当前Block的InformationType。
	 */
	protected abstract void grammarWork();
}
