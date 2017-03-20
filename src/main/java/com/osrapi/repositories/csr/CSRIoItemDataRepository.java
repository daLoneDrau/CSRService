package com.osrapi.repositories.csr;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.csr.CSRIoItemDataEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CSRIoItemDataRepository
extends CrudRepository<CSRIoItemDataEntity, Long> {
	/**
	 * Retrieves a list of io item datas by their count.
	 * @param count the count
	 * @return {@link List}<{@link CSRIoItemDataEntity}>
	 */
	List<CSRIoItemDataEntity> findByCount(Long count);
	/**
	 * Retrieves a list of io item datas by their description.
	 * @param description the description
	 * @return {@link List}<{@link CSRIoItemDataEntity}>
	 */
	List<CSRIoItemDataEntity> findByDescription(String description);
	/**
	 * Retrieves a list of io item datas by their foodValue.
	 * @param foodValue the foodValue
	 * @return {@link List}<{@link CSRIoItemDataEntity}>
	 */
	List<CSRIoItemDataEntity> findByFoodValue(Long foodValue);
	/**
	 * Retrieves a list of io item datas by their internalScript.
	 * @param internalScript the internalScript
	 * @return {@link List}<{@link CSRIoItemDataEntity}>
	 */
	List<CSRIoItemDataEntity> findByInternalScript(String internalScript);
	/**
	 * Retrieves a list of io item datas by their leftRing.
	 * @param leftRing the leftRing
	 * @return {@link List}<{@link CSRIoItemDataEntity}>
	 */
	List<CSRIoItemDataEntity> findByLeftRing(Boolean leftRing);
	/**
	 * Retrieves a list of io item datas by their lightValue.
	 * @param lightValue the lightValue
	 * @return {@link List}<{@link CSRIoItemDataEntity}>
	 */
	List<CSRIoItemDataEntity> findByLightValue(Long lightValue);
	/**
	 * Retrieves a list of io item datas by their maxOwned.
	 * @param maxOwned the maxOwned
	 * @return {@link List}<{@link CSRIoItemDataEntity}>
	 */
	List<CSRIoItemDataEntity> findByMaxOwned(Long maxOwned);
	/**
	 * Retrieves a list of io item datas by their name.
	 * @param name the name
	 * @return {@link List}<{@link CSRIoItemDataEntity}>
	 */
	List<CSRIoItemDataEntity> findByName(String name);
	/**
	 * Retrieves a list of io item datas by their price.
	 * @param price the price
	 * @return {@link List}<{@link CSRIoItemDataEntity}>
	 */
	List<CSRIoItemDataEntity> findByPrice(Float price);
	/**
	 * Retrieves a list of io item datas by their ringType.
	 * @param ringType the ringType
	 * @return {@link List}<{@link CSRIoItemDataEntity}>
	 */
	List<CSRIoItemDataEntity> findByRingType(Long ringType);
	/**
	 * Retrieves a list of io item datas by their stackSize.
	 * @param stackSize the stackSize
	 * @return {@link List}<{@link CSRIoItemDataEntity}>
	 */
	List<CSRIoItemDataEntity> findByStackSize(Long stackSize);
	/**
	 * Retrieves a list of io item datas by their stealValue.
	 * @param stealValue the stealValue
	 * @return {@link List}<{@link CSRIoItemDataEntity}>
	 */
	List<CSRIoItemDataEntity> findByStealValue(Long stealValue);
	/**
	 * Retrieves a list of io item datas by their title.
	 * @param title the title
	 * @return {@link List}<{@link CSRIoItemDataEntity}>
	 */
	List<CSRIoItemDataEntity> findByTitle(String title);
	/**
	 * Retrieves a list of io item datas by their weight.
	 * @param weight the weight
	 * @return {@link List}<{@link CSRIoItemDataEntity}>
	 */
	List<CSRIoItemDataEntity> findByWeight(Float weight);
}
