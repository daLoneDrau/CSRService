package com.osrapi.repositories.csr;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.csr.CSRGenderEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CSRGenderRepository
extends CrudRepository<CSRGenderEntity, Long> {
	/**
	 * Retrieves a list of genders by their description.
	 * @param description the description
	 * @return {@link List}<{@link CSRGenderEntity}>
	 */
	List<CSRGenderEntity> findByDescription(String description);
	/**
	 * Retrieves a list of genders by their name.
	 * @param name the name
	 * @return {@link List}<{@link CSRGenderEntity}>
	 */
	List<CSRGenderEntity> findByName(String name);
	/**
	 * Retrieves a list of genders by their subjective.
	 * @param subjective the subjective
	 * @return {@link List}<{@link CSRGenderEntity}>
	 */
	List<CSRGenderEntity> findBySubjective(String subjective);
	/**
	 * Retrieves a list of genders by their objective.
	 * @param objective the objective
	 * @return {@link List}<{@link CSRGenderEntity}>
	 */
	List<CSRGenderEntity> findByObjective(String objective);
	/**
	 * Retrieves a list of genders by their dependentPossessive.
	 * @param dependentPossessive the dependentPossessive
	 * @return {@link List}<{@link CSRGenderEntity}>
	 */
	List<CSRGenderEntity> findByDependentPossessive(String dependentPossessive);
	/**
	 * Retrieves a list of genders by their independentPossessive.
	 * @param independentPossessive the independentPossessive
	 * @return {@link List}<{@link CSRGenderEntity}>
	 */
	List<CSRGenderEntity> findByIndependentPossessive(String independentPossessive);
	/**
	 * Retrieves a list of genders by their reflexive.
	 * @param reflexive the reflexive
	 * @return {@link List}<{@link CSRGenderEntity}>
	 */
	List<CSRGenderEntity> findByReflexive(String reflexive);
}
