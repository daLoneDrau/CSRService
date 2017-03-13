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

import com.osrapi.models.csr.CSRBirthAspectEntity;

import com.osrapi.repositories.csr.CSRBirthAspectRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/csr/birth_aspects")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CSRBirthAspectController {
    /** the static instance of {@link CSRBirthAspectController}. */
    private static CSRBirthAspectController instance;
    /**
     * Gets the static instance.
     * @return {@link CSRBirthAspectController}
     */
    public static CSRBirthAspectController getInstance() {
        if (instance == null) {
            new CSRBirthAspectController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CSRBirthAspectRepository repository;
    /** Creates a new instance of {@link CSRBirthAspectController}. */
    public CSRBirthAspectController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CSRBirthAspectEntity}s.
     * @return {@link List}<{@link Resource}<{@link CSRBirthAspectEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CSRBirthAspectEntity>> getAll() {
        Iterator<CSRBirthAspectEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CSRBirthAspectEntity>> resources =
                new ArrayList<Resource<CSRBirthAspectEntity>>();
        while (iter.hasNext()) {
            resources.add(getBirthAspectResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CSRBirthAspectEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CSRBirthAspectEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CSRBirthAspectEntity>> getById(
            @PathVariable final Long id) {
        CSRBirthAspectEntity entity = repository.findOne(id);
        List<Resource<CSRBirthAspectEntity>> resources =
                new ArrayList<Resource<CSRBirthAspectEntity>>();
        resources.add(getBirthAspectResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CSRBirthAspectEntity}.
     * @param entity the {@link CSRBirthAspectEntity}
     * @return {@link Resource}<{@link CSRBirthAspectEntity}>
     */
    private Resource<CSRBirthAspectEntity> getBirthAspectResource(
            final CSRBirthAspectEntity entity) {
        Resource<CSRBirthAspectEntity> resource =
                new Resource<CSRBirthAspectEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CSRBirthAspectEntity}s.
     * @param entities the list of {@link CSRBirthAspectEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRBirthAspectEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CSRBirthAspectEntity>> save(
            @RequestBody final List<CSRBirthAspectEntity> entities) {
        List<Resource<CSRBirthAspectEntity>> resources =
                new ArrayList<Resource<CSRBirthAspectEntity>>();
        Iterator<CSRBirthAspectEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CSRBirthAspectEntity}.
     * @param entity the {@link CSRBirthAspectEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRBirthAspectEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CSRBirthAspectEntity>> save(
            @RequestBody final CSRBirthAspectEntity entity) {
    
    
        CSRBirthAspectEntity savedEntity = repository.save(entity);
        List<Resource<CSRBirthAspectEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CSRBirthAspectEntity} instance
     */
    private void setIdFromRepository(final CSRBirthAspectEntity entity) {
        List<CSRBirthAspectEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CSRBirthAspectEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                System.out.println("Cannot get Entity CSRBirthAspectEntity from Repository by name");
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CSRBirthAspectEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CSRBirthAspectEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
          System.out.println("Cannot get Entity CSRBirthAspectEntity from Repository by code");
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CSRBirthAspectEntity>) method.invoke(
                                repository, (String) field.get(entity));
                    }
                }
            }
            method = null;
            field = null;
        } catch (SecurityException | IllegalArgumentException
                | IllegalAccessException
                | InvocationTargetException e) {
                System.out.println("Cannot get Entity CSRBirthAspectEntity from Repository by name or code");
        }
        if (old != null
                && old.size() == 1) {
            entity.setId(old.get(0).getId());
        }
        old = null;        
    }
    /**
     * Updates multiple {@link CSRBirthAspectEntity}s.
     * @param entities the list of {@link CSRBirthAspectEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRBirthAspectEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CSRBirthAspectEntity>> update(
            @RequestBody final List<CSRBirthAspectEntity> entities) {
        List<Resource<CSRBirthAspectEntity>> resources = new ArrayList<Resource<CSRBirthAspectEntity>>();
        Iterator<CSRBirthAspectEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CSRBirthAspectEntity}.
     * @param entity the {@link CSRBirthAspectEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRBirthAspectEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CSRBirthAspectEntity>> update(
            @RequestBody final CSRBirthAspectEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CSRBirthAspectEntity savedEntity = repository.save(entity);
        List<Resource<CSRBirthAspectEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CSRBirthAspectEntity}s that share a code.
     * @param code the birth_aspect' code
     * @return {@link List}<{@link Resource}<{@link CSRBirthAspectEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<CSRBirthAspectEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<CSRBirthAspectEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<CSRBirthAspectEntity>> resources =
                new ArrayList<Resource<CSRBirthAspectEntity>>();
        while (iter.hasNext()) {
            resources.add(getBirthAspectResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRBirthAspectEntity}s that share a title.
     * @param title the birth_aspect' title
     * @return {@link List}<{@link Resource}<{@link CSRBirthAspectEntity}>>
     */
    @RequestMapping(path = "title/{title}",
            method = RequestMethod.GET)
    public List<Resource<CSRBirthAspectEntity>> getByTitle(
            @PathVariable final String title) {
        Iterator<CSRBirthAspectEntity> iter = repository.findByTitle(title)
                .iterator();
        List<Resource<CSRBirthAspectEntity>> resources =
                new ArrayList<Resource<CSRBirthAspectEntity>>();
        while (iter.hasNext()) {
            resources.add(getBirthAspectResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRBirthAspectEntity}s that share a rollMin.
     * @param rollMin the birth_aspect' rollMin
     * @return {@link List}<{@link Resource}<{@link CSRBirthAspectEntity}>>
     */
    @RequestMapping(path = "roll_min/{rollMin}",
            method = RequestMethod.GET)
    public List<Resource<CSRBirthAspectEntity>> getByRollMin(
            @PathVariable final Long rollMin) {
        Iterator<CSRBirthAspectEntity> iter = repository.findByRollMin(rollMin)
                .iterator();
        List<Resource<CSRBirthAspectEntity>> resources =
                new ArrayList<Resource<CSRBirthAspectEntity>>();
        while (iter.hasNext()) {
            resources.add(getBirthAspectResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRBirthAspectEntity}s that share a rollMax.
     * @param rollMax the birth_aspect' rollMax
     * @return {@link List}<{@link Resource}<{@link CSRBirthAspectEntity}>>
     */
    @RequestMapping(path = "roll_max/{rollMax}",
            method = RequestMethod.GET)
    public List<Resource<CSRBirthAspectEntity>> getByRollMax(
            @PathVariable final Long rollMax) {
        Iterator<CSRBirthAspectEntity> iter = repository.findByRollMax(rollMax)
                .iterator();
        List<Resource<CSRBirthAspectEntity>> resources =
                new ArrayList<Resource<CSRBirthAspectEntity>>();
        while (iter.hasNext()) {
            resources.add(getBirthAspectResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRBirthAspectEntity}s that share a pointsAdjustment.
     * @param pointsAdjustment the birth_aspect' pointsAdjustment
     * @return {@link List}<{@link Resource}<{@link CSRBirthAspectEntity}>>
     */
    @RequestMapping(path = "points_adjustment/{pointsAdjustment}",
            method = RequestMethod.GET)
    public List<Resource<CSRBirthAspectEntity>> getByPointsAdjustment(
            @PathVariable final Long pointsAdjustment) {
        Iterator<CSRBirthAspectEntity> iter = repository.findByPointsAdjustment(pointsAdjustment)
                .iterator();
        List<Resource<CSRBirthAspectEntity>> resources =
                new ArrayList<Resource<CSRBirthAspectEntity>>();
        while (iter.hasNext()) {
            resources.add(getBirthAspectResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
