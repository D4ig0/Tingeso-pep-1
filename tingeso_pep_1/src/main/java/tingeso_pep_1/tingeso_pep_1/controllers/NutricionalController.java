package tingeso_pep_1.tingeso_pep_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tingeso_pep_1.tingeso_pep_1.services.AcopioService;
import tingeso_pep_1.tingeso_pep_1.services.NutricionalService;


@Controller
public class NutricionalController {

        @Autowired
        private NutricionalService nutricionalService;

        @GetMapping("/fileUploadNutricional")
        public String main() {
        return "fileUploadNutricional";
    }

        @PostMapping("/fileUploadNutricional")
        public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
            nutricionalService.guardar(file);
            redirectAttributes.addFlashAttribute("mensaje", "¡Archivo cargado correctamente!");
            nutricionalService.leerCsv("Nutricional.csv");
        return "redirect:/fileUploadNutricional";
    }
}
