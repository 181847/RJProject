package unfinishedClass.customRClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import basicTool.NamedItemList;
import basicTool.RLogger;
import rClass.RClassLoaderManager;

public class LoadCusRClassSequence extends NamedItemList {
	/**预先申请space个元素空间。
	 * @param space
	 * 		要初始化的空间数量。
	 */
	public LoadCusRClassSequence(int space){
		super(space);
	}
	
	/**
	 * 无参构造方法自动申请五个元素空间。
	 */
	public LoadCusRClassSequence(){
		super();
	}
	
	/**
	 * 向加载序列添加这个工程文件中指定名字的CustomRClass的脚本。
	 * @param projectFile
	 * 		工程文件。
	 * @param cusRClassName
	 * 		要添加到序列中的CustomRClass的全名，
	 * 		比如"myPackage.RClass1"。
	 * @return
	 * 		如果序列中或者RClassLoader中已经包含这个名字的CustomRClass，
	 * 		返回0；
	 * 		如果发生语法或者输入流错误，返回-1；
	 * 		否则返回1。
	 */
	public int join(ZipFile projectFile, String cusRClassName){
		if (had(cusRClassName)){
			return 0;
		}
		ZipEntry cusRClassScriptEntry = null;
		InputStream scriptIS = null;
		BufferedReader scriptBR = null;
		String scriptLine;
		
		//scriptLines第一行存储这个脚本信息从哪个工程文件的哪一个内部文件获得
		ArrayList<String> scriptLines;
		RClassScriptStruct thisScriptStruct;
		String[] parents;
		boolean occuredError = false;
		
		//获取脚本文件头
		cusRClassName = "src" + cusRClassName.replace('.', '/') + ".crc";
		cusRClassScriptEntry = 
				projectFile.getEntry(cusRClassName);
		if (cusRClassScriptEntry == null){
			RLogger.logError("工程加载序列添加加载脚本对象时无法获取指定的ZipEntry"
					+ "请检查工程文件：" + projectFile.getName()
					+ "。要获取的脚本路径：" + cusRClassName);
			occuredError = true;
		}
		
		//获取脚本文件输入流
		if ( ! occuredError){
			try {
				scriptIS = projectFile.getInputStream(cusRClassScriptEntry);
			} catch (IOException e) {
				RLogger.logError("工程加载序列获取脚本输入流发生IO异常，序列添加失败。");
				RLogger.logException(e);
				occuredError = true;
			}
			
			//读取脚本信息，用ArrayList存储直接的脚本信息
			if ( ! occuredError){
				scriptBR = new BufferedReader(new InputStreamReader(scriptIS));
				scriptLines = new ArrayList<String>();
				//添加脚本文件的主要位置信息
				scriptLines.add("工程文件：" + projectFile.getName() + "脚本位置：" + cusRClassName);
				
				try {
					while((scriptLine = scriptBR.readLine()) != null){
						scriptLines.add(scriptLine);
					}
				} catch (IOException e) {
					RLogger.logError("工程加载序列读取一个CustomRClass的脚本文件时发生IO异常，"
							+ "创建工程加载序列失败，"
							+ "请检查工程文件：" + projectFile.getName()
							+ "。要读取的脚本路径：" + cusRClassName);
					RLogger.logException(e);
					occuredError = true;
				}//提取脚本信息完毕
				
				//创建RClassScriptStruct对象
				if ( ! occuredError){
					thisScriptStruct = 
							RClassScriptStruct.getRClassScriptStruct(scriptLines);
					if (thisScriptStruct == null){
						RLogger.logError("工程加载序列无法根据脚本信息创建RClassScriptStruct，"
								+ "创建工程加载序列失败，"
								+ "请检查工程文件：" + projectFile.getName()
								+ "。要读取的脚本路径：" + cusRClassName);
						occuredError = true;
					}//生成脚本结构完毕
					
					//向加载序列添加本脚本的所有父类，包括接口父类的脚本结构对象
					if ( ! occuredError){
						parents = thisScriptStruct.getParents();
						for (int i = 0; i < parents.length && ! occuredError; ++i){
							if (-1 == join(projectFile, parents[i])){
								RLogger.logError("工程加载序列加载父类脚本结构失败。");
								occuredError = true;
							}
						}
						
						//向加载序列添加这个ScriptStruct对象
						if ( ! occuredError){
							append(thisScriptStruct);
						}
					}//if 	occuredError 加载序列添加父类
				}//if occuredError 创建RClassScriptStruct对象
			}//if occuredError 读取脚本信息，用ArrayList存储直接的脚本信息
		}//if occuredError 获取脚本文件输入流
		
		if (occuredError){
			return -1;
		}
		return 1;
	}

	/**
	 * 将当前这个RClassScriptStruct添加到加载序列当中，
	 * 这个方法直接对父类的成员进行修改。
	 * @param thisScriptStruct
	 * 		要添加的ScriptStruct对象。	
	 */
	private void append(RClassScriptStruct thisScriptStruct) {
		if (itemNum >= buffer.length){
			doubleExpendList();
		}
		buffer[++itemNum] = thisScriptStruct;
	}

	/**
	 * 检查指定名字的RClass是否已经存在于RClassLoader当中。
	 * @param cusRClassName
	 * 		被检查的RClass名字。
	 * @return
	 * 		如果当前加载序列或者RClassLoader中存在同名RClass，返回true；
	 * 		否则返回false。
	 */
	private boolean had(String cusRClassName) {
		if (getItem(cusRClassName) != null ||
				RClassLoaderManager
				.getRClassLoader()
				.getRClassIDOf(cusRClassName) != 0){
			return true;
		}
		return false;
	}
}
