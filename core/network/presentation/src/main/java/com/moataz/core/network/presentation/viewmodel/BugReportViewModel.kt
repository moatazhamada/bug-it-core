package com.moataz.core.network.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.core.network.domain.model.BugEntity
import com.moataz.core.network.domain.usecase.FetchBugsUseCase
import com.moataz.core.network.domain.usecase.UploadBugUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BugReportViewModel @Inject constructor(
    private val fetchBugsUseCase: FetchBugsUseCase,
    private val uploadBugUseCase: UploadBugUseCase
) : ViewModel() {

    private val _imageUri = MutableLiveData<Uri?>()
    val imageUri: LiveData<Uri?> = _imageUri

    private val _bugUploadStatus = MutableLiveData<Result<Boolean>>()
    val bugUploadStatus: LiveData<Result<Boolean>> = _bugUploadStatus

    // Function to set the image URI when a user selects an image
    fun setImageUri(uri: Uri?) {
        _imageUri.value = uri
    }

    // Function to upload the bug
    fun uploadBug(description: String) {
        val imageUriValue = _imageUri.value

        if (imageUriValue != null) {
            // Create BugEntity with image URI and description
            val bug = BugEntity(screenshotUrl = imageUriValue.toString(), description = description)

            viewModelScope.launch {
                val result = uploadBugUseCase(bug)
                _bugUploadStatus.value = result
            }
        }
    }
}
