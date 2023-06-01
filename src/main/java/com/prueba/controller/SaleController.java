package com.prueba.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.model.dto.in.CreateSaleDTO;
import com.prueba.model.dto.in.ListByDateSale;
import com.prueba.model.dto.out.CreatedSaleDTO;
import com.prueba.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("drogueria")
public class SaleController {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private SaleService service;

    @GetMapping("/listarVentas")
    public ResponseEntity<String> listSales(){
        try{
            List<CreatedSaleDTO> listInventory = service.listComplete();
            return ResponseEntity.ok(mapper.writeValueAsString(listInventory));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("ERROR "+ e.getMessage());
        }
    }

    @PostMapping("/crearVentas")
    public ResponseEntity<String> createSale(@RequestBody CreateSaleDTO createSaleDTO){
        try{
            CreatedSaleDTO createdSaleDTO = service.createSale(createSaleDTO);
            return ResponseEntity.ok(mapper.writeValueAsString(createdSaleDTO));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("ERROR "+ e.getMessage());
        }
    }

    @PostMapping("/listarVentasPorFechas")
    public ResponseEntity<String> listSalesByDate(@RequestBody ListByDateSale byDateSale){
        try{
            List<CreatedSaleDTO> listInventory = service.listSalesByDate(byDateSale);
            return ResponseEntity.ok(mapper.writeValueAsString(listInventory));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("ERROR "+ e.getMessage());
        }
    }
}
