package com.example.teste.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    private final ResponseMessage m200 = simplesMessage(200, "Chamada realizada com sucesso");
    private final ResponseMessage m201 = simplesMessage(201, "Recurso Criado");
    private final ResponseMessage m204 = simplesMessage(204, "Atualização Ok");
    private final ResponseMessage m400 = simplesMessage(400, "Erro Inesperado");
    private final ResponseMessage m401 = simplesMessage(401, "Autorização é Requerida");
    private final ResponseMessage m403 = simplesMessage(403, "Não Autorizado");
    private final ResponseMessage m404 = simplesMessage(404, "Objeto não Encontrado");
    private final ResponseMessage m422 = simplesMessage(422, "Erro de Validação");
    private final ResponseMessage m500 = simplesMessage(500, "Erro interno de Servidor");

    @Bean
    public Docket greetingApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.POST, Arrays.asList(m200,m403,m422,m500,m201))
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(m403,m404,m500))
                .globalResponseMessage(RequestMethod.PUT, Arrays.asList(m403,m204,m422,m500))
                .globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m403,m404,m200))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.teste"))
                .build()
                .apiInfo(metaData());

    }
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Spring Boot REST API")
                .description("\"Spring Boot REST API for greeting teste\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    private ResponseMessage simplesMessage(int code, String msg){
        return new ResponseMessageBuilder().code(code).message(msg).build();
    }



























}
