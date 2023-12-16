package cz.janjanovec.socialhubkmp.model.selectable.delegates

import cz.janjanovec.socialhubkmp.model.selectable.Selectable
import kotlinx.serialization.Serializable

enum class Gender(
    val genderId: Int,
    override val listIcon: String,
    val genderName: String
): Selectable {
    MALE(0, "🤠", "Male"),
    FEMALE(1, "👧🏼", "Female"),
    OTHER(2, "🧚🏻‍", "Other");
    override val id: String
        get() = genderId.toString()
    override val listDescription: String?
        get() = null
    override val onSelectedColor: String?
        get() = null
    override val listTitle: String
        get() = genderName

    companion object {
        fun getGenderById(id: Int): Gender? =
            values().find { it.genderId == id }
    }
}
