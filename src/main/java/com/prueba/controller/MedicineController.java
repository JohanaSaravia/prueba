package com.prueba.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.model.dto.in.CreateMedicineDTO;
import com.prueba.model.dto.in.UpdateMedicineDTO;
import com.prueba.model.dto.out.CreatedMedicineDTO;
import com.prueba.model.dto.out.DeletedMedicineDTO;
import com.prueba.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("drogueria")
public class MedicineController {

    @Autowired
    MedicineService medicineService;
    @Autowired
    private ObjectMapper mapper;

    @PostMapping("/crearMedicamento")
    public ResponseEntity<String> createMedicine(@RequestBody CreateMedicineDTO creacionMedicamento){
        try{
            CreatedMedicineDTO medicine = medicineService.createMedicine(creacionMedicamento);
            return ResponseEntity.ok(mapper.writeValueAsString(medicine));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("ERROR "+ e.getMessage());
        }
    }

    @GetMapping("/consultarMedicamento/{id}")
    public ResponseEntity<String> consultMedicine(@PathVariable("id") Long id){
        try{
            CreatedMedicineDTO medicine = medicineService.consultMedicine(id);
            return ResponseEntity.ok(mapper.writeValueAsString(medicine));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("ERROR "+ e.getMessage());
        }
    }

    @DeleteMapping("/eliminarMedicamento/{id}")
    public ResponseEntity<String>  deleteMedicine(@PathVariable("id")Long id){
        try{
            DeletedMedicineDTO medicine = medicineService.deleteMedicine(id);
            return ResponseEntity.ok(mapper.writeValueAsString(medicine));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("ERROR "+ e.getMessage());
        }
    }

    @GetMapping("/listarMedicamentos")
    public ResponseEntity<String> consultMedicine(){
        try{
            List<CreatedMedicineDTO> medicine = medicineService.listMedicine();
            return ResponseEntity.ok(mapper.writeValueAsString(medicine));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("ERROR "+ e.getMessage());
        }
    }

    @PostMapping("/actualizarMedicamento")
    public ResponseEntity<String> updateMedicine(@RequestBody UpdateMedicineDTO updateMedicineDTO){
        try{
            CreatedMedicineDTO medicineDTO = medicineService.updateMedicine(updateMedicineDTO);
            return ResponseEntity.ok(mapper.writeValueAsString(medicineDTO));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("ERROR "+ e.getMessage());
        }
    }
}
