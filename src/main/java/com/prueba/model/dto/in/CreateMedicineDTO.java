package com.prueba.model.dto.in;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CreateMedicineDTO {

    String nombre;
    String laboratorioFabrica;
    Date fechaFabricacion;
    Date fechaVencimiento;
    Double valorUnitario;
}
