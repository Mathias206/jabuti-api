package com.mathias.jabuti.domain.service;

import com.mathias.jabuti.domain.model.Goal;
import com.mathias.jabuti.domain.model.GoalStatus;
import com.mathias.jabuti.domain.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoalStatusProcessService {

    @Autowired
    private GoalRegistrationService goalRegistrationService;

    @Transactional
    public void toPending(Long goalId) {
        Goal goal = goalRegistrationService.findOrFail(goalId);
        goal.setGoalStatus(GoalStatus.PENDING);
    }
}
