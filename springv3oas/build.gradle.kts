plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.0"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.tevolx"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	/**
	 * spring boot 3 이상부터 springfox 지원x.
 	 */
//	implementation("io.springfox:springfox-boot-starter:3.0.0")

	/**
	 * springdoc-openapi v1.8.0
	 * spring boot 3 부터는 springdoc-openapi v1 지원x -> springdoc-openapi v2 사용 필요.
	 */
//	implementation("org.springdoc:springdoc-openapi-ui:1.8.0")

	/**
	 * springdoc-openapi v2.7.0 (webmvc)
	 * webmvc-ui를 추가해주면 자동으로 webmvc-api 및 common 의존성 추가
	 */
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.7.0")

	/**
	 * springdoc-openapi v2.7.0 (webflux)
	 * webflux-ui를 추가해주면 자동으로 webflux-api 및 common 의존성 추가
	 */
//	implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.7.0")

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
