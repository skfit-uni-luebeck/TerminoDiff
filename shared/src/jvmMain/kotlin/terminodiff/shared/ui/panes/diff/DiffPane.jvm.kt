package terminodiff.shared.ui.panes.diff

import terminodiff.shared.i18n.LocalizedStrings
import terminodiff.shared.java.ui.NeighborhoodJFrame

actual fun showNeighborhoodJFrame(
    neighborhoodDisplay: NeighborhoodDisplay,
    useDarkTheme: Boolean,
    localizedStrings: LocalizedStrings
) {
    NeighborhoodJFrame(
        /* graph = */ neighborhoodDisplay.getNeighborhoodGraph(),
        /* focusCode = */ neighborhoodDisplay.focusCode,
        /* isDarkTheme = */ useDarkTheme,
        /* localizedStrings = */ localizedStrings,
        /* frameTitle = */ localizedStrings.graph
    ).apply {
        addClickListener { delta ->
            val newValue = neighborhoodDisplay.changeLayers(delta)
            this.setGraph(neighborhoodDisplay.getNeighborhoodGraph())
            newValue
        }
    }
}