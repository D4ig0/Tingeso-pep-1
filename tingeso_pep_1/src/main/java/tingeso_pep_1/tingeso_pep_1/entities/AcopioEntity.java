package tingeso_pep_1.tingeso_pep_1.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity
@Table(name = "acopios")
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
public class AcopioEntity{
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id_acopio;
    private Integer id_proveedor;
    private String fecha;
    private String turno;
    private String proveedor;
    private String kls_leche;



}