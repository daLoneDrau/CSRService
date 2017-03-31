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

import com.osrapi.models.csr.CSRSiblingRankEntity;

import com.osrapi.repositories.csr.CSRSiblingRankRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/csr/sibling_ranks")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CSRSiblingRankController {
    /** the static instance of {@link CSRSiblingRankController}. */
    private static CSRSiblingRankController instance;
    /**
     * Gets the static instance.
     * @return {@link CSRSiblingRankController}
     */
    public static CSRSiblingRankController getInstance() {
        if (instance == null) {
            new CSRSiblingRankController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CSRSiblingRankRepository repository;
    /** Creates a new instance of {@link CSRSiblingRankController}. */
    public CSRSiblingRankController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CSRSiblingRankEntity}s.
     * @return {@link List}<{@link Resource}<{@link CSRSiblingRankEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CSRSiblingRankEntity>> getAll() {
        Iterator<CSRSiblingRankEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CSRSiblingRankEntity>> resources =
                new ArrayList<Resource<CSRSiblingRankEntity>>();
        while (iter.hasNext()) {
            resources.add(getSiblingRankResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CSRSiblingRankEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CSRSiblingRankEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CSRSiblingRankEntity>> getById(
            @PathVariable final Long id) {
        CSRSiblingRankEntity entity = repository.findOne(id);
        List<Resource<CSRSiblingRankEntity>> resources =
                new ArrayList<Resource<CSRSiblingRankEntity>>();
        resources.add(getSiblingRankResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CSRSiblingRankEntity}.
     * @param entity the {@link CSRSiblingRankEntity}
     * @return {@link Resource}<{@link CSRSiblingRankEntity}>
     */
    private Resource<CSRSiblingRankEntity> getSiblingRankResource(
            final CSRSiblingRankEntity entity) {
        Resource<CSRSiblingRankEntity> resource =
                new Resource<CSRSiblingRankEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CSRSiblingRankEntity}s.
     * @param entities the list of {@link CSRSiblingRankEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRSiblingRankEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CSRSiblingRankEntity>> save(
            @RequestBody final List<CSRSiblingRankEntity> entities) {
        List<Resource<CSRSiblingRankEntity>> resources =
                new ArrayList<Resource<CSRSiblingRankEntity>>();
        Iterator<CSRSiblingRankEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CSRSiblingRankEntity}.
     * @param entity the {@link CSRSiblingRankEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRSiblingRankEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CSRSiblingRankEntity>> save(
            @RequestBody final CSRSiblingRankEntity entity) {
    
    
        CSRSiblingRankEntity savedEntity = repository.save(entity);
        List<Resource<CSRSiblingRankEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CSRSiblingRankEntity} instance
     */
    private void setIdFromRepository(final CSRSiblingRankEntity entity) {
        List<CSRSiblingRankEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CSRSiblingRankEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                System.out.println("Cannot get Entity CSRSiblingRankEntity from Repository by name");
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CSRSiblingRankEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CSRSiblingRankEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
          System.out.println("Cannot get Entity CSRSiblingRankEntity from Repository by code");
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CSRSiblingRankEntity>) method.invoke(
                                repository, (String) field.get(entity));
                    }
                }
            }
            method = null;
            field = null;
        } catch (SecurityException | IllegalArgumentException
                | IllegalAccessException
                | InvocationTargetException e) {
                System.out.println("Cannot get Entity CSRSiblingRankEntity from Repository by name or code");
        }
        if (old != null
                && old.size() == 1) {
            entity.setId(old.get(0).getId());
        }
        old = null;        
    }
    /**
     * Updates multiple {@link CSRSiblingRankEntity}s.
     * @param entities the list of {@link CSRSiblingRankEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRSiblingRankEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CSRSiblingRankEntity>> update(
            @RequestBody final List<CSRSiblingRankEntity> entities) {
        List<Resource<CSRSiblingRankEntity>> resources = new ArrayList<Resource<CSRSiblingRankEntity>>();
        Iterator<CSRSiblingRankEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CSRSiblingRankEntity}.
     * @param entity the {@link CSRSiblingRankEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRSiblingRankEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CSRSiblingRankEntity>> update(
            @RequestBody final CSRSiblingRankEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CSRSiblingRankEntity savedEntity = repository.save(entity);
        List<Resource<CSRSiblingRankEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CSRSiblingRankEntity}s that share a code.
     * @param code the sibling_rank' code
     * @return {@link List}<{@link Resource}<{@link CSRSiblingRankEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<CSRSiblingRankEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<CSRSiblingRankEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<CSRSiblingRankEntity>> resources =
                new ArrayList<Resource<CSRSiblingRankEntity>>();
        while (iter.hasNext()) {
            resources.add(getSiblingRankResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRSiblingRankEntity}s that share a title.
     * @param title the sibling_rank' title
     * @return {@link List}<{@link Resource}<{@link CSRSiblingRankEntity}>>
     */
    @RequestMapping(path = "title/{title}",
            method = RequestMethod.GET)
    public List<Resource<CSRSiblingRankEntity>> getByTitle(
            @PathVariable final String title) {
        Iterator<CSRSiblingRankEntity> iter = repository.findByTitle(title)
                .iterator();
        List<Resource<CSRSiblingRankEntity>> resources =
                new ArrayList<Resource<CSRSiblingRankEntity>>();
        while (iter.hasNext()) {
            resources.add(getSiblingRankResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRSiblingRankEntity}s that share a rollMin.
     * @param rollMin the sibling_rank' rollMin
     * @return {@link List}<{@link Resource}<{@link CSRSiblingRankEntity}>>
     */
    @RequestMapping(path = "roll_min/{rollMin}",
            method = RequestMethod.GET)
    public List<Resource<CSRSiblingRankEntity>> getByRollMin(
            @PathVariable final Long rollMin) {
        Iterator<CSRSiblingRankEntity> iter = repository.findByRollMin(rollMin)
                .iterator();
        List<Resource<CSRSiblingRankEntity>> resources =
                new ArrayList<Resource<CSRSiblingRankEntity>>();
        while (iter.hasNext()) {
            resources.add(getSiblingRankResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRSiblingRankEntity}s that share a rollMax.
     * @param rollMax the sibling_rank' rollMax
     * @return {@link List}<{@link Resource}<{@link CSRSiblingRankEntity}>>
     */
    @RequestMapping(path = "roll_max/{rollMax}",
            method = RequestMethod.GET)
    public List<Resource<CSRSiblingRankEntity>> getByRollMax(
            @PathVariable final Long rollMax) {
        Iterator<CSRSiblingRankEntity> iter = repository.findByRollMax(rollMax)
                .iterator();
        List<Resource<CSRSiblingRankEntity>> resources =
                new ArrayList<Resource<CSRSiblingRankEntity>>();
        while (iter.hasNext()) {
            resources.add(getSiblingRankResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRSiblingRankEntity}s that share a pointsAdjustment.
     * @param pointsAdjustment the sibling_rank' pointsAdjustment
     * @return {@link List}<{@link Resource}<{@link CSRSiblingRankEntity}>>
     */
    @RequestMapping(path = "points_adjustment/{pointsAdjustment}",
            method = RequestMethod.GET)
    public List<Resource<CSRSiblingRankEntity>> getByPointsAdjustment(
            @PathVariable final Long pointsAdjustment) {
        Iterator<CSRSiblingRankEntity> iter = repository.findByPointsAdjustment(pointsAdjustment)
                .iterator();
        List<Resource<CSRSiblingRankEntity>> resources =
                new ArrayList<Resource<CSRSiblingRankEntity>>();
        while (iter.hasNext()) {
            resources.add(getSiblingRankResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
