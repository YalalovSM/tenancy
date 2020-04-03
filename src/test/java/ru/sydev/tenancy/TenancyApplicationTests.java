package ru.sydev.tenancy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sydev.tenancy.company.CompanyRepository;
import ru.sydev.tenancy.company.entity.Company;
import ru.sydev.tenancy.employee.EmployeeRepository;
import ru.sydev.tenancy.employee.entity.Employee;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TenancyApplicationTests {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void createCompany() {
		final String name = "ABC";

		final Company savedCompany = companyRepository.save(new Company( name ));
		final Company foundCompany = companyRepository.findById(savedCompany.getId()).get();

		assertThat(foundCompany, is(notNullValue()));
		assertThat(foundCompany.getId(), is(savedCompany.getId()));
	}

	@Test
	void createEmployee() {
		final Company savedCompany = companyRepository.save(new Company( "ABC" ));
		final Company foundCompany = companyRepository.findById(savedCompany.getId()).get();

		final Employee savedEmployee = employeeRepository.save(new Employee("John Doe"));
		final Employee foundEmployee = employeeRepository.findById(savedEmployee.getId()).get();

		assertThat(foundEmployee, is(notNullValue()));
		assertThat(foundEmployee.getId(), is(savedEmployee.getId()));
	}
}
