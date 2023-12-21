package utils

import cz.janjanovec.socialhubkmp.model.location.Coordinates
import cz.janjanovec.socialhubkmp.model.location.Placemark
import cz.janjanovec.socialhubkmp.model.selectable.delegates.CountryHelper
import cz.janjanovec.socialhubkmp.utils.Logger
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.CoreLocation.CLGeocoder
import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLPlacemark
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

actual class GeoCoder() {
    private val geoCoder = CLGeocoder()
    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun getCoordinates(address: String): Coordinates? {
        var coordinates: Coordinates? = null
        geoCoder.geocodeAddressString(
            addressString = address,
            completionHandler = { placemarks, _ ->
            (placemarks as List<CLPlacemark>).firstOrNull()?.let {
                it.location?.coordinate?.useContents {
                    coordinates = Coordinates(latitude, longitude)
                }
            }
        })
        return coordinates
    }

    actual suspend fun getPlace(coordinates: Coordinates): Placemark? {
        return suspendCoroutine { continuation ->
            geoCoder.reverseGeocodeLocation(
                location = CLLocation(
                    coordinates.latitude,
                    coordinates.longitude
                ),
                completionHandler = { placemarks, _ ->
                    val placemark = (placemarks as List<CLPlacemark>).firstOrNull()?.let {
                        Logger.log("Placemark found: $it")
                        Placemark(
                            street = it.thoroughfare,
                            placeName = it.name,
                            city = it.locality,
                            country = it.ISOcountryCode?.let {
                                CountryHelper.getCountryByIsoCode(it)
                            },
                            postalCode = it.postalCode,
                            district = it.subLocality,
                            administrativeArea = it.administrativeArea,
                            subAdministrativeArea = it.subAdministrativeArea
                        )
                    }
                    continuation.resume(placemark)
                }
            )
        }
    }
}