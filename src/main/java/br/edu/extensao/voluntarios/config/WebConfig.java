package br.edu.extensao.voluntarios.config;

import br.edu.extensao.voluntarios.interceptor.PerfilInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final PerfilInterceptor perfilInterceptor;

    public WebConfig(PerfilInterceptor perfilInterceptor) {
        this.perfilInterceptor = perfilInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(perfilInterceptor)
                .addPathPatterns("/coordenador/**", "/voluntario/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/acesso-negado").setViewName("erro/acesso-negado");
    }
}
