package tingeso_pep_1.tingeso_pep_1.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "proveedores")
@NoArgsConstructor
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
