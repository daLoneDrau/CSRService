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
@Table(name = "io_item_data", schema = "csr")
public final class CSRIoItemDataEntity {
    /**
     * the primary key - an autogenerated id (unique for each user in the db).
     */
    @Id
    @Column(name = "io_item_data_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
  generator = "io_item_data_seq")
    @SequenceGenerator(
        name = "io_item_data_seq",
        sequenceName = "csr.io_item_data_id_seq",
        allocationSize = 1
    )
    private Long                    id;
    /** Creates a new instance of {@link CSRIoItemDataEntity}. */
    public CSRIoItemDataEntity() {
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

    /** the weight. */
    @Column(name = "weight")
    @JsonProperty("weight")
    @NotNull
    private float                    weight;
    /**
     * Gets the weight.
     * @return {@link float}
     */
    public float getWeight() {
        return weight;
    }
    /**
     * Sets the weight.
     * @param val the new value
     */
    public void setWeight(final float val) {
        weight = val;
    }

    /** the title. */
    @Column(name = "title")
    @JsonProperty("title")
    @NotNull
    private String                    title;
    /**
     * Gets the title.
     * @return {@link String}
     */
    public String getTitle() {
        return title;
    }
    /**
     * Sets the title.
     * @param val the new value
     */
    public void setTitle(final String val) {
        title = val;
    }

    /** the stealValue. */
    @Column(name = "steal_value")
    @JsonProperty("steal_value")
    
    private Long                    stealValue;
    /**
     * Gets the stealValue.
     * @return {@link Long}
     */
    public Long getStealValue() {
        return stealValue;
    }
    /**
     * Sets the stealValue.
     * @param val the new value
     */
    public void setStealValue(final Long val) {
        stealValue = val;
    }

    /** the stackSize. */
    @Column(name = "stack_size")
    @JsonProperty("stack_size")
    @NotNull
    private long                    stackSize;
    /**
     * Gets the stackSize.
     * @return {@link long}
     */
    public long getStackSize() {
        return stackSize;
    }
    /**
     * Sets the stackSize.
     * @param val the new value
     */
    public void setStackSize(final long val) {
        stackSize = val;
    }

    /** the ringType. */
    @Column(name = "ring_type")
    @JsonProperty("ring_type")
    
    private Long                    ringType;
    /**
     * Gets the ringType.
     * @return {@link Long}
     */
    public Long getRingType() {
        return ringType;
    }
    /**
     * Sets the ringType.
     * @param val the new value
     */
    public void setRingType(final Long val) {
        ringType = val;
    }

    /** the price. */
    @Column(name = "price")
    @JsonProperty("price")
    @NotNull
    private float                    price;
    /**
     * Gets the price.
     * @return {@link float}
     */
    public float getPrice() {
        return price;
    }
    /**
     * Sets the price.
     * @param val the new value
     */
    public void setPrice(final float val) {
        price = val;
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

    /** the maxOwned. */
    @Column(name = "max_owned")
    @JsonProperty("max_owned")
    
    private Long                    maxOwned;
    /**
     * Gets the maxOwned.
     * @return {@link Long}
     */
    public Long getMaxOwned() {
        return maxOwned;
    }
    /**
     * Sets the maxOwned.
     * @param val the new value
     */
    public void setMaxOwned(final Long val) {
        maxOwned = val;
    }

    /** the lightValue. */
    @Column(name = "light_value")
    @JsonProperty("light_value")
    
    private Long                    lightValue;
    /**
     * Gets the lightValue.
     * @return {@link Long}
     */
    public Long getLightValue() {
        return lightValue;
    }
    /**
     * Sets the lightValue.
     * @param val the new value
     */
    public void setLightValue(final Long val) {
        lightValue = val;
    }

    /** the leftRing. */
    @Column(name = "left_ring")
    @JsonProperty("left_ring")
    
    private Boolean                    leftRing;
    /**
     * Gets the leftRing.
     * @return {@link Boolean}
     */
    public Boolean getLeftRing() {
        return leftRing;
    }
    /**
     * Sets the leftRing.
     * @param val the new value
     */
    public void setLeftRing(final Boolean val) {
        leftRing = val;
    }

    /** the internalScript. */
    @Column(name = "internal_script")
    @JsonProperty("internal_script")
    @NotNull
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

    /** the foodValue. */
    @Column(name = "food_value")
    @JsonProperty("food_value")
    
    private Long                    foodValue;
    /**
     * Gets the foodValue.
     * @return {@link Long}
     */
    public Long getFoodValue() {
        return foodValue;
    }
    /**
     * Sets the foodValue.
     * @param val the new value
     */
    public void setFoodValue(final Long val) {
        foodValue = val;
    }

    /** the description. */
    @Column(name = "description")
    @JsonProperty("description")
    @NotNull
    private String                    description;
    /**
     * Gets the description.
     * @return {@link String}
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description.
     * @param val the new value
     */
    public void setDescription(final String val) {
        description = val;
    }

    /** the count. */
    @Column(name = "count")
    @JsonProperty("count")
    
    private Long                    count;
    /**
     * Gets the count.
     * @return {@link Long}
     */
    public Long getCount() {
        return count;
    }
    /**
     * Sets the count.
     * @param val the new value
     */
    public void setCount(final Long val) {
        count = val;
    }

    /**
     * the list of {@link CSRObjectTypeEntity}s associated with this
     * {@link CSRIoItemDataEntity}.
     */
    @OneToMany(targetEntity = CSRObjectTypeEntity.class,
      fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "io_item_data_types_lookup", schema = "csr",
  joinColumns = @JoinColumn(name = "io_item_data_id",
  referencedColumnName = "io_item_data_id"),
  inverseJoinColumns = @JoinColumn(name = "object_type_id",
  referencedColumnName = "object_type_id"))
    @JsonProperty("types")
    private List<CSRObjectTypeEntity>    types;
    /**
     * Gets the list of types.
     * @return {@link List}<{@link CSRObjectTypeEntity}>
     */
    public List<CSRObjectTypeEntity> getTypes() {
        return types;
    }
    /**
     * Sets the list of types.
     * @param val the new value
     */
    public void setTypes(final List<CSRObjectTypeEntity> val) {
        types = val;
    }

    /**
     * the list of {@link CSRGroupEntity}s associated with this
     * {@link CSRIoItemDataEntity}.
     */
    @OneToMany(targetEntity = CSRGroupEntity.class,
      fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "io_item_data_groups_lookup", schema = "csr",
  joinColumns = @JoinColumn(name = "io_item_data_id",
  referencedColumnName = "io_item_data_id"),
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
    @CollectionTable(name = "io_item_data_modifiers_lookup",
  schema = "csr", joinColumns = @JoinColumn(name = "io_item_data_id"))
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    @JsonProperty("modifiers")
    private Map<String, String> modifiers;
    /**
     * Gets the map of modifierss.
     * @return {@link Map}<{@link String}, {@link String}>
     */
    public Map<String, String> getModifiers() {
        return modifiers;
    }
    /**
     * Sets the mapping for modifierss.
     * @param val the new value
     */
    public void setModifiers(Map<String, String> val) {
        modifiers = val;
    }

}

