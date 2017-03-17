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
	 * Retrieves a list of father vocations by their thievesGuildStatus.
	 * @param thievesGuildStatus the thievesGuildStatus
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByThievesGuildStatus(Long thievesGuildStatus);
	/**
	 * Retrieves a list of father vocations by their socialStatus.
	 * @param socialStatus the socialStatus
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findBySocialStatus(Long socialStatus);
	/**
	 * Retrieves a list of father vocations by their numStartingAnimalSkills.
	 * @param numStartingAnimalSkills the numStartingAnimalSkills
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByNumStartingAnimalSkills(Long numStartingAnimalSkills);
	/**
	 * Retrieves a list of father vocations by their numStartingAgriculturalSkills.
	 * @param numStartingAgriculturalSkills the numStartingAgriculturalSkills
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByNumStartingAgriculturalSkills(Long numStartingAgriculturalSkills);
	/**
	 * Retrieves a list of father vocations by their numStartingCombatSkills.
	 * @param numStartingCombatSkills the numStartingCombatSkills
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByNumStartingCombatSkills(Long numStartingCombatSkills);
	/**
	 * Retrieves a list of father vocations by their numStartingOutdoorSkills.
	 * @param numStartingOutdoorSkills the numStartingOutdoorSkills
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByNumStartingOutdoorSkills(Long numStartingOutdoorSkills);
	/**
	 * Retrieves a list of father vocations by their numStartingThieverySkills.
	 * @param numStartingThieverySkills the numStartingThieverySkills
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByNumStartingThieverySkills(Long numStartingThieverySkills);
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
	/**
	 * Retrieves a list of father vocations by their isLiveried.
	 * @param isLiveried the isLiveried
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByIsLiveried(Boolean isLiveried);
}
