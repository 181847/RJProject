package unfinishedClass.customRClass.scriptBlock;

import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.RClassStruct;

/**
 * 加载时继承图，
 * 继承于RCGraph，
 * 实现有关继承图最多的方法：
 * 插入节点，连接弧线，检查继承错误。
 * 相当于工具类的一种存在，
 * 为RuntimeRCGraph提供服务和桥梁。
 */
public class LoadRCGraph extends RCGraph{

	/**
	 * 插入一个RClassStruct对象，
	 * 为其创建一个结点。
	 * @param rClassStruct
	 * 		被插入的RClass信息结构。
	 */
	public void insert(RClassStruct rClassStruct) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 根据每个RClass内部的继承信息，
	 * 自动生成弧线来表示继承的方向。
	 */
	public void autoLink() {
		// TODO Auto-generated method stub
		
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
