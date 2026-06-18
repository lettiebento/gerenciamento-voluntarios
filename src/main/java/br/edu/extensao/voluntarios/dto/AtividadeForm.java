package br.edu.extensao.voluntarios.dto;

import br.edu.extensao.voluntarios.model.Atividade;
import br.edu.extensao.voluntarios.model.StatusAtividade;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AtividadeForm {
    @NotBlank(message = "Informe o título.")
    @Size(max = 150)
    private String titulo;

    @NotBlank(message = "Informe a descrição.")
    @Size(max = 1000)
    private String descricao;

    @NotNull(message = "Informe a data.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate data;

    @NotBlank(message = "Informe o local.")
    @Size(max = 150)
    private String local;

    @NotNull(message = "Informe as horas previstas.")
    @DecimalMin(value = "0.5", message = "A carga horária mínima é 0,5 hora.")
    @DecimalMax(value = "999.99", message = "A carga horária máxima é 999,99 horas.")
    private BigDecimal horasPrevistas;

    @NotNull(message = "Selecione o status.")
    private StatusAtividade status;

    public static AtividadeForm from(Atividade atividade) {
        AtividadeForm form = new AtividadeForm();
        form.setTitulo(atividade.getTitulo());
        form.setDescricao(atividade.getDescricao());
        form.setData(atividade.getData());
        form.setLocal(atividade.getLocal());
        form.setHorasPrevistas(atividade.getHorasPrevistas());
        form.setStatus(atividade.getStatus());
        return form;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }
    public BigDecimal getHorasPrevistas() { return horasPrevistas; }
    public void setHorasPrevistas(BigDecimal horasPrevistas) { this.horasPrevistas = horasPrevistas; }
    public StatusAtividade getStatus() { return status; }
    public void setStatus(StatusAtividade status) { this.status = status; }
}
