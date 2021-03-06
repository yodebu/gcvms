package com.cma.gcvms.domain;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.TemporalType.DATE;
import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;

@Entity
@Table(name = "DAILY_TRIP")
public class DailyTrip implements Identifiable<Integer>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(DailyTrip.class);

    // Raw attributes
    private Integer id;
    private Date tripDate;
    private Date tripTime;

    // Many to one
    private Vehicle vehicle;
    private Route route;
    private Employee supervisor;

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

    public DailyTrip id(Integer id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    @XmlTransient
    public boolean isIdSet() {
        return id != null;
    }

    // -- [tripDate] ------------------------

    @Column(name = "TRIP_DATE", length = 10)
    @Temporal(DATE)
    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }

    public DailyTrip tripDate(Date tripDate) {
        setTripDate(tripDate);
        return this;
    }

    // -- [tripTime] ------------------------

    @Column(name = "TRIP_TIME", length = 26)
    @Temporal(TIMESTAMP)
    public Date getTripTime() {
        return tripTime;
    }

    public void setTripTime(Date tripTime) {
        this.tripTime = tripTime;
    }

    public DailyTrip tripTime(Date tripTime) {
        setTripTime(tripTime);
        return this;
    }

    // -----------------------------------------------------------------
    // Many to One support
    // -----------------------------------------------------------------

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: DailyTrip.vehicle ==> Vehicle.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @NotNull
    @JoinColumn(name = "VEHICLE_ID", nullable = false)
    @ManyToOne(cascade = { PERSIST, MERGE }, fetch = LAZY)
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Set the {@link #vehicle} without adding this DailyTrip instance on the passed {@link #vehicle}
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public DailyTrip vehicle(Vehicle vehicle) {
        setVehicle(vehicle);
        return this;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: DailyTrip.route ==> Route.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @NotNull
    @JoinColumn(name = "ROUTE_ID", nullable = false)
    @ManyToOne(cascade = { PERSIST, MERGE }, fetch = LAZY)
    public Route getRoute() {
        return route;
    }

    /**
     * Set the {@link #route} without adding this DailyTrip instance on the passed {@link #route}
     */
    public void setRoute(Route route) {
        this.route = route;
    }

    public DailyTrip route(Route route) {
        setRoute(route);
        return this;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: DailyTrip.supervisor ==> Employee.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @JoinColumn(name = "SUPERVISOR_ID")
    @ManyToOne(cascade = { PERSIST, MERGE }, fetch = LAZY)
    public Employee getSupervisor() {
        return supervisor;
    }

    /**
     * Set the {@link #supervisor} without adding this DailyTrip instance on the passed {@link #supervisor}
     */
    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }

    public DailyTrip supervisor(Employee supervisor) {
        setSupervisor(supervisor);
        return this;
    }

    /**
     * Apply the default values.
     */
    public DailyTrip withDefaults() {
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof DailyTrip && hashCode() == other.hashCode());
    }

    private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

    @Override
    public int hashCode() {
        return identifiableHashBuilder.hash(log, this);
    }

    /**
     * Construct a readable string representation for this DailyTrip instance.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return Objects.toStringHelper(this) //
                .add("id", getId()) //
                .add("tripDate", getTripDate()) //
                .add("tripTime", getTripTime()) //
                .toString();
    }
}