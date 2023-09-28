package com.pmu.course.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.pmu.course.beans.Course;
import com.pmu.course.beans.CourseDTO;
import com.pmu.course.beans.Partant;
import com.pmu.course.beans.PartantDTO;
import com.pmu.course.repositroy.CourseRepository;

@SpringBootTest
class CourseServiceTest {
	@Autowired
	private CourseService courseService;

	@MockBean
	private CourseRepository courseRepository;
	
	@MockBean
    private PartantService partantService;

	@MockBean
    private KafkaProducerService kafkaProducerService;


    @Test
     void testCreate() throws Exception {

        when(courseRepository.existsByFormattedDateAndDayUniqueNumber(anyString(), anyInt())).thenReturn(false);
        when(courseRepository.save(any(Course.class))).thenReturn(new Course());
        when(partantService.saveAll(any())).thenReturn(new ArrayList<>(Arrays.asList(new Partant(), new Partant(), new Partant())));
        
        CourseDTO courseDTO = new CourseDTO();
        
        courseDTO.setDate(new Date());
        courseDTO.getPartants().add(new PartantDTO());
        courseDTO.getPartants().add(new PartantDTO());
        courseDTO.getPartants().add(new PartantDTO());
        
        Course createdCourse = this.courseService.create(courseDTO);

        assertNotNull(createdCourse);

        verify(courseRepository, Mockito.times(1)).existsByFormattedDateAndDayUniqueNumber(anyString(), anyInt());
        verify(courseRepository, Mockito.times(1)).save(any(Course.class));
        
        verify(kafkaProducerService, Mockito.times(1)).sendMessage(anyString(), anyString());
    }
    
    @Test
    void testCreateWithUnvalidPartants() throws Exception {

       when(courseRepository.existsByFormattedDateAndDayUniqueNumber(anyString(), anyInt())).thenReturn(false);
       when(courseRepository.save(any(Course.class))).thenReturn(new Course());
       when(partantService.saveAll(any())).thenReturn(new ArrayList<>(Arrays.asList(new Partant(), new Partant())));

       CourseDTO courseDTO = new CourseDTO();
       
       courseDTO.setDate(new Date());
       
       PartantDTO partantDTO1 = new PartantDTO();
       PartantDTO partantDTO2 = new PartantDTO();
       PartantDTO partantDTO3 = new PartantDTO();
       
       partantDTO1.setName("Partant1");
       partantDTO2.setName("Partant2");
       partantDTO3.setName("Partant3");
       
       courseDTO.getPartants().add(partantDTO1);
       courseDTO.getPartants().add(partantDTO2);
       courseDTO.getPartants().add(partantDTO3);
       
       Course createdCourse = this.courseService.create(courseDTO);

       assertNotNull(createdCourse);

       verify(courseRepository, Mockito.times(1)).existsByFormattedDateAndDayUniqueNumber(anyString(), anyInt());
       verify(courseRepository, Mockito.times(1)).save(any(Course.class));

       verify(kafkaProducerService, Mockito.never()).sendMessage(anyString(), anyString());
   }
    
    @Test
     void testCreateWithExistingCourse() {
        when(courseRepository.existsByFormattedDateAndDayUniqueNumber(anyString(), anyInt())).thenReturn(true);

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setDate(new Date());
        assertThrows(Exception.class, () -> courseService.create(courseDTO));

        verify(courseRepository, Mockito.times(1)).existsByFormattedDateAndDayUniqueNumber(anyString(), anyInt());
        verify(courseRepository, Mockito.never()).save(any(Course.class));
    }

    @Test
     void testCreateWithLessThanThreePartants() {
        CourseDTO courseDTO = new CourseDTO();

        courseDTO.getPartants().add(new PartantDTO());
        courseDTO.getPartants().add(new PartantDTO());

        assertThrows(Exception.class, () -> courseService.create(courseDTO));

        verify(courseRepository, Mockito.never()).existsByFormattedDateAndDayUniqueNumber(anyString(), anyInt());
        verify(courseRepository, Mockito.never()).save(any(Course.class));
    }
}
