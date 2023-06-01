package com.prueba.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.model.dto.in.CreateSaleDTO;
import com.prueba.model.dto.in.SaleDetailDTO;
import com.prueba.model.dto.in.UpdateInventoryDTO;
import com.prueba.model.dto.out.CreatedInventoryDTO;
import com.prueba.model.dto.out.CreatedSaleDTO;
import com.prueba.model.dto.out.SaleDetailCompleteDTO;
import com.prueba.model.entities.SaleDetailEntity;
import com.prueba.model.entities.SaleEntity;
import com.prueba.repository.SaleDetailRepo;
import com.prueba.repository.SaleRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepo saleRepo;

    @Autowired
    private SaleDetailRepo saleDetailRepo;

    @Autowired
    private  InventoryService inventoryService;

    @Autowired
    private ObjectMapper objectMapper;
    @Transactional(rollbackOn = {Exception.class})
    public CreatedSaleDTO createSale(CreateSaleDTO createSaleDTO) throws JsonProcessingException {
        if(createSaleDTO.getDetalleVenta().isEmpty()){
            return new CreatedSaleDTO();
        }
        SaleEntity saleEntity = saleRepo.save(new SaleEntity());
        Double valorFinal = null;
        List<SaleDetailCompleteDTO> salesDetailList=new ArrayList<>();
        for(SaleDetailDTO e : createSaleDTO.getDetalleVenta()){
            try{
                CreatedInventoryDTO inventoryDTO = inventoryService.consultMedicine(e.getIdMedicamento());
                if(inventoryDTO==null || e.getCantidad()>inventoryDTO.getCantidad()){
                   throw new RuntimeException();
                }
                int cantidad = inventoryDTO.getCantidad()-e.getCantidad();
                inventoryDTO.setCantidad(e.getCantidad());
                SaleDetailCompleteDTO saleDetail = createSaleDetail(inventoryDTO,saleEntity);
                inventoryService.updateInventory(new UpdateInventoryDTO(inventoryDTO.getIdInventario(), cantidad));
                salesDetailList.add(saleDetail);
                valorFinal += saleDetail.getValorUnitario()*saleDetail.getCantidad();
            }catch(Exception ex){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new RuntimeException(ex.getMessage());
            }
        }
        return convertEntityToBean(saleEntity,salesDetailList,valorFinal);
    }

    public CreatedSaleDTO convertEntityToBean(SaleEntity saleEntity,List<SaleDetailCompleteDTO> salesDetailList,Double valorFinal){
        CreatedSaleDTO createdSaleDTO= new CreatedSaleDTO();
        createdSaleDTO.setIdVenta(saleEntity.getIdVenta());
        createdSaleDTO.setValorTotal(valorFinal);
        createdSaleDTO.setListaDetalleVenta(salesDetailList);
        createdSaleDTO.setFechaVenta(saleEntity.getFechaVenta());
        return createdSaleDTO;
    }
    public SaleDetailCompleteDTO createSaleDetail(CreatedInventoryDTO saleDetailDTO,SaleEntity saleEntity){
        try{
            SaleDetailEntity entity = objectMapper.readValue(objectMapper.writeValueAsString(saleDetailDTO), SaleDetailEntity.class);
            entity.setIdVenta(saleEntity.getIdVenta());
            entity = saleDetailRepo.save(entity);
            return objectMapper.readValue(objectMapper.writeValueAsString(entity), SaleDetailCompleteDTO.class);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
