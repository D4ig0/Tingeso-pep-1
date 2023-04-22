package tingeso_pep_1.tingeso_pep_1.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso_pep_1.tingeso_pep_1.entities.NutricionalEntity;
import tingeso_pep_1.tingeso_pep_1.entities.PagoEntity;

import java.util.ArrayList;

@Repository

public interface PagoRepository extends JpaRepository<PagoEntity, Integer> {
    @Query("SELECT p FROM PagoEntity p WHERE p.id_proveedor=: pago  ORDER BY p.id_pago DESC")
    PagoEntity obtenerPagoActual(@Param("pago") int pago);


    @Query("SELECT p FROM PagoEntity p WHERE p.id_proveedor = :idActual AND p.id_pago < :idActual ORDER BY p.id_pago DESC")
    PagoEntity obtenerPagoAnterior(@Param("idActual") int idActual);

}
