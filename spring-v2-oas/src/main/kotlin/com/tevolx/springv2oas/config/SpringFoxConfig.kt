package com.tevolx.springv2oas.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class SpringFoxConfig {

    @Bean
    fun docket(): Docket {
        return Docket(DocumentationType.SWAGGER_2) // OAS 버전 지정 가능
            .select()
            .paths(PathSelectors.ant("/spring-v2/**"))
            .build()
    }
}