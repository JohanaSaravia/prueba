package com.prueba.repository;

import com.prueba.model.entities.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SaleRepo extends JpaRepository<SaleEntity,Integer> {

    @Query("Select u from SaleEntity u where u.fechaVenta between :fechaInicio and :fechaFin")
    List<SaleEntity> listSales(@Param("fechaInicio") Date inicio, @Param("fechaFin") Date fin );
}
