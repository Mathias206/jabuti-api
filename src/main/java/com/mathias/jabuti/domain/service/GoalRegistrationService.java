package com.mathias.jabuti.domain.service;

import com.mathias.jabuti.domain.exception.DuplicatedEntityException;
import com.mathias.jabuti.domain.exception.EntityNotFoundException;
import com.mathias.jabuti.domain.model.Goal;
import com.mathias.jabuti.domain.model.GoalType;
import com.mathias.jabuti.domain.model.User;
import com.mathias.jabuti.domain.repository.GoalRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoalRegistrationService {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private EntityManager manager;

    @Autowired
    private UserRegistrationService userService;

    @Transactional
    public Goal save(Goal goal) {
        Long userId = goal.getUser().getId();
        User user = userService.findOrFail(userId);
        goal.setUser(user);

        Long parentGoalId = goal.getParentGoal().getId();
        Goal parentGoal = findOrFail(parentGoalId);
        goal.setParentGoal(parentGoal);

        return goalRepository.save(goal);
    }

    @Transactional
    public Goal create(Goal goal) {
        if (goal.getId() != null && goalRepository.existsById(goal.getId())) {
            throw new DuplicatedEntityException(Goal.class, goal.getId());
        }
        Long userId = goal.getUser().getId();
        User user = userService.findOrFail(userId);
        goal.setUser(user);

        Long parentGoalId = goal.getParentGoal().getId();
        Goal parentGoal = findOrFail(parentGoalId);
        validateParentGoal(parentGoal, goal);
        
        goal.setParentGoal(parentGoal);
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

    @Transactional
    public void delete(Long goalId) {
        if (!goalRepository.existsById(goalId)) {
            throw new EntityNotFoundException(Goal.class, goalId);
        }
        goalRepository.deleteById(goalId);
    }

    public void validateParentGoal(Goal parentGoal, Goal goal) {

        GoalType parentGoalType = parentGoal.getGoalType();

        switch (goal.getGoalType()) {
            case DAILY:
                if (!parentGoalType.equals(GoalType.WEEKLY)) {
                    throw new DomainException("DAILY goals must have a WEEKLY parent.");
                }
                break;
            case WEEKLY:
                if (!parentGoalType.equals(GoalType.MONTHLY)) {
                    throw new DomainException("WEEKLY goals must have a MONTHLY parent.");
                }
                break;
            case MONTHLY:
                if (!parentGoalType.equals(GoalType.ANNUAL)) {
                    throw new DomainException("MONTHLY goals must have an ANNUAL parent.");
                }
                break;
            case ANNUAL:
                if (parentGoalType != null) {
                    throw new DomainException("ANNUAL goals should not have a parent.");
                }
                break;
            default:
                throw new DomainException("Unknown GoalType: " + parentGoalType);
        }

    }
}
