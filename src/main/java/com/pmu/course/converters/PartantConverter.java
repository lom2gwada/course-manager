package com.pmu.course.converters;

import org.modelmapper.ModelMapper;

import com.pmu.course.beans.Partant;
import com.pmu.course.beans.PartantDTO;

/**
 * Converter class for mapping between Partant and PartantDTO objects.
 */
public class PartantConverter {

	/**
     * Converts a Partant entity to a PartantDTO.
     *
     * @param partant The Partant entity to convert.
     * @return The corresponding PartantDTO.
     */
	public PartantDTO entityToDto(Partant partant) {
		final ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(partant, PartantDTO.class);
	}

    /**
     * Converts a PartantDTO to a Partant entity.
     *
     * @param partantDTO The PartantDTO to convert.
     * @return The corresponding Partant entity.
     */
	public Partant dtoToEntity(PartantDTO partantDTO) {
		final ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(partantDTO, Partant.class);
	}
}
