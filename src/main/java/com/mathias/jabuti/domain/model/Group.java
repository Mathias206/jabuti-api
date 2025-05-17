package com.mathias.jabuti.domain.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "`groups`")
public class Group {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "group_name", nullable = false)
    @NotBlank
    private String name;

    @JoinTable(name = "group_permission", joinColumns = @JoinColumn(name = "group_id"), 
        inverseJoinColumns = @JoinColumn(name = "permission_id"))
    @ManyToMany
    private Set<Permission> permissions = new HashSet<>();

}
