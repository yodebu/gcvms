package com.cma.gcvms.repository;

import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.transaction.annotation.Transactional;

import com.cma.gcvms.domain.Route;
import com.cma.gcvms.repository.support.GenericRepository;

/**
 * {@link GenericRepository} for {@link Route} 
 */
@Named
@Singleton
public class RouteRepository extends GenericRepository<Route, Integer> {

    public RouteRepository() {
        super(Route.class);
    }

    @Override
    public Route getNew() {
        return new Route();
    }

    @Override
    public Route getNewWithDefaults() {
        return getNew().withDefaults();
    }

    /**
     * Return the persistent instance of {@link Route} with the given unique property value name,
     * or null if there is no such persistent instance.
     *
     * @param name the unique value
     * @return the corresponding {@link Route} persistent instance or null
     */
    @Transactional(readOnly = true)
    public Route getByName(String name) {
        return findUniqueOrNone(new Route().name(name));
    }

    /**
     * Delete a {@link Route} using the unique column name
     *
     * @param name the unique value
     */
    @Transactional
    public void deleteByName(String name) {
        delete(getByName(name));
    }
}