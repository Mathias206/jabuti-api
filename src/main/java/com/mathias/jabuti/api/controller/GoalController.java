package com.mathias.jabuti.api.controller;

import com.mathias.jabuti.api.assembler.ChildGoalDTOAssembler;
import com.mathias.jabuti.api.assembler.GoalDTOAssembler;
import com.mathias.jabuti.api.assembler.GoalDTODisassembler;
import com.mathias.jabuti.api.assembler.ParentChildGoalDTOAssembler;
import com.mathias.jabuti.api.model.ChildGoalDTO;
import com.mathias.jabuti.api.model.GoalDTO;
import com.mathias.jabuti.api.model.input.GoalInputDTO;
import com.mathias.jabuti.domain.exception.EntityNotFoundException;
import com.mathias.jabuti.domain.model.Goal;
import com.mathias.jabuti.domain.model.GoalPriority;
import com.mathias.jabuti.domain.model.GoalStatus;
import com.mathias.jabuti.domain.repository.GoalRepository;
import com.mathias.jabuti.domain.service.DomainException;
import com.mathias.jabuti.domain.service.GoalRegistrationService;

import com.mathias.jabuti.domain.service.GoalStatusProcessService;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private GoalDTOAssembler goalDTOAssembler;

    @Autowired
    private GoalDTODisassembler goalDTODisassembler;

    @Autowired
    private GoalStatusProcessService goalStatusProcessService;

    @Autowired
    private ChildGoalDTOAssembler childGoalDTOAssembler;

    @GetMapping
    public Page<GoalDTO> list(Pageable pageable) {
        Page<Goal> goals = repository.findAll(pageable);
        List<GoalDTO> goalsDTO = goalDTOAssembler.toCollectionModel(goals.getContent());
        Page<GoalDTO> goalDTOPage = new PageImpl<>(goalsDTO, pageable, goals.getTotalElements());
        return goalDTOPage;
    }

    @GetMapping("/{goalId}")
    public GoalDTO retrieve(@PathVariable("goalId") Long id) {
        Goal goal = service.findOrFail(id);
        return goalDTOAssembler.toModel(goal);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid GoalInputDTO goalInput) {
        try {
            Goal goal = goalDTODisassembler.toDomainObject(goalInput);
            GoalDTO createdGoal = goalDTOAssembler.toModel(service.create(goal));
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGoal);
        } catch (EntityNotFoundException e) {
            throw new DomainException(e.getMessage());
        }
    }

    @GetMapping("/specifications")
    public List<Goal> getWithSpecifications() {
        return repository.findAll(goalStatus(GoalStatus.PENDING)
                .and(goalPriority(GoalPriority.MEDIUM)));
    }

    @GetMapping("/customJpaRepository")
    public Optional<Goal> getFirstGoal() {
        return repository.findFirst();
    }

    @PutMapping("/{goalId}")
    public GoalDTO update(@PathVariable("goalId") Long goalId, @RequestBody @Valid GoalInputDTO goalInputDTO) {
        Goal goal = service.findOrFail(goalId);
        goalDTODisassembler.copyToDomainObject(goalInputDTO, goal);
        try {
            var savedGoal = service.save(goal);
            return goalDTOAssembler.toModel(savedGoal);
        } catch (EntityNotFoundException e) {
            throw new DomainException(e.getMessage());
        }
    }

    @DeleteMapping("/{goalId}")
    public ResponseEntity<?> delete(@PathVariable("goalId") Long goalId) {
        service.delete(goalId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{goalId}/pending")
    public ResponseEntity<?> toPending(@PathVariable("goalId") Long goalId) {
        goalStatusProcessService.toPending(goalId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{goalId}/child-goals")
    public Page<ChildGoalDTO> getChildrenGoals(@PathVariable("goalId") Long goalId, Pageable pageable) {
        Goal goal = service.findOrFail(goalId);
        Page<Goal> childGoalsPage = repository.findByParentGoalId(goal.getId(), pageable);
        List<ChildGoalDTO> childGolsDTO = childGoalDTOAssembler.toCollectionModel(childGoalsPage.getContent());
        Page<ChildGoalDTO> goalDTOPage = new PageImpl<>(childGolsDTO, pageable, childGoalsPage.getTotalElements());
        return goalDTOPage;
    }
}
