package com.prueba.model.dto.out;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaleDetailCompleteDTO {

    Long idDetalleVenta;
    Long idMedicamento;
    Integer cantidad;
    Double valorUnitario;
}
