package basicInterface;

/**
 * 一个可以用字符串来修改对象的接口，
 * 实现这个接口的类可以通过一个字符串来修改他的对象，
 * 例如IntegerAddFunction可以通过这个接口来添加参数针脚，
 * 来实现任意数量的整数相加。
 * @author 75309
 *
 */
public interface IModifier {
	/**
	 * 用字符串返回类型的自定义信息，
	 * 这种自定义信息由程序员在实现这个接口时定义，
	 * 在modify()方法中读取。
	 * @return 自定义所需要的信息。
	 */
	public String getModifierInfo();
	
	/**
	 * 读取一个自定义信息，
	 * 对当前类型执行一定的改变。
	 * @param modifierInfo 自定义的信息。
	 * @return 成功返回1，失败返回0。
	 */
	public int modify(String modifierInfo);
}
