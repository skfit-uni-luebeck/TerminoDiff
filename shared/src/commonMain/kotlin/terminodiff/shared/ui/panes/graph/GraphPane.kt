package terminodiff.shared.ui.panes.graph

import org.hl7.fhir.r4.model.CodeSystem
import org.jgrapht.Graph
import terminodiff.shared.engine.graph.DiffEdge
import terminodiff.shared.engine.graph.DiffNode
import terminodiff.shared.i18n.LocalizedStrings

expect fun showDiffGraph(diffGraph: Graph<DiffNode, DiffEdge>,
                         frameTitle: String,
                         useDarkTheme: Boolean,
                         localizedStrings: LocalizedStrings,)

expect fun showDiffGraph(
    codeSystem: CodeSystem,
    frameTitle: String,
    useDarkTheme: Boolean,
    localizedStrings: LocalizedStrings,
)