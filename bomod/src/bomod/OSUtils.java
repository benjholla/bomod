package bomod;

public class OSUtils {

	private OSUtils() {}
	
	private static String OS = null;
	private static boolean isWindows = false;
	private static boolean isMac = false;
	private static boolean isUnix = false;

	public static String getOsName() {
		if (OS == null) {
			OS = System.getProperty("os.name");
		}
		if (OS.startsWith("Windows")) {
			isWindows = true;
		} else if (OS.contains("OS X")) {
			isMac = true;
		} else {
			isUnix = true;
		}
		return OS;
	}

	public static boolean isWindows() {
		if (OS == null)
			getOsName();
		return isWindows;
	}

	public static boolean isMac() {
		if (OS == null)
			getOsName();
		return isMac;
	}

	public static boolean isUnix() {
		if (OS == null)
			getOsName();
		return isUnix;
	}
	
}
