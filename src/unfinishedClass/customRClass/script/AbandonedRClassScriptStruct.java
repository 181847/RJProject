package unfinishedClass.customRClass.script;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import basicTool.RLogger;
import functionInterface.IFunctionMaker;
import unfinishedClass.RReferenceHelper;
import unfinishedClass.RReferenceInfo;
import unfinishedClass.customRClass.CustomRClass;

/**
 * 本类暂时被废弃。
 * @author 75309
 *
 */
public class AbandonedRClassScriptStruct {
	/**
	 * 当前脚本文件是否正确，
	 * 语法是否符合规范，
	 * 父类对象是否存在……
	 */
	private boolean isScriptCorrect;
	
	/**
	 * RClass脚本文件的目录。
	 */
	protected String rClassScriptStructPath;
	
	/**
	 * 所有导入的其他RClass的文件的目录。
	 */
	protected String[] importRClassPaths;
	
	/**
	 * 这个RClass脚本文件所定义的RClass的名字。
	 */
	protected String mainRClassName;
	
	/**
	 * 继承的父类RClass（非接口类型）名字。
	 */
	protected String superRClassName;
	
	/**
	 * 继承的接口的名字。
	 */
	protected String[] superInterfaceNames;
	
	/**
	 * 声明的静态成员信息，
	 * 第一维任意长度，
	 * 第二维必须三个长度，
	 * 分别存类型、名字、初始化数据。
	 */
	protected String[][] staticMemberInfos;
	
	/**
	 * 声明的普通成员信息
	 * 第一维任意长度，
	 * 第二维必须三个长度，
	 * 分别存类型、名字、初始化数据。
	 */
	protected String[][] normalMemberInfos;
	
	/**
	 * 构造Function的脚本文件在Zip文件中的路径。
	 */
	protected String constructFunctionScriptPath;
	
	/**
	 * 其他Function的脚本文件在Zip文件中的路径。
	 */
	protected String functionScriptPath;
	
	/**
	 * 构造Function的脚本信息体。
	 */
	protected FunctionScriptStruct conFunScriptStruct;
	
	/**
	 * 其他Function的脚本信息体。
	 */
	protected FunctionScriptStruct[] funScriptStructs;
	
	/**
	 * 父类的RClassID，
	 * 在checkLegal()方法中才初始化。
	 */
	protected int superRClassID;
	
	/**
	 * 所有接口的RClassID，
	 * 在checkLegal()方法中才初始化。
	 */
	protected int[] superInterfaceIDs;
	
	
	
	/**
	 * 读取一个CustomRClassZip文件路径，
	 * 来把这个Zip文件下的CustomRClass信息读取出来，
	 * 并返回一个RClassScriptStruct。
	 * @param customRClassPath
	 * 		要加载的Zip文件路径。
	 * @return
	 * 		如果文件不存在，
	 * 		或者发生接口RClass，
	 * 		却声明的成员变量之类的错误的话，返回null；
	 * 		如果正确返回一个RClassScriptStruct对象。
	 */
	public static RClassScriptStruct getScriptStruct(String customRClassPath){
		ZipFile rClassZF;
		try{
			rClassZF = new ZipFile(customRClassPath);
		} catch(ZipException e){
			RLogger.logError("RClassScriptStruct获取 ZipFile 失败，"
					+ "目标路径不是Zip文件格式，"
					+ "请检查RClass脚本路径：" + customRClassPath + "。");
			RLogger.logException(e);
			return null;
			
		} catch (FileNotFoundException e){
			RLogger.logError("RClassScriptStruct获取ZipInputStream时异常，"
					+ "文件不存在，"
					+ "请检查RClass脚本路径：" + customRClassPath + "。");
			return null;
			
		} catch (IOException e){
			RLogger.logError("RClassScriptStruct获取 ZipFile 时发生IO异常，"
					+ "请检查RClass脚本路径：" + customRClassPath + "。");
			RLogger.logException(e);
			return null;
			
		} catch (SecurityException e){
			RLogger.logError("RClassScriptStruct获取 ZipFile 时发生 安全性 异常，"
					+ "读取脚本文件失败，"
					+ "请检查RClass脚本路径：" + customRClassPath + "。");
			RLogger.logException(e);
			return null;
		}
		
		//获取主要描述文件
		ZipEntry rManifest;
		try{
			rManifest = rClassZF.getEntry("RMETA-INF/RMANIFEST.txt");
		} catch (IllegalStateException e){
			RLogger.logError("获取RClass脚本的Zip文件中的\"RMETA-INF/RMANIFEST.txt\" 时Zip文件"
					+ "已被关闭，创建RClassScriptStruct失败，"
					+ "请检查RClass脚本路径：" + customRClassPath + "。");
			return null;
		}
		
		if (rManifest == null){
			RLogger.logError("获取RClass脚本的Zip文件中不存在的\"RMETA-INF/RMANIFEST.txt\"，"
					+ "创建RClassScriptStruct失败，"
					+ "请检查RClass脚本路径：" + customRClassPath + "。");
			return null;
		}
		
		BufferedReader rManifestBR = 
				new BufferedReader(
						new InputStreamReader(
								rClassZF.getInputStream(rManifest)));
		
		RClassScriptStruct rClassScriptStruct =
				new RClassScriptStruct();
		
		String reader;
		boolean haveMainRClassInfo = false;
		ArrayList<String> importRClassList = new ArrayList<String>();
		while ((reader = rManifestBR.readLine()) != null){
			if (reader.startsWith("MainRClass: ")){
				
				//出现两个MainRClass，显示出错信息，加载失败
				if (haveMainRClassInfo){
					RLogger.logError("创建RClassScriptStruct时，"
							+ "在RMANIFEST.txt中发现两个MainRClass信息：，"
							+ "创建ScriptStruct失败");
					return null;
				}
				
				reader = reader.substring(12);
				//初始化RClassScriptStruct的主要信息，
				//包括父类、成员变量、成员Function，
				//返回值表示加载是否成功，
				//加载过程中一旦发生错误就会返回false。
				haveMainRClassInfo = formatMainRClassInfo(rClassScriptStruct, rClassZF, reader);
				if (haveMainRClassInfo){
					continue;
				} else {
					RLogger.logError("读取RClass主要文件时发生错误，"
							+ "加载RClass主要信息失败，请检查："
							+ "ZipFile文件路径：" + customRClassPath
							+ "，zip中主要RClass文件路径：" + reader);
					return null;
				}
			}
			
			//导入外部RClass，
			//注意导入的外部RClass是一个Zip/Jar文件的路径，
			//是本RClass脚本Zip文件外面的文件。
			if (reader.startsWith("ImportRClass: ")){
				importRClassList.add(reader.substring(14));
			}
		}
		
		//初始化导入类信息。
		rClassScriptStruct.importRClassPaths = 
				importRClassList.toArray(new String[0]);
		
		rClassZF.close();
		return null;
	}

