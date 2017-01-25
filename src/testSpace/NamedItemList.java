package testSpace;
import basicInterface.*;

public class NamedItemList implements INamedItemList{
	public INameable[] buffer;
	int itemNum;
	
	public NamedItemList(){
		buffer = null;
		itemNum = 0;
	}
	
	public NamedItemList(int space){
		init(space);
	}
	
	//为buffer数组分配space个空间，设置项目数为0
	public int init(int space){
		if (space < 1)
			return 0;
		buffer = new INameable[space];
		itemNum = 0;
		for(int i = 0; i < space; ++i){
			buffer[i] = null;
		}
		
		return 1;
	}

	
	//插入可命名项目
	@Override
	public int insertItem(INameable namedItem){
		if (namedItem == null)
			return 0;
		if (buffer == null){
			init(5);
		}
		
		int i;
		
		for (i = 0; i < itemNum; ++i){
			//如果存在名字相同的项
			//直接返回0，表示插入失败
			if (namedItem.getName().compareTo(buffer[i].getName()) == 0)
				return 0;
		}
		
		if (i >= buffer.length)
			//如果检查的结果大于buffer的长度
			//要增加bufffer的长度
			doubleExpendList();
			
		buffer[i] = namedItem;
		++ itemNum;
		
		return 1;
	}

	
	//通过名字得到可命名项
	@Override
	public INameable getItem(String name){
		int i;

		for (i = 0; i < itemNum; ++i){
			//如果存在名字相同的项
			//直接返回0，表示插入失败
			if (name.compareTo(buffer[i].getName()) == 0)
				return buffer[i];
		}
		
		return null;
	}
	
	//通过数组序号得到可命名项
	@Override
	public INameable getItem(int index){
		if (index < 0 || index > itemNum - 1)
			return null;
		else
			return buffer[index];
	}

	//删除指定名字的可命名项
	@Override
	public int deleteItem(String name){
		int i;

		for (i = 0; i < itemNum; ++i){
			//如果存在名字相同的项
			//跳出循环
			if (name.compareTo(buffer[i].getName()) == 0)
				break;
		}

		//未找到指定项
		if (i >= itemNum)
			return 0;
		else{
			--itemNum;
			for(;i < itemNum; ++i){
				buffer[i] = buffer[i + 1];
			}
			buffer[i] = null;
			return 1;
		}
		
	}
	
	public void doubleExpendList(){
		if (buffer == null)
			return;
		
		INameable[] tempBuffer = new INameable[buffer.length * 2];
		for(int i = 0; i < itemNum; ++i){
			tempBuffer[i] = buffer[i];
		}
		
		buffer = tempBuffer;
		tempBuffer = null;
	}
}
