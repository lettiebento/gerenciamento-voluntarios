package br.edu.extensao.voluntarios.repository;

import br.edu.extensao.voluntarios.model.Atividade;
import br.edu.extensao.voluntarios.model.StatusAtividade;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
    List<Atividade> findAllByOrderByDataDesc();
    long countByStatus(StatusAtividade status);
}
