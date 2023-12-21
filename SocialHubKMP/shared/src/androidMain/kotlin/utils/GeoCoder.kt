package utils

import android.content.Context
import cz.janjanovec.socialhubkmp.model.location.Coordinates
import cz.janjanovec.socialhubkmp.model.location.Placemark

actual class GeoCoder(
    context: Context
) {
    actual suspend fun getCoordinates(address: String): Coordinates? {
        TODO("Not yet implemented")
    }

    actual suspend fun getPlace(coordinates: Coordinates): Placemark? {
        TODO("Not yet implemented")
    }
}