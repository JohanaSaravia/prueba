package com.prueba.model.dto.out;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class DeleteInventoryDTO {

    String mensaje;
    Integer idInventario;
    Integer idMedicamento;
    Integer cantidad;
    Date fechaRegistro;
    Date fechaActualizacion;
}
