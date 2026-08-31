package terminodiff.shared.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Suppress("CheckReturnValue")
val icon_markdown_copy: ImageVector
  get() {
    if (_markdown_copy != null) {
      return _markdown_copy!!
    }
    _markdown_copy =
      ImageVector.Builder(
          name = "markdown_copy",
          defaultWidth = 24.dp,
          defaultHeight = 24.dp,
          viewportWidth = 24f,
          viewportHeight = 24f,
        )
        .apply {
          path(
            fill = SolidColor(Color.Black),
            fillAlpha = 1f,
            stroke = null,
            strokeAlpha = 1f,
            strokeLineWidth = 1f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Bevel,
            strokeLineMiter = 1f,
            pathFillType = PathFillType.NonZero,
          ) {
            moveTo(9f, 18f)
            quadTo(8.18f, 18f, 7.59f, 17.41f)
            reflectiveQuadTo(7f, 16f)
            verticalLineTo(4f)
            quadTo(7f, 3.17f, 7.59f, 2.59f)
            reflectiveQuadTo(9f, 2f)
            horizontalLineToRelative(9f)
            quadToRelative(0.82f, 0f, 1.41f, 0.59f)
            reflectiveQuadTo(20f, 4f)
            verticalLineTo(16f)
            quadToRelative(0f, 0.82f, -0.59f, 1.41f)
            reflectiveQuadTo(18f, 18f)
            horizontalLineTo(9f)
            close()
            moveTo(9f, 16f)
            horizontalLineToRelative(9f)
            verticalLineTo(4f)
            horizontalLineTo(9f)
            verticalLineTo(16f)
            close()
            moveTo(5f, 22f)
            quadTo(4.18f, 22f, 3.59f, 21.41f)
            reflectiveQuadTo(3f, 20f)
            verticalLineTo(6f)
            horizontalLineTo(5f)
            verticalLineTo(20f)
            horizontalLineTo(16f)
            verticalLineToRelative(2f)
            horizontalLineTo(5f)
            close()
            moveToRelative(5.25f, -9f)
            horizontalLineToRelative(1.5f)
            verticalLineTo(8.5f)
            horizontalLineToRelative(1f)
            verticalLineToRelative(3f)
            horizontalLineToRelative(1.5f)
            verticalLineToRelative(-3f)
            horizontalLineToRelative(1f)
            verticalLineTo(13f)
            horizontalLineToRelative(1.5f)
            verticalLineTo(8f)
            quadToRelative(0f, -0.43f, -0.29f, -0.71f)
            reflectiveQuadTo(15.75f, 7f)
            horizontalLineToRelative(-4.5f)
            quadTo(10.83f, 7f, 10.54f, 7.29f)
            reflectiveQuadTo(10.25f, 8f)
            verticalLineToRelative(5f)
            close()
            moveTo(9f, 16f)
            verticalLineTo(4f)
            verticalLineTo(16f)
            close()
          }
        }
        .build()
    return _markdown_copy!!
  }

private var _markdown_copy: ImageVector? = null
