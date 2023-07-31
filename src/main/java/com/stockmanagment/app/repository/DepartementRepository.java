package com.stockmanagment.app.repository;

import com.stockmanagment.app.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Long> {

}
