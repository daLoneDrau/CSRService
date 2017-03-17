package com.osrapi.repositories.csr;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.csr.CSRObjectTypeEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CSRObjectTypeRepository
extends CrudRepository<CSRObjectTypeEntity, Long> {
	/**
	 * Retrieves a list of object types by their code.
	 * @param code the code
	 * @return {@link List}<{@link CSRObjectTypeEntity}>
	 */
	List<CSRObjectTypeEntity> findByCode(String code);
	/**
	 * Retrieves a list of object types by their flag.
	 * @param flag the flag
	 * @return {@link List}<{@link CSRObjectTypeEntity}>
	 */
	List<CSRObjectTypeEntity> findByFlag(Long flag);
}
