package com.prueba.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.model.dto.in.CreateInventoryDTO;
import com.prueba.model.dto.in.UpdateInventoryDTO;
import com.prueba.model.dto.out.CreatedInventoryDTO;
import com.prueba.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("drogueria")
public class InventoryController {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    InventoryService inventoryService;


    @PostMapping("/crearInventario")
    public ResponseEntity<String> createInventory(@RequestBody CreateInventoryDTO createInventoryDTO){
        try{
            CreatedInventoryDTO createdInventoryDTO = inventoryService.createInventory(createInventoryDTO);
            return ResponseEntity.ok(mapper.writeValueAsString(createdInventoryDTO));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("ERROR "+ e.getMessage());
        }
    }

    @GetMapping("/consultarInventarioXMedicamento/{idMedicamento}")
    public ResponseEntity<String> consultInventoryWithIdMedicine(@PathVariable("idMedicamento") Long id){
        try{
            CreatedInventoryDTO medicine = inventoryService.consultMedicine(id);
            return ResponseEntity.ok(mapper.writeValueAsString(medicine));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("ERROR "+ e.getMessage());
        }
    }

    @GetMapping("/consultarInventario/{idInventario}")
    public ResponseEntity<String> consultInventory(@PathVariable("idInventario") Long id){
        try{
            CreatedInventoryDTO medicine = inventoryService.consultInventory(id);
            return ResponseEntity.ok(mapper.writeValueAsString(medicine));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("ERROR "+ e.getMessage());
        }
    }

    @GetMapping("/listarInventario")
    public ResponseEntity<String> listInventory(){
        try{
            List<CreatedInventoryDTO> listInventory = inventoryService.listComplete();
            return ResponseEntity.ok(mapper.writeValueAsString(listInventory));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("ERROR "+ e.getMessage());
        }
    }

    @PostMapping("/actualizarInventario")
    public ResponseEntity<String> updateInventory(@RequestBody UpdateInventoryDTO updateInventoryDTO){
        try{
            CreatedInventoryDTO createdInventoryDTO = inventoryService.updateInventory(updateInventoryDTO);
            return ResponseEntity.ok(mapper.writeValueAsString(createdInventoryDTO));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("ERROR "+ e.getMessage());
        }
    }
}

