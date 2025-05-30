package com.mathias.jabuti.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mathias.jabuti.Groups;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

  @CreationTimestamp
  @Column(nullable = false)
  private OffsetDateTime createdAt;

  @UpdateTimestamp
  @Column(nullable = false)
  private OffsetDateTime updatedAt;

  @Column(name="user_password", nullable = false)
  private String password;

  @JoinTable(name="user_group", joinColumns = @JoinColumn(name="user_id"), 
    inverseJoinColumns = @JoinColumn(name="group_id"))
  @ManyToMany
  private Set<Group> groups = new HashSet<>();
}
