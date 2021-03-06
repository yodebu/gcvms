package com.cma.gcvms.domain;

import static javax.persistence.TemporalType.DATE;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;

@Entity
@Table(name = "ROUTE")
public class Route implements Identifiable<Integer>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(Route.class);

    // Raw attributes
    private Integer id;
    private String name;
    private Double distance;
    private String startLoc;
    private String endLoc;
    private Integer createdBy;
    private Date createdDate;

    // -- [id] ------------------------

    @Override
    @Column(name = "ID", precision = 32)
    @GeneratedValue
    @Id
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Route id(Integer id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    @XmlTransient
    public boolean isIdSet() {
        return id != null;
    }

    // -- [name] ------------------------

    @Size(max = 30)
    @Column(name = "NAME", unique = true, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Route name(String name) {
        setName(name);
        return this;
    }

    // -- [distance] ------------------------

    @Digits(integer = 7, fraction = 3)
    @NotNull
    @Column(name = "DISTANCE", nullable = false, precision = 10, scale = 3)
    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Route distance(Double distance) {
        setDistance(distance);
        return this;
    }

    // -- [startLoc] ------------------------

    @Size(max = 25)
    @Column(name = "START_LOC", length = 25)
    public String getStartLoc() {
        return startLoc;
    }

    public void setStartLoc(String startLoc) {
        this.startLoc = startLoc;
    }

    public Route startLoc(String startLoc) {
        setStartLoc(startLoc);
        return this;
    }

    // -- [endLoc] ------------------------

    @Size(max = 25)
    @Column(name = "END_LOC", length = 25)
    public String getEndLoc() {
        return endLoc;
    }

    public void setEndLoc(String endLoc) {
        this.endLoc = endLoc;
    }

    public Route endLoc(String endLoc) {
        setEndLoc(endLoc);
        return this;
    }

    // -- [createdBy] ------------------------

    @Digits(integer = 32, fraction = 0)
    @Column(name = "CREATED_BY", precision = 32)
    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Route createdBy(Integer createdBy) {
        setCreatedBy(createdBy);
        return this;
    }

    // -- [createdDate] ------------------------

    @Column(name = "CREATED_DATE", length = 10)
    @Temporal(DATE)
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Route createdDate(Date createdDate) {
        setCreatedDate(createdDate);
        return this;
    }

    /**
     * Apply the default values.
     */
    public Route withDefaults() {
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof Route && hashCode() == other.hashCode());
    }

    private volatile int previousHashCode = 0;

    @Override
    public int hashCode() {
        int hashCode = Objects.hashCode(getName());

        if (previousHashCode != 0 && previousHashCode != hashCode) {
            log.warn("DEVELOPER: hashCode has changed!." //
                    + "If you encounter this message you should take the time to carefuly review equals/hashCode for: " //
                    + getClass().getCanonicalName());
        }

        previousHashCode = hashCode;
        return hashCode;
    }

    /**
     * Construct a readable string representation for this Route instance.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return Objects.toStringHelper(this) //
                .add("id", getId()) //
                .add("name", getName()) //
                .add("distance", getDistance()) //
                .add("startLoc", getStartLoc()) //
                .add("endLoc", getEndLoc()) //
                .add("createdBy", getCreatedBy()) //
                .add("createdDate", getCreatedDate()) //
                .toString();
    }
}