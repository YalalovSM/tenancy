package ru.sydev.tenancy.tenancy;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Aspect
@Component
public class TenancyAspect {
  @Autowired
  private EntityManager entityManager;

  @Before( "execution(* org.springframework.data.repository.Repository+.*(..))" )
  public void applyTenancyFilter( JoinPoint joinPoint ) {
    final Filter filter = entityManager.unwrap( Session.class ).enableFilter(Tenancy.FILTER);
    filter.setParameter(Tenancy.PARAMETER, RequestContext.getContext().getTenantId());
    filter.validate();
  }
}
