package com.mathias.jabuti.domain.service;

import com.mathias.jabuti.domain.exception.EntityNotFoundException;
import com.mathias.jabuti.domain.model.Goal;
import com.mathias.jabuti.domain.model.User;
import com.mathias.jabuti.domain.repository.GoalRepository;
import com.mathias.jabuti.domain.repository.UserRepository;
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

	private static final String DUPLICATED_GOAL_ENTRY_MSG = "Duplicated Goal entity";

	private static final String ENTITY_NOT_FOUND_MESSAGE = "Entity %d not found";

	@Autowired
	private GoalRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EntityManager manager;

	public Goal create(Goal goal) {
		if (goal.getId() != null && repository.existsById(goal.getId())) {
			throw new IllegalArgumentException(DUPLICATED_GOAL_ENTRY_MSG);
		}
		Long userId = goal.getUser().getId();
		Optional<User> user = userRepository.findById(userId);
		if (user.isEmpty()) {
			String message = String.format(ENTITY_NOT_FOUND_MESSAGE, userId);
			throw new EntityNotFoundException(message);
		}
		return repository.save(goal);
	}

	public Goal retrieve(Long id) {
		Optional<Goal> goal = repository.findById(id);
		if (goal.isEmpty()) {
			String message = String.format(ENTITY_NOT_FOUND_MESSAGE, id);
			throw new EntityNotFoundException(message);
		}
		return goal.get();
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
		String message = String.format(ENTITY_NOT_FOUND_MESSAGE, goalId);
		return repository.findById(goalId).orElseThrow(() -> new EntityNotFoundException(message));
	}
}
