package terminodiff.shared.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import ca.uhn.fhir.context.FhirContext
import com.formdev.flatlaf.FlatDarkLaf
import terminodiff.shared.engine.resources.DiffDataContainer
import terminodiff.shared.i18n.SupportedLocale
import terminodiff.shared.i18n.getStrings
import terminodiff.shared.preferences.AppPreferences
import terminodiff.shared.ui.TerminodiffAppContent
import java.io.File
import javax.imageio.ImageIO
import javax.swing.UIManager

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

@Composable
fun AppWindow(
    applicationScope: ApplicationScope,
    resourcesDir: File? = null,
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
