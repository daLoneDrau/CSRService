package com.osrapi.models.csr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author drau
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
@Table(name = "io_pc_data", schema = "csr")
public final class CSRIoPcDataEntity {
    /**
     * the primary key - an autogenerated id (unique for each user in the db).
     */
    @Id
    @Column(name = "io_pc_data_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
  generator = "io_pc_data_seq")
    @SequenceGenerator(
        name = "io_pc_data_seq",
        sequenceName = "csr.io_pc_data_id_seq",
        allocationSize = 1
    )
    private Long                    id;
    /** Creates a new instance of {@link CSRIoPcDataEntity}. */
    public CSRIoPcDataEntity() {
        super();
    }
    /**
     * Gets the id.
     * @return {@link Long}
     */
    public Long getId() {
        return id;
    }
    /**
     * Sets the id.
     * @param val the new value
     */
    public void setId(final Long val) {
        id = val;
    }

    /** the fatherVocation. */
    @ManyToOne(targetEntity = CSRFatherVocationEntity.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "fatherVocation")
    @JsonProperty("father_vocation")
  @NotNull
    private CSRFatherVocationEntity    fatherVocation;
    /**
     * Gets the fatherVocation.
     * @return {@link CSRFatherVocationEntity}
     */
    public CSRFatherVocationEntity getFatherVocation() {
        return fatherVocation;
    }
    /**
     * Sets the fatherVocation.
     * @param val the new value
     */
    public void setFatherVocation(final CSRFatherVocationEntity val) {
        fatherVocation = val;
    }

    /** the xp. */
    @Column(name = "xp")
    @JsonProperty("xp")
    @NotNull
    private long                    xp;
    /**
     * Gets the xp.
     * @return {@link long}
     */
    public long getXp() {
        return xp;
    }
    /**
     * Sets the xp.
     * @param val the new value
     */
    public void setXp(final long val) {
        xp = val;
    }

    /** the socialClass. */
    @ManyToOne(targetEntity = CSRSocialClassEntity.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "socialClass")
    @JsonProperty("social_class")
  @NotNull
    private CSRSocialClassEntity    socialClass;
    /**
     * Gets the socialClass.
     * @return {@link CSRSocialClassEntity}
     */
    public CSRSocialClassEntity getSocialClass() {
        return socialClass;
    }
    /**
     * Sets the socialClass.
     * @param val the new value
     */
    public void setSocialClass(final CSRSocialClassEntity val) {
        socialClass = val;
    }

    /** the race. */
    @ManyToOne(targetEntity = CSRRaceEntity.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "race")
    @JsonProperty("race")
  @NotNull
    private CSRRaceEntity    race;
    /**
     * Gets the race.
     * @return {@link CSRRaceEntity}
     */
    public CSRRaceEntity getRace() {
        return race;
    }
    /**
     * Sets the race.
     * @param val the new value
     */
    public void setRace(final CSRRaceEntity val) {
        race = val;
    }

    /** the name. */
    @Column(name = "name")
    @JsonProperty("name")
    @NotNull
    private String                    name;
    /**
     * Gets the name.
     * @return {@link String}
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name.
     * @param val the new value
     */
    public void setName(final String val) {
        name = val;
    }

    /** the level. */
    @Column(name = "level")
    @JsonProperty("level")
    @NotNull
    private long                    level;
    /**
     * Gets the level.
     * @return {@link long}
     */
    public long getLevel() {
        return level;
    }
    /**
     * Sets the level.
     * @param val the new value
     */
    public void setLevel(final long val) {
        level = val;
    }

    /** the internalScript. */
    @Column(name = "internal_script")
    @JsonProperty("internal_script")
    
    private String                    internalScript;
    /**
     * Gets the internalScript.
     * @return {@link String}
     */
    public String getInternalScript() {
        return internalScript;
    }
    /**
     * Sets the internalScript.
     * @param val the new value
     */
    public void setInternalScript(final String val) {
        internalScript = val;
    }

    /** the interfaceFlags. */
    @Column(name = "interface_flags")
    @JsonProperty("interface_flags")
    
