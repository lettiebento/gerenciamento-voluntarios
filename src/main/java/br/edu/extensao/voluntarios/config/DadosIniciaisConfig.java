package br.edu.extensao.voluntarios.config;

import br.edu.extensao.voluntarios.model.*;
import br.edu.extensao.voluntarios.repository.*;
import br.edu.extensao.voluntarios.util.SenhaUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class DadosIniciaisConfig {
    @Bean
    CommandLineRunner carregarDados(CoordenadorRepository coordenadores,
                                     VoluntarioRepository voluntarios,
                                     AtividadeRepository atividades,
                                     ParticipacaoRepository participacoes,
                                     SenhaUtil senhaUtil) {
        return args -> inicializar(coordenadores, voluntarios, atividades, participacoes, senhaUtil);
    }

    @Transactional
    void inicializar(CoordenadorRepository coordenadores,
                     VoluntarioRepository voluntarios,
                     AtividadeRepository atividades,
                     ParticipacaoRepository participacoes,
                     SenhaUtil senhaUtil) {
        if (coordenadores.count() == 0) {
            coordenadores.save(new Coordenador("Coordenação do Projeto", "admin@extensao.com",
                    senhaUtil.gerarHash("admin123")));
        }

        if (voluntarios.count() == 0) {
            voluntarios.save(criarVoluntario("Tiago", "tiago@extensao.com", "2026001",
                    "Pedagogia", "(43) 99999-1001", senhaUtil));
            voluntarios.save(criarVoluntario("Josiane", "josiane@extensao.com", "2026002",
                    "Engenharia Civil", "(43) 99999-1002", senhaUtil));
            voluntarios.save(criarVoluntario("Pamela", "pamela@extensao.com", "2026003",
                    "Administração", "(43) 99999-1003", senhaUtil));
        }

        if (atividades.count() == 0) {
            atividades.save(criarAtividade("Oficina de inclusão digital",
                    "Apoio aos participantes no uso de ferramentas digitais e serviços públicos on-line.",
                    LocalDate.now().minusDays(20), "Laboratório de Informática", "8.00", StatusAtividade.CONCLUIDA));
            atividades.save(criarAtividade("Campanha de arrecadação",
                    "Organização, triagem e distribuição de alimentos para famílias atendidas pelo projeto.",
                    LocalDate.now().minusDays(8), "Centro Comunitário", "6.00", StatusAtividade.CONCLUIDA));
            atividades.save(criarAtividade("Feira de profissões",
                    "Apresentação dos cursos da universidade para estudantes do ensino médio.",
                    LocalDate.now().plusDays(12), "UTFPR", "5.00", StatusAtividade.PLANEJADA));
        }

        if (participacoes.count() == 0) {
            var vs = voluntarios.findAllByOrderByNomeAsc();
            var ats = atividades.findAllByOrderByDataDesc();
            var concluida1 = ats.stream().filter(a -> a.getTitulo().contains("inclusão")).findFirst().orElseThrow();
            var concluida2 = ats.stream().filter(a -> a.getTitulo().contains("arrecadação")).findFirst().orElseThrow();
            participacoes.save(criarParticipacao(vs.get(0), concluida1, "8.00", true, "Participação integral."));
            participacoes.save(criarParticipacao(vs.get(1), concluida1, "7.50", true, "Auxílio na organização."));
            participacoes.save(criarParticipacao(vs.get(0), concluida2, "6.00", true, "Triagem de alimentos."));
            participacoes.save(criarParticipacao(vs.get(2), concluida2, "5.00", true, "Apoio na distribuição."));
        }
    }

    private Voluntario criarVoluntario(String nome, String email, String matricula,
                                       String curso, String telefone, SenhaUtil senhaUtil) {
        Voluntario v = new Voluntario();
        v.setNome(nome); v.setEmail(email); v.setMatricula(matricula);
        v.setCurso(curso); v.setTelefone(telefone); v.setAtivo(true);
        v.setSenhaHash(senhaUtil.gerarHash("voluntario123"));
        return v;
    }

    private Atividade criarAtividade(String titulo, String descricao, LocalDate data,
                                     String local, String horas, StatusAtividade status) {
        Atividade a = new Atividade();
        a.setTitulo(titulo); a.setDescricao(descricao); a.setData(data);
        a.setLocal(local); a.setHorasPrevistas(new BigDecimal(horas)); a.setStatus(status);
        return a;
    }

    private Participacao criarParticipacao(Voluntario v, Atividade a, String horas,
                                           boolean presente, String observacao) {
        Participacao p = new Participacao();
        p.setVoluntario(v); p.setAtividade(a); p.setHorasRealizadas(new BigDecimal(horas));
        p.setPresente(presente); p.setObservacao(observacao);
        return p;
    }
}
