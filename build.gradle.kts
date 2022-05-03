import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val daggerVersion: String = "2.41"
val koinVersion: String = "3.2.0-beta-1"

// val koinKspVersion: String = "1.0.0-beta-2"

plugins {
    val kotlinVersion = "1.6.10" // No nos sirve la 1.6.20 aun con Ksp para Koin
    // val kspVersion = "1.6.10-1.0.2"

    kotlin("jvm") version kotlinVersion
    // Usamos kapt para anotaciones de Dagger
    kotlin("kapt") version kotlinVersion
    // Usamos KSP para Koin con anotaciones
    // id("com.google.devtools.ksp") version kspVersion
}

group = "es.joseluisgs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    // Dagger
    implementation("com.google.dagger:dagger:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")

    // Koin
    implementation("io.insert-koin:koin-core:$koinVersion")
    // Si queremos las anotaciones de Koin
    // implementation("io.insert-koin:koin-annotations:$koinKspVersion")
    // ksp("io.insert-koin:koin-ksp-compiler:$koinKspVersion")
    // si queremos test
    //testImplementation("io.insert-koin:koin-test:$koinVersion")

    // Test
    testImplementation(kotlin("test"))
}

// Para usar notaciones de Koin
//kotlin {
//    sourceSets.main {
//        kotlin.srcDir("build/generated/ksp/main/kotlin")
//    }
//    sourceSets.test {
//        kotlin.srcDir("build/generated/ksp/test/kotlin")
//    }
//}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}