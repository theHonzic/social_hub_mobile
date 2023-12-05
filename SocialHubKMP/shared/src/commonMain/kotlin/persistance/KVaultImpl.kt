package persistance

import com.liftric.kvault.KVault

class KVaultImpl(
    private val store: KVault
): IKVault {
    override fun clearAll() {
        store.clear()
    }

    override fun saveString(key: String, value: String): Boolean {
        if (value == "") {
            return store.deleteObject(forKey = key)
        }
        return store.set(key = key, stringValue = value)
    }

    override fun getString(key: String): String? {
        return store.string(forKey = key)
    }
}