    private Long                    interfaceFlags;
    /**
     * Gets the interfaceFlags.
     * @return {@link Long}
     */
    public Long getInterfaceFlags() {
        return interfaceFlags;
    }
    /**
     * Sets the interfaceFlags.
     * @param val the new value
     */
    public void setInterfaceFlags(final Long val) {
        interfaceFlags = val;
    }

    /** the gold. */
    @Column(name = "gold")
    @JsonProperty("gold")
    @NotNull
    private float                    gold;
    /**
     * Gets the gold.
     * @return {@link float}
     */
    public float getGold() {
        return gold;
    }
    /**
     * Sets the gold.
     * @param val the new value
     */
    public void setGold(final float val) {
        gold = val;
    }

    /** the gender. */
    @ManyToOne(targetEntity = CSRGenderEntity.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "gender")
    @JsonProperty("gender")
  @NotNull
    private CSRGenderEntity    gender;
    /**
     * Gets the gender.
     * @return {@link CSRGenderEntity}
     */
    public CSRGenderEntity getGender() {
        return gender;
    }
    /**
     * Sets the gender.
     * @param val the new value
     */
    public void setGender(final CSRGenderEntity val) {
        gender = val;
    }

    /** the flags. */
    @Column(name = "flags")
    @JsonProperty("flags")
    
    private Long                    flags;
    /**
     * Gets the flags.
     * @return {@link Long}
     */
    public Long getFlags() {
        return flags;
    }
    /**
     * Sets the flags.
     * @param val the new value
     */
    public void setFlags(final Long val) {
        flags = val;
    }

    /** the bags. */
    @Column(name = "bags")
    @JsonProperty("bags")
    @NotNull
    private long                    bags;
    /**
     * Gets the bags.
     * @return {@link long}
     */
    public long getBags() {
        return bags;
    }
    /**
     * Sets the bags.
     * @param val the new value
     */
    public void setBags(final long val) {
        bags = val;
    }

    /** the status. */
    @ManyToOne(targetEntity = CSRFamilyStatusEntity.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "status")
    @JsonProperty("family_status")
  @NotNull
    private CSRFamilyStatusEntity    status;
    /**
     * Gets the status.
     * @return {@link CSRFamilyStatusEntity}
     */
    public CSRFamilyStatusEntity getStatus() {
        return status;
    }
    /**
     * Sets the status.
     * @param val the new value
     */
    public void setStatus(final CSRFamilyStatusEntity val) {
        status = val;
    }

    /** the rank. */
    @ManyToOne(targetEntity = CSRSiblingRankEntity.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "rank")
    @JsonProperty("sibling_rank")
  @NotNull
    private CSRSiblingRankEntity    rank;
    /**
     * Gets the rank.
     * @return {@link CSRSiblingRankEntity}
     */
    public CSRSiblingRankEntity getRank() {
        return rank;
    }
    /**
     * Sets the rank.
     * @param val the new value
     */
    public void setRank(final CSRSiblingRankEntity val) {
        rank = val;
    }

    /** the aspect. */
    @ManyToOne(targetEntity = CSRBirthAspectEntity.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "aspect")
    @JsonProperty("birth_aspect")
  @NotNull
    private CSRBirthAspectEntity    aspect;
    /**
     * Gets the aspect.
     * @return {@link CSRBirthAspectEntity}
     */
    public CSRBirthAspectEntity getAspect() {
        return aspect;
    }
    /**
     * Sets the aspect.
     * @param val the new value
     */
    public void setAspect(final CSRBirthAspectEntity val) {
        aspect = val;
    }

    /** the weight. */
    @Column(name = "weight")
    @JsonProperty("weight")
    @NotNull
    private long                    weight;
    /**
     * Gets the weight.
     * @return {@link long}
     */
    public long getWeight() {
        return weight;
    }
    /**
     * Sets the weight.
     * @param val the new value
     */
    public void setWeight(final long val) {
        weight = val;
    }

    /** the height. */
    @Column(name = "height")
    @JsonProperty("height")
    @NotNull
    private long                    height;
    /**
     * Gets the height.
     * @return {@link long}
     */
    public long getHeight() {
        return height;
    }
    /**
     * Sets the height.
     * @param val the new value
     */
    public void setHeight(final long val) {
        height = val;
    }

