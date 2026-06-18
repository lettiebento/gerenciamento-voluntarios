package br.edu.extensao.voluntarios.service;

import br.edu.extensao.voluntarios.dto.AtividadeForm;
import br.edu.extensao.voluntarios.exception.RegraNegocioException;
import br.edu.extensao.voluntarios.model.Atividade;
import br.edu.extensao.voluntarios.repository.AtividadeRepository;
import br.edu.extensao.voluntarios.repository.ParticipacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AtividadeService {
    private final AtividadeRepository repository;
    private final ParticipacaoRepository participacaoRepository;

    public AtividadeService(AtividadeRepository repository, ParticipacaoRepository participacaoRepository) {
        this.repository = repository;
        this.participacaoRepository = participacaoRepository;
    }

    public List<Atividade> listarTodas() {
        return repository.findAllByOrderByDataDesc();
    }

    public Atividade buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Atividade não encontrada."));
    }

    @Transactional
    public Atividade criar(AtividadeForm form) {
        Atividade atividade = new Atividade();
        copiar(form, atividade);
        return repository.save(atividade);
    }

    @Transactional
    public Atividade atualizar(Long id, AtividadeForm form) {
        Atividade atividade = buscar(id);
        copiar(form, atividade);
        return repository.save(atividade);
    }

    @Transactional
    public void excluir(Long id) {
        buscar(id);
        participacaoRepository.deleteByAtividadeId(id);
        repository.deleteById(id);
    }

    private void copiar(AtividadeForm form, Atividade atividade) {
        atividade.setTitulo(form.getTitulo().trim());
        atividade.setDescricao(form.getDescricao().trim());
        atividade.setData(form.getData());
        atividade.setLocal(form.getLocal().trim());
        atividade.setHorasPrevistas(form.getHorasPrevistas());
        atividade.setStatus(form.getStatus());
    }
}
