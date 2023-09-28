package com.pmu.course.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmu.course.beans.Partant;
import com.pmu.course.repositroy.PartantRepository;

/**
 * Service class for managing Partant entities.
 *
 * This service provides methods to save and delete Partant entities and retrieve them by course ID.
 * It also supports batch saving of multiple Partant entities.
 */
@Service
public class PartantService {

	@Autowired
	private PartantRepository partantRepository;

    /**
     * Saves a list of Partant entities.
     *
     * @param partants The list of Partant entities to be saved.
     * @return The list of saved Partant entities.
     */
	List<Partant> saveAll(List<Partant> partants){
		return this.partantRepository.save(partants);
	}

    /**
     * Deletes Partant entities associated with a specific course by course ID.
     *
     * @param id The ID of the course whose associated Partant entities should be deleted.
     */
	public void deleteByCourseId(Long id) {
		this.partantRepository.deleteByCourseId(id);
	}

}

