package cz.janjanovec.socialhubkmp.utils

data class AlertContainer(
    var title: String,
    var message: String,
    var isPresented: Boolean = false
)