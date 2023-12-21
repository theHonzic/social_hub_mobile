package utils

import cz.janjanovec.socialhubkmp.model.location.Coordinates
import cz.janjanovec.socialhubkmp.model.location.Placemark

expect class GeoCoder {
    suspend fun getCoordinates(address: String): Coordinates?
    suspend fun getPlace(coordinates: Coordinates): Placemark?
}