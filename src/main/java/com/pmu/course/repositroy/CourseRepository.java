package com.pmu.course.repositroy;

import org.springframework.data.repository.Repository;

import com.pmu.course.beans.Course;

/**
 * Repository interface for managing Course entities.
 */
public interface CourseRepository extends Repository<Course, String>{

    /**
     * Find a course by its name.
     *
     * @param name The name of the course to find.
     * @return The course with the specified name.
     */
	Course findByName(String name);

    /**
     * Find a course by its ID.
     *
     * @param id The ID of the course to find.
     * @return The course with the specified ID.
     */
	Course findById(Long id);

    /**
     * Save a course.
     *
     * @param course The course to be saved.
     * @return The saved course.
     */
	Course save(Course course);

    /**
     * Delete a course by its ID.
     *
     * @param id The ID of the course to delete.
     */
	void deleteById(Long id);

    /**
     * Check if a course with the given formatted date and day unique number exists.
     *
     * @param day The formatted date of the course.
     * @param number The day unique number.
     * @return {@code true} if a course with the specified date and number exists, {@code false} otherwise.
     */
	boolean existsByFormattedDateAndDayUniqueNumber(String day, int number);
}
