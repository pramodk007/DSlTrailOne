package com.example.movieexp.common.utils.extension

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.IBinder
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.helper.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun Fragment.showDialogLoading(): AlertDialog =
    AlertDialog.Builder(requireContext()).run {
        //setView(R.layout.)
        setCancelable(false)
    }.create().apply {
        setCanceledOnTouchOutside(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

fun Fragment.showSoftKeyboard(windowToken: IBinder?, show: Boolean) {
    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    when (show) {
        true -> imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        else -> imm.hideSoftInputFromWindow(windowToken, 0)
    }
}

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

inline fun Fragment.observeFlows(crossinline observationFunction: suspend (CoroutineScope) -> Unit) {
    viewLifecycleOwner.lifecycle.coroutineScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            observationFunction(this)
        }
    }
}