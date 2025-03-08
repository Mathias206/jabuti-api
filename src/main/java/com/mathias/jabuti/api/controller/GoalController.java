package com.mathias.jabuti.api.controller;

import static com.mathias.jabuti.infrastructure.repository.specs.GoalSpecs.goalPriority;
import static com.mathias.jabuti.infrastructure.repository.specs.GoalSpecs.goalStatus;

import java.util.List;
import java.util.Optional;

import com.mathias.jabuti.domain.model.Goal;
import com.mathias.jabuti.domain.model.GoalPriority;
import com.mathias.jabuti.domain.model.GoalStatus;
import com.mathias.jabuti.domain.repository.GoalRepository;
import com.mathias.jabuti.domain.service.GoalRegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/goal")
public class GoalController {

  @Autowired private GoalRepository repository;

  @Autowired private GoalRegistrationService service;

  @GetMapping
  public List<Goal> list() {
    return repository.findAll();
  }

  @GetMapping("/{goalId}")
  public Goal retrieve(@PathVariable("goalId") Long id) {
    return service.findOrFail(id);
  }

  @GetMapping("/jpa/{goalId}")
  public ResponseEntity<Goal> jpaRetrieve(@PathVariable("goalId") Long id) {
    Optional<Goal> goal = repository.findById(id);
    if (goal.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(goal.get());
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody Goal requestGoal) {
    Goal createdGoal = service.create(requestGoal);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdGoal);
  }

  @GetMapping("/specifications")
  public List<Goal> getWithSpecifications() {
    return repository.findAll(
        goalStatus(GoalStatus.PENDING).and(goalPriority(GoalPriority.MEDIUM)));
  }

  @GetMapping("/customJpaRepository")
  public Optional<Goal> getFirstGoal() {
    return repository.findFirst();
  }
}
