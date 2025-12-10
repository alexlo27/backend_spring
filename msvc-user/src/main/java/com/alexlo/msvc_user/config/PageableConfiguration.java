package com.alexlo.msvc_user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class PageableConfiguration  implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        PageableHandlerMethodArgumentResolver resolver =
                new PageableHandlerMethodArgumentResolver();

        // Usar páginas basadas en 1 (cliente manda page=1 → Spring lo usa como 0)
        resolver.setOneIndexedParameters(true);

        // Valores por defecto si no envían page/size
        resolver.setFallbackPageable(PageRequest.of(0, 10));

        // Evita page < 1 automáticamente (Spring lo corrige)
        resolver.setMaxPageSize(100); // puedes ajustarlo según tu caso

        resolvers.add(resolver);
    }

}
