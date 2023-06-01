package com.prueba.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;

@Entity
@Table(name="medicamento")
@DynamicInsert
@DynamicUpdate
@Data
public class MedicineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator =  "medicamento_generator")
    @SequenceGenerator(name = "medicamento_generator", sequenceName = "medicamento_seq", allocationSize = 1)
    @Column(name="id_medicamento")
    private Long idMedicamento;

    @Column(name="nombre")
    private String nombre;

    @Column(name="laboratorio_fabrica")
    private String laboratorioFabrica;

    @Column(name = "fecha_fabricacion")
    private Timestamp fechaFabricacion;

    @Column(name="fecha_vencimiento")
    private Timestamp fechaVencimiento;

    @Column(name = "valor_unitario")
    private Double valorUnitario;

}
