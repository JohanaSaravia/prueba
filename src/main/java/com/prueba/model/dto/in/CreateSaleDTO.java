package com.prueba.model.dto.in;

import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
public class CreateSaleDTO {

    List<SaleDetailDTO> detalleVenta;
}
