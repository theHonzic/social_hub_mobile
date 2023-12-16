package persistance

import com.liftric.kvault.KVault
import cz.janjanovec.socialhubkmp.model.Account
import cz.janjanovec.socialhubkmp.model.selectable.delegates.CountryHelper
import cz.janjanovec.socialhubkmp.model.selectable.delegates.Gender
import cz.janjanovec.socialhubkmp.utils.Logger

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

    override fun saveAccount(account: Account): Boolean {
        return try {
            (
                store.set(key = "ACCOUNT_USERNAME", stringValue = account.username) &&
                store.set(key = "ACCOUNT_FIRST_NAME", stringValue = account.firstName) &&
                store.set(key = "ACCOUNT_LAST_NAME", stringValue = account.lastName) &&
                store.set(key = "ACCOUNT_EMAIL", stringValue = account.email) &&
                store.set(key = "ACCOUNT_PHONE_NUMBER", stringValue = account.phoneNumber) &&
                store.set(key = "ACCOUNT_COUNTRY", stringValue = account.country.isoCode) &&
                store.set(key = "ACCOUNT_GENDER", intValue = account.gender.genderId) &&
                account.profilePicture?.let { store.set(key = "ACCOUNT_PROFILE_PICTURE", stringValue = it) } ?: true
                )
        } catch (e: Exception) {
            Logger.log("Could not save account to KVault: ${e.message}", Logger.MessageKind.ERROR)
            false
        }
    }

    override fun getAccount(): Account? {
        return try {
            Account(
                store.string("ACCOUNT_USERNAME") ?: "",
                store.string("ACCOUNT_FIRST_NAME") ?: "",
                store.string("ACCOUNT_LAST_NAME") ?: "",
                store.int("ACCOUNT_GENDER")?.let { Gender.getGenderById(it) ?: Gender.OTHER } ?: Gender.OTHER,
                store.string("ACCOUNT_EMAIL") ?: "",
                store.string("ACCOUNT_PHONE_NUMBER") ?: "",
                store.string("ACCOUNT_COUNTRY")?.let { CountryHelper.getCountryByIsoCode(it) ?: CountryHelper.getAll().first() } ?: CountryHelper.getAll().first(),
                store.string("ACCOUNT_PROFILE_PICTURE")
            )
        } catch (e: Exception) {
            Logger.log("Could not get account from KVault: ${e.message}", Logger.MessageKind.ERROR)
            null
        }
    }
}