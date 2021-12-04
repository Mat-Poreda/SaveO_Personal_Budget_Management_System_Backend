package Save.O.Save.O.Gateway.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


import java.util.Arrays;

@Configuration
public class ApiGatewayConfiguration {



    @Bean
    CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:8000", "http://localhost:8100", "http://localhost:8200"));
        corsConfig.setMaxAge(8000L);
        corsConfig.addAllowedMethod("GET");
        corsConfig.addAllowedMethod("PUT");
        corsConfig.addAllowedMethod("POST");
        corsConfig.addAllowedMethod("PATCH");
        corsConfig.addAllowedMethod("DELETE");
        corsConfig.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/api/data_storage/**")
                        .uri("lb://DATA-STORAGE"))
                .route(p -> p.path("/api/data_storage**")
                        .uri("lb://DATA-STORAGE"))
                .route(p -> p.path("/api/user_details/**")
                        .uri("lb://USER-PROFILE"))
                .route(p -> p.path("/api/user_details**")
                        .uri("lb://USER-PROFILE"))
                .build();
    }
}
