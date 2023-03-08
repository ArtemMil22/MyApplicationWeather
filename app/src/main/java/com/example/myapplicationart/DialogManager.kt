package com.example.myapplicationart

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat

const val TAG = "AlertDialog"

object DialogManager {

    fun searchByNameDialog(context: Context, listener: Listener){
        val builder = AlertDialog.Builder(context)
        val edName = EditText(context)
        builder.setView(edName)
        val dialog = builder.create()
        dialog.setTitle("City name:")
        dialog.setButton(AlertDialog.BUTTON_POSITIVE,"Ok"){ _, _ ->
            listener.onClick(edName.text.toString())
            dialog.dismiss()
        }
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Cancel"){ _, _ ->
            dialog.dismiss()
        }
        dialog.show()
    }

    interface Listener{
        fun onClick(name:String?)
    }

    @SuppressLint("ResourceAsColor")
    fun showAlertDialog( context: Context) {

        fun showToast(messageRes: String) {
            Toast.makeText(context, messageRes, Toast.LENGTH_SHORT).show()
        }

        val listener = DialogInterface.OnClickListener { _, which ->

            when (which) {
                DialogInterface.BUTTON_POSITIVE -> showToast("R.string.action_ignore")
                DialogInterface.BUTTON_NEGATIVE -> showToast("R.string.action_ignore")
                DialogInterface.BUTTON_NEUTRAL ->  showToast("R.string.action_ignore")
            }
        }


        val dialog = AlertDialog.Builder(context)
            .setCancelable(false)
            .setIcon(R.mipmap.ic_launcher_round)
            .setTitle("R.string.default_alert_title")
            .setMessage("R.string.default_alert_message")
            .setPositiveButton("OK", listener)
            .setNegativeButton("CANCEL", listener)
            .setNeutralButton("IGNORE", listener)
            .setOnCancelListener {
                showToast("R.string.action_ignore")
            }
            .setOnDismissListener {
                Log.d(TAG, "Dialog dismissed")
            }
            .create()
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(context,R.color.white)))
        dialog.show()
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context,R.color.black))
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(context,R.color.black))
        dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(ContextCompat.getColor(context,R.color.black))
    }



}