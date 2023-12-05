package cz.janjanovec.socialhubkmp.viewModel

sealed interface BasicUiState<out T> {
    data class Success<T>(val data: T) : BasicUiState<T> {
        override fun equals(other: Any?): Boolean {
            return if (this === other) {
                this.data == other.data
            } else {
                false
            }
        }

        override fun hashCode(): Int {
            return data?.hashCode() ?: 0
        }
    }
    data class LoadingOverData<T>(val data: T) : BasicUiState<T>
    data class Error(val message: Throwable? = null) : BasicUiState<Nothing>
    object Loading : BasicUiState<Nothing>
    object Empty : BasicUiState<Nothing>
    object Idle : BasicUiState<Nothing>
}
