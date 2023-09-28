package com.pmu.course.repositroy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.pmu.course.beans.Partant;

@DataJpaTest
class PartantRepositoryTest {

	@Autowired
	private PartantRepository partantRepository;

	@Test
	void testFindByCourseId() {
		final Partant partant1 = new Partant();
		final Partant partant2 = new Partant();
		final Partant partantToNotRetrieve = new Partant();

		partant1.setCourseId(Long.valueOf(1L));
		partant1.setName("Partant 1");
		partant1.setNumber(1);

		partant2.setCourseId(Long.valueOf(1L));
		partant2.setName("Partant 2");
		partant2.setNumber(2);

		partantToNotRetrieve.setCourseId(Long.valueOf(99L));
		partantToNotRetrieve.setName("Partant 3");
		partantToNotRetrieve.setNumber(3);


		this.partantRepository.save(partant1);
		this.partantRepository.save(partant2);
		this.partantRepository.save(partantToNotRetrieve);

		final List<Partant> retrieveds = this.partantRepository.findByCourseId(Long.valueOf(1L));

		assertEquals(2, retrieveds.size());
	}

	@Test
	void testSave() {
		final Partant partant = new Partant();

		partant.setCourseId(Long.valueOf(4L));
		partant.setName("Partant 4");
		partant.setNumber(1);

		final Partant savedPartant = this.partantRepository.save(partant);

		assertEquals("Partant 4", savedPartant.getName());
		assertEquals(4L, savedPartant.getCourseId());
		assertEquals(1, savedPartant.getNumber());
		assertTrue(savedPartant.getId() > 0);
	}

	@Test
	void testDeleteById() {
		final Partant partant = new Partant();
		partant.setName("Partant 5");
		partant.setCourseId(1L);
		final Partant saved = this.partantRepository.save(partant);

		assertNotNull(this.partantRepository.findById(saved.getId()));

		this.partantRepository.deleteById(saved.getId());

		assertNull(this.partantRepository.findById(saved.getId()));
	}
}
