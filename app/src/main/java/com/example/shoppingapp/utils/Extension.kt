package com.example.shoppingapp.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.showToast(view: View, msg:String){
    Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()
}