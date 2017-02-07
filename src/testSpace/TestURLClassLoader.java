package testSpace;
import rClassInterface.IRClass;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

public class TestURLClassLoader {
	public static void main(String[] args){
		try{
			URL url = new URL("file:D:/tempFile/RObject.jar");
			URLClassLoader myClassLoader = new URLClassLoader(new URL[]{url}, Thread.currentThread().getContextClassLoader());
			System.out.println(myClassLoader);
			Class<?> myClass = myClassLoader.loadClass("unfinishedClass.RObject");
			IRClass rClass = (IRClass) myClass.newInstance();
			System.out.println(rClass);
			InputStream is = myClassLoader.getResourceAsStream("testResource.txt");
			System.out.println(is == null);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String s = "";
			while((s = br.readLine()) != null){
				System.out.println(s);
			}
			br.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
