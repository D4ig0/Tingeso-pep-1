package tingeso_pep_1.tingeso_pep_1.services;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso_pep_1.tingeso_pep_1.controllers.AcopioController;
import tingeso_pep_1.tingeso_pep_1.entities.*;
import tingeso_pep_1.tingeso_pep_1.repositories.*;

import java.util.List;

@Service
public class PagoService {
    private static final Integer categoria_a = 700;
    private static final Integer categoria_b = 550;
    private static final Integer categoria_c = 400;
    private static final Integer categoria_d = 250;

    @Autowired
    AcopioService acopioService;
    @Autowired
    NutricionalService  nutricionalService;
    @Autowired
    PagoService pagoService;
    @Autowired
    ProveedorService proveedorService;


    public Integer pagoporleche(String categoria){
        return switch(categoria){
            case "A" -> categoria_a;
            case "B" -> categoria_b;
            case "C" -> categoria_c;
            case "D" -> categoria_d;
            default -> 0;
        };
    }

    public Integer pagoporgrasa (Integer grasa ){
        if (0 <= grasa && grasa<=20)
        {return 30 ;}
        else if (21 <= grasa && grasa<=45)
        {return 80 ;}
        else
            return 120;}
    public Integer pagoporsolido (Integer solido)
    {
        if (0 <= solido && solido<=7)
        {return  -130;}
        else if (8 <= solido && solido<=18)
        {return -90;}
        else if (19 <= solido && solido<=35)
        {return  95;}
        else
        {return  150;}}



    public void pagototal()

    {   List<ProveedorEntity> proveedores= proveedorService.obtenerProveedores();
        for  (int i = 0; i<proveedores.size(); i++){
        ProveedorEntity proveedor = proveedores.get(i);



        }}

    public  void inicializar_pago(ProveedorEntity proveedor){


    }
}