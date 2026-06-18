package br.edu.extensao.voluntarios.service;

import br.edu.extensao.voluntarios.dto.ResumoVoluntario;
import br.edu.extensao.voluntarios.model.StatusAtividade;
import br.edu.extensao.voluntarios.repository.AtividadeRepository;
import br.edu.extensao.voluntarios.repository.ParticipacaoRepository;
import br.edu.extensao.voluntarios.repository.VoluntarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class RelatorioService {
    private final VoluntarioRepository voluntarioRepository;
    private final AtividadeRepository atividadeRepository;
    private final ParticipacaoRepository participacaoRepository;
    private final BigDecimal horasMinimas;

    public RelatorioService(VoluntarioRepository voluntarioRepository,
                            AtividadeRepository atividadeRepository,
                            ParticipacaoRepository participacaoRepository,
                            @Value("${app.certificado.horas-minimas:20}") BigDecimal horasMinimas) {
        this.voluntarioRepository = voluntarioRepository;
        this.atividadeRepository = atividadeRepository;
        this.participacaoRepository = participacaoRepository;
        this.horasMinimas = horasMinimas;
    }

    public long totalVoluntariosAtivos() { return voluntarioRepository.countByAtivoTrue(); }
    public long totalAtividades() { return atividadeRepository.count(); }
    public long totalAtividadesConcluidas() { return atividadeRepository.countByStatus(StatusAtividade.CONCLUIDA); }
    public long totalPresencas() { return participacaoRepository.countByPresenteTrue(); }
    public BigDecimal totalHorasProjeto() { return participacaoRepository.somarTodasAsHoras(); }
    public BigDecimal getHorasMinimas() { return horasMinimas; }

    public List<ResumoVoluntario> resumoPorVoluntario() {
        return voluntarioRepository.findByAtivoTrueOrderByNomeAsc().stream().map(voluntario -> {
            BigDecimal horas = participacaoRepository.somarHorasPorVoluntario(voluntario.getId());
            boolean apto = horas.compareTo(horasMinimas) >= 0;
            int percentual = horasMinimas.signum() == 0 ? 100 : horas
                    .multiply(BigDecimal.valueOf(100))
                    .divide(horasMinimas, 0, RoundingMode.DOWN)
                    .min(BigDecimal.valueOf(100)).intValue();
            return new ResumoVoluntario(voluntario.getId(), voluntario.getNome(),
                    voluntario.getMatricula(), voluntario.getCurso(), horas, percentual, apto);
        }).toList();
    }
}
