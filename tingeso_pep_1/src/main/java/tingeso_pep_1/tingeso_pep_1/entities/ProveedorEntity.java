package tingeso_pep_1.tingeso_pep_1.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;


import java.util.List;


@Entity
@Table(name = "proveedoresdata")
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
public class ProveedorEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)

    private int id_proveedor;
    private String codigo;
    private String nombre;
    private String categoria;
    private String retencion;


    @OneToMany(mappedBy = "proveedor")
    private List<AcopioEntity> proveedor_acopio;

}

