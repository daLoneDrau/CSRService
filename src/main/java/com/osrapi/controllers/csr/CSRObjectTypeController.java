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

import com.osrapi.models.csr.CSRObjectTypeEntity;

import com.osrapi.repositories.csr.CSRObjectTypeRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/csr/object_types")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CSRObjectTypeController {
    /** the static instance of {@link CSRObjectTypeController}. */
    private static CSRObjectTypeController instance;
    /**
     * Gets the static instance.
     * @return {@link CSRObjectTypeController}
     */
    public static CSRObjectTypeController getInstance() {
        if (instance == null) {
            new CSRObjectTypeController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CSRObjectTypeRepository repository;
    /** Creates a new instance of {@link CSRObjectTypeController}. */
    public CSRObjectTypeController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CSRObjectTypeEntity}s.
     * @return {@link List}<{@link Resource}<{@link CSRObjectTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CSRObjectTypeEntity>> getAll() {
        Iterator<CSRObjectTypeEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CSRObjectTypeEntity>> resources =
                new ArrayList<Resource<CSRObjectTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getObjectTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CSRObjectTypeEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CSRObjectTypeEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CSRObjectTypeEntity>> getById(
            @PathVariable final Long id) {
        CSRObjectTypeEntity entity = repository.findOne(id);
        List<Resource<CSRObjectTypeEntity>> resources =
                new ArrayList<Resource<CSRObjectTypeEntity>>();
        resources.add(getObjectTypeResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CSRObjectTypeEntity}.
     * @param entity the {@link CSRObjectTypeEntity}
     * @return {@link Resource}<{@link CSRObjectTypeEntity}>
     */
    private Resource<CSRObjectTypeEntity> getObjectTypeResource(
            final CSRObjectTypeEntity entity) {
        Resource<CSRObjectTypeEntity> resource =
                new Resource<CSRObjectTypeEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CSRObjectTypeEntity}s.
     * @param entities the list of {@link CSRObjectTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRObjectTypeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CSRObjectTypeEntity>> save(
            @RequestBody final List<CSRObjectTypeEntity> entities) {
        List<Resource<CSRObjectTypeEntity>> resources =
                new ArrayList<Resource<CSRObjectTypeEntity>>();
        Iterator<CSRObjectTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CSRObjectTypeEntity}.
     * @param entity the {@link CSRObjectTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRObjectTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CSRObjectTypeEntity>> save(
            @RequestBody final CSRObjectTypeEntity entity) {
    
    
        CSRObjectTypeEntity savedEntity = repository.save(entity);
        List<Resource<CSRObjectTypeEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CSRObjectTypeEntity} instance
     */
    private void setIdFromRepository(final CSRObjectTypeEntity entity) {
        List<CSRObjectTypeEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CSRObjectTypeEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                System.out.println("Cannot get Entity CSRObjectTypeEntity from Repository by name");
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CSRObjectTypeEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CSRObjectTypeEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
          System.out.println("Cannot get Entity CSRObjectTypeEntity from Repository by code");
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CSRObjectTypeEntity>) method.invoke(
                                repository, (String) field.get(entity));
                    }
                }
            }
            method = null;
            field = null;
        } catch (SecurityException | IllegalArgumentException
                | IllegalAccessException
                | InvocationTargetException e) {
                System.out.println("Cannot get Entity CSRObjectTypeEntity from Repository by name or code");
        }
        if (old != null
                && old.size() == 1) {
            entity.setId(old.get(0).getId());
        }
        old = null;        
    }
    /**
     * Updates multiple {@link CSRObjectTypeEntity}s.
     * @param entities the list of {@link CSRObjectTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRObjectTypeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CSRObjectTypeEntity>> update(
            @RequestBody final List<CSRObjectTypeEntity> entities) {
        List<Resource<CSRObjectTypeEntity>> resources = new ArrayList<Resource<CSRObjectTypeEntity>>();
        Iterator<CSRObjectTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CSRObjectTypeEntity}.
     * @param entity the {@link CSRObjectTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRObjectTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CSRObjectTypeEntity>> update(
            @RequestBody final CSRObjectTypeEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CSRObjectTypeEntity savedEntity = repository.save(entity);
        List<Resource<CSRObjectTypeEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CSRObjectTypeEntity}s that share a code.
     * @param code the object_type' code
     * @return {@link List}<{@link Resource}<{@link CSRObjectTypeEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<CSRObjectTypeEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<CSRObjectTypeEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<CSRObjectTypeEntity>> resources =
                new ArrayList<Resource<CSRObjectTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getObjectTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRObjectTypeEntity}s that share a flag.
     * @param flag the object_type' flag
     * @return {@link List}<{@link Resource}<{@link CSRObjectTypeEntity}>>
     */
    @RequestMapping(path = "flag/{flag}",
            method = RequestMethod.GET)
    public List<Resource<CSRObjectTypeEntity>> getByFlag(
            @PathVariable final Long flag) {
        Iterator<CSRObjectTypeEntity> iter = repository.findByFlag(flag)
                .iterator();
        List<Resource<CSRObjectTypeEntity>> resources =
                new ArrayList<Resource<CSRObjectTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getObjectTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
