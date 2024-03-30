plugins {
    id("java")
    id("checkstyle")
    id("pmd")
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "org.library"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")

    // junit
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")

    // lombok
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    // db
    implementation("com.h2database:h2")

    // tools
    testImplementation("net.javacrumbs.json-unit:json-unit-assertj:3.2.2")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
    implementation("org.openapitools:jackson-databind-nullable:0.2.6")
}

checkstyle {
    toolVersion = "10.14.0"
    config = resources.text.fromFile("${project.projectDir}/src/main/resources/checkstyle.xml")
}

pmd {
    toolVersion = "6.55.0"
    isConsoleOutput = true
    ruleSets = listOf("${project.projectDir}/src/main/resources/pmdrules.xml")
}

tasks.test {
    useJUnitPlatform()
}

task("codeQualityCheck") {
    group = "verification"
    dependsOn("checkstyleMain", "checkstyleTest", "pmdMain", "pmdTest")
}
