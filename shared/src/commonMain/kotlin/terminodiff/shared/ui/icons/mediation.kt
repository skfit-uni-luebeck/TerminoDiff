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
val icon_icon_mediation: ImageVector
  get() {
    if (_mediation != null) {
      return _mediation!!
    }
    _mediation =
      ImageVector.Builder(
          name = "mediation",
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
            moveTo(5f, 23f)
            quadTo(3.75f, 23f, 2.88f, 22.13f)
            reflectiveQuadTo(2f, 20f)
            reflectiveQuadTo(2.88f, 17.88f)
            reflectiveQuadTo(5f, 17f)
            quadToRelative(0.68f, 0f, 1.3f, 0.31f)
            reflectiveQuadToRelative(1.05f, 0.84f)
            quadTo(8.83f, 17.35f, 9.75f, 16f)
            reflectiveQuadTo(10.9f, 13f)
            horizontalLineTo(7.8f)
            quadTo(7.5f, 13.9f, 6.73f, 14.45f)
            reflectiveQuadTo(5f, 15f)
            quadTo(3.75f, 15f, 2.88f, 14.13f)
            reflectiveQuadTo(2f, 12f)
            reflectiveQuadTo(2.88f, 9.88f)
            reflectiveQuadTo(5f, 9f)
            quadTo(5.95f, 9f, 6.73f, 9.55f)
            reflectiveQuadTo(7.8f, 11f)
            horizontalLineToRelative(3.1f)
            quadTo(10.68f, 9.35f, 9.75f, 8f)
            quadTo(8.83f, 6.65f, 7.35f, 5.85f)
            quadTo(6.93f, 6.38f, 6.3f, 6.69f)
            reflectiveQuadTo(5f, 7f)
            quadTo(3.75f, 7f, 2.88f, 6.13f)
            reflectiveQuadTo(2f, 4f)
            reflectiveQuadTo(2.88f, 1.88f)
            reflectiveQuadTo(5f, 1f)
            reflectiveQuadTo(7.1f, 1.85f)
            reflectiveQuadTo(8f, 3.95f)
            quadTo(10.03f, 5f, 11.35f, 6.85f)
            reflectiveQuadTo(12.95f, 11f)
            horizontalLineTo(18.2f)
            lineTo(16.6f, 9.4f)
            lineTo(18f, 8f)
            lineToRelative(4f, 4f)
            lineToRelative(-4f, 4f)
            lineTo(16.6f, 14.6f)
            lineTo(18.2f, 13f)
            horizontalLineTo(12.95f)
            quadToRelative(-0.28f, 2.3f, -1.58f, 4.16f)
            quadTo(10.08f, 19.02f, 8f, 20.05f)
            quadTo(7.95f, 21.3f, 7.1f, 22.15f)
            reflectiveQuadTo(5f, 23f)
            close()
          }
        }
        .build()
    return _mediation!!
  }

private var _mediation: ImageVector? = null
