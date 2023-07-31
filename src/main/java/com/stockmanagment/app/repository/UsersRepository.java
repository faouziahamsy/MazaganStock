package com.stockmanagment.app.repository;

import com.stockmanagment.app.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
