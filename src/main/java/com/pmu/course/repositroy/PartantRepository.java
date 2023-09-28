package com.pmu.course.repositroy;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.pmu.course.beans.Partant;

/**
 * Repository interface for managing Partant entities.
 */
public interface PartantRepository extends Repository<Partant, String>{

    /**
     * Find all partants associated with a specific course.
     *
     * @param id The ID of the course to retrieve partants for.
     * @return A list of partants associated with the specified course ID.
     */
	List<Partant> findByCourseId(Long id);

    /**
     * Find a partant by its ID.
     *
     * @param id The ID of the partant to find.
     * @return The partant with the specified ID.
     */
	Partant findById(Long id);

    /**
     * Save a partant.
     *
     * @param partant The partant to be saved.
     * @return The saved partant.
     */
	Partant save(Partant partant);

    /**
     * Save a list of partants.
     *
     * @param partants The list of partants to be saved.
     * @return The saved list of partants.
     */
	List<Partant> save(List<Partant> partants);

	  /**
     * Delete a partant by its ID.
     *
     * @param id The ID of the partant to delete.
     */
	void deleteById(Long id);

    /**
     * Delete all partants associated with a specific course.
     *
     * @param id The ID of the course whose associated partants should be deleted.
     */
	void deleteByCourseId(Long id);

}
