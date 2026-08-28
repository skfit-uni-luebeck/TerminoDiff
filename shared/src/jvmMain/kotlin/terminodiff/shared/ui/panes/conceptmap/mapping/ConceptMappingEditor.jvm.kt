package terminodiff.shared.ui.panes.conceptmap.mapping

import terminodiff.shared.engine.conceptmap.ConceptMapElement
import terminodiff.shared.i18n.LocalizedStrings
import terminodiff.shared.java.ui.NeighborhoodJFrame

actual fun showElementNeighborhood(
    focusElement: ConceptMapElement,
    useDarkTheme: Boolean,
    localizedStrings: LocalizedStrings
) {
    val neighborhoodDisplay = focusElement.neighborhood
    NeighborhoodJFrame(
        /* graph = */ neighborhoodDisplay.getNeighborhoodGraph(),
        /* focusCode = */ neighborhoodDisplay.focusCode,
        /* isDarkTheme = */ useDarkTheme,
        /* localizedStrings = */ localizedStrings,
        /* frameTitle = */ localizedStrings.graphFor_.invoke(focusElement.code.value)
    ).apply {
        addClickListener { delta ->
            val newValue = neighborhoodDisplay.changeLayers(delta)
            this.setGraph(neighborhoodDisplay.getNeighborhoodGraph())
            newValue
        }
    }
}