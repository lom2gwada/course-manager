package com.pmu.course.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pmu.course.beans.Partant;
import com.pmu.course.beans.PartantDTO;

class PartantConverterTest {

	private PartantConverter partantConverter;

	@BeforeEach
	public void setUp() {
		this.partantConverter = new PartantConverter();
	}

	@Test
	void testEntityToDto() {
		final Partant partant = new Partant();
		partant.setId(1L);
		partant.setName("Partant 1");

		final PartantDTO partantDTO = this.partantConverter.entityToDto(partant);

		assertEquals(partant.getName(), partantDTO.getName());
		assertEquals(partant.getNumber(), partantDTO.getNumber());
	}

	@Test
	void testDtoToEntity() {
		final PartantDTO partantDTO = new PartantDTO();
		partantDTO.setName("Partant 2");

		final Partant partant = this.partantConverter.dtoToEntity(partantDTO);

		assertEquals(partantDTO.getName(), partant.getName());
		assertEquals(partantDTO.getNumber(), partant.getNumber());
	}
}
