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
	 * Retrieves a list of father vocations by their feudalHolding.
	 * @param feudalHolding the feudalHolding
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByFeudalHolding(Long feudalHolding);
	/**
	 * Retrieves a list of father vocations by their thievesGuildStatus.
	 * @param thievesGuildStatus the thievesGuildStatus
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByThievesGuildStatus(Long thievesGuildStatus);
	/**
	 * Retrieves a list of father vocations by their numBonusD10SocialStatus.
	 * @param numBonusD10SocialStatus the numBonusD10SocialStatus
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByNumBonusD10SocialStatus(Long numBonusD10SocialStatus);
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
	 * Retrieves a list of father vocations by their numStartingArtisticSkills.
	 * @param numStartingArtisticSkills the numStartingArtisticSkills
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByNumStartingArtisticSkills(Long numStartingArtisticSkills);
	/**
	 * Retrieves a list of father vocations by their numStartingCrafttSkills.
	 * @param numStartingCrafttSkills the numStartingCrafttSkills
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByNumStartingCrafttSkills(Long numStartingCrafttSkills);
	/**
	 * Retrieves a list of father vocations by their numStartingCombatSkills.
	 * @param numStartingCombatSkills the numStartingCombatSkills
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByNumStartingCombatSkills(Long numStartingCombatSkills);
	/**
	 * Retrieves a list of father vocations by their numStartingLoreSkills.
	 * @param numStartingLoreSkills the numStartingLoreSkills
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByNumStartingLoreSkills(Long numStartingLoreSkills);
	/**
	 * Retrieves a list of father vocations by their numStartingOutdoorSkills.
	 * @param numStartingOutdoorSkills the numStartingOutdoorSkills
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByNumStartingOutdoorSkills(Long numStartingOutdoorSkills);
	/**
	 * Retrieves a list of father vocations by their numStartingSeaSkills.
	 * @param numStartingSeaSkills the numStartingSeaSkills
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByNumStartingSeaSkills(Long numStartingSeaSkills);
	/**
	 * Retrieves a list of father vocations by their numStartingThieverySkills.
	 * @param numStartingThieverySkills the numStartingThieverySkills
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByNumStartingThieverySkills(Long numStartingThieverySkills);
	/**
	 * Retrieves a list of father vocations by their numStartingTradeSkills.
	 * @param numStartingTradeSkills the numStartingTradeSkills
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByNumStartingTradeSkills(Long numStartingTradeSkills);
	/**
	 * Retrieves a list of father vocations by their numStartingBonusSkills.
	 * @param numStartingBonusSkills the numStartingBonusSkills
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByNumStartingBonusSkills(Long numStartingBonusSkills);
	/**
	 * Retrieves a list of father vocations by their numStartingBinary0MagickMethods.
	 * @param numStartingBinary0MagickMethods the numStartingBinary0MagickMethods
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByNumStartingBinary0MagickMethods(Long numStartingBinary0MagickMethods);
	/**
	 * Retrieves a list of father vocations by their numStartingBinary1MagickMethods.
	 * @param numStartingBinary1MagickMethods the numStartingBinary1MagickMethods
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByNumStartingBinary1MagickMethods(Long numStartingBinary1MagickMethods);
	/**
	 * Retrieves a list of father vocations by their numStartingBinary1LoreSkills.
	 * @param numStartingBinary1LoreSkills the numStartingBinary1LoreSkills
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByNumStartingBinary1LoreSkills(Long numStartingBinary1LoreSkills);
	/**
	 * Retrieves a list of father vocations by their numStartingForeignLanguages.
	 * @param numStartingForeignLanguages the numStartingForeignLanguages
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByNumStartingForeignLanguages(Long numStartingForeignLanguages);
	/**
	 * Retrieves a list of father vocations by their numStartingWrittenLanguages.
	 * @param numStartingWrittenLanguages the numStartingWrittenLanguages
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByNumStartingWrittenLanguages(Long numStartingWrittenLanguages);
	/**
	 * Retrieves a list of father vocations by their readingIntRequired.
	 * @param readingIntRequired the readingIntRequired
	 * @return {@link List}<{@link CSRFatherVocationEntity}>
	 */
	List<CSRFatherVocationEntity> findByReadingIntRequired(Long readingIntRequired);
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
