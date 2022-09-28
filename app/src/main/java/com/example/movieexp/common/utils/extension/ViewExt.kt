package com.example.movieexp.common.utils.extension

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.*
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.helper.uitls.showSoftKeyboard
import com.google.android.material.snackbar.Snackbar

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.setVisible(visible: Boolean, invisible: Boolean? = false) {
    visibility = when {
        visible -> View.VISIBLE
        invisible == true -> View.INVISIBLE
        else -> View.GONE
    }
}

fun ImageView.loadFromUrl(url: String) =
    Glide.with(this.context.applicationContext)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)

fun View.hideKeyboard() {
    val inputMethod: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethod.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showKeyboard() {
    val inputMethod: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethod.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

inline fun View.afterMeasured(crossinline f: View.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}

@BindingAdapter(value = ["singleClick", "hiddenKeyboard"], requireAll = false)
fun View.singleClickListener(singleClick: (() -> Unit)? = null, hiddenKeyboard: Boolean? = false) {
    setOnClickListener {
        singleClick?.invoke()
        isClickable = false
        when (hiddenKeyboard) {
            true -> context.showSoftKeyboard(false)
        }

        postDelayed({
            isClickable = true
        }, 300L)
    }
}

@BindingAdapter("circleUrl")
fun ImageView.circleUrl(url: String?) = url?.let {
    Glide.with(context)
        .load(it)
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}

fun EditText.afterTextChange(afterTextChanged: (String) -> Unit) {

    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s.toString())
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    })
}

fun EditText.textString(): String {
    return this.text.toString()
}

fun EditText.textStringTrim(): String {
    return this.textString().trim()
}

fun EditText.isEmpty(): Boolean {
    return this.textString().isEmpty()
}

fun EditText.isTrimEmpty(): Boolean {
    return this.textStringTrim().isEmpty()
}

fun TextView.textString(): String {
    return this.text.toString()
}

fun TextView.textStringTrim(): String {
    return this.textString().trim()
}

fun TextView.isEmpty(): Boolean {
    return this.textString().isEmpty()
}

fun TextView.isTrimEmpty(): Boolean {
    return this.textStringTrim().isEmpty()
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visibleOrGone(flag:Boolean) {
    visibility = if(flag){
        View.VISIBLE
    }else{
        View.GONE
    }
}

fun View.visibleOrInvisible(flag:Boolean) {
    visibility = if(flag){
        View.VISIBLE
    }else{
        View.INVISIBLE
    }
}

fun View.gone() {
    visibility = View.GONE
}

/**
 * 将view转为bitmap
 */
@Deprecated("use View.drawToBitmap()")
fun View.toBitmap(scale: Float = 1f, config: Bitmap.Config = Bitmap.Config.ARGB_8888): Bitmap? {
    if (this is ImageView) {
        if (drawable is BitmapDrawable) return (drawable as BitmapDrawable).bitmap
    }
    this.clearFocus()
    val bitmap = createBitmapSafely(
        (width * scale).toInt(),
        (height * scale).toInt(),
        config,
        1
    )
    if (bitmap != null) {
        Canvas().run {
            setBitmap(bitmap)
            save()
            drawColor(Color.WHITE)
            scale(scale, scale)
            this@toBitmap.draw(this)
            restore()
            setBitmap(null)
        }
    }
    return bitmap
}

fun createBitmapSafely(width: Int, height: Int, config: Bitmap.Config, retryCount: Int): Bitmap? {
    try {
        return Bitmap.createBitmap(width, height, config)
    } catch (e: OutOfMemoryError) {
        e.printStackTrace()
        if (retryCount > 0) {
            System.gc()
            return createBitmapSafely(width, height, config, retryCount - 1)
        }
        return null
    }
}


var lastClickTime = 0L
fun View.clickNoRepeat(interval: Long = 500, action: (view: View) -> Unit) {
    setOnClickListener {
        val currentTime = System.currentTimeMillis()
        if (lastClickTime != 0L && (currentTime - lastClickTime < interval)) {
            return@setOnClickListener
        }
        lastClickTime = currentTime
        action(it)
    }
}


fun Any?.notNull(notNullAction:(value:Any) ->Unit,nullAction1:() ->Unit){
    if(this!=null){
        notNullAction.invoke(this)
    }else{
        nullAction1.invoke()
    }
}

fun View.snackbar(message: String){
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()
}
