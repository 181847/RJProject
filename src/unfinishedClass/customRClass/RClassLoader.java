package unfinishedClass.customRClass;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import basicTool.RLogger;
import rClass.RClassIDField;
import rClass.RClassLoaderManager;
import rClassInterface.IRClass;
import rClassInterface.IRClassLoader;

/**
 * 本工程的核心，
 * 放置所有运行时需要的RClass对象，
 * 每个RClass对象都分配了一个独一无二的RClassID。
 */
public class RClassLoader implements IRClassLoader{
	public static String jarBufferPath = "jarBufferFolder";
	public RClassIDField idField;
	public Hashtable<String, Integer> nameToID;

	/**
	 * 带参构造方法，初始化IDField和Hashtable的空间，
	 * Hashtable的Capacity为IDField的正负空间数量之和。
	 * @param positiveSpace
	 * 		IDField的正空间数量，存储基本数据类型RClass以及Java包装类型RClass。
	 * @param negativeSpace 
	 * 		IDField的负空间数量，存储完全自定义RClass。
	 * @param loadFactor 
	 * 		Hashtable的装载因子。
	 */
	public RClassLoader(int positiveSpace, int negativeSpace, float loadFactor){
		idField = new RClassIDField(positiveSpace, negativeSpace);
		nameToID = new Hashtable<String, Integer>(positiveSpace + negativeSpace, loadFactor);
	}
	
	
	/**
	 * 将一个RClass实例对象装载进入RClassLoader，
	 * 并建立RClass的名字到RClassID的联系。
	 * @param loadingRClass 
	 * 		要装载的RClass类型对象。
	 * @return 
	 * 		为RClass注册的RClassID。
	 */
	public int loadJarRClass(IRClass rClassToLoad){
		if (rClassToLoad == null){
			System.out.println("错误，在方法loadRClass(IRClass) 中，参数rClassToload为null");
		}
		
		System.out.print("已获得RClass实例对象，RClass名字：" + rClassToLoad.getName() + "，准备装载....");
		int ID = idField.registJarRClass(rClassToLoad);
		if (ID <= 0){
			System.out.println("\n注册RClassID失败，RClass名字：" + rClassToLoad.getName());
			return ID;
		}
		System.out.println("装载成功");
		
		nameToID.put(rClassToLoad.getName(), ID);
		rClassToLoad.setRClassID(ID);
		rClassToLoad.loadFunction();
		return ID;
	}
	
	/**
	 * 已知文件是jar文件，从中加载RClass，
	 * 从指定的绝对路径下读取jar文件，
	 * 加载其中的RClass，这个jar文件中有一个RMETA-INF/RMANIFEST.txt文件，
	 * 其中有一行记录着MainRClass，冒号空一格之后的信息，
	 * 就是要从文件中加载RClass路径。
	 * @param rClassPath 
	 * 		文件的绝对路径。
	 * @return 
	 * 		加载好了的RClass的ID，
	 * 		已存在同名JarRClass返回-1，
	 * 		加载失败返回0。
	 */
	public int loadRClassFromJar(String rClassPath){
		String manifestString = "";
		IRClass rClassToLoad = null;
		boolean foundMainRClass = false;
		try{
			URL url = new URL("file:" + rClassPath);
			URLClassLoader jarRClassLoader = new URLClassLoader(new URL[]{url}, 
					Thread.currentThread().getContextClassLoader());
			
			InputStream isManifest = jarRClassLoader.getResourceAsStream("RMETA-INF/RMANIFEST.txt");
			if (isManifest == null){
				System.out.println("指定的Jar文件中没有找到 RMETA-INF/RMANIFEST.txt，文件路径："+ rClassPath);
				jarRClassLoader.close();
				return 0;
			}
			BufferedReader brManifest = new BufferedReader(new InputStreamReader(isManifest));
			while((manifestString = brManifest.readLine()) != null){
				if (manifestString.startsWith("MainRClass: ")){
					//12就是字符串 “MainRClass: ” 的长度，注意：包括最后的那个空格
					manifestString = manifestString.substring(12);
					Class<?> jarRClass = jarRClassLoader.loadClass(manifestString);
					rClassToLoad = (IRClass) jarRClass.newInstance();
					foundMainRClass = true;
					break;
				}
			}
			if (!foundMainRClass){
				System.out.println("未能发现啊MainRClass信息，请检查jar文件中的RMETA-INF/RMANIFEST.txt是否有“MainRClass: ***.****.**”，文件路径：" + rClassPath);
			}
			brManifest.close();
			jarRClassLoader.close();
			
		} catch (MalformedURLException e) {
			System.out.println("Jar包文件路径不正确，无法创建URL，文件路径：" + rClassPath);
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			System.out.println("没有发现指定的Class，文件路径：" + rClassPath
					+ "\t将要加载的类名为：" + manifestString);
			e.printStackTrace();
			
		} catch (InstantiationException e) {
			System.out.println("要加载的类可能为抽象类、接口、数组类、原始类型、void类型，或者该类没有无参构造方法：\n"
					+ "文件路径：" + rClassPath + "\t要加载的类：" + manifestString);
			e.printStackTrace();
			
		} catch (IllegalAccessException e) {
			System.out.println("反射创建RClass的Java类实例失败，文件路径：" + rClassPath 
					+ "\t要加载的类：" + manifestString);
			e.printStackTrace();
			
		} catch (IOException e) {
			System.out.println("RMETA-INF/RMANIFEST.txt文件读取错误，或者文件关闭时发生错误，文件路径：" + rClassPath);
			e.printStackTrace();
		}
		
		if (foundMainRClass){
			if (nameToID.get(rClassToLoad.getName()) == null){
				return loadJarRClass(rClassToLoad);
			}
			RLogger.logError("加载JarRClass失败，已存在同名RClass对象。");
			return -1;
		}
		return 0;
	}
	
