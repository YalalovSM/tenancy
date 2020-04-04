package ru.sydev.tenancy.configuration;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import ru.sydev.tenancy.tenancy.RequestContext;
import ru.sydev.tenancy.tenancy.Tenantable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

@Configuration
public class JpaInterceptor {

  @Autowired
  private JpaProperties jpaProperties;

  @Bean
  public EmptyInterceptor hibernateInterceptor() {
    return new EmptyInterceptor() {
      @Override
      public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames,
          Type[] types) {
        if (entity instanceof Tenantable) {
          ((Tenantable) entity).setTenantId(RequestContext.getContext().getTenantId());
        }
      }

      @Override
      public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState,
          Object[] previousState, String[] propertyNames, Type[] types) {
        if (entity instanceof Tenantable) {
          ((Tenantable) entity).setTenantId(RequestContext.getContext().getTenantId());
        }

        return false;
      }

      @Override
      public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames,
          Type[] types) {
        if (entity instanceof Tenantable) {
          ((Tenantable) entity).setTenantId(RequestContext.getContext().getTenantId());
        }

        return false;
      }
    };
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      EntityManagerFactoryBuilder factory, DataSource dataSource, JpaProperties properties) {
    Map<String, Object> jpaPropertiesMap = new HashMap<>(jpaProperties.getProperties());
    jpaPropertiesMap.put("hibernate.ejb.interceptor", hibernateInterceptor());
    return factory.dataSource(dataSource).packages("ru.sydev").properties(jpaPropertiesMap).build();
  }
}
