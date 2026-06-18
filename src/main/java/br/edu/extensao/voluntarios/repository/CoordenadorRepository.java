package br.edu.extensao.voluntarios.repository;

import br.edu.extensao.voluntarios.model.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CoordenadorRepository extends JpaRepository<Coordenador, Long> {
    Optional<Coordenador> findByEmailIgnoreCase(String email);
}
