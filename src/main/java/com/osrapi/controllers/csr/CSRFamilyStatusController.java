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

import com.osrapi.models.csr.CSRFamilyStatusEntity;

import com.osrapi.repositories.csr.CSRFamilyStatusRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/csr/family_statuss")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CSRFamilyStatusController {
    /** the static instance of {@link CSRFamilyStatusController}. */
    private static CSRFamilyStatusController instance;
    /**
     * Gets the static instance.
     * @return {@link CSRFamilyStatusController}
     */
    public static CSRFamilyStatusController getInstance() {
        if (instance == null) {
            new CSRFamilyStatusController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CSRFamilyStatusRepository repository;
    /** Creates a new instance of {@link CSRFamilyStatusController}. */
    public CSRFamilyStatusController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CSRFamilyStatusEntity}s.
     * @return {@link List}<{@link Resource}<{@link CSRFamilyStatusEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CSRFamilyStatusEntity>> getAll() {
        Iterator<CSRFamilyStatusEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CSRFamilyStatusEntity>> resources =
                new ArrayList<Resource<CSRFamilyStatusEntity>>();
        while (iter.hasNext()) {
            resources.add(getFamilyStatusResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CSRFamilyStatusEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CSRFamilyStatusEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CSRFamilyStatusEntity>> getById(
            @PathVariable final Long id) {
        CSRFamilyStatusEntity entity = repository.findOne(id);
        List<Resource<CSRFamilyStatusEntity>> resources =
                new ArrayList<Resource<CSRFamilyStatusEntity>>();
        resources.add(getFamilyStatusResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CSRFamilyStatusEntity}.
     * @param entity the {@link CSRFamilyStatusEntity}
     * @return {@link Resource}<{@link CSRFamilyStatusEntity}>
     */
    private Resource<CSRFamilyStatusEntity> getFamilyStatusResource(
            final CSRFamilyStatusEntity entity) {
        Resource<CSRFamilyStatusEntity> resource =
                new Resource<CSRFamilyStatusEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CSRFamilyStatusEntity}s.
     * @param entities the list of {@link CSRFamilyStatusEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRFamilyStatusEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CSRFamilyStatusEntity>> save(
            @RequestBody final List<CSRFamilyStatusEntity> entities) {
        List<Resource<CSRFamilyStatusEntity>> resources =
                new ArrayList<Resource<CSRFamilyStatusEntity>>();
        Iterator<CSRFamilyStatusEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CSRFamilyStatusEntity}.
     * @param entity the {@link CSRFamilyStatusEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRFamilyStatusEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CSRFamilyStatusEntity>> save(
            @RequestBody final CSRFamilyStatusEntity entity) {
    
    
        CSRFamilyStatusEntity savedEntity = repository.save(entity);
        List<Resource<CSRFamilyStatusEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CSRFamilyStatusEntity} instance
     */
    private void setIdFromRepository(final CSRFamilyStatusEntity entity) {
        List<CSRFamilyStatusEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CSRFamilyStatusEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                System.out.println("Cannot get Entity CSRFamilyStatusEntity from Repository by name");
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CSRFamilyStatusEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CSRFamilyStatusEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
          System.out.println("Cannot get Entity CSRFamilyStatusEntity from Repository by code");
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CSRFamilyStatusEntity>) method.invoke(
                                repository, (String) field.get(entity));
                    }
                }
            }
            method = null;
            field = null;
        } catch (SecurityException | IllegalArgumentException
                | IllegalAccessException
                | InvocationTargetException e) {
                System.out.println("Cannot get Entity CSRFamilyStatusEntity from Repository by name or code");
        }
        if (old != null
                && old.size() == 1) {
            entity.setId(old.get(0).getId());
        }
        old = null;        
    }
    /**
     * Updates multiple {@link CSRFamilyStatusEntity}s.
     * @param entities the list of {@link CSRFamilyStatusEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRFamilyStatusEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CSRFamilyStatusEntity>> update(
            @RequestBody final List<CSRFamilyStatusEntity> entities) {
        List<Resource<CSRFamilyStatusEntity>> resources = new ArrayList<Resource<CSRFamilyStatusEntity>>();
        Iterator<CSRFamilyStatusEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CSRFamilyStatusEntity}.
     * @param entity the {@link CSRFamilyStatusEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRFamilyStatusEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CSRFamilyStatusEntity>> update(
            @RequestBody final CSRFamilyStatusEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CSRFamilyStatusEntity savedEntity = repository.save(entity);
        List<Resource<CSRFamilyStatusEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CSRFamilyStatusEntity}s that share a code.
     * @param code the family_status's code
     * @return {@link List}<{@link Resource}<{@link CSRFamilyStatusEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<CSRFamilyStatusEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<CSRFamilyStatusEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<CSRFamilyStatusEntity>> resources =
                new ArrayList<Resource<CSRFamilyStatusEntity>>();
        while (iter.hasNext()) {
            resources.add(getFamilyStatusResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRFamilyStatusEntity}s that share a title.
     * @param title the family_status's title
     * @return {@link List}<{@link Resource}<{@link CSRFamilyStatusEntity}>>
     */
    @RequestMapping(path = "title/{title}",
            method = RequestMethod.GET)
    public List<Resource<CSRFamilyStatusEntity>> getByTitle(
            @PathVariable final String title) {
        Iterator<CSRFamilyStatusEntity> iter = repository.findByTitle(title)
                .iterator();
        List<Resource<CSRFamilyStatusEntity>> resources =
                new ArrayList<Resource<CSRFamilyStatusEntity>>();
        while (iter.hasNext()) {
            resources.add(getFamilyStatusResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRFamilyStatusEntity}s that share a rollMin.
     * @param rollMin the family_status's rollMin
     * @return {@link List}<{@link Resource}<{@link CSRFamilyStatusEntity}>>
     */
    @RequestMapping(path = "roll_min/{rollMin}",
            method = RequestMethod.GET)
    public List<Resource<CSRFamilyStatusEntity>> getByRollMin(
            @PathVariable final Long rollMin) {
        Iterator<CSRFamilyStatusEntity> iter = repository.findByRollMin(rollMin)
                .iterator();
        List<Resource<CSRFamilyStatusEntity>> resources =
                new ArrayList<Resource<CSRFamilyStatusEntity>>();
        while (iter.hasNext()) {
            resources.add(getFamilyStatusResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRFamilyStatusEntity}s that share a rollMax.
     * @param rollMax the family_status's rollMax
     * @return {@link List}<{@link Resource}<{@link CSRFamilyStatusEntity}>>
     */
    @RequestMapping(path = "roll_max/{rollMax}",
            method = RequestMethod.GET)
    public List<Resource<CSRFamilyStatusEntity>> getByRollMax(
            @PathVariable final Long rollMax) {
        Iterator<CSRFamilyStatusEntity> iter = repository.findByRollMax(rollMax)
                .iterator();
        List<Resource<CSRFamilyStatusEntity>> resources =
                new ArrayList<Resource<CSRFamilyStatusEntity>>();
        while (iter.hasNext()) {
            resources.add(getFamilyStatusResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRFamilyStatusEntity}s that share a pointsAdjustment.
     * @param pointsAdjustment the family_status's pointsAdjustment
     * @return {@link List}<{@link Resource}<{@link CSRFamilyStatusEntity}>>
     */
    @RequestMapping(path = "points_adjustment/{pointsAdjustment}",
            method = RequestMethod.GET)
    public List<Resource<CSRFamilyStatusEntity>> getByPointsAdjustment(
            @PathVariable final Long pointsAdjustment) {
        Iterator<CSRFamilyStatusEntity> iter = repository.findByPointsAdjustment(pointsAdjustment)
                .iterator();
        List<Resource<CSRFamilyStatusEntity>> resources =
                new ArrayList<Resource<CSRFamilyStatusEntity>>();
        while (iter.hasNext()) {
            resources.add(getFamilyStatusResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
