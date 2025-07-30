package terminodiff.ui.panes.conceptmap

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea
import org.fife.ui.rsyntaxtextarea.SyntaxConstants
import org.fife.ui.rsyntaxtextarea.Theme
import org.fife.ui.rtextarea.RTextScrollPane
import java.awt.BorderLayout
import javax.swing.JFrame
import javax.swing.JPanel

fun showJsonViewer(jsonText: String, isDarkTheme: Boolean) = showRoCodeViewer(
    codeText = jsonText,
    syntax = SyntaxConstants.SYNTAX_STYLE_JSON,
    isDarkTheme = isDarkTheme,
    titleText = "FHIR JSON"
)

fun showRoCodeViewer(
    codeText: String,
    syntax: String,
    isDarkTheme: Boolean,
    titleText: String,
    rows: Int = 40,
    columns: Int = 80,
) {
    RoTextEditor(
        codeText = codeText,
        syntax = syntax,
        isDarkTheme = isDarkTheme,
        titleText = titleText,
        rows = rows,
        columns = columns,
    ).apply {
        isVisible = true
    }
}

class RoTextEditor(
    codeText: String,
    syntax: String,
    isDarkTheme: Boolean,
    titleText: String,
    rows: Int = 40,
    columns: Int = 80,
) : JFrame() {
    init {
        val cp = JPanel(BorderLayout()).apply {
            val textArea = RSyntaxTextArea(rows, columns).apply {
                syntaxEditingStyle = syntax
                isCodeFoldingEnabled = true
                antiAliasingEnabled = true
                isEditable = false
            }
            applyTheme(isDarkTheme, textArea)
            val sp = RTextScrollPane(textArea).apply {
                textArea.text = codeText
            }
            add(sp)
        }
        contentPane = cp
        title = titleText
        defaultCloseOperation = DISPOSE_ON_CLOSE
        pack()
        setLocationRelativeTo(null)
    }

    private fun applyTheme(darkTheme: Boolean, textArea: RSyntaxTextArea) {
        val filename = "/org/fife/ui/rsyntaxtextarea/themes/${if (darkTheme) "dark.xml" else "default.xml"}"
        val theme = Theme.load(javaClass.getResourceAsStream(filename))
        theme.apply(textArea)
    }
}