package com.prueba.model.dto.out;

import lombok.Data;

import java.util.Date;

@Data
public class CreatedMedicineDTO {

    Long idMedicamento;
    String nombre;
    String laboratorioFabrica;
    Date fechaFabricacion;
    Date fechaVencimiento;
    Double valorUnitario;
}
