plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    jvm()
    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.components.splitpane)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.org.jetbrains.kotlin.reflect)
            implementation(libs.org.jetbrains.kotlinx.coroutines.core)
            implementation(libs.ca.uhn.hapi.fhir.base)
            implementation(libs.ca.uhn.hapi.fhir.structures.r4)
            implementation(libs.ca.uhn.hapi.fhir.validation)
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
    }
}