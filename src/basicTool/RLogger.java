package basicTool;

public class RLogger{
	public static void log(String message){
		System.out.println("Logger message: " + message);
	}
	
	public static void logException(Exception e){
		e.printStackTrace();
	}
}
