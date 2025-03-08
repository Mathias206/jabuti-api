package com.mathias.jabuti.infrastructure.repository.specs;

import org.springframework.data.jpa.domain.Specification;

import com.mathias.jabuti.domain.model.Goal;
import com.mathias.jabuti.domain.model.GoalPriority;
import com.mathias.jabuti.domain.model.GoalStatus;

public class GoalSpecs {
    public static Specification<Goal> goalStatus(GoalStatus goalSatus) {
        return (root, query, builder) ->
            builder.equal(root.get("goalStatus"), goalSatus);
    }

    public static Specification<Goal> goalPriority(GoalPriority goalPriority) {
        return (root, query, builder) ->
            builder.equal(root.get("goalPriority"), goalPriority);
    }
}
