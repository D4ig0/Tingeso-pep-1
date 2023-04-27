package tingeso_pep_1.tingeso_pep_1.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso_pep_1.tingeso_pep_1.entities.NutricionalEntity;
import tingeso_pep_1.tingeso_pep_1.entities.PagoEntity;

import java.util.ArrayList;
import java.util.Date;

@Repository

public interface PagoRepository extends JpaRepository<PagoEntity, Integer> {


    @Query("SELECT p FROM PagoEntity p WHERE p.codigo_proveedor = :codigo AND p.id_pago = (SELECT MAX(p2.id_pago) FROM PagoEntity p2 WHERE p2.codigo_proveedor = :codigo)")
    PagoEntity obtenerPagoActual(@Param("codigo") String codigo);

    @Query("SELECT p FROM PagoEntity p WHERE p.id_pago < :codigo ORDER BY p.id_pago DESC LIMIT 1")
    PagoEntity obtenerPagoAnterior(@Param("codigo") int codigo);

    @Query("SELECT COUNT(p) FROM PagoEntity p WHERE p.codigo_proveedor = :codigo")
    Integer obtenerCantPagos(@Param("codigo") String codigo);


    //PagoEntity getPagoEntityByCodigo_proveedor(String codigo);JPA PRO



}

