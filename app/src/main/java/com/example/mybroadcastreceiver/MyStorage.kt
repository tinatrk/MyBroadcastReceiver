package com.example.mybroadcastreceiver

import android.content.Context
import androidx.core.content.edit

internal data class MyData(
    val actionCount: Int,
    val lastActionId: String?,
)

internal class MyStorage(context: Context) {

    private companion object {
        const val PREFS_NAME = "my_prefs"
        const val PREFS_KEY_ACTION_COUNT = "action_count"
        const val PREFS_KEY_LAST_ACTION_ID = "last_action_id"
        const val UNKNOWN_ID = "unknown_id"
    }

    private val prefs by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun incrementActionCount(lastActionId: String) {
        val currentActionCount = prefs.getInt(PREFS_KEY_ACTION_COUNT, 0)

        prefs.edit {
            putInt(PREFS_KEY_ACTION_COUNT, currentActionCount + 1)
                .putString(PREFS_KEY_LAST_ACTION_ID, lastActionId)
        }
    }

    fun getCurrentData(): MyData {
        return MyData(
            actionCount = prefs.getInt(PREFS_KEY_ACTION_COUNT, 0),
            lastActionId = prefs.getString(PREFS_KEY_LAST_ACTION_ID, null)
        )
    }

    fun clearData() {
        prefs.edit {
            putInt(PREFS_KEY_ACTION_COUNT, 0)
                .putString(PREFS_KEY_LAST_ACTION_ID, UNKNOWN_ID)
        }
    }
}