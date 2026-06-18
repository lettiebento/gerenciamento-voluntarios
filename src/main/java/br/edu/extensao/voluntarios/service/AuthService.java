package br.edu.extensao.voluntarios.service;

import br.edu.extensao.voluntarios.dto.UsuarioAutenticado;
import br.edu.extensao.voluntarios.model.Perfil;
import br.edu.extensao.voluntarios.repository.CoordenadorRepository;
import br.edu.extensao.voluntarios.repository.VoluntarioRepository;
import br.edu.extensao.voluntarios.util.SenhaUtil;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {
    private final CoordenadorRepository coordenadorRepository;
    private final VoluntarioRepository voluntarioRepository;
    private final SenhaUtil senhaUtil;

    public AuthService(CoordenadorRepository coordenadorRepository,
                       VoluntarioRepository voluntarioRepository,
                       SenhaUtil senhaUtil) {
        this.coordenadorRepository = coordenadorRepository;
        this.voluntarioRepository = voluntarioRepository;
        this.senhaUtil = senhaUtil;
    }

    public Optional<UsuarioAutenticado> autenticar(String email, String senha) {
        var coordenador = coordenadorRepository.findByEmailIgnoreCase(email.trim());
        if (coordenador.isPresent() && senhaUtil.conferir(senha, coordenador.get().getSenhaHash())) {
            return Optional.of(new UsuarioAutenticado(
                    coordenador.get().getId(), coordenador.get().getNome(), Perfil.COORDENADOR));
        }

        var voluntario = voluntarioRepository.findByEmailIgnoreCase(email.trim());
        if (voluntario.isPresent() && voluntario.get().isAtivo()
                && senhaUtil.conferir(senha, voluntario.get().getSenhaHash())) {
            return Optional.of(new UsuarioAutenticado(
                    voluntario.get().getId(), voluntario.get().getNome(), Perfil.VOLUNTARIO));
        }

        return Optional.empty();
    }
}