	/**
	 * 加载工程文件。
	 * @param rClassPath
	 * 		以.zip结尾的Zip压缩文件路径。
	 * @return
	 * 		加载成功返回1，
	 * 		失败返回0。
	 */
	public int loadProject(String rClassPath) {
		ZipFile projectFile = null;
		//保存所有JarRClass的描述信息
		ArrayList<String> jarRClassDeclarations = 
				new ArrayList<String>();
		//保存所有CustomRClass的声明
		ArrayList<String> cusRClassDeclarations = 
				new ArrayList<String>();
		BufferedReader rManifestBR = null;
		ZipEntry rManifestEntry = null;
		String rManifestLine;
		boolean occuredError = false;
		
		try{
			projectFile = new ZipFile(rClassPath);
		} catch(ZipException e){
			RLogger.logError("RClassLoader加载工程文件，"
					+ "目标路径不是Zip文件格式，"
					+ "请检查工程文件：" + rClassPath + "。");
			RLogger.logException(e);
			occuredError = true;
			
		} catch (IOException e){
			RLogger.logError("RClassScriptStruct获取 ZipFile 时发生IO异常，"
					+ "请检查工程文件：" + rClassPath + "。");
			RLogger.logException(e);
			occuredError = true;
			
		} catch (SecurityException e){
			RLogger.logError("RClassScriptStruct获取 ZipFile 时发生 安全性 异常，"
					+ "加载工程文件失败，"
					+ "请检查工程文件：" + rClassPath + "。");
			RLogger.logException(e);
			occuredError = true;
		}
		
		//获取工程描述文件头
		if ( ! occuredError){
			try{
				rManifestEntry = projectFile.getEntry("RMETA-INF/RMANIFEST.txt");
			} catch (IllegalStateException e){
				RLogger.logError("获取工程文件中的\"RMETA-INF/RMANIFEST.txt\" 时，Zip文件"
						+ "已被关闭，加载工程文件失败，"
						+ "请检查工程文件：" + rClassPath + "。");
				RLogger.logException(e);
			}
			if (rManifestEntry == null){
				RLogger.logError("被加载的工程文件中不存在的\"RMETA-INF/RMANIFEST.txt\"，"
						+ "加载工程文件失败，"
						+ "请检查工程文件：" + rClassPath + "。");
				occuredError = true;
			}//工程描述文件头完毕
			
			//获取工程描述文件输入流
			if ( ! occuredError){
				
				try {
					rManifestBR = new BufferedReader(
							new InputStreamReader(
									projectFile.getInputStream(rManifestEntry)));
				} catch (IOException e) {
					RLogger.logError("加载工程文件，获取\"RMETA-INF/RMANIFEST.txt\"文件的输入流时发生异常，"
							+ "加载工程文件失败，请检查工程文件：" + rClassPath);
					RLogger.logException(e);
				}//获取工程描述文件输入流完毕
				
				//读取描述文件中有关JarRClass和CustomRClass的信息
				if ( ! occuredError){
					try {
						while((rManifestLine = rManifestBR.readLine()) != null){
							if (rManifestLine.startsWith("JarRClass: ")){
								rManifestLine = rManifestLine.substring(11).replace('.', '/');
								jarRClassDeclarations
									.add("src/" + rManifestLine + ".jar");
								continue;
							}
							if (rManifestLine.startsWith("CustomRClass: ")){
								cusRClassDeclarations
									.add(rManifestLine.substring(14));
							}
						}//while
					} catch (IOException e) {
						RLogger.logError("加载工程文件，读取\"RMETA-INF/RMANIFEST.txt\"文件时发生异常，"
								+ "加载工程文件失败，请检查工程文件：" + rClassPath);
						RLogger.logException(e);
						occuredError = true;
					}//读取描述文件完毕
					
					//加载指定的工程文件中的JarRClass
					if ( ! occuredError){
						if (1 != loadJarRClassInProject(projectFile, jarRClassDeclarations)){
							RLogger.logError("加载工程文件中的JarRClass发生错误，"
									+ "导致加载工程文件失败，"
									+ "未能加载剩余的JarRClass和CustomRClass。");
							occuredError = true;
						}//加载JarRClass完毕
						
						//加载指定的工程文件中的CustomRClass
						if ( ! occuredError){
							if (1 != loadCusRClassInProject(projectFile, cusRClassDeclarations)){
								RLogger.logError("加载工程文件中的CustomRClass发生错误，"
										+ "导致加载工程文件失败。");
								occuredError = true;
							}//加载CustomRClass完毕
						}//if occuredError 加载CustomRClass
					}//if occuredError 加载JarRClass
				}//if occuredError 读取JarRClass、CustomRClass声明
			}//if occuredError 工程描述文件输入流
		}//if occuredError 工程描述文件头
		
		//关闭工程描述文件输入流
		try{
			if (rManifestBR != null){
				rManifestBR.close();
			}
		} catch (IOException e){
			RLogger.logError("加载工程文件结尾关闭rManifestBR输入流时发生IO异常。");
			RLogger.logException(e);
		}//关闭输入流完毕
		
		//关闭工程文件
		try{
			if (projectFile != null){
				projectFile.close();
			}
		} catch (IOException e){
			RLogger.logError("加载工程文件结尾关闭Zip文件时发生IO异常。");
			RLogger.logException(e);
		}//关闭工程文件完毕
		
		//检查错误发生情况
		if (occuredError){
			return 0;
		} else {
			return 1;
		}
	}
	
