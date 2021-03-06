package com.cma.gcvms.web.domain;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import com.cma.gcvms.domain.Route;
import com.cma.gcvms.domain.TripSchedule;
import com.cma.gcvms.domain.TripSchedule_;
import com.cma.gcvms.domain.Vehicle;
import com.cma.gcvms.repository.TripScheduleRepository;
import com.cma.gcvms.web.domain.support.GenericEditForm;
import com.cma.gcvms.web.domain.support.GenericToOneAssociation;
import com.cma.gcvms.web.faces.ConversationContextScoped;

/**
 * View Helper/Controller to edit {@link TripSchedule}.
 */
@Named
@ConversationContextScoped
public class TripScheduleEditForm extends GenericEditForm<TripSchedule, Integer> {
    @Inject
    protected TripScheduleController tripScheduleController;
    @Inject
    protected RouteController routeController;
    protected GenericToOneAssociation<Route, Integer> route;
    @Inject
    protected VehicleController vehicleController;
    protected GenericToOneAssociation<Vehicle, Integer> vehicle;

    @Inject
    public TripScheduleEditForm(TripScheduleRepository tripScheduleRepository, TripScheduleGraphLoader tripScheduleGraphLoader) {
        super(tripScheduleRepository, tripScheduleGraphLoader);
    }

    /**
     * The entity to edit/view.
     */
    public TripSchedule getTripSchedule() {
        return getEntity();
    }

    public String print() {
        return tripScheduleController.print(getTripSchedule());
    }

    @PostConstruct
    void setupRouteActions() {
        route = new GenericToOneAssociation<Route, Integer>(routeController, TripSchedule_.route) {
            @Override
            protected Route get() {
                return getTripSchedule().getRoute();
            }

            @Override
            protected void set(Route route) {
                getTripSchedule().setRoute(route);
            }

            @NotNull
            @Override
            public Route getSelected() {
                return super.getSelected();
            }
        };
    }

    public GenericToOneAssociation<Route, Integer> getRoute() {
        return route;
    }

    @PostConstruct
    void setupVehicleActions() {
        vehicle = new GenericToOneAssociation<Vehicle, Integer>(vehicleController, TripSchedule_.vehicle) {
            @Override
            protected Vehicle get() {
                return getTripSchedule().getVehicle();
            }

            @Override
            protected void set(Vehicle vehicle) {
                getTripSchedule().setVehicle(vehicle);
            }

            @NotNull
            @Override
            public Vehicle getSelected() {
                return super.getSelected();
            }
        };
    }

    public GenericToOneAssociation<Vehicle, Integer> getVehicle() {
        return vehicle;
    }
}
