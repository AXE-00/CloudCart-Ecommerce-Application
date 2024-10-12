package com_selfProject_APIGateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


@Configuration
public class APIGateWay {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder rlb){
        return rlb.routes()
                .route(p->p.path("/api/v1/authService/**")
                        .uri("http://localhost:8081"))
                .route(p->p.path("/api/v1/userService/**")
                        .uri("http://localhost:8082"))
                .route(p->p.path("/api/v1/productService/**")
                        .uri("http://localhost:8083"))
                .route(p->p.path("/v1/api/cart/**")
                        .uri("http://localhost:8084"))
                .route(p->p.path("/api/v1/emailService/**")
                        .uri("http://localhost:8089"))
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
