package cz.janjanovec.socialhubkmp.useCases.location

import cz.janjanovec.socialhubkmp.model.location.Coordinates
import cz.janjanovec.socialhubkmp.model.location.UserLocation
import cz.janjanovec.socialhubkmp.utils.Logger
import dev.icerock.moko.geo.LocationTracker
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import utils.GeoCoder

class GetUserLocationUseCase(
    private val locationTracker: LocationTracker,
    private val geoCoder: GeoCoder
) {
    suspend fun invoke(): Flow<Result<UserLocation>> = flow {
        try {
            locationTracker.startTracking()
            locationTracker.getLocationsFlow().first().let {
                locationTracker.stopTracking()
                emit(Result.success(UserLocation(Coordinates(it.latitude, it.longitude))))
                geoCoder.getPlace(Coordinates(it.latitude, it.longitude))
                    ?.let { placemark ->
                        emit(Result.success(UserLocation(Coordinates(it.latitude, it.longitude), placemark)))
                    }
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}