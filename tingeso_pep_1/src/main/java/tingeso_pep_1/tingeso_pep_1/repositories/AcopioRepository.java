package tingeso_pep_1.tingeso_pep_1.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso_pep_1.tingeso_pep_1.entities.AcopioEntity;
import tingeso_pep_1.tingeso_pep_1.entities.ProveedorEntity;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface AcopioRepository extends JpaRepository <AcopioEntity, Integer>{

    @Query("select a from AcopioEntity a")
    List<AcopioEntity> findAll();

    @Query("select a.proveedor from AcopioEntity a where a.proveedor =: proveedor")
    String findName(@Param("proveedor") String proveedor);

    @Query("select a from AcopioEntity a where a.proveedor =: proveedor")
    List<AcopioEntity> obtenerAcopios(@Param("proveedor") String  proveedor);

    @Query("SELECT COUNT(a) FROM AcopioEntity a WHERE a.proveedor = :proveedor AND LOWER(a.turno) = LOWER('M')")
    Integer cantidadTurnoM(@Param("proveedor") String proveedor);


    @Query("SELECT COUNT(a) FROM AcopioEntity a WHERE a.proveedor = :proveedor AND LOWER(a.turno) = LOWER('T')")
    Integer cantidadTurnoT(@Param("proveedor") String proveedor);

    @Query("SELECT SUM(CAST(a.kls_leche AS double)) FROM AcopioEntity a WHERE a.proveedor = :proveedor")
    Double totalLecheProveedor(@Param("proveedor") String proveedor);

    @Query("SELECT COUNT(DISTINCT a.fecha) FROM AcopioEntity a WHERE a.proveedor = :proveedor")
    Integer totalDiasEnviados(@Param("proveedor") String proveedor);

}
