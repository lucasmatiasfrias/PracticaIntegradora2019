package model.validations;

import static model.validations.GenericValidator.isNumerical;

import java.util.List;

import dto.SubjectDTO;

public class SubjectValidator {

	public static boolean isCode(String s) {
		return isNumerical(s) && s.length() < 9;
	}

	private static boolean isDescription(String s) {
		return GenericValidator.isAlphabeticalString(s) && s.length() < 50;
	}

	public static boolean isSubjectValid(SubjectDTO subject) {
		return isCode(subject.getId()) && SubjectValidator.isDescription(subject.getDescription());
	}

	public static boolean areSubjectsCodes(List<String> codes) {
		assert (!codes.isEmpty());
		boolean b = true;
		for (String string : codes) {
			if (!isCode(string))
				b = false;
		}
		return b;
	}

}
