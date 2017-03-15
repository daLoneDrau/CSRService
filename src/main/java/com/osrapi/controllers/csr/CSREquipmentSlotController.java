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

import com.osrapi.models.csr.CSREquipmentSlotEntity;

import com.osrapi.repositories.csr.CSREquipmentSlotRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/csr/equipment_slots")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CSREquipmentSlotController {
    /** the static instance of {@link CSREquipmentSlotController}. */
    private static CSREquipmentSlotController instance;
    /**
     * Gets the static instance.
     * @return {@link CSREquipmentSlotController}
     */
    public static CSREquipmentSlotController getInstance() {
        if (instance == null) {
            new CSREquipmentSlotController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CSREquipmentSlotRepository repository;
    /** Creates a new instance of {@link CSREquipmentSlotController}. */
    public CSREquipmentSlotController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CSREquipmentSlotEntity}s.
     * @return {@link List}<{@link Resource}<{@link CSREquipmentSlotEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CSREquipmentSlotEntity>> getAll() {
        Iterator<CSREquipmentSlotEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CSREquipmentSlotEntity>> resources =
                new ArrayList<Resource<CSREquipmentSlotEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentSlotResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CSREquipmentSlotEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CSREquipmentSlotEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CSREquipmentSlotEntity>> getById(
            @PathVariable final Long id) {
        CSREquipmentSlotEntity entity = repository.findOne(id);
        List<Resource<CSREquipmentSlotEntity>> resources =
                new ArrayList<Resource<CSREquipmentSlotEntity>>();
        resources.add(getEquipmentSlotResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CSREquipmentSlotEntity}.
     * @param entity the {@link CSREquipmentSlotEntity}
     * @return {@link Resource}<{@link CSREquipmentSlotEntity}>
     */
    private Resource<CSREquipmentSlotEntity> getEquipmentSlotResource(
            final CSREquipmentSlotEntity entity) {
        Resource<CSREquipmentSlotEntity> resource =
                new Resource<CSREquipmentSlotEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CSREquipmentSlotEntity}s.
     * @param entities the list of {@link CSREquipmentSlotEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSREquipmentSlotEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CSREquipmentSlotEntity>> save(
            @RequestBody final List<CSREquipmentSlotEntity> entities) {
        List<Resource<CSREquipmentSlotEntity>> resources =
                new ArrayList<Resource<CSREquipmentSlotEntity>>();
        Iterator<CSREquipmentSlotEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CSREquipmentSlotEntity}.
     * @param entity the {@link CSREquipmentSlotEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSREquipmentSlotEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CSREquipmentSlotEntity>> save(
            @RequestBody final CSREquipmentSlotEntity entity) {
    
    
        CSREquipmentSlotEntity savedEntity = repository.save(entity);
        List<Resource<CSREquipmentSlotEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CSREquipmentSlotEntity} instance
     */
    private void setIdFromRepository(final CSREquipmentSlotEntity entity) {
        List<CSREquipmentSlotEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CSREquipmentSlotEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                System.out.println("Cannot get Entity CSREquipmentSlotEntity from Repository by name");
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CSREquipmentSlotEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CSREquipmentSlotEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
          System.out.println("Cannot get Entity CSREquipmentSlotEntity from Repository by code");
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CSREquipmentSlotEntity>) method.invoke(
                                repository, (String) field.get(entity));
                    }
                }
            }
            method = null;
            field = null;
        } catch (SecurityException | IllegalArgumentException
                | IllegalAccessException
                | InvocationTargetException e) {
                System.out.println("Cannot get Entity CSREquipmentSlotEntity from Repository by name or code");
        }
        if (old != null
                && old.size() == 1) {
            entity.setId(old.get(0).getId());
        }
        old = null;        
    }
    /**
     * Updates multiple {@link CSREquipmentSlotEntity}s.
     * @param entities the list of {@link CSREquipmentSlotEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSREquipmentSlotEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CSREquipmentSlotEntity>> update(
            @RequestBody final List<CSREquipmentSlotEntity> entities) {
        List<Resource<CSREquipmentSlotEntity>> resources = new ArrayList<Resource<CSREquipmentSlotEntity>>();
        Iterator<CSREquipmentSlotEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CSREquipmentSlotEntity}.
     * @param entity the {@link CSREquipmentSlotEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSREquipmentSlotEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CSREquipmentSlotEntity>> update(
            @RequestBody final CSREquipmentSlotEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CSREquipmentSlotEntity savedEntity = repository.save(entity);
        List<Resource<CSREquipmentSlotEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CSREquipmentSlotEntity}s that share a code.
     * @param code the equipment_slot' code
     * @return {@link List}<{@link Resource}<{@link CSREquipmentSlotEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<CSREquipmentSlotEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<CSREquipmentSlotEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<CSREquipmentSlotEntity>> resources =
                new ArrayList<Resource<CSREquipmentSlotEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentSlotResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSREquipmentSlotEntity}s that share a value.
     * @param value the equipment_slot' value
     * @return {@link List}<{@link Resource}<{@link CSREquipmentSlotEntity}>>
     */
    @RequestMapping(path = "value/{value}",
            method = RequestMethod.GET)
    public List<Resource<CSREquipmentSlotEntity>> getByValue(
            @PathVariable final Long value) {
        Iterator<CSREquipmentSlotEntity> iter = repository.findByValue(value)
                .iterator();
        List<Resource<CSREquipmentSlotEntity>> resources =
                new ArrayList<Resource<CSREquipmentSlotEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentSlotResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
