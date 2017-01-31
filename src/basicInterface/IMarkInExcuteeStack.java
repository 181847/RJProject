package basicInterface;


//此类是与IExcuteeStack配套的接口
//这两种接口的实现类必须一致
//例如A implements IExcuteeStack
//B implements IExcuteeStack
//X implements IMarkInExcuteeStack
//Y implements IMarkInExcuteeStack
//定义中
//在A类中getMark()时，创建的是X类的实例
//在B类中getMark()时，创建的是Y类的实例
//实际使用时
//则类A的clearTo(mark)时，不能传入一个Y类的实例，
//必须使用X类的实例
//同理B的clearTo(mark),不能传入一个X的实例
//必须使用Y类的实例
public interface IMarkInExcuteeStack{
	
}
