package br.edu.extensao.voluntarios.service;

import br.edu.extensao.voluntarios.dto.ParticipacaoForm;
import br.edu.extensao.voluntarios.exception.RegraNegocioException;
import br.edu.extensao.voluntarios.model.Participacao;
import br.edu.extensao.voluntarios.repository.ParticipacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ParticipacaoService {
    private final ParticipacaoRepository repository;
    private final VoluntarioService voluntarioService;
    private final AtividadeService atividadeService;

    public ParticipacaoService(ParticipacaoRepository repository,
                               VoluntarioService voluntarioService,
                               AtividadeService atividadeService) {
        this.repository = repository;
        this.voluntarioService = voluntarioService;
        this.atividadeService = atividadeService;
    }

    @Transactional(readOnly = true)
    public List<Participacao> listarTodas() {
        return repository.listarComRelacionamentos();
    }

    @Transactional(readOnly = true)
    public List<Participacao> listarPorVoluntario(Long voluntarioId) {
        return repository.listarPorVoluntario(voluntarioId);
    }

    @Transactional(readOnly = true)
    public Participacao buscar(Long id) {
        Participacao participacao = repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Participação não encontrada."));
        participacao.getVoluntario().getNome();
        participacao.getAtividade().getTitulo();
        return participacao;
    }

    @Transactional
    public Participacao criar(ParticipacaoForm form) {
        if (repository.existsByVoluntarioIdAndAtividadeId(form.getVoluntarioId(), form.getAtividadeId())) {
            throw new RegraNegocioException("Este voluntário já está registrado nesta atividade.");
        }
        Participacao participacao = new Participacao();
        copiar(form, participacao);
        return repository.save(participacao);
    }

    @Transactional
    public Participacao atualizar(Long id, ParticipacaoForm form) {
        Participacao participacao = buscar(id);
        if (repository.existsByVoluntarioIdAndAtividadeIdAndIdNot(
                form.getVoluntarioId(), form.getAtividadeId(), id)) {
            throw new RegraNegocioException("Este voluntário já está registrado nesta atividade.");
        }
        copiar(form, participacao);
        return repository.save(participacao);
    }

    @Transactional
    public void excluir(Long id) {
        buscar(id);
        repository.deleteById(id);
    }

    public BigDecimal totalHoras(Long voluntarioId) {
        return repository.somarHorasPorVoluntario(voluntarioId);
    }

    private void copiar(ParticipacaoForm form, Participacao participacao) {
        var voluntario = voluntarioService.buscar(form.getVoluntarioId());
        var atividade = atividadeService.buscar(form.getAtividadeId());

        if (!form.isPresente() && form.getHorasRealizadas().compareTo(BigDecimal.ZERO) > 0) {
            throw new RegraNegocioException("Uma ausência não pode contabilizar horas realizadas.");
        }
        if (form.getHorasRealizadas().compareTo(atividade.getHorasPrevistas()) > 0) {
            throw new RegraNegocioException("As horas realizadas não podem superar as horas previstas da atividade.");
        }

        participacao.setVoluntario(voluntario);
        participacao.setAtividade(atividade);
        participacao.setHorasRealizadas(form.isPresente() ? form.getHorasRealizadas() : BigDecimal.ZERO);
        participacao.setPresente(form.isPresente());
        participacao.setObservacao(form.getObservacao() == null ? null : form.getObservacao().trim());
    }
}
