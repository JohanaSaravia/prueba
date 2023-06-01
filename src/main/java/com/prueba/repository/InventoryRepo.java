package com.prueba.repository;

import com.prueba.model.entities.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<InventoryEntity,Integer> {

    @Query("Select u from InventoryEntity u where u.idMedicamento =:id")
    InventoryEntity findMedicine(@Param("id") Long idMedicine);

}
