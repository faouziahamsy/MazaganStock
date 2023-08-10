package com.stockmanagment.app.repository;

import com.stockmanagment.app.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByMatricule(String matricule);

    Boolean existsByMatricule(String matricule);

   // Boolean existsByEmail(String email);
   // findOneByEmailAndPassword(String matricule, String password) ;


}
