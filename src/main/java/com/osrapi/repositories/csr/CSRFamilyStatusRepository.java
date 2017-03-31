package com.osrapi.repositories.csr;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.csr.CSRFamilyStatusEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CSRFamilyStatusRepository
extends CrudRepository<CSRFamilyStatusEntity, Long> {
	/**
	 * Retrieves a list of family statuss by their code.
	 * @param code the code
	 * @return {@link List}<{@link CSRFamilyStatusEntity}>
	 */
	List<CSRFamilyStatusEntity> findByCode(String code);
	/**
	 * Retrieves a list of family statuss by their title.
	 * @param title the title
	 * @return {@link List}<{@link CSRFamilyStatusEntity}>
	 */
	List<CSRFamilyStatusEntity> findByTitle(String title);
	/**
	 * Retrieves a list of family statuss by their rollMin.
	 * @param rollMin the rollMin
	 * @return {@link List}<{@link CSRFamilyStatusEntity}>
	 */
	List<CSRFamilyStatusEntity> findByRollMin(Long rollMin);
	/**
	 * Retrieves a list of family statuss by their rollMax.
	 * @param rollMax the rollMax
	 * @return {@link List}<{@link CSRFamilyStatusEntity}>
	 */
	List<CSRFamilyStatusEntity> findByRollMax(Long rollMax);
	/**
	 * Retrieves a list of family statuss by their pointsAdjustment.
	 * @param pointsAdjustment the pointsAdjustment
	 * @return {@link List}<{@link CSRFamilyStatusEntity}>
	 */
	List<CSRFamilyStatusEntity> findByPointsAdjustment(Long pointsAdjustment);
}
