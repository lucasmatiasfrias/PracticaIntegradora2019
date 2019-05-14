package services;

import static java.util.regex.Pattern.compile;

public class RegexValidator {

	public static boolean isNumeric(String s) {
		return compile("[0-9]*").matcher(s).matches();
	}

	public static boolean isDocument(String s) {
		return compile("[\\d$]{7,8}").matcher(s).matches();
	}

	public static boolean isAlphabeticalString(String s) {
		return compile("[A-Za-z\\s]*").matcher(s).matches();
	}

	public static boolean isEmail(String s) {
		return compile("^(.+)@(.+)").matcher(s).matches();
	}

	public static boolean isPassword(String s) {
		return compile("[A-Za-z0-9]*").matcher(s).matches();
	}

}
