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

import com.osrapi.models.csr.CSRGenderEntity;

import com.osrapi.repositories.csr.CSRGenderRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/csr/genders")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CSRGenderController {
    /** the static instance of {@link CSRGenderController}. */
    private static CSRGenderController instance;
    /**
     * Gets the static instance.
     * @return {@link CSRGenderController}
     */
    public static CSRGenderController getInstance() {
        if (instance == null) {
            new CSRGenderController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CSRGenderRepository repository;
    /** Creates a new instance of {@link CSRGenderController}. */
    public CSRGenderController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CSRGenderEntity}s.
     * @return {@link List}<{@link Resource}<{@link CSRGenderEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CSRGenderEntity>> getAll() {
        Iterator<CSRGenderEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CSRGenderEntity>> resources =
                new ArrayList<Resource<CSRGenderEntity>>();
        while (iter.hasNext()) {
            resources.add(getGenderResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CSRGenderEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CSRGenderEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CSRGenderEntity>> getById(
            @PathVariable final Long id) {
        CSRGenderEntity entity = repository.findOne(id);
        List<Resource<CSRGenderEntity>> resources =
                new ArrayList<Resource<CSRGenderEntity>>();
        resources.add(getGenderResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CSRGenderEntity}.
     * @param entity the {@link CSRGenderEntity}
     * @return {@link Resource}<{@link CSRGenderEntity}>
     */
    private Resource<CSRGenderEntity> getGenderResource(
            final CSRGenderEntity entity) {
        Resource<CSRGenderEntity> resource =
                new Resource<CSRGenderEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CSRGenderEntity}s.
     * @param entities the list of {@link CSRGenderEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRGenderEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CSRGenderEntity>> save(
            @RequestBody final List<CSRGenderEntity> entities) {
        List<Resource<CSRGenderEntity>> resources =
                new ArrayList<Resource<CSRGenderEntity>>();
        Iterator<CSRGenderEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CSRGenderEntity}.
     * @param entity the {@link CSRGenderEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRGenderEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CSRGenderEntity>> save(
            @RequestBody final CSRGenderEntity entity) {
    
    
        CSRGenderEntity savedEntity = repository.save(entity);
        List<Resource<CSRGenderEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CSRGenderEntity} instance
     */
    private void setIdFromRepository(final CSRGenderEntity entity) {
        List<CSRGenderEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CSRGenderEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                System.out.println("Cannot get Entity CSRGenderEntity from Repository by name");
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CSRGenderEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CSRGenderEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
          System.out.println("Cannot get Entity CSRGenderEntity from Repository by code");
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CSRGenderEntity>) method.invoke(
                                repository, (String) field.get(entity));
                    }
                }
            }
            method = null;
            field = null;
        } catch (SecurityException | IllegalArgumentException
                | IllegalAccessException
                | InvocationTargetException e) {
                System.out.println("Cannot get Entity CSRGenderEntity from Repository by name or code");
        }
        if (old != null
                && old.size() == 1) {
            entity.setId(old.get(0).getId());
        }
        old = null;        
    }
    /**
     * Updates multiple {@link CSRGenderEntity}s.
     * @param entities the list of {@link CSRGenderEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRGenderEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CSRGenderEntity>> update(
            @RequestBody final List<CSRGenderEntity> entities) {
        List<Resource<CSRGenderEntity>> resources = new ArrayList<Resource<CSRGenderEntity>>();
        Iterator<CSRGenderEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CSRGenderEntity}.
     * @param entity the {@link CSRGenderEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRGenderEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CSRGenderEntity>> update(
            @RequestBody final CSRGenderEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CSRGenderEntity savedEntity = repository.save(entity);
        List<Resource<CSRGenderEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CSRGenderEntity}s that share a description.
     * @param description the gender' description
     * @return {@link List}<{@link Resource}<{@link CSRGenderEntity}>>
     */
    @RequestMapping(path = "description/{description}",
            method = RequestMethod.GET)
    public List<Resource<CSRGenderEntity>> getByDescription(
            @PathVariable final String description) {
        Iterator<CSRGenderEntity> iter = repository.findByDescription(description)
                .iterator();
        List<Resource<CSRGenderEntity>> resources =
                new ArrayList<Resource<CSRGenderEntity>>();
        while (iter.hasNext()) {
            resources.add(getGenderResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRGenderEntity}s that share a name.
     * @param name the gender' name
     * @return {@link List}<{@link Resource}<{@link CSRGenderEntity}>>
     */
    @RequestMapping(path = "name/{name}",
            method = RequestMethod.GET)
    public List<Resource<CSRGenderEntity>> getByName(
            @PathVariable final String name) {
        Iterator<CSRGenderEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<CSRGenderEntity>> resources =
                new ArrayList<Resource<CSRGenderEntity>>();
        while (iter.hasNext()) {
            resources.add(getGenderResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRGenderEntity}s that share a subjective.
     * @param subjective the gender' subjective
     * @return {@link List}<{@link Resource}<{@link CSRGenderEntity}>>
     */
    @RequestMapping(path = "subjective/{subjective}",
            method = RequestMethod.GET)
    public List<Resource<CSRGenderEntity>> getBySubjective(
            @PathVariable final String subjective) {
        Iterator<CSRGenderEntity> iter = repository.findBySubjective(subjective)
                .iterator();
        List<Resource<CSRGenderEntity>> resources =
                new ArrayList<Resource<CSRGenderEntity>>();
        while (iter.hasNext()) {
            resources.add(getGenderResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRGenderEntity}s that share a objective.
     * @param objective the gender' objective
     * @return {@link List}<{@link Resource}<{@link CSRGenderEntity}>>
     */
    @RequestMapping(path = "objective/{objective}",
            method = RequestMethod.GET)
    public List<Resource<CSRGenderEntity>> getByObjective(
            @PathVariable final String objective) {
        Iterator<CSRGenderEntity> iter = repository.findByObjective(objective)
                .iterator();
        List<Resource<CSRGenderEntity>> resources =
                new ArrayList<Resource<CSRGenderEntity>>();
        while (iter.hasNext()) {
            resources.add(getGenderResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRGenderEntity}s that share a dependentPossessive.
     * @param dependentPossessive the gender' dependentPossessive
     * @return {@link List}<{@link Resource}<{@link CSRGenderEntity}>>
     */
    @RequestMapping(path = "dependent_possessive/{dependentPossessive}",
            method = RequestMethod.GET)
    public List<Resource<CSRGenderEntity>> getByDependentPossessive(
            @PathVariable final String dependentPossessive) {
        Iterator<CSRGenderEntity> iter = repository.findByDependentPossessive(dependentPossessive)
                .iterator();
        List<Resource<CSRGenderEntity>> resources =
                new ArrayList<Resource<CSRGenderEntity>>();
        while (iter.hasNext()) {
            resources.add(getGenderResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRGenderEntity}s that share a independentPossessive.
     * @param independentPossessive the gender' independentPossessive
     * @return {@link List}<{@link Resource}<{@link CSRGenderEntity}>>
     */
    @RequestMapping(path = "independent_possessive/{independentPossessive}",
            method = RequestMethod.GET)
    public List<Resource<CSRGenderEntity>> getByIndependentPossessive(
            @PathVariable final String independentPossessive) {
        Iterator<CSRGenderEntity> iter = repository.findByIndependentPossessive(independentPossessive)
                .iterator();
        List<Resource<CSRGenderEntity>> resources =
                new ArrayList<Resource<CSRGenderEntity>>();
        while (iter.hasNext()) {
            resources.add(getGenderResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRGenderEntity}s that share a reflexive.
     * @param reflexive the gender' reflexive
     * @return {@link List}<{@link Resource}<{@link CSRGenderEntity}>>
     */
    @RequestMapping(path = "reflexive/{reflexive}",
            method = RequestMethod.GET)
    public List<Resource<CSRGenderEntity>> getByReflexive(
            @PathVariable final String reflexive) {
        Iterator<CSRGenderEntity> iter = repository.findByReflexive(reflexive)
                .iterator();
        List<Resource<CSRGenderEntity>> resources =
                new ArrayList<Resource<CSRGenderEntity>>();
        while (iter.hasNext()) {
            resources.add(getGenderResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRGenderEntity}s that share a genderOffspring.
     * @param genderOffspring the gender' genderOffspring
     * @return {@link List}<{@link Resource}<{@link CSRGenderEntity}>>
     */
    @RequestMapping(path = "gender_offspring/{genderOffspring}",
            method = RequestMethod.GET)
    public List<Resource<CSRGenderEntity>> getByGenderOffspring(
            @PathVariable final String genderOffspring) {
        Iterator<CSRGenderEntity> iter = repository.findByGenderOffspring(genderOffspring)
                .iterator();
        List<Resource<CSRGenderEntity>> resources =
                new ArrayList<Resource<CSRGenderEntity>>();
        while (iter.hasNext()) {
            resources.add(getGenderResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRGenderEntity}s that share a genderParent.
     * @param genderParent the gender' genderParent
     * @return {@link List}<{@link Resource}<{@link CSRGenderEntity}>>
     */
    @RequestMapping(path = "gender_parent/{genderParent}",
            method = RequestMethod.GET)
    public List<Resource<CSRGenderEntity>> getByGenderParent(
            @PathVariable final String genderParent) {
        Iterator<CSRGenderEntity> iter = repository.findByGenderParent(genderParent)
                .iterator();
        List<Resource<CSRGenderEntity>> resources =
                new ArrayList<Resource<CSRGenderEntity>>();
        while (iter.hasNext()) {
            resources.add(getGenderResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
