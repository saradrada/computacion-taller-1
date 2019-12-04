package co.edu.icesi.model;

import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DateValidator {

	public static boolean isThisDateValid(String dateToValidate, String dateFromat) {

		if (dateToValidate == null) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);

		try {
			sdf.parse(dateToValidate);

		} catch (ParseException e) {
			return false;
		}

		return true;
	}
}
