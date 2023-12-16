package persistance

import platform.Foundation.NSUserDefaults

actual class LocalStorage {
    private val userDefaults = NSUserDefaults.standardUserDefaults
    actual fun isAppNewlyInstalled(): Boolean {
        userDefaults.objectForKey("NEW_INSTALL")?.let {
            println("ALREADY INSTALLED")
            return it as Boolean
        } ?: run {
            println("NEW INSTALL")
            userDefaults.setBool(true, "NEW_INSTALL")
            return true
        }
    }

    actual fun setAppNewlyInstalled() {
        userDefaults.setBool(false, "NEW_INSTALL")
    }
}
