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

import com.osrapi.models.csr.CSRAttributeEntity;

import com.osrapi.repositories.csr.CSRAttributeRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/csr/attributes")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CSRAttributeController {
    /** the static instance of {@link CSRAttributeController}. */
    private static CSRAttributeController instance;
    /**
     * Gets the static instance.
     * @return {@link CSRAttributeController}
     */
    public static CSRAttributeController getInstance() {
        if (instance == null) {
            new CSRAttributeController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CSRAttributeRepository repository;
    /** Creates a new instance of {@link CSRAttributeController}. */
    public CSRAttributeController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CSRAttributeEntity}s.
     * @return {@link List}<{@link Resource}<{@link CSRAttributeEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CSRAttributeEntity>> getAll() {
        Iterator<CSRAttributeEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CSRAttributeEntity>> resources =
                new ArrayList<Resource<CSRAttributeEntity>>();
        while (iter.hasNext()) {
            resources.add(getAttributeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CSRAttributeEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CSRAttributeEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CSRAttributeEntity>> getById(
            @PathVariable final Long id) {
        CSRAttributeEntity entity = repository.findOne(id);
        List<Resource<CSRAttributeEntity>> resources =
                new ArrayList<Resource<CSRAttributeEntity>>();
        resources.add(getAttributeResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CSRAttributeEntity}.
     * @param entity the {@link CSRAttributeEntity}
     * @return {@link Resource}<{@link CSRAttributeEntity}>
     */
    private Resource<CSRAttributeEntity> getAttributeResource(
            final CSRAttributeEntity entity) {
        Resource<CSRAttributeEntity> resource =
                new Resource<CSRAttributeEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CSRAttributeEntity}s.
     * @param entities the list of {@link CSRAttributeEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRAttributeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CSRAttributeEntity>> save(
            @RequestBody final List<CSRAttributeEntity> entities) {
        List<Resource<CSRAttributeEntity>> resources =
                new ArrayList<Resource<CSRAttributeEntity>>();
        Iterator<CSRAttributeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CSRAttributeEntity}.
     * @param entity the {@link CSRAttributeEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRAttributeEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CSRAttributeEntity>> save(
            @RequestBody final CSRAttributeEntity entity) {
    
    
        CSRAttributeEntity savedEntity = repository.save(entity);
        List<Resource<CSRAttributeEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CSRAttributeEntity} instance
     */
    private void setIdFromRepository(final CSRAttributeEntity entity) {
        List<CSRAttributeEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CSRAttributeEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                System.out.println("Cannot get Entity CSRAttributeEntity from Repository by name");
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CSRAttributeEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CSRAttributeEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
          System.out.println("Cannot get Entity CSRAttributeEntity from Repository by code");
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CSRAttributeEntity>) method.invoke(
                                repository, (String) field.get(entity));
                    }
                }
            }
            method = null;
            field = null;
        } catch (SecurityException | IllegalArgumentException
                | IllegalAccessException
                | InvocationTargetException e) {
                System.out.println("Cannot get Entity CSRAttributeEntity from Repository by name or code");
        }
        if (old != null
                && old.size() == 1) {
            entity.setId(old.get(0).getId());
        }
        old = null;        
    }
    /**
     * Updates multiple {@link CSRAttributeEntity}s.
     * @param entities the list of {@link CSRAttributeEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRAttributeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CSRAttributeEntity>> update(
            @RequestBody final List<CSRAttributeEntity> entities) {
        List<Resource<CSRAttributeEntity>> resources = new ArrayList<Resource<CSRAttributeEntity>>();
        Iterator<CSRAttributeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CSRAttributeEntity}.
     * @param entity the {@link CSRAttributeEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRAttributeEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CSRAttributeEntity>> update(
            @RequestBody final CSRAttributeEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CSRAttributeEntity savedEntity = repository.save(entity);
        List<Resource<CSRAttributeEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CSRAttributeEntity}s that share a code.
     * @param code the attribute' code
     * @return {@link List}<{@link Resource}<{@link CSRAttributeEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<CSRAttributeEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<CSRAttributeEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<CSRAttributeEntity>> resources =
                new ArrayList<Resource<CSRAttributeEntity>>();
        while (iter.hasNext()) {
            resources.add(getAttributeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRAttributeEntity}s that share a description.
     * @param description the attribute' description
     * @return {@link List}<{@link Resource}<{@link CSRAttributeEntity}>>
     */
    @RequestMapping(path = "description/{description}",
            method = RequestMethod.GET)
    public List<Resource<CSRAttributeEntity>> getByDescription(
            @PathVariable final String description) {
        Iterator<CSRAttributeEntity> iter = repository.findByDescription(description)
                .iterator();
        List<Resource<CSRAttributeEntity>> resources =
                new ArrayList<Resource<CSRAttributeEntity>>();
        while (iter.hasNext()) {
            resources.add(getAttributeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRAttributeEntity}s that share a name.
     * @param name the attribute' name
     * @return {@link List}<{@link Resource}<{@link CSRAttributeEntity}>>
     */
    @RequestMapping(path = "name/{name}",
            method = RequestMethod.GET)
    public List<Resource<CSRAttributeEntity>> getByName(
            @PathVariable final String name) {
        Iterator<CSRAttributeEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<CSRAttributeEntity>> resources =
                new ArrayList<Resource<CSRAttributeEntity>>();
        while (iter.hasNext()) {
            resources.add(getAttributeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
