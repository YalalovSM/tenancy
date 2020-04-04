package ru.sydev.tenancy.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sydev.tenancy.company.entity.Company;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class CompanyService {
  private final static Logger log = Logger.getLogger(CompanyService.class.getName());

  @Autowired
  private CompanyRepository companyRepository;

  @Transactional( propagation = Propagation.REQUIRED )
  public Company create( final String name ) {
    log.info("Company.create() started");

    Company company = new Company(name);
    final Company saved = companyRepository.save(company);

    log.info("Company.create() company created");
    return saved;
  }

  @Transactional( propagation = Propagation.REQUIRED, readOnly = true )
  public Company get( final UUID id ) throws Exception {
    log.info("Company.get() started");

    Optional<Company> optionalCompany = companyRepository.findById(id);
    return optionalCompany.orElseThrow(() -> new Exception("Cannot find company by id=" + id));
  }
}
