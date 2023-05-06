package tingeso_pep_1.tingeso_pep_1;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tingeso_pep_1.tingeso_pep_1.entities.PagoEntity;
import tingeso_pep_1.tingeso_pep_1.entities.ProveedorEntity;
import tingeso_pep_1.tingeso_pep_1.repositories.ProveedorRepository;
import tingeso_pep_1.tingeso_pep_1.services.ProveedorService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProveedorTest {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProveedorService proveedorService;

    @Test
    void testGuardarProveedor() {
        proveedorRepository.deleteAll();
        String codigo = "001";
        String nombre = "Proveedor A";
        String categoria = "Categoria 1";
        String retencion = "10%";
        proveedorService.guardarProveedor(codigo, nombre, categoria, retencion);
        ProveedorEntity proveedorGuardado = proveedorRepository.findByCodigo(codigo);
        assertEquals(codigo, proveedorGuardado.getCodigo());
        assertEquals(nombre, proveedorGuardado.getNombre());
        assertEquals(categoria, proveedorGuardado.getCategoria());
        assertEquals(retencion, proveedorGuardado.getRetencion());
    }
    @Test
    void testobtenerProveedores(){

        ProveedorEntity proveedor1 = new ProveedorEntity();
        String codigo = "001";
        String nombre = "Proveedor A";
        String categoria = "B";
        String retencion = "Si";
        proveedor1.setId_proveedor(1);
        proveedor1.setCodigo(codigo);
        proveedor1.setNombre(nombre);
        proveedor1.setCategoria(categoria);
        proveedor1.setRetencion(retencion);
        proveedorService.guardarProveedor(codigo, nombre, categoria, retencion);
        ProveedorEntity proveedor2 = new ProveedorEntity();
        String codigo2 = "002";
        String nombre2 = "Proveedor N";
        String categoria2 = "A";
        String retencion2 = "Si";
        proveedor2.setId_proveedor(2);
        proveedor2.setCodigo(codigo2);
        proveedor2.setNombre(nombre2);
        proveedor2.setCategoria(categoria2);
        proveedor2.setRetencion(retencion2);
        proveedorService.guardarProveedor(codigo2, nombre2, categoria2, retencion2);

        List<ProveedorEntity> expected = new ArrayList<>() ;
        expected.add(proveedor1);
        expected.add(proveedor2);
        List<ProveedorEntity> result = proveedorService.obtenerProveedores();
        assertEquals(expected, result);

    }

}