package com.osrapi.repositories.csr;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.csr.CSRRaceEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CSRRaceRepository
extends CrudRepository<CSRRaceEntity, Long> {
	/**
	 * Retrieves a list of races by their name.
	 * @param name the name
	 * @return {@link List}<{@link CSRRaceEntity}>
	 */
	List<CSRRaceEntity> findByName(String name);
}
