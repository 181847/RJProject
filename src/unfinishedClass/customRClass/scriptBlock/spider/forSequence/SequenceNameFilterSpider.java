package unfinishedClass.customRClass.scriptBlock.spider.forSequence;

import java.util.Hashtable;

import rClass.RClassLoaderManager;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.RClassStructInformation;
import unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider.DeleteSpider;
import unfinishedClass.customRClass.struct.RClassStruct;

/**
 * RClass冲突类名剔除，
 * 这一步保证加载序列中的类名不能与现有的RClassLoader中的类名冲突，
 * 并且加载序列内部也不能有重名，
 * 删除掉任何重名的targetBlock。
 */
public class SequenceNameFilterSpider extends DeleteSpider {
	public static int loadCapacity = 20;
	
	//哈希表存储RClass的名字，用于检查命名冲突。
	protected Hashtable<String, Object> nameTable;

	public SequenceNameFilterSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		nameTable = new Hashtable<String, Object>(loadCapacity);
	}

	@Override
	protected void dealWithTargetBlock() {
		//获取RClassStruct的名字。
		String name = 
				( (RClassStructInformation)
						targetBlock.getInformation())
				.getRClassStruct()
				.getName();
		
		if (0 != RClassLoaderManager
				.getRClassLoader().getRClassIDOf(name)	//在RClassLoader中搜索类名
				|| nameTable.containsKey(name)){		//在本加载序列中搜索已记录类名
			//RClassLoader或者加载序列中发现重名的记录，
			//则当前RClassStruct不允许加载，
			//删除targetBlock。
			deleteTarget = true;
		} else {
			nameTable.put(name, null);
		}
	}

}
