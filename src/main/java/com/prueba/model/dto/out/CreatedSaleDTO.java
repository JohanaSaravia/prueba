package com.prueba.model.dto.out;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class CreatedSaleDTO {

    Long idVenta;
    List<SaleDetailCompleteDTO> listaDetalleVenta;
    Date fechaVenta;
    Double valorTotal;
}
