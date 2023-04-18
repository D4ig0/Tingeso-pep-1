package tingeso_pep_1.tingeso_pep_1.entities;


import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pagodata")
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
public class PagoEntity {
    @Id
    @NotNull
    @Column(unique = true, nullable = false)
    private int id_pago;
    private int id_proveedor;


    private Double pago_leche;
    private Double pago_grasa;
    private Double pago_solido;
    private Date quincena;
    private String codigo_proveedor;
    private String nombre_proveedor;
    private Double total_kls_leche;
    private Double nro_dias_leche;
    private Double grasa;
    private Double solidos_totales;
    private Double promedio_diario_leche;
    private Double variacion_leche;
    private Double variacion_grasa;
    private Double variacion_solidos_totales;
    private Double frecuencia;
    private Double dcto_variacion_leche;
    private Double dcto_variacion_grasa;
    private Double dcto_variacion_solidos_totales;
    private Double pago_total;
    private Double monto_retencion;
    private Double monto_final;



}
