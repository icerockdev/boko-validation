/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import java.util.Base64
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("maven-publish")
    id("java-library")
    id("signing")
}

group = "com.icerockdev"
version = "0.1.0"

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

repositories {
    mavenCentral()
}

dependencies {
    val konformVersion: String by project
    val jodaTimeVersion: String by project

    implementation(kotlin("stdlib"))
    implementation("io.konform:konform:$konformVersion")
    implementation("joda-time:joda-time:$jodaTimeVersion")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
    withJavadocJar()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

publishing {
    repositories.maven("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/") {
        name = "OSSRH"

        credentials {
            username = System.getenv("OSSRH_USER")
            password = System.getenv("OSSRH_KEY")
        }
    }
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
            artifact(sourcesJar.get())
            pom {
                name.set("Konform constraints")
                description.set("Constraints for konform validations")
                url.set("https://github.com/icerockdev/konform-constraints")
                inceptionYear.set("2022")

                licenses {
                    license {
                        name.set("Apache License 2.0")
                        url.set("https://github.com/icerockdev/konform-constraints/blob/master/LICENSE.md")
                    }
                }

                developers {
                    developer {
                        id.set("YokiToki")
                        name.set("Stanislav")
                        email.set("skarakovski@icerockdev.com")
                    }
                }

                scm {
                    connection.set("scm:git:ssh://github.com/icerockdev/konform-constraints.git")
                    developerConnection.set("scm:git:ssh://github.com/icerockdev/konform-constraints.git")
                    url.set("https://github.com/icerockdev/konform-constraints")
                }
            }
        }

        signing {
            val signingKeyId: String? = System.getenv("SIGNING_KEY_ID")
            val signingPassword: String? = System.getenv("SIGNING_PASSWORD")
            val signingKey: String? = System.getenv("SIGNING_KEY")?.let { base64Key ->
                String(Base64.getDecoder().decode(base64Key))
            }
            useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
            sign(publishing.publications["mavenJava"])
        }
    }
}
