package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 本类属于抽象类，
 * 添加了一些关于检查Function内部信息的方法，
 * 这个类的扩展类用来检查ConFun、StaticFun、Fun、AbstractFun。
 * <p>（注意：请将本类和AbstractFunGrammarspider区分开来，
 * 本类属于抽象类，不能创建实例；
 * 而AbstractFunGrammarSpider属于本类的子类，
 * 是实体类，专门检查脚本中声明的抽象Function，可以创建实例）
 */
public abstract class FunAbstractGrammarSpider extends GrammarSpider {
	/**
	 * 组件检查记录，相应序号的变量为true就表示本Spider已经检查过这个组件。<br>
	 * 0 - Excutee；<br>
	 * 1 - Parameter；<br>
	 * 2 - Excuter；<br>
	 * 3 - Returnval；<br>
	 * 4 - LocalVar；<br>
	 * 5 - subFun；<br>
	 * 6 - Arc；<br>
	 * 7 - Comment；<br>
	 */
	protected boolean[] foundType= new boolean[8];
	
	/**
	 * 默认发生错误。
	 * @param targetBlock
	 * 		目标Block。
	 * @param spiderDescription
	 * 		描述当前Spider，这个描述将会被添加进ErrorReason当中，
	 * 		使得输出错误原因的时候能够知道错误是在什么Spider的检查下发生的。
	 */
	public FunAbstractGrammarSpider(ScriptBlock targetBlock,
			String spiderDescription) {
		super(targetBlock, spiderDescription);
		//初始化查找记录
		for (int i = 0; i < 8; ++i){
			foundType[i] = false;
		}
	}

	protected abstract void dealWithTargetBlock();
	
	protected void dealWith_LOCALVAR(){
		foundOneTaggle();
		if (foundType[4]){
			//找到过LOCALVAR类型的信息，
			//Function当中不允许重复的LOCALVAR类型声明。
			appendReason("Function的定义中发现多个LocalVar信息块", false);
			error = true;
			return;
		}
		
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			appendReason("Function声明中的LocalVar信息块之下"
					+ "没有具体的变量声明", false);
			error = true;
			return;
		}
		
		GrammarSpider subSpider = 
				new VarFieldGrammarSpider(subBlock, "Function本地变量检查");
		subSpider.workUntilEnd();
		
		if (subSpider.occurredError()){
			//子声明中发现错误
			appendReason(subSpider.getErrorReason());
			error = true;
			return;
		}
	}

	/**
	 * 检查注释信息块。
	 */
	protected void dealWith_COMMENT() {
		foundOneTaggle();
		if (foundType[7]){
			//找到过COMMENT类型的信息，
			//Function当中不允许重复的COMMENT类型声明。
			appendReason("Function的定义中发现多个Comment信息块", false);
			error = true;
			return;
		}
		
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			appendReason("Function声明中的Comment信息块之下"
					+ "没有具体的注释方形区域声明", false);
			error = true;
			return;
		}
		
		GrammarSpider subSpider = 
				new CommentRectGrammarSpider(subBlock);
		subSpider.workUntilEnd();
		
		if (subSpider.occurredError()){
			//子声明中发现错误
			appendReason(subSpider.getErrorReason());
			error = true;
			return;
		}
	}

	/**
	 * 检查单一类型，
	 * 规定targetBlock所在的Block链称为父链，
	 * targetBlock.subBlock所在的Block链称为子链，
	 * 父链中不得出现重复的合法Block，
	 * 比如不能有两个Parameter声明，
	 * 是否重复由一个布尔型数组记录
	 * 用typeIndex来告诉这个方法，
	 * 这个子链检查的目标所对应的记录；
	 * 子链中有且只有 一 种 类型的Block。
	 * @param spiderIndex
	 * 		对应的记录序号，
	 * 		防止重复类型的Block出现在父链上，
	 * 		记录序号比照表：<br>
	 * 		0 - Excutee；<br>
	 * 		1 - Parameter；<br>
	 * 		2 - Excuter；<br>
	 * 		3 - Returnval；<br>
	 * 		4 - LocalVar；<br>
	 * 		5 - subFun；<br>
	 * 		6 - Arc；<br>
	 * 		7 - Comment；<br>
	 * @param type
	 * 		希望子链上所拥有的InformationType。
	 * @param typeDescription
	 * 		描述在子链上检查的InformationType，
	 * 		之所以加上这个描述是为了方便在子链上发生错误的时候能够
	 * 		准确告知用户是在什么的检查过程中发生的错误。
	 */
	protected void dealWith_SingleType(int typeIndex,
			InformationType type, 
			String typeDescription){
		foundOneTaggle();
		if (foundType[typeIndex]){
			//找到过指定的类型信息，
			//Function当中不允许重复的信息类型声明。
			appendReason("Function的定义中发现多个" + typeDescription, false);
			error = true;
			return;
		}
		
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			appendReason("Function声明中" 
					+ typeDescription + "没有具体的声明。", false);
			error = true;
			return;
		}
		
		GrammarSpider subSpider = 
				new SingleTypeGrammarSpider(
						subBlock, 
						type, 
						typeDescription);
		subSpider.workUntilEnd();
		
		if (subSpider.occurredError()){
			//子声明中发现错误
			appendReason(subSpider.getErrorReason());
			error = true;
			return;
		}
	}
}
