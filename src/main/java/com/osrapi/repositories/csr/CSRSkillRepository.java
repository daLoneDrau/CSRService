package com.osrapi.repositories.csr;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.csr.CSRSkillEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CSRSkillRepository
extends CrudRepository<CSRSkillEntity, Long> {
	/**
	 * Retrieves a list of skills by their name.
	 * @param name the name
	 * @return {@link List}<{@link CSRSkillEntity}>
	 */
	List<CSRSkillEntity> findByName(String name);
}
