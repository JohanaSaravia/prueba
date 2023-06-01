package com.prueba.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "detalle_venta")
@DynamicInsert
@DynamicUpdate
@Data
public class SaleDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator =  "detalle_venta_generator")
    @SequenceGenerator(name = "detalle_venta_generator", sequenceName = "detalle_venta_seq", allocationSize = 1)
    @Column(name = "id_detalle_venta")
    private Long idDetalleVenta;

    @Column(name = "id_venta")
    private Long idVenta;

    @Column(name="id_medicamento")
    private Long idMedicamento;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "valor_unitario")
    private Double valorUnitario;
}
