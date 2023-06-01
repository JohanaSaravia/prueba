package com.prueba.repository;

import com.prueba.model.entities.SaleDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailRepo extends JpaRepository<SaleDetailEntity,Integer> {
}
