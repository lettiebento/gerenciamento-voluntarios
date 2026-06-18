package br.edu.extensao.voluntarios.controller;

import br.edu.extensao.voluntarios.dto.LoginForm;
import br.edu.extensao.voluntarios.model.Perfil;
import br.edu.extensao.voluntarios.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping({"/", "/login"})
    public String login(Model model, HttpSession session) {
        if (session.getAttribute("usuarioPerfil") != null) return redirecionar(session);
        if (!model.containsAttribute("loginForm")) model.addAttribute("loginForm", new LoginForm());
        return "auth/login";
    }

    @PostMapping("/login")
    public String autenticar(@Valid @ModelAttribute LoginForm loginForm,
                             BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) return "auth/login";
        var usuario = authService.autenticar(loginForm.getEmail(), loginForm.getSenha());
        if (usuario.isEmpty()) {
            model.addAttribute("erro", "E-mail ou senha inválidos, ou usuário inativo.");
            return "auth/login";
        }

        session.setAttribute("usuarioId", usuario.get().id());
        session.setAttribute("usuarioNome", usuario.get().nome());
        session.setAttribute("usuarioPerfil", usuario.get().perfil().name());
        return usuario.get().perfil() == Perfil.COORDENADOR
                ? "redirect:/coordenador" : "redirect:/voluntario";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }

    private String redirecionar(HttpSession session) {
        return Perfil.COORDENADOR.name().equals(session.getAttribute("usuarioPerfil"))
                ? "redirect:/coordenador" : "redirect:/voluntario";
    }
}
