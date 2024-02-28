package com.emoting.android.feature.signup.setprofile

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.compose.AsyncImage
import com.emoting.android.R
import com.emoting.android.data.util.RetrofitClient
import com.emoting.android.feature.signup.SignUpData
import com.emoting.designsystem.ui.button.ButtonColor
import com.emoting.designsystem.ui.button.EmotingButton
import com.emoting.designsystem.ui.theme.EmotingColors
import com.emoting.designsystem.ui.theme.EmotingTypography
import com.emoting.designsystem.ui.topbar.EmotingTopBar
import com.emoting.designsystem.utils.clickable

@Composable
internal fun SetProfileScreen(
    onBackPressed: () -> Unit,
    navigateToLanding: () -> Unit,
    signUpData: SignUpData,
    viewModel: SetProfileViewModel = viewModel(factory = SetProfileViewModelFactory(RetrofitClient.getAuthApi())),
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val activityResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = viewModel::setUri,
    )

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            when (it) {
                is SetProfileSideEffect.SuccessSignUp -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.success_sign_up),
                        Toast.LENGTH_SHORT
                    ).show()
                    navigateToLanding()
                }

                is SetProfileSideEffect.AlreadyExists -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.conflict_email),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        EmotingTopBar(
            onBackPressed = onBackPressed,
            title = stringResource(id = R.string.sign_up),
        )
        Text(
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 40.dp,
            ),
            text = stringResource(id = R.string.set_profile),
            style = EmotingTypography.TitleMedium,
        )
        ProfileImage(
            uri = state.profileImageUri,
            onClick = {
                val mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                val request = PickVisualMediaRequest(mediaType)
                activityResultLauncher.launch(request)
            },
        )
        Spacer(modifier = Modifier.weight(1f))
        EmotingButton(
            modifier = Modifier.offset(y = 20.dp),
            text = stringResource(id = R.string.later),
            color = ButtonColor.Secondary,
            onClick = { viewModel.onLaterClick(signUpData = signUpData) },
        )
        EmotingButton(
            text = stringResource(id = R.string.next),
            color = ButtonColor.Primary,
            onClick = { viewModel.onNextClick(signUpData = signUpData) },
            enabled = state.buttonEnabled,
        )
    }
}

@Composable
private fun ProfileImage(
    uri: Uri?,
    onClick: () -> Unit,
) {
    Box(modifier = Modifier.clickable(onClick = onClick)) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(EmotingColors.Gray200),
            contentAlignment = Alignment.Center,
        ) {
            if (uri == null) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_gallery),
                    contentDescription = "gallery",
                )
            } else {
                // TODO AsyncImage 사용하기
                AsyncImage(
                    model = uri,
                    contentDescription = "profile",
                    contentScale = ContentScale.Crop,
                )
            }
        }
        if (uri == null) {
            Icon(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clip(CircleShape)
                    .background(EmotingColors.Gray100)
                    .padding(8.dp),
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "add",
                tint = EmotingColors.Gray500,
            )
        }
    }
}
