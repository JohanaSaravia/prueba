package com.prueba.repository;

import com.prueba.model.entities.SaleDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDetailRepo extends JpaRepository<SaleDetailEntity,Integer> {

    @Query("Select u from SaleDetailEntity u where u.idVenta =:idVenta")
    List<SaleDetailEntity> consultSaleDetailXSale(@Param("idVenta") Long id);
}
