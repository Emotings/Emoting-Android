package com.emoting.android.data.api

import com.emoting.android.data.model.request.auth.PostSignInRequest
import com.emoting.android.data.model.request.auth.PostSignUpRequest
import com.emoting.android.data.model.response.PostSignInResponse
import retrofit2.http.Body
import retrofit2.http.POST

internal interface AuthApi {
    @POST(RequestUrl.Auth.LOGIN)
    suspend fun postSignIn(
        @Body postSignInRequest: PostSignInRequest,
    ): PostSignInResponse

    @POST(RequestUrl.Auth.SIGN_UP)
    suspend fun postSignUp(
        @Body postSignUpRequest: PostSignUpRequest,
    )
}
