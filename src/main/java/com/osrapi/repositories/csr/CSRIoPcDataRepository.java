package com.osrapi.repositories.csr;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.csr.CSRIoPcDataEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CSRIoPcDataRepository
extends CrudRepository<CSRIoPcDataEntity, Long> {
	/**
	 * Retrieves a list of io pc datas by their bags.
	 * @param bags the bags
	 * @return {@link List}<{@link CSRIoPcDataEntity}>
	 */
	List<CSRIoPcDataEntity> findByBags(Long bags);
	/**
	 * Retrieves a list of io pc datas by their flags.
	 * @param flags the flags
	 * @return {@link List}<{@link CSRIoPcDataEntity}>
	 */
	List<CSRIoPcDataEntity> findByFlags(Long flags);
	/**
	 * Retrieves a list of io pc datas by their gold.
	 * @param gold the gold
	 * @return {@link List}<{@link CSRIoPcDataEntity}>
	 */
	List<CSRIoPcDataEntity> findByGold(Float gold);
	/**
	 * Retrieves a list of io pc datas by their interfaceFlags.
	 * @param interfaceFlags the interfaceFlags
	 * @return {@link List}<{@link CSRIoPcDataEntity}>
	 */
	List<CSRIoPcDataEntity> findByInterfaceFlags(Long interfaceFlags);
	/**
	 * Retrieves a list of io pc datas by their internalScript.
	 * @param internalScript the internalScript
	 * @return {@link List}<{@link CSRIoPcDataEntity}>
	 */
	List<CSRIoPcDataEntity> findByInternalScript(String internalScript);
	/**
	 * Retrieves a list of io pc datas by their level.
	 * @param level the level
	 * @return {@link List}<{@link CSRIoPcDataEntity}>
	 */
	List<CSRIoPcDataEntity> findByLevel(Long level);
	/**
	 * Retrieves a list of io pc datas by their name.
	 * @param name the name
	 * @return {@link List}<{@link CSRIoPcDataEntity}>
	 */
	List<CSRIoPcDataEntity> findByName(String name);
	/**
	 * Retrieves a list of io pc datas by their xp.
	 * @param xp the xp
	 * @return {@link List}<{@link CSRIoPcDataEntity}>
	 */
	List<CSRIoPcDataEntity> findByXp(Long xp);
}