	/**
	 * 通过这个rClassZF的Zip文件，以及指定的Zip文件内部路径，
	 * 来初始化RClassScriptStruct内部的RClass主要信息，
	 * 包括父类信息、接口信息、RClass名字、
	 * 静态成员变量、普通成员变量、成员FunctionScript、
	 * 静态FunctionScript。
	 * @param rClassScriptStruct
	 * 		rClassScriptStruct对象，
	 * 		用来存放RClass脚本信息。
	 * @param rClassZF
	 * 		拥有所有脚本信息的Zip文件，
	 * 		这里不会检查zip文件的正确性。
	 * @param innerZipFilePath
	 * 		记录了RClass主要信息的zip内部文件地址。
	 * @return 
	 * 		如果发生任何读取错误，
	 * 		或者基本的格式规范错误，
	 * 		返回false；
	 * 		如果正常加载完的话，返回true。
	 */
	private static boolean formatMainRClassInfo(RClassScriptStruct rClassScriptStruct, 
			ZipFile rClassZF,
			String mainScriptPath) {
		// TODO Auto-generated method stub
		
		ZipEntry mainEntry;
		try{
			mainEntry = rClassZF.getEntry(mainScriptPath);
		} catch (IllegalStateException e){
			RLogger.logError("RClassScriptStruct.formatMainRClassInfo()准备读取"
					+ "主要RClass信息，但是包含RClass的Zip文件已被关闭，"
					+ "读取操作失败。请检查Zip文件：" + rClassZF.getName());
			return false;
		}
		
		
		rClassZF.getInputStream(mainScriptPath);
		
		return false;
	}

	/**
	 * 获取当前这个RClass脚本文件内部声明的外部导入RClass的路径。
	 * @return
	 * 		字符串数组，
	 * 		数组的每个元素都是一个RClass的路径，
	 * 		可以是Zip文件格式或者Jar文件格式的路径。
	 */
	public String[] getImportRClassPathArray(){
		return importRClassPaths;
	}
	
	/**
	 * 获取这个RClass脚本所定义的RClass的名字。
	 * @return
	 * 		这个RClass脚本所定义的RClass的名字。
	 */
	public String getMainRClassName(){
		return this.mainRClassName;
	}
	
	/**
	 * 通过这个RClass脚本来创建一个空白的CustomRClass对象，
	 * 这个CustomRClass只被初始化一个
	 * @return
	 */
	public CustomRClass makeRowRClass(){
		if (isScriptCorrect){
			return new CustomRClass(mainRClassName, 1);
		}
		return null;
	}
	
	/**
	 * 初始化父类和接口ID，
	 * 查看是否存在这些父类，
	 * 检查这个RClass脚本是否符合基本的语法，
	 * 例如执行出口是否都有连接，
	 * 发动的Function是否都存在……
	 * @return
	 * 		脚本正确返回true，
	 * 		存在错误返回false。
	 */
	public boolean isLegal(){
		//TODO
		isScriptCorrect = false;
		return isScriptCorrect;
	}
	
