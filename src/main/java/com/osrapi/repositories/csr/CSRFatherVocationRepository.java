package com.osrapi.repositories.csr;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.csr.CSRFatherVocationEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CSRFatherVocationRepository
extends CrudRepository<CSRFatherVocationEntity, Long> {
	/**
	 * Retrieves a list of father vocations by their name.
	 * @param name the name
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByName(String name);
	/**
	 * Retrieves a list of father vocations by their socialStatus.
	 * @param socialStatus the socialStatus
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findBySocialStatus(Long socialStatus);
	/**
	 * Retrieves a list of father vocations by their numStartingAgriculturalSkills.
	 * @param numStartingAgriculturalSkills the numStartingAgriculturalSkills
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByNumStartingAgriculturalSkills(Long numStartingAgriculturalSkills);
	/**
	 * Retrieves a list of father vocations by their numStartingBonusSkills.
	 * @param numStartingBonusSkills the numStartingBonusSkills
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByNumStartingBonusSkills(Long numStartingBonusSkills);
	/**
	 * Retrieves a list of father vocations by their rollMin.
	 * @param rollMin the rollMin
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByRollMin(Long rollMin);
	/**
	 * Retrieves a list of father vocations by their rollMax.
	 * @param rollMax the rollMax
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByRollMax(Long rollMax);
}
