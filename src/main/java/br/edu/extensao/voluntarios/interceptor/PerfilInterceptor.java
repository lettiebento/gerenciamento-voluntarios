package br.edu.extensao.voluntarios.interceptor;

import br.edu.extensao.voluntarios.model.Perfil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class PerfilInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object perfilSessao = request.getSession().getAttribute("usuarioPerfil");
        if (perfilSessao == null) {
            response.sendRedirect("/login");
            return false;
        }

        Perfil perfil = Perfil.valueOf(perfilSessao.toString());
        String caminho = request.getRequestURI();

        if (caminho.startsWith("/coordenador") && perfil != Perfil.COORDENADOR) {
            response.sendRedirect("/voluntario");
            return false;
        }
        if (caminho.startsWith("/voluntario") && perfil != Perfil.VOLUNTARIO) {
            response.sendRedirect("/coordenador");
            return false;
        }
        return true;
    }
}
