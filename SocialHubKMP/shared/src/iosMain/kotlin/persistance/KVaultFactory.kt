package persistance

import com.liftric.kvault.KVault

actual class KVaultFactory {
    actual fun generate(): KVault {
        return KVault("cz.janjanovec.socialhubkmp")
    }
}