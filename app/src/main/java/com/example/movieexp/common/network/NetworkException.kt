package com.example.movieexp.common.network

//https://github.com/pramodk007/Clean_Architecture_Sample_Practice/blob/master/base/src/main/java/com/hong/base/exception/ResponseExceptionHandler.kt
sealed class NetworkException(message: String) : Exception(message) {
    data class BadRequestException(override val message: String) :
        NetworkException(message) // 400

    data class UnauthorizedException(override val message: String) :
        NetworkException(message) // 401

    data class ForbiddenException(override val message: String) :
        NetworkException(message) // 403

    data class NotFoundException(override val message: String) :
        NetworkException(message) // 404

    data class RequestTimeoutException(override val message: String) :
        NetworkException(message) // 408

    data class ConflictException(override val message: String) :
        NetworkException(message) // 409

    data class TooManyRequestsException(override val message: String) :
        NetworkException(message) // 429

    data class UnknownException(override val message: String) :
        NetworkException(message) // 429

    data class SocketTimeoutException(override val message: String) :
        NetworkException(message)

    data class InternalServerException(override val message: String) :
        NetworkException(message) // 500
}

//class ResponseExceptionHandler<T : Any> : SingleTransformer<T, T> {
//    override fun apply(upstream: Single<T>): Single<T> =
//        upstream.onErrorResumeNext { throwable ->
//            when (throwable) {
//                is SocketTimeoutException -> {
//                    NetworkException.SocketTimeoutException(throwable.message.orEmpty())
//                }
//                is HttpException -> {
//                    when (throwable.code()) {
//                        400 -> NetworkException.BadRequestException(throwable.message())
//                        401 -> NetworkException.UnauthorizedException(throwable.message())
//                        403 -> NetworkException.ForbiddenException(throwable.message())
//                        404 -> NetworkException.NotFoundException(throwable.message())
//                        408 -> NetworkException.RequestTimeoutException(throwable.message())
//                        409 -> NetworkException.ConflictException(throwable.message())
//                        429 -> NetworkException.TooManyRequestsException(throwable.message())
//                        500 -> NetworkException.InternalServerException(throwable.message())
//                        else -> NetworkException.UnknownException(throwable.message())
//                    }
//                }
//                else -> NetworkException.UnknownException(throwable.message.orEmpty())
//            }.let { resultException -> Single.error(resultException) }
//        }
//}