package br.edu.extensao.voluntarios.controller;

import br.edu.extensao.voluntarios.service.RelatorioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RelatorioController {
    private final RelatorioService service;

    public RelatorioController(RelatorioService service) { this.service = service; }

    @GetMapping("/coordenador/relatorios")
    public String relatorio(Model model) {
        model.addAttribute("resumos", service.resumoPorVoluntario());
        model.addAttribute("horasMinimas", service.getHorasMinimas());
        model.addAttribute("totalHoras", service.totalHorasProjeto());
        model.addAttribute("totalPresencas", service.totalPresencas());
        return "coordenador/relatorios/index";
    }
}
