package persistance

import cz.janjanovec.socialhubkmp.model.account.Account

interface IKVault {
    fun clearAll()
    fun saveString(key: String, value: String): Boolean
    fun getString(key: String): String?
    fun saveAccount(account: Account): Boolean
    fun getAccount(): Account?
}