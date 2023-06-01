package com.prueba.model.dto.in;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateInventoryDTO {

    Long idMedicamento;
    Integer cantidad;
}
