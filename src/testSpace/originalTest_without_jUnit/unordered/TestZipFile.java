package testSpace.originalTest_without_jUnit.unordered;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class TestZipFile {

	public static void main(String[] args) {
		try{
			String filePath = "D:/tempFile/RObject.jar";
			ZipFile zf = new ZipFile(filePath);
			
			InputStream is = new FileInputStream(filePath);
			ZipInputStream zis = new ZipInputStream(is);
			ZipEntry ze = null;
			
			while ((ze = zis.getNextEntry()) != null){
				if (ze.isDirectory()){
					System.out.println(ze.getName());
					continue;
				} else {
					System.out.println(ze.getName());
					InputStreamReader isr = new InputStreamReader(zf.getInputStream(ze));
					BufferedReader br = new BufferedReader(isr);
					String s = "";
					while((s = br.readLine()) != null){
						System.out.println(s);
					}
				}
			}
			
			zis.close();
			zf.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		

	}

}
