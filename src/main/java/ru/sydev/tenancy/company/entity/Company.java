package ru.sydev.tenancy.company.entity;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import ru.sydev.tenancy.tenancy.Tenancy;
import ru.sydev.tenancy.tenancy.Tenantable;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table(name = "company")
@Entity
@FilterDef(
    name = Tenancy.FILTER,
    parameters = { @ParamDef(name = Tenancy.PARAMETER, type = "uuid-binary") }
)
@Filter(name = Tenancy.FILTER, condition = "tenant_id = :" + Tenancy.PARAMETER)
public class Company implements Tenantable {
  @Id
  @NotNull
  private UUID id = UUID.randomUUID();

  @Size( min = 1, max = 1024 )
  private String name;

  @NotNull
  @Column( name = "tenant_id" )
  private UUID tenantId;

  @Override
  public UUID getTenantId() {
    return tenantId;
  }

  @Override
  public void setTenantId(UUID tenantId) {
    this.tenantId = tenantId;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Company( final String name ) {
    this.name = name;
  }

  public Company() {
  }
}
