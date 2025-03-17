package com.mathias.jabuti.domain.service;

import com.mathias.jabuti.domain.exception.DuplicatedEntityException;
import com.mathias.jabuti.domain.exception.EntityNotFoundException;
import com.mathias.jabuti.domain.model.Goal;
import com.mathias.jabuti.domain.model.User;
import com.mathias.jabuti.domain.repository.GoalRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalRegistrationService {

	@Autowired
	private GoalRepository goalRepository;

	@Autowired
	private EntityManager manager;

	@Autowired
	private UserRegistrationService userService;

	public Goal save(Goal goal) {
		Long userId = goal.getUser().getId();
		User user = userService.findOrFail(userId);
		goal.setUser(user);
		return goalRepository.save(goal);
	}

	public Goal create(Goal goal) {
		if (goal.getId() != null && goalRepository.existsById(goal.getId())) {
			throw new DuplicatedEntityException(Goal.class, goal.getId());		}
		Long userId = goal.getUser().getId();
		User user = userService.findOrFail(userId);
		goal.setUser(user);
		return goalRepository.save(goal);
	}

	public List<Goal> criteriaCustomQuery(String goalName) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Goal> criteria = builder.createQuery(Goal.class);
		Root<Goal> root = criteria.from(Goal.class);
		Predicate goalNamePredicate = builder.like(root.get("name"), "%" + goalName + "%");
		criteria.where(goalNamePredicate);
		TypedQuery<Goal> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	public Goal findOrFail(Long goalId) {
		return goalRepository.findById(goalId).orElseThrow(() -> new EntityNotFoundException(Goal.class, goalId));
	}
}
