package com.pmu.course.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for date-related operations.
 *
 * This class provides a method to format a Date object as a string in the "dd-MM-yyyy" format.
 */
public class DateUtils {

	private DateUtils() {
		// Private constructor to prevent instantiation.
	}

    /**
     * Formats a Date object as a string in "dd-MM-yyyy" format.
     *
     * @param date The Date object to be formatted.
     * @return A string representation of the date in "dd-MM-yyyy" format.
     */
    public static final String formatDateToString(Date date) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }
}
