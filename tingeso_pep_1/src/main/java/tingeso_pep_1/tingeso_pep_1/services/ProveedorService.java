package tingeso_pep_1.tingeso_pep_1.services;

import tingeso_pep_1.tingeso_pep_1.entities.ProveedorEntity;
import tingeso_pep_1.tingeso_pep_1.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProveedorService {

    @Autowired
    ProveedorRepository proveedorRepository;

    public void guardarProveedor(String codigo, String nombre, String categoria, String retencion){
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setCodigo(codigo);
        proveedor.setNombre(nombre);
        proveedor.setCategoria(categoria);
        proveedor.setRetencion(retencion);
        proveedorRepository.save(proveedor);
    }
    public ArrayList<ProveedorEntity> obtenerProveedores(){
        return (ArrayList<ProveedorEntity>) proveedorRepository.findAll();}

    public String obtenerCategoria(String codigo){
        return proveedorRepository.findCategory(codigo);
    }

    public ProveedorEntity findByCodigo(String codigo){
        String gato = proveedorRepository.findById(3);
        return proveedorRepository.findByCodigo(codigo);
    }


}

