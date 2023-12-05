package cz.janjanovec.socialhubkmp.model.selectable

interface Selectable {
    val id: String
    val listIcon: String
    val listTitle: String
    val listDescription: String?
    val onSelectedColor: String?
}