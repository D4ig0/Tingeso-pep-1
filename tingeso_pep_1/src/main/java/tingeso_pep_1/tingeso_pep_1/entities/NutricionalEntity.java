package tingeso_pep_1.tingeso_pep_1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;


@Entity
@Table(name = "nutricionales")
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
public class NutricionalEntity {
        @Id
        @NotNull
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(unique = true, nullable = false)
        private Integer id_nutricional;
        private String  proveedor;
        private Integer grasa;
        private Integer solidos_totales;



}
