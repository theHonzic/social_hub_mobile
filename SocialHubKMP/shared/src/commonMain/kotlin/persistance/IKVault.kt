package persistance

interface IKVault {
    fun clearAll()
    fun saveString(key: String, value: String): Boolean
    fun getString(key: String): String?
}