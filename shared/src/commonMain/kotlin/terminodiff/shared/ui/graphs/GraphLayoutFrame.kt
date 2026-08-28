package terminodiff.shared.ui.graphs

import org.hl7.fhir.r4.model.CodeSystem
import org.jgrapht.Graph
import terminodiff.shared.engine.graph.CodeSystemGraphBuilder
import terminodiff.shared.engine.graph.DiffEdge
import terminodiff.shared.engine.graph.DiffNode
import terminodiff.shared.i18n.LocalizedStrings

expect fun codeSystemGraphLayoutFrame(
    codeSystem: CodeSystem,
    useDarkTheme: Boolean,
    localizedStrings: LocalizedStrings,
    frameTitle: String
)



expect fun diffGraphLayoutFrame(
    diffGraph: Graph<DiffNode, DiffEdge>,
    useDarkTheme: Boolean,
    localizedStrings: LocalizedStrings,
    frameTitle: String
)