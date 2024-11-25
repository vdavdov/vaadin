plugins {
    java
    id("org.springframework.boot") version "3.3.6"
    id("io.spring.dependency-management") version "1.1.6"
    id("com.vaadin") version "24.5.5"
}

group = "by.vdavdov"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

extra["vaadinVersion"] = "24.5.5"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.vaadin:vaadin-spring-boot-starter")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:mongodb")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.apache.poi:poi-ooxml:5.2.3")
}

dependencyManagement {
    imports {
        mavenBom("com.vaadin:vaadin-bom:${property("vaadinVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

vaadin {
    productionMode = true
}

