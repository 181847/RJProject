package unfinishedClass.customRClass;

public class OffsetIndex {
	int[] indexArray;
	int[] offsetArray;
	
	/**
	 * 已有索引的数量，
	 * 0表示没有索引，
	 * 1表示有一个索引
	 * ……。
	 */
	int length;
	
	/**
	 * 可存储的空间容量。
	 */
	int capacity;
	
	public OffsetIndex(){
		length = 0;
		capacity = 5;
		indexArray = new int[capacity];
		offsetArray = new int[capacity];
	}
	
	public void addOffsetIndex(int index, int offset){
		ensureCapacity();
		indexArray[length] = index;
		offsetArray[length] = offset;
		++length;
	}
	
}
