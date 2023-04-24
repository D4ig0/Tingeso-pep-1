package tingeso_pep_1.tingeso_pep_1.controllers;


import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tingeso_pep_1.tingeso_pep_1.entities.PagoEntity;
import tingeso_pep_1.tingeso_pep_1.services.NutricionalService;
import tingeso_pep_1.tingeso_pep_1.services.PagoService;

import java.util.List;

@Controller
@RequestMapping
public class  PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping("/successful_spreadsheet")
    public String realizarCalculos(Model model)

    {   boolean ejecucion = pagoService.pagototal();
        model.addAttribute("ejecucion",ejecucion);
        return "successful_spreadsheet";
    }

    @GetMapping("/view_spreadsheet")
    public String visualizarPagos(Model model) {
        List<PagoEntity> pagos = pagoService.obtenerPagos();
        model.addAttribute("pagos", pagos);
        return "view_spreadsheet"; }




}

