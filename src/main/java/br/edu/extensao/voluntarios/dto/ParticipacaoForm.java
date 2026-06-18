package br.edu.extensao.voluntarios.dto;

import br.edu.extensao.voluntarios.model.Participacao;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ParticipacaoForm {
    @NotNull(message = "Selecione o voluntário.")
    private Long voluntarioId;

    @NotNull(message = "Selecione a atividade.")
    private Long atividadeId;

    @NotNull(message = "Informe as horas realizadas.")
    @DecimalMin(value = "0.0", inclusive = true, message = "As horas não podem ser negativas.")
    @DecimalMax(value = "999.99", message = "O valor máximo é 999,99 horas.")
    private BigDecimal horasRealizadas = BigDecimal.ZERO;

    private boolean presente = true;

    @Size(max = 500)
    private String observacao;

    public static ParticipacaoForm from(Participacao participacao) {
        ParticipacaoForm form = new ParticipacaoForm();
        form.setVoluntarioId(participacao.getVoluntario().getId());
        form.setAtividadeId(participacao.getAtividade().getId());
        form.setHorasRealizadas(participacao.getHorasRealizadas());
        form.setPresente(participacao.isPresente());
        form.setObservacao(participacao.getObservacao());
        return form;
    }

    public Long getVoluntarioId() { return voluntarioId; }
    public void setVoluntarioId(Long voluntarioId) { this.voluntarioId = voluntarioId; }
    public Long getAtividadeId() { return atividadeId; }
    public void setAtividadeId(Long atividadeId) { this.atividadeId = atividadeId; }
    public BigDecimal getHorasRealizadas() { return horasRealizadas; }
    public void setHorasRealizadas(BigDecimal horasRealizadas) { this.horasRealizadas = horasRealizadas; }
    public boolean isPresente() { return presente; }
    public void setPresente(boolean presente) { this.presente = presente; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
}
