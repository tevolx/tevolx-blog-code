package com.tevolx.springv2oas

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2

//@EnableSwagger2 // springfox 3.x 이상 버전에서는 불필요
@SpringBootApplication
class SpringV2OASApplication

fun main(args: Array<String>) {
	runApplication<SpringV2OASApplication>(*args)
}
