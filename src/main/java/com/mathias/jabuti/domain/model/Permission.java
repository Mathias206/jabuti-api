package com.mathias.jabuti.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity(name = "permissions")
public class Permission {

    @EqualsAndHashCode.Include
    @Id
    private Long id;

    @Column(name = "permission_name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "permission_description", nullable = true)
    private String description;

}
