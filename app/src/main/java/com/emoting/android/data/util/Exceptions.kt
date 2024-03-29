package com.emoting.android.data.util

data object BadRequestException : RuntimeException()
data object UnAuthorizedException : RuntimeException()
data object ForbiddenException : RuntimeException()
data object NotFoundException : RuntimeException()
data object MethodNotAllowedException : RuntimeException()
data object ConflictException : RuntimeException()
data object TooManyRequestException : RuntimeException()
data object ServerException : RuntimeException()
