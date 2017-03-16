package unfinishedClass.customRClass;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Hashtable;

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
	 * @param rClassFile 
	 * 		文件的绝对路径。
	 * @return 
	 * 		加载好了的RClass的ID。
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
					System.out.println(rClassToLoad.getName());
					
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
				RLogger.logError("加载JarRClass失败，已存在同名RClass对象。");
				return 0;
			}
			return loadJarRClass(rClassToLoad);
		}
		return 0;
	}
	
	
	
	/**
	 * 暂时废弃的方法，
	 * 加载CustomRClass统一通过工程文件进行加载，
	 * 详情见方法：RClassLoader.loadRWorkSpace(String workSpacePath)
	 * 此方法专门对应完全自定义的RClass，现在未完成，
	 * 已知文件是zip文件，从中加载RClass。
	 * @param rClassFile 
	 * 		文件的绝对路径。
	 * @return 
	 * 		加载好了的RClass的ID。
	 */
	public int loadRClassFromZip(String rClassPath){
		//TODO
		
		//从文件目录获取RClass的结构化信息，
		//包括引用的其他RClass的路径，RClass的成员信息，RClass的Function脚本的位置。
		//在这里要检查RClass的相关描述是否正确，
		//比如命名声明的Interface，但是却带有成员变量，
		//这种RClass是非法的，不允许创建，
		//直接返回null。
		RClassScriptStruct rClassScriptStruct = RClassScriptStruct.getScriptStruct(rClassPath);

		if (rClassScriptStruct == null){
			RLogger.logError("获取RClass脚本信息失败，"
					+ "无法加载CustomRClass，"
					+ "请检查CustomRClass的路径："
					+ rClassPath + "。");
			return 0;
		}
		
		//加载导入的其他RClass
		for (String importRClassPath : rClassScriptStruct.getImportRClassPathArray()){
			if (0 == loadRClassFrom(importRClassPath)){
				RLogger.logError("CustomRClass导入外部RClass失败，请检查导入RClass的路径："
						+ importRClassPath);
				return 0;
			}
		}
		
		//检查要加载的这个RClass是否与RClassLoader中的RClass有同名冲突。
		if (null == 
				nameToID.get(rClassScriptStruct.getMainRClassName()) ){
			RLogger.log("要加载的RClass与现有的RClass发生名字冲突，"
					+ "可能这个RClass已经被加载过，无需再加载；"
					+ "又或者纯粹的是名字冲突，无法加载同名RClass。"
					+ "\n请检查RClass的名字：" + rClassScriptStruct.getMainRClassName());
			return 1;
		}
		
		//检查RClass信息是否能够被加载，
		//比如需要的外部RClass是否存在，
		//Function是否完整，
		//构造Function是否包含了父类的构造Function，
		//调用的Function是否存在……
		if (rClassScriptStruct.isLegal()){
			CustomRClass rowRClass = rClassScriptStruct.makeRowRClass();
			int rClassID = idField.registZipRClass(rowRClass);
			nameToID.put(rowRClass.getName(), rClassID);
			rowRClass.setRClassID(rClassID);
			
			//请注意，这里在插入本类定义的FunctionMaker的时候插入的都是AbstractFunctionMaker，
			//让这个RClass先有这些Function的记录，
			//之后再去创建拥有实际功能的FunctionMaker时就能找到自己的Function。
			CustomRClassHelper.extendsRClass(
					rowRClass,
					rClassScriptStruct.getSuperID(),
					rClassScriptStruct.getInterfaceIDArray(), 
					rClassScriptStruct.getStaticMemberInfoArray(), 
					rClassScriptStruct.getNormalMemberInfoArray(), 
					rClassScriptStruct.getAbstractConstructFunctionMaker(), 
					rClassScriptStruct.getAbstractFunctionMakerArray());
			
			//此方法用来将rowRClass中的占位置的AbstractFunction覆盖成真正的FunctionMaker，
			//当然如果是抽象RClass的话，可能覆盖之后还是AbstractFunctionMaker。
			rClassScriptStruct.replaceAbstractFunction(rowRClass);
			return rClassID;
		}
		
		return 0;
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
			return loadRClassFromZip(rClassPath);
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
