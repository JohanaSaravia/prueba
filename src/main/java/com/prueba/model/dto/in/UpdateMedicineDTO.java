package com.prueba.model.dto.in;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateMedicineDTO {

    private Long idMedicamento;
    private Double valorUnitario;
}
