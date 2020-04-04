package ru.sydev.tenancy.tenancy;

import java.util.UUID;

public interface Tenantable {
  UUID getTenantId();

  void setTenantId( UUID tenantId );
}
