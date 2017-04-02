package unfinishedClass.customRClass.scriptBlock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import basicTool.RLogger;

public class ScriptBlockHelper {
	public static char hierarchyCharacter = '\t';
	/**
	 * 从zip文件中读取指定的脚本文件，
	 * 生成脚本结构，
	 * 每个脚本都用双重循环链表进行连接，
	 * 最终结果返回这个双重循环链表的头结点。
	 * @param projectFile
	 * 		包含脚本文件的Zip文件。
	 * @param cusRClassDeclarations
	 * 		脚本定义的RClass的名字，
	 * 		程序通过这个名字来获取脚本的位置，
	 * 		注意这里的每一项都是形如“myPacakge.RClass1”这样的RClass全名。
	 * @return
	 * 		脚本结构链表的头结点。
	 */
	public static ScriptBlock generateSequence(ZipFile projectFile, ArrayList<String> cusRClassDeclarations){
		//获取一个脚本结构的双重循环链表的头结点
		ScriptBlock sequenceHead = getNewSequenceHead();
		ScriptBlock singleScriptBlock;
		
		ArrayList<String> script;
		for (int declarLine = cusRClassDeclarations.size() - 1;
				declarLine >= 0; --declarLine){
			//获取脚本文件定义的一行一行的字符串
			script = pickScriptInZip(projectFile, cusRClassDeclarations.get(declarLine));
			
			//script的第一个元素是script所来自的工程文件路径，
			//以及脚本文件在工程文件中的位置，
			//script必须大于1保证有实际的声明语句。
			if (script != null && script.size() >= 2){
				//从脚本行来生成脚本结构
				singleScriptBlock = generateScriptBlock(script);
				
				if (singleScriptBlock != null){
					//插入循环链表的队尾，
					//即放置到头结点的前面。
					singleScriptBlock.pull(sequenceHead);
				}
			}
		}
		return sequenceHead;
	}

	/**
	 * 从指定的脚本文件中生成一个ScriptBlock对象，
	 * 用来保存脚本的结构化信息。
	 * @param script
	 * 		脚本文件中的每一行文件，
	 * 		script的size必须保证至少两行信息。
	 * @return
	 * 		一个ScriptBlock，prec/next为null。
	 */
	public static ScriptBlock generateScriptBlock(ArrayList<String> script) {
		// TODO Auto-generated method stub
		
		ScriptBlock scriptBlock = new ScriptBlock(
				new RawScriptInformation(script.get(0)), false);
		ScriptBlock contentScriptBlockHead = getNewContentHead();
		
		scriptBlock.include(contentScriptBlockHead);
		ScriptBlockChainGenerator chainGenerator = 
				new ScriptBlockChainGenerator(contentScriptBlockHead, script, 1);
		
		chainGenerator.generate();
		if (chainGenerator.failed()){
			return null;
		}
		
		return scriptBlock;
	}

	/**
	 * 从Zip文件中提取出一个脚本文件。
 	 * @param projectFile
 	 * 		包含脚本文件的Zip文件。
	 * @param string
	 * 		脚本声明的RClass的名字，
	 * 		程序将会通过这个名字，
	 * 		在Zip文件中找到这个脚本文件，
	 * 		这里的名字是形如“myPacakge.RClass1”这样的RClass全名。
	 * @return
	 * 		如果文件存在就返回包含脚本文件每一行字符串的ArrayList；
	 * 		如果脚本文件不存在，就返回null。
	 */
	private static ArrayList<String> pickScriptInZip(ZipFile projectFile, String cusRClassName) {
		// TODO Auto-generated method stub
		ArrayList<String> script;
		ZipEntry scriptEntry = null;
		BufferedReader scriptBR = null;
		String scriptLine;
		
		boolean occuredError = false;
		
		cusRClassName = "src/" + cusRClassName.replace('.', '/') + ".crc";
		scriptEntry = 
				projectFile.getEntry(cusRClassName);
		
		if (scriptEntry == null){
			RLogger.logError("ScriptBlockHelper.pickScriptInZip()方法操作失败，"
					+ "获取ZipEntry失败。");
			return null;
		}
		
		try{
			scriptBR = 	new BufferedReader(
						new InputStreamReader(
							projectFile.getInputStream(scriptEntry)));
		} catch (IOException e){
			RLogger.logError("ScriptBlockHelper.pickScriptInZip()方法操作失败，"
					+ "获取BufferedReader失败。");
			RLogger.logException(e);
			return null;
		}
		
		script = new ArrayList<String>();
		script.add("工程文件：" + projectFile.getName()
				+ "脚本位置：" + cusRClassName);
		try {
			while((scriptLine = scriptBR.readLine()) != null){
				script.add(scriptLine);
			}
		} catch (IOException e) {
			RLogger.logException(e, 
					"ScritBlockHelper.pickScriptInZip()", 
					"读取脚本信息失败",  
					"从BufferedReader中读取行时发生IO异常");
			script = null;
		}
		
		if (scriptBR != null){
			try {
				scriptBR.close();
			} catch (IOException e) {
				RLogger.logException(e, 
						"ScritBlockHelper.pickScriptInZip()", 
						"BufferedReader关闭失败",  
						"关闭BufferedReader时发生IO异常");
			}
		}
		
		return script;
	}

	/**
	 * 获取一个脚本加载序列的头结点，
	 * 头结点自己形成一个循环链表。
	 * @return
	 * 		一个脚本加载序列的头结点。
	 */
	private static ScriptBlock getNewSequenceHead() {
		ScriptBlock head = new ScriptBlock(new ScriptSequenceInformation(), true);
		head.follow(head);
		return head;
	}
	
	/**
	 * 创建一个普通的ScriptBlock的头部节点，
	 * 这个头部节点首尾相连。
	 */
	public static ScriptBlock getNewContentHead() {
		// TODO Auto-generated method stub
		ScriptBlock contentHead = new ScriptBlock(null, true);
		contentHead.follow(contentHead);
		return contentHead;
	}
	
	/**
	 * 计算scriptLine这一行的层次数。
	 * @param scriptLine
	 * 		脚本的一行信息。
	 * @return
	 * 		scriptLine的开头所包含的层次符号的数量，
	 * 		层次符号为ScriptBlockHelper.hierarchyCharacter。
	 */
	public static int calculateHierarchy(String scriptLine) {
		// TODO Auto-generated method stub
		int countHierarchy = 0;
		for (char pointer: scriptLine.toCharArray()){
			if (pointer == ScriptBlockHelper.hierarchyCharacter){
				++ countHierarchy;
			} else {
				break;
			}
		}
		return countHierarchy;
	}

	/**
	 * 去掉ScriptLine的前方的层次声明符号。
	 * @param scriptLine
	 * 		脚本文件中的原生信息。
	 * @return
	 * 		修剪层次声明符号之后的信息。
	 */
	public static String trimScriptLine(String scriptLine) {
		return scriptLine.substring(calculateHierarchy(scriptLine));
	}

	/**
	 * 对加载序列中的脚本进行错误的剔除，
	 * 错误的类型包括基本语法错误，，
	 * 声明类型与声明的内容不匹配错误
	 * 循环继承，
	 * 继承父类不存在。
	 * @param scriptSequenceHead
	 * 		加载序列的HeadBlock。
	 */
	public static void organize(ScriptBlock scriptSequenceHead) {
		// TODO Auto-generated method stub
		//为各个脚本结构的信息赋予行数，
		//方便在后期输出错误信息的位置。
		new SequenceLineAssignerSpider(scriptSequenceHead)
			.workUntilEnd();
		
		//检查语法错误，
		//此阶段假定所有脚本定义的都是AbstractRClass，
		//各个层次中的一些包含的信息必须存在，
		//比如类型声明必须存在，类型声明下面的具体类型也必须存在，
		//哪怕是包含一个最微小的语法错误，
		//这个脚本都将整个地从加载序列中删除。
		new SequenceGrammarSpider(scriptSequenceHead)
			.workUntilEnd();
		
		//信息升级，
		//将脚本中的信息进行提升，
		//比如原来一行的成员信息，
		//在这里就要把它按照类型、名字、初始化值 三个部分进行分割
		//方便后续的实例化操作。
		new InformationUpgradeSpider(scriptSequenceHead)
			.workUntilEnd();
		
		//检查声明类型和声明内容不匹配错误，
		//特别针对脚本中声明的RClass类型进行检查，
		//保证接口类型的RClass没有声明Extends、Member
		//、ConFun、StaticFun、Fun；
		//保证普通RClass类型没有声明AbstractFun。
		new SequenceContentMatchSpider(scriptSequenceHead)
			.worUntilEnd();
		
		//检查父类是否存在，
		//如果父类没有被加载，
		//在加载序列中找到这个父类，
		//如果加载序列中也没有这个父类，
		//那么这个脚本就是错误的，
		将其从加载序列中删除。
		new SequenceSuperExistSpider(scriptSequenceHead)
			.workUnitlEnd();
		
		//检查循环继承是否存在，
		//将存在于循环继承中的脚本全部删除，
		//并且将剩余的正常序列进行重排序，
		//按照先父类后子类的顺序进行排序。
		new SequenceLoopExtendsSpider(scriptSequenceHead)
			.worUntilEnd();
		
	}
}
