package com.mathias.jabuti.api.controller;

import com.mathias.jabuti.domain.exception.EntityNotFoundException;
import com.mathias.jabuti.domain.model.Goal;
import com.mathias.jabuti.domain.model.GoalPriority;
import com.mathias.jabuti.domain.model.GoalStatus;
import com.mathias.jabuti.domain.repository.GoalRepository;
import com.mathias.jabuti.domain.service.DomainException;
import com.mathias.jabuti.domain.service.GoalRegistrationService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.mathias.jabuti.infrastructure.repository.specs.GoalSpecs.*;

@RestController
@RequestMapping(value = "/goal")
public class GoalController {

  @Autowired
  private GoalRepository repository;

  @Autowired
  private GoalRegistrationService service;

  @GetMapping
  public List<Goal> list() {
    return repository.findAll();
  }

  @GetMapping("/{goalId}")
  public Goal retrieve(@PathVariable("goalId") Long id) {
    return service.findOrFail(id);
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody Goal requestGoal) {
    try {
      Goal createdGoal = service.create(requestGoal);
      return ResponseEntity.status(HttpStatus.CREATED).body(createdGoal);
    } catch (EntityNotFoundException e) {
      throw new DomainException(e.getMessage());
    }
  }

  @GetMapping("/specifications")
  public List<Goal> getWithSpecifications() {
    return repository.findAll(goalStatus(GoalStatus.PENDING).and(goalPriority(GoalPriority.MEDIUM)));
  }

  @GetMapping("/customJpaRepository")
  public Optional<Goal> getFirstGoal() {
    return repository.findFirst();
  }

  @PutMapping("/{goalId}")
  public Goal update(@PathVariable("goalId") Long goalId, @RequestBody Goal requestGoal) {
    Goal goal = service.findOrFail(goalId);
    BeanUtils.copyProperties(requestGoal, goal, "id");
    try {
      return service.save(goal);
    } catch (EntityNotFoundException e) {
      throw new DomainException(e.getMessage());
    }
  }
}
