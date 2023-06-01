package com.prueba.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.model.dto.in.CreateMedicineDTO;
import com.prueba.model.dto.in.UpdateMedicineDTO;
import com.prueba.model.dto.out.CreatedInventoryDTO;
import com.prueba.model.dto.out.CreatedMedicineDTO;
import com.prueba.model.dto.out.DeletedMedicineDTO;
import com.prueba.model.entities.MedicineEntity;
import com.prueba.repository.MedicineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepo medicineRepo;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ObjectMapper objectMapper;
    public CreatedMedicineDTO createMedicine(CreateMedicineDTO createMedicineDTO) throws JsonProcessingException {
        validateParamsMedicine(createMedicineDTO);
        MedicineEntity medicineEntity = objectMapper.readValue(objectMapper.writeValueAsString(createMedicineDTO),MedicineEntity.class);
        MedicineEntity medicineCreated = medicineRepo.save(medicineEntity);
       return objectMapper.readValue(objectMapper.writeValueAsString(medicineCreated), CreatedMedicineDTO.class);
    }

    public void validateParamsMedicine(CreateMedicineDTO medicineDTO){
        if(medicineDTO==null||medicineDTO.getNombre()==null || medicineDTO.getNombre().trim().isEmpty()||
            medicineDTO.getLaboratorioFabrica()==null || medicineDTO.getLaboratorioFabrica().trim().isEmpty()||
                medicineDTO.getFechaFabricacion()==null ||
                medicineDTO.getFechaVencimiento()==null ||
                medicineDTO.getValorUnitario()==null || medicineDTO.getValorUnitario()==0
        ){
            throw new IllegalArgumentException("The parameters aren't complete");
        }
    }

    public CreatedMedicineDTO consultMedicine(Long id) throws JsonProcessingException {
        Optional<MedicineEntity> entity = medicineRepo.findById(id.intValue());
        if(entity.isEmpty()){
            new CreatedMedicineDTO();
        }
        return objectMapper.readValue(objectMapper.writeValueAsString(entity.get()), CreatedMedicineDTO.class);
    }

    public DeletedMedicineDTO deleteMedicine(Long id) throws JsonProcessingException {
        try{
            Optional<MedicineEntity> entity = medicineRepo.findById(id.intValue());
            if(entity.isEmpty()){
                throw new IllegalArgumentException("El medicamento no existe");
            }
            CreatedInventoryDTO inventory = inventoryService.consultMedicine(id);
            if(inventory!=null){
                inventoryService.deleteInventory(id);
            }
            medicineRepo.deleteById(id.intValue());
            DeletedMedicineDTO delete = objectMapper.readValue(objectMapper.writeValueAsString(entity.get()), DeletedMedicineDTO.class);
            delete.setMensaje("Se ha eliminado correctamente el medicamento");
            return delete;
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public List<CreatedMedicineDTO> listMedicine() throws JsonProcessingException {
        List<MedicineEntity> listEntity = medicineRepo.findAll();
        List<CreatedMedicineDTO> list = new ArrayList<>();
        for(MedicineEntity e : listEntity){
            CreatedMedicineDTO medicine =  objectMapper.readValue(objectMapper.writeValueAsString(e), CreatedMedicineDTO.class);
            list.add(medicine);
        }
        return list;
    }

    public CreatedMedicineDTO updateMedicine(UpdateMedicineDTO medicineDTO) throws JsonProcessingException {
        Optional<MedicineEntity> entity = medicineRepo.findById(medicineDTO.getIdMedicamento().intValue());
        if(entity.isEmpty()){
            throw new IllegalArgumentException("No es posible actualizar no existe medicamento");
        }
        MedicineEntity medicineEntity = entity.get();
        medicineEntity.setValorUnitario(medicineDTO.getValorUnitario());
        medicineEntity = medicineRepo.save(medicineEntity);
        return   objectMapper.readValue(objectMapper.writeValueAsString(medicineEntity), CreatedMedicineDTO.class);
    }
}
