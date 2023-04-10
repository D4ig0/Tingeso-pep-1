package tingeso_pep_1.tingeso_pep_1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tingeso_pep_1.tingeso_pep_1.entities.AcopioEntity;
import tingeso_pep_1.tingeso_pep_1.entities.NutricionalEntity;

import java.util.ArrayList;

@Repository
public interface NutricionalRepository extends JpaRepository<NutricionalEntity, Integer> {

    @Query("select a from NutricionalEntity a")
    ArrayList<NutricionalEntity> findAll();

    void deleteAll();

}
