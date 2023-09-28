package com.pmu.course.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmu.course.beans.Course;
import com.pmu.course.beans.CourseDTO;
import com.pmu.course.services.CourseService;

@SpringBootTest
@AutoConfigureMockMvc
class CourseControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CourseService courseService;

	@Test
	void testCreateCourse() throws Exception {
		final CourseDTO courseDTO = new CourseDTO();
		courseDTO.setName("Course 1");
		courseDTO.setDate(new Date());

		this.mockMvc.perform(post("/api/courses/create").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(courseDTO))).andExpect(status().isCreated());

		verify(this.courseService).create(any());
	}

}
