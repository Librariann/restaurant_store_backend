package com.restaurant.store.user.repository;

import com.restaurant.store.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
