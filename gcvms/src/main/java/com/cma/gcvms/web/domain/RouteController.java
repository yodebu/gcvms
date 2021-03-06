package com.cma.gcvms.web.domain;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.cma.gcvms.domain.Route;
import com.cma.gcvms.domain.Route_;
import com.cma.gcvms.printer.RoutePrinter;
import com.cma.gcvms.repository.RouteRepository;
import com.cma.gcvms.repository.support.SearchParameters;
import com.cma.gcvms.web.domain.support.GenericController;
import com.cma.gcvms.web.permission.RoutePermission;

/**
 * Stateless controller for {@link Route} conversation start.
 */
@Named
@Singleton
public class RouteController extends GenericController<Route, Integer> {
    public static final String ROUTE_EDIT_URI = "/domain/routeEdit.faces";
    public static final String ROUTE_SELECT_URI = "/domain/routeSelect.faces";

    @Inject
    public RouteController(RouteRepository routeRepository, RoutePermission routePermission, RoutePrinter routePrinter) {
        super(routeRepository, routePermission, routePrinter, ROUTE_SELECT_URI, ROUTE_EDIT_URI);
    }

    @Override
    protected SearchParameters defaultOrder(SearchParameters searchParameters) {
        return searchParameters.asc(Route_.name);
    }
}