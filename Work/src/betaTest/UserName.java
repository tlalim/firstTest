package betaTest;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserName {
	 static String username = "";

	public static void getUsername() {
		// TODO Auto-generated method stub
		RuntimeMXBean rmx = ManagementFactory.getRuntimeMXBean();
		String new1 = rmx.getName().replaceAll("@", "");
		Pattern pattern = Pattern.compile("\\d+");
		String word = rmx.getName();
		Matcher matcher = pattern.matcher(word);
		int start = 0;
		while (matcher.find(start)) {
			String value = word.substring(matcher.start(), matcher.end());
			username = new1.replaceAll(value, "");

			start = matcher.end();
		}
	}
}
