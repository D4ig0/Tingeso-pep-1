package tingeso_pep_1.tingeso_pep_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tingeso_pep_1.tingeso_pep_1.entities.ProveedorEntity;
import tingeso_pep_1.tingeso_pep_1.services.NutricionalService;
import tingeso_pep_1.tingeso_pep_1.entities.NutricionalEntity;
import tingeso_pep_1.tingeso_pep_1.repositories.NutricionalRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
 class NutricionalTest {

    @Autowired
    NutricionalRepository nutricionalRepository;


    @Autowired
    NutricionalService nutricionalService;

    @Test
    void testGuardarDataDB() {
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

    @Test
    void testEliminarData() {
        // Arrange
        NutricionalEntity nutricional1 = new NutricionalEntity();
        nutricional1.setProveedor("1001");
        nutricional1.setGrasa(10);
        nutricional1.setSolidos_totales(5);

        NutricionalEntity nutricional2 = new NutricionalEntity();
        nutricional2.setProveedor("1002");
        nutricional2.setGrasa(20);
        nutricional2.setSolidos_totales(10);

        List<NutricionalEntity> lista = new ArrayList<>();
        lista.add(nutricional1);
        lista.add(nutricional2);

        // Guardar datos en la base de datos para verificar que se eliminan correctamente
        nutricionalRepository.saveAll(lista);

        // Verificar que los datos están en la base de datos
        List<NutricionalEntity> datosAntes = nutricionalRepository.findAll();
        assertEquals(2, datosAntes.size());

        // Act
        nutricionalService.eliminarData(lista);

        // Verificar que los datos han sido eliminados de la base de datos
        List<NutricionalEntity> result = nutricionalRepository.findAll();
        assertEquals(0, result.size());
    }




    }



