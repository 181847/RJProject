package testSpace.testTool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 提供一个从指定文本文件中获取所有脚本信息的功能。
 */
public class ScriptPicker {
	/**
	 * 从指定的文本文件读取信息，存储到一个ArrayList中。
	 * @param path
	 * 		文本文件路径。
	 * @return
	 * 		每一行文本信息作为一个数组元素，
	 * 		固定第一行存储的就是path中的字符串。
	 */
	public static ArrayList<String> pickScriptFrom(String path) {
		String bufferString = null;
		//用于存储脚本信息的数组。
		ArrayList<String> scriptArray = null;
		BufferedReader fileReader;
		File scriptFile = new File(path);
		
		if ( ! scriptFile.isFile()){
			//防止读取空文件。
			return null;
		}
		
		try{
			fileReader = new BufferedReader(
							new InputStreamReader(
							new FileInputStream(scriptFile)));
			scriptArray = new ArrayList<String>();
			
			//添加路径信息。
			scriptArray.add(path);
			
			//读取脚本中的所有信息。
			while(null != (bufferString = fileReader.readLine())){
				scriptArray.add(bufferString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return scriptArray;
	}
}
