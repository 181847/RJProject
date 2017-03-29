package unfinishedClass.customRClass.scriptChecker;

import java.util.ArrayList;

import unfinishedClass.customRClass.script.RClassScriptStruct;

/**
 * 专门为检查Function脚本的父类型Checker，
 * 用来检查ConstructFunction、
 * StaticFunction、
 * 普通Function、
 * 抽象Function。
 */
public class ConstructFunctionScriptChecker extends ScriptUnForceChecker {
	protected FunScriptCheckerConfig checkConfig;

	public ConstructFunctionScriptChecker() {
		super("ConstructFunction声明");
	}

	@Override
	protected int checkDetail(ArrayList<String> scriptLines, int checkLine) {
		boolean hitEnd;
		int scriptSize = scriptLines.size();
		String scriptLine = scriptLines.get(checkLine);
		
		do{
			hitEnd = false;
			//检查构造Function
			if (scriptLine.startsWith(checkConfig.getDeclaration())){
				++checkLine;
				
				//询问配置文件是否检查Excutee
				if (checkConfig.checkExcutee()){
					
				}
				
				//询问配置文件是否检查Parameter
				if (checkConfig.checkParameter()){
					//检查Function的Parameter
					checkLine = new ParameterScriptChecker().check(scriptLines, checkLine);
					if (checkLine < 0){
						return -1;
					}
				}
				
				//询问配置文件是否检查Returnval
				if (checkConfig.checkReturnval()){
					
				}
				
				//询问配置文件是否检查Excuter
				if (checkConfig.checkExcuter()){
					
				}
				
			}//if
			
			//如果程序正常执行到了这个地方，
			//那么hitEnd为true的可能只有当前的这次检查
			//检查到了checkConfig中需要
		} while (hitEnd && checkConfig.loopCheck());//do
		
		
		return checkLine;
	}

}
