package com.example.mybroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Process
import android.util.Log

class MyBroadcastReceiver : BroadcastReceiver() {
    private companion object {
        const val LOG_TAG = "BroadcastExample"

        const val ACTION = "com.example.mybroadcastreceiver.my_action"

        const val EXTRAS_KEY_ACTION_ID = "actionId"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(LOG_TAG, "[processId: ${Process.myPid()}] MyBroadcastReceiver -> onReceive")

        if (intent?.action != ACTION) {
            return
        }

        val actionId = intent.getStringExtra(EXTRAS_KEY_ACTION_ID)

        Log.d(LOG_TAG, "[processId: ${Process.myPid()}] MyBroadcastReceiver -> receive action (id: $actionId)")
        if (actionId != null && context != null) {
            Log.d(LOG_TAG, "[processId: ${Process.myPid()}] MyBroadcastReceiver -> save action into prefs")
            MyStorage(context).incrementActionCount(actionId)
        }
    }
}