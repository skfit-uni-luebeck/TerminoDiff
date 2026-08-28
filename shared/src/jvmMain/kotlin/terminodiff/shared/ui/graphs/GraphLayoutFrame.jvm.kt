package terminodiff.shared.ui.graphs

import org.hl7.fhir.r4.model.CodeSystem
import org.jgrapht.Graph
import terminodiff.shared.engine.graph.CodeSystemGraphBuilder
import terminodiff.shared.engine.graph.DiffEdge
import terminodiff.shared.engine.graph.DiffNode
import terminodiff.shared.i18n.LocalizedStrings
import terminodiff.shared.java.ui.CodeSystemGraphJFrame
import terminodiff.shared.java.ui.DiffGraphJFrame

actual fun codeSystemGraphLayoutFrame(
    codeSystem: CodeSystem,
    useDarkTheme: Boolean,
    localizedStrings: LocalizedStrings,
    frameTitle: String
) {
    val graphBuilder = CodeSystemGraphBuilder(codeSystem = codeSystem, localizedStrings)
    CodeSystemGraphJFrame(graphBuilder.graph, useDarkTheme, localizedStrings, frameTitle) { c: String ->
        graphBuilder.nodeTree[c]?.display ?: "no display"
    }
}

actual fun diffGraphLayoutFrame(
    diffGraph: Graph<DiffNode, DiffEdge>,
    useDarkTheme: Boolean,
    localizedStrings: LocalizedStrings,
    frameTitle: String
) {
    DiffGraphJFrame(diffGraph, useDarkTheme, localizedStrings, frameTitle)
}