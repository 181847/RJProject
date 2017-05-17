package unfinishedClass.customRClass.rCGraph;

import java.util.Hashtable;

import basicTool.RLogger;
import rClass.RClassLoaderManager;
import unfinishedClass.customRClass.set.Set;
import unfinishedClass.customRClass.struct.RClassStruct;
import unfinishedClass.customRClass.struct.Struct;

/**
 * 加载时继承图，
 * 继承于RCGraph，
 * 实现有关继承图最多的方法：
 * 插入节点，连接弧线，检查继承错误。
 * 相当于工具类的一种存在，
 * 为RuntimeRCGraph提供服务和桥梁，
 * 图中用于存储顶点的数组的零号单元不放任何值，
 * 表示空节点。
 */
public class LoadRCGraph extends RCGraph{
	Hashtable<String, Integer> nameToIndex;
	
	public LoadRCGraph(int vNum){
		vertics = new RCGNode[vNum + 1];
		vCount = 0;
		//在0号单元放置一个空白无用的RCGNode，
		//它的名字叫"不存在的RClass"，
		//这个空白的RCGNode可以记录所有继承了不存在的RClass的弧线。
		RClassStruct zeroRClassStruct = 
				new RClassStruct();
		zeroRClassStruct.setName("不存在的RClass");
		vertics[0] = new RCGNode(zeroRClassStruct);
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
		//注意vertics数组的零号单元不存放顶点。
		vertics[ ++vCount] = 
				new RCGNode(rClassStruct);
		//记录下对应的序号
		nameToIndex.put(rClassStruct.getName(), vCount);
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
			buffer = null;
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
		
		//检查非接口父类信息是否存在，
		//如果存在就尝试向其创建继承弧线。
		Struct father = rClassStruct.getExtend();
		if (father != null){
			//创建非接口父类继承弧线，
			//弧线指向本图时，弧线的入度需要形成链表，
			//注意这里可能会创建一个到不存在的节点
			rCGNode.linkOut(
					createExtArc(father.getName()));
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
					//创建接口父类继承弧线，
					//弧线指向本图时，弧线的入度需要形成链表
					rCGNode.linkOut(
							createImpArc(father.getName()));
				}
			}//for
		}//if
	}

	/**
	 * 创建到指定名字的RCGNode的接口继承弧线。
	 * @param extendsName
	 * 		RClass的名字。
	 * @return
	 * 		如果找不到指定名字的RClass，
	 * 		创建出来的弧线指向的序号为0；
	 * 		在本图中，指向的序号就是正数；
	 * 		在运行时继承图中，指向的序号就是负数。
	 */
	private ImpArc createImpArc(String interfaceName) {
		int index = searchFor(interfaceName);
		ImpArc newArc = new ImpArc(index);
		
		if (index > 0){
			//如果结点在本图当中，
			//在目标结点的入度中作记录，
			//如果继承的RClass不存在与本图中，
			//则需要将这个继承弧线指向零号位的空白RCGNode，
			//后期删除的时候更方便。
			vertics[index].linkIn(newArc);
		}
		return newArc;
	}

	/**
	 * 创建到指定名字的RCGNode的非接口继承弧线。
	 * @param extendsName
	 * 		RClass的名字。
	 * @return
	 * 		如果找不到指定名字的RClass，
	 * 		创建出来的弧线指向的序号为0；
	 * 		在本图中，指向的序号就是正数；
	 * 		在运行时继承图中，指向的序号就是负数。
	 */
	private ExtArc createExtArc(String extendsName) {
		int index = searchFor(extendsName);
		ExtArc newArc = new ExtArc(index);
		
		if (index >= 0){
			//如果结点在本图当中，
			//在目标结点的入度中作记录，
			//如果继承的RClass不存在与本图中，
			//则需要将这个继承弧线指向零号位的空白RCGNode，
			//后期删除的时候更方便。
			vertics[index].linkIn(newArc);
		}
		return newArc;
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
		//删除记录
		//本图中的每个结点都会对应这个数组当中的一个整数，
		//例如vertics[15] 对应 deleteLog[15]（注：vertics[0]没有意义），
		//任何一个小于等于-1的记录代表当前节点应该被删除，
		//大于等于0的记录代表在所有删除节点被移除之后，
		//对应的结点应该向前移动多少个单位距离（0就代表不移动）。
		int deleteLog[] = new int[vCount];
		StringBuffer deleteReason;
		
		//首先将所有指向零号位空白RCGNode的结点全部标记为删除状态。
		logSubTree(0, deleteLog, deleteReason);
		
		//记录应该被删除的结点序号，
		//被记录为被删除状态的结点应该具有连锁效应，
		//假如图中的某个节点作为另一个结点的子类，
		//这个父节点如果被记录为删除状态，
		//那么连锁地，子类也应该被记录为删除状态。
		logIllegal(deleteLog, deleteReason);
		
		//执行删除操作，
		//每一次的删除操作就会在没有删除记录的结点记录上增加压缩偏移记录。
		deleteIllegal(deleteLog);
		
		compressVArray(deleteLog);
		
		RLogger.logError(deleteReason.toString());
	}
	
	/**
	 * 检查每个结点，
	 * 将所有应该被删除的结点进行记录，
	 * 并且记录删除原因。
	 * @param deleteLog
	 * 		记录被删除的结点，
	 * 		把对应结点序号的数组元素置为负数，
	 * 		而且只会设置负数。
	 * @param deleteReason
	 * 		所有被删除的原因。
	 */	private void logIllegal(int[] deleteLog, StringBuffer deleteReason) {
		// TODO Auto-generated method stub
		for (int vIndex = 1; vIndex <= vCount; ++vIndex){
			if (deleteLog[vIndex] == 0 
					&& vertics[vIndex].linkZeroArc()){
				//连接了一个零号弧线，
				//零号弧线的入度序号为0，
				//表示指向的结点不存在，
				//记录删除这样的结点。
				--deleteLog[vIndex];
				
				//记录错误信息
				deleteReason.append("名为" + vertics[vIndex].getName() + 
						"的RClass继承某些可能不存在的RClass:");
				
				//输出该结点的所有父类名称。
				Set fatherSet = vertics[vIndex].getFatherSet();
				for (int fatherIndex = fatherSet.getNum(); fatherIndex > 0; --fatherIndex){
					deleteReason.append(fatherSet.getStruct(fatherIndex));
				}
				
				//将所有连接（或者间接连接）到这个结点的其他子节点删除。
				logSubTree(vIndex, deleteLog, deleteReason);
			}
		}
	}


	/**
	 * 将所有连接到参数指定结点的子结点全部删除。
	 * @param vIndex
	 * 		参考结点序号，所有继承参考结点的子结点都将被记录为删除状态，
	 * 		同时会递归调用删除这些子结点的子结点。
	 * @param deleteLog
	 * 		记录被删除的结点，
	 * 		把对应结点序号的数组元素置为负数，
	 * 		而且只会设置负数。
	 * @param deleteReason
	 * 		所有被删除的原因。
	 */
	private void logSubTree(int vIndex, int[] deleteLog, StringBuffer deleteReason) {
		if (vIndex <= 0 || vIndex > vCount){
			//结点序号超出正常范围，直接返回。
			return;
		}
		
		//获取结点的入度弧线链表
		RCGArc inArc = vertics[vIndex].getFirstIn();
		
		int subVIndex;
		//循环这个结点的每个子结点
		while (inArc != null){
			//检查每个入度弧线的出度序号
			//即A <------- B 弧线中 的B端的 序号。
			subVIndex = inArc.getOutIndex();
			if (deleteLog[subVIndex] == 0){
				//子结点还未被记录为删除状态。
				
				//记录删除状态
				-- deleteLog[subVIndex];
				deleteReason.append("名为" + vertics[subVIndex].getName()
						+ "的RClass继承了一个被删除的RClass："
						+ vertics[vIndex].getName() + "，因而被删除。");
				
				//删除这个结点以下的子结点
				logSubTree(subVIndex, deleteLog, deleteReason);
			}
			//切换入度弧线
			inArc = inArc.getNextIn();
		}
	}

	/**
	 * 促使每个结点开始继承RClass，
	 * 获取应有的父类信息。
	 */
	public void extend() {
		// TODO Auto-generated method stub
		
	}

}
