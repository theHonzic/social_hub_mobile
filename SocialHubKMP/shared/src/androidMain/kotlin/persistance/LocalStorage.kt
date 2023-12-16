package persistance

import android.content.Context
import java.lang.reflect.Array.set

actual class LocalStorage(private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("cz.janjanovec.socialhubkmm", Context.MODE_PRIVATE)

    fun set(key: String, value: Any) {
        val editor = sharedPreferences.edit()
        when (value) {
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
            is Long -> editor.putLong(key, value)
            is Float -> editor.putFloat(key, value)
            is Boolean -> editor.putBoolean(key, value)
            else -> throw IllegalArgumentException("Unsupported value type")
        }
        editor.apply()
    }

    fun get(key: String): Any? {
        return when {
            sharedPreferences.contains(key) -> {
                val value = sharedPreferences.all[key]
                // Handle different value types here if needed
                value
            }
            else -> null
        }
    }
    actual fun isAppNewlyInstalled(): Boolean {

        get("NEW_INSTALL")?.let {
            return it as Boolean
        } ?: run {
            set("NEW_INSTALL", true)
            return true
        }
    }

    actual fun setAppNewlyInstalled() {
        set("NEW_INSTALL", false)
    }
}