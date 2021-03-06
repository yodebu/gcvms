package com.cma.gcvms.repository;

import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.transaction.annotation.Transactional;

import com.cma.gcvms.domain.Vehicle;
import com.cma.gcvms.repository.support.GenericRepository;

/**
 * {@link GenericRepository} for {@link Vehicle} 
 */
@Named
@Singleton
public class VehicleRepository extends GenericRepository<Vehicle, Integer> {

    public VehicleRepository() {
        super(Vehicle.class);
    }

    @Override
    public Vehicle getNew() {
        return new Vehicle();
    }

    @Override
    public Vehicle getNewWithDefaults() {
        return getNew().withDefaults();
    }

    /**
     * Return the persistent instance of {@link Vehicle} with the given unique property value regNo,
     * or null if there is no such persistent instance.
     *
     * @param regNo the unique value
     * @return the corresponding {@link Vehicle} persistent instance or null
     */
    @Transactional(readOnly = true)
    public Vehicle getByRegNo(String regNo) {
        return findUniqueOrNone(new Vehicle().regNo(regNo));
    }

    /**
     * Delete a {@link Vehicle} using the unique column regNo
     *
     * @param regNo the unique value
     */
    @Transactional
    public void deleteByRegNo(String regNo) {
        delete(getByRegNo(regNo));
    }
}