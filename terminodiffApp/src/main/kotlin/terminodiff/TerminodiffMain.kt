package terminodiff

import androidx.compose.ui.window.*
import ca.gosyer.appdirs.AppDirs
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.core.config.Configurator
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory
import org.apache.logging.log4j.kotlin.Logging
import terminodiff.shared.app.AppWindow
import java.io.File
import java.nio.file.Path
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.io.path.absolutePathString

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
    AppWindow(this, resourcesDir)
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


