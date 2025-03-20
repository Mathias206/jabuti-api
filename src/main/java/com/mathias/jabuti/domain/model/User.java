package com.mathias.jabuti.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mathias.jabuti.Groups;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "users")
public class User {

  @NotNull(groups = Groups.UserId.class)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @EqualsAndHashCode.Include
  private Long id;

  @NotBlank
  @Column(name = "user_name", nullable = false)
  private String name;

  @NotBlank
  @Column(nullable = false)
  private String email;

  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private List<Goal> goals = new ArrayList<>();
}
