package tingeso_pep_1.tingeso_pep_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tingeso_pep_1.tingeso_pep_1.services.NutricionalService;
import tingeso_pep_1.tingeso_pep_1.entities.NutricionalEntity;
import tingeso_pep_1.tingeso_pep_1.repositories.NutricionalRepository;


@SpringBootTest
 class NutricionalTest {

    @Autowired
    NutricionalRepository nutricionalRepository;


    @Autowired
    NutricionalService nutricionalService;

    @Test
    void guardarDataDBTest() {
        nutricionalRepository.deleteAll();
        // Arrange
        String proveedor = "1004";
        String grasa = "10";
        String solido = "20";

        // Act
        nutricionalService.guardarDataDB(proveedor, grasa, solido);

        // Assert
        NutricionalEntity nutricionalEntity = nutricionalService.obtenerData().get(0);
        Assertions.assertEquals(proveedor, nutricionalEntity.getProveedor());
        Assertions.assertEquals(Integer.parseInt(grasa), nutricionalEntity.getGrasa());
        Assertions.assertEquals(Integer.parseInt(solido), nutricionalEntity.getSolidos_totales());
    }


}
