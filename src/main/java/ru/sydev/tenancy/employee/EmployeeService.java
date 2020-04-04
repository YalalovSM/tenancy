package ru.sydev.tenancy.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sydev.tenancy.employee.entity.Employee;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class EmployeeService {
  private final static Logger log = Logger.getLogger(EmployeeService.class.getName());

  @Autowired
  private EmployeeRepository employeeRepository;

  @Transactional( propagation = Propagation.REQUIRED )
  public Employee create( final String name ) {
    log.info("Employee.create() started");

    final Employee employee = new Employee(name);
    final Employee savedEmployee = employeeRepository.save(employee);

    log.info("Employee.create() employee created");
    return savedEmployee;
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public Employee get( final UUID id ) throws Exception {
    log.info("Employee.create() started");

    Optional<Employee> optionalEmployee = employeeRepository.findById(id);

    return optionalEmployee.orElseThrow(() -> new Exception("Cannot find employee by id=" + id));
  }
}
