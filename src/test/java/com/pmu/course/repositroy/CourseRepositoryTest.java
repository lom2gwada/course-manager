package com.pmu.course.repositroy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.pmu.course.beans.Course;
import com.pmu.course.utils.DateUtils;

@DataJpaTest
class CourseRepositoryTest {

	@Autowired
	private CourseRepository courseRepository;

	@Test
	void testFindByName() {
		final Course course = new Course();
		final Date currentDate = new Date();
		course.setName("Course 4");
		course.setDate(currentDate);
		course.setFormattedDate(DateUtils.formatDateToString(currentDate));

		final Course saved = this.courseRepository.save(course);

		final Course retrieved = this.courseRepository.findByName(saved.getName());

		assertEquals(saved.getName(), retrieved.getName());
		assertEquals(saved.getDate(), retrieved.getDate());
		assertEquals(saved.getId(), retrieved.getId());
	}


	@Test
	void testFindById() {
		final Course course = new Course();
		final Date currentDate = new Date();
		course.setName("Course 3");
		course.setDate(currentDate);
		course.setFormattedDate(DateUtils.formatDateToString(currentDate));

		final Course saved = this.courseRepository.save(course);

		final Course retrieved = this.courseRepository.findById(saved.getId());

		assertEquals(saved.getName(), retrieved.getName());
		assertEquals(saved.getDate(), retrieved.getDate());
		assertEquals(saved.getId(), retrieved.getId());
	}

	@Test
	void testSave() {
		final Course course = new Course();
		final Date currentTime = new Date();
		course.setDate(currentTime);
		course.setName("Course 1");
		course.setFormattedDate(DateUtils.formatDateToString(currentTime));

		final Course savedCourse = this.courseRepository.save(course);

		assertEquals("Course 1", savedCourse.getName());
		assertEquals(currentTime, savedCourse.getDate());
		assertTrue(savedCourse.getId() > 0);
	}

	@Test
	void testDeleteById() {
		final Course course = new Course();

		final Date date = new Date();

		course.setName("Course 2");
		course.setDate(date);
		course.setFormattedDate(DateUtils.formatDateToString(date));

		final Course savedCourse = this.courseRepository.save(course);

		assertNotNull(this.courseRepository.findById(savedCourse.getId()));

		this.courseRepository.deleteById(savedCourse.getId());

		assertNull(this.courseRepository.findById(savedCourse.getId()));
	}

	@Test
	void existsByFormattedDateAndDayUniqueNumber() {

		assertFalse(this.courseRepository.existsByFormattedDateAndDayUniqueNumber(DateUtils.formatDateToString(new Date()), 1));

		final Course course = new Course();

		final Date date = new Date();

		course.setName("Course 1");
		course.setDate(date);
		course.setFormattedDate(DateUtils.formatDateToString(date));
		course.setDayUniqueNumber(1);

		this.courseRepository.save(course);

		assertTrue(this.courseRepository.existsByFormattedDateAndDayUniqueNumber(DateUtils.formatDateToString(date), 1));
	}

}
