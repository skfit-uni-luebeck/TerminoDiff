import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.1.10"
    id("org.jetbrains.compose") version "1.7.3"
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.10"
    id("org.openjfx.javafxplugin") version "0.1.0"
}
val projectVersion: String by project
group = "de.uzl.imbs.skfit"
version = projectVersion

repositories {
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
    mavenCentral()
}

val hapiVersion = "6.8.7"
val slf4jVersion = "2.0.17"
val graphStreamVersion = "2.0"
val jGraphTVersion = "1.5.2"
val jungraphtVersion = "1.4"
val composeDesktopVersion = "1.7.3"
val ktorVersion = "3.1.1"
val jenaVersion = "5.3.0"

dependencies {
    testImplementation(kotlin("test"))
    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")
    implementation("org.jetbrains.compose.components:components-splitpane:$composeDesktopVersion")
    implementation("org.jetbrains.compose.material:material-icons-core-desktop:$composeDesktopVersion")
    implementation("org.jetbrains.compose.material:material-icons-extended-desktop:$composeDesktopVersion")
    implementation("org.jetbrains.compose.material3:material3-desktop:$composeDesktopVersion")
    implementation("ca.uhn.hapi.fhir:hapi-fhir-base:$hapiVersion")
    implementation("ca.uhn.hapi.fhir:hapi-fhir-structures-r4:$hapiVersion")
    implementation("ca.uhn.hapi.fhir:hapi-fhir-validation:$hapiVersion")
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")
    implementation("org.jgrapht:jgrapht-core:$jGraphTVersion")
    implementation("org.jgrapht:jgrapht-ext:$jGraphTVersion")
    implementation("com.github.tomnelson:jungrapht-visualization:$jungraphtVersion")
    implementation("com.github.tomnelson:jungrapht-layout:$jungraphtVersion")
    implementation("net.mahdilamb:colormap:0.9.511")
    implementation("li.flor:native-j-file-chooser:1.6.4")
    implementation("javax.xml.bind:jaxb-api:2.4.0-b180830.0359") // provides org.xml.sax
    implementation("org.apache.commons:commons-lang3:3.17.0")
    implementation("com.formdev:flatlaf:3.5.4")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("me.xdrop:fuzzywuzzy:1.4.0")
    implementation("com.fifesoft:rsyntaxtextarea:3.6.0")
    implementation("org.apache.jena:jena-core:$jenaVersion")
    implementation("org.apache.jena:jena-arq:$jenaVersion")

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
                vendor = "Section for Clinical Research IT, Institute of Medical Biometry and Statistics, University of Lübeck"
                copyright = "Joshua Wiedekopf / Section for Clinical Research IT, Institute of Medical Biometry and Statistics, 2022-"

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
                        bundleID = "de.uzl.itcr.terminodiff"
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