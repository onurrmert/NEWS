package com.onurmert.news.Utils

import android.app.ProgressDialog
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProgresDialog1 {

    companion object{

        fun dialog(context: Context, message: String){
            CoroutineScope(Dispatchers.Main).launch {
                val progresDialog = ProgressDialog(context)
                progresDialog.setMessage(message)
                progresDialog.show()
                delay(400)
                progresDialog.dismiss()
            }
        }
    }
}