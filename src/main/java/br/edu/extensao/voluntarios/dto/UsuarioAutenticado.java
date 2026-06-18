package br.edu.extensao.voluntarios.dto;

import br.edu.extensao.voluntarios.model.Perfil;

public record UsuarioAutenticado(Long id, String nome, Perfil perfil) {}
