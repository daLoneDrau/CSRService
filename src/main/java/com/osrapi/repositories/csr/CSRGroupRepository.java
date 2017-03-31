package com.osrapi.repositories.csr;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.csr.CSRGroupEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CSRGroupRepository
extends CrudRepository<CSRGroupEntity, Long> {
	/**
	 * Retrieves a list of groups by their name.
	 * @param name the name
	 * @return {@link List}<{@link CSRGroupEntity}>
	 */
	List<CSRGroupEntity> findByName(String name);
}
