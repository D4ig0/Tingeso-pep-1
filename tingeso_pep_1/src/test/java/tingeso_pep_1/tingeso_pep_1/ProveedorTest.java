package tingeso_pep_1.tingeso_pep_1;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tingeso_pep_1.tingeso_pep_1.entities.ProveedorEntity;
import tingeso_pep_1.tingeso_pep_1.repositories.ProveedorRepository;
import tingeso_pep_1.tingeso_pep_1.services.ProveedorService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProveedorTest {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProveedorService proveedorService;

    @Test
    void GuardarProveedorTest() {
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

}