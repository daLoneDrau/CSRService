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
@Table(name = "name", schema = "csr")
public final class CSRNameEntity {
    /**
     * the primary key - an autogenerated id (unique for each user in the db).
     */
    @Id
    @Column(name = "name_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
  generator = "name_seq")
    @SequenceGenerator(
        name = "name_seq",
        sequenceName = "csr.name_id_seq",
        allocationSize = 1
    )
    private Long                    id;
    /** Creates a new instance of {@link CSRNameEntity}. */
    public CSRNameEntity() {
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

    /** the isFemale. */
    @Column(name = "is_female")
    @JsonProperty("is_female")
    @NotNull
    private Boolean                    isFemale;
    /**
     * Gets the isFemale.
     * @return {@link Boolean}
     */
    public Boolean getIsFemale() {
        return isFemale;
    }
    /**
     * Sets the isFemale.
     * @param val the new value
     */
    public void setIsFemale(final Boolean val) {
        isFemale = val;
    }

    /** the isLast. */
    @Column(name = "is_last")
    @JsonProperty("is_last")
    @NotNull
    private Boolean                    isLast;
    /**
     * Gets the isLast.
     * @return {@link Boolean}
     */
    public Boolean getIsLast() {
        return isLast;
    }
    /**
     * Sets the isLast.
     * @param val the new value
     */
    public void setIsLast(final Boolean val) {
        isLast = val;
    }

}

