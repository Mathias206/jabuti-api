package com.mathias.jabuti.api.model.input;

import com.mathias.jabuti.domain.model.GoalPriority;
import com.mathias.jabuti.domain.model.GoalStatus;
import com.mathias.jabuti.domain.model.GoalType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoalInputDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private GoalType goalType;

    private GoalStatus goalStatus;

    private GoalPriority goalPriority;

    @NotNull
    private UserIdDTO user;
}
