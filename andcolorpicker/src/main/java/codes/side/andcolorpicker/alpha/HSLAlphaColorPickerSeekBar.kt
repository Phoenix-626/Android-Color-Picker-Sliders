package codes.side.andcolorpicker.alpha

import android.content.Context
import android.util.AttributeSet
import codes.side.andcolorpicker.converter.IntegerHSLColorConverter
import codes.side.andcolorpicker.model.IntegerHSLColor
import codes.side.andcolorpicker.model.factory.HSLColorFactory
import codes.side.andcolorpicker.view.picker.ColorSeekBar

// TODO: Add modes support
class HSLAlphaColorPickerSeekBar @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyle: Int = androidx.appcompat.R.attr.seekBarStyle
) :
  AlphaColorPickerSeekBar<IntegerHSLColor>(
    HSLColorFactory(),
    context,
    attrs,
    defStyle
  ) {

  override val colorConverter: IntegerHSLColorConverter
    get() = super.colorConverter as IntegerHSLColorConverter

  init {
    refreshProperties()
  }

  override fun refreshProperties() {
    super.refreshProperties()
    max = IntegerHSLColor.Component.A.maxValue
  }

  override fun updateInternalPickedColorFrom(value: IntegerHSLColor) {
    super.updateInternalPickedColorFrom(value)
    internalPickedColor.setFrom(value)
  }

  override fun refreshInternalPickedColorFromProgress() {
    super.refreshInternalPickedColorFromProgress()
    val currentProgress = progress
    val currentA = internalPickedColor.intA
    val changed = if (currentA != currentProgress) {
      internalPickedColor.intA = progress
      true
    } else {
      false
    }

    if (changed) {
      notifyListenersOnColorChanged()
    }
  }

  override fun refreshProgressFromCurrentColor() {
    super.refreshProgressFromCurrentColor()
    progress = internalPickedColor.intA
  }

  interface OnColorPickListener :
    ColorSeekBar.OnColorPickListener<HSLAlphaColorPickerSeekBar, IntegerHSLColor>

  open class DefaultOnColorPickListener : OnColorPickListener {
    override fun onColorPicking(
      picker: HSLAlphaColorPickerSeekBar,
      color: IntegerHSLColor,
      value: Int,
      fromUser: Boolean
    ) {

    }

    override fun onColorPicked(
      picker: HSLAlphaColorPickerSeekBar,
      color: IntegerHSLColor,
      value: Int,
      fromUser: Boolean
    ) {

    }

    override fun onColorChanged(
      picker: HSLAlphaColorPickerSeekBar,
      color: IntegerHSLColor,
      value: Int
    ) {

    }
  }
}