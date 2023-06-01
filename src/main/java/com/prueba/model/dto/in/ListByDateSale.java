package com.prueba.model.dto.in;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ListByDateSale {

    Date fechaInicio;
    Date fechaFin;
}
