package unfinishedClass.customRClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import basicTool.RLogger;

public class RClassScriptStruct {
	protected String rClassType;
	protected String name;
	protected String superRClass;
	protected String[] interfaces;
	protected String[][] staticMembers;
	protected String[][] members;
	/*
	protected FunctionScriptStruct conFunStruct;
	protected FunctionScriptStruct[] funStructs;
	*/
	
	public static RClassScriptStruct getRClassScriptStruct(BufferedReader scriptReader){
		ArrayList<String> scriptLines = new ArrayList<String>();
		String tempReader;
		try {
			while((tempReader = scriptReader.readLine()) != null){
				scriptLines.add(tempReader);
			}
		} catch (IOException e) {
			RLogger.logError("RClassScriptStruct读取脚本信息流出错，发生IO异常。");
			RLogger.logException(e);
			return null;
		}
		
		if (grammarIsRight(ScriptLines)){//grammarIsRight()检查脚本文件的语法正确；
			RClassScriptStruct rScriptStruct = new RClassScriptStruct();
			int nextLine = 0;
			nextLine = readRClassType(scriptLines, nextLine, rScriptStruct);
			nextLine = readName(scriptLines, nextLine, rScriptStruct);
			nextLine = readSuperRClass(scriptLines, nextLine, rScriptStruct);
			nextLine = readInterface(scriptLines, nextLine, rScriptStruct);
			nextLine = readStaticMember(scriptLines, nextLine, rScriptStruct);
			nextLine = readMember(scriptLines, nextLine, rScriptStruct);
			nextLine = readFunction(scriptLines, nextLine, rScriptStruct);
		}
		return null;
	}

	/**
	 * 读取CustomRClass的非接口父类对象，
	 * （请注意：
	 * 本方法不会处理字符串向数字转换的异常）。
	 * @param scriptLines
	 * 		包含原生脚本字符的ArrayList对象，
	 * 		每一行脚本存储一个单位。
	 * @param startLine
	 * 		从指定的行数读取。
	 * @param rScriptStruct
	 * 		接受信息的ScriptStruct对象。
	 * @return
	 * 		本信息读取完成之后的紧邻的下一个信息块行数。
	 * 		如果本行以"#Implements: "打头，则有接口信息，返回startLine + 接口数量 + 1；
	 * 		否则返回startLine。
	 */
	private static int readInterface(ArrayList<String> scriptLines, 
			int startLine, RClassScriptStruct rScriptStruct) {

		String interfaceLine = scriptLines.get(startLine);
		if (interfaceLine.startsWith("#Implements: ")){
			int interfaceNum = Integer.parseInt(interfaceLine.substring(13));
			rScriptStruct.interfaces = new String[interfaceNum];
			
			//读取指针下移一位
			++startLine;
			for (int i = 0; i < interfaceNum; ++i, ++startLine){
				rScriptStruct.interfaces[i] = 
						scriptLines.get(startLine);
			}
		}
		return startLine;
	}

	/**
	 * 读取CustomRClass的非接口父类对象。
	 * @param scriptLines
	 * 		包含原生脚本字符的ArrayList对象，
	 * 		每一行脚本存储一个单位。
	 * @param startLine
	 * 		从指定的行数读取。
	 * @param rScriptStruct
	 * 		接受信息的ScriptStruct对象。
	 * @return
	 * 		本信息读取完成之后的紧邻的下一个信息块行数。
	 * 		如果本行以"#Extends: "打头，则有父类信息，返回startLine + 1；
	 * 		否则返回startLine。
	 */
	private static int readSuperRClass(ArrayList<String> scriptLines, 
			int startLine, RClassScriptStruct rScriptStruct) {
		
		String superRClassLine = scriptLines.get(startLine);
		if (superRClassLine.startsWith("#Extends: ")){
			rScriptStruct.superRClass = superRClassLine.substring(10);
			return startLine + 1;
		}
		return startLine;
	}

	/**
	 * 读取CustomRClass的名称信息。
	 * @param scriptLines
	 * 		包含原生脚本字符的ArrayList对象，
	 * 		每一行脚本存储一个单位。
	 * @param startLine
	 * 		从指定的行数读取。
	 * @param rScriptStruct
	 * 		接受信息的ScriptStruct对象。
	 * @return
	 * 		本信息读取完成之后的紧邻的下一个信息块行数。
	 * 		返回startLine + 1。
	 */
	private static int readName(ArrayList<String> scriptLines, 
			int startLine, RClassScriptStruct rScriptStruct) {
		
		//去掉本行开头的7个字符"#Name: "，包括空格
		rScriptStruct.rClassType = scriptLines.get(startLine).substring(7);
		return startLine + 1;
	}

	/**
	 * 读取CustomRClass的类型信息。
	 * @param scriptLines
	 * 		包含原生脚本字符的ArrayList对象，
	 * 		每一行脚本存储一个单位。
	 * @param startLine
	 * 		从指定的行数读取。
	 * @param rScriptStruct
	 * 		接受信息的ScriptStruct对象。
	 * @return
	 * 		本信息读取完成之后的紧邻的下一个信息块行数。
	 * 		返回startLine + 1。
	 */
	private static int readRClassType(ArrayList<String> scriptLines, int startLine, RClassScriptStruct rScriptStruct) {
		//去掉本行开头的12个字符"#RClassType: "，包括空格
		rScriptStruct.rClassType = scriptLines.get(startLine).substring(12);
		return startLine + 1;
	}
}
