package com.osrapi.controllers.csr;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.osrapi.models.csr.CSREquipmentElementTypeEntity;

import com.osrapi.repositories.csr.CSREquipmentElementTypeRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/csr/equipment_element_types")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CSREquipmentElementTypeController {
    /** the static instance of {@link CSREquipmentElementTypeController}. */
    private static CSREquipmentElementTypeController instance;
    /**
     * Gets the static instance.
     * @return {@link CSREquipmentElementTypeController}
     */
    public static CSREquipmentElementTypeController getInstance() {
        if (instance == null) {
            new CSREquipmentElementTypeController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CSREquipmentElementTypeRepository repository;
    /** Creates a new instance of {@link CSREquipmentElementTypeController}. */
    public CSREquipmentElementTypeController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CSREquipmentElementTypeEntity}s.
     * @return {@link List}<{@link Resource}<{@link CSREquipmentElementTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CSREquipmentElementTypeEntity>> getAll() {
        Iterator<CSREquipmentElementTypeEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CSREquipmentElementTypeEntity>> resources =
                new ArrayList<Resource<CSREquipmentElementTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentElementTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CSREquipmentElementTypeEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CSREquipmentElementTypeEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CSREquipmentElementTypeEntity>> getById(
            @PathVariable final Long id) {
        CSREquipmentElementTypeEntity entity = repository.findOne(id);
        List<Resource<CSREquipmentElementTypeEntity>> resources =
                new ArrayList<Resource<CSREquipmentElementTypeEntity>>();
        resources.add(getEquipmentElementTypeResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CSREquipmentElementTypeEntity}.
     * @param entity the {@link CSREquipmentElementTypeEntity}
     * @return {@link Resource}<{@link CSREquipmentElementTypeEntity}>
     */
    private Resource<CSREquipmentElementTypeEntity> getEquipmentElementTypeResource(
            final CSREquipmentElementTypeEntity entity) {
        Resource<CSREquipmentElementTypeEntity> resource =
                new Resource<CSREquipmentElementTypeEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CSREquipmentElementTypeEntity}s.
     * @param entities the list of {@link CSREquipmentElementTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSREquipmentElementTypeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CSREquipmentElementTypeEntity>> save(
            @RequestBody final List<CSREquipmentElementTypeEntity> entities) {
        List<Resource<CSREquipmentElementTypeEntity>> resources =
                new ArrayList<Resource<CSREquipmentElementTypeEntity>>();
        Iterator<CSREquipmentElementTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CSREquipmentElementTypeEntity}.
     * @param entity the {@link CSREquipmentElementTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSREquipmentElementTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CSREquipmentElementTypeEntity>> save(
            @RequestBody final CSREquipmentElementTypeEntity entity) {
    
    
        CSREquipmentElementTypeEntity savedEntity = repository.save(entity);
        List<Resource<CSREquipmentElementTypeEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CSREquipmentElementTypeEntity} instance
     */
    private void setIdFromRepository(final CSREquipmentElementTypeEntity entity) {
        List<CSREquipmentElementTypeEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CSREquipmentElementTypeEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                System.out.println("Cannot get Entity CSREquipmentElementTypeEntity from Repository by name");
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CSREquipmentElementTypeEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CSREquipmentElementTypeEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
          System.out.println("Cannot get Entity CSREquipmentElementTypeEntity from Repository by code");
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CSREquipmentElementTypeEntity>) method.invoke(
                                repository, (String) field.get(entity));
                    }
                }
            }
            method = null;
            field = null;
        } catch (SecurityException | IllegalArgumentException
                | IllegalAccessException
                | InvocationTargetException e) {
                System.out.println("Cannot get Entity CSREquipmentElementTypeEntity from Repository by name or code");
        }
        if (old != null
                && old.size() == 1) {
            entity.setId(old.get(0).getId());
        }
        old = null;        
    }
    /**
     * Updates multiple {@link CSREquipmentElementTypeEntity}s.
     * @param entities the list of {@link CSREquipmentElementTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSREquipmentElementTypeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CSREquipmentElementTypeEntity>> update(
            @RequestBody final List<CSREquipmentElementTypeEntity> entities) {
        List<Resource<CSREquipmentElementTypeEntity>> resources = new ArrayList<Resource<CSREquipmentElementTypeEntity>>();
        Iterator<CSREquipmentElementTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CSREquipmentElementTypeEntity}.
     * @param entity the {@link CSREquipmentElementTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSREquipmentElementTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CSREquipmentElementTypeEntity>> update(
            @RequestBody final CSREquipmentElementTypeEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CSREquipmentElementTypeEntity savedEntity = repository.save(entity);
        List<Resource<CSREquipmentElementTypeEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CSREquipmentElementTypeEntity}s that share a code.
     * @param code the equipment_element_type' code
     * @return {@link List}<{@link Resource}<{@link CSREquipmentElementTypeEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<CSREquipmentElementTypeEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<CSREquipmentElementTypeEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<CSREquipmentElementTypeEntity>> resources =
                new ArrayList<Resource<CSREquipmentElementTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentElementTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSREquipmentElementTypeEntity}s that share a value.
     * @param value the equipment_element_type' value
     * @return {@link List}<{@link Resource}<{@link CSREquipmentElementTypeEntity}>>
     */
    @RequestMapping(path = "value/{value}",
            method = RequestMethod.GET)
    public List<Resource<CSREquipmentElementTypeEntity>> getByValue(
            @PathVariable final Long value) {
        Iterator<CSREquipmentElementTypeEntity> iter = repository.findByValue(value)
                .iterator();
        List<Resource<CSREquipmentElementTypeEntity>> resources =
                new ArrayList<Resource<CSREquipmentElementTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentElementTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
