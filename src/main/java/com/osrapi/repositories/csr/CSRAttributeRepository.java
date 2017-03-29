package com.osrapi.repositories.csr;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.csr.CSRAttributeEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CSRAttributeRepository
extends CrudRepository<CSRAttributeEntity, Long> {
	/**
	 * Retrieves a list of attributes by their code.
	 * @param code the code
	 * @return {@link List}<{@link CSRAttributeEntity}>
	 */
	List<CSRAttributeEntity> findByCode(String code);
	/**
	 * Retrieves a list of attributes by their description.
	 * @param description the description
	 * @return {@link List}<{@link CSRAttributeEntity}>
	 */
	List<CSRAttributeEntity> findByDescription(String description);
	/**
	 * Retrieves a list of attributes by their name.
	 * @param name the name
	 * @return {@link List}<{@link CSRAttributeEntity}>
	 */
	List<CSRAttributeEntity> findByName(String name);
}
