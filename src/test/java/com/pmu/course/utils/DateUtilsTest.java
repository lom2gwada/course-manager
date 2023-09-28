package com.pmu.course.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

class DateUtilsTest {

	@Test
	public void testFormatDateToString() throws ParseException {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		final Date date = dateFormat.parse("25-12-2022");

		final String formattedDate = DateUtils.formatDateToString(date);

		assertEquals("25-12-2022", formattedDate);
	}

}
