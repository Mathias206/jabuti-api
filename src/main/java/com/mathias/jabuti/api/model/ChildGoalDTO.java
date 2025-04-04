package com.mathias.jabuti.api.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ChildGoalDTO {

	private Long id;
    private String title;
    private String description;
    private String goalStatus;
    private String goalPriority;
    private String goalType;
}