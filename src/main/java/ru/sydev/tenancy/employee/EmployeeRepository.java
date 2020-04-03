package ru.sydev.tenancy.employee;

import org.springframework.data.repository.CrudRepository;
import ru.sydev.tenancy.employee.entity.Employee;

import java.util.UUID;

public interface EmployeeRepository extends CrudRepository<Employee, UUID> {
}
