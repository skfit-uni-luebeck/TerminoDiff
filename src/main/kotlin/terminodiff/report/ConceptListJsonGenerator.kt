package terminodiff.terminodiff.report

import terminodiff.ui.panes.conceptdiff.TableData

class ConceptListJsonGenerator {
    fun generateCodeListJson(chipFilteredTableData: TableData): String {
        val joinedList = chipFilteredTableData.shownCodes.sorted().joinToString(",") { "\"${it}\"" }
        return "[${joinedList}]"
    }

}