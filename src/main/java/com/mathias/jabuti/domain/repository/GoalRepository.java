package com.mathias.jabuti.domain.repository;

import com.mathias.jabuti.domain.model.Goal;
import com.mathias.jabuti.infrastructure.repository.CustomJpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends CustomJpaRepository<Goal, Long>, JpaSpecificationExecutor<Goal> {

	@Query("from Goal g join fetch g.user")
	Page<Goal> findAll(Pageable pageable);

	@Query("SELECT g FROM Goal g JOIN FETCH g.user " +
			"WHERE g.parentGoal.id = :parentGoalId")
	Page<Goal> findByParentGoalId(@Param("parentGoalId") Long parentGoalId, Pageable pageable);
}