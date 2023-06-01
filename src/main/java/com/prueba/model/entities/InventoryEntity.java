package com.prueba.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;

@Entity
@Table(name = "inventario")
@DynamicInsert
@DynamicUpdate
@Data
public class InventoryEntity {

    @Id
    @Column(name = "id_inventario")
    @GeneratedValue(strategy = GenerationType.AUTO,generator =  "inventario_generator")
    @SequenceGenerator(name = "inventario_generator", sequenceName = "inventario_seq", allocationSize = 1)
    private Long idInventario;

    @Column(name = "cantidad")
    private Integer cantidad;

    @CreationTimestamp
    @Column(name = "fecha_registro")
    private Timestamp fechaRegistro;

    @CreationTimestamp
    @Column(name = "fecha_actualizacion")
    private Timestamp fechaActualizacion;

    @Column(name="id_medicamento")
    private Long idMedicamento;
}
