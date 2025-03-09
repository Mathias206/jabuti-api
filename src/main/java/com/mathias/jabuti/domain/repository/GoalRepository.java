package com.mathias.jabuti.domain.repository;

import com.mathias.jabuti.domain.model.Goal;
import com.mathias.jabuti.infrastructure.repository.CustomJpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends CustomJpaRepository<Goal, Long>, JpaSpecificationExecutor<Goal> {
}
