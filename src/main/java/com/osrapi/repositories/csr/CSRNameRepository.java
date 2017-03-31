package com.osrapi.repositories.csr;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.csr.CSRNameEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CSRNameRepository
extends CrudRepository<CSRNameEntity, Long> {
	/**
	 * Retrieves a list of names by their isLast.
	 * @param isLast the isLast
	 * @return {@link List}<{@link CSRNameEntity}>
	 */
	List<CSRNameEntity> findByIsLast(Boolean isLast);
	/**
	 * Retrieves a list of names by their isFemale.
	 * @param isFemale the isFemale
	 * @return {@link List}<{@link CSRNameEntity}>
	 */
	List<CSRNameEntity> findByIsFemale(Boolean isFemale);
	/**
	 * Retrieves a list of names by their name.
	 * @param name the name
	 * @return {@link List}<{@link CSRNameEntity}>
	 */
	List<CSRNameEntity> findByName(String name);
    /**
     * Retrieves a list of names by their isFemale.
     * @param isFemale the isFemale
     * @return {@link List}<{@link CSRNameEntity}>
     */
    Long countByIsFemale(Boolean isFemale);
    /**
     * Counts the number of names that match the specific criteria.
     * @param isLast the isLast
     * @param isFemale the isFemale
     * @return {@link List}<{@link CSRNameEntity}>
     */
    Long countByIsLastAndIsFemale(Boolean isLast, Boolean isFemale);
    /**
     * Retrieves a list of names by their isFemale and page.
     * @param isFemale the isFemale
     * @param pageable the {@link Pageable} instance
     * @return {@link List}<{@link CSRNameEntity}>
     */
    List<CSRNameEntity> findByIsFemale(Boolean isFemale, Pageable pageable);
    /**
     * Retrieves a list of names by their isFemale and page.
     * @param isLast the isLast
     * @param isFemale the isFemale
     * @param pageable the {@link Pageable} instance
     * @return {@link List}<{@link CSRNameEntity}>
     */
    List<CSRNameEntity> findByIsLastAndIsFemale(Boolean isLast, Boolean isFemale, Pageable pageable);
}
