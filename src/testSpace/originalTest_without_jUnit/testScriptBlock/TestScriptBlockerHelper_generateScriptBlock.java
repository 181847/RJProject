package testSpace.originalTest_without_jUnit.testScriptBlock;

import java.util.ArrayList;

import basicTool.RLogger;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptBlockHelper;
import unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider.ScriptPrintSpider;

public class TestScriptBlockerHelper_generateScriptBlock {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> script = new ArrayList<String>();

		script.add("脚本文件属性");
		script.add("Type:");
		script.add("\tAbstractRClass");
		script.add("Name:");
		script.add("\tcom.github.liuyang.RClassDemo");
		script.add("Extends:");
		script.add("\tdefault.myPackage.RClass4");
		script.add("Implements:");
		script.add("\tmyPackage.Interface1");
		script.add("\tmyPackage.Interface2");
		script.add("Member:");
		script.add("\tStatic:");
		script.add("\t\tmyPackage.RClass2 staticMember1");
		script.add("\t\tmyPackage.RClass3 staticMember2");
		script.add("\tbasic.Integer member3 -12");
		script.add("\tdefault.myPackage.RClass4 member4");
		script.add("ConstructFunction:");
		script.add("\tParameter:");
		script.add("\t\tmyPackage.RClass2 parameter1");
		script.add("\t\tbasic.Integer parameter2");
		script.add("\tLocalVar:");
		script.add("\t\tStatic:");
		script.add("\t\t\tmyPackage.RClass2 staticLocalVar1");
		script.add("\t\t\tmyPackage.RClass2 staticLocalVar2");
		script.add("\t\tbasic.Integer localVar3 = 12");
		script.add("\tSubFunction:");
		script.add("\t\tmyPackage.RClass1.fun");
		script.add("\t\tbasic.Integer.addInteger{3}");
		script.add("\t\tbasic.Integer.divide");
		script.add("\t\tmyPackage.RClass1.RClass1");
		script.add("\tArcs:");
		script.add("\t\tEtoE: 1.funEnd -> 4.construct");
		script.add("\t\tRtoP: 2.result->3.by");
		script.add("\tFunctionLocation:");
		script.add("\t\t1 -> 12.5,7.77");
		script.add("\t\t2 -> -4,-7");
		script.add("\t\t3 -> 7,0");
		script.add("\t\t4 -> 8.12,0.44");
		script.add("\tComments:");
		script.add("\t\t(55.1, 22.1) -> (21.0, 50.1)");
		script.add("\t\t\t注释信息二第一行");
		script.add("\t\t\t注释信息二第二行");
		script.add("\t\t(-1, -2) -> (12, 11)");
		script.add("\t\t\t另一个注释信息第一行");
		script.add("\t\t\t另一个注释信息第二行");
		script.add("StaticFunction: staticFunctionName");
		script.add("StaticFunction: anotherStaticFunctionName");
		script.add("Function: functionName");
		script.add("AbstractFunction: abstractFunctionName");
		
		ScriptBlock scriptBlock = 
				ScriptBlockHelper.generateScriptBlock(script);
		
		new ScriptPrintSpider(scriptBlock)
			.workUntilEnd();
		
		System.out.println(scriptBlock.toString());
		RLogger.log("测试结束");
	}

}
