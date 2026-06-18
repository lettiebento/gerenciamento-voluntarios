package br.edu.extensao.voluntarios.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "participacoes", uniqueConstraints = {
        @UniqueConstraint(name = "uk_participacao", columnNames = {"voluntario_id", "atividade_id"})
})
public class Participacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "voluntario_id", nullable = false)
    private Voluntario voluntario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "atividade_id", nullable = false)
    private Atividade atividade;

    @Column(name = "horas_realizadas", nullable = false, precision = 6, scale = 2)
    private BigDecimal horasRealizadas;

    @Column(nullable = false)
    private boolean presente;

    @Column(length = 500)
    private String observacao;

    public Participacao() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Voluntario getVoluntario() { return voluntario; }
    public void setVoluntario(Voluntario voluntario) { this.voluntario = voluntario; }
    public Atividade getAtividade() { return atividade; }
    public void setAtividade(Atividade atividade) { this.atividade = atividade; }
    public BigDecimal getHorasRealizadas() { return horasRealizadas; }
    public void setHorasRealizadas(BigDecimal horasRealizadas) { this.horasRealizadas = horasRealizadas; }
    public boolean isPresente() { return presente; }
    public void setPresente(boolean presente) { this.presente = presente; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
}
