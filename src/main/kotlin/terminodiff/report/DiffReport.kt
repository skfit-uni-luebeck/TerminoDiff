package terminodiff.terminodiff.report

import androidx.compose.runtime.snapshots.SnapshotStateList
import org.hl7.fhir.r4.model.CodeSystem
import terminodiff.engine.concepts.ConceptDiff
import terminodiff.engine.concepts.ConceptDiffItem
import terminodiff.engine.resources.DiffDataContainer
import terminodiff.i18n.EnglishStrings

class DiffReportGenerator {

    private val ls = EnglishStrings()

    fun generateDiffReport(diffDataContainer: DiffDataContainer): String {
        return buildString {
            a("# Difference Report")
            appendMetadataDiffTable(diffDataContainer)
            appendConceptDiffTable(diffDataContainer)
        }
    }

    private fun StringBuilder.appendConceptDiffTable(
        diffDataContainer: DiffDataContainer
    ) {
        appendNumericResultTable(diffDataContainer)

        a("## Removed & Added Concepts")
        appendSubsetTable(
            header = "Only Left Version",
            subsetCodeList = diffDataContainer.codeSystemDiff!!.onlyInLeftConcepts,
            diffDataContainer.leftCodeSystem!!.concept
        )
        appendSubsetTable(
            header = "Only Right Version",
            subsetCodeList = diffDataContainer.codeSystemDiff!!.onlyInRightConcepts,
            concept = diffDataContainer.rightCodeSystem!!.concept
        )

        appendSubsetDiffTable(
            header = "Concepts with Display Differences",
            valueColumnName = "Display",
            differences = diffDataContainer.codeSystemDiff!!.onlyDisplayDifferences,
            diffDataContainer = diffDataContainer,
            getter = { display }
        )
    }

    private fun StringBuilder.appendSubsetTable(
        header: String,
        subsetCodeList: SnapshotStateList<String>,
        concept: List<CodeSystem.ConceptDefinitionComponent>
    ) {
        a("### $header")
        val codeDisplayMap = concept.filter { it.code in subsetCodeList }.associate { it.code to it.display }.toList()
        drawTable(
            mapOf(
                "Code" to codeDisplayMap.map { it.first },
                "Display" to codeDisplayMap.map { it.second },
            ),
            truncate = true,
            maxColumWidth = 120
        )
    }

    private fun StringBuilder.appendSubsetDiffTable(
        header: String,
        valueColumnName: String,
        differences: Map<String, ConceptDiff>,
        diffDataContainer: DiffDataContainer,
        getter: CodeSystem.ConceptDefinitionComponent.() -> String?
    ) {
        a("### $header")

        val codeList = differences.keys.toList().sorted()
        val leftValues = codeList.map { code ->
            diffDataContainer.leftCodeSystem!!.concept.find { it.code == code }?.getter()
        }
        val rightValues = codeList.map { code ->
            diffDataContainer.rightCodeSystem!!.concept.find { it.code == code }?.getter()
        }

        drawTable(
            mapOf(
                "Code" to codeList,
                "Left $valueColumnName" to leftValues,
                "Right $valueColumnName" to rightValues
            ),
            truncate = false,
            maxColumWidth = null
        )
    }

    private fun StringBuilder.appendNumericResultTable(diffDataContainer: DiffDataContainer) {
        a("## Concept Differences Overview")
        val total = diffDataContainer.allCodes.size
        val headerDataMap = mapOf(
            "Total Left" to diffDataContainer.leftCodeSystem!!.concept.size,
            "Total Right" to diffDataContainer.rightCodeSystem!!.concept.size,
            "Only Left" to diffDataContainer.codeSystemDiff!!.onlyInLeftConcepts.size,
            "Only Right" to diffDataContainer.codeSystemDiff!!.onlyInRightConcepts.size,
            "Concept differences" to diffDataContainer.codeSystemDiff!!.conceptDifferences.values.filter { c-> c.conceptComparison.any { it.result == ConceptDiffItem.ConceptDiffResultEnum.DIFFERENT} }.size,
            "Display differences" to diffDataContainer.codeSystemDiff!!.onlyDisplayDifferences.values.size
        ).let { map ->
            val differenceKeys = listOf("Only Left", "Only Right", "Concept differences")
            val sumOfDifferences = differenceKeys.sumOf { map[it]!! }
            map.plus("Identical" to total - sumOfDifferences)
        }.mapValues { listOf(it.value.toString()) }
        drawTable(headerDataMap, truncate = false, maxColumWidth = 30, margin = 0)
    }

    private fun StringBuilder.appendMetadataDiffTable(diffDataContainer: DiffDataContainer) {
        val comparisons =
            diffDataContainer.codeSystemDiff!!.metadataDifferences.comparisons.sortedBy { it.result.ordinal }
        val items = comparisons.map { it.diffItem.label(ls) }
        val results = comparisons.map { it.result.toString() }
        val leftValues = comparisons.map { it.diffItem.getRenderDisplay(diffDataContainer.leftCodeSystem!!) }
        val rightValues = comparisons.map { it.diffItem.getRenderDisplay(diffDataContainer.rightCodeSystem!!) }
        a("## Metadata Differences")
        drawTable(
            headerDataMap = mapOf(
                "Item" to items,
                "Comparison" to results,
                "Left" to leftValues,
                "Right" to rightValues
            ),
            truncate = false,
            maxColumWidth = null
        )
    }

    private fun StringBuilder.drawTable(
        headerDataMap: Map<String, List<String?>>,
        truncate: Boolean,
        maxColumWidth: Int?,
        margin: Int = 2,
    ) {
        val numberOfRows = headerDataMap.values.maxOf { it.size }
        val lengthList = headerDataMap.mapValues { v ->
            val fromData = v.value.maxOf { s -> s?.length ?: 0 }
            val fromHeader = v.key.length
            val finalValue = (maxOf(fromData, fromHeader) + margin).let {
                when (truncate) {
                    true -> it.coerceAtMost(maxColumWidth!!)
                    else -> it
                }
            }
            return@mapValues finalValue
        }
        appendPaddedTableRow(lengthList.values.toList(), headerDataMap.keys)
        append("|")
        lengthList.values.forEach {
            append("-".repeat(it) + "|")
        }
        appendLine()
        for (i in 0 until numberOfRows) {
            val dataList = headerDataMap.mapValues { it.value.getOrNull(i) ?: "" }.map {
                val maxWidth = lengthList[it.key]!!
                when (truncate && it.value.length > maxWidth) {
                    true -> it.value.take(maxWidth) + "…"
                    else -> it.value
                }
            }
            appendPaddedTableRow(lengthList.values.toList(), dataList)
        }
        appendLine()
    }

    private fun StringBuilder.appendPaddedTableRow(lengthList: List<Int>, data: Collection<String?>) {
        if (lengthList.size != data.size) {
            throw IllegalArgumentException("lengthList and data must be the same size")
        }
        append("|")
        data.forEachIndexed { index, s ->
            append(s.orEmpty().padEnd(lengthList[index]) + "|")
        }
        appendLine()
    }


    private fun StringBuilder.a(s: String) = this.appendLine(s + "\n")
}