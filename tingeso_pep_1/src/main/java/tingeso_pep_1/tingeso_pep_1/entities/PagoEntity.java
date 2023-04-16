package tingeso_pep_1.tingeso_pep_1.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

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
    private Integer pago_leche;
    private Integer pago_grasa;
    private Integer pago_solido;

    @OneToMany(mappedBy = "pago")
    private List<NutricionalEntity> pago_nutricional;

}
