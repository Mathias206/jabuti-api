package com.mathias.jabuti.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "users")
public class User {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @EqualsAndHashCode.Include
  private Long id;

  @Column(name = "user_name", nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private List<Goal> goals = new ArrayList<>();
}
