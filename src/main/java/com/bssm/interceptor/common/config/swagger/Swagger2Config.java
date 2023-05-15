package com.bssm.interceptor.common.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger2Config {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
            .group("v1-documentation")
            .pathsToMatch("/api/**")
            .addOpenApiCustomiser(openApiCustomiser())
            .build();
    }

    @Bean
    public OpenAPI springShopOpenApi() {
        return new OpenAPI()
            .info(new Info().title("Interceptor API")
                .description("개인 프로젝트 API 명세서입니다.")
                .version("v1.0.0"))
            .components(getComponents());
    }

    private Components getComponents() {
        return new Components()
            .addSecuritySchemes("jwt", getJwtSecurityScheme());
    }

    private SecurityScheme getJwtSecurityScheme() {
        return new SecurityScheme()
            .type(SecurityScheme.Type.APIKEY)
            .in(SecurityScheme.In.HEADER)
            .name("Authorization");
    }

    @Bean
    public OpenApiCustomiser openApiCustomiser() {
        return OpenApi -> OpenApi
            .addSecurityItem(getSecurityItem())
            .addServersItem(getServersItem());
    }

    @Bean
    public OpenApiCustomiser resumeOpenApiCustomiser() {
        return OpenApi -> OpenApi
            .addSecurityItem(getSecurityItem())
            .addServersItem(getServersItem());
    }

    private SecurityRequirement getSecurityItem() {
        return new SecurityRequirement()
            .addList("jwt");
    }

    private Server getServersItem() {
        return new Server().url("/");
    }

}