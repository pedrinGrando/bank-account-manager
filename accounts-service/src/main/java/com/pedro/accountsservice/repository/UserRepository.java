package com.pedro.accountsservice.repository;

import com.pedro.accountsservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
