package basicTool;
import basicInterface.*;


public class NamedItemList implements INamedItemList{
	public INameable[] buffer;
	/**
	 * 已经存储的元素的数量。
	 */
	public int itemNum;
	
	/**
	 * 无参构造方法自动申请五个元素空间
	 * 这种列表会在空间不够时自动重新申请两倍的空间
	 */
	public NamedItemList(){
		init(5);
	}
	
	/**
	 * @param space
	 * 		初始化的空间数量。
	 */
	public NamedItemList(int space){
		init(space);
	}
	
	/**
	 * 为buffer数组分配space个空间，设置项目数为0
	 * @param space 可放置元素的空间数量
	 * @return 如果分配的空间小于等于0，返回0表示失败
	 */
	public int init(int space){
		if (space <= 1){
			space = 5;
		}
		buffer = new INameable[space];
		itemNum = 0;
		
		return 1;
	}
	
	/**
	 * 将命名列表的总长度增加到原来的两倍
	 */
	public void doubleExpendList(){
		INameable[] tempBuffer = new INameable[buffer.length * 2];
		
		for(int i = 0; i < itemNum; ++i){
			tempBuffer[i] = buffer[i];
		}
		buffer = tempBuffer;
		tempBuffer = null;
	}
	
	/**
	 * 返回存储了多少个对象，
	 * @return 可命名列表的元素数量。
	 */
	@Override
	public int getNum(){
		return itemNum;
	}

	
	/**
	 * 插入一个可命名对象，用int返回插入的结果。
	 * @param namedItem 要插入命名列表的对象。
	 * @return 如果插入的元素为null返回0；
	 * 如果插入成功就返回1；
	 * 如果已存在相同名字的元素就返回2。
	 */
	@Override
	public int insertItem(INameable namedItem){
		if (namedItem == null){
			return 0;
		}
		
		int i;
		for (i = 0; i < itemNum; ++i){
			if (namedItem.getName().compareTo(buffer[i].getName()) == 0){
				return 2;
			}
		}
		
		//如果检查的结果大于buffer的长度
		//要增加bufffer的长度
		if (i >= buffer.length){
			doubleExpendList();
		}
		buffer[i] = namedItem;
		++ itemNum;
		
		return 1;
	}

	
	/**
	 * 通过名字得到一个可命名对象，
	 * @param name 想要获取的命名对象的名字。
	 * @return 指定名字的元素，
	 * 如果没有找到就返回null。
	 */
	@Override
	public INameable getItem(String name){
		for (int i = 0; i < itemNum; ++i){
			if (name.compareTo(buffer[i].getName()) == 0){
				return buffer[i];
			}
		}
		return null;
	}
	
	/**
	 * 通过序号得到一个可命名对象。
	 * @param index 想要获取的命名对象的在命名列表中的序号。
	 * @return 指定序号的元素，
	 * 如果没有找到就返回null。
	 */
	@Override
	public INameable getItem(int index){
		if (index < 0 || index > itemNum - 1){
			return null;
		} else {
			return buffer[index];
		}
	}

	/**
	 * 删除一个可命名对象，
	 * @param name 想要删除的的命名对象的名字。
	 * @return 未找到指定名称的可命名对象返回-1。
	 * 成功则返回1。
	 */
	@Override
	public int deleteItem(String name){
		if (name == null){
			return 0;
		}
		
		int i;
		//如果存在名字相同的项，跳出循环
		for (i = 0; i < itemNum; ++i){
			if (name.compareTo(buffer[i].getName()) == 0){
				break;
			}
		}
		
		//未找到指定项
		if (i >= itemNum){
			return -1;
		} else {
			
			--itemNum;
			for(;i < itemNum; ++i){
				buffer[i] = buffer[i + 1];
			}
			buffer[i] = null;
			return 1;
		}
		
	}
	
	/**
	 * 返回指定可命名对象的序号。
	 * @param name 指定的名字
	 * @return 成功返回大于等于0的数字，
	 * 失败返回-1。
	 */
	@Override
	public int getIndexOf(String name) {
		int i;
		
		for (i = 0; i < itemNum; ++i){
			if (name.compareTo(buffer[i].getName()) == 0){
				return i;
			}
		}
		return -1;
	}
}
