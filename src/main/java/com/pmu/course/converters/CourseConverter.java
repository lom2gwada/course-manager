package com.pmu.course.converters;

import org.modelmapper.ModelMapper;

import com.pmu.course.beans.Course;
import com.pmu.course.beans.CourseDTO;

/**
 * Converter class for mapping between Course and CourseDTO objects.
 */
public class CourseConverter {

    /**
     * Converts a Course entity to a CourseDTO.
     *
     * @param course The Course entity to convert.
     * @return The corresponding CourseDTO.
     */
	public CourseDTO entityToDto(Course course) {
		final ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(course, CourseDTO.class);
	}

    /**
     * Converts a CourseDTO to a Course entity.
     *
     * @param courseDTO The CourseDTO to convert.
     * @return The corresponding Course entity.
     */
	public Course dtoToEntity(CourseDTO courseDTO) {
		final ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(courseDTO, Course.class);
	}
}
