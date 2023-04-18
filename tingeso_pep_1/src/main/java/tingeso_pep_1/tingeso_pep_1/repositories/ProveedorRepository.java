package tingeso_pep_1.tingeso_pep_1.repositories;

import tingeso_pep_1.tingeso_pep_1.entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity, String> {

    @Query("select e from ProveedorEntity e where e.nombre = :nombre")
    ProveedorEntity findByNameCustomQuery(@Param("nombre") String nombre);

    @Query("select e.categoria from ProveedorEntity e where e.codigo = :codigo")
    String findCategory(@Param("codigo") String codigo);

    @Query("select e from ProveedorEntity e where e.codigo = :codigo")
    ProveedorEntity findByCodigo(@Param("codigo") String codigo);

    @Query("select u.id_proveedor from ProveedorEntity u where u.codigo=:numero ")
    String findById(@Param("numero") int numero);


}
