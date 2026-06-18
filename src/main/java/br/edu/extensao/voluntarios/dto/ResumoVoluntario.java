package br.edu.extensao.voluntarios.dto;

import java.math.BigDecimal;

public record ResumoVoluntario(
        Long id,
        String nome,
        String matricula,
        String curso,
        BigDecimal horas,
        int percentual,
        boolean aptoCertificado
) {}
