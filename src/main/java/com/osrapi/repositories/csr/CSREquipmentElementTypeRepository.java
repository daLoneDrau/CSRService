package com.osrapi.repositories.csr;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.csr.CSREquipmentElementTypeEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CSREquipmentElementTypeRepository
extends CrudRepository<CSREquipmentElementTypeEntity, Long> {
	/**
	 * Retrieves a list of equipment element types by their code.
	 * @param code the code
	 * @return {@link List}<{@link CSREquipmentElementTypeEntity}>
	 */
	List<CSREquipmentElementTypeEntity> findByCode(String code);
	/**
	 * Retrieves a list of equipment element types by their value.
	 * @param value the value
	 * @return {@link List}<{@link CSREquipmentElementTypeEntity}>
	 */
	List<CSREquipmentElementTypeEntity> findByValue(Long value);
}
