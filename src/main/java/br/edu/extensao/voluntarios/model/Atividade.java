package br.edu.extensao.voluntarios.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "atividades")
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, length = 1000)
    private String descricao;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false, length = 150)
    private String local;

    @Column(name = "horas_previstas", nullable = false, precision = 6, scale = 2)
    private BigDecimal horasPrevistas;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StatusAtividade status;

    public Atividade() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
