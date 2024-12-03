package com.afs.tutrd.feature.sign.sreen.phone

import android.app.Activity
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afs.tutrd.core.model.auth.param.RequestVerificationCodeParam
import com.afs.tutrd.core.model.auth.response.RequestVerificationCodeResponse
import com.easyhz.tutrd.domain.auth.useacse.RequestVerificationCodeUseCase
import com.easyhz.tutrd.domain.auth.useacse.SignInWithPhoneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun PhoneScreen(
    viewModel: PhoneViewModel = hiltViewModel()
) {
    val context = LocalContext.current as Activity
    Button(
        onClick = {
            viewModel.sign(context)
        }
    ) { }
}


@HiltViewModel
class PhoneViewModel @Inject constructor(
    private val requestVerificationCodeUseCase: RequestVerificationCodeUseCase,
    private val signInWithPhoneUseCase: SignInWithPhoneUseCase,
) : ViewModel() {
    fun sign(activity: Activity) = viewModelScope.launch {
        val param = RequestVerificationCodeParam(
            phoneNumber = "01012345678",
            activity = activity
        )
        val response = requestVerificationCodeUseCase.invoke(param).getOrNull()
        when(response) {
            is RequestVerificationCodeResponse.ReturnCodeSent -> {
                println("> verificationId: ${response.verificationId}, phoneNumber: ${response.phoneNumber}")
                signInWithPhoneUseCase.invoke(response.verificationId, "123456").onSuccess {
                    println("> uid: $it")
                }.onFailure {
                    println("> error: $it")
                }
            }
            else -> {
                println("> error: $response")
            }
        }
        println("> response: $response")
    }
}