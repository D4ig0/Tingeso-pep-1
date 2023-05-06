package tingeso_pep_1.tingeso_pep_1;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tingeso_pep_1.tingeso_pep_1.entities.AcopioEntity;
import tingeso_pep_1.tingeso_pep_1.entities.PagoEntity;
import tingeso_pep_1.tingeso_pep_1.entities.ProveedorEntity;
import tingeso_pep_1.tingeso_pep_1.repositories.AcopioRepository;
import tingeso_pep_1.tingeso_pep_1.repositories.NutricionalRepository;
import tingeso_pep_1.tingeso_pep_1.repositories.PagoRepository;
import tingeso_pep_1.tingeso_pep_1.repositories.ProveedorRepository;
import tingeso_pep_1.tingeso_pep_1.services.AcopioService;
import tingeso_pep_1.tingeso_pep_1.services.NutricionalService;
import tingeso_pep_1.tingeso_pep_1.services.PagoService;
import tingeso_pep_1.tingeso_pep_1.services.ProveedorService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PagoTest {


    @Autowired
    ProveedorRepository proveedorRepository;
    @Autowired
    ProveedorService proveedorService;

    @Autowired
    PagoService pagoService;
    @Autowired
    PagoRepository pagoRepository;

    @Autowired
    NutricionalService nutricionalService;
    @Autowired
    NutricionalRepository nutricionalRepository;

    @Autowired
    AcopioService acopioService;
    @Autowired
    AcopioRepository acopioRepository;



    @Test
    void testObtenerPagos() {
        // Arrange
        PagoEntity pago1 = new PagoEntity();

        PagoEntity pago2 = new PagoEntity();
        // Guardar los pagos en la base de datos utilizando el repositorio
        pagoRepository.saveAll(Arrays.asList(pago1, pago2));

        // Act
        List<PagoEntity> pagosObtenidos = pagoService.obtenerPagos();
        // Assert
        assertEquals(2, pagosObtenidos.size());
    }
    @Test
    public void testPagoporleche1() {
        double total_kls = 20.0;
        String categoria = "A";
        double expected = 14000.0;
        double result = pagoService.pagoporleche(total_kls, categoria);
        assertEquals(expected, result, 0.0);
    }
    @Test
    public void testPagoporleche2() {
        double total_kls = 20.0;
        String categoria = "B";
        double expected = 11000.0;
        double result = pagoService.pagoporleche(total_kls, categoria);
        assertEquals(expected, result, 0.0);
    }
    @Test
    public void testPagoporleche3() {
        double total_kls = 20.0;
        String categoria = "C";
        double expected = 8000.0;
        double result = pagoService.pagoporleche(total_kls, categoria);
        assertEquals(expected, result, 0.0);
    }
    @Test
    public void testPagoporleche4() {
        double total_kls = 20.0;
        String categoria = "D";
        double expected = 5000.0;
        double result = pagoService.pagoporleche(total_kls, categoria);
        assertEquals(expected, result, 0.0);
    }
    @Test
    public void testPagoporleche5() {
        double total_kls = 20.0;
        String categoria = "E";
        double expected = 0.0;
        double result = pagoService.pagoporleche(total_kls, categoria);
        assertEquals(expected, result, 0.0);
    }


    @Test
    public void testPagoporgrasa1() {
        double total_kls = 10.0;
        double grasa = 15.0;
        double expected = 300.0;
        double result = pagoService.pagoporgrasa(total_kls, grasa);
        assertEquals(expected, result, 0.0);
    }
    @Test
    public void testPagoporgrasa2() {
        double total_kls = 10.0;
        double grasa = 25.0;
        double expected = 800.0;
        double result = pagoService.pagoporgrasa(total_kls, grasa);
        assertEquals(expected, result, 0.0);
    }

    @Test
    public void testPagoporgrasa3() {
        double total_kls = 10.0;
        double grasa = 46.0;
        double expected = 1200.0;
        double result = pagoService.pagoporgrasa(total_kls, grasa);
        assertEquals(expected, result, 0.0);
    }

    @Test
    public void testPagoporsolido1() {
        double total_kls = 5.0;
        double solido = 5.0;
        double expected = -650.0;
        double result = pagoService.pagoporsolido(total_kls, solido);
        assertEquals(expected, result, 0.0);
    }

    @Test
    public void testPagoporsolido2() {
        double total_kls = 5.0;
        double solido = 9.0;
        double expected = -450.0;
        double result = pagoService.pagoporsolido(total_kls, solido);
        assertEquals(expected, result, 0.0);
    }

    @Test
    public void testPagoporsolido3() {
        double total_kls = 5.0;
        double solido = 20.0;
        double expected = 475.0;
        double result = pagoService.pagoporsolido(total_kls, solido);
        assertEquals(expected, result, 0.0);
    }

    @Test
    public void testPagoporsolido4() {
        double total_kls = 5.0;
        double solido = 50.0;
        double expected = 750.0;
        double result = pagoService.pagoporsolido(total_kls, solido);
        assertEquals(expected, result, 0.0);
    }
    @Test
    public void testRetencion(){
        double monto= 960000.0;
        double result = pagoService.retencion(monto);
        double expected = 124800;
        assertEquals(expected, result, 0.0);

    }   @Test
    public void testRetencion2(){
        double monto= 940000.0;
        double result = pagoService.retencion(monto);
        double expected = 0;
        assertEquals(expected, result, 0.0);

    }

    @Test
    public void testBonoFrecuencia1(){

        proveedorRepository.deleteAll();
        pagoRepository.deleteAll();
        nutricionalRepository.deleteAll();
        acopioRepository.deleteAll();
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setCategoria("A");
        proveedor.setCodigo("1003");
        proveedor.setRetencion("Si");
        proveedor.setNombre("ejemplo1");
        proveedorRepository.save(proveedor);
        AcopioEntity acopio =new AcopioEntity();
        acopio.setTurno("M");
        acopio.setProveedor("1003");
        acopio.setFecha(LocalDate.now().toString());
        acopio.setKls_leche("100");
        acopioRepository.save(acopio);
        double pago = 0.0;
        double result = pagoService.bonoFrecuencia(proveedor,pago);
        double expected = 0;
        assertEquals(expected, result, 0.0);
    }

    @Test
    public void testBonoFrecuencia2() {
        proveedorRepository.deleteAll();
        pagoRepository.deleteAll();
        acopioRepository.deleteAll();
        nutricionalRepository.deleteAll();

        // Arrange
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setCategoria("A");
        proveedor.setCodigo("1003");
        proveedor.setRetencion("Si");
        proveedor.setNombre("ejemplo1");
        proveedorRepository.save(proveedor);

        List<AcopioEntity> acopios = new ArrayList<>();
        LocalDate fecha = LocalDate.of(2023, 2, 10);
        for (int i = 0; i < 11; i++) {
            AcopioEntity acopio = new AcopioEntity();
            acopio.setTurno("M");
            acopio.setProveedor("1003");
            acopio.setFecha(fecha.toString());
            acopio.setKls_leche("100.0");
            acopios.add(acopio);
            acopioRepository.save(acopio);
            fecha = fecha.plusDays(1); // Agrega un día a la fecha
        }

        double result = pagoService.bonoFrecuencia(proveedor, 100.0);
        double expected = 12.0;
        assertEquals(expected, result, 0.0);
    }

    @Test
    public void testBonoFrecuencia3() {
        proveedorRepository.deleteAll();
        pagoRepository.deleteAll();
        acopioRepository.deleteAll();
        nutricionalRepository.deleteAll();

        // Arrange
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setCategoria("A");
        proveedor.setCodigo("1003");
        proveedor.setRetencion("Si");
        proveedor.setNombre("ejemplo1");
        proveedorRepository.save(proveedor);

        List<AcopioEntity> acopios = new ArrayList<>();
        LocalDate fecha = LocalDate.of(2023, 2, 10);
        for (int i = 0; i < 11; i++) {
            AcopioEntity acopio = new AcopioEntity();
            acopio.setTurno("t");
            acopio.setProveedor("1003");
            acopio.setFecha(fecha.toString());
            acopio.setKls_leche("100.0");
            acopios.add(acopio);
            acopioRepository.save(acopio);
            fecha = fecha.plusDays(1); // Agrega un día a la fecha
        }

        double result = pagoService.bonoFrecuencia(proveedor, 100.0);
        double expected = 8.0;
        assertEquals(expected, result, 0.0);
    }
    @Test
    public void testBonoFrecuencia4() {
        proveedorRepository.deleteAll();
        pagoRepository.deleteAll();
        acopioRepository.deleteAll();
        nutricionalRepository.deleteAll();

        // Arrange
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setCategoria("A");
        proveedor.setCodigo("1003");
        proveedor.setRetencion("Si");
        proveedor.setNombre("ejemplo1");
        proveedorRepository.save(proveedor);

        List<AcopioEntity> acopios = new ArrayList<>();
        LocalDate fecha = LocalDate.of(2023, 2, 10);
        for (int i = 0; i < 11; i++) {
            AcopioEntity acopio = new AcopioEntity();
            acopio.setTurno("T");
            acopio.setProveedor("1003");
            acopio.setFecha(fecha.toString());
            acopio.setKls_leche("100.0");
            acopios.add(acopio);
            acopioRepository.save(acopio);
            fecha = fecha.plusDays(1); // Agrega un día a la fecha
        }
        for (int i = 0; i < 11; i++) {
            AcopioEntity acopio = new AcopioEntity();
            acopio.setTurno("M");
            acopio.setProveedor("1003");
            acopio.setFecha(fecha.toString());
            acopio.setKls_leche("100.0");
            acopios.add(acopio);
            acopioRepository.save(acopio);
            fecha = fecha.plusDays(1); // Agrega un día a la fecha
        }

        double result = pagoService.bonoFrecuencia(proveedor, 100.0);
        double expected = 20.0;
        assertEquals(expected, result, 0.0);
    }

    @Test
    public void testVariacionNegativaLeche1(){
        double result = pagoService.variacionNegativaLeche(13.0,50000.0);
        double expected = 3500;
        assertEquals(expected, result, 0.1);
    }
    @Test
    public void testVariacionNegativaLeche2(){
        double result = pagoService.variacionNegativaLeche(27.0,50000.0);
        double expected = 7500;
        assertEquals(expected, result, 0.1);
    }
    @Test
    public void testVariacionNegativaLeche3(){
        double result = pagoService.variacionNegativaLeche(46.0,50000.0);
        double expected = 15000;
        assertEquals(expected, result, 0.1);
    }



    @Test
    public void testVariacionNegativaGrasa1(){
        double result = pagoService.variacionNegativaGrasa(1.0,50000.0);
        double expected = 0;
        assertEquals(expected, result, 0.1);
    }

    @Test
    public void testVariacionNegativaGrasa2(){
        double result = pagoService.variacionNegativaGrasa(17.0,50000.0);
        double expected = 6000;
        assertEquals(expected, result, 0.1);
    }
    @Test
    public void testVariacionNegativaGrasa3(){
        double result = pagoService.variacionNegativaGrasa(38.0,50000.0);
        double expected = 10000;
        assertEquals(expected, result, 0.1);
    }
    @Test
    public void testVariacionNegativaGrasa4(){
        double result = pagoService.variacionNegativaGrasa(42.0,50000.0);
        double expected = 15000;
        assertEquals(expected, result, 0.1);
    }

    @Test
    public void testVariacionNegativaS1T(){
        double result = pagoService.variacionNegativaST(5.0,50000.0);
        double expected = 0;
        assertEquals(expected, result, 0.0);
    }
    @Test
    public void testVariacionNegativaST2(){
        double result = pagoService.variacionNegativaST(8.0,50000.0);
        double expected = 9000;
        assertEquals(expected, result, 0.1);
    }
    @Test
    public void testVariacionNegativaST3(){
        double result = pagoService.variacionNegativaST(14.0,50000.0);
        double expected = 13500;
        assertEquals(expected, result, 0.1);
    }
    @Test
    public void testVariacionNegativaST4(){
        double result = pagoService.variacionNegativaST(36.0,50000.0);
        double expected = 22500;
        assertEquals(expected, result, 0.1);
    }

    @Test
    public void  testCantDiasEnviados(){

        acopioRepository.deleteAll();
        proveedorRepository.deleteAll();

        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setCategoria("A");
        proveedor.setCodigo("1003");
        proveedor.setRetencion("Si");
        proveedor.setNombre("ejemplo1");
        proveedorRepository.save(proveedor);


        AcopioEntity acopio= new AcopioEntity();
        acopio.setFecha("19/04/2022");
        acopio.setKls_leche("12");
        acopio.setTurno("M");
        acopio.setProveedor("1003");
        acopioRepository.save(acopio);

        double result = pagoService.cantDiasEnviados(proveedor);
        double expected = 1;
        assertEquals(expected, result, 0.0);

    }
    @Test
    public void testVariacionLeche(){
        acopioRepository.deleteAll();
        proveedorRepository.deleteAll();
        pagoRepository.deleteAll();
        nutricionalRepository.deleteAll();

        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setCategoria("A");
        proveedor.setCodigo("1003");
        proveedor.setRetencion("Si");
        proveedor.setNombre("ejemplo1");
        proveedorRepository.save(proveedor);

        PagoEntity pago1= new PagoEntity();
        pago1.setTotal_kls_leche(10.0);
        pago1.setCodigo_proveedor("1003");
        pagoRepository.save(pago1);

        PagoEntity pago2= new PagoEntity();
        pago2.setTotal_kls_leche(10.0);
        pago2.setCodigo_proveedor("1003");
        pagoRepository.save(pago2);

        double result = pagoService.variacionLeche(proveedor);
        double expected =0;
        assertEquals(expected, result, 0.0);

    }
    @Test
    public void testVariacionGrasa(){
        acopioRepository.deleteAll();
        proveedorRepository.deleteAll();
        pagoRepository.deleteAll();
        nutricionalRepository.deleteAll();
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setCategoria("A");
        proveedor.setCodigo("1003");
        proveedor.setRetencion("Si");
        proveedor.setNombre("ejemplo1");
        proveedorRepository.save(proveedor);

        PagoEntity pago1= new PagoEntity();
        pago1.setGrasa(10.0);
        pago1.setCodigo_proveedor("1003");
        pagoRepository.save(pago1);

        PagoEntity pago2= new PagoEntity();
        pago2.setGrasa(10.0);
        pago2.setCodigo_proveedor("1003");
        pagoRepository.save(pago2);
        double result = pagoService.variacionGrasa(proveedor);
        double expected =0;
        assertEquals(expected, result, 0.0);
    }

    @Test
    public void testVariacionST(){

        acopioRepository.deleteAll();
        proveedorRepository.deleteAll();
        pagoRepository.deleteAll();
        nutricionalRepository.deleteAll();

        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setCategoria("A");
        proveedor.setCodigo("1003");
        proveedor.setRetencion("Si");
        proveedor.setNombre("ejemplo1");
        proveedorRepository.save(proveedor);

        PagoEntity pago1= new PagoEntity();
        pago1.setSolidos_totales(10.0);
        pago1.setCodigo_proveedor("1003");
        pagoRepository.save(pago1);

        PagoEntity pago2= new PagoEntity();
        pago2.setSolidos_totales(10.0);
        pago2.setCodigo_proveedor("1003");
        pagoRepository.save(pago2);
        double result = pagoService.variacionST(proveedor);
        double expected =0;
        assertEquals(expected, result, 0.0);
    }



}
