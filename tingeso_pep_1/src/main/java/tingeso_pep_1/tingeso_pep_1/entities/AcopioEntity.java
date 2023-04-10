package tingeso_pep_1.tingeso_pep_1.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "acopiodata")
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
public class AcopioEntity{
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String fecha;
    private String turno;
    private String proveedor;
    private String kls_leche;

}