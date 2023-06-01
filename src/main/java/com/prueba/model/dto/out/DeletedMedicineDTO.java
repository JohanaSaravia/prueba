package com.prueba.model.dto.out;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class DeletedMedicineDTO {

    String mensaje;
    Long idMedicamento;
    String nombre;
    String laboratorioFabrica;
    Date fechaFabricacion;
    Date fechaVencimiento;
    Double valorUnitario;
}
