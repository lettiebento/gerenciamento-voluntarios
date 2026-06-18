package br.edu.extensao.voluntarios.dto;

import br.edu.extensao.voluntarios.model.Voluntario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class VoluntarioForm {
    @NotBlank(message = "Informe o nome.")
    @Size(max = 120)
    private String nome;

    @NotBlank(message = "Informe o e-mail.")
    @Email(message = "Informe um e-mail válido.")
    @Size(max = 150)
    private String email;

    @NotBlank(message = "Informe a matrícula.")
    @Size(max = 30)
    private String matricula;

    @NotBlank(message = "Informe o curso.")
    @Size(max = 120)
    private String curso;

    @Size(max = 30)
    private String telefone;

    private boolean ativo = true;

    @Size(max = 100, message = "A senha deve ter no máximo 100 caracteres.")
    private String senha;

    public static VoluntarioForm from(Voluntario voluntario) {
        VoluntarioForm form = new VoluntarioForm();
        form.setNome(voluntario.getNome());
        form.setEmail(voluntario.getEmail());
        form.setMatricula(voluntario.getMatricula());
        form.setCurso(voluntario.getCurso());
        form.setTelefone(voluntario.getTelefone());
        form.setAtivo(voluntario.isAtivo());
        return form;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
