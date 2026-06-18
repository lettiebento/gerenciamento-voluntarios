package br.edu.extensao.voluntarios.controller;

import br.edu.extensao.voluntarios.dto.AtividadeForm;
import br.edu.extensao.voluntarios.model.StatusAtividade;
import br.edu.extensao.voluntarios.service.AtividadeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/coordenador/atividades")
public class AtividadeController {
    private final AtividadeService service;

    public AtividadeController(AtividadeService service) { this.service = service; }

    @ModelAttribute("statusDisponiveis")
    public StatusAtividade[] statusDisponiveis() { return StatusAtividade.values(); }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("atividades", service.listarTodas());
        return "coordenador/atividades/lista";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        AtividadeForm form = new AtividadeForm();
        form.setStatus(StatusAtividade.PLANEJADA);
        model.addAttribute("form", form); model.addAttribute("edicao", false);
        return "coordenador/atividades/form";
    }

    @PostMapping
    public String criar(@Valid @ModelAttribute("form") AtividadeForm form,
                        BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("edicao", false);
            return "coordenador/atividades/form";
        }
        service.criar(form);
        redirect.addFlashAttribute("sucesso", "Atividade cadastrada com sucesso.");
        return "redirect:/coordenador/atividades";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("form", AtividadeForm.from(service.buscar(id)));
        model.addAttribute("edicao", true); model.addAttribute("id", id);
        return "coordenador/atividades/form";
    }

    @PostMapping("/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute("form") AtividadeForm form,
                            BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("edicao", true); model.addAttribute("id", id);
            return "coordenador/atividades/form";
        }
        service.atualizar(id, form);
        redirect.addFlashAttribute("sucesso", "Atividade atualizada com sucesso.");
        return "redirect:/coordenador/atividades";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes redirect) {
        service.excluir(id);
        redirect.addFlashAttribute("sucesso", "Atividade excluída.");
        return "redirect:/coordenador/atividades";
    }
}
