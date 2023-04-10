package tingeso_pep_1.tingeso_pep_1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;


@Entity
@Table(name = "nutricionaldata")
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
public class NutricionalEntity {
        @Id
        @NotNull
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer ID;
        private String Proveedor;
        private Integer Grasa;
        private Integer Solido;
}
