package com.prueba.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.model.dto.in.CreateInventoryDTO;
import com.prueba.model.dto.in.UpdateInventoryDTO;
import com.prueba.model.dto.out.CreatedInventoryDTO;
import com.prueba.model.dto.out.CreatedMedicineDTO;
import com.prueba.model.dto.out.DeleteInventoryDTO;
import com.prueba.model.entities.InventoryEntity;
import com.prueba.repository.InventoryRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(rollbackOn = {Exception.class})
public class InventoryService {


    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    ObjectMapper objectMapper;

    public CreatedInventoryDTO createInventory(CreateInventoryDTO createInventoryDTO) throws JsonProcessingException {
        validateParameterInventory(createInventoryDTO);
        CreatedMedicineDTO medicine = medicineService.consultMedicine(createInventoryDTO.getIdMedicamento());
        if (medicine != null) {
            InventoryEntity entity = objectMapper.readValue(objectMapper.writeValueAsString(createInventoryDTO), InventoryEntity.class);
            entity = inventoryRepo.save(entity);
            CreatedInventoryDTO createdInventoryDTO = objectMapper.readValue(objectMapper.writeValueAsString(entity), CreatedInventoryDTO.class);
            createdInventoryDTO.setLaboratorioFabrica(medicine.getLaboratorioFabrica());
            createdInventoryDTO.setNombreMedicamento(medicine.getNombre());
            createdInventoryDTO.setValorUnitario(medicine.getValorUnitario());
            return createdInventoryDTO;
        } else {
            throw new IllegalArgumentException("Don't possible create register of inventory");
        }
    }

    public void validateParameterInventory(CreateInventoryDTO createdInventoryDTO) {
        if (createdInventoryDTO == null ||
                createdInventoryDTO.getIdMedicamento() == null || createdInventoryDTO.getIdMedicamento() == 0 ||
                createdInventoryDTO.getCantidad() == null
        ) {
            throw new IllegalArgumentException("The parameters aren't complete");
        }
    }

    public CreatedInventoryDTO consultMedicine(Long id) throws JsonProcessingException {
        CreatedMedicineDTO medicineDTO = medicineService.consultMedicine(id);
        if (medicineDTO == null) {
            return new CreatedInventoryDTO();
        } else {
            InventoryEntity entity = inventoryRepo.findMedicine(id);
            if (entity == null) {
                return new CreatedInventoryDTO();
            }
            CreatedInventoryDTO createdInventoryDTO = objectMapper.readValue(objectMapper.writeValueAsString(entity), CreatedInventoryDTO.class);
            createdInventoryDTO.setNombreMedicamento(medicineDTO.getNombre());
            createdInventoryDTO.setValorUnitario(medicineDTO.getValorUnitario());
            createdInventoryDTO.setLaboratorioFabrica(medicineDTO.getLaboratorioFabrica());
            return createdInventoryDTO;
        }
    }

    public CreatedInventoryDTO consultInventory(Long id) throws JsonProcessingException {
        Optional<InventoryEntity> entity = inventoryRepo.findById(id.intValue());
        if (entity.isEmpty()) {
            return new CreatedInventoryDTO();
        }
        CreatedMedicineDTO medicineDTO = medicineService.consultMedicine(entity.get().getIdMedicamento());
        CreatedInventoryDTO createdInventoryDTO = objectMapper.readValue(objectMapper.writeValueAsString(entity), CreatedInventoryDTO.class);
        createdInventoryDTO.setNombreMedicamento(medicineDTO.getNombre());
        createdInventoryDTO.setValorUnitario(medicineDTO.getValorUnitario());
        createdInventoryDTO.setLaboratorioFabrica(medicineDTO.getLaboratorioFabrica());
        return createdInventoryDTO;

    }

    public DeleteInventoryDTO deleteInventory(Long id) throws JsonProcessingException {
        try {
            InventoryEntity entity = inventoryRepo.findMedicine(id);
            if (entity == null) {
                throw new IllegalArgumentException("El medicamento no existe");
            }
            inventoryRepo.deleteById(entity.getIdInventario().intValue());
            DeleteInventoryDTO delete = objectMapper.readValue(objectMapper.writeValueAsString(entity), DeleteInventoryDTO.class);
            delete.setMensaje("Se ha eliminado correctamente el inventario");
            return delete;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public List<CreatedInventoryDTO> listComplete() throws JsonProcessingException {
        List<InventoryEntity> listEntity = inventoryRepo.findAll();
        List<CreatedInventoryDTO> inventoryList = new ArrayList<>();
        for (InventoryEntity entity : listEntity) {
            CreatedInventoryDTO createdInventoryDTO = objectMapper.readValue(objectMapper.writeValueAsString(entity), CreatedInventoryDTO.class);
            CreatedMedicineDTO medicineDTO = medicineService.consultMedicine(entity.getIdMedicamento());
            createdInventoryDTO.setNombreMedicamento(medicineDTO.getNombre());
            createdInventoryDTO.setValorUnitario(medicineDTO.getValorUnitario());
            createdInventoryDTO.setLaboratorioFabrica(medicineDTO.getLaboratorioFabrica());
            inventoryList.add(createdInventoryDTO);
        }
        return inventoryList;
    }

    public CreatedInventoryDTO updateInventory(UpdateInventoryDTO update) throws JsonProcessingException {
        CreatedInventoryDTO createdInventoryDTO = consultInventory(update.getIdInventario());
        if (createdInventoryDTO==null) {
            throw new IllegalArgumentException("No existe inventario para actualizar");
        }
        try {
            InventoryEntity entityUpdate = objectMapper.readValue(objectMapper.writeValueAsString(createdInventoryDTO), InventoryEntity.class);
            entityUpdate.setFechaActualizacion(Timestamp.from(Instant.now()));
            entityUpdate.setCantidad(update.getCantidad());
            entityUpdate = inventoryRepo.save(entityUpdate);
            createdInventoryDTO.setCantidad(entityUpdate.getCantidad());
            createdInventoryDTO.setFechaActualizacion(entityUpdate.getFechaActualizacion());
            return createdInventoryDTO;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new IllegalArgumentException(e.getMessage());
        }
    }


}
