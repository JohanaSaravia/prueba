package com.prueba.model.dto.out;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CreatedInventoryDTO {

    Long idInventario;
    Long idMedicamento;
    String nombreMedicamento;
    String laboratorioFabrica;
    Double valorUnitario;
    Integer cantidad;
    Date fechaRegistro;
    Date fechaActualizacion;
}
