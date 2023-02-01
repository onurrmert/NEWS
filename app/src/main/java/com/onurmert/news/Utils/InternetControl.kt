package com.onurmert.retro4fitt.Utils

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import com.google.android.material.snackbar.Snackbar

class InternetControl() {

    companion object{

        fun connectionControl(context: Context) : Boolean{
            val manager =
                context.applicationContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = manager.activeNetworkInfo

            if (networkInfo == null) {
                return true
            }else{
                return false
            }
        }

        fun internetSnackMessage(connection : Boolean, view : View){
            if (connection){
                Snackbar.make(view,
                    "Your internet connection has been interrupted!!!",
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction("Ok", View.OnClickListener {
                        internetSnackMessage(connection, view)
                    }).show()
            }else{
                return
            }
        }
    }
}