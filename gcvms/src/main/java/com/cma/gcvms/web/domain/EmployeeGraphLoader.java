package com.cma.gcvms.web.domain;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.cma.gcvms.domain.Employee;
import com.cma.gcvms.repository.EmployeeRepository;
import com.cma.gcvms.repository.support.EntityGraphLoader;

/**
 * Preloads the {@link Employee} associations required by the view layer.
 * 
 * Do not use other GraphLoaders in this GraphLoader. 
 */
@Named
@Singleton
public class EmployeeGraphLoader extends EntityGraphLoader<Employee, Integer> {
    // required by cglib to create a proxy around the object as we are using the @Transactional annotation
    protected EmployeeGraphLoader() {
        super();
    }

    @Inject
    public EmployeeGraphLoader(EmployeeRepository employeeRepository) {
        super(employeeRepository);
    }

    @Override
    public void loadGraph(Employee employee) {
        loadSingular(employee.getMun());
        loadSingular(employee.getSupervisor());
        loadSingular(employee.getDept());
    }
}