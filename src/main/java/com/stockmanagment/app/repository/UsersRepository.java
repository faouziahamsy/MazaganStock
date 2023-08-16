package com.stockmanagment.app.repository;

import com.stockmanagment.app.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByMatricule(String matricule);

    Boolean existsByMatricule(String matricule);

   // Boolean existsByEmail(String email);
   // findOneByEmailAndPassword(String matricule, String password) ;


}
