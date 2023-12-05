package cz.janjanovec.socialhubkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform