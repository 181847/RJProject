package unfinishedClass.customRClass.script;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import basicInterface.INameable;
import basicTool.RLogger;
import unfinishedClass.customRClass.scriptChecker.RClassTypeScriptChecker;
import unfinishedClass.customRClass.scriptChecker.ScriptCheckResult;

/**
 * 这个脚本结构之所以继承INameable结构是为了方便在加载工程文件时，
 * 方便创建加载序列对象：LoadCusRClassSequence，
 * 这个LoadCusRClassSequence继承了NamedItemList，
 * 使用了NamedItemList中一些关于名字的方法，
 * 让这个RClassScriptStruct继承INameable接口，
 * 方便加载序列添加这个对象。
 */
public class RClassScriptStruct implements INameable{
	public static String rClassTypeDeclaration = "#RClassType: ";
	
	public static String rClassInterfaceType = "Interface";
	public static String rClassAbstractType = "AbstractClass";
	public static String rClassClassType = "Class";
	
	public static String rClassNameDeclaration = "#Name: ";
	public static String rClassExtendsDeclaration = "#Extends: ";
	public static String rClassInterfaceDeclaration = "#Implements: ";
	public static String rClassStaticMemberDeclaration = "#StaticMember: ";
	public static String rClassMemberDeclaration = "#Member: ";
	public static String rClassConFunDeclaration = "#ConstructFunction: ";
	public static String rClassStaticFunDeclaration = "#StaticFunction: ";
	public static String rClassFunctionDeclaration = "#Function: ";
	
	protected String rClassType;
	protected String name;
	/**
	 * 非接口父类的名字，
	 * 如果没有继承非接口父类，
	 * 这一项应该为空串，即""。
	 */
	protected String superRClass;
	protected String[] interfaces;
	protected String[][] staticMembers;
	protected String[][] members;
	/*
	protected FunctionScriptStruct conFunStruct;
	protected FunctionScriptStruct[] funStructs;
	*/
	
	public static RClassScriptStruct getRClassScriptStruct(ArrayList<String> scriptLines){
		if (grammarIsRight(scriptLines)){//grammarIsRight()检查脚本文件的语法正确
			RClassScriptStruct rScriptStruct = new RClassScriptStruct();
			int nextLine = 0;
			nextLine = readRClassType(scriptLines, nextLine, rScriptStruct);
			nextLine = readName(scriptLines, nextLine, rScriptStruct);
			nextLine = readSuperRClass(scriptLines, nextLine, rScriptStruct);
			nextLine = readInterface(scriptLines, nextLine, rScriptStruct);
			nextLine = readStaticMember(scriptLines, nextLine, rScriptStruct);
			nextLine = readMember(scriptLines, nextLine, rScriptStruct);
			nextLine = readFunction(scriptLines, nextLine, rScriptStruct);
			return rScriptStruct;
		}
		return null;
	}
	
	/**
	 * 检查脚本信息是否符合基本的语法结构（注意：这里不会检查任何类型正确，
	 * 例如继承了一个不存在的类、或者声明了一个不存在变量类型等等，
	 * 这些错误不在检查范围之内），
	 * 检查的项目包括CustomRClass类型声明是否为一下三种：
	 * “Interface, AbstractClass, Class”；
	 * 名称、父类、接口等是否包含非法字符；
	 * 声明的项目数量是否与声明的对象对应，
	 * 比如"#Implement: 3"声明了三个接口，
	 * 但是下方只写出了两个接口的名字，
	 * 这就算是语法错误；
	 * 声明的RClass类型是否与声明的成员变量、Function一致，
	 * 诸如声明为“Interface”，但是又声明了成员变量，
	 * 这就算是语法错误，
	 * 要求，“Interface”只能声明抽象成员Function、
	 * “AbstractClass”至少声明一个抽象成员Function、
	 * “Class”不能声明抽象成员Function。
	 * @param scriptLines
	 * 		包含脚本信息的字符串数组列表，
	 * 		每一个元素都是脚本的一行字符串。
	 */
	public static boolean grammarIsRight(ArrayList<String> scriptLines) {
		// TODO Auto-generated method stub
		//checkResult用来存储临时的检查信息，
		//比如当前脚本是接口、抽象类还是普通类，
		
		ScriptCheckResult checkResult = new ScriptCheckResult();
		int checkLine = 0;
		checkLine = new RClassTypeScriptChecker()
				.check(scriptLines, checkLine, checkResult);
		checkLine = checkRClassType(scriptLines, checkLine, checkResult);
		checkLine = checkName(scriptLines, checkLine, checkResult);
		checkLine = checkSuperRClass(scriptLines, checkLine, checkResult);
		checkLine = checkInterface(scriptLines, checkLine, checkResult);
		checkLine = checkStaticMember(scriptLines, checkLine, checkResult);
		checkLine = checkMember(scriptLines, checkLine, checkResult);
		checkLine = checkFunction(scriptLines, checkLine, checkResult);
		
		return checkResult.isRight();
	}

	/**
	 * 读取CustomRClass的静态成员信息。
	 * @param scriptLines
	 * 		包含原生脚本字符的ArrayList对象，
	 * 		每一行脚本存储一个单位。
	 * @param startLine
	 * 		从指定的行数读取。
	 * @param rScriptStruct
	 * 		存储信息的ScriptStruct对象。
	 * @return
	 * 		如果本行以rClassMemberDeclaration开头，
	 * 		则返回startLine + 成员数量 + 1；
	 * 		否则返回startLine。
	 */
	private static int readMember(ArrayList<String> scriptLines, 
			int startLine, RClassScriptStruct rScriptStruct) {
		
		String memberLine = scriptLines.get(startLine);
		int memberNum;
		if (memberLine.startsWith(rClassStaticMemberDeclaration)){
			memberNum = Integer.parseInt(
					memberLine.substring(
							rClassStaticMemberDeclaration.length()));
			
			rScriptStruct.members = new String[memberNum][];
			
			//读取指针下移一位
			++startLine;
			for (int i = 0; i < memberNum; ++i, ++startLine){
				memberLine = 
						scriptLines.get(startLine);
				rScriptStruct.members[i] = memberLine.split(" ");
			}//for
			
		} else {
			rScriptStruct.members = new String[0][];
		}
		return startLine;
	}//readMember

	/**
	 * 读取CustomRClass的静态成员信息。
	 * @param scriptLines
	 * 		包含原生脚本字符的ArrayList对象，
	 * 		每一行脚本存储一个单位。
	 * @param startLine
	 * 		从指定的行数读取。
	 * @param rScriptStruct
	 * 		存储信息的ScriptStruct对象。
	 * @return
	 * 		如果本行以rClassStaticMemberDeclaration开头，
	 * 		则返回startLine + 静态成员数量 + 1；
	 * 		否则返回startLine。
	 */
	private static int readStaticMember(ArrayList<String> scriptLines, 
			int startLine, RClassScriptStruct rScriptStruct) {
		
		String staticMemberLine = scriptLines.get(startLine);
		int staticMemberNum;
		if (staticMemberLine.startsWith(rClassStaticMemberDeclaration)){
			//去掉本行开头的静态成员声明
			staticMemberNum = Integer.parseInt(
					staticMemberLine.substring(
							rClassStaticMemberDeclaration.length()));
			rScriptStruct.staticMembers = new String[staticMemberNum][];
			
			//读取指针下移一位
			++startLine;
			for (int i = 0; i < staticMemberNum; ++i, ++startLine){
				staticMemberLine = 
						scriptLines.get(startLine);
				rScriptStruct.staticMembers[i] = staticMemberLine.split(" ");
			}//for
		} else {
			rScriptStruct.staticMembers = new String[0][];
		}
		return startLine;
	}

	/**
	 * 读取CustomRClass的非接口父类对象，
	 * （请注意：
	 * 本方法不会处理字符串向数字转换的异常，
	 * 所有异常的数字表达都会在isGrammerRight()中被视为语法错误）。
	 * @param scriptLines
	 * 		包含原生脚本字符的ArrayList对象，
	 * 		每一行脚本存储一个单位。
	 * @param startLine
	 * 		从指定的行数读取。
	 * @param rScriptStruct
	 * 		存储信息的ScriptStruct对象。
	 * @return
	 * 		本信息读取完成之后的紧邻的下一个信息块行数。
	 * 		如果本行以rClassInterfaceDeclaration打头，则有接口信息，返回startLine + 接口数量 + 1；
	 * 		否则返回原本的startLine。
	 */
	private static int readInterface(ArrayList<String> scriptLines, 
			int startLine, RClassScriptStruct rScriptStruct) {
		int interfaceNum;
		String interfaceLine = scriptLines.get(startLine);
		if (interfaceLine.startsWith(rClassInterfaceDeclaration)){
			//去掉本行开头的接口声明
			interfaceNum = Integer.parseInt(
					interfaceLine.substring(
							rClassInterfaceDeclaration.length()));
			rScriptStruct.interfaces = new String[interfaceNum];
			
			//读取指针下移一位
			++startLine;
			for (int i = 0; i < interfaceNum; ++i, ++startLine){
				rScriptStruct.interfaces[i] = 
						scriptLines.get(startLine);
			}//for
		} else {
			rScriptStruct.interfaces = new String[0];
		}
		return startLine;
	}//readInterface

	/**
	 * 读取CustomRClass的非接口父类对象。
	 * @param scriptLines
	 * 		包含原生脚本字符的ArrayList对象，
	 * 		每一行脚本存储一个单位。
	 * @param startLine
	 * 		从指定的行数读取。
	 * @param rScriptStruct
	 * 		存储信息的ScriptStruct对象。
	 * @return
	 * 		本方法所需信息读取完成之后的紧邻的下一个信息块行数。
	 * 		如果本行以"#Extends: "打头，则有父类信息，返回startLine + 1；
	 * 		否则返回startLine。
	 */
	private static int readSuperRClass(ArrayList<String> scriptLines, 
			int startLine, RClassScriptStruct rScriptStruct) {
		
		String superRClassLine = scriptLines.get(startLine);
		if (superRClassLine.startsWith(rClassExtendsDeclaration)){
			//去掉本行开头的父类声明
			rScriptStruct.superRClass = 
					superRClassLine.substring(rClassExtendsDeclaration.length());
			return startLine + 1;
		} else {
			rScriptStruct.superRClass = "";
		}
		return startLine;
	}//readSuperRClass

	/**
	 * 读取CustomRClass的名称信息。
	 * @param scriptLines
	 * 		包含原生脚本字符的ArrayList对象，
	 * 		每一行脚本存储一个单位。
	 * @param startLine
	 * 		从指定的行数读取。
	 * @param rScriptStruct
	 * 		存储信息的ScriptStruct对象。
	 * @return
	 * 		本方法所需信息读取完成之后的紧邻的下一个信息块行数。
	 * 		返回startLine + 1。
	 */
	private static int readName(ArrayList<String> scriptLines, 
			int startLine, RClassScriptStruct rScriptStruct) {
		
		//去掉本行开头的RClass名称声明
		rScriptStruct.rClassType = 
				scriptLines.get(startLine)
				.substring(rClassNameDeclaration.length());
		return startLine + 1;
	}//readName

	/**
	 * 读取CustomRClass的类型信息。
	 * @param scriptLines
	 * 		包含原生脚本字符的ArrayList对象，
	 * 		每一行脚本存储一个单位。
	 * @param startLine
	 * 		从指定的行数读取。
	 * @param rScriptStruct
	 * 		存储信息的ScriptStruct对象。
	 * @return
	 * 		本方法所需信息读取完成之后的紧邻的下一个信息块行数。
	 * 		返回startLine + 1。
	 */
	private static int readRClassType(ArrayList<String> scriptLines,
			int startLine, RClassScriptStruct rScriptStruct) {
		
		//去掉本行开头的类型声明
		rScriptStruct.rClassType = 
				scriptLines.get(startLine)
				.substring(rClassTypeDeclaration.length());
		return startLine + 1;
	}//readRClassType

	/**
	 * @return
	 * 		这个CustomRClass脚本结构所定义的
	 * 		CustomRClass的全名，比如“myPackage.RClass1”。
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * 空方法，
	 * 不允许随便更改CustomRClass脚本结构的名字。
	 */
	@Override
	public void setName(String newName) {
		//Empty Body
	}
	
	/**
	 * 获取这个脚本结构所声明的父类RClass全名，包括接口。
	 * @return
	 * 		包含所有父类的String字符串数组，
	 * 		如果没有继承任何父类、接口，就返回一个长度为0的字符串数组。
	 */
	public String[] getParents(){
		int parentsNum = 0;
		//计算父类数量
		if ( ! superRClass.isEmpty()){
			parentsNum = 1;
		}
		parentsNum += interfaces.length;
		String[] parents = new String[parentsNum];
		
		//复制父类名字
		parentsNum = 0;
		if ( ! superRClass.isEmpty()){
			parents[parentsNum] = superRClass;
		}
		for (String father : interfaces){
			parents[parentsNum++] = father;
		}
		return parents;
	}//getParents
}
