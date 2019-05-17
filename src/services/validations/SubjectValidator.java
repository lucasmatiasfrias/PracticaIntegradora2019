package services.validations;

import static services.validations.GenericValidator.isNumeric;

import dto.SubjectDTO;

public class SubjectValidator {

	private static boolean isCode(String s) {
		return isNumeric(s) && s.length() < 9;
	}

	private static boolean isDescription(String s) {
		return GenericValidator.isAlphabeticalString(s) && s.length() < 50;
	}

	public static boolean isSubjectValid(SubjectDTO subject) {
		return isCode(subject.getId()) && SubjectValidator.isDescription(subject.getDescription());
	}

}
