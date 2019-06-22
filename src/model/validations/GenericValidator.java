package model.validations;

import static java.util.regex.Pattern.compile;

import java.util.List;

public class GenericValidator {

	public static boolean isNumerical(String s) {
		return s!=null && compile("[0-9]*").matcher(s).matches();
	}

	public static boolean isDocument(String s) {
		return s!=null && compile("[\\d$]{7,8}").matcher(s).matches();
	}

	public static boolean isAlphabeticalString(String s) {
		return s!=null && compile("[A-Za-zñÑáéíóúÁÉÍÓÚ\\s]*").matcher(s).matches();
	}

	public static boolean isEmail(String s) {
		return s!=null && compile("^(.+)@(.+)").matcher(s).matches();
	}

	public static boolean isPassword(String s) {
		return s!=null && compile("[A-Za-z0-9]*").matcher(s).matches();
	}

	public static boolean areNumerical(List<String> s) {
		assert (!s.isEmpty());
		boolean b=true;
		for (String string : s) {
			if(!isNumerical(string))
				b=false;
		}
		return b;
	}
}
