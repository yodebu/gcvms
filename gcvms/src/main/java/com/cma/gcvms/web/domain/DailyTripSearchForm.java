package com.cma.gcvms.web.domain;

import static com.cma.gcvms.repository.support.PropertySelector.newPropertySelector;
import static com.cma.gcvms.repository.support.Range.newRange;

import java.util.Date;

import javax.inject.Named;

import com.cma.gcvms.domain.DailyTrip;
import com.cma.gcvms.domain.DailyTrip_;
import com.cma.gcvms.domain.Employee;
import com.cma.gcvms.domain.Route;
import com.cma.gcvms.domain.Vehicle;
import com.cma.gcvms.repository.support.PropertySelector;
import com.cma.gcvms.repository.support.Range;
import com.cma.gcvms.repository.support.SearchParameters;
import com.cma.gcvms.web.domain.support.GenericSearchForm;
import com.cma.gcvms.web.faces.ConversationContextScoped;

/**
 * View Helper to search {@link DailyTrip}.
 * It exposes a {@link DailyTrip} instance so it can be used in search by-example-query.
 */
@Named
@ConversationContextScoped
public class DailyTripSearchForm extends GenericSearchForm<DailyTrip, Integer, DailyTripSearchForm> {
    private static final long serialVersionUID = 1L;
    protected DailyTrip dailyTrip = new DailyTrip();
    protected Range<DailyTrip, Date> tripDateRange = newRange(DailyTrip_.tripDate);
    protected Range<DailyTrip, Date> tripTimeRange = newRange(DailyTrip_.tripTime);
    protected PropertySelector<DailyTrip, Vehicle> vehicleSelector = newPropertySelector(DailyTrip_.vehicle);
    protected PropertySelector<DailyTrip, Route> routeSelector = newPropertySelector(DailyTrip_.route);
    protected PropertySelector<DailyTrip, Employee> supervisorSelector = newPropertySelector(DailyTrip_.supervisor);

    public DailyTrip getDailyTrip() {
        return dailyTrip;
    }

    @Override
    protected DailyTrip getEntity() {
        return getDailyTrip();
    }

    @Override
    public DailyTripSearchForm newInstance() {
        return new DailyTripSearchForm();
    }

    @Override
    public SearchParameters toSearchParameters() {
        SearchParameters sp = searchParameters();
        sp.range(tripDateRange, tripTimeRange);
        sp.property(vehicleSelector, routeSelector, supervisorSelector);
        return sp;
    }

    @Override
    public void resetWithOther(DailyTripSearchForm other) {
        this.dailyTrip = other.getDailyTrip();
        this.tripDateRange = other.getTripDateRange();
        this.tripTimeRange = other.getTripTimeRange();
        this.vehicleSelector = other.getVehicleSelector();
        this.routeSelector = other.getRouteSelector();
        this.supervisorSelector = other.getSupervisorSelector();
    }

    // Ranges
    public Range<DailyTrip, Date> getTripDateRange() {
        return tripDateRange;
    }

    public Range<DailyTrip, Date> getTripTimeRange() {
        return tripTimeRange;
    }

    // Relation selectors
    public PropertySelector<DailyTrip, Vehicle> getVehicleSelector() {
        return vehicleSelector;
    }

    public PropertySelector<DailyTrip, Route> getRouteSelector() {
        return routeSelector;
    }

    public PropertySelector<DailyTrip, Employee> getSupervisorSelector() {
        return supervisorSelector;
    }
}
