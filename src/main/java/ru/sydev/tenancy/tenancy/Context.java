package ru.sydev.tenancy.tenancy;

import java.util.UUID;

public class Context {
  private UUID tenantId = null;

  public UUID getTenantId() {
    return tenantId;
  }

  public void setTenantId(UUID tenantId) {
    this.tenantId = tenantId;
  }
}
