// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package terminodiff

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import ca.gosyer.appdirs.AppDirs
import ca.uhn.fhir.context.FhirContext
import com.formdev.flatlaf.FlatDarkLaf
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.core.config.Configurator
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory
import org.apache.logging.log4j.kotlin.Logging
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import terminodiff.engine.resources.DiffDataContainer
import terminodiff.i18n.SupportedLocale
import terminodiff.i18n.getStrings
import terminodiff.preferences.AppPreferences
import terminodiff.terminodiff.ui.TerminodiffAppContent
import java.io.File
import java.nio.file.Path
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.imageio.ImageIO
import javax.swing.UIManager
import kotlin.io.path.absolutePathString

/**
 * just for creating the log
 */
object TerminoDiffApp : Logging

val appDirs by lazy {
    AppDirs {
        appName = "TerminoDiff"
        appAuthor = "de.uzl.imbs.skfit"
    }
}

val resourcesDir = System.getProperty("compose.application.resources.dir")?.let { path ->
    // this only works in the native distribution, (this includes when using `runDistributable` in Gradle/IntelliJ)
    // otherwise, resourcesDir will be `null`
    File(path)
}

fun main() = application {
    configureFileLogging()
    AppWindow(this)
}

fun configureFileLogging() {
    val configBuilder = ConfigurationBuilderFactory.newConfigurationBuilder()
    val currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
    val logFileName = appDirs.getUserLogDir().let {
        Path.of(it)
    }.resolve("terminodiff-$currentDateTime.log")
    val config = configBuilder.add(
        configBuilder.newAppender("StdOut", "CONSOLE").add(configBuilder.newLayout("JsonTemplateLayout"))
    ).add(
        configBuilder.newAppender(
            "File", "File"
        ).addAttribute("fileName", logFileName).add(configBuilder.newLayout("JsonTemplateLayout"))
    ).add(
        configBuilder.newRootLogger(Level.INFO).add(configBuilder.newAppenderRef("StdOut"))
            .add(configBuilder.newAppenderRef("File"))
    ).build(false)
    Configurator.reconfigure(config)
    LogManager.getRootLogger().info("Writing log to: ${logFileName.absolutePathString()}")
}

@Composable
fun AppWindow(
    applicationScope: ApplicationScope
) {
    FlatDarkLaf.setup()

    Window(
        onCloseRequest = { applicationScope.exitApplication() },
        state = WindowState(size = DpSize(1366.dp, 768.dp), position = WindowPosition(Alignment.Center))
    ) {
        this.window.title = "TerminoDiff"
        resourcesDir?.let {
            this.window.iconImage = ImageIO.read(it.resolve("terminodiff@0.5x.png"))
        }
        UIManager.setLookAndFeel(FlatDarkLaf())
        LocalizedContent()
    }
}


@OptIn(ExperimentalSplitPaneApi::class)
@Composable
fun LocalizedContent() {
    var useDarkTheme by remember { mutableStateOf(AppPreferences.darkModeEnabled) }
    var locale by remember { mutableStateOf(SupportedLocale.valueOf(AppPreferences.language)) }
    val localizedStrings by derivedStateOf { getStrings(locale) }
    val fhirContext = remember { FhirContext.forR4() }
    val diffDataContainer = remember { DiffDataContainer(fhirContext, localizedStrings) }
    TerminodiffAppContent(
        localizedStrings = localizedStrings,
        diffDataContainer = diffDataContainer,
        fhirContext = fhirContext,
        useDarkTheme = useDarkTheme,
        onLocaleChange = {
            locale = when (locale) {
                SupportedLocale.DE -> SupportedLocale.EN
                SupportedLocale.EN -> SupportedLocale.DE
            }
            AppPreferences.language = locale.name
            TerminoDiffApp.logger.info("changed locale to ${locale.name}")
            diffDataContainer.localizedStrings = getStrings(locale)
        },
        onChangeDarkTheme = {
            useDarkTheme = !useDarkTheme
            AppPreferences.darkModeEnabled = useDarkTheme
        },
    )
}


