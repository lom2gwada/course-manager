package com.pmu.course.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmu.course.beans.Course;
import com.pmu.course.beans.CourseDTO;
import com.pmu.course.beans.Partant;
import com.pmu.course.beans.PartantDTO;
import com.pmu.course.converters.CourseConverter;
import com.pmu.course.converters.PartantConverter;
import com.pmu.course.repositroy.CourseRepository;
import com.pmu.course.utils.DateUtils;

/**
 * Service class for managing courses.
 */
@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private PartantService partantService;
	
	@Autowired
	private KafkaProducerService kafkaProducerService;

	/**
     * Creates a new course based on the provided CourseDTO.
     *
     * @param courseDTO The CourseDTO containing course information.
     * @return The created Course entity.
     * @throws Exception If the course number already exists for the same day or the number of partants is less than 3.
     */
	public Course create(CourseDTO courseDTO) throws Exception {

		final String day = DateUtils.formatDateToString(courseDTO.getDate());

		if(this.courseRepository.existsByFormattedDateAndDayUniqueNumber(day, courseDTO.getDayUniqueNumber())) {
			throw new Exception("Le numéro de course existe déjà pour ce jour");
		}

		if(courseDTO.getPartants().size() < 3) {
			throw new Exception("Nombre de Partant inférieur à 3");
		}

		final CourseConverter courseConverter = new CourseConverter();

		final Course course = courseConverter.dtoToEntity(courseDTO);
		course.setFormattedDate(day);

		final List<Partant>  partants = new ArrayList<>();

		final PartantConverter partantConverter = new PartantConverter();
		int number = 0;
		for(final PartantDTO partantDTO: courseDTO.getPartants()) {
			final Partant partant = partantConverter.dtoToEntity(partantDTO);

			partant.setCourseId(course.getId());
			partant.setNumber(++number);
			
			partants.add(partant);
		}
		
		List<Partant> savedPartants = this.partantService.saveAll(partants);

		if(savedPartants.size() < 3) {
			this.partantService.deleteByCourseId(course.getId());
			this.courseRepository.deleteById(course.getId());
		} else {
			ObjectMapper objectMapper = new ObjectMapper();
			kafkaProducerService.sendMessage("Nouvelle course", objectMapper.writeValueAsString(course));
		}

		return this.courseRepository.save(course);
	}

}
