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

import com.osrapi.models.csr.CSRSocialClassEntity;

import com.osrapi.repositories.csr.CSRSocialClassRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/csr/social_classes")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CSRSocialClassController {
    /** the static instance of {@link CSRSocialClassController}. */
    private static CSRSocialClassController instance;
    /**
     * Gets the static instance.
     * @return {@link CSRSocialClassController}
     */
    public static CSRSocialClassController getInstance() {
        if (instance == null) {
            new CSRSocialClassController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CSRSocialClassRepository repository;
    /** Creates a new instance of {@link CSRSocialClassController}. */
    public CSRSocialClassController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CSRSocialClassEntity}s.
     * @return {@link List}<{@link Resource}<{@link CSRSocialClassEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CSRSocialClassEntity>> getAll() {
        Iterator<CSRSocialClassEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CSRSocialClassEntity>> resources =
                new ArrayList<Resource<CSRSocialClassEntity>>();
        while (iter.hasNext()) {
            resources.add(getSocialClassResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CSRSocialClassEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CSRSocialClassEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CSRSocialClassEntity>> getById(
            @PathVariable final Long id) {
        CSRSocialClassEntity entity = repository.findOne(id);
        List<Resource<CSRSocialClassEntity>> resources =
                new ArrayList<Resource<CSRSocialClassEntity>>();
        resources.add(getSocialClassResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CSRSocialClassEntity}.
     * @param entity the {@link CSRSocialClassEntity}
     * @return {@link Resource}<{@link CSRSocialClassEntity}>
     */
    private Resource<CSRSocialClassEntity> getSocialClassResource(
            final CSRSocialClassEntity entity) {
        Resource<CSRSocialClassEntity> resource =
                new Resource<CSRSocialClassEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CSRSocialClassEntity}s.
     * @param entities the list of {@link CSRSocialClassEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRSocialClassEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CSRSocialClassEntity>> save(
            @RequestBody final List<CSRSocialClassEntity> entities) {
        List<Resource<CSRSocialClassEntity>> resources =
                new ArrayList<Resource<CSRSocialClassEntity>>();
        Iterator<CSRSocialClassEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CSRSocialClassEntity}.
     * @param entity the {@link CSRSocialClassEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRSocialClassEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CSRSocialClassEntity>> save(
            @RequestBody final CSRSocialClassEntity entity) {
    
    
        CSRSocialClassEntity savedEntity = repository.save(entity);
        List<Resource<CSRSocialClassEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CSRSocialClassEntity} instance
     */
    private void setIdFromRepository(final CSRSocialClassEntity entity) {
        List<CSRSocialClassEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CSRSocialClassEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                System.out.println("Cannot get Entity CSRSocialClassEntity from Repository by name");
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CSRSocialClassEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CSRSocialClassEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
          System.out.println("Cannot get Entity CSRSocialClassEntity from Repository by code");
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CSRSocialClassEntity>) method.invoke(
                                repository, (String) field.get(entity));
                    }
                }
            }
            method = null;
            field = null;
        } catch (SecurityException | IllegalArgumentException
                | IllegalAccessException
                | InvocationTargetException e) {
                System.out.println("Cannot get Entity CSRSocialClassEntity from Repository by name or code");
        }
        if (old != null
                && old.size() == 1) {
            entity.setId(old.get(0).getId());
        }
        old = null;        
    }
    /**
     * Updates multiple {@link CSRSocialClassEntity}s.
     * @param entities the list of {@link CSRSocialClassEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRSocialClassEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CSRSocialClassEntity>> update(
            @RequestBody final List<CSRSocialClassEntity> entities) {
        List<Resource<CSRSocialClassEntity>> resources = new ArrayList<Resource<CSRSocialClassEntity>>();
        Iterator<CSRSocialClassEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CSRSocialClassEntity}.
     * @param entity the {@link CSRSocialClassEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRSocialClassEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CSRSocialClassEntity>> update(
            @RequestBody final CSRSocialClassEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CSRSocialClassEntity savedEntity = repository.save(entity);
        List<Resource<CSRSocialClassEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CSRSocialClassEntity}s that share a name.
     * @param name the social_class's name
     * @return {@link List}<{@link Resource}<{@link CSRSocialClassEntity}>>
     */
    @RequestMapping(path = "name/{name}",
            method = RequestMethod.GET)
    public List<Resource<CSRSocialClassEntity>> getByName(
            @PathVariable final String name) {
        Iterator<CSRSocialClassEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<CSRSocialClassEntity>> resources =
                new ArrayList<Resource<CSRSocialClassEntity>>();
        while (iter.hasNext()) {
            resources.add(getSocialClassResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRSocialClassEntity}s that share a title.
     * @param title the social_class's title
     * @return {@link List}<{@link Resource}<{@link CSRSocialClassEntity}>>
     */
    @RequestMapping(path = "title/{title}",
            method = RequestMethod.GET)
    public List<Resource<CSRSocialClassEntity>> getByTitle(
            @PathVariable final String title) {
        Iterator<CSRSocialClassEntity> iter = repository.findByTitle(title)
                .iterator();
        List<Resource<CSRSocialClassEntity>> resources =
                new ArrayList<Resource<CSRSocialClassEntity>>();
        while (iter.hasNext()) {
            resources.add(getSocialClassResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRSocialClassEntity}s that share a rollMin.
     * @param rollMin the social_class's rollMin
     * @return {@link List}<{@link Resource}<{@link CSRSocialClassEntity}>>
     */
    @RequestMapping(path = "roll_min/{rollMin}",
            method = RequestMethod.GET)
    public List<Resource<CSRSocialClassEntity>> getByRollMin(
            @PathVariable final Long rollMin) {
        Iterator<CSRSocialClassEntity> iter = repository.findByRollMin(rollMin)
                .iterator();
        List<Resource<CSRSocialClassEntity>> resources =
                new ArrayList<Resource<CSRSocialClassEntity>>();
        while (iter.hasNext()) {
            resources.add(getSocialClassResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRSocialClassEntity}s that share a rollMax.
     * @param rollMax the social_class's rollMax
     * @return {@link List}<{@link Resource}<{@link CSRSocialClassEntity}>>
     */
    @RequestMapping(path = "roll_max/{rollMax}",
            method = RequestMethod.GET)
    public List<Resource<CSRSocialClassEntity>> getByRollMax(
            @PathVariable final Long rollMax) {
        Iterator<CSRSocialClassEntity> iter = repository.findByRollMax(rollMax)
                .iterator();
        List<Resource<CSRSocialClassEntity>> resources =
                new ArrayList<Resource<CSRSocialClassEntity>>();
        while (iter.hasNext()) {
            resources.add(getSocialClassResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRSocialClassEntity}s that share a pointsAdjustment.
     * @param pointsAdjustment the social_class's pointsAdjustment
     * @return {@link List}<{@link Resource}<{@link CSRSocialClassEntity}>>
     */
    @RequestMapping(path = "points_adjustment/{pointsAdjustment}",
            method = RequestMethod.GET)
    public List<Resource<CSRSocialClassEntity>> getByPointsAdjustment(
            @PathVariable final Long pointsAdjustment) {
        Iterator<CSRSocialClassEntity> iter = repository.findByPointsAdjustment(pointsAdjustment)
                .iterator();
        List<Resource<CSRSocialClassEntity>> resources =
                new ArrayList<Resource<CSRSocialClassEntity>>();
        while (iter.hasNext()) {
            resources.add(getSocialClassResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
