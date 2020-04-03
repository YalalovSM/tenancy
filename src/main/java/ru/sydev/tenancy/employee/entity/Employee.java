package ru.sydev.tenancy.employee.entity;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Employee {
  @Id
  @NotNull
  private UUID id = UUID.randomUUID();

  @Size( min = 1, max = 254 )
  private String name;

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

  public Employee( final String name ) {
    this.name = name;
  }

  public Employee() {
  }

}
