package testSpace;
import rClassInterface.IRClass;
import testSpace.testTool.FunctionTester;
import unfinishedClass.RClassLoaderManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

/**
 * 测试从指定的绝对路径下读取jar文件，
 * 加载其中的RClass，这个jar文件中有一个RMETA-INF/RMANIFEST.txt文件，
 * 其中有一行记录着MainRClass，冒号空一格之后的信息，
 * 就是要从文件中加载RClass路径。
 * @author 75309
 */
public class TestURLClassLoader {
	public static void main(String[] args){
		RClassLoaderManager.prepareRClassLoader();
		
		String rClassPath = "D:/tempFile/RObject.jar";
		String manifestString = "";
		IRClass loadedRClass = null;
		boolean foundMainRClass = false;
		try{
			URL url = new URL("file:" + rClassPath);
			URLClassLoader jarRClassLoader = new URLClassLoader(new URL[]{url}, 
					Thread.currentThread().getContextClassLoader());
			
			InputStream isManifest = jarRClassLoader.getResourceAsStream("RMETA-INF/RMANIFEST.txt");
			if (isManifest == null){
				System.out.println("指定的Jar文件中没有找到 RMETA-INF/RMANIFEST.txt，文件路径："+ rClassPath);
				jarRClassLoader.close();
				return;
			}
			BufferedReader brManifest = new BufferedReader(new InputStreamReader(isManifest));
			
			while((manifestString = brManifest.readLine()) != null){
				if (manifestString.startsWith("MainRClass: ")){
					
					//12就是字符串 “MainRClass: ” 的长度，注意：包括最后的那个空格
					manifestString = manifestString.substring(12);
					
					Class<?> jarRClass = jarRClassLoader.loadClass(manifestString);
					loadedRClass = (IRClass) jarRClass.newInstance();
					System.out.println(loadedRClass.getName());
					
					foundMainRClass = true;
					break;
				}
			}
			
			if (!foundMainRClass){
				System.out.println("未能发现啊MainRClass信息，请检查jar文件中的RMETA-INF/RMANIFEST.txt是否有“MainRClass: ***.****.**”，文件路径：" + rClassPath);
			}
			brManifest.close();
			jarRClassLoader.close();
			
			//从RClass中获取一个Function并测试
			new FunctionTester(loadedRClass.Function("HelloWorldFunction")).test();
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
	}
}
