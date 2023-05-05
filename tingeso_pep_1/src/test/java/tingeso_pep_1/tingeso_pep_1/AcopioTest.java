package tingeso_pep_1.tingeso_pep_1;


import org.junit.jupiter.api.Assertions;
import tingeso_pep_1.tingeso_pep_1.entities.AcopioEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tingeso_pep_1.tingeso_pep_1.services.AcopioService;

import java.time.LocalDate;





@SpringBootTest
class AcopioTest {

    @Autowired
    private AcopioService acopioService;


    @Test
    void guardarDataDBTest() {

        // Arrange

        String fecha = LocalDate.now().toString();
        String turno = "M";
        String proveedor = "Proveedor 1";
        String kls_leche = "500";
        // Act
        acopioService.guardarDataDB(fecha, turno, proveedor, kls_leche);
        // Assert
        AcopioEntity acopioEntity = acopioService.obtenerData().get(0);
        Assertions.assertEquals(fecha, acopioEntity.getFecha());
        Assertions.assertEquals(turno, acopioEntity.getTurno());
        Assertions.assertEquals(proveedor, acopioEntity.getProveedor());
        Assertions.assertEquals(kls_leche, acopioEntity.getKls_leche());
    }




}
