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

import com.osrapi.models.csr.CSRIoItemDataEntity;
import com.osrapi.models.csr.CSRGroupEntity;
import com.osrapi.models.csr.CSRObjectTypeEntity;

import com.osrapi.repositories.csr.CSRIoItemDataRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/csr/io_item_data")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CSRIoItemDataController {
    /** the static instance of {@link CSRIoItemDataController}. */
    private static CSRIoItemDataController instance;
    /**
     * Gets the static instance.
     * @return {@link CSRIoItemDataController}
     */
    public static CSRIoItemDataController getInstance() {
        if (instance == null) {
            new CSRIoItemDataController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CSRIoItemDataRepository repository;
    /** Creates a new instance of {@link CSRIoItemDataController}. */
    public CSRIoItemDataController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CSRIoItemDataEntity}s.
     * @return {@link List}<{@link Resource}<{@link CSRIoItemDataEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CSRIoItemDataEntity>> getAll() {
        Iterator<CSRIoItemDataEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CSRIoItemDataEntity>> resources =
                new ArrayList<Resource<CSRIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CSRIoItemDataEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CSRIoItemDataEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CSRIoItemDataEntity>> getById(
            @PathVariable final Long id) {
        CSRIoItemDataEntity entity = repository.findOne(id);
        List<Resource<CSRIoItemDataEntity>> resources =
                new ArrayList<Resource<CSRIoItemDataEntity>>();
        resources.add(getIoItemDataResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CSRIoItemDataEntity}.
     * @param entity the {@link CSRIoItemDataEntity}
     * @return {@link Resource}<{@link CSRIoItemDataEntity}>
     */
    private Resource<CSRIoItemDataEntity> getIoItemDataResource(
            final CSRIoItemDataEntity entity) {
        Resource<CSRIoItemDataEntity> resource =
                new Resource<CSRIoItemDataEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CSRIoItemDataEntity}s.
     * @param entities the list of {@link CSRIoItemDataEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRIoItemDataEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CSRIoItemDataEntity>> save(
            @RequestBody final List<CSRIoItemDataEntity> entities) {
        List<Resource<CSRIoItemDataEntity>> resources =
                new ArrayList<Resource<CSRIoItemDataEntity>>();
        Iterator<CSRIoItemDataEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CSRIoItemDataEntity}.
     * @param entity the {@link CSRIoItemDataEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRIoItemDataEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CSRIoItemDataEntity>> save(
            @RequestBody final CSRIoItemDataEntity entity) {
        if (entity.getGroups() != null
                && !entity.getGroups().isEmpty()) {
            for (int i = entity.getGroups().size() - 1; i >= 0; i--) {
                CSRGroupEntity groups = null;
                List<Resource<CSRGroupEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = CSRGroupController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
            System.out.println("Cannot get embedded lookup Entity CSRGroupEntity from Controller by name");
                    }
                    Field field = null;
          try {
            field = CSRGroupEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
            System.out.println("Cannot get embedded lookup Entity CSRGroupEntity from class by name");
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getGroups().get(i)) != null) {
                            list = (List<Resource<CSRGroupEntity>>) method
                                    .invoke(
                                            CSRGroupController.getInstance(),
                                            (String) field.get(entity.getGroups().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = CSRGroupController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              System.out.println("Cannot get embedded lookup Entity CSRGroupEntity from Controller by code");
            }
            try {
              field = CSRGroupEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              System.out.println("Cannot get embedded lookup Entity CSRGroupEntity from class by code");
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getGroups().get(i)) != null) {
                                list = (List<Resource<CSRGroupEntity>>) method
                                        .invoke(
                                                CSRGroupController
                                                        .getInstance(),
                                                (String) field
                                                        .get(entity.getGroups().get(i)));
                            }
                        }
                    }
                    method = null;
                    field = null;
                } catch (SecurityException | IllegalArgumentException
                        | IllegalAccessException
                        | InvocationTargetException e) {
              System.out.println("CANNOT get embedded lookup Entity CSRGroupEntity by name or code");
                }
                if (list != null
                        && !list.isEmpty()) {
                    groups = list.get(0).getContent();
                }
                if (groups == null) {
                    groups = (CSRGroupEntity) ((Resource) CSRGroupController
                            .getInstance()
                            .save(entity.getGroups().get(i)).get(0)).getContent();
                }
                entity.getGroups().set(i, groups);
                list = null;
            }
        }

    if (entity.getTypes() != null
                && !entity.getTypes().isEmpty()) {
            for (int i = entity.getTypes().size() - 1; i >= 0; i--) {
                CSRObjectTypeEntity types = null;
                List<Resource<CSRObjectTypeEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = CSRObjectTypeController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
            System.out.println("Cannot get embedded lookup Entity CSRObjectTypeEntity from Controller by name");
                    }
                    Field field = null;
          try {
            field = CSRObjectTypeEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
            System.out.println("Cannot get embedded lookup Entity CSRObjectTypeEntity from class by name");
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getTypes().get(i)) != null) {
                            list = (List<Resource<CSRObjectTypeEntity>>) method
                                    .invoke(
                                            CSRObjectTypeController.getInstance(),
                                            (String) field.get(entity.getTypes().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = CSRObjectTypeController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              System.out.println("Cannot get embedded lookup Entity CSRObjectTypeEntity from Controller by code");
            }
            try {
              field = CSRObjectTypeEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              System.out.println("Cannot get embedded lookup Entity CSRObjectTypeEntity from class by code");
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getTypes().get(i)) != null) {
                                list = (List<Resource<CSRObjectTypeEntity>>) method
                                        .invoke(
                                                CSRObjectTypeController
                                                        .getInstance(),
                                                (String) field
                                                        .get(entity.getTypes().get(i)));
                            }
                        }
                    }
                    method = null;
                    field = null;
                } catch (SecurityException | IllegalArgumentException
                        | IllegalAccessException
                        | InvocationTargetException e) {
              System.out.println("CANNOT get embedded lookup Entity CSRObjectTypeEntity by name or code");
                }
                if (list != null
                        && !list.isEmpty()) {
                    types = list.get(0).getContent();
                }
                if (types == null) {
                    types = (CSRObjectTypeEntity) ((Resource) CSRObjectTypeController
                            .getInstance()
                            .save(entity.getTypes().get(i)).get(0)).getContent();
                }
                entity.getTypes().set(i, types);
                list = null;
            }
        }


    
        CSRIoItemDataEntity savedEntity = repository.save(entity);
        List<Resource<CSRIoItemDataEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CSRIoItemDataEntity} instance
     */
    private void setIdFromRepository(final CSRIoItemDataEntity entity) {
        List<CSRIoItemDataEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CSRIoItemDataEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                System.out.println("Cannot get Entity CSRIoItemDataEntity from Repository by name");
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CSRIoItemDataEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CSRIoItemDataEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
          System.out.println("Cannot get Entity CSRIoItemDataEntity from Repository by code");
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CSRIoItemDataEntity>) method.invoke(
                                repository, (String) field.get(entity));
                    }
                }
            }
            method = null;
            field = null;
        } catch (SecurityException | IllegalArgumentException
                | IllegalAccessException
                | InvocationTargetException e) {
                System.out.println("Cannot get Entity CSRIoItemDataEntity from Repository by name or code");
        }
        if (old != null
                && old.size() == 1) {
            entity.setId(old.get(0).getId());
        }
        old = null;        
    }
    /**
     * Updates multiple {@link CSRIoItemDataEntity}s.
     * @param entities the list of {@link CSRIoItemDataEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRIoItemDataEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CSRIoItemDataEntity>> update(
            @RequestBody final List<CSRIoItemDataEntity> entities) {
        List<Resource<CSRIoItemDataEntity>> resources = new ArrayList<Resource<CSRIoItemDataEntity>>();
        Iterator<CSRIoItemDataEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CSRIoItemDataEntity}.
     * @param entity the {@link CSRIoItemDataEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRIoItemDataEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CSRIoItemDataEntity>> update(
            @RequestBody final CSRIoItemDataEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
        if (entity.getGroups() != null
                && !entity.getGroups().isEmpty()) {
            for (int i = entity.getGroups().size() - 1; i >= 0; i--) {
                CSRGroupEntity groups = null;
                List<Resource<CSRGroupEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = CSRGroupController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
            System.out.println("Cannot get embedded lookup Entity CSRGroupEntity from Controller by name");
                    }
                    Field field = null;
          try {
            field = CSRGroupEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
            System.out.println("Cannot get embedded lookup Entity CSRGroupEntity from class by name");
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getGroups().get(i)) != null) {
                            list = (List<Resource<CSRGroupEntity>>) method
                                    .invoke(
                                            CSRGroupController.getInstance(),
                                            (String) field.get(entity.getGroups().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = CSRGroupController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              System.out.println("Cannot get embedded lookup Entity CSRGroupEntity from Controller by code");
            }
            try {
              field = CSRGroupEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              System.out.println("Cannot get embedded lookup Entity CSRGroupEntity from class by code");
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getGroups().get(i)) != null) {
                                list = (List<Resource<CSRGroupEntity>>) method
                                        .invoke(
                                                CSRGroupController
                                                        .getInstance(),
                                                (String) field
                                                        .get(entity.getGroups().get(i)));
                            }
                        }
                    }
                    method = null;
                    field = null;
                } catch (SecurityException | IllegalArgumentException
                        | IllegalAccessException
                        | InvocationTargetException e) {
              System.out.println("CANNOT get embedded lookup Entity CSRGroupEntity by name or code");
                }
                if (list != null
                        && !list.isEmpty()) {
                    groups = list.get(0).getContent();
                }
                if (groups == null) {
                    groups = (CSRGroupEntity) ((Resource) CSRGroupController
                            .getInstance()
                            .save(entity.getGroups().get(i)).get(0)).getContent();
                }
                entity.getGroups().set(i, groups);
                list = null;
            }
        }

    if (entity.getTypes() != null
                && !entity.getTypes().isEmpty()) {
            for (int i = entity.getTypes().size() - 1; i >= 0; i--) {
                CSRObjectTypeEntity types = null;
                List<Resource<CSRObjectTypeEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = CSRObjectTypeController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
            System.out.println("Cannot get embedded lookup Entity CSRObjectTypeEntity from Controller by name");
                    }
                    Field field = null;
          try {
            field = CSRObjectTypeEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
            System.out.println("Cannot get embedded lookup Entity CSRObjectTypeEntity from class by name");
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getTypes().get(i)) != null) {
                            list = (List<Resource<CSRObjectTypeEntity>>) method
                                    .invoke(
                                            CSRObjectTypeController.getInstance(),
                                            (String) field.get(entity.getTypes().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = CSRObjectTypeController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              System.out.println("Cannot get embedded lookup Entity CSRObjectTypeEntity from Controller by code");
            }
            try {
              field = CSRObjectTypeEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              System.out.println("Cannot get embedded lookup Entity CSRObjectTypeEntity from class by code");
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getTypes().get(i)) != null) {
                                list = (List<Resource<CSRObjectTypeEntity>>) method
                                        .invoke(
                                                CSRObjectTypeController
                                                        .getInstance(),
                                                (String) field
                                                        .get(entity.getTypes().get(i)));
                            }
                        }
                    }
                    method = null;
                    field = null;
                } catch (SecurityException | IllegalArgumentException
                        | IllegalAccessException
                        | InvocationTargetException e) {
              System.out.println("CANNOT get embedded lookup Entity CSRObjectTypeEntity by name or code");
                }
                if (list != null
                        && !list.isEmpty()) {
                    types = list.get(0).getContent();
                }
                if (types == null) {
                    types = (CSRObjectTypeEntity) ((Resource) CSRObjectTypeController
                            .getInstance()
                            .save(entity.getTypes().get(i)).get(0)).getContent();
                }
                entity.getTypes().set(i, types);
                list = null;
            }
        }


    
        CSRIoItemDataEntity savedEntity = repository.save(entity);
        List<Resource<CSRIoItemDataEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CSRIoItemDataEntity}s that share a count.
     * @param count the io_item_data' count
     * @return {@link List}<{@link Resource}<{@link CSRIoItemDataEntity}>>
     */
    @RequestMapping(path = "count/{count}",
            method = RequestMethod.GET)
    public List<Resource<CSRIoItemDataEntity>> getByCount(
            @PathVariable final Long count) {
        Iterator<CSRIoItemDataEntity> iter = repository.findByCount(count)
                .iterator();
        List<Resource<CSRIoItemDataEntity>> resources =
                new ArrayList<Resource<CSRIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRIoItemDataEntity}s that share a description.
     * @param description the io_item_data' description
     * @return {@link List}<{@link Resource}<{@link CSRIoItemDataEntity}>>
     */
    @RequestMapping(path = "description/{description}",
            method = RequestMethod.GET)
    public List<Resource<CSRIoItemDataEntity>> getByDescription(
            @PathVariable final String description) {
        Iterator<CSRIoItemDataEntity> iter = repository.findByDescription(description)
                .iterator();
        List<Resource<CSRIoItemDataEntity>> resources =
                new ArrayList<Resource<CSRIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRIoItemDataEntity}s that share a foodValue.
     * @param foodValue the io_item_data' foodValue
     * @return {@link List}<{@link Resource}<{@link CSRIoItemDataEntity}>>
     */
    @RequestMapping(path = "food_value/{foodValue}",
            method = RequestMethod.GET)
    public List<Resource<CSRIoItemDataEntity>> getByFoodValue(
            @PathVariable final Long foodValue) {
        Iterator<CSRIoItemDataEntity> iter = repository.findByFoodValue(foodValue)
                .iterator();
        List<Resource<CSRIoItemDataEntity>> resources =
                new ArrayList<Resource<CSRIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRIoItemDataEntity}s that share a internalScript.
     * @param internalScript the io_item_data' internalScript
     * @return {@link List}<{@link Resource}<{@link CSRIoItemDataEntity}>>
     */
    @RequestMapping(path = "internal_script/{internalScript}",
            method = RequestMethod.GET)
    public List<Resource<CSRIoItemDataEntity>> getByInternalScript(
            @PathVariable final String internalScript) {
        Iterator<CSRIoItemDataEntity> iter = repository.findByInternalScript(internalScript)
                .iterator();
        List<Resource<CSRIoItemDataEntity>> resources =
                new ArrayList<Resource<CSRIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRIoItemDataEntity}s that share a leftRing.
     * @param leftRing the io_item_data' leftRing
     * @return {@link List}<{@link Resource}<{@link CSRIoItemDataEntity}>>
     */
    @RequestMapping(path = "left_ring/{leftRing}",
            method = RequestMethod.GET)
    public List<Resource<CSRIoItemDataEntity>> getByLeftRing(
            @PathVariable final Boolean leftRing) {
        Iterator<CSRIoItemDataEntity> iter = repository.findByLeftRing(leftRing)
                .iterator();
        List<Resource<CSRIoItemDataEntity>> resources =
                new ArrayList<Resource<CSRIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRIoItemDataEntity}s that share a lightValue.
     * @param lightValue the io_item_data' lightValue
     * @return {@link List}<{@link Resource}<{@link CSRIoItemDataEntity}>>
     */
    @RequestMapping(path = "light_value/{lightValue}",
            method = RequestMethod.GET)
    public List<Resource<CSRIoItemDataEntity>> getByLightValue(
            @PathVariable final Long lightValue) {
        Iterator<CSRIoItemDataEntity> iter = repository.findByLightValue(lightValue)
                .iterator();
        List<Resource<CSRIoItemDataEntity>> resources =
                new ArrayList<Resource<CSRIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRIoItemDataEntity}s that share a maxOwned.
     * @param maxOwned the io_item_data' maxOwned
     * @return {@link List}<{@link Resource}<{@link CSRIoItemDataEntity}>>
     */
    @RequestMapping(path = "max_owned/{maxOwned}",
            method = RequestMethod.GET)
    public List<Resource<CSRIoItemDataEntity>> getByMaxOwned(
            @PathVariable final Long maxOwned) {
        Iterator<CSRIoItemDataEntity> iter = repository.findByMaxOwned(maxOwned)
                .iterator();
        List<Resource<CSRIoItemDataEntity>> resources =
                new ArrayList<Resource<CSRIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRIoItemDataEntity}s that share a name.
     * @param name the io_item_data' name
     * @return {@link List}<{@link Resource}<{@link CSRIoItemDataEntity}>>
     */
    @RequestMapping(path = "name/{name}",
            method = RequestMethod.GET)
    public List<Resource<CSRIoItemDataEntity>> getByName(
            @PathVariable final String name) {
        Iterator<CSRIoItemDataEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<CSRIoItemDataEntity>> resources =
                new ArrayList<Resource<CSRIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRIoItemDataEntity}s that share a price.
     * @param price the io_item_data' price
     * @return {@link List}<{@link Resource}<{@link CSRIoItemDataEntity}>>
     */
    @RequestMapping(path = "price/{price}",
            method = RequestMethod.GET)
    public List<Resource<CSRIoItemDataEntity>> getByPrice(
            @PathVariable final float price) {
        Iterator<CSRIoItemDataEntity> iter = repository.findByPrice(price)
                .iterator();
        List<Resource<CSRIoItemDataEntity>> resources =
                new ArrayList<Resource<CSRIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRIoItemDataEntity}s that share a ringType.
     * @param ringType the io_item_data' ringType
     * @return {@link List}<{@link Resource}<{@link CSRIoItemDataEntity}>>
     */
    @RequestMapping(path = "ring_type/{ringType}",
            method = RequestMethod.GET)
    public List<Resource<CSRIoItemDataEntity>> getByRingType(
            @PathVariable final Long ringType) {
        Iterator<CSRIoItemDataEntity> iter = repository.findByRingType(ringType)
                .iterator();
        List<Resource<CSRIoItemDataEntity>> resources =
                new ArrayList<Resource<CSRIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRIoItemDataEntity}s that share a stackSize.
     * @param stackSize the io_item_data' stackSize
     * @return {@link List}<{@link Resource}<{@link CSRIoItemDataEntity}>>
     */
    @RequestMapping(path = "stack_size/{stackSize}",
            method = RequestMethod.GET)
    public List<Resource<CSRIoItemDataEntity>> getByStackSize(
            @PathVariable final Long stackSize) {
        Iterator<CSRIoItemDataEntity> iter = repository.findByStackSize(stackSize)
                .iterator();
        List<Resource<CSRIoItemDataEntity>> resources =
                new ArrayList<Resource<CSRIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRIoItemDataEntity}s that share a stealValue.
     * @param stealValue the io_item_data' stealValue
     * @return {@link List}<{@link Resource}<{@link CSRIoItemDataEntity}>>
     */
    @RequestMapping(path = "steal_value/{stealValue}",
            method = RequestMethod.GET)
    public List<Resource<CSRIoItemDataEntity>> getByStealValue(
            @PathVariable final Long stealValue) {
        Iterator<CSRIoItemDataEntity> iter = repository.findByStealValue(stealValue)
                .iterator();
        List<Resource<CSRIoItemDataEntity>> resources =
                new ArrayList<Resource<CSRIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRIoItemDataEntity}s that share a title.
     * @param title the io_item_data' title
     * @return {@link List}<{@link Resource}<{@link CSRIoItemDataEntity}>>
     */
    @RequestMapping(path = "title/{title}",
            method = RequestMethod.GET)
    public List<Resource<CSRIoItemDataEntity>> getByTitle(
            @PathVariable final String title) {
        Iterator<CSRIoItemDataEntity> iter = repository.findByTitle(title)
                .iterator();
        List<Resource<CSRIoItemDataEntity>> resources =
                new ArrayList<Resource<CSRIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRIoItemDataEntity}s that share a weight.
     * @param weight the io_item_data' weight
     * @return {@link List}<{@link Resource}<{@link CSRIoItemDataEntity}>>
     */
    @RequestMapping(path = "weight/{weight}",
            method = RequestMethod.GET)
    public List<Resource<CSRIoItemDataEntity>> getByWeight(
            @PathVariable final float weight) {
        Iterator<CSRIoItemDataEntity> iter = repository.findByWeight(weight)
                .iterator();
        List<Resource<CSRIoItemDataEntity>> resources =
                new ArrayList<Resource<CSRIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
