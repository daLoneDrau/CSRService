package com.osrapi.repositories.csr;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.csr.CSRSiblingRankEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CSRSiblingRankRepository
extends CrudRepository<CSRSiblingRankEntity, Long> {
	/**
	 * Retrieves a list of sibling ranks by their code.
	 * @param code the code
	 * @return {@link List}<{@link CSRSiblingRankEntity}>
	 */
	List<CSRSiblingRankEntity> findByCode(String code);
	/**
	 * Retrieves a list of sibling ranks by their title.
	 * @param title the title
	 * @return {@link List}<{@link CSRSiblingRankEntity}>
	 */
	List<CSRSiblingRankEntity> findByTitle(String title);
	/**
	 * Retrieves a list of sibling ranks by their rollMin.
	 * @param rollMin the rollMin
	 * @return {@link List}<{@link CSRSiblingRankEntity}>
	 */
	List<CSRSiblingRankEntity> findByRollMin(Long rollMin);
	/**
	 * Retrieves a list of sibling ranks by their rollMax.
	 * @param rollMax the rollMax
	 * @return {@link List}<{@link CSRSiblingRankEntity}>
	 */
	List<CSRSiblingRankEntity> findByRollMax(Long rollMax);
	/**
	 * Retrieves a list of sibling ranks by their pointsAdjustment.
	 * @param pointsAdjustment the pointsAdjustment
	 * @return {@link List}<{@link CSRSiblingRankEntity}>
	 */
	List<CSRSiblingRankEntity> findByPointsAdjustment(Long pointsAdjustment);
}
