package basicInterface;


/**
 * 此类是与IExcuteeStack配套的接口，
 * 本接口并不声明任何方法，
 * 由具体的实现类定义相应的方法，
 * 外部只能存储这个类，不能调用这个类的方法，
 * 方法只能在ExcuteeStack的内部进行强制类型转换后才能调用；
 * 这两种接口（IExcuteeStack、IMarkInExcuteeStack）的实现类必须一致，
 * 例如A implements IExcuteeStack，
 * B implements IExcuteeStack，
 * X implements IMarkInExcuteeStack，
 * Y implements IMarkInExcuteeStack，
 * 定义中：
 * 在A类中getMark()时，创建的是X类的实例；
 * 在B类中getMark()时，创建的是Y类的实例；
 * 实际使用时：
 * 则类A的clearTo(mark)时，不能传入一个Y类的实例，
 * 必须使用X类的实例；
 * 同理B的clearTo(mark),不能传入一个X的实例，
 * 必须使用Y类的实例。
 */
public interface IMarkInExcuteeStack{
	/**
	 * No Method
	 */
}
