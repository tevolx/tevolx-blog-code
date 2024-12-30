plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "2.7.5"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.tevolx"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(11)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	/**
	 * springfox 3.x 버전 이하를 사용할 경우 swagger2와 ui를 각각 추가
	 * ui url은 "/swagger-ui.html"
	 */
//	implementation("io.springfox:springfox-swagger-ui:2.9.2")
//	implementation("io.springfox:springfox-swagger2:2.9.2")

	/**
	 * springfox 3.0.0 버전을 이용할 경우 springfox-boot-starter 하나만 추가
	 * ui url은 "/swagger-ui/index.html" 아니면 "/swagger-ui/"
	 */
	implementation("io.springfox:springfox-boot-starter:3.0.0")

	/**
	 * springdoc-openapi v1.8.0
	 * spring boot 2.x 이하 버전까지 지원.
	 */
//	implementation("org.springdoc:springdoc-openapi-ui:1.8.0")

	/**
	 * springdoc-openapi v2.7.0
	 * spring boot 2.x 이하 버전은 지원하지 않음
	 */
//	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.7.0")


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
