package unfinishedClass.customRClass;

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
		checkLine = new RClassTypeScriptChecker().check(scriptLines, checkLine, checkResult);
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
	 * 检查静态成员变量声明是否正确，
	 * 检查项目包括是否声明了相应数量的静态成员，
	 * 静态成员变量名字是否包含非法字符。
	 * @param scriptLines
	 * 		包含原生脚本字符的ArrayList对象，
	 * 		每一行脚本存储一个单位。
	 * @param checkLine
	 * 		从指定的行数开始检查。
	 * @param checkResult
	 * 		存储检查的结果。
	 * @return
	 * 		无错误发生的话返回checkLine + 1，
	 * 		出现任何错误返回-1。
	 */
	private static int checkStaticMember(ArrayList<String> scriptLines, int checkLine,
			ScriptCheckResult checkResult) {
		// TODO Auto-generated method stub
		int interfaceNum = 0;
		String scriptLine;
		//确保之前的检查结果为true
		if (checkResult.isRight()){
			
			//确保checkLine可提取脚本信息
			if (checkLine >= 0 && checkLine < scriptLines.size()){
				
				//获取脚本信息
				scriptLine = scriptLines.get(checkLine);
				
				//本行是否声明接口
				if (scriptLine.startsWith(rClassInterfaceDeclaration)){
					
					scriptLine = 
							scriptLine.substring(rClassInterfaceDeclaration.length());
					
					//检查接口数量声明是否符合规范
					try{
						interfaceNum = Integer.parseInt(scriptLine);
					} catch (NumberFormatException e){
						RLogger.logError("CustomRClass脚本中第" + checkLine
								+ "行  接口声明出错，"
								+ "声明的接口数量无法从字符串转换成整形数字。");
						RLogger.logException(e);
						checkResult.setResult(false);
						interfaceNum = 0;
					}
					
					//检查接口数量声明是否为正整数
					if (interfaceNum < 0){
						RLogger.logError("CustomRClass脚本中第" + checkLine
								+ "行  接口声明出错，"
								+ "声明的接口数量不能为负数。");
						checkResult.setResult(false);
						interfaceNum = 0;
					}
					
					//检查固定数量的接口名字的正确性
					for (int i = 1; i < interfaceNum && checkResult.isRight(); ++i){
						
						//检查是否到了脚本文件的结尾
						if(checkLine + i < scriptLines.size()){
							scriptLine = scriptLines.get(checkLine + i);
							
							//检查接口声明是否以'$'开头
							if (scriptLine.startsWith("$")){
								
								//检查接口名字是否包含非法字符
								if (RNameChecker.check(scriptLine.substring(1))){
									//TODO
								} else {
									RLogger.logError("CustomRClass脚本中第" + checkLine + i
											+ "行  接口声明出错，"
											+ "该行声明的接口名字包含以下非法字符："
											+ RNameChecker.getErrorStrings());
									checkResult.setResult(false);
								}
								
							} else {
								RLogger.logError("CustomRClass脚本中第" + checkLine + i
										+ "行  接口声明出错，"
										+ "该行的信息没有以'$'字符开始。");
								checkResult.setResult(false);
							}//if 检查接口声明是否以'$'开头
							
						} else {
							RLogger.logError("CustomRClass脚本中接口声明出错，"
									+ "该脚本声明了" + interfaceNum
									+ "个接口，但是程序只检查到第" + i
									+ "个接口的时候到达了脚本文件的末尾，"
									+ "接口声明缺失。");
							checkResult.setResult(false);
							
						}//if 检查是否到了脚本文件结尾
					}//for 检查各个接口名称的正确性
				}//if 本行是否声明接口
				
			} else {
				RLogger.logError("CustomRClass脚本 接口声明出错，没有可检查的脚本信息。");
					checkResult.setResult(false);
			}//if 能否获取脚本信息
			
		} else {
			RLogger.logError("CustomRClass脚本 接口声明检查取消，因为本次检查之前的检查结果false，不能进行本次检查。");
		}//if 本次检查前结果是否为true
		
		
		if (checkResult.isRight()){
			return checkResult.getRClassType() == 0 ? 
					checkLine : checkLine + 1;
		} else {
			return -1;
		}//if
	}

	/**
	 * 检查接口声明是否正确，
	 * 检查项目包括是否声明了相应数量的接口名字，
	 * 接口名字是否包含非法字符。
	 * @param scriptLines
	 * 		包含原生脚本字符的ArrayList对象，
	 * 		每一行脚本存储一个单位。
	 * @param checkLine
	 * 		从指定的行数开始检查。
	 * @param checkResult
	 * 		存储检查的结果。
	 * @return
	 * 		无错误发生的话返回checkLine + 1，
	 * 		出现任何错误返回-1。
	 */
	private static int checkInterface(ArrayList<String> scriptLines, int checkLine, 
			ScriptCheckResult checkResult) {
		int interfaceNum = 0;
		String scriptLine;
		//确保之前的检查结果为true
		if (checkResult.isRight()){
			
			//确保checkLine可提取脚本信息
			if (checkLine >= 0 && checkLine < scriptLines.size()){
				
				//获取脚本信息
				scriptLine = scriptLines.get(checkLine);
				
				//本行是否声明接口
				if (scriptLine.startsWith(rClassInterfaceDeclaration)){
					
					scriptLine = 
							scriptLine.substring(rClassInterfaceDeclaration.length());
					
					//检查接口数量声明是否符合规范
					try{
						interfaceNum = Integer.parseInt(scriptLine);
					} catch (NumberFormatException e){
						RLogger.logError("CustomRClass脚本中第" + checkLine
								+ "行  接口声明出错，"
								+ "声明的接口数量无法从字符串转换成整形数字。");
						RLogger.logException(e);
						checkResult.setResult(false);
						interfaceNum = 0;
					}
					
					//检查接口数量声明是否为正整数
					if (interfaceNum < 0){
						RLogger.logError("CustomRClass脚本中第" + checkLine
								+ "行  接口声明出错，"
								+ "声明的接口数量不能为负数。");
						checkResult.setResult(false);
						interfaceNum = 0;
					}
					
					//检查固定数量的接口名字的正确性
					for (int i = 1; i < interfaceNum && checkResult.isRight(); ++i){
						
						//检查是否到了脚本文件的结尾
						if(checkLine + i < scriptLines.size()){
							scriptLine = scriptLines.get(checkLine + i);
							
							//检查接口声明是否以'$'开头
							if (scriptLine.startsWith("$")){
								
								//检查接口名字是否包含非法字符
								if (RNameChecker.check(scriptLine.substring(1))){
									//TODO
								} else {
									RLogger.logError("CustomRClass脚本中第" + checkLine + i
											+ "行  接口声明出错，"
											+ "该行声明的接口名字包含以下非法字符："
											+ RNameChecker.getErrorStrings());
									checkResult.setResult(false);
								}
								
							} else {
								RLogger.logError("CustomRClass脚本中第" + checkLine + i
										+ "行  接口声明出错，"
										+ "该行的信息没有以'$'字符开始。");
								checkResult.setResult(false);
							}//if 检查接口声明是否以'$'开头
							
						} else {
							RLogger.logError("CustomRClass脚本中接口声明出错，"
									+ "该脚本声明了" + interfaceNum
									+ "个接口，但是程序只检查到第" + i
									+ "个接口的时候到达了脚本文件的末尾，"
									+ "接口声明缺失。");
							checkResult.setResult(false);
							
						}//if 检查是否到了脚本文件结尾
					}//for 检查各个接口名称的正确性
				}//if 本行是否声明接口
				
			} else {
				RLogger.logError("CustomRClass脚本 接口声明出错，没有可检查的脚本信息。");
					checkResult.setResult(false);
			}//if 能否获取脚本信息
			
		} else {
			RLogger.logError("CustomRClass脚本 接口声明检查取消，因为本次检查之前的检查结果false，不能进行本次检查。");
		}//if 本次检查前结果是否为true
		
		
		if (checkResult.isRight()){
			return checkResult.getRClassType() == 0 ? 
					checkLine : checkLine + 1;
		} else {
			return -1;
		}//if
	}//checkInterface

	/**
	 * 检查父类的声明是否正确，
	 * 如果根据之前的检查结果，
	 * 当前脚本定义的是接口，
	 * 但是却声明了父类，就算是语法错误。
	 * @param scriptLines
	 * 		包含原生脚本字符的ArrayList对象，
	 * 		每一行脚本存储一个单位。
	 * @param checkLine
	 * 		从指定的行数开始检查。
	 * @param checkResult
	 * 		存储检查的结果。
	 * @return
	 * 		无错误发生且脚本定义的是接口类型的话返回checkLine；
	 * 		无错误发生，且脚本定义的是抽象类型或者普通类型的话返回checkLine + 1；
	 * 		出现任何错误返回-1。
	 */
	private static int checkSuperRClass(ArrayList<String> scriptLines, int checkLine, ScriptCheckResult checkResult) {
		String scriptLine;
		//确保之前的检查结果为true
		if (checkResult.isRight()){
			
			//确保checkLine可提取脚本信息
			if (checkLine >= 0 && checkLine < scriptLines.size()){
				
				//获取脚本信息
				scriptLine = scriptLines.get(checkLine);
				
				//本行是否声明父类
				if (scriptLine.startsWith(rClassExtendsDeclaration)){
					
					//声明了父类，查看先前的检查结果，
					//判断这个脚本信息能够声明父类，
					//要求，接口类型不能声明父类。
					if (checkResult.getRClassType() == 0){
						RLogger.logError("CustomRClass脚本中第" + checkLine
								+ "行  父类声明出错，"
								+ "先前的检查结果表明这个脚本定义的是一个接口类型，"
								+ "但是这个脚本却声明了一个父类，"
								+ "接口不允许声明父类，如果接口要继承其他接口，"
								+ "请使用“" + rClassInterfaceDeclaration 
								+ "”加被继承接口数量，换行，相应数量的被继承接口的名字，"
								+ "这样的方式来声明要继承的接口。");
						checkResult.setResult(false);
					} else {
						
						//获取父类名字
						scriptLine = 
								scriptLine.substring(rClassExtendsDeclaration.length());
						if (RNameChecker.check(scriptLine)){
							//TODO
							//RLogger.log("CustomRClass");
						} else {
							RLogger.logError("CustomRClass脚本中第" + checkLine
									+ "行  父类声明出错，"
									+ "父类的名字中包含以下非法字符串："
									+ RNameChecker.getErrorStrings());
						}
					}//if 父类是否为接口
				}//if 本行是否声明父类
				
			} else {
				RLogger.logError("CustomRClass脚本 父类声明出错，没有可检查的脚本信息。");
				checkResult.setResult(false);
			}//if 能否获取脚本信息
			
		} else {
			RLogger.logError("CustomRClass脚本 名字声明检查取消，因为本次检查之前的检查结果false，不能进行本次检查。");
		}//if 本次检查前结果是否为true
		
		
		if (checkResult.isRight()){
			return checkResult.getRClassType() == 0 ? 
					checkLine : checkLine + 1;
		} else {
			return -1;
		}//if
	}//checkSuperRClass

	/**
	 * 检查名字的声明是否正确，
	 * 名字中不能包含'#''@''{''}'','' '(不能包含空格)。
	 * @param scriptLines
	 * 		包含原生脚本字符的ArrayList对象，
	 * 		每一行脚本存储一个单位。
	 * @param checkLine
	 * 		从指定的行数开始检查。
	 * @param checkResult
	 * 		存储检查的结果。
	 * @return
	 * 		无错误发生的话返回checkLine + 1，
	 * 		出现任何错误返回-1。
	 */
	private static int checkName(ArrayList<String> scriptLines, int checkLine, ScriptCheckResult checkResult) {
		String scriptLine;
		//确保之前的检查结果为true
		if (checkResult.isRight()){
			
			//确保checkLine可提取脚本信息
			if (checkLine >= 0 && checkLine < scriptLines.size()){
				
				//获取脚本信息
				scriptLine = scriptLines.get(checkLine);
				
				//本行是否声明名字
				if (scriptLine.startsWith(rClassNameDeclaration)){
					
					//获取RClass名字声明
					scriptLine = 
							scriptLine.substring(rClassNameDeclaration.length());
					
					if (RNameChecker.check(scriptLine)){
						//TODO
						//checkResult.setResult(true);
					} else {
						RLogger.logError("CustomRClass脚本中第" + checkLine
								+ "行 名字声明出错，"
								+ "声明的RClass名字中包含以下非法字符串："
								+ RNameChecker.getErrorStrings());
						checkResult.setResult(false);
					}
					
				} else {
					RLogger.logError("CustomRClass脚本中第" + checkLine
							+ "行  名字声明出错，"
							+ "没有在此行中声明RClass的名字。");
					checkResult.setResult(false);
				}//if 本行是否声明名字
				
			} else {
				RLogger.logError("CustomRClass脚本 名字声明出错，"
						+ "没有可检查的脚本信息。");
				checkResult.setResult(false);
			}//if 确保checkLine可提取脚本信息
			
		} else {
			RLogger.logError("CustomRClass脚本 名字声明检查取消，"
					+ "因为本次检查之前的检查结果false，"
					+ "不能进行本次检查。");
		}//if 本次检查前结果是否为true
		
		
		if (checkResult.isRight()){
			return checkLine + 1;
		} else {
			return -1;
		}//if
	}//checkName

	/**
	 * 检查脚本的RClass类型声明是否正确，
	 * 并且在正确的情况下对应的设置checkResult中的rClassType整形变量，
	 * 0代表Interface，
	 * 1代表AbstractClass，
	 * 2代表Class；
	 * 如果错误就设置rClassType中的isRight为false，
	 * 对应的整形变量为-1。
	 * @param scriptLines
	 * 		包含原生脚本字符的ArrayList对象，
	 * 		每一行脚本存储一个单位。
	 * @param checkLine
	 * 		从指定的行数开始检查。
	 * @param checkResult
	 * 		存储检查的结果。
	 * @return
	 * 		检查完毕之后下一个要检查的行数，
	 * 		-1代表脚本检查完毕。
	 */
	private static int checkRClassType(ArrayList<String> scriptLines, int checkLine,
			ScriptCheckResult checkResult) {
		String scriptLine;
		//确保之前的检查结果为true
		if (checkResult.isRight()){
			
			//确保checkLine可提取脚本信息
			if (checkLine >= 0 && checkLine < scriptLines.size()){
				
				//获取本行检查信息
				scriptLine = scriptLines.get(checkLine);
				
				//获取类型声明信息
				if (scriptLine.startsWith(rClassTypeDeclaration)){
					//获取RClass类型声明
					scriptLine = 
							scriptLine.substring(rClassTypeDeclaration.length());
					//检查三种类型声明
					if (scriptLine.equals(rClassInterfaceType)){
						checkResult.setRClassType(0);
					} else if (scriptLine.equals(rClassAbstractType)){
						checkResult.setRClassType(1);
					} else if (scriptLine.equals(rClassClassType)){
						checkResult.setRClassType(2);
					} else {
						RLogger.logError("CustomRClass脚本中第" + checkLine
								+ "行  类型声明出错，没有正确声明CustomRClass的类型，"
								+ "请确保脚本文件中的第一行“" + rClassTypeDeclaration 
								+ "”之后声明的CustomRClass类型为一下三种中的一种："
								+ rClassInterfaceType + "，"
								+ rClassAbstractType + "，"
								+ rClassClassType +  "。" );
						checkResult.setRClassType(-1);
						checkResult.setResult(false);
					}//if 检查三种类型声明
					
				} else {
					RLogger.logError("CustomRClass脚本 类型声明出错中第" + checkLine
							+ "行 ，没有声明CustomRClass的类型，"
							+ "请确保以一下三种方式在脚本文件的第一行声明CustomRClass的类型："
							+ rClassTypeDeclaration + rClassInterfaceType + "，"
							+ rClassTypeDeclaration + rClassAbstractType + "，"
							+ rClassTypeDeclaration + rClassClassType +  "。" );
					checkResult.setRClassType(-1);
					checkResult.setResult(false);
				}//if 获取本行检查信息
				
			} else {
				RLogger.logError("CustomRClass脚本 类型声明出错，没有可检查的脚本信息。");
				checkResult.setRClassType(-1);
				checkResult.setResult(false);
			}//if 确保checkLine可读取脚本信息
		} else {
			RLogger.logError("CustomRClass脚本 类型声明检查取消，因为本次检查之前的检查结果false，不能进行本次检查。");
		}//if 本次检查前结果是否为true
		
		
		if (checkResult.isRight()){
			return checkLine + 1;
		} else {
			return -1;
		}//if checkResult
	}//checkRClassType

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
