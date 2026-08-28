package terminodiff.shared.ui.panes.graph

import org.hl7.fhir.r4.model.CodeSystem
import org.jgrapht.Graph
import terminodiff.shared.engine.graph.DiffEdge
import terminodiff.shared.engine.graph.DiffNode
import terminodiff.shared.i18n.LocalizedStrings
import terminodiff.shared.ui.graphs.codeSystemGraphLayoutFrame
import terminodiff.shared.ui.graphs.diffGraphLayoutFrame

actual fun showDiffGraph(
    diffGraph: Graph<DiffNode, DiffEdge>,
    frameTitle: String,
    useDarkTheme: Boolean,
    localizedStrings: LocalizedStrings
) {
    diffGraphLayoutFrame(diffGraph, useDarkTheme, localizedStrings, frameTitle)
}

actual fun showDiffGraph(
    codeSystem: CodeSystem,
    frameTitle: String,
    useDarkTheme: Boolean,
    localizedStrings: LocalizedStrings
) {
    codeSystemGraphLayoutFrame(codeSystem, useDarkTheme, localizedStrings, frameTitle)
}