package br.edu.extensao.voluntarios.controller;

import br.edu.extensao.voluntarios.service.ParticipacaoService;
import br.edu.extensao.voluntarios.service.RelatorioService;
import br.edu.extensao.voluntarios.service.VoluntarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VoluntarioAreaController {
    private final VoluntarioService voluntarioService;
    private final ParticipacaoService participacaoService;
    private final RelatorioService relatorioService;

    public VoluntarioAreaController(VoluntarioService voluntarioService,
                                    ParticipacaoService participacaoService,
                                    RelatorioService relatorioService) {
        this.voluntarioService = voluntarioService;
        this.participacaoService = participacaoService;
        this.relatorioService = relatorioService;
    }

    @GetMapping("/voluntario")
    public String dashboard(HttpSession session, Model model) {
        Long id = (Long) session.getAttribute("usuarioId");
        var voluntario = voluntarioService.buscar(id);
        var totalHoras = participacaoService.totalHoras(id);
        var horasMinimas = relatorioService.getHorasMinimas();
        int percentual = horasMinimas.signum() == 0 ? 100 : totalHoras
                .multiply(java.math.BigDecimal.valueOf(100))
                .divide(horasMinimas, 0, java.math.RoundingMode.DOWN)
                .min(java.math.BigDecimal.valueOf(100)).intValue();

        model.addAttribute("voluntario", voluntario);
        model.addAttribute("participacoes", participacaoService.listarPorVoluntario(id));
        model.addAttribute("totalHoras", totalHoras);
        model.addAttribute("horasMinimas", horasMinimas);
        model.addAttribute("percentual", percentual);
        model.addAttribute("apto", totalHoras.compareTo(horasMinimas) >= 0);
        return "voluntario/dashboard";
    }
}
