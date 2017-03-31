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

import com.osrapi.models.csr.CSREquipmentItemModifierEntity;

import com.osrapi.repositories.csr.CSREquipmentItemModifierRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/csr/equipment_item_modifiers")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CSREquipmentItemModifierController {
    /** the static instance of {@link CSREquipmentItemModifierController}. */
    private static CSREquipmentItemModifierController instance;
    /**
     * Gets the static instance.
     * @return {@link CSREquipmentItemModifierController}
     */
    public static CSREquipmentItemModifierController getInstance() {
        if (instance == null) {
            new CSREquipmentItemModifierController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CSREquipmentItemModifierRepository repository;
    /** Creates a new instance of {@link CSREquipmentItemModifierController}. */
    public CSREquipmentItemModifierController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CSREquipmentItemModifierEntity}s.
     * @return {@link List}<{@link Resource}<{@link CSREquipmentItemModifierEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CSREquipmentItemModifierEntity>> getAll() {
        Iterator<CSREquipmentItemModifierEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CSREquipmentItemModifierEntity>> resources =
                new ArrayList<Resource<CSREquipmentItemModifierEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentItemModifierResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CSREquipmentItemModifierEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CSREquipmentItemModifierEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CSREquipmentItemModifierEntity>> getById(
            @PathVariable final Long id) {
        CSREquipmentItemModifierEntity entity = repository.findOne(id);
        List<Resource<CSREquipmentItemModifierEntity>> resources =
                new ArrayList<Resource<CSREquipmentItemModifierEntity>>();
        resources.add(getEquipmentItemModifierResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CSREquipmentItemModifierEntity}.
     * @param entity the {@link CSREquipmentItemModifierEntity}
     * @return {@link Resource}<{@link CSREquipmentItemModifierEntity}>
     */
    private Resource<CSREquipmentItemModifierEntity> getEquipmentItemModifierResource(
            final CSREquipmentItemModifierEntity entity) {
        Resource<CSREquipmentItemModifierEntity> resource =
                new Resource<CSREquipmentItemModifierEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CSREquipmentItemModifierEntity}s.
     * @param entities the list of {@link CSREquipmentItemModifierEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSREquipmentItemModifierEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CSREquipmentItemModifierEntity>> save(
            @RequestBody final List<CSREquipmentItemModifierEntity> entities) {
        List<Resource<CSREquipmentItemModifierEntity>> resources =
                new ArrayList<Resource<CSREquipmentItemModifierEntity>>();
        Iterator<CSREquipmentItemModifierEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CSREquipmentItemModifierEntity}.
     * @param entity the {@link CSREquipmentItemModifierEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSREquipmentItemModifierEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CSREquipmentItemModifierEntity>> save(
            @RequestBody final CSREquipmentItemModifierEntity entity) {
    
    
        CSREquipmentItemModifierEntity savedEntity = repository.save(entity);
        List<Resource<CSREquipmentItemModifierEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CSREquipmentItemModifierEntity} instance
     */
    private void setIdFromRepository(final CSREquipmentItemModifierEntity entity) {
        List<CSREquipmentItemModifierEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CSREquipmentItemModifierEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                System.out.println("Cannot get Entity CSREquipmentItemModifierEntity from Repository by name");
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CSREquipmentItemModifierEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CSREquipmentItemModifierEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
          System.out.println("Cannot get Entity CSREquipmentItemModifierEntity from Repository by code");
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CSREquipmentItemModifierEntity>) method.invoke(
                                repository, (String) field.get(entity));
                    }
                }
            }
            method = null;
            field = null;
        } catch (SecurityException | IllegalArgumentException
                | IllegalAccessException
                | InvocationTargetException e) {
                System.out.println("Cannot get Entity CSREquipmentItemModifierEntity from Repository by name or code");
        }
        if (old != null
                && old.size() == 1) {
            entity.setId(old.get(0).getId());
        }
        old = null;        
    }
    /**
     * Updates multiple {@link CSREquipmentItemModifierEntity}s.
     * @param entities the list of {@link CSREquipmentItemModifierEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSREquipmentItemModifierEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CSREquipmentItemModifierEntity>> update(
            @RequestBody final List<CSREquipmentItemModifierEntity> entities) {
        List<Resource<CSREquipmentItemModifierEntity>> resources = new ArrayList<Resource<CSREquipmentItemModifierEntity>>();
        Iterator<CSREquipmentItemModifierEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CSREquipmentItemModifierEntity}.
     * @param entity the {@link CSREquipmentItemModifierEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSREquipmentItemModifierEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CSREquipmentItemModifierEntity>> update(
            @RequestBody final CSREquipmentItemModifierEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CSREquipmentItemModifierEntity savedEntity = repository.save(entity);
        List<Resource<CSREquipmentItemModifierEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CSREquipmentItemModifierEntity}s that share a code.
     * @param code the equipment_item_modifier' code
     * @return {@link List}<{@link Resource}<{@link CSREquipmentItemModifierEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<CSREquipmentItemModifierEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<CSREquipmentItemModifierEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<CSREquipmentItemModifierEntity>> resources =
                new ArrayList<Resource<CSREquipmentItemModifierEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentItemModifierResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSREquipmentItemModifierEntity}s that share a percent.
     * @param percent the equipment_item_modifier' percent
     * @return {@link List}<{@link Resource}<{@link CSREquipmentItemModifierEntity}>>
     */
    @RequestMapping(path = "percent/{percent}",
            method = RequestMethod.GET)
    public List<Resource<CSREquipmentItemModifierEntity>> getByPercent(
            @PathVariable final Boolean percent) {
        Iterator<CSREquipmentItemModifierEntity> iter = repository.findByPercent(percent)
                .iterator();
        List<Resource<CSREquipmentItemModifierEntity>> resources =
                new ArrayList<Resource<CSREquipmentItemModifierEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentItemModifierResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSREquipmentItemModifierEntity}s that share a special.
     * @param special the equipment_item_modifier' special
     * @return {@link List}<{@link Resource}<{@link CSREquipmentItemModifierEntity}>>
     */
    @RequestMapping(path = "special/{special}",
            method = RequestMethod.GET)
    public List<Resource<CSREquipmentItemModifierEntity>> getBySpecial(
            @PathVariable final Long special) {
        Iterator<CSREquipmentItemModifierEntity> iter = repository.findBySpecial(special)
                .iterator();
        List<Resource<CSREquipmentItemModifierEntity>> resources =
                new ArrayList<Resource<CSREquipmentItemModifierEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentItemModifierResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSREquipmentItemModifierEntity}s that share a value.
     * @param value the equipment_item_modifier' value
     * @return {@link List}<{@link Resource}<{@link CSREquipmentItemModifierEntity}>>
     */
    @RequestMapping(path = "value/{value}",
            method = RequestMethod.GET)
    public List<Resource<CSREquipmentItemModifierEntity>> getByValue(
            @PathVariable final float value) {
        Iterator<CSREquipmentItemModifierEntity> iter = repository.findByValue(value)
                .iterator();
        List<Resource<CSREquipmentItemModifierEntity>> resources =
                new ArrayList<Resource<CSREquipmentItemModifierEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentItemModifierResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
