package br.edu.extensao.voluntarios.controller;

import br.edu.extensao.voluntarios.dto.VoluntarioForm;
import br.edu.extensao.voluntarios.exception.RegraNegocioException;
import br.edu.extensao.voluntarios.service.VoluntarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/coordenador/voluntarios")
public class VoluntarioController {
    private final VoluntarioService service;

    public VoluntarioController(VoluntarioService service) { this.service = service; }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("voluntarios", service.listarTodos());
        return "coordenador/voluntarios/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("form", new VoluntarioForm());
        model.addAttribute("edicao", false);
        return "coordenador/voluntarios/form";
    }

    @PostMapping
    public String criar(@Valid @ModelAttribute("form") VoluntarioForm form,
                        BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("edicao", false);
            return "coordenador/voluntarios/form";
        }
        try {
            service.criar(form);
            redirect.addFlashAttribute("sucesso", "Voluntário cadastrado com sucesso.");
            return "redirect:/coordenador/voluntarios";
        } catch (RegraNegocioException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("edicao", false);
            return "coordenador/voluntarios/form";
        }
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("form", VoluntarioForm.from(service.buscar(id)));
        model.addAttribute("edicao", true);
        model.addAttribute("id", id);
        return "coordenador/voluntarios/form";
    }

    @PostMapping("/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute("form") VoluntarioForm form,
                            BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("edicao", true); model.addAttribute("id", id);
            return "coordenador/voluntarios/form";
        }
        try {
            service.atualizar(id, form);
            redirect.addFlashAttribute("sucesso", "Voluntário atualizado com sucesso.");
            return "redirect:/coordenador/voluntarios";
        } catch (RegraNegocioException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("edicao", true); model.addAttribute("id", id);
            return "coordenador/voluntarios/form";
        }
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes redirect) {
        service.excluir(id);
        redirect.addFlashAttribute("sucesso", "Voluntário excluído.");
        return "redirect:/coordenador/voluntarios";
    }
}
