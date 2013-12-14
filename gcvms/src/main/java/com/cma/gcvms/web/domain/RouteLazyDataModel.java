/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to purchase Celerio ? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Template pack-jsf2-spring-conversation:src/main/java/domain/LazyDataModel.e.vm.java
 */
package com.cma.gcvms.web.domain;

import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import com.cma.gcvms.domain.Route;
import com.cma.gcvms.repository.RouteRepository;
import com.cma.gcvms.web.domain.support.GenericLazyDataModel;
import com.cma.gcvms.web.faces.ConversationContextScoped;

/**
 * Provide PrimeFaces {@link LazyDataModel} for {@link Route}
 */
@Named
@ConversationContextScoped
public class RouteLazyDataModel extends GenericLazyDataModel<Route, Integer, RouteSearchForm> {
    private static final long serialVersionUID = 1L;

    @Inject
    public RouteLazyDataModel(RouteRepository routeRepository, RouteController routeController, RouteSearchForm routeSearchForm,
            RouteExcelExporter routeExcelExporter) {
        super(routeRepository, routeController, routeSearchForm, routeExcelExporter);
    }
}