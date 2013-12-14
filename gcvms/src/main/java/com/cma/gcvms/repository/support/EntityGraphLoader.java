/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to purchase Celerio ? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Template pack-backend-jpa:src/main/java/repository/support/EntityGraphLoader.p.vm.java
 */
package com.cma.gcvms.repository.support;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import com.cma.gcvms.domain.Identifiable;

/**
 * The EntityGraphLoader is used to load within a single read-only transaction all the desired associations that 
 * are normally lazily loaded.
 */
public abstract class EntityGraphLoader<T extends Identifiable<PK>, PK extends Serializable> {

    protected GenericRepository<T, PK> repository;

    // required by cglib to create a proxy around the object as we are using the @Transactional annotation
    public EntityGraphLoader() {
    }

    public EntityGraphLoader(GenericRepository<T, PK> repository) {
        this.repository = repository;
    }

    /**
     * Get the entity by id and load its graph using loadGraph.
     */
    @Transactional(readOnly = true)
    public T getById(PK pk) {
        T entity = repository.getById(pk);
        loadGraph(entity);
        return entity;
    }

    /**
     * Merge the passed entity and load the graph of the merged entity using loadGraph.
     */
    @Transactional(readOnly = true)
    public T merge(T entity) {
        T mergedEntity = repository.merge(entity);
        loadGraph(mergedEntity);
        return mergedEntity;
    }

    /**
     * Load whatever is needed in the graph of the passed entity, for example x-to-many collection, x-to-one object, etc.
     */
    public abstract void loadGraph(T entity);

    /**
     * Load the passed 'x-to-many' association.
     */
    protected void loadCollection(Collection<?> collection) {
        if (collection != null) {
            collection.size();
        }
    }

    /**
     * Load the passed 'x-to-one' association.
     */
    protected void loadSingular(Object association) {
        if (association != null) {
            association.toString();
        }
    }
}