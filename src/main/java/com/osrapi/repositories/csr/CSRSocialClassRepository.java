package com.osrapi.repositories.csr;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.csr.CSRSocialClassEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CSRSocialClassRepository
extends CrudRepository<CSRSocialClassEntity, Long> {
	/**
	 * Retrieves a list of social classs by their name.
	 * @param name the name
	 * @return {@link List}<{@link CSRSocialClassEntity}>
	 */
	List<CSRSocialClassEntity> findByName(String name);
	/**
	 * Retrieves a list of social classs by their title.
	 * @param title the title
	 * @return {@link List}<{@link CSRSocialClassEntity}>
	 */
	List<CSRSocialClassEntity> findByTitle(String title);
	/**
	 * Retrieves a list of social classs by their rollMin.
	 * @param rollMin the rollMin
	 * @return {@link List}<{@link CSRSocialClassEntity}>
	 */
	List<CSRSocialClassEntity> findByRollMin(Long rollMin);
	/**
	 * Retrieves a list of social classs by their rollMax.
	 * @param rollMax the rollMax
	 * @return {@link List}<{@link CSRSocialClassEntity}>
	 */
	List<CSRSocialClassEntity> findByRollMax(Long rollMax);
	/**
	 * Retrieves a list of social classs by their pointsAdjustment.
	 * @param pointsAdjustment the pointsAdjustment
	 * @return {@link List}<{@link CSRSocialClassEntity}>
	 */
	List<CSRSocialClassEntity> findByPointsAdjustment(Long pointsAdjustment);
}
