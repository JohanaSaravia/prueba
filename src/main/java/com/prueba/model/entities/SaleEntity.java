package com.prueba.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;

@Entity
@Table(name = "venta")
@DynamicInsert
@DynamicUpdate
@Data
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator =  "venta_generator")
    @SequenceGenerator(name = "venta_generator", sequenceName = "venta_seq", allocationSize = 1)
    @Column(name = "id_venta")
    private Long idVenta;

    @CreationTimestamp
    @Column(name = "fecha_venta")
    private Timestamp fechaVenta;

    @Column(name = "valor_total")
    private Double valorTotal;
}
