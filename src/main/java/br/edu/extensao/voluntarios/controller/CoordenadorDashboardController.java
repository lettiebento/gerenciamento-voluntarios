package br.edu.extensao.voluntarios.controller;

import br.edu.extensao.voluntarios.service.RelatorioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoordenadorDashboardController {
    private final RelatorioService relatorioService;

    public CoordenadorDashboardController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/coordenador")
    public String dashboard(Model model) {
        model.addAttribute("totalVoluntarios", relatorioService.totalVoluntariosAtivos());
        model.addAttribute("totalAtividades", relatorioService.totalAtividades());
        model.addAttribute("totalConcluidas", relatorioService.totalAtividadesConcluidas());
        model.addAttribute("totalHoras", relatorioService.totalHorasProjeto());
        model.addAttribute("resumos", relatorioService.resumoPorVoluntario().stream().limit(5).toList());
        return "coordenador/dashboard";
    }
}
