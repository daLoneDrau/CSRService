package com.osrapi.repositories.csr;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.csr.CSRNameEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CSRNameRepository
extends CrudRepository<CSRNameEntity, Long> {
	/**
	 * Retrieves a list of names by their isLast.
	 * @param isLast the isLast
	 * @return {@link List}<{@link CSRNameEntity}>
	 */
	List<CSRNameEntity> findByIsLast(Boolean isLast);
	/**
	 * Retrieves a list of names by their isFemale.
	 * @param isFemale the isFemale
	 * @return {@link List}<{@link CSRNameEntity}>
	 */
	List<CSRNameEntity> findByIsFemale(Boolean isFemale);
	/**
	 * Retrieves a list of names by their name.
	 * @param name the name
	 * @return {@link List}<{@link CSRNameEntity}>
	 */
	List<CSRNameEntity> findByName(String name);
}
