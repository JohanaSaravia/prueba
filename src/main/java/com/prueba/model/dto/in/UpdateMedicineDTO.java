package com.prueba.model.dto.in;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateMedicineDTO {

    private Long idMedicine;
    private Double valorUnitario;
}