    /** the build. */
    @Column(name = "build")
    @JsonProperty("build")
    @NotNull
    private long                    build;
    /**
     * Gets the build.
     * @return {@link long}
     */
    public long getBuild() {
        return build;
    }
    /**
     * Sets the build.
     * @param val the new value
     */
    public void setBuild(final long val) {
        build = val;
    }

    /**
     * the list of keyring associated with this
     * {@link CSRIoPcDataEntity}.
     */
    @Column
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "io_pc_data_keyring_lookup", schema = "csr",
  joinColumns = { @JoinColumn(name = "io_pc_data_id") })
    @JsonProperty("keyring")
    private List<String>    keyring;
    /**
     * Gets the list of keyring.
     * @return {@link List}<{@link String}>
     */
    public List<String> getKeyring() {
        return keyring;
    }
    /**
     * Sets the list of keyring.
     * @param val the new value
     */
    public void setKeyring(final List<String> val) {
        keyring = val;
    }

    /**
     * the list of {@link CSRIoItemDataEntity}s associated with this
     * {@link CSRIoPcDataEntity}.
     */
    @OneToMany(targetEntity = CSRIoItemDataEntity.class,
      fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "io_pc_data_inventory_items_lookup", schema = "csr",
  joinColumns = @JoinColumn(name = "io_pc_data_id",
  referencedColumnName = "io_pc_data_id"),
  inverseJoinColumns = @JoinColumn(name = "io_item_data_id",
  referencedColumnName = "io_item_data_id"))
    @JsonProperty("inventory_items")
    private List<CSRIoItemDataEntity>    inventoryItems;
    /**
     * Gets the list of inventoryItemss.
     * @return {@link List}<{@link CSRIoItemDataEntity}>
     */
    public List<CSRIoItemDataEntity> getInventoryItems() {
        return inventoryItems;
    }
    /**
     * Sets the list of inventoryItemss.
     * @param val the new value
     */
    public void setInventoryItems(final List<CSRIoItemDataEntity> val) {
        inventoryItems = val;
    }

    /**
     * the list of {@link CSRGroupEntity}s associated with this
     * {@link CSRIoPcDataEntity}.
     */
    @OneToMany(targetEntity = CSRGroupEntity.class,
      fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "io_pc_data_groups_lookup", schema = "csr",
  joinColumns = @JoinColumn(name = "io_pc_data_id",
  referencedColumnName = "io_pc_data_id"),
  inverseJoinColumns = @JoinColumn(name = "group_id",
  referencedColumnName = "group_id"))
    @JsonProperty("groups")
    private List<CSRGroupEntity>    groups;
    /**
     * Gets the list of groupss.
     * @return {@link List}<{@link CSRGroupEntity}>
     */
    public List<CSRGroupEntity> getGroups() {
        return groups;
    }
    /**
     * Sets the list of groupss.
     * @param val the new value
     */
    public void setGroups(final List<CSRGroupEntity> val) {
        groups = val;
    }

    @ElementCollection
    @CollectionTable(name = "io_pc_data_equipped_items_lookup",
  schema = "csr", joinColumns = @JoinColumn(name = "io_pc_data_id"))
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    @JsonProperty("equipped_items")
    private Map<String, String> equippedItems;
    /**
     * Gets the map of equippedItemss.
     * @return {@link Map}<{@link String}, {@link String}>
     */
    public Map<String, String> getEquippedItems() {
        return equippedItems;
    }
    /**
     * Sets the mapping for equippedItemss.
     * @param val the new value
     */
    public void setEquippedItems(Map<String, String> val) {
        equippedItems = val;
    }

    @ElementCollection
    @CollectionTable(name = "io_pc_data_attributes_lookup",
  schema = "csr", joinColumns = @JoinColumn(name = "io_pc_data_id"))
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    @JsonProperty("attributes")
    private Map<String, Integer> attributes;
    /**
     * Gets the map of attributes.
     * @return {@link Map}<{@link String}, {@link Integer}>
     */
    public Map<String, Integer> getAttributes() {
        return attributes;
    }
    /**
     * Sets the mapping for attributes.
     * @param val the new value
     */
    public void setAttributes(Map<String, Integer> val) {
        attributes = val;
    }

}

