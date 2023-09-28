package com.pmu.course.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pmu.course.beans.Course;
import com.pmu.course.beans.CourseDTO;
import com.pmu.course.services.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	/**
	 * Creates a new race using the provided data in JSON format.
	 *
	 * @param courseDTO The details of the race to create, in JSON format.
	 * @return The newly created race.
	 * @throws Exception If an error occurs during the race creation.
	 */
	@PostMapping(value = "/create", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Course create(@RequestBody CourseDTO courseDTO) throws Exception {
		return this.courseService.create(courseDTO);
	}
}