	/**
	 * 加载工程文件中的CustomRClass。
	 * @param projectFile
	 * 		工程文件。
	 * @param cusRClassDeclarations
	 * 		关于此工程文件中所有CustomRClass的名字，
	 * 		注意这里的每一项都是形如“myPacakge.RClass1”这样的RClass全名。
	 * @return
	 * 		成功返回1，
	 * 		失败返回0。
	 */
	private int loadCusRClassInProject(ZipFile projectFile, ArrayList<String> cusRClassDeclarations) {
		// TODO Auto-generated method stub
		LoadCusRClassSequence sequence = new LoadCusRClassSequence();
		CustomRClass cusRClassArray[];
		for (int i = cusRClassDeclarations.size(); i > 0; --i){
			sequence.append(projectFile, cusRClassDeclarations.get(i - 1));
		}
		
		//按照先父类，后子类的方法进行脚本文件的排序
		sequence.organize();
		//在这个方法中对RClass的脚本进行声明检查，
		//比如声明的父类必须存在，
		//接口不能声明成员等等错误都要在这里检查出来
		sequence.checkAllRClass();
		//从加载序列生成CustomRClass实例对象，
		//这些生成的CustomRClass对象只初始化了名称信息。
		sequence.makeCusRClass();
		//注册RClassID
		sequence.registRClassID(cusRClassArray);
		//初始化成员信息
		sequence.initMember(cusRClassArray, sequence);
		//陈列抽象Function
		sequence.initAbstractFunctionMaker(cusRClassArray, sequence);
		//实现抽象Function
		sequence.overriedAbstractFunctionMaker(cusRClassArray, sequence);
		return 1;
	}


