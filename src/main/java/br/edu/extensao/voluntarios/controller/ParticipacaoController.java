package br.edu.extensao.voluntarios.controller;

import br.edu.extensao.voluntarios.dto.ParticipacaoForm;
import br.edu.extensao.voluntarios.exception.RegraNegocioException;
import br.edu.extensao.voluntarios.service.AtividadeService;
import br.edu.extensao.voluntarios.service.ParticipacaoService;
import br.edu.extensao.voluntarios.service.VoluntarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/coordenador/participacoes")
public class ParticipacaoController {
    private final ParticipacaoService service;
    private final VoluntarioService voluntarioService;
    private final AtividadeService atividadeService;

    public ParticipacaoController(ParticipacaoService service,
                                  VoluntarioService voluntarioService,
                                  AtividadeService atividadeService) {
        this.service = service;
        this.voluntarioService = voluntarioService;
        this.atividadeService = atividadeService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("participacoes", service.listarTodas());
        return "coordenador/participacoes/lista";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        model.addAttribute("form", new ParticipacaoForm());
        preparar(model, false, null);
        return "coordenador/participacoes/form";
    }

    @PostMapping
    public String criar(@Valid @ModelAttribute("form") ParticipacaoForm form,
                        BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            preparar(model, false, null);
            return "coordenador/participacoes/form";
        }
        try {
            service.criar(form);
            redirect.addFlashAttribute("sucesso", "Participação registrada com sucesso.");
            return "redirect:/coordenador/participacoes";
        } catch (RegraNegocioException e) {
            model.addAttribute("erro", e.getMessage());
            preparar(model, false, null);
            return "coordenador/participacoes/form";
        }
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("form", ParticipacaoForm.from(service.buscar(id)));
        preparar(model, true, id);
        return "coordenador/participacoes/form";
    }

    @PostMapping("/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute("form") ParticipacaoForm form,
                            BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            preparar(model, true, id);
            return "coordenador/participacoes/form";
        }
        try {
            service.atualizar(id, form);
            redirect.addFlashAttribute("sucesso", "Participação atualizada com sucesso.");
            return "redirect:/coordenador/participacoes";
        } catch (RegraNegocioException e) {
            model.addAttribute("erro", e.getMessage());
            preparar(model, true, id);
            return "coordenador/participacoes/form";
        }
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes redirect) {
        service.excluir(id);
        redirect.addFlashAttribute("sucesso", "Participação excluída.");
        return "redirect:/coordenador/participacoes";
    }

    private void preparar(Model model, boolean edicao, Long id) {
        model.addAttribute("voluntarios", voluntarioService.listarAtivos());
        model.addAttribute("atividades", atividadeService.listarTodas());
        model.addAttribute("edicao", edicao);
        model.addAttribute("id", id);
    }
}
