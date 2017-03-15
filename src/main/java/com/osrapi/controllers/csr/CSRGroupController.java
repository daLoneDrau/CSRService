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

import com.osrapi.models.csr.CSRGroupEntity;

import com.osrapi.repositories.csr.CSRGroupRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/csr/groups")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CSRGroupController {
    /** the static instance of {@link CSRGroupController}. */
    private static CSRGroupController instance;
    /**
     * Gets the static instance.
     * @return {@link CSRGroupController}
     */
    public static CSRGroupController getInstance() {
        if (instance == null) {
            new CSRGroupController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CSRGroupRepository repository;
    /** Creates a new instance of {@link CSRGroupController}. */
    public CSRGroupController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CSRGroupEntity}s.
     * @return {@link List}<{@link Resource}<{@link CSRGroupEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CSRGroupEntity>> getAll() {
        Iterator<CSRGroupEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CSRGroupEntity>> resources =
                new ArrayList<Resource<CSRGroupEntity>>();
        while (iter.hasNext()) {
            resources.add(getGroupResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CSRGroupEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CSRGroupEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CSRGroupEntity>> getById(
            @PathVariable final Long id) {
        CSRGroupEntity entity = repository.findOne(id);
        List<Resource<CSRGroupEntity>> resources =
                new ArrayList<Resource<CSRGroupEntity>>();
        resources.add(getGroupResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CSRGroupEntity}.
     * @param entity the {@link CSRGroupEntity}
     * @return {@link Resource}<{@link CSRGroupEntity}>
     */
    private Resource<CSRGroupEntity> getGroupResource(
            final CSRGroupEntity entity) {
        Resource<CSRGroupEntity> resource =
                new Resource<CSRGroupEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CSRGroupEntity}s.
     * @param entities the list of {@link CSRGroupEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRGroupEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CSRGroupEntity>> save(
            @RequestBody final List<CSRGroupEntity> entities) {
        List<Resource<CSRGroupEntity>> resources =
                new ArrayList<Resource<CSRGroupEntity>>();
        Iterator<CSRGroupEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CSRGroupEntity}.
     * @param entity the {@link CSRGroupEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRGroupEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CSRGroupEntity>> save(
            @RequestBody final CSRGroupEntity entity) {
    
    
        CSRGroupEntity savedEntity = repository.save(entity);
        List<Resource<CSRGroupEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CSRGroupEntity} instance
     */
    private void setIdFromRepository(final CSRGroupEntity entity) {
        List<CSRGroupEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CSRGroupEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                System.out.println("Cannot get Entity CSRGroupEntity from Repository by name");
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CSRGroupEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CSRGroupEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
          System.out.println("Cannot get Entity CSRGroupEntity from Repository by code");
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CSRGroupEntity>) method.invoke(
                                repository, (String) field.get(entity));
                    }
                }
            }
            method = null;
            field = null;
        } catch (SecurityException | IllegalArgumentException
                | IllegalAccessException
                | InvocationTargetException e) {
                System.out.println("Cannot get Entity CSRGroupEntity from Repository by name or code");
        }
        if (old != null
                && old.size() == 1) {
            entity.setId(old.get(0).getId());
        }
        old = null;        
    }
    /**
     * Updates multiple {@link CSRGroupEntity}s.
     * @param entities the list of {@link CSRGroupEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRGroupEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CSRGroupEntity>> update(
            @RequestBody final List<CSRGroupEntity> entities) {
        List<Resource<CSRGroupEntity>> resources = new ArrayList<Resource<CSRGroupEntity>>();
        Iterator<CSRGroupEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CSRGroupEntity}.
     * @param entity the {@link CSRGroupEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRGroupEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CSRGroupEntity>> update(
            @RequestBody final CSRGroupEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CSRGroupEntity savedEntity = repository.save(entity);
        List<Resource<CSRGroupEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CSRGroupEntity}s that share a name.
     * @param name the group' name
     * @return {@link List}<{@link Resource}<{@link CSRGroupEntity}>>
     */
    @RequestMapping(path = "name/{name}",
            method = RequestMethod.GET)
    public List<Resource<CSRGroupEntity>> getByName(
            @PathVariable final String name) {
        Iterator<CSRGroupEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<CSRGroupEntity>> resources =
                new ArrayList<Resource<CSRGroupEntity>>();
        while (iter.hasNext()) {
            resources.add(getGroupResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
