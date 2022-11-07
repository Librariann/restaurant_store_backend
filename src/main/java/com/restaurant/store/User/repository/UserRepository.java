package com.restaurant.store.User.repository;

import com.restaurant.store.User.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
