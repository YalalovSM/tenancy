package ru.sydev.tenancy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sydev.tenancy.company.CompanyService;
import ru.sydev.tenancy.company.entity.Company;
import ru.sydev.tenancy.employee.EmployeeService;
import ru.sydev.tenancy.employee.entity.Employee;
import ru.sydev.tenancy.tenancy.RequestContext;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TenancyApplicationTests {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private EmployeeService employeeService;

	@Test
	void contextLoads() {
	}

	@Test
	void createCompany() {
		final String name = "ABC";

		final UUID tenantId = UUID.randomUUID();
		RequestContext.getContext().setTenantId(tenantId);
		final Company savedCompany = companyService.create(name);
		Company foundCompany = null;

		try {
			foundCompany = companyService.get(savedCompany.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertThat(foundCompany, is(notNullValue()));
		assertThat(foundCompany.getId(), is(savedCompany.getId()));
		assertThat(foundCompany.getTenantId(), is(tenantId));
	}

	@Test
	void createEmployee() {
		final Employee savedEmployee = employeeService.create("John Doe");

		final UUID tenantId = UUID.randomUUID();
		RequestContext.getContext().setTenantId(tenantId);

		Employee foundEmployee = null;
		try {
			foundEmployee = employeeService.get(savedEmployee.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertThat(foundEmployee, is(notNullValue()));
		assertThat(foundEmployee.getId(), is(savedEmployee.getId()));
		assertThat(foundEmployee.getTenantId(), is(savedEmployee.getTenantId()));
	}

}
