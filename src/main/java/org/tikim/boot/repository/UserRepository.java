package org.tikim.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tikim.boot.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
