package terminodiff.ui.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ToggleableChip(
    name: String,
    text: String,
    isSelected: Boolean = false,
    onSelectionChanged: (String) -> Unit = {},
) {
    val enabledColor = colorScheme.primary
    val disabledColor = colorScheme.primary.copy(alpha = 0.5f)
    val enabledTextColor = contentColorFor(enabledColor)
    val disabledTextColor = contentColorFor(disabledColor)
    val containerTextColor by derivedStateOf {
        when (isSelected) {
            true -> enabledColor to enabledTextColor
            else -> disabledColor to disabledTextColor
        }
    }
    Button(
        onClick = {
            onSelectionChanged(name)
        },
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerTextColor.first,
            contentColor = containerTextColor.second,
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
fun ToggleableChipGroup(
    specs: List<ToggleableChipSpec>,
    selectedItem: String?,
    onSelectionChanged: (String) -> Unit,
    filterCounts: Map<String, Int>
) {
    Column(modifier = Modifier.padding(2.dp)) {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)) {
            items(specs) { spec ->
                ToggleableChip(
                    name = spec.name,
                    text = "${spec.text} (${filterCounts[spec.name]})",
                    isSelected = spec.name == selectedItem,
                    onSelectionChanged = { name ->
                        onSelectionChanged(name)
                    }
                )
            }
        }
    }
}

data class ToggleableChipSpec(
    val name: String,
    val text: String,
) {
    companion object
}