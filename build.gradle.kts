import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.kotlin.dsl.withType
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.compose)
    alias(libs.plugins.compose)
    alias(libs.plugins.javafxplugin)
    alias(libs.plugins.versions)
}
val projectVersion: String by project
group = "de.uzl.imbs.skfit"
version = projectVersion

repositories {
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(compose.desktop.currentOs)
    implementation(libs.org.jetbrains.kotlin.reflect)
    implementation(libs.org.jetbrains.kotlinx.coroutines.core)
    implementation(libs.org.jetbrains.compose.components.splitpane)
    implementation(libs.org.jetbrains.compose.material.icons.core.desktop)
    implementation(libs.org.jetbrains.compose.material.icons.extended.desktop)
    implementation(libs.org.jetbrains.compose.components.resources)
    implementation(libs.org.jetbrains.compose.material3.desktop)
    implementation(libs.ca.uhn.hapi.fhir.base)
    implementation(libs.ca.uhn.hapi.fhir.structures.r4)
    implementation(libs.ca.uhn.hapi.fhir.validation) {
        exclude(module = "ucum")
        exclude(group = "junit", module = "junit")
    }
    implementation(libs.org.slf4j.api)
    implementation(libs.org.apache.logging.log4j.api)
    implementation(libs.org.apache.logging.log4j.core)
    implementation(libs.org.apache.logging.log4j.slf4j2.impl)
    implementation(libs.org.apache.logging.log4j.layout.template.json)
    implementation(libs.org.apache.logging.log4j.api.kotlin)
    implementation(libs.org.jgrapht.core)
    implementation(libs.org.jgrapht.ext)
    implementation(libs.com.github.tomnelson.jungrapht.visualization)
    implementation(libs.com.github.tomnelson.jungrapht.layout)
    implementation(libs.net.mahdilamb.colormap)
    implementation(libs.li.flor.native.j.file.chooser)
    implementation(libs.javax.xml.bind.jaxb.api)
    implementation(libs.org.apache.commons.lang3)
    implementation(libs.com.formdev.flatlaf)
    implementation(libs.io.ktor.client.core)
    implementation(libs.io.ktor.client.cio)
    implementation(libs.me.xdrop.fuzzywuzzy)
    implementation(libs.com.fifesoft.rsyntaxtextarea)
    implementation(libs.org.apache.jena.core)
    implementation(libs.org.apache.jena.arq)
    implementation(libs.ca.gosyer.kotlin.multiplatform.appdirs)
}

tasks.test {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

javafx {
    // add javafx to the classpath
    version = "17.0.1"
    modules("javafx.controls", "javafx.swing")
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
        optIn.add("kotlin.RequiresOptIn")
    }
}

val composeBuildVersion: String by project
val composeBuildOs: String? by project

compose.desktop {
    application {
        mainClass = "terminodiff.MainKt"
        if (composeBuildOs != null) {
            nativeDistributions {
                val resourceDir = project.layout.projectDirectory.dir("resources")
                appResourcesRootDir.set(resourceDir)
                licenseFile.set(project.file("LICENSE"))
                packageName = "TerminoDiff"
                packageVersion = composeBuildVersion
                description = "Visually compare HL7 FHIR Terminology"
                vendor =
                    "Section for Clinical Research IT, Institute of Medical Biometry and Statistics, University of Lübeck"
                copyright =
                    "Joshua Wiedekopf / Section for Clinical Research IT, Institute of Medical Biometry and Statistics, 2022-"

                when (composeBuildOs?.lowercase()) {
                    "ubuntu", "redhat", "debian", "rpm", "deb" -> linux {
                        iconFile.set(resourceDir.file("common/terminodiff.png"))
                        rpmLicenseType = "GPL-3.0"
                        debMaintainer = "j.wiedekopf@uni-luebeck.de"
                        appCategory = "Development"
                        when (composeBuildOs) {
                            "ubuntu", "debian", "deb" -> targetFormats(
                                TargetFormat.Deb,
                            )

                            "redhat", "rpm" -> targetFormats(
                                TargetFormat.Rpm
                            )
                        }
                    }

                    "mac", "macos" -> macOS {
                        jvmArgs += listOf("-Dskiko.renderApi=SOFTWARE")
                        bundleID = "de.uzl.imbs.skfit.terminodiff"
                        signing {
                            sign.set(false)
                        }
                        iconFile.set(resourceDir.file("macos/terminodiff.icns"))
                        targetFormats(
                            TargetFormat.Dmg
                        )
                    }

                    "windows", "win" -> windows {
                        iconFile.set(resourceDir.file("windows/terminodiff.ico"))
                        perUserInstall = true
                        dirChooser = true
                        upgradeUuid = "ECFA19D9-D1F2-4AF5-9E5E-59A8F21C3A79"
                        menuGroup = "TerminoDiff"
                        targetFormats(
                            TargetFormat.Exe
                        )
                    }
                }
            }
        }
    }
}

fun isNonStable(version: String): Boolean {
    val hasStableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val hasUnstableKeyword = listOf("ALPHA", "BETA", "RC", "SNAPSHOT", "DEV").any { version.uppercase().contains(it) }
    return when {
        hasUnstableKeyword -> true
        hasStableKeyword -> false
        else -> false
    }
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}