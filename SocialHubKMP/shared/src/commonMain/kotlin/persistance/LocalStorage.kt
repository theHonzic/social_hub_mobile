package persistance

expect class LocalStorage {
    fun isAppNewlyInstalled(): Boolean
    fun setAppNewlyInstalled()
}