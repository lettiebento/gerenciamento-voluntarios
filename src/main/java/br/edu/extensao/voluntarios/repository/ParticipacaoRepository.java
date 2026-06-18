package br.edu.extensao.voluntarios.repository;

import br.edu.extensao.voluntarios.model.Participacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;

public interface ParticipacaoRepository extends JpaRepository<Participacao, Long> {
    boolean existsByVoluntarioIdAndAtividadeId(Long voluntarioId, Long atividadeId);
    boolean existsByVoluntarioIdAndAtividadeIdAndIdNot(Long voluntarioId, Long atividadeId, Long id);

    @Query("select p from Participacao p join fetch p.voluntario join fetch p.atividade order by p.atividade.data desc")
    List<Participacao> listarComRelacionamentos();

    @Query("select p from Participacao p join fetch p.atividade where p.voluntario.id = :voluntarioId order by p.atividade.data desc")
    List<Participacao> listarPorVoluntario(@Param("voluntarioId") Long voluntarioId);

    @Query("select coalesce(sum(p.horasRealizadas), 0) from Participacao p where p.voluntario.id = :voluntarioId and p.presente = true")
    BigDecimal somarHorasPorVoluntario(@Param("voluntarioId") Long voluntarioId);

    @Query("select coalesce(sum(p.horasRealizadas), 0) from Participacao p where p.presente = true")
    BigDecimal somarTodasAsHoras();

    long countByPresenteTrue();
    long deleteByVoluntarioId(Long voluntarioId);
    long deleteByAtividadeId(Long atividadeId);
}