	/**
	 * 从工程文件中加载所有声明的JarRClass。
	 * @param projectFile
	 * 		Zip格式的工程文件。
	 * @param jarRClassDeclarations
	 * 		所有JarRClass的.jar文件在Zip文件中的位置。
	 * @return
	 * 		成功返回1，
	 * 		失败返回0。（注意：要加载的JarRClass的名字和本地RClassLoader中的冲突的话，不算做失败，这相当于已经加载了这个JarRClass。）
	 */
	private int loadJarRClassInProject(ZipFile projectFile, ArrayList<String> jarRClassDeclarations) {
		ZipEntry jarEntry = null;
		FileOutputStream jarOutputStream = null;
		InputStream jarInputStream = null;
		int bufferByte;
		boolean occuredError = false;
		int jarNum = jarRClassDeclarations.size();
		
		for (int i = 1; i <= jarNum && ! occuredError; ++i){
			
			//创建解压缩Jar文件输出流
			if ( ! occuredError){
				try {
					jarOutputStream = 
							new FileOutputStream(jarBufferPath + "/jarRClassToLoad.jar");
				} catch (FileNotFoundException e) {
					RLogger.logError("加载工程文件中的JarRClass时，"
							+ "无法再外部创建临时Jar文件，"
							+ "加载工程失败，"
							+ "请检查工程文件：" + projectFile.getName());
					RLogger.logException(e);
					occuredError = true;
				}//外部解压缩文件输出流创建完毕
				
				//获取解压缩文件头
				if ( ! occuredError){
					jarEntry = projectFile.getEntry(jarRClassDeclarations.get(i - 1));
					if (jarEntry == null){
						RLogger.logError("未能获得工程文件中的Jar文件的ZipEntry，"
								+ "加载工程文件失败，"
								+ "要加载的JarRClass的目标路径是：" + jarRClassDeclarations.get(i - 1)
								+ "请检查工程文件：" + projectFile.getName());
						occuredError = true;
					}//获取工程文件解压缩文件头完毕
					
					//获取解压缩Jar文件输入流
					if ( ! occuredError){
						try {
							jarInputStream = projectFile.getInputStream(jarEntry);
						} catch (IOException e) {
							RLogger.logError("加载工程文件中的JarRClass时发生IOException，"
									+ "加载工程失败，"
									+ "要加载的JarRClass的目标路径是：" + jarEntry.getName()
									+ "请检查工程文件：" + projectFile.getName());
							RLogger.logException(e);
							occuredError = true;
						}//获取解压缩Jar文件输入流完毕
						
						//解压缩Jar文件
						if ( ! occuredError){
							try {
								while((bufferByte = jarInputStream.read()) != -1){
									jarOutputStream.write(bufferByte);
								}
							} catch (IOException e) {

								RLogger.logError("解压缩工程文件中的JarRClass时发生IOException，"
										+ "加载工程失败，"
										+ "要解压的JarRClass的目标路径是：" + jarEntry.getName()
										+ "请检查工程文件：" + projectFile.getName());
								RLogger.logException(e);
								occuredError = true;
								break;
							}//解压缩Jar文件完毕
							
							//加载被解压缩的Jar文件
							if ( ! occuredError){
								if (loadRClassFromJar(jarBufferPath + "/jarRClassToLoad.jar")
										> 0){
									RLogger.log("成功加载工程文件中第" + i + "个JarRClass。");
								} else {
									RLogger.log("加载工程文件中第" + i + "个JarRClass失败。"
											+ "请检查工程："+ projectFile.getName()
											+ "的这个.jar文件：" + jarEntry.getName());
								}
							}//加载被解压缩文件完毕
						}//if occuredError 解压缩Jar文件
					}//if occuredError 获取解压缩Jar文件输入流
				}//if occuredError 获取解压缩文件头
			}//if occuredError 创建解压缩Jar文件输出流
			
			//关闭解压缩Jar文件输出流
			if (jarOutputStream != null){
				try {
					jarOutputStream.close();
					jarOutputStream = null;
				} catch (IOException e) {
					RLogger.logError("加载工程文件中Jar文件完毕时，关闭Jar输出流失败。");
					RLogger.logException(e);
					occuredError = true;
				}
			}//关闭输出流完毕
			
			//关闭解压缩Jar文件输出流
			if (jarInputStream != null){
				try {
					jarInputStream.close();
					jarInputStream = null;
				} catch (IOException e) {
					RLogger.logError("加载工程文件中Jar文件完毕时，关闭Jar输入流失败。");
					RLogger.logException(e);
					occuredError = true;
				}
			}//关闭输入流完毕
		}//*********************************while循环加载Jar文件*****************************
		
		if (occuredError){
			return 0;
		}
		return 1;
	}

