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

    @Query("select a.proveedor from AcopioEntity  a where a.proveedor =: proveedor")
    String findName();

    @Query("select a from AcopioEntity a where a.id_proveedor =: proveedor")
    List<AcopioEntity> obtenerProveedor(@Param("proveedor") String  proveedor);
}
