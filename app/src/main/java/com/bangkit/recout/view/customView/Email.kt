package com.bangkit.recout.view.customView

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import androidx.core.content.ContextCompat
import com.bangkit.recout.R

class Email @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.editTextStyle
) : androidx.appcompat.widget.AppCompatEditText(context, attrs, defStyleAttr) {

    init {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
                    showError(resources.getString(R.string.error_email))
                } else {
                    hideError()
                }
            }
            override fun afterTextChanged(s: Editable) {}
        })
    }

    private fun showError(errorText: String) {
        val errorIcon: Drawable? = ContextCompat.getDrawable(context, R.drawable.baseline_error_24)
        setError(errorText, errorIcon?.apply { setBounds(0, 0, intrinsicWidth, intrinsicHeight) })
        setCompoundDrawables(null, null, errorIcon, null)
    }

    private fun hideError() {
        error = null
        setCompoundDrawables(null, null, null, null)
    }
}