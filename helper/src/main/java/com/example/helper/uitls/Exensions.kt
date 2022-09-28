package com.example.helper.uitls

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.helper.R
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackbar(
    @StringRes messageStringId: Int,
    @StringRes actionStringId: Int = R.string.app_name,
    function: () -> Unit
) {
    Snackbar.make(
        requireView(),
        messageStringId,
        Snackbar.LENGTH_LONG
    )
        .setAction(actionStringId) { function() }
        .show()
}
fun Context.hideSoftInput(){
    val imm: InputMethodManager =
        this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow((this as Activity).window.decorView.windowToken, 0)
}



