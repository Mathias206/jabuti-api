package com.mathias.jabuti.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoalDTO {

    private Long id;
    private String title;
    private String description;
    private Long userId;
}
