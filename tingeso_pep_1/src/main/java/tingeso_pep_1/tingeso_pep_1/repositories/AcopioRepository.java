package tingeso_pep_1.tingeso_pep_1.repositories;


import tingeso_pep_1.tingeso_pep_1.AcopioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AcopioRepository extends JpaRepository <AcopioRepository, Integer>{
}