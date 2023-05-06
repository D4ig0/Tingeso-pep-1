package tingeso_pep_1.tingeso_pep_1;


import org.junit.jupiter.api.Assertions;
import tingeso_pep_1.tingeso_pep_1.entities.AcopioEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tingeso_pep_1.tingeso_pep_1.entities.NutricionalEntity;
import tingeso_pep_1.tingeso_pep_1.repositories.AcopioRepository;
import tingeso_pep_1.tingeso_pep_1.services.AcopioService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class AcopioTest {

    @Autowired
    private AcopioService acopioService;
    @Autowired
    private AcopioRepository acopioRepository;


    @Test
    void testGuardarDataDB() {

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

    @Test
    void testEliminarData() {
        // Arrange
        AcopioEntity acopio1 = new AcopioEntity();
        acopio1.setFecha("10/02/2021");
        acopio1.setId_acopio(1);
        acopio1.setProveedor("1003");
        acopio1.setKls_leche("100");
        acopio1.setTurno("M");

        AcopioEntity acopio2 = new AcopioEntity();
        acopio2.setFecha("11/02/2021");
        acopio2.setId_acopio(2);
        acopio2.setProveedor("1004");
        acopio2.setKls_leche("100");
        acopio2.setTurno("M");

        List<AcopioEntity> lista = new ArrayList<>();
        lista.add(acopio1);
        lista.add(acopio2);

        // Guardar datos en la base de datos para verificar que se eliminan correctamente
        acopioRepository.saveAll(lista);

        // Verificar que los datos están en la base de datos
        List<AcopioEntity> datosAntes = acopioRepository.findAll();
        assertEquals(2, datosAntes.size());

        // Act
        acopioService.eliminarData(lista);

        // Verificar que los datos han sido eliminados de la base de datos
        List<AcopioEntity> result = acopioRepository.findAll();
        assertEquals(0, result.size());
    }




}
