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

import com.osrapi.models.csr.CSRIoPcDataEntity;
import com.osrapi.models.csr.CSRBirthAspectEntity;
import com.osrapi.models.csr.CSRGenderEntity;
import com.osrapi.models.csr.CSRRaceEntity;
import com.osrapi.models.csr.CSRSocialClassEntity;
import com.osrapi.models.csr.CSRGroupEntity;
import com.osrapi.models.csr.CSRIoItemDataEntity;

import com.osrapi.repositories.csr.CSRIoPcDataRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/csr/io_pc_data")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CSRIoPcDataController {
    /** the static instance of {@link CSRIoPcDataController}. */
    private static CSRIoPcDataController instance;
    /**
     * Gets the static instance.
     * @return {@link CSRIoPcDataController}
     */
    public static CSRIoPcDataController getInstance() {
        if (instance == null) {
            new CSRIoPcDataController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CSRIoPcDataRepository repository;
    /** Creates a new instance of {@link CSRIoPcDataController}. */
    public CSRIoPcDataController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CSRIoPcDataEntity}s.
     * @return {@link List}<{@link Resource}<{@link CSRIoPcDataEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CSRIoPcDataEntity>> getAll() {
        Iterator<CSRIoPcDataEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CSRIoPcDataEntity>> resources =
                new ArrayList<Resource<CSRIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CSRIoPcDataEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CSRIoPcDataEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CSRIoPcDataEntity>> getById(
            @PathVariable final Long id) {
        CSRIoPcDataEntity entity = repository.findOne(id);
        List<Resource<CSRIoPcDataEntity>> resources =
                new ArrayList<Resource<CSRIoPcDataEntity>>();
        resources.add(getIoPcDataResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CSRIoPcDataEntity}.
     * @param entity the {@link CSRIoPcDataEntity}
     * @return {@link Resource}<{@link CSRIoPcDataEntity}>
     */
    private Resource<CSRIoPcDataEntity> getIoPcDataResource(
            final CSRIoPcDataEntity entity) {
        Resource<CSRIoPcDataEntity> resource =
                new Resource<CSRIoPcDataEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CSRIoPcDataEntity}s.
     * @param entities the list of {@link CSRIoPcDataEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRIoPcDataEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CSRIoPcDataEntity>> save(
            @RequestBody final List<CSRIoPcDataEntity> entities) {
        List<Resource<CSRIoPcDataEntity>> resources =
                new ArrayList<Resource<CSRIoPcDataEntity>>();
        Iterator<CSRIoPcDataEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CSRIoPcDataEntity}.
     * @param entity the {@link CSRIoPcDataEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRIoPcDataEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CSRIoPcDataEntity>> save(
            @RequestBody final CSRIoPcDataEntity entity) {
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

    if (entity.getInventoryItems() != null
                && !entity.getInventoryItems().isEmpty()) {
            for (int i = entity.getInventoryItems().size() - 1; i >= 0; i--) {
                CSRIoItemDataEntity inventoryItems = null;
                List<Resource<CSRIoItemDataEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = CSRIoItemDataController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
            System.out.println("Cannot get embedded lookup Entity CSRIoItemDataEntity from Controller by name");
                    }
                    Field field = null;
          try {
            field = CSRIoItemDataEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
            System.out.println("Cannot get embedded lookup Entity CSRIoItemDataEntity from class by name");
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getInventoryItems().get(i)) != null) {
                            list = (List<Resource<CSRIoItemDataEntity>>) method
                                    .invoke(
                                            CSRIoItemDataController.getInstance(),
                                            (String) field.get(entity.getInventoryItems().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = CSRIoItemDataController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              System.out.println("Cannot get embedded lookup Entity CSRIoItemDataEntity from Controller by code");
            }
            try {
              field = CSRIoItemDataEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              System.out.println("Cannot get embedded lookup Entity CSRIoItemDataEntity from class by code");
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getInventoryItems().get(i)) != null) {
                                list = (List<Resource<CSRIoItemDataEntity>>) method
                                        .invoke(
                                                CSRIoItemDataController
                                                        .getInstance(),
                                                (String) field
                                                        .get(entity.getInventoryItems().get(i)));
                            }
                        }
                    }
                    method = null;
                    field = null;
                } catch (SecurityException | IllegalArgumentException
                        | IllegalAccessException
                        | InvocationTargetException e) {
              System.out.println("CANNOT get embedded lookup Entity CSRIoItemDataEntity by name or code");
                }
                if (list != null
                        && !list.isEmpty()) {
                    inventoryItems = list.get(0).getContent();
                }
                if (inventoryItems == null) {
                    inventoryItems = (CSRIoItemDataEntity) ((Resource) CSRIoItemDataController
                            .getInstance()
                            .save(entity.getInventoryItems().get(i)).get(0)).getContent();
                }
                entity.getInventoryItems().set(i, inventoryItems);
                list = null;
            }
        }

        if (entity.getAspect() != null
        && entity.getAspect().getId() == null) {
      setAspectIdFromRepository(entity);
        }

        if (entity.getGender() != null
        && entity.getGender().getId() == null) {
      setGenderIdFromRepository(entity);
        }

        if (entity.getRace() != null
        && entity.getRace().getId() == null) {
      setRaceIdFromRepository(entity);
        }

        if (entity.getSocialClass() != null
        && entity.getSocialClass().getId() == null) {
      setSocialClassIdFromRepository(entity);
        }


    
        CSRIoPcDataEntity savedEntity = repository.save(entity);
        List<Resource<CSRIoPcDataEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CSRIoPcDataEntity} instance
     */
    private void setIdFromRepository(final CSRIoPcDataEntity entity) {
        List<CSRIoPcDataEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CSRIoPcDataEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                System.out.println("Cannot get Entity CSRIoPcDataEntity from Repository by name");
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CSRIoPcDataEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CSRIoPcDataEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
          System.out.println("Cannot get Entity CSRIoPcDataEntity from Repository by code");
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CSRIoPcDataEntity>) method.invoke(
                                repository, (String) field.get(entity));
                    }
                }
            }
            method = null;
            field = null;
        } catch (SecurityException | IllegalArgumentException
                | IllegalAccessException
                | InvocationTargetException e) {
                System.out.println("Cannot get Entity CSRIoPcDataEntity from Repository by name or code");
        }
        if (old != null
                && old.size() == 1) {
            entity.setId(old.get(0).getId());
        }
        old = null;        
    }
    /**
     * Updates multiple {@link CSRIoPcDataEntity}s.
     * @param entities the list of {@link CSRIoPcDataEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRIoPcDataEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CSRIoPcDataEntity>> update(
            @RequestBody final List<CSRIoPcDataEntity> entities) {
        List<Resource<CSRIoPcDataEntity>> resources = new ArrayList<Resource<CSRIoPcDataEntity>>();
        Iterator<CSRIoPcDataEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CSRIoPcDataEntity}.
     * @param entity the {@link CSRIoPcDataEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRIoPcDataEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CSRIoPcDataEntity>> update(
            @RequestBody final CSRIoPcDataEntity entity) {        
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

    if (entity.getInventoryItems() != null
                && !entity.getInventoryItems().isEmpty()) {
            for (int i = entity.getInventoryItems().size() - 1; i >= 0; i--) {
                CSRIoItemDataEntity inventoryItems = null;
                List<Resource<CSRIoItemDataEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = CSRIoItemDataController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
            System.out.println("Cannot get embedded lookup Entity CSRIoItemDataEntity from Controller by name");
                    }
                    Field field = null;
          try {
            field = CSRIoItemDataEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
            System.out.println("Cannot get embedded lookup Entity CSRIoItemDataEntity from class by name");
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getInventoryItems().get(i)) != null) {
                            list = (List<Resource<CSRIoItemDataEntity>>) method
                                    .invoke(
                                            CSRIoItemDataController.getInstance(),
                                            (String) field.get(entity.getInventoryItems().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = CSRIoItemDataController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              System.out.println("Cannot get embedded lookup Entity CSRIoItemDataEntity from Controller by code");
            }
            try {
              field = CSRIoItemDataEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              System.out.println("Cannot get embedded lookup Entity CSRIoItemDataEntity from class by code");
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getInventoryItems().get(i)) != null) {
                                list = (List<Resource<CSRIoItemDataEntity>>) method
                                        .invoke(
                                                CSRIoItemDataController
                                                        .getInstance(),
                                                (String) field
                                                        .get(entity.getInventoryItems().get(i)));
                            }
                        }
                    }
                    method = null;
                    field = null;
                } catch (SecurityException | IllegalArgumentException
                        | IllegalAccessException
                        | InvocationTargetException e) {
              System.out.println("CANNOT get embedded lookup Entity CSRIoItemDataEntity by name or code");
                }
                if (list != null
                        && !list.isEmpty()) {
                    inventoryItems = list.get(0).getContent();
                }
                if (inventoryItems == null) {
                    inventoryItems = (CSRIoItemDataEntity) ((Resource) CSRIoItemDataController
                            .getInstance()
                            .save(entity.getInventoryItems().get(i)).get(0)).getContent();
                }
                entity.getInventoryItems().set(i, inventoryItems);
                list = null;
            }
        }

        if (entity.getAspect() != null
        && entity.getAspect().getId() == null) {
      setAspectIdFromRepository(entity);
        }

        if (entity.getGender() != null
        && entity.getGender().getId() == null) {
      setGenderIdFromRepository(entity);
        }

        if (entity.getRace() != null
        && entity.getRace().getId() == null) {
      setRaceIdFromRepository(entity);
        }

        if (entity.getSocialClass() != null
        && entity.getSocialClass().getId() == null) {
      setSocialClassIdFromRepository(entity);
        }


    
        CSRIoPcDataEntity savedEntity = repository.save(entity);
        List<Resource<CSRIoPcDataEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

  private void setAspectIdFromRepository(
      final CSRIoPcDataEntity entity) {
    CSRBirthAspectEntity memberEntity = null;
    List<Resource<CSRBirthAspectEntity>> list = null;
    try {
      Method method = null;
      Field field = null;
      try {
        method = CSRBirthAspectController.class.getDeclaredMethod(
            "getByName", new Class[] { String.class });
        field = CSRBirthAspectEntity.class.getDeclaredField("name");
      } catch (NoSuchMethodException | NoSuchFieldException e) {
      }
      if (method != null
          && field != null) {
        field.setAccessible(true);
        if (field.get(entity.getAspect()) != null) {
          list = (List<Resource<CSRBirthAspectEntity>>) method
              .invoke(
                  CSRBirthAspectController.getInstance(),
                  (String) field
                      .get(entity.getAspect()));
        }
      }
      if (list == null) {
        try {
          method = CSRBirthAspectController.class.getDeclaredMethod(
              "getByCode", new Class[] { String.class });
          field = CSRBirthAspectEntity.class
              .getDeclaredField("code");
        } catch (NoSuchMethodException | NoSuchFieldException e) {
        }
        if (method != null
            && field != null) {
          field.setAccessible(true);
          if (field.get(entity.getAspect()) != null) {
            list = (List<Resource<CSRBirthAspectEntity>>)
                method.invoke(CSRBirthAspectController
                    .getInstance(),(String) field.get(
                        entity.getAspect()));
          }
        }
      }
      method = null;
      field = null;
    } catch (SecurityException | IllegalArgumentException
        | IllegalAccessException
        | InvocationTargetException e) {
    }
    if (list != null
        && !list.isEmpty()) {
      memberEntity = list.get(0).getContent();
    }
    if (memberEntity == null) {
      memberEntity = (CSRBirthAspectEntity)
          ((Resource) CSRBirthAspectController.getInstance().save(
              entity.getAspect()).get(0)).getContent();
    }
    entity.setAspect(memberEntity);
    list = null;
    }

  private void setGenderIdFromRepository(
      final CSRIoPcDataEntity entity) {
    CSRGenderEntity memberEntity = null;
    List<Resource<CSRGenderEntity>> list = null;
    try {
      Method method = null;
      Field field = null;
      try {
        method = CSRGenderController.class.getDeclaredMethod(
            "getByName", new Class[] { String.class });
        field = CSRGenderEntity.class.getDeclaredField("name");
      } catch (NoSuchMethodException | NoSuchFieldException e) {
      }
      if (method != null
          && field != null) {
        field.setAccessible(true);
        if (field.get(entity.getGender()) != null) {
          list = (List<Resource<CSRGenderEntity>>) method
              .invoke(
                  CSRGenderController.getInstance(),
                  (String) field
                      .get(entity.getGender()));
        }
      }
      if (list == null) {
        try {
          method = CSRGenderController.class.getDeclaredMethod(
              "getByCode", new Class[] { String.class });
          field = CSRGenderEntity.class
              .getDeclaredField("code");
        } catch (NoSuchMethodException | NoSuchFieldException e) {
        }
        if (method != null
            && field != null) {
          field.setAccessible(true);
          if (field.get(entity.getGender()) != null) {
            list = (List<Resource<CSRGenderEntity>>)
                method.invoke(CSRGenderController
                    .getInstance(),(String) field.get(
                        entity.getGender()));
          }
        }
      }
      method = null;
      field = null;
    } catch (SecurityException | IllegalArgumentException
        | IllegalAccessException
        | InvocationTargetException e) {
    }
    if (list != null
        && !list.isEmpty()) {
      memberEntity = list.get(0).getContent();
    }
    if (memberEntity == null) {
      memberEntity = (CSRGenderEntity)
          ((Resource) CSRGenderController.getInstance().save(
              entity.getGender()).get(0)).getContent();
    }
    entity.setGender(memberEntity);
    list = null;
    }

  private void setRaceIdFromRepository(
      final CSRIoPcDataEntity entity) {
    CSRRaceEntity memberEntity = null;
    List<Resource<CSRRaceEntity>> list = null;
    try {
      Method method = null;
      Field field = null;
      try {
        method = CSRRaceController.class.getDeclaredMethod(
            "getByName", new Class[] { String.class });
        field = CSRRaceEntity.class.getDeclaredField("name");
      } catch (NoSuchMethodException | NoSuchFieldException e) {
      }
      if (method != null
          && field != null) {
        field.setAccessible(true);
        if (field.get(entity.getRace()) != null) {
          list = (List<Resource<CSRRaceEntity>>) method
              .invoke(
                  CSRRaceController.getInstance(),
                  (String) field
                      .get(entity.getRace()));
        }
      }
      if (list == null) {
        try {
          method = CSRRaceController.class.getDeclaredMethod(
              "getByCode", new Class[] { String.class });
          field = CSRRaceEntity.class
              .getDeclaredField("code");
        } catch (NoSuchMethodException | NoSuchFieldException e) {
        }
        if (method != null
            && field != null) {
          field.setAccessible(true);
          if (field.get(entity.getRace()) != null) {
            list = (List<Resource<CSRRaceEntity>>)
                method.invoke(CSRRaceController
                    .getInstance(),(String) field.get(
                        entity.getRace()));
          }
        }
      }
      method = null;
      field = null;
    } catch (SecurityException | IllegalArgumentException
        | IllegalAccessException
        | InvocationTargetException e) {
    }
    if (list != null
        && !list.isEmpty()) {
      memberEntity = list.get(0).getContent();
    }
    if (memberEntity == null) {
      memberEntity = (CSRRaceEntity)
          ((Resource) CSRRaceController.getInstance().save(
              entity.getRace()).get(0)).getContent();
    }
    entity.setRace(memberEntity);
    list = null;
    }

  private void setSocialClassIdFromRepository(
      final CSRIoPcDataEntity entity) {
    CSRSocialClassEntity memberEntity = null;
    List<Resource<CSRSocialClassEntity>> list = null;
    try {
      Method method = null;
      Field field = null;
      try {
        method = CSRSocialClassController.class.getDeclaredMethod(
            "getByName", new Class[] { String.class });
        field = CSRSocialClassEntity.class.getDeclaredField("name");
      } catch (NoSuchMethodException | NoSuchFieldException e) {
      }
      if (method != null
          && field != null) {
        field.setAccessible(true);
        if (field.get(entity.getSocialClass()) != null) {
          list = (List<Resource<CSRSocialClassEntity>>) method
              .invoke(
                  CSRSocialClassController.getInstance(),
                  (String) field
                      .get(entity.getSocialClass()));
        }
      }
      if (list == null) {
        try {
          method = CSRSocialClassController.class.getDeclaredMethod(
              "getByCode", new Class[] { String.class });
          field = CSRSocialClassEntity.class
              .getDeclaredField("code");
        } catch (NoSuchMethodException | NoSuchFieldException e) {
        }
        if (method != null
            && field != null) {
          field.setAccessible(true);
          if (field.get(entity.getSocialClass()) != null) {
            list = (List<Resource<CSRSocialClassEntity>>)
                method.invoke(CSRSocialClassController
                    .getInstance(),(String) field.get(
                        entity.getSocialClass()));
          }
        }
      }
      method = null;
      field = null;
    } catch (SecurityException | IllegalArgumentException
        | IllegalAccessException
        | InvocationTargetException e) {
    }
    if (list != null
        && !list.isEmpty()) {
      memberEntity = list.get(0).getContent();
    }
    if (memberEntity == null) {
      memberEntity = (CSRSocialClassEntity)
          ((Resource) CSRSocialClassController.getInstance().save(
              entity.getSocialClass()).get(0)).getContent();
    }
    entity.setSocialClass(memberEntity);
    list = null;
    }


    /**
     * Gets a list of {@link CSRIoPcDataEntity}s that share a bags.
     * @param bags the io_pc_data' bags
     * @return {@link List}<{@link Resource}<{@link CSRIoPcDataEntity}>>
     */
    @RequestMapping(path = "bags/{bags}",
            method = RequestMethod.GET)
    public List<Resource<CSRIoPcDataEntity>> getByBags(
            @PathVariable final Long bags) {
        Iterator<CSRIoPcDataEntity> iter = repository.findByBags(bags)
                .iterator();
        List<Resource<CSRIoPcDataEntity>> resources =
                new ArrayList<Resource<CSRIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRIoPcDataEntity}s that share a flags.
     * @param flags the io_pc_data' flags
     * @return {@link List}<{@link Resource}<{@link CSRIoPcDataEntity}>>
     */
    @RequestMapping(path = "flags/{flags}",
            method = RequestMethod.GET)
    public List<Resource<CSRIoPcDataEntity>> getByFlags(
            @PathVariable final Long flags) {
        Iterator<CSRIoPcDataEntity> iter = repository.findByFlags(flags)
                .iterator();
        List<Resource<CSRIoPcDataEntity>> resources =
                new ArrayList<Resource<CSRIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRIoPcDataEntity}s that share a gold.
     * @param gold the io_pc_data' gold
     * @return {@link List}<{@link Resource}<{@link CSRIoPcDataEntity}>>
     */
    @RequestMapping(path = "gold/{gold}",
            method = RequestMethod.GET)
    public List<Resource<CSRIoPcDataEntity>> getByGold(
            @PathVariable final float gold) {
        Iterator<CSRIoPcDataEntity> iter = repository.findByGold(gold)
                .iterator();
        List<Resource<CSRIoPcDataEntity>> resources =
                new ArrayList<Resource<CSRIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRIoPcDataEntity}s that share a interfaceFlags.
     * @param interfaceFlags the io_pc_data' interfaceFlags
     * @return {@link List}<{@link Resource}<{@link CSRIoPcDataEntity}>>
     */
    @RequestMapping(path = "interface_flags/{interfaceFlags}",
            method = RequestMethod.GET)
    public List<Resource<CSRIoPcDataEntity>> getByInterfaceFlags(
            @PathVariable final Long interfaceFlags) {
        Iterator<CSRIoPcDataEntity> iter = repository.findByInterfaceFlags(interfaceFlags)
                .iterator();
        List<Resource<CSRIoPcDataEntity>> resources =
                new ArrayList<Resource<CSRIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRIoPcDataEntity}s that share a internalScript.
     * @param internalScript the io_pc_data' internalScript
     * @return {@link List}<{@link Resource}<{@link CSRIoPcDataEntity}>>
     */
    @RequestMapping(path = "internal_script/{internalScript}",
            method = RequestMethod.GET)
    public List<Resource<CSRIoPcDataEntity>> getByInternalScript(
            @PathVariable final String internalScript) {
        Iterator<CSRIoPcDataEntity> iter = repository.findByInternalScript(internalScript)
                .iterator();
        List<Resource<CSRIoPcDataEntity>> resources =
                new ArrayList<Resource<CSRIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRIoPcDataEntity}s that share a level.
     * @param level the io_pc_data' level
     * @return {@link List}<{@link Resource}<{@link CSRIoPcDataEntity}>>
     */
    @RequestMapping(path = "level/{level}",
            method = RequestMethod.GET)
    public List<Resource<CSRIoPcDataEntity>> getByLevel(
            @PathVariable final Long level) {
        Iterator<CSRIoPcDataEntity> iter = repository.findByLevel(level)
                .iterator();
        List<Resource<CSRIoPcDataEntity>> resources =
                new ArrayList<Resource<CSRIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRIoPcDataEntity}s that share a name.
     * @param name the io_pc_data' name
     * @return {@link List}<{@link Resource}<{@link CSRIoPcDataEntity}>>
     */
    @RequestMapping(path = "name/{name}",
            method = RequestMethod.GET)
    public List<Resource<CSRIoPcDataEntity>> getByName(
            @PathVariable final String name) {
        Iterator<CSRIoPcDataEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<CSRIoPcDataEntity>> resources =
                new ArrayList<Resource<CSRIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRIoPcDataEntity}s that share a xp.
     * @param xp the io_pc_data' xp
     * @return {@link List}<{@link Resource}<{@link CSRIoPcDataEntity}>>
     */
    @RequestMapping(path = "xp/{xp}",
            method = RequestMethod.GET)
    public List<Resource<CSRIoPcDataEntity>> getByXp(
            @PathVariable final Long xp) {
        Iterator<CSRIoPcDataEntity> iter = repository.findByXp(xp)
                .iterator();
        List<Resource<CSRIoPcDataEntity>> resources =
                new ArrayList<Resource<CSRIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
