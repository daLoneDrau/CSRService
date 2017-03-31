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

import com.osrapi.models.csr.CSRNameEntity;

import com.osrapi.repositories.csr.CSRNameRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/csr/names")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CSRNameController {
    /** the static instance of {@link CSRNameController}. */
    private static CSRNameController instance;
    /**
     * Gets the static instance.
     * @return {@link CSRNameController}
     */
    public static CSRNameController getInstance() {
        if (instance == null) {
            new CSRNameController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CSRNameRepository repository;
    /** Creates a new instance of {@link CSRNameController}. */
    public CSRNameController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CSRNameEntity}s.
     * @return {@link List}<{@link Resource}<{@link CSRNameEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CSRNameEntity>> getAll() {
        Iterator<CSRNameEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CSRNameEntity>> resources =
                new ArrayList<Resource<CSRNameEntity>>();
        while (iter.hasNext()) {
            resources.add(getNameResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CSRNameEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CSRNameEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CSRNameEntity>> getById(
            @PathVariable final Long id) {
        CSRNameEntity entity = repository.findOne(id);
        List<Resource<CSRNameEntity>> resources =
                new ArrayList<Resource<CSRNameEntity>>();
        resources.add(getNameResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CSRNameEntity}.
     * @param entity the {@link CSRNameEntity}
     * @return {@link Resource}<{@link CSRNameEntity}>
     */
    private Resource<CSRNameEntity> getNameResource(
            final CSRNameEntity entity) {
        Resource<CSRNameEntity> resource =
                new Resource<CSRNameEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CSRNameEntity}s.
     * @param entities the list of {@link CSRNameEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRNameEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CSRNameEntity>> save(
            @RequestBody final List<CSRNameEntity> entities) {
        List<Resource<CSRNameEntity>> resources =
                new ArrayList<Resource<CSRNameEntity>>();
        Iterator<CSRNameEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CSRNameEntity}.
     * @param entity the {@link CSRNameEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRNameEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CSRNameEntity>> save(
            @RequestBody final CSRNameEntity entity) {
    
    
        CSRNameEntity savedEntity = repository.save(entity);
        List<Resource<CSRNameEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CSRNameEntity} instance
     */
    private void setIdFromRepository(final CSRNameEntity entity) {
        List<CSRNameEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CSRNameEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                System.out.println("Cannot get Entity CSRNameEntity from Repository by name");
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CSRNameEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CSRNameEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
          System.out.println("Cannot get Entity CSRNameEntity from Repository by code");
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CSRNameEntity>) method.invoke(
                                repository, (String) field.get(entity));
                    }
                }
            }
            method = null;
            field = null;
        } catch (SecurityException | IllegalArgumentException
                | IllegalAccessException
                | InvocationTargetException e) {
                System.out.println("Cannot get Entity CSRNameEntity from Repository by name or code");
        }
        if (old != null
                && old.size() == 1) {
            entity.setId(old.get(0).getId());
        }
        old = null;        
    }
    /**
     * Updates multiple {@link CSRNameEntity}s.
     * @param entities the list of {@link CSRNameEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRNameEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CSRNameEntity>> update(
            @RequestBody final List<CSRNameEntity> entities) {
        List<Resource<CSRNameEntity>> resources = new ArrayList<Resource<CSRNameEntity>>();
        Iterator<CSRNameEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CSRNameEntity}.
     * @param entity the {@link CSRNameEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRNameEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CSRNameEntity>> update(
            @RequestBody final CSRNameEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CSRNameEntity savedEntity = repository.save(entity);
        List<Resource<CSRNameEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CSRNameEntity}s that share a isLast.
     * @param isLast the name' isLast
     * @return {@link List}<{@link Resource}<{@link CSRNameEntity}>>
     */
    @RequestMapping(path = "is_last/{isLast}",
            method = RequestMethod.GET)
    public List<Resource<CSRNameEntity>> getByIsLast(
            @PathVariable final Boolean isLast) {
        Iterator<CSRNameEntity> iter = repository.findByIsLast(isLast)
                .iterator();
        List<Resource<CSRNameEntity>> resources =
                new ArrayList<Resource<CSRNameEntity>>();
        while (iter.hasNext()) {
            resources.add(getNameResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRNameEntity}s that share a isFemale.
     * @param isFemale the name' isFemale
     * @return {@link List}<{@link Resource}<{@link CSRNameEntity}>>
     */
    @RequestMapping(path = "is_female/{isFemale}",
            method = RequestMethod.GET)
    public List<Resource<CSRNameEntity>> getByIsFemale(
            @PathVariable final Boolean isFemale) {
        Iterator<CSRNameEntity> iter = repository.findByIsFemale(isFemale)
                .iterator();
        List<Resource<CSRNameEntity>> resources =
                new ArrayList<Resource<CSRNameEntity>>();
        while (iter.hasNext()) {
            resources.add(getNameResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRNameEntity}s that share a name.
     * @param name the name' name
     * @return {@link List}<{@link Resource}<{@link CSRNameEntity}>>
     */
    @RequestMapping(path = "name/{name}",
            method = RequestMethod.GET)
    public List<Resource<CSRNameEntity>> getByName(
            @PathVariable final String name) {
        Iterator<CSRNameEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<CSRNameEntity>> resources =
                new ArrayList<Resource<CSRNameEntity>>();
        while (iter.hasNext()) {
            resources.add(getNameResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
