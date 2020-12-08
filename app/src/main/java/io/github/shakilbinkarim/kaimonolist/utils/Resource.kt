package io.github.shakilbinkarim.kaimonolist.utils

enum class Status { SUCCESS, ERROR, LOADING }

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {

        fun <T> onSuccess(data: T?): Resource<T> = Resource(Status.SUCCESS, data, null)

        fun <T> onError(message: String, data: T?) = Resource(Status.ERROR, data, message)

        fun <T> onLoading(data: T?): Resource<T> = Resource(Status.LOADING, data, null)

    }
}
