package com.osrapi.repositories.csr;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.csr.CSREquipmentItemModifierEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CSREquipmentItemModifierRepository
extends CrudRepository<CSREquipmentItemModifierEntity, Long> {
	/**
	 * Retrieves a list of equipment item modifiers by their code.
	 * @param code the code
	 * @return {@link List}<{@link CSREquipmentItemModifierEntity}>
	 */
	List<CSREquipmentItemModifierEntity> findByCode(String code);
	/**
	 * Retrieves a list of equipment item modifiers by their percent.
	 * @param percent the percent
	 * @return {@link List}<{@link CSREquipmentItemModifierEntity}>
	 */
	List<CSREquipmentItemModifierEntity> findByPercent(Boolean percent);
	/**
	 * Retrieves a list of equipment item modifiers by their special.
	 * @param special the special
	 * @return {@link List}<{@link CSREquipmentItemModifierEntity}>
	 */
	List<CSREquipmentItemModifierEntity> findBySpecial(Long special);
	/**
	 * Retrieves a list of equipment item modifiers by their value.
	 * @param value the value
	 * @return {@link List}<{@link CSREquipmentItemModifierEntity}>
	 */
	List<CSREquipmentItemModifierEntity> findByValue(Float value);
}
