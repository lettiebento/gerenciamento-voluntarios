package br.edu.extensao.voluntarios.service;

import br.edu.extensao.voluntarios.dto.VoluntarioForm;
import br.edu.extensao.voluntarios.exception.RegraNegocioException;
import br.edu.extensao.voluntarios.model.Voluntario;
import br.edu.extensao.voluntarios.repository.ParticipacaoRepository;
import br.edu.extensao.voluntarios.repository.VoluntarioRepository;
import br.edu.extensao.voluntarios.util.SenhaUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class VoluntarioService {
    private final VoluntarioRepository repository;
    private final ParticipacaoRepository participacaoRepository;
    private final SenhaUtil senhaUtil;

    public VoluntarioService(VoluntarioRepository repository,
                             ParticipacaoRepository participacaoRepository,
                             SenhaUtil senhaUtil) {
        this.repository = repository;
        this.participacaoRepository = participacaoRepository;
        this.senhaUtil = senhaUtil;
    }

    public List<Voluntario> listarTodos() {
        return repository.findAllByOrderByNomeAsc();
    }

    public List<Voluntario> listarAtivos() {
        return repository.findByAtivoTrueOrderByNomeAsc();
    }

    public Voluntario buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Voluntário não encontrado."));
    }

    @Transactional
    public Voluntario criar(VoluntarioForm form) {
        validarDuplicidade(form, null);
        if (form.getSenha() == null || form.getSenha().trim().length() < 6) {
            throw new RegraNegocioException("A senha deve possuir pelo menos 6 caracteres.");
        }

        Voluntario voluntario = new Voluntario();
        copiar(form, voluntario);
        voluntario.setSenhaHash(senhaUtil.gerarHash(form.getSenha().trim()));
        return repository.save(voluntario);
    }

    @Transactional
    public Voluntario atualizar(Long id, VoluntarioForm form) {
        Voluntario voluntario = buscar(id);
        validarDuplicidade(form, id);
        copiar(form, voluntario);
        if (form.getSenha() != null && !form.getSenha().isBlank()) {
            if (form.getSenha().trim().length() < 6) {
                throw new RegraNegocioException("A nova senha deve possuir pelo menos 6 caracteres.");
            }
            voluntario.setSenhaHash(senhaUtil.gerarHash(form.getSenha().trim()));
        }
        return repository.save(voluntario);
    }

    @Transactional
    public void excluir(Long id) {
        buscar(id);
        participacaoRepository.deleteByVoluntarioId(id);
        repository.deleteById(id);
    }

    private void validarDuplicidade(VoluntarioForm form, Long id) {
        boolean emailExiste = id == null
                ? repository.existsByEmailIgnoreCase(form.getEmail().trim())
                : repository.existsByEmailIgnoreCaseAndIdNot(form.getEmail().trim(), id);
        if (emailExiste) throw new RegraNegocioException("Já existe um voluntário com este e-mail.");

        boolean matriculaExiste = id == null
                ? repository.existsByMatriculaIgnoreCase(form.getMatricula().trim())
                : repository.existsByMatriculaIgnoreCaseAndIdNot(form.getMatricula().trim(), id);
        if (matriculaExiste) throw new RegraNegocioException("Já existe um voluntário com esta matrícula.");
    }

    private void copiar(VoluntarioForm form, Voluntario voluntario) {
        voluntario.setNome(form.getNome().trim());
        voluntario.setEmail(form.getEmail().trim().toLowerCase());
        voluntario.setMatricula(form.getMatricula().trim().toUpperCase());
        voluntario.setCurso(form.getCurso().trim());
        voluntario.setTelefone(form.getTelefone() == null ? null : form.getTelefone().trim());
        voluntario.setAtivo(form.isAtivo());
    }
}