	/**
	 * 获取这个RClass的父类ID，
	 * 如果没有父类就返回0，
	 * 发动此方法前必须已经调用checkLegal()方法。
	 * @return
	 * 		这个RClass脚本定义的父类的RClassID，
	 * 		如果没有父类就返回0。
	 */
	public int getSuperID(){
		if (isScriptCorrect){
			return this.superRClassID;
		}
		return 0;
	}
	
	/**
	 * 获取所有父类接口的ID，
	 * 如果没有父类接口就返回一个长度为0的数组。
	 * 发动此方法前必须已经调用checkLegal()方法。
	 * @return
	 * 		这个RClass脚本显示声明的所有的接口的ID的数组，
	 * 		如果没有显示继承接口就返回长度为0的数组。
	 */
	public int[] getInterfaceIDArray(){
		if (isScriptCorrect){
			return this.superInterfaceIDs;
		}
		return new int[0];
	}
	
	/**
	 * 获取这个RClass脚本定义的静态成员的信息数组。
	 * @return
	 * 		一个专门重新创建的包含静态成员信息的数组，
	 * 		如果没有声明静态成员就返回一个长度为0的数组。
	 */
	public RReferenceInfo[] getStaticMemberInfoArray(){
		if (isScriptCorrect){
			//根据静态成员信息来创建RReferenceInfo数组。
			return RReferenceHelper.makeRRefArray(staticMemberInfos);
		}
		return new RReferenceInfo[0];
	}

	/**
	 * 获取这个RClass脚本定义的普通成员的信息数组。
	 * @return
	 * 		一个专门重新创建的包含普通成员信息的数组。
	 * 		如果没有有生命普通成员就返回长度为0的数组。
	 */
	public RReferenceInfo[] getNormalMemberInfoArray(){
		if (isScriptCorrect){
			//根据普通成员信息来创建RReferenceInfo数组。
			return RReferenceHelper.makeRRefArray(normalMemberInfos);
		}
		return new RReferenceInfo[0];
	}
	
	/**
	 * 返回这个RClass脚本所指向的ConstructFunction所定义的抽象FunctionMaker，
	 * 抽象FunctionMaker并没有包括内部的FunctionGraph，
	 * 之所以获取抽象FunctionMaker，是为了先在FunctionFactory中占好位置，
	 * 告诉RClass：“你有这么一个Function，需要这些执行入口、参数、执行出口、返回值，里面会调用啥，你现在不用管。”；
	 * 等到这个RClass的所有FunctionMaker都已经在FunctionFactory中占好了位置，
	 * 再去这些抽象FunctionMaker中填充FunctionGraph，
	 * 告诉RClass：“你的这个Function里面还需要调用你内部的另外一个Function，还有那个Function……”；
	 * 所以这里的抽象FunctionMaker就是为了方便调用RClass自身Function而存在的。
	 * @return
	 * 		抽象ConstructFunctionMaker。
	 */
	public IFunctionMaker getAbstractConstructFunctionMaker(){
		if (isScriptCorrect){
			return conFunScriptStruct.makeAbstractFunctionMaker();
		}
		return null;
	}
	
	/**
	 * 这个RClass脚本所定义的成员Function所对应的抽象FunctionMaker，
	 * 先用这个FunctionMaker来占位置，声明一个Function，
	 * 等全部的FunctionMaker都占好位置之后才会去填充FunctionGraph，
	 * 都是为了方便在这些成员Function内部调用自己的Function。
	 * @return
	 * 		抽象成员FunctionMaker。
	 */
	public IFunctionMaker[] getAbstractFunctionMakerArray(){
		if (isScriptCorrect){
			IFunctionMaker[] abstractFunctionMakerArray = 
					new IFunctionMaker[funScriptStructs.length];
			for (int i = 0; i < abstractFunctionMakerArray.length; ++i){
				abstractFunctionMakerArray[i] =
						funScriptStructs[i].makeAbstractFunctionMaker();
			}
		}
		return new IFunctionMaker[0];
	}
	
	/**
	 * 将RClass内部用于临时占位的抽象FunctionMaker替换为真正定义的Functionmaker。
	 * @param customRClass
	 * 		从本RClass脚本制造出来的customRClass对象。
	 * @return
	 * 		成功返回1，
	 * 		失败返回0。
	 */
	public int replaceAbstractFunction(CustomRClass customRClass){
		customRClass.replaceConstructFunctionMaker(
				conFunScriptStruct.makeFunctionMaker());
		
		CustomFunctionFactory custFunFactory = 
				customRClass.getCustomFunctionFactory();
		for (FunctionScriptStruct scriptStruct : funScriptStructs){
			custFunFactory.override(scriptStruct.makeFunctionMaker());
		}
		return 1;
	}
}
