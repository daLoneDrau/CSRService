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

import com.osrapi.models.csr.CSRFatherVocationEntity;
import com.osrapi.models.csr.CSRSocialClassEntity;
import com.osrapi.models.csr.CSRSkillEntity;

import com.osrapi.repositories.csr.CSRFatherVocationRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/csr/father_vocations")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CSRFatherVocationController {
    /** the static instance of {@link CSRFatherVocationController}. */
    private static CSRFatherVocationController instance;
    /**
     * Gets the static instance.
     * @return {@link CSRFatherVocationController}
     */
    public static CSRFatherVocationController getInstance() {
        if (instance == null) {
            new CSRFatherVocationController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CSRFatherVocationRepository repository;
    /** Creates a new instance of {@link CSRFatherVocationController}. */
    public CSRFatherVocationController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CSRFatherVocationEntity}s.
     * @return {@link List}<{@link Resource}<{@link CSRFatherVocationEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CSRFatherVocationEntity>> getAll() {
        Iterator<CSRFatherVocationEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CSRFatherVocationEntity>> resources =
                new ArrayList<Resource<CSRFatherVocationEntity>>();
        while (iter.hasNext()) {
            resources.add(getFatherVocationResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CSRFatherVocationEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CSRFatherVocationEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CSRFatherVocationEntity>> getById(
            @PathVariable final Long id) {
        CSRFatherVocationEntity entity = repository.findOne(id);
        List<Resource<CSRFatherVocationEntity>> resources =
                new ArrayList<Resource<CSRFatherVocationEntity>>();
        resources.add(getFatherVocationResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CSRFatherVocationEntity}.
     * @param entity the {@link CSRFatherVocationEntity}
     * @return {@link Resource}<{@link CSRFatherVocationEntity}>
     */
    private Resource<CSRFatherVocationEntity> getFatherVocationResource(
            final CSRFatherVocationEntity entity) {
        Resource<CSRFatherVocationEntity> resource =
                new Resource<CSRFatherVocationEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CSRFatherVocationEntity}s.
     * @param entities the list of {@link CSRFatherVocationEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRFatherVocationEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CSRFatherVocationEntity>> save(
            @RequestBody final List<CSRFatherVocationEntity> entities) {
        List<Resource<CSRFatherVocationEntity>> resources =
                new ArrayList<Resource<CSRFatherVocationEntity>>();
        Iterator<CSRFatherVocationEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CSRFatherVocationEntity}.
     * @param entity the {@link CSRFatherVocationEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRFatherVocationEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CSRFatherVocationEntity>> save(
            @RequestBody final CSRFatherVocationEntity entity) {
        if (entity.getStartingSkills() != null
                && !entity.getStartingSkills().isEmpty()) {
            for (int i = entity.getStartingSkills().size() - 1; i >= 0; i--) {
                CSRSkillEntity startingSkills = null;
                List<Resource<CSRSkillEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = CSRSkillController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
            System.out.println("Cannot get embedded lookup Entity CSRSkillEntity from Controller by name");
                    }
                    Field field = null;
          try {
            field = CSRSkillEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
            System.out.println("Cannot get embedded lookup Entity CSRSkillEntity from class by name");
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getStartingSkills().get(i)) != null) {
                            list = (List<Resource<CSRSkillEntity>>) method
                                    .invoke(
                                            CSRSkillController.getInstance(),
                                            (String) field.get(entity.getStartingSkills().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = CSRSkillController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              System.out.println("Cannot get embedded lookup Entity CSRSkillEntity from Controller by code");
            }
            try {
              field = CSRSkillEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              System.out.println("Cannot get embedded lookup Entity CSRSkillEntity from class by code");
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getStartingSkills().get(i)) != null) {
                                list = (List<Resource<CSRSkillEntity>>) method
                                        .invoke(
                                                CSRSkillController
                                                        .getInstance(),
                                                (String) field
                                                        .get(entity.getStartingSkills().get(i)));
                            }
                        }
                    }
                    method = null;
                    field = null;
                } catch (SecurityException | IllegalArgumentException
                        | IllegalAccessException
                        | InvocationTargetException e) {
              System.out.println("CANNOT get embedded lookup Entity CSRSkillEntity by name or code");
                }
                if (list != null
                        && !list.isEmpty()) {
                    startingSkills = list.get(0).getContent();
                }
                if (startingSkills == null) {
                    startingSkills = (CSRSkillEntity) ((Resource) CSRSkillController
                            .getInstance()
                            .save(entity.getStartingSkills().get(i)).get(0)).getContent();
                }
                entity.getStartingSkills().set(i, startingSkills);
                list = null;
            }
        }

    if (entity.getBinarySkills() != null
                && !entity.getBinarySkills().isEmpty()) {
            for (int i = entity.getBinarySkills().size() - 1; i >= 0; i--) {
                CSRSkillEntity binarySkills = null;
                List<Resource<CSRSkillEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = CSRSkillController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
            System.out.println("Cannot get embedded lookup Entity CSRSkillEntity from Controller by name");
                    }
                    Field field = null;
          try {
            field = CSRSkillEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
            System.out.println("Cannot get embedded lookup Entity CSRSkillEntity from class by name");
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getBinarySkills().get(i)) != null) {
                            list = (List<Resource<CSRSkillEntity>>) method
                                    .invoke(
                                            CSRSkillController.getInstance(),
                                            (String) field.get(entity.getBinarySkills().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = CSRSkillController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              System.out.println("Cannot get embedded lookup Entity CSRSkillEntity from Controller by code");
            }
            try {
              field = CSRSkillEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              System.out.println("Cannot get embedded lookup Entity CSRSkillEntity from class by code");
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getBinarySkills().get(i)) != null) {
                                list = (List<Resource<CSRSkillEntity>>) method
                                        .invoke(
                                                CSRSkillController
                                                        .getInstance(),
                                                (String) field
                                                        .get(entity.getBinarySkills().get(i)));
                            }
                        }
                    }
                    method = null;
                    field = null;
                } catch (SecurityException | IllegalArgumentException
                        | IllegalAccessException
                        | InvocationTargetException e) {
              System.out.println("CANNOT get embedded lookup Entity CSRSkillEntity by name or code");
                }
                if (list != null
                        && !list.isEmpty()) {
                    binarySkills = list.get(0).getContent();
                }
                if (binarySkills == null) {
                    binarySkills = (CSRSkillEntity) ((Resource) CSRSkillController
                            .getInstance()
                            .save(entity.getBinarySkills().get(i)).get(0)).getContent();
                }
                entity.getBinarySkills().set(i, binarySkills);
                list = null;
            }
        }

        if (entity.getSocialClass() != null
        && entity.getSocialClass().getId() == null) {
      setSocialClassIdFromRepository(entity);
        }


    
        CSRFatherVocationEntity savedEntity = repository.save(entity);
        List<Resource<CSRFatherVocationEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CSRFatherVocationEntity} instance
     */
    private void setIdFromRepository(final CSRFatherVocationEntity entity) {
        List<CSRFatherVocationEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CSRFatherVocationEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                System.out.println("Cannot get Entity CSRFatherVocationEntity from Repository by name");
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CSRFatherVocationEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CSRFatherVocationEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
          System.out.println("Cannot get Entity CSRFatherVocationEntity from Repository by code");
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CSRFatherVocationEntity>) method.invoke(
                                repository, (String) field.get(entity));
                    }
                }
            }
            method = null;
            field = null;
        } catch (SecurityException | IllegalArgumentException
                | IllegalAccessException
                | InvocationTargetException e) {
                System.out.println("Cannot get Entity CSRFatherVocationEntity from Repository by name or code");
        }
        if (old != null
                && old.size() == 1) {
            entity.setId(old.get(0).getId());
        }
        old = null;        
    }
    /**
     * Updates multiple {@link CSRFatherVocationEntity}s.
     * @param entities the list of {@link CSRFatherVocationEntity} instances
     * @return {@link List}<{@link Resource}<{@link CSRFatherVocationEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CSRFatherVocationEntity>> update(
            @RequestBody final List<CSRFatherVocationEntity> entities) {
        List<Resource<CSRFatherVocationEntity>> resources = new ArrayList<Resource<CSRFatherVocationEntity>>();
        Iterator<CSRFatherVocationEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CSRFatherVocationEntity}.
     * @param entity the {@link CSRFatherVocationEntity} instance
     * @return {@link List}<{@link Resource}<{@link CSRFatherVocationEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CSRFatherVocationEntity>> update(
            @RequestBody final CSRFatherVocationEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
        if (entity.getStartingSkills() != null
                && !entity.getStartingSkills().isEmpty()) {
            for (int i = entity.getStartingSkills().size() - 1; i >= 0; i--) {
                CSRSkillEntity startingSkills = null;
                List<Resource<CSRSkillEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = CSRSkillController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
            System.out.println("Cannot get embedded lookup Entity CSRSkillEntity from Controller by name");
                    }
                    Field field = null;
          try {
            field = CSRSkillEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
            System.out.println("Cannot get embedded lookup Entity CSRSkillEntity from class by name");
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getStartingSkills().get(i)) != null) {
                            list = (List<Resource<CSRSkillEntity>>) method
                                    .invoke(
                                            CSRSkillController.getInstance(),
                                            (String) field.get(entity.getStartingSkills().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = CSRSkillController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              System.out.println("Cannot get embedded lookup Entity CSRSkillEntity from Controller by code");
            }
            try {
              field = CSRSkillEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              System.out.println("Cannot get embedded lookup Entity CSRSkillEntity from class by code");
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getStartingSkills().get(i)) != null) {
                                list = (List<Resource<CSRSkillEntity>>) method
                                        .invoke(
                                                CSRSkillController
                                                        .getInstance(),
                                                (String) field
                                                        .get(entity.getStartingSkills().get(i)));
                            }
                        }
                    }
                    method = null;
                    field = null;
                } catch (SecurityException | IllegalArgumentException
                        | IllegalAccessException
                        | InvocationTargetException e) {
              System.out.println("CANNOT get embedded lookup Entity CSRSkillEntity by name or code");
                }
                if (list != null
                        && !list.isEmpty()) {
                    startingSkills = list.get(0).getContent();
                }
                if (startingSkills == null) {
                    startingSkills = (CSRSkillEntity) ((Resource) CSRSkillController
                            .getInstance()
                            .save(entity.getStartingSkills().get(i)).get(0)).getContent();
                }
                entity.getStartingSkills().set(i, startingSkills);
                list = null;
            }
        }

    if (entity.getBinarySkills() != null
                && !entity.getBinarySkills().isEmpty()) {
            for (int i = entity.getBinarySkills().size() - 1; i >= 0; i--) {
                CSRSkillEntity binarySkills = null;
                List<Resource<CSRSkillEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = CSRSkillController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
            System.out.println("Cannot get embedded lookup Entity CSRSkillEntity from Controller by name");
                    }
                    Field field = null;
          try {
            field = CSRSkillEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
            System.out.println("Cannot get embedded lookup Entity CSRSkillEntity from class by name");
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getBinarySkills().get(i)) != null) {
                            list = (List<Resource<CSRSkillEntity>>) method
                                    .invoke(
                                            CSRSkillController.getInstance(),
                                            (String) field.get(entity.getBinarySkills().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = CSRSkillController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              System.out.println("Cannot get embedded lookup Entity CSRSkillEntity from Controller by code");
            }
            try {
              field = CSRSkillEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              System.out.println("Cannot get embedded lookup Entity CSRSkillEntity from class by code");
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getBinarySkills().get(i)) != null) {
                                list = (List<Resource<CSRSkillEntity>>) method
                                        .invoke(
                                                CSRSkillController
                                                        .getInstance(),
                                                (String) field
                                                        .get(entity.getBinarySkills().get(i)));
                            }
                        }
                    }
                    method = null;
                    field = null;
                } catch (SecurityException | IllegalArgumentException
                        | IllegalAccessException
                        | InvocationTargetException e) {
              System.out.println("CANNOT get embedded lookup Entity CSRSkillEntity by name or code");
                }
                if (list != null
                        && !list.isEmpty()) {
                    binarySkills = list.get(0).getContent();
                }
                if (binarySkills == null) {
                    binarySkills = (CSRSkillEntity) ((Resource) CSRSkillController
                            .getInstance()
                            .save(entity.getBinarySkills().get(i)).get(0)).getContent();
                }
                entity.getBinarySkills().set(i, binarySkills);
                list = null;
            }
        }

        if (entity.getSocialClass() != null
        && entity.getSocialClass().getId() == null) {
      setSocialClassIdFromRepository(entity);
        }


    
        CSRFatherVocationEntity savedEntity = repository.save(entity);
        List<Resource<CSRFatherVocationEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

  private void setSocialClassIdFromRepository(
      final CSRFatherVocationEntity entity) {
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
     * Gets a list of {@link CSRFatherVocationEntity}s that share a name.
     * @param name the father_vocation' name
     * @return {@link List}<{@link Resource}<{@link CSRFatherVocationEntity}>>
     */
    @RequestMapping(path = "name/{name}",
            method = RequestMethod.GET)
    public List<Resource<CSRFatherVocationEntity>> getByName(
            @PathVariable final String name) {
        Iterator<CSRFatherVocationEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<CSRFatherVocationEntity>> resources =
                new ArrayList<Resource<CSRFatherVocationEntity>>();
        while (iter.hasNext()) {
            resources.add(getFatherVocationResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRFatherVocationEntity}s that share a thievesGuildStatus.
     * @param thievesGuildStatus the father_vocation' thievesGuildStatus
     * @return {@link List}<{@link Resource}<{@link CSRFatherVocationEntity}>>
     */
    @RequestMapping(path = "thieves_guild_status/{thievesGuildStatus}",
            method = RequestMethod.GET)
    public List<Resource<CSRFatherVocationEntity>> getByThievesGuildStatus(
            @PathVariable final Long thievesGuildStatus) {
        Iterator<CSRFatherVocationEntity> iter = repository.findByThievesGuildStatus(thievesGuildStatus)
                .iterator();
        List<Resource<CSRFatherVocationEntity>> resources =
                new ArrayList<Resource<CSRFatherVocationEntity>>();
        while (iter.hasNext()) {
            resources.add(getFatherVocationResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRFatherVocationEntity}s that share a socialStatus.
     * @param socialStatus the father_vocation' socialStatus
     * @return {@link List}<{@link Resource}<{@link CSRFatherVocationEntity}>>
     */
    @RequestMapping(path = "social_status/{socialStatus}",
            method = RequestMethod.GET)
    public List<Resource<CSRFatherVocationEntity>> getBySocialStatus(
            @PathVariable final Long socialStatus) {
        Iterator<CSRFatherVocationEntity> iter = repository.findBySocialStatus(socialStatus)
                .iterator();
        List<Resource<CSRFatherVocationEntity>> resources =
                new ArrayList<Resource<CSRFatherVocationEntity>>();
        while (iter.hasNext()) {
            resources.add(getFatherVocationResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRFatherVocationEntity}s that share a numStartingAnimalSkills.
     * @param numStartingAnimalSkills the father_vocation' numStartingAnimalSkills
     * @return {@link List}<{@link Resource}<{@link CSRFatherVocationEntity}>>
     */
    @RequestMapping(path = "num_starting_animal_skills/{numStartingAnimalSkills}",
            method = RequestMethod.GET)
    public List<Resource<CSRFatherVocationEntity>> getByNumStartingAnimalSkills(
            @PathVariable final Long numStartingAnimalSkills) {
        Iterator<CSRFatherVocationEntity> iter = repository.findByNumStartingAnimalSkills(numStartingAnimalSkills)
                .iterator();
        List<Resource<CSRFatherVocationEntity>> resources =
                new ArrayList<Resource<CSRFatherVocationEntity>>();
        while (iter.hasNext()) {
            resources.add(getFatherVocationResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRFatherVocationEntity}s that share a numStartingAgriculturalSkills.
     * @param numStartingAgriculturalSkills the father_vocation' numStartingAgriculturalSkills
     * @return {@link List}<{@link Resource}<{@link CSRFatherVocationEntity}>>
     */
    @RequestMapping(path = "num_starting_agricultural_skills/{numStartingAgriculturalSkills}",
            method = RequestMethod.GET)
    public List<Resource<CSRFatherVocationEntity>> getByNumStartingAgriculturalSkills(
            @PathVariable final Long numStartingAgriculturalSkills) {
        Iterator<CSRFatherVocationEntity> iter = repository.findByNumStartingAgriculturalSkills(numStartingAgriculturalSkills)
                .iterator();
        List<Resource<CSRFatherVocationEntity>> resources =
                new ArrayList<Resource<CSRFatherVocationEntity>>();
        while (iter.hasNext()) {
            resources.add(getFatherVocationResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRFatherVocationEntity}s that share a numStartingCombatSkills.
     * @param numStartingCombatSkills the father_vocation' numStartingCombatSkills
     * @return {@link List}<{@link Resource}<{@link CSRFatherVocationEntity}>>
     */
    @RequestMapping(path = "num_starting_combat_skills/{numStartingCombatSkills}",
            method = RequestMethod.GET)
    public List<Resource<CSRFatherVocationEntity>> getByNumStartingCombatSkills(
            @PathVariable final Long numStartingCombatSkills) {
        Iterator<CSRFatherVocationEntity> iter = repository.findByNumStartingCombatSkills(numStartingCombatSkills)
                .iterator();
        List<Resource<CSRFatherVocationEntity>> resources =
                new ArrayList<Resource<CSRFatherVocationEntity>>();
        while (iter.hasNext()) {
            resources.add(getFatherVocationResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRFatherVocationEntity}s that share a numStartingOutdoorSkills.
     * @param numStartingOutdoorSkills the father_vocation' numStartingOutdoorSkills
     * @return {@link List}<{@link Resource}<{@link CSRFatherVocationEntity}>>
     */
    @RequestMapping(path = "num_starting_outdoor_skills/{numStartingOutdoorSkills}",
            method = RequestMethod.GET)
    public List<Resource<CSRFatherVocationEntity>> getByNumStartingOutdoorSkills(
            @PathVariable final Long numStartingOutdoorSkills) {
        Iterator<CSRFatherVocationEntity> iter = repository.findByNumStartingOutdoorSkills(numStartingOutdoorSkills)
                .iterator();
        List<Resource<CSRFatherVocationEntity>> resources =
                new ArrayList<Resource<CSRFatherVocationEntity>>();
        while (iter.hasNext()) {
            resources.add(getFatherVocationResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRFatherVocationEntity}s that share a numStartingThieverySkills.
     * @param numStartingThieverySkills the father_vocation' numStartingThieverySkills
     * @return {@link List}<{@link Resource}<{@link CSRFatherVocationEntity}>>
     */
    @RequestMapping(path = "num_starting_thievery_skills/{numStartingThieverySkills}",
            method = RequestMethod.GET)
    public List<Resource<CSRFatherVocationEntity>> getByNumStartingThieverySkills(
            @PathVariable final Long numStartingThieverySkills) {
        Iterator<CSRFatherVocationEntity> iter = repository.findByNumStartingThieverySkills(numStartingThieverySkills)
                .iterator();
        List<Resource<CSRFatherVocationEntity>> resources =
                new ArrayList<Resource<CSRFatherVocationEntity>>();
        while (iter.hasNext()) {
            resources.add(getFatherVocationResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRFatherVocationEntity}s that share a numStartingBonusSkills.
     * @param numStartingBonusSkills the father_vocation' numStartingBonusSkills
     * @return {@link List}<{@link Resource}<{@link CSRFatherVocationEntity}>>
     */
    @RequestMapping(path = "num_starting_bonus_skills/{numStartingBonusSkills}",
            method = RequestMethod.GET)
    public List<Resource<CSRFatherVocationEntity>> getByNumStartingBonusSkills(
            @PathVariable final Long numStartingBonusSkills) {
        Iterator<CSRFatherVocationEntity> iter = repository.findByNumStartingBonusSkills(numStartingBonusSkills)
                .iterator();
        List<Resource<CSRFatherVocationEntity>> resources =
                new ArrayList<Resource<CSRFatherVocationEntity>>();
        while (iter.hasNext()) {
            resources.add(getFatherVocationResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRFatherVocationEntity}s that share a rollMin.
     * @param rollMin the father_vocation' rollMin
     * @return {@link List}<{@link Resource}<{@link CSRFatherVocationEntity}>>
     */
    @RequestMapping(path = "roll_min/{rollMin}",
            method = RequestMethod.GET)
    public List<Resource<CSRFatherVocationEntity>> getByRollMin(
            @PathVariable final Long rollMin) {
        Iterator<CSRFatherVocationEntity> iter = repository.findByRollMin(rollMin)
                .iterator();
        List<Resource<CSRFatherVocationEntity>> resources =
                new ArrayList<Resource<CSRFatherVocationEntity>>();
        while (iter.hasNext()) {
            resources.add(getFatherVocationResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRFatherVocationEntity}s that share a rollMax.
     * @param rollMax the father_vocation' rollMax
     * @return {@link List}<{@link Resource}<{@link CSRFatherVocationEntity}>>
     */
    @RequestMapping(path = "roll_max/{rollMax}",
            method = RequestMethod.GET)
    public List<Resource<CSRFatherVocationEntity>> getByRollMax(
            @PathVariable final Long rollMax) {
        Iterator<CSRFatherVocationEntity> iter = repository.findByRollMax(rollMax)
                .iterator();
        List<Resource<CSRFatherVocationEntity>> resources =
                new ArrayList<Resource<CSRFatherVocationEntity>>();
        while (iter.hasNext()) {
            resources.add(getFatherVocationResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CSRFatherVocationEntity}s that share a isLiveried.
     * @param isLiveried the father_vocation' isLiveried
     * @return {@link List}<{@link Resource}<{@link CSRFatherVocationEntity}>>
     */
    @RequestMapping(path = "is_liveried/{isLiveried}",
            method = RequestMethod.GET)
    public List<Resource<CSRFatherVocationEntity>> getByIsLiveried(
            @PathVariable final Boolean isLiveried) {
        Iterator<CSRFatherVocationEntity> iter = repository.findByIsLiveried(isLiveried)
                .iterator();
        List<Resource<CSRFatherVocationEntity>> resources =
                new ArrayList<Resource<CSRFatherVocationEntity>>();
        while (iter.hasNext()) {
            resources.add(getFatherVocationResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
