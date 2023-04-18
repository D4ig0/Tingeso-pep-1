package tingeso_pep_1.tingeso_pep_1.services;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.support.PropertiesBasedNamedQueries;
import org.springframework.stereotype.Service;
import tingeso_pep_1.tingeso_pep_1.controllers.AcopioController;

import tingeso_pep_1.tingeso_pep_1.entities.*;
import tingeso_pep_1.tingeso_pep_1.repositories.*;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Service
public class PagoService {
    private static final Integer categoria_a = 700;
    private static final Integer categoria_b = 550;
    private static final Integer categoria_c = 400;
    private static final Integer categoria_d = 250;
    private static final Integer bonificacion_MT = 20;
    private static final Integer bonificacion_M = 12;
    private static final Integer bonificacion_T = 8;


    @Autowired
    PagoService pagoService;
    @Autowired
    AcopioService acopioService;
    @Autowired
    NutricionalService  nutricionalService;
    @Autowired
    NutricionalRepository  nutricionalRepository;
    @Autowired
    AcopioRepository acopioRepository;
    @Autowired
    PagoRepository pagoRepository;
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
        else return 120;}

    public Integer pagoporsolido (Integer solido)
    {
        if (0 <= solido && solido<=7)
        {return  -130;}
        else if (8 <= solido && solido<=18)
        {return -90;}
        else if (19 <= solido && solido<=35)
        {return  95;}
        else return  150;}


    public Integer bonoFrecuencia(ProveedorEntity proveedor){
        Integer turno_m = acopioRepository.cantidadTurnoM(proveedor.getId_proveedor());
        Integer turno_t = acopioRepository.cantidadTurnoT(proveedor.getId_proveedor());
        if (turno_t>10 && turno_t >10){
            return bonificacion_MT;}
        else if (turno_m>10){
            return bonificacion_M;}
        else if (turno_t>10) {
            return bonificacion_T;}
        else  return 0 ;}


    //
    public double retencion(Double monto){
        double retenido=0;
        if (monto>950000)
        {retenido = monto*0.13;
        return  (monto-retenido);}
        else return monto;
    }

    public Double variacionNegativaLeche(ProveedorEntity proveedor)
    {
        PagoEntity actual= pagoRepository.obtenerPagoActual(proveedor.getId_proveedor());
        PagoEntity anterior= pagoRepository.obtenerPagoAnterior(actual.getId_pago());
        Double variacion_leche= (anterior.getTotal_kls_leche()/ actual.getTotal_kls_leche())*100;
        return  variacion_leche;
    }

    public Double variacionNegativaGrasa(ProveedorEntity proveedor)
    {
        PagoEntity actual= pagoRepository.obtenerPagoActual(proveedor.getId_proveedor());
        PagoEntity anterior= pagoRepository.obtenerPagoAnterior(actual.getId_pago());
        Double variacion_grasa= (anterior.getGrasa()/ actual.getGrasa())*100;
        return  variacion_grasa;
    }

    public Double variacionNegativaST(ProveedorEntity proveedor)
    {
        PagoEntity actual= pagoRepository.obtenerPagoActual(proveedor.getId_proveedor());
        PagoEntity anterior= pagoRepository.obtenerPagoAnterior(actual.getId_pago());
        Double variacion_st= (anterior.getSolidos_totales()/ actual.getSolidos_totales())*100;
        return  variacion_st;
    }

    public void pagototal()
    {   List<ProveedorEntity> proveedores= proveedorService.obtenerProveedores();
        for  (int i = 0; i<proveedores.size(); i++) {
        ProveedorEntity proveedor = proveedores.get(i);}}

    public  PagoEntity iniciarpago(ProveedorEntity proveedor) {

        PagoEntity pago = new PagoEntity();
        pago.setGrasa(nutricionalRepository.obtenerGrasa(proveedor.getId_proveedor()));
        pago.setSolidos_totales(nutricionalRepository.obtenerSolidos(proveedor.getId_proveedor()));
        pago.setCodigo_proveedor(proveedor.getCodigo());
        pago.setNombre_proveedor(proveedor.getNombre());
        pago.setId_proveedor(proveedor.getId_proveedor());
        pago.setQuincena(new Date());
        //pago.setTotal_kls_leche();query que cuente todos los kilos de leche de un proveedor
        return  pago;
    }
}