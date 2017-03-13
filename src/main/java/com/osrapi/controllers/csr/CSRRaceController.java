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

import com.osrapi.models.csr.CSRRaceEntity;

import com.osrapi.repositories.csr.CSRRaceRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/csr/races")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CSRRaceController {
    /** the static instance of {@link CSRRaceController}. */
    private static CSRRaceController instance;
    /**
     * Gets the static instance.
     * @return {@link CSRRaceController}
     */
    public static CSRRaceController getInstance() {
        if (instance == null) {
            new CSRRaceController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CSRRaceRepository repository;
    /** Creates a new instance of {@link CSRRaceController}. */
    public CSRRaceController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CSRRaceEntity}s.
     * @return {@link List}<{@link Resource}<{@link CSRRaceEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CSRRaceEntity>> getAll() {
        Iterator<CSRRaceEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CSRRaceEntity>> resources =
                new ArrayList<Resource<CSRRaceEntity>>();
        while (iter.hasNext()) {
            resources.add(getRaceResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CSRRaceEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CSRRaceEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CSRRaceEntity>> getById(
            @PathVariable final Long id) {
        CSRRaceEntity entity = repository.findOne(id);
        List<Resource<CSRRaceEntity>> resources =
                new ArrayList<Resource<CSRRaceEntity>>();
        resources.add(getRaceResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CSRRaceEntity}.
     * @param entity the {@link CSRRaceEntity}
     * @return {@link Resource}<{@link CSRRaceEntity}>
     */
    private Resource<CSRRaceEntity> getRaceResource(
            final CSRRaceEntity entity) {
        Resource<CSRRaceEntity> resource =
                new Resource<CSRRaceEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CSRRaceEntity}s.
     * @param entities the list of {@link CSRRaceEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRRaceEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CSRRaceEntity>> save(
            @RequestBody final List<CSRRaceEntity> entities) {
        List<Resource<CSRRaceEntity>> resources =
                new ArrayList<Resource<CSRRaceEntity>>();
        Iterator<CSRRaceEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CSRRaceEntity}.
     * @param entity the {@link CSRRaceEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRRaceEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CSRRaceEntity>> save(
            @RequestBody final CSRRaceEntity entity) {
    
    
        CSRRaceEntity savedEntity = repository.save(entity);
        List<Resource<CSRRaceEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CSRRaceEntity} instance
     */
    private void setIdFromRepository(final CSRRaceEntity entity) {
        List<CSRRaceEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CSRRaceEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                System.out.println("Cannot get Entity CSRRaceEntity from Repository by name");
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CSRRaceEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CSRRaceEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
          System.out.println("Cannot get Entity CSRRaceEntity from Repository by code");
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CSRRaceEntity>) method.invoke(
                                repository, (String) field.get(entity));
                    }
                }
            }
            method = null;
            field = null;
        } catch (SecurityException | IllegalArgumentException
                | IllegalAccessException
                | InvocationTargetException e) {
                System.out.println("Cannot get Entity CSRRaceEntity from Repository by name or code");
        }
        if (old != null
                && old.size() == 1) {
            entity.setId(old.get(0).getId());
        }
        old = null;        
    }
    /**
     * Updates multiple {@link CSRRaceEntity}s.
     * @param entities the list of {@link CSRRaceEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRRaceEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CSRRaceEntity>> update(
            @RequestBody final List<CSRRaceEntity> entities) {
        List<Resource<CSRRaceEntity>> resources = new ArrayList<Resource<CSRRaceEntity>>();
        Iterator<CSRRaceEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CSRRaceEntity}.
     * @param entity the {@link CSRRaceEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRRaceEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CSRRaceEntity>> update(
            @RequestBody final CSRRaceEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CSRRaceEntity savedEntity = repository.save(entity);
        List<Resource<CSRRaceEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CSRRaceEntity}s that share a name.
     * @param name the race' name
     * @return {@link List}<{@link Resource}<{@link CSRRaceEntity}>>
     */
    @RequestMapping(path = "name/{name}",
            method = RequestMethod.GET)
    public List<Resource<CSRRaceEntity>> getByName(
            @PathVariable final String name) {
        Iterator<CSRRaceEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<CSRRaceEntity>> resources =
                new ArrayList<Resource<CSRRaceEntity>>();
        while (iter.hasNext()) {
            resources.add(getRaceResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