	/**
	 * 从指定的文件读取rClass，要求文件的名字就是RClass的名字。
	 * @param rClassPath 文件的绝对路径。
	 * @return 为RClass注册的RClassID。
	 */
	public int loadRClassFrom(String rClassPath) {
		if (rClassPath.endsWith(".jar"))
		{
			return loadRClassFromJar(rClassPath);
			
		} else if (rClassPath.endsWith(".zip")){
			return loadProject(rClassPath);
		}
		return 0;
	}

	/**
	 * 检查两个RClass的相互关系是怎样的，
	 * 现阶段只考虑基本数据类型相等的情况。
	 * @param rClassA RClass类型A，可以用来检查是否为父类。
	 * @param rClassB RClass类型B，可以用来检查是否为子类。
	 * @return 现阶段只考虑相等情况，相等返回1；
	 * 不等返回Integer的最大值。
	 */
	public int checkRClassMatchType(String rClassA, String rClassB) {
		if (rClassA.compareTo(rClassB) == 0){
			return 1;
		}
		return Integer.MAX_VALUE;
	}
	
	/**
	 * 通过RClassID的情况检查两个RClass类型的关系，
	 * 目前只有在都是正数，且相等的情况下返回1，其他情况下返回0。
	 * @param rClassAID RClassID
	 * @param rClassBID 另一个RClassID
	 * @return 检查结果，相同且都为Java包装类，返回1；
	 * 否则返回0。
	 */
	public int checkRClassMatchType(int rClassAID, int rClassBID){
		if (rClassAID >= 0){
			if (rClassAID == rClassBID){
				return 1;
			}
		}
		return 0;
	}

	/**
	 * 获得一个RClass类型实例。
	 * @param rClass 要获取的RClas的名字。
	 * @return RClass的类型实例引用。
	 */
	public IRClass getRClass(String rClass) {
		Integer returnedID = nameToID.get(rClass);
		
		if (returnedID == null){
			return null;
		}
		return idField.getRClass((int)returnedID);
	}
	
	/**
	 * 获得一个RClass类型实例。
	 * @param rClassID RClassID。
	 * @return RClass的类型实例引用。
	 */
	@Override
	public IRClass getRClass(int rClassID) {
		return idField.getRClass(rClassID);
	}
	
	/**
	 * 查看这个RClass的注册ID，1至9代表基本数据类型。
	 * @param rClass rClass的名字。
	 * @return 粗略类型标识，
	 * 10以及10以上的是Java包装类；
	 * 负数代表完全自定义RClass；
	 * 0代表空类型；
	 * 当前只考虑9种基本数据类型。
	 */
	public int getRClassIDOf(String rClass){
		switch(rClass.charAt(0)){
		case 'B':
			if (rClass.compareTo("Byte") == 0)
				return 1;
			if (rClass.compareTo("Boolean") == 0)
				return 2;
			break;
		case 'C':
			if (rClass.compareTo("Character") == 0)
				return 8;
			break;
		case 'D':
			if (rClass.compareTo("Double") == 0)
				return 7;
			break;
		case 'F':
			if (rClass.compareTo("Float") == 0)
				return 6;
			break;
		case 'L':
			if (rClass.compareTo("Long") == 0)
				return 5;
			break;
		case 'I':
			if (rClass.compareTo("Integer") == 0)
				return 4;
			break;
		case 'S':
			if (rClass.compareTo("Short") == 0)
				return 3;
			if (rClass.compareTo("String") == 0)
				return 9;
			break;
		}
		
		Integer returnedID = nameToID.get(rClass);
		
		if (returnedID == null){
			return 0;
		}
		return (int)returnedID;
	}
}
