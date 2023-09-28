package com.pmu.course.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pmu.course.beans.Course;
import com.pmu.course.beans.CourseDTO;

class CourseConverterTest {

	private CourseConverter courseConverter;

	@BeforeEach
	public void setUp() {
		this.courseConverter = new CourseConverter();
	}

	@Test
	void testEntityToDto() {
		final Course course = new Course();
		course.setId(1L);
		course.setName("Course 1");
		course.setDate(new Date());

		final CourseDTO courseDTO = this.courseConverter.entityToDto(course);

		assertEquals(course.getName(), courseDTO.getName());
		assertEquals(course.getDate(), courseDTO.getDate());
		assertEquals(course.getDayUniqueNumber(), courseDTO.getDayUniqueNumber());
	}

	@Test
	void testDtoToEntity() {
		final CourseDTO courseDTO = new CourseDTO();
		courseDTO.setName("Course 2");
		courseDTO.setDate(new Date());
		courseDTO.setDayUniqueNumber(2);

		final Course course = this.courseConverter.dtoToEntity(courseDTO);

		assertEquals(courseDTO.getName(), course.getName());
		assertEquals(courseDTO.getDate(), course.getDate());
		assertEquals(courseDTO.getDayUniqueNumber(), course.getDayUniqueNumber());
	}
}