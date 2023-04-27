package tingeso_pep_1.tingeso_pep_1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tingeso_pep_1.tingeso_pep_1.entities.*;
import tingeso_pep_1.tingeso_pep_1.repositories.*;
import java.time.*;
import java.text.SimpleDateFormat;
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
        switch(categoria){
            case "A" : return categoria_a * total_kls;
            case "B" : return categoria_b * total_kls;
            case "C" : return categoria_c * total_kls;
            case "D" : return categoria_d * total_kls;
            default :
                return 0.0;
        }
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
        Integer turno_m = acopioRepository.cantidadTurnoM(proveedor.getCodigo());
        Integer turno_t = acopioRepository.cantidadTurnoT(proveedor.getCodigo());
        if (turno_t>10 && turno_t >10){
            return bonificacion_MT * total_kls;}
        else if (turno_m>10){
            return bonificacion_M * total_kls;}
        else if (turno_t>10) {
            return bonificacion_T * total_kls;}
        else  return 0.0 ;}

    public double retencion(Double monto){

        if (monto>950000)
        {return monto*0.13;}

        else return 0;
    }

    public Double variacionLeche(ProveedorEntity proveedor)
    {
        
        Integer numero= pagoRepository.obtenerCantPagos(proveedor.getCodigo());
        PagoEntity anterior;
        PagoEntity actual;
        if (numero==1){

            actual= pagoRepository.obtenerPagoActual(proveedor.getCodigo());
            return  (0/ actual.getTotal_kls_leche())*100;
        }
        else{
            actual= pagoRepository.obtenerPagoActual(proveedor.getCodigo());
            anterior= pagoRepository.obtenerPagoAnterior(actual.getId_pago());
           return (anterior.getTotal_kls_leche()/ actual.getTotal_kls_leche())*100;}

    }
    public Double variacionNegativaLeche(Double variacion_leche, Double monto)
    {
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


    public Double variacionGrasa(ProveedorEntity proveedor){

        Integer numero= pagoRepository.obtenerCantPagos(proveedor.getCodigo());
        PagoEntity anterior;
        PagoEntity actual;
        
        if (numero==1){

            actual= pagoRepository.obtenerPagoActual(proveedor.getCodigo());
            return  (0/ actual.getGrasa())*100;
        }
        else{
            actual= pagoRepository.obtenerPagoActual(proveedor.getCodigo());
            anterior= pagoRepository.obtenerPagoAnterior(actual.getId_pago());
            return (anterior.getGrasa()/ actual.getGrasa())*100;}
    }
        
        
    public Double variacionNegativaGrasa(Double variacion_grasa, Double monto)
    {
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

    public Double variacionST(ProveedorEntity proveedor){
        
        Integer numero= pagoRepository.obtenerCantPagos(proveedor.getCodigo());
        PagoEntity anterior;
        PagoEntity actual;

        if (numero==1){
            actual= pagoRepository.obtenerPagoActual(proveedor.getCodigo());
            return  (0/ actual.getSolidos_totales())*100;
        }
        else{
            actual= pagoRepository.obtenerPagoActual(proveedor.getCodigo());
            anterior= pagoRepository.obtenerPagoAnterior(actual.getId_pago());
            return (anterior.getSolidos_totales()/ actual.getSolidos_totales())*100;}
    }
    public Double variacionNegativaST(Double variacion_st, Double monto)
    {
        if (variacion_st<= 0  && variacion_st<=6){
            return 0.0*monto;}
        else if (variacion_st<= 7  && variacion_st<=12)
        {return 18.0*monto;}
        else if (variacion_st<= 13  && variacion_st<=35)
        {return 27.0*monto;}
        else if (variacion_st<= 36)
        {return 45.0*monto;}
        else return 0.0*monto;}

    public Integer cantDiasEnviados(ProveedorEntity proveedor){
        return acopioRepository.totalDiasEnviados(proveedor.getCodigo());
    }

    public boolean pagototal()
    {   List<ProveedorEntity> proveedores= proveedorService.obtenerProveedores();
        for  (int i = 0; i<proveedores.size(); i++){
            ProveedorEntity proveedor = proveedores.get(i);
            //VER SI LAS LISTAS DE ACOPIO SON DISTINTAS
            PagoEntity pago = iniciarpago(proveedor);
            pago.setPago_leche(pagoporleche(pago.getTotal_kls_leche(),proveedor.getCategoria()));
            pago.setPago_grasa(pagoporgrasa(pago.getTotal_kls_leche(),pago.getGrasa()));
            pago.setPago_solido(pagoporsolido(pago.getTotal_kls_leche(),pago.getSolidos_totales()));
            pago.setNro_dias_leche(cantDiasEnviados(proveedor));
            pago.setPromedio_diario_leche(pago.getTotal_kls_leche()/pago.getNro_dias_leche());
            pago.setFrecuencia(bonoFrecuencia(proveedor,pago.getTotal_kls_leche()));
            pago.setVariacion_grasa(variacionGrasa(proveedor));
            pago.setDcto_variacion_grasa(variacionNegativaGrasa(pago.getVariacion_grasa(),pago.getPago_leche()));
            pago.setVariacion_leche(variacionLeche(proveedor));
            pago.setDcto_variacion_leche(variacionNegativaLeche(pago.getVariacion_leche(),pago.getPago_leche()));
            pago.setVariacion_solidos_totales(variacionST(proveedor));
            pago.setDcto_variacion_solidos_totales(variacionNegativaST(pago.getVariacion_solidos_totales(),pago.getPago_leche()));
            Double pago_acopio = pago.getPago_leche() + pago.getPago_grasa() + pago.getPago_solido()+pago.getFrecuencia();
            Double descuentos = pago.getDcto_variacion_grasa()+pago.getDcto_variacion_leche()+pago.getDcto_variacion_solidos_totales();
            pago.setPago_total(pago_acopio-descuentos);
            pago.setMonto_retencion(retencion(pago.getPago_total()));
            pago.setMonto_final(pago.getPago_total() -pago.getMonto_retencion());
            guardarData(pago);}
            return  true;}

    public  PagoEntity iniciarpago(ProveedorEntity proveedor) {

        PagoEntity pago = new PagoEntity();
        pago.setGrasa(nutricionalRepository.obtenerGrasa(proveedor.getCodigo()));
        pago.setSolidos_totales(nutricionalRepository.obtenerSolidos(proveedor.getCodigo()));
        pago.setTotal_kls_leche(acopioRepository.totalLecheProveedor(proveedor.getCodigo()));
        pago.setCodigo_proveedor(proveedor.getCodigo());
        pago.setNombre_proveedor(proveedor.getNombre());
        LocalDate fechaActual = LocalDate.now();
        pago.setQuincena(fechaActual) ;
        //COMPARAR ESTE PAGO CON EL ÚLTIMO EN LA BASE DE DATOS SI ES IGUAL, EN CASO DE QUE NO TIRE ERROR O ALGO ASI QUE NOTIFIQUE
        guardarData(pago);
        return  pago;
    }

    public void guardarData(PagoEntity data){
        pagoRepository.save(data);
    }
}

