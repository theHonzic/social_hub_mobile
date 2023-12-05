package persistance

import com.liftric.kvault.KVault

expect class KVaultFactory {
    fun generate(): KVault
}