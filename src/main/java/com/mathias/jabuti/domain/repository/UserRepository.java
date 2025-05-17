package com.mathias.jabuti.domain.repository;

import com.mathias.jabuti.api.model.UserEmailPasswordProjection;
import com.mathias.jabuti.domain.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"groups", "groups.permissions"})
    Optional<User> findByEmail(@Param("email") String email);

    @Query("select u.email as email, u.password as password from User u where u.email = :email")
    Optional<UserEmailPasswordProjection> findByEmailProjected(@Param("email") String email);


}
