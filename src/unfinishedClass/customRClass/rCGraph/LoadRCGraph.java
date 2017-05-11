package unfinishedClass.customRClass.rCGraph;

import java.util.Hashtable;

import rClass.RClassLoaderManager;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.RClassStruct;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.Set;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.tool.Struct;

/**
 * 加载时继承图，
 * 继承于RCGraph，
 * 实现有关继承图最多的方法：
 * 插入节点，连接弧线，检查继承错误。
 * 相当于工具类的一种存在，
 * 为RuntimeRCGraph提供服务和桥梁。
 */
public class LoadRCGraph extends RCGraph{
	Hashtable<String, Integer> nameToIndex;
	
	public LoadRCGraph(int vNum){
		vertics = new RCGNode[vNum];
		vCount = 0;
	}

	/**
	 * 插入一个RClassStruct对象，
	 * 为其创建一个结点。
	 * @param rClassStruct
	 * 		被插入的RClass信息结构。
	 */
	public void insert(RClassStruct rClassStruct) {
		//确保空间足够，
		//空间不足时自动扩充。
		ensureCapacity();
		vertics[vCount] = 
				new RCGNode(rClassStruct);
		//记录下对应的序号
		nameToIndex.put(rClassStruct.getName(), vCount++);
	}

	/**
	 * 通过此方法来确保空间足够，
	 * 空间不足时自动扩充。
	 */
	private void ensureCapacity() {
		if (vCount >= vertics.length){
			//申请两倍于原来的长度的空间
			RCGNode[] buffer = new RCGNode[vCount * 2];
			//转移对象
			for (int index = 0; index < vCount; ++index){
				buffer[index] = vertics[index];
				vertics[index] = null;
			}
			vertics = buffer;
		}
	}

	/**
	 * 根据每个RClass内部的继承信息，
	 * 自动生成弧线来表示继承的方向。
	 */
	public void autoLink() {
		//为vertics中的每个RCGNode自动创建弧线。
		for (int vIndex = 0; vIndex < vCount; ++vIndex){
			createArcFor(vertics[vIndex]);
		}
	}

	/**
	 * 为指定的结点创建到本图或者运行时继承图中结点的继承弧线。
	 * @param rCGNode
	 * 		被连接弧线的结点，
	 * 		要求这个结点应该在本图当中。
	 */
	protected void createArcFor(RCGNode rCGNode) {
		RClassStruct rClassStruct = 
				rCGNode.getRClassStruct();
		//存储父类序号的值，
		//正数代表父类在本图中，
		//负数代表父类在运行时继承图中。
		//0表示没有在任何位置找到父类。
		int index;
		
		//检查非接口父类信息是否存在，
		//如果存在就尝试向其创建继承弧线。
		Struct father = rClassStruct.getExtend();
		if (father != null){
			index = searchFor(father.getName());
			if (index != 0){
				rCGNode.linkOut(new ExtArc(index));
			}
		}

		//检查接口父类信息是否存在，
		//如果存在就尝试向其创建继承弧线。
		Set implementSet = rClassStruct.getImplementSet();
		if (implementSet != null){
			//遍历接口父类集合中的每一个元素。
			for (int i = implementSet.getNum();
					i >= 0; --i){
				father = implementSet.getStruct(i);
				if (father != null){
					index = searchFor(father.getName());
					if (index != 0){
						rCGNode.linkOut(new ImpArc(index));
					}
				}
			}//for
		}//if
	}

	/**
	 * 本方法用于在本图，
	 * 或者运行时继承图中找到指定类名的RClass序号。
	 * @param name
	 * 		想要寻找的类名。
	 * @return
	 * 		如果类在本图中，返回一个正数；
	 * 		如果类在运行时继承图中，返回一个负数；
	 * 		如果都没有，就返回0。
	 */
	private int searchFor(String name) {
		int index;
		//先尝试在本图中搜索同名节点。
		Integer value = nameToIndex.get(name);
		if (value == null){
			//本图中没有同名节点，
			//在运行时继承图中搜索，
			//也就是直接在RClassLoader中查找指定名字的RClassID。
			index = RClassLoaderManager
					.getRClassLoader()
					.getRClassIDOf(name);
			if (index > 0){
				//index > 0 表示找到的是JarRClass，不能被继承，
				//将index设为0表示不能创建弧线。
				index = 0;
			}
		} else {
			index = value;
		}
		return index;
	}

	/**
	 * 删除所有继承弧线出错的结点，
	 * 导致结点被删除的原因有：
	 * 继承不存在的RClass，
	 * 循环继承。
	 */
	public void clearIllegal() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 促使每个结点开始继承RClass，
	 * 获取应有的父类信息。
	 */
	public void extend() {
		// TODO Auto-generated method stub
		
	}

}
