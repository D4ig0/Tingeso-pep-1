package tingeso_pep_1.tingeso_pep_1;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tingeso_pep_1.tingeso_pep_1.entities.AcopioEntity;
import tingeso_pep_1.tingeso_pep_1.entities.NutricionalEntity;
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
    public void testPagoporleche() {
        double total_kls = 20.0;
        String categoria = "A";
        double expected = 14000.0;
        double result = pagoService.pagoporleche(total_kls, categoria);
        assertEquals(expected, result, 0.0);
    }

    @Test
    public void testPagoporgrasa() {
        double total_kls = 10.0;
        double grasa = 15.0;
        double expected = 300.0;
        double result = pagoService.pagoporgrasa(total_kls, grasa);
        assertEquals(expected, result, 0.0);
    }

    @Test
    public void testPagoporsolido() {
        double total_kls = 5.0;
        double solido = 5.0;
        double expected = -650.0;
        double result = pagoService.pagoporsolido(total_kls, solido);
        assertEquals(expected, result, 0.0);
    }
    @Test
    public void testRetencion(){
        double monto= 960000.0;
        double result = pagoService.retencion(monto);
        double expected = 124800;
        assertEquals(expected, result, 0.0);

    }

    @Test
    public void testBonoFrecuencia(){

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
    public void testVariacionNegativaLeche(){
        double result = pagoService.variacionNegativaLeche(13.0,50000.0);
        double expected = 350000;
        assertEquals(expected, result, 0.0);
    }

    @Test
    public void testVariacionNegativaGrasa(){
        double result = pagoService.variacionNegativaGrasa(42.0,50000.0);
        double expected = 1500000;
        assertEquals(expected, result, 0.0);
    }

    @Test
    public void testVariacionNegativaST(){
        double result = pagoService.variacionNegativaST(40.0,50000.0);
        double expected = 2250000;
        assertEquals(expected, result, 0.0);
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
