package tingeso_pep_1.tingeso_pep_1.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;


@Entity
@Table(name = "proveedores")
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
public class ProveedorEntity {
    @Id
    @NotNull
    private String codigo;
    private String nombre;
    private String categoria;
    private String retencion;
}
