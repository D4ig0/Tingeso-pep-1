package tingeso_pep_1.tingeso_pep_1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tingeso_pep_1.tingeso_pep_1.entities.*;
import tingeso_pep_1.tingeso_pep_1.repositories.*;

import java.util.Date;
import java.util.List;

@Service
public class PagoService {
    private static final Double categoria_a = 700.0;
    private static final Double categoria_b = 550.0;
    private static final Double categoria_c = 400.0;
    private static final Double categoria_d = 250.0;
    private static final Double bonificacion_MT = 20.0;
    private static final Double bonificacion_M = 12.0;
    private static final Double bonificacion_T = 8.0;




    @Autowired
    AcopioService acopioService;

    @Autowired
    AcopioRepository acopioRepository;

    @Autowired
    NutricionalService  nutricionalService;

    @Autowired
    NutricionalRepository  nutricionalRepository;

    @Autowired
    PagoRepository pagoRepository;

    @Autowired
    ProveedorService proveedorService;




    public List<PagoEntity> obtenerPagos(){ return pagoRepository.findAll(); }

    public double pagoporleche(Double total_kls, String categoria){
        return switch(categoria){
            case "A" -> categoria_a * total_kls;
            case "B" -> categoria_b * total_kls;
            case "C" -> categoria_c * total_kls;
            case "D" -> categoria_d * total_kls;
            default -> 0;
        };
    }

    public Double pagoporgrasa (Double total_kls, Double grasa ){
        if (0 <= grasa && grasa<=20)
        {return 30.0 * total_kls ;}
        else if (21 <= grasa && grasa<=45)
        {return 80.0 * total_kls ;}
        else return 120.0 * total_kls;}

    public Double pagoporsolido (Double total_kls, Double solido)
    {
        if (0 <= solido && solido<=7)
        {return  -130 * total_kls;}
        else if (8 <= solido && solido<=18)
        {return -90 * total_kls;}
        else if (19 <= solido && solido<=35)
        {return  95* total_kls;}
        else return  150 * total_kls;}


    public Double bonoFrecuencia(ProveedorEntity proveedor,Double total_kls){
        Integer turno_m = acopioRepository.cantidadTurnoM(proveedor.getId_proveedor());
        Integer turno_t = acopioRepository.cantidadTurnoT(proveedor.getId_proveedor());
        if (turno_t>10 && turno_t >10){
            return bonificacion_MT * total_kls;}
        else if (turno_m>10){
            return bonificacion_M * total_kls;}
        else if (turno_t>10) {
            return bonificacion_T * total_kls;}
        else  return 0.0 ;}

    public double retencion(Double monto){
        double retenido=0;
        if (monto>950000)
        {retenido = monto*0.13;
        return  (monto-retenido);}
        else return monto;
    }

    public Double variacionNegativaLeche(ProveedorEntity proveedor, Double monto)
    {
        PagoEntity actual= pagoRepository.obtenerPagoActual(proveedor.getId_proveedor());
        PagoEntity anterior= pagoRepository.obtenerPagoAnterior(actual.getId_pago());
        Double variacion_leche= (anterior.getTotal_kls_leche()/ actual.getTotal_kls_leche())*100;
        if (variacion_leche<= 0  && variacion_leche<=8)
        {return 0.0*monto;}
        else if (variacion_leche<= 9  && variacion_leche<=25)
        {return 7.0*monto;}
        else if (variacion_leche<= 26  && variacion_leche<=45)
        {return 15.0*monto;}
        else if (variacion_leche<= 45 )
        {return 30.0*monto;}

        else return 0.0*monto;
    }
    public Double variacionNegativaGrasa(ProveedorEntity proveedor, Double monto)
    {
        PagoEntity actual= pagoRepository.obtenerPagoActual(proveedor.getId_proveedor());
        PagoEntity anterior= pagoRepository.obtenerPagoAnterior(actual.getId_pago());
        Double variacion_grasa= (anterior.getGrasa()/ actual.getGrasa())*100;
        if (variacion_grasa<= 0  && variacion_grasa<=15)
        {return 0.0*monto;}
        else if (variacion_grasa<= 16  && variacion_grasa<=25)
        {return 0.0*monto;}
        else if (variacion_grasa<= 26  && variacion_grasa<=40)
        {return 20.0*monto;}
        else if (variacion_grasa<= 41 )
        {return 30.0*monto;}
        else return 0.0*monto;
    }

    public Double variacionNegativaST(ProveedorEntity proveedor, Double monto)
    {
        PagoEntity actual= pagoRepository.obtenerPagoActual(proveedor.getId_proveedor());
        PagoEntity anterior= pagoRepository.obtenerPagoAnterior(actual.getId_pago());
        Double variacion_st= (anterior.getSolidos_totales()/ actual.getSolidos_totales())*100;
        if (variacion_st<= 0  && variacion_st<=6){
            return 0.0*monto;}
        else if (variacion_st<= 7  && variacion_st<=12)
        {return 18.0*monto;}
        else if (variacion_st<= 13  && variacion_st<=35)
        {return 27.0*monto;}
        else if (variacion_st<= 36)
        {return 45.0*monto;}
        else return 0.0*monto;}

    public boolean pagototal()
    {   List<ProveedorEntity> proveedores= proveedorService.obtenerProveedores();
        for  (int i = 0; i<proveedores.size(); i++){
            ProveedorEntity proveedor = proveedores.get(i);
            PagoEntity pago = iniciarpago(proveedor);
            pago.setPago_leche(pagoporleche(pago.getTotal_kls_leche(),proveedor.getCategoria()));
            pago.setPago_grasa(pagoporgrasa(pago.getTotal_kls_leche(),pago.getGrasa()));
            pago.setPago_solido(pagoporsolido(pago.getTotal_kls_leche(),pago.getSolidos_totales()));
            pago.setFrecuencia(bonoFrecuencia(proveedor,pago.getTotal_kls_leche()));
            pago.setDcto_variacion_grasa(variacionNegativaGrasa(proveedor,pago.getPago_leche()));
            pago.setDcto_variacion_leche(variacionNegativaLeche(proveedor,pago.getPago_leche()));
            pago.setDcto_variacion_solidos_totales(variacionNegativaST(proveedor,pago.getPago_leche()));
            Double pago_acopio_leche = pago.getPago_leche() + pago.getPago_grasa() + pago.getPago_solido()+pago.getFrecuencia();
            Double descuentos = pago.getDcto_variacion_grasa()+pago.getDcto_variacion_leche()+pago.getDcto_variacion_solidos_totales();
            pago.setPago_total(pago_acopio_leche-descuentos);
            pago.setMonto_final(retencion(pago_acopio_leche));}
            return  true;}
    public  PagoEntity iniciarpago(ProveedorEntity proveedor) {

        PagoEntity pago = new PagoEntity();
        pago.setGrasa(nutricionalRepository.obtenerGrasa(proveedor.getId_proveedor()));
        pago.setSolidos_totales(nutricionalRepository.obtenerSolidos(proveedor.getId_proveedor()));
        pago.setCodigo_proveedor(proveedor.getCodigo());
        pago.setNombre_proveedor(proveedor.getNombre());
        pago.setId_proveedor(proveedor.getId_proveedor());
        pago.setQuincena(new Date());
        pago.setTotal_kls_leche(acopioRepository.totalLecheProveedor(proveedor.getId_proveedor()));
        return  pago;
    }

}