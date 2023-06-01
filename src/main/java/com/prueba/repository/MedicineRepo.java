package com.prueba.repository;

import com.prueba.model.entities.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepo extends JpaRepository<MedicineEntity,Integer> {
}
