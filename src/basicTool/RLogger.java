package basicTool;

public class RLogger{
	public static void log(String message){
		System.out.println("Logger message: " + message);
	}
	
	public static void logException(Exception e){
		System.out.println("Occured Exception: ");
		e.printStackTrace();
	}
	
	public static void logException(Exception e, String location, String error, String reason){
		System.out.println("ERROR Location: " + location);
		logException(e);
		logError(location, error, reason);
	}
	
	public static void logError(String message){
		System.out.println("ERROR! Message: " + message);
	}
	
	public static void logError(String location, String error, String reason){
		System.out.println("ERROR Location: " + location 
				+ "\nERROR: " + error
				+ "\nReason: " + reason);
	}
	
	public static void logError(String location, String error,
			String reason, String detail){
		logError(location, error, detail);
		System.out.println("ErrorDetail: ");
	}
}
