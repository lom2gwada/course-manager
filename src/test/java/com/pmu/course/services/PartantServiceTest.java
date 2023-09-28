package com.pmu.course.services;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.pmu.course.beans.Partant;
import com.pmu.course.repositroy.PartantRepository;

@SpringBootTest
class PartantServiceTest {

	@Mock
	private PartantRepository partantRepository;

	@InjectMocks
	private PartantService partantService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSaveAll(){
		final List<Partant> partants = new ArrayList<>();

		final Partant partant1 = new Partant();
		partant1.setName("Partant 1");
		partant1.setCourseId(1L);

		partants.add(partant1);

		this.partantService.saveAll(partants);

		verify(this.partantRepository).save(partants);
	}

	@Test
	void deleteByCourseId() {
		this.partantRepository.deleteByCourseId(1L);

		verify(this.partantRepository).deleteByCourseId(1L);
	}

}
