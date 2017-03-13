package com.osrapi.repositories.csr;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.csr.CSRBirthAspectEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CSRBirthAspectRepository
extends CrudRepository<CSRBirthAspectEntity, Long> {
	/**
	 * Retrieves a list of birth aspects by their code.
	 * @param code the code
	 * @return {@link List}<{@link CSRBirthAspectEntity}>
	 */
	List<CSRBirthAspectEntity> findByCode(String code);
	/**
	 * Retrieves a list of birth aspects by their title.
	 * @param title the title
	 * @return {@link List}<{@link CSRBirthAspectEntity}>
	 */
	List<CSRBirthAspectEntity> findByTitle(String title);
	/**
	 * Retrieves a list of birth aspects by their rollMin.
	 * @param rollMin the rollMin
	 * @return {@link List}<{@link CSRBirthAspectEntity}>
	 */
	List<CSRBirthAspectEntity> findByRollMin(Long rollMin);
	/**
	 * Retrieves a list of birth aspects by their rollMax.
	 * @param rollMax the rollMax
	 * @return {@link List}<{@link CSRBirthAspectEntity}>
	 */
	List<CSRBirthAspectEntity> findByRollMax(Long rollMax);
	/**
	 * Retrieves a list of birth aspects by their pointsAdjustment.
	 * @param pointsAdjustment the pointsAdjustment
	 * @return {@link List}<{@link CSRBirthAspectEntity}>
	 */
	List<CSRBirthAspectEntity> findByPointsAdjustment(Long pointsAdjustment);
}
