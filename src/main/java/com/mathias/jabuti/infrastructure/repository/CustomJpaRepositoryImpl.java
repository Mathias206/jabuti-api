package com.mathias.jabuti.infrastructure.repository;

import jakarta.persistence.EntityManager;
import java.util.Optional;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID>
    implements CustomJpaRepository<T, ID> {

  private EntityManager manager;

  public CustomJpaRepositoryImpl(
      JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
    super(entityInformation, entityManager);
    this.manager = entityManager;
  }

  @Override
  public Optional<T> findFirst() {
    var jpql = "from " + getDomainClass().getName();
    T entity = manager.createQuery(jpql, getDomainClass()).setMaxResults(1).getSingleResult();
    return Optional.ofNullable(entity);
  }
}
