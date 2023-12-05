package persistance

import android.content.Context
import com.liftric.kvault.KVault

actual class KVaultFactory(
    private val context: Context
) {
    actual fun generate(): KVault {
        return KVault(context, "cz.janjanovec.socialhubkmp")
    }
}