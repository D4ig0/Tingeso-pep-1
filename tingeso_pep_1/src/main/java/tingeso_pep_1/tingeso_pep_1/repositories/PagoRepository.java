package tingeso_pep_1.tingeso_pep_1.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso_pep_1.tingeso_pep_1.entities.PagoEntity;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository

public interface PagoRepository extends JpaRepository<PagoEntity, Integer> {


    @Query("SELECT p FROM PagoEntity p WHERE p.codigo_proveedor = :codigo AND p.id_pago= (SELECT MAX (p2.id_pago) FROM PagoEntity p2 WHERE p2.codigo_proveedor = :codigo)")
    PagoEntity obtenerPagoActual(@Param("codigo") String codigo);

    @Query("SELECT e FROM PagoEntity e WHERE e.id_pago = :numero AND e.codigo_proveedor= :codigo")
    PagoEntity obtenerPagoAnteriorByID(@Param("numero") Long numero, @Param("codigo") String codigo);


    @Query("SELECT p FROM PagoEntity p WHERE p.codigo_proveedor = :codigo AND p.id_pago < :idPagoActual ORDER BY p.id_pago DESC LIMIT 1")
    List<PagoEntity> obtenerPagoAnterior(@Param("codigo") String codigo, @Param("idPagoActual") Integer idPagoActual);

    @Query("SELECT p.id_pago FROM PagoEntity p WHERE p.codigo_proveedor = :codigo AND p.id_pago= (SELECT MAX (p2.id_pago) FROM PagoEntity p2 WHERE p2.codigo_proveedor = :codigo)")
    Long obtenerIdPagoActual(@Param("codigo") String codigo);

    @Query("SELECT COUNT(p) FROM PagoEntity p WHERE p.codigo_proveedor = :codigo")
    Integer obtenerCantPagos(@Param("codigo") String codigo);


    //PagoEntity getPagoEntityByCodigo_proveedor(String codigo);JPA PRO



}

