package ru.sydev.tenancy.company;

import org.springframework.data.repository.CrudRepository;
import ru.sydev.tenancy.company.entity.Company;

import java.util.UUID;

public interface CompanyRepository extends CrudRepository<Company, UUID> {
}
