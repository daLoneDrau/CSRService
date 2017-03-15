package com.osrapi.repositories.csr;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.csr.CSREquipmentSlotEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CSREquipmentSlotRepository
extends CrudRepository<CSREquipmentSlotEntity, Long> {
	/**
	 * Retrieves a list of equipment slots by their code.
	 * @param code the code
	 * @return {@link List}<{@link CSREquipmentSlotEntity}>
	 */
	List<CSREquipmentSlotEntity> findByCode(String code);
	/**
	 * Retrieves a list of equipment slots by their value.
	 * @param value the value
	 * @return {@link List}<{@link CSREquipmentSlotEntity}>
	 */
	List<CSREquipmentSlotEntity> findByValue(Long value);
}
