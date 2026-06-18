package br.edu.extensao.voluntarios.repository;

import br.edu.extensao.voluntarios.model.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {
    Optional<Voluntario> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
    boolean existsByMatriculaIgnoreCaseAndIdNot(String matricula, Long id);
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByMatriculaIgnoreCase(String matricula);
    long countByAtivoTrue();
    List<Voluntario> findAllByOrderByNomeAsc();
    List<Voluntario> findByAtivoTrueOrderByNomeAsc();
}
