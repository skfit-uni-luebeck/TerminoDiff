package terminodiff.shared.ui.panes.loaddata.panes

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import li.flor.nativejfilechooser.NativeJFileChooser
import terminodiff.shared.i18n.LocalizedStrings
import terminodiff.shared.preferences.AppPreferences
import terminodiff.shared.engine.resources.InputResource
import terminodiff.shared.ui.util.LabeledTextField
import terminodiff.shared.ui.AppIconResource
import terminodiff.shared.ui.AppImageIcon
import terminodiff.shared.ui.LoadListener
import terminodiff.shared.ui.icons.icon_folder_open
import terminodiff.shared.ui.icons.icon_splitscreen_left
import terminodiff.shared.ui.icons.icon_splitscreen_right
import terminodiff.shared.ui.theme.contentColor
import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter
import kotlin.io.path.invariantSeparatorsPathString


@Composable
fun FromFileScreenWrapper(
    localizedStrings: LocalizedStrings,
    onLoadLeft: LoadListener,
    onLoadRight: LoadListener,
) {
    var selectedPath: String by remember { mutableStateOf("") }
    val selectedFile: File by derivedStateOf { File(selectedPath) }
    FromFileScreen(
        localizedStrings = localizedStrings,
        selectedFile = selectedFile,
        selectedPath = selectedPath,
        onChangeFilePath = {
            selectedPath = it ?: ""
        },
        onLoadLeftFile = {
            onLoadLeft(it)
            selectedPath = ""
        },
        onLoadRightFile = {
            onLoadRight(it)
            selectedPath = ""
        })
}

@Composable
private fun FromFileScreen(
    localizedStrings: LocalizedStrings,
    selectedFile: File?,
    onChangeFilePath: (String?) -> Unit,
    onLoadLeftFile: (InputResource) -> Unit,
    onLoadRightFile: (InputResource) -> Unit,
    selectedPath: String,
) = Column(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onPrimary
    )
    val isValidPath by derivedStateOf {
        when {
            selectedFile == null -> false
            selectedFile.exists() -> true
            else -> false
        }
    }
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        LabeledTextField(
            modifier = Modifier.weight(0.6f),
            value = selectedPath,
            onValueChange = onChangeFilePath,
            labelText = localizedStrings.fileSystem
        )
        Button(modifier = Modifier.weight(0.15f), onClick = {
            val newFile = showLoadFileDialog(localizedStrings.loadFromFile)
            newFile?.let {
                onChangeFilePath.invoke(it.absolutePath)
                AppPreferences.fileBrowserDirectory = it.toPath().parent.invariantSeparatorsPathString
            }
        }) {
            Icon(icon_folder_open, localizedStrings.open)
            Text(localizedStrings.open)
        }
    }


    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(
            modifier = Modifier.padding(4.dp),
            colors = buttonColors,
            enabled = isValidPath,
            onClick = { onLoadLeftFile(InputResource(InputResource.Kind.FILE, selectedFile)) }) {
            Icon(
                icon_splitscreen_left,
                contentDescription = localizedStrings.loadLeft,
                tint = buttonColors.contentColor(isValidPath)
            )
            Text(localizedStrings.loadLeft, color = buttonColors.contentColor(isValidPath))
        }
        Button(
            modifier = Modifier.padding(4.dp),
            colors = buttonColors,
            enabled = isValidPath,
            onClick = { onLoadRightFile(InputResource(InputResource.Kind.FILE, selectedFile)) }) {
            Icon(
                imageVector = icon_splitscreen_right,
                contentDescription = localizedStrings.loadRight,
                tint = buttonColors.contentColor(isValidPath)
            )
            Text(localizedStrings.loadRight, color = buttonColors.contentColor(isValidPath))
        }
    }
}

private fun getFileChooser(title: String): JFileChooser {
    return NativeJFileChooser(AppPreferences.fileBrowserDirectory).apply {
        dialogTitle = title
        isAcceptAllFileFilterUsed = false
        addChoosableFileFilter(FileNameExtensionFilter("FHIR+JSON (*.json)", "json", "JSON"))
        addChoosableFileFilter(FileNameExtensionFilter("FHIR+XML (*.xml)", "xml", "XML"))
    }
}

fun showLoadFileDialog(title: String): File? = getFileChooser(title).let { chooser ->
    when (chooser.showOpenDialog(null)) {
        JFileChooser.CANCEL_OPTION -> null
        JFileChooser.APPROVE_OPTION -> {
            return@let chooser.selectedFile?.absoluteFile
        }

        else -> null
    }
}