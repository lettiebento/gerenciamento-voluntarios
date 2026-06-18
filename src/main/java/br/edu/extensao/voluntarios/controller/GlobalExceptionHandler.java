package br.edu.extensao.voluntarios.controller;

import br.edu.extensao.voluntarios.exception.RegraNegocioException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RegraNegocioException.class)
    public String regraNegocio(RegraNegocioException e, Model model) {
        model.addAttribute("mensagem", e.getMessage());
        return "erro/erro";
    }

    @ExceptionHandler(Exception.class)
    public String erroGeral(Exception e, Model model) {
        model.addAttribute("mensagem", "Ocorreu um erro inesperado. Verifique os dados e tente novamente.");
        return "erro/erro";
    }
}
