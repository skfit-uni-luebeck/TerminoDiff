import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.javafxplugin)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinJvm)
}

group = "de.uzl.imbs.skfit"
version = "3.0.0"

repositories {
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
    mavenCentral()
}


dependencies {
    implementation(project(":shared"))
    implementation(libs.compose.runtime)
    implementation(libs.compose.foundation)
    implementation(compose.desktop.currentOs)
    implementation(libs.org.slf4j.api)
    implementation(libs.org.apache.logging.log4j.api)
    implementation(libs.org.apache.logging.log4j.core)
    implementation(libs.org.apache.logging.log4j.slf4j2.impl)
    implementation(libs.org.apache.logging.log4j.layout.template.json)
    implementation(libs.org.apache.logging.log4j.api.kotlin)
    implementation(libs.com.formdev.flatlaf)
    implementation(libs.ca.gosyer.kotlin.multiplatform.appdirs)
}

javafx {
    // add javafx to the classpath
    version = "17.0.1"
    modules("javafx.controls", "javafx.swing")
}

compose.desktop {
    application {
        mainClass = "terminodiff.TerminodiffMainKt"
        nativeDistributions {
            licenseFile.set(project.file("LICENSE"))
            packageName = "TerminoDiff"
            packageVersion = version.toString()
            description = "Visually compare HL7 FHIR Terminology"
            vendor =
                "Section for Clinical Research IT, Institute of Medical Biometry and Statistics, University of Lübeck"
            copyright =
                "Joshua Wiedekopf / Section for Clinical Research IT, Institute of Medical Biometry and Statistics, 2022-"

            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb, TargetFormat.Rpm)

            macOS {
                bundleID = "de.uzl.imbs.skfit.terminodiff"
                iconFile = project.file("src/main/resources/appicons/macos/terminodiff.icns")
                appStore = false
            }
            windows {
                iconFile.set(project.file("src/main/resources/appicons/windows/terminodiff.ico"))
                perUserInstall = true
                dirChooser = true
                upgradeUuid = "ECFA19D9-D1F2-4AF5-9E5E-59A8F21C3A79"
                menuGroup = "TerminoDiff"
            }
            linux {
                iconFile.set(project.file("src/main/resources/appicons/common/terminodiff.png"))
                rpmLicenseType = "GPL-3.0"
                debMaintainer = "j.wiedekopf@uni-luebeck.de"
                appCategory = "Development"
                shortcut = true
            }
        }
    }
}

//compose.desktop {
//    application {
//        mainClass = "terminodiff.MainKt"
//        if (composeBuildOs != null) {
//            nativeDistributions {
//                val resourceDir = project.layout.projectDirectory.dir("resources")
//                appResourcesRootDir.set(resourceDir)
//                licenseFile.set(project.file("LICENSE"))
//                packageName = "TerminoDiff"
//                packageVersion = composeBuildVersion
//                description = "Visually compare HL7 FHIR Terminology"
//                vendor =
//                    "Section for Clinical Research IT, Institute of Medical Biometry and Statistics, University of Lübeck"
//                copyright =
//                    "Joshua Wiedekopf / Section for Clinical Research IT, Institute of Medical Biometry and Statistics, 2022-"
//
//                when (composeBuildOs?.lowercase()) {
//                    "ubuntu", "redhat", "debian", "rpm", "deb" -> linux {
//                        iconFile.set(resourceDir.file("common/terminodiff.png"))
//                        rpmLicenseType = "GPL-3.0"
//                        debMaintainer = "j.wiedekopf@uni-luebeck.de"
//                        appCategory = "Development"
//                        when (composeBuildOs) {
//                            "ubuntu", "debian", "deb" -> targetFormats(
//                                TargetFormat.Deb,
//                            )
//
//                            "redhat", "rpm" -> targetFormats(
//                                TargetFormat.Rpm
//                            )
//                        }
//                    }
//
//                    "mac", "macos" -> macOS {
//                        jvmArgs += listOf("-Dskiko.renderApi=SOFTWARE")
//                        bundleID = "de.uzl.imbs.skfit.terminodiff"
//                        signing {
//                            sign.set(false)
//                        }
//                        iconFile.set(resourceDir.file("macos/terminodiff.icns"))
//                        targetFormats(
//                            TargetFormat.Dmg
//                        )
//                    }
//
//                    "windows", "win" -> windows {
//                        iconFile.set(resourceDir.file("windows/terminodiff.ico"))
//                        perUserInstall = true
//                        dirChooser = true
//                        upgradeUuid = "ECFA19D9-D1F2-4AF5-9E5E-59A8F21C3A79"
//                        menuGroup = "TerminoDiff"
//                        targetFormats(
//                            TargetFormat.Exe
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//fun isNonStable(version: String): Boolean {
//    val hasStableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
//    val hasUnstableKeyword = listOf("ALPHA", "BETA", "RC", "SNAPSHOT", "DEV").any { version.uppercase().contains(it) }
//    return when {
//        hasUnstableKeyword -> true
//        hasStableKeyword -> false
//        else -> false
//    }
//}
//
//tasks.withType<DependencyUpdatesTask> {
//    rejectVersionIf {
//        isNonStable(candidate.version)
//    }
//}