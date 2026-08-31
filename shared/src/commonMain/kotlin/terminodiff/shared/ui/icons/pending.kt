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
val icon_pending: ImageVector
  get() {
    if (_pending != null) {
      return _pending!!
    }
    _pending =
      ImageVector.Builder(
          name = "pending",
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
            moveTo(8.06f, 13.06f)
            quadTo(8.5f, 12.63f, 8.5f, 12f)
            reflectiveQuadTo(8.06f, 10.94f)
            reflectiveQuadTo(7f, 10.5f)
            reflectiveQuadTo(5.94f, 10.94f)
            reflectiveQuadTo(5.5f, 12f)
            reflectiveQuadToRelative(0.44f, 1.06f)
            reflectiveQuadTo(7f, 13.5f)
            reflectiveQuadTo(8.06f, 13.06f)
            close()
            moveToRelative(5f, 0f)
            quadTo(13.5f, 12.63f, 13.5f, 12f)
            reflectiveQuadTo(13.06f, 10.94f)
            reflectiveQuadTo(12f, 10.5f)
            reflectiveQuadToRelative(-1.06f, 0.44f)
            reflectiveQuadTo(10.5f, 12f)
            reflectiveQuadToRelative(0.44f, 1.06f)
            reflectiveQuadTo(12f, 13.5f)
            reflectiveQuadToRelative(1.06f, -0.44f)
            close()
            moveToRelative(5f, 0f)
            quadTo(18.5f, 12.63f, 18.5f, 12f)
            reflectiveQuadTo(18.06f, 10.94f)
            reflectiveQuadTo(17f, 10.5f)
            reflectiveQuadToRelative(-1.06f, 0.44f)
            reflectiveQuadTo(15.5f, 12f)
            reflectiveQuadToRelative(0.44f, 1.06f)
            reflectiveQuadTo(17f, 13.5f)
            reflectiveQuadToRelative(1.06f, -0.44f)
            close()
            moveTo(12f, 22f)
            quadTo(9.93f, 22f, 8.1f, 21.21f)
            quadTo(6.28f, 20.43f, 4.93f, 19.08f)
            quadTo(3.58f, 17.73f, 2.79f, 15.9f)
            reflectiveQuadTo(2f, 12f)
            quadTo(2f, 9.92f, 2.79f, 8.1f)
            quadTo(3.58f, 6.27f, 4.93f, 4.93f)
            quadTo(6.28f, 3.57f, 8.1f, 2.79f)
            quadTo(9.93f, 2f, 12f, 2f)
            reflectiveQuadToRelative(3.9f, 0.79f)
            reflectiveQuadToRelative(3.17f, 2.14f)
            quadToRelative(1.35f, 1.35f, 2.14f, 3.17f)
            quadTo(22f, 9.92f, 22f, 12f)
            reflectiveQuadToRelative(-0.79f, 3.9f)
            reflectiveQuadToRelative(-2.14f, 3.17f)
            quadToRelative(-1.35f, 1.35f, -3.17f, 2.14f)
            reflectiveQuadTo(12f, 22f)
            close()
            moveToRelative(0f, -2f)
            quadToRelative(3.35f, 0f, 5.68f, -2.32f)
            reflectiveQuadTo(20f, 12f)
            reflectiveQuadTo(17.68f, 6.32f)
            reflectiveQuadTo(12f, 4f)
            reflectiveQuadTo(6.33f, 6.32f)
            reflectiveQuadTo(4f, 12f)
            reflectiveQuadToRelative(2.33f, 5.68f)
            reflectiveQuadTo(12f, 20f)
            close()
            moveToRelative(0f, -8f)
            close()
          }
        }
        .build()
    return _pending!!
  }

private var _pending: ImageVector? = null